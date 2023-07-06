package util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import sv.utils.ExceptionHandler;
import sv.utils.TypConverter;

/**
 * @author SV-Benutzer
 *
 */
public class DateiUploadHelper extends sv.utils.Helper {
    private final TypConverter typConverter;

    private final ExceptionHandler exceptionHandler;

    /**
     * Default-Konstruktor
     *
     */
    public DateiUploadHelper() {
        this.exceptionHandler = new ExceptionHandler();
        this.typConverter = new TypConverter();
    }

    /**
     * @param byteArray
     *            Bytes aus dem Request
     * @param mapOfByteInputs
     *            Bytes der hochgeladenen Files, pro File
     * @param bytesTrenner
     *            Trenner in Request
     * @param params
     *            Requestparameter
     */
    public Map<String, String> findeParameterUndDateiInhalte(final byte[] byteArray,
            final Map<String, byte[]> mapOfByteInputs, final byte[] bytesTrenner, final Map<String, String[]> params,
            final String request) {
        Map<String, String> retVal = null;
        try {
            if ((byteArray != null) && (mapOfByteInputs != null) && (bytesTrenner != null) && (params != null)) {
                boolean continueReading = true;
                int startAt = bytesTrenner.length + 1;
                final int firstEndIndex = findeNaechstenUmbruch(byteArray, startAt);
                int endIndex = firstEndIndex;
                while (continueReading) {
                    if (startAt != (bytesTrenner.length + 1)) {
                        startAt = endIndex + 2;
                    }
                    if (endIndex != firstEndIndex) {
                        endIndex = findeNaechstenUmbruch(byteArray, startAt);
                    }
                    if (endIndex >= (byteArray.length - 2)) {
                        continueReading = false;
                        this.log.debug("Beendet, endIndex=" + endIndex);
                    } else if ((endIndex - startAt) < 200) {
                        String zeile = new String(Arrays.copyOfRange(byteArray, startAt + 1, endIndex + 1));
                        // this.log.debug("Zeile=" + zeile + ", startAt=" +
                        // startAt
                        // + ", endIndex=" + endIndex);
                        if (zeile.startsWith("Content-Disposition:")) {
                            final String[] strArray = zeile.split(";");
                            if ((strArray.length == 3) && (strArray[2].contains("filename="))
                                    && (!strArray[2].contains("filename=\"\"")) && (strArray[1].contains("name="))) {
                                String formElement = this.typConverter.getGetrimmtenString(strArray[1]);
                                formElement = formElement.substring("name=\"".length(), formElement.length() - 1);
                                this.log.debug("Es wurde " + formElement + " gefunden.");
                                strArray[2] = this.typConverter.getGetrimmtenString(strArray[2]);
                                if (strArray[2].length() > 11) {
                                    if (retVal == null) {
                                        retVal = new HashMap<>();
                                    }
                                    retVal.put(formElement,
                                            strArray[2].substring("filename=\"".length(), strArray[2].length() - 1));
                                }
                                startAt = endIndex + 2;
                                endIndex = findeNaechstenUmbruch(byteArray, startAt);
                                zeile = new String(Arrays.copyOfRange(byteArray, startAt + 1, endIndex + 1));
                                this.log.debug("Nun analysiere " + zeile);
                                if ((zeile.startsWith("Content-Type: ") && zeile.contains("zip"))
                                        || (zeile.startsWith("Content-Type: text/")
                                                || (zeile.startsWith("Content-Type: application/octet-stream")))) {
                                    startAt = endIndex + 2;
                                    endIndex = findeNaechstenUmbruch(byteArray, startAt);
                                    startAt = endIndex + 2;
                                    endIndex = findeVorkommenVonTrenner(mapOfByteInputs, byteArray, bytesTrenner,
                                            startAt, formElement);
                                }
                            } else if (strArray.length == 2) {
                                this.log.debug("Analysiere " + zeile);
                                String formElement = this.typConverter.getGetrimmtenString(strArray[1]);
                                formElement = formElement.substring("name=\"".length(), formElement.length() - 1);
                                startAt = endIndex + 2;
                                endIndex = findeNaechstenUmbruch(byteArray, startAt);
                                startAt = endIndex + 2;
                                endIndex = findeNaechstenUmbruch(byteArray, startAt);
                                zeile = new String(Arrays.copyOfRange(byteArray, startAt + 1, endIndex + 1));
                                this.log.debug("Zeile=" + zeile + ", startAt=" + startAt + ", endIndex=" + endIndex);
                                params.put(formElement, new String[] { zeile });
                                this.log.info("Parameter " + formElement + ", Wert: " + zeile);
                            }
                        }
                    }
                }
            } else {
                if ((byteArray != null)) {
                    this.log.error("byteArray ist null");
                }
                if ((mapOfByteInputs != null)) {
                    this.log.error("mapOfByteInputs ist null");
                }
                if ((bytesTrenner != null)) {
                    this.log.error("bytesTrenner ist null");
                }
                if ((params != null)) {
                    this.log.error("params ist null");
                }
            }
        } catch (final Exception e) {
            this.log.error(this.exceptionHandler.getErrorMeldung(e, request));
        }
        this.log.info("Map = " + retVal);
        return retVal;
    }

    private int findeVorkommenVonTrenner(final Map<String, byte[]> mapOfByteInputs, final byte[] byteArr,
            final byte[] bytesTrenner, int from, final String formElement) {
        // final String bTrenner = new String(bytesTrenner);
        boolean wurdeTrennerGefunden = false;
        int endIndex = from;
        int i = endIndex + 2;
        final int startFrom = from;
        final int lengthTrenner = bytesTrenner.length;
        while (!wurdeTrennerGefunden) {
            i = endIndex + 2;
            endIndex = findeNaechstenUmbruch(byteArr, from);
            from = endIndex + 2;
            if (endIndex >= (byteArr.length - 2)) {
                wurdeTrennerGefunden = true;
                endIndex = byteArr.length - 2;
            } else if ((endIndex - i) >= lengthTrenner) {
                final byte[] copiedArray = Arrays.copyOfRange(byteArr, (endIndex - lengthTrenner) + 1, endIndex + 1);
                // this.log.info("Vergleiche: " + new String(copiedArray)
                // + " mit " + bTrenner);
                if (Arrays.equals(bytesTrenner, copiedArray)) {
                    wurdeTrennerGefunden = true;
                    mapOfByteInputs.put(formElement,
                            Arrays.copyOfRange(byteArr, startFrom + 1, (endIndex - lengthTrenner) - 1));
                }
            }
        }
        return endIndex;
    }

    /**
     * @param byteArr
     *            Byte Array
     * @param request
     *            HttpServletRequest
     * @return Trenner fuer Multipart-Request
     */
    public byte[] findeTrenner(final byte[] byteArr, final HttpServletRequest request) {
        final int len = "multipart/form-data; boundary=".length();
        final int lenSep = request.getContentType().substring(len).length();
        return Arrays.copyOf(byteArr, lenSep + 2);
    }

    /**
     *
     * Kopiert den Servlet input Stream
     *
     * @param request
     *            HttpServletRequest
     *
     * @return Byte arrays mit File aus
     */
    public byte[] getByteArrayVonRequest(final HttpServletRequest request, final String requestString) {
        byte[] byteArray = null;
        if (request != null) {
            try (final ServletInputStream sis = request.getInputStream()) {
                byteArray = new byte[request.getContentLength()];
                int i = 0;
                for (int c; (c = sis.read()) != -1;) {
                    byteArray[i] = (byte) c;
                    i++;
                }
            } catch (final Exception e) {
                this.log.error(this.exceptionHandler.getErrorMeldung(e, requestString));
            }
        }
        return byteArray;
    }

    /**
     *
     * Kopiert den Servlet input Stream
     *
     * @param requestOrPart
     *            HttpServletRequest oder Part
     *
     * @return Byte arrays mit File aus
     */
    public byte[] getByteArrayVonRequestOrPart(final Object requestOrPart, final String request) {
        byte[] byteArray = null;
        if (requestOrPart != null) {
            int length = 0;
            if (requestOrPart instanceof HttpServletRequest) {
                length = ((HttpServletRequest) requestOrPart).getContentLength();
            }
            if (requestOrPart instanceof Part) {
                length = (int) ((Part) requestOrPart).getSize();
            }
            try (final InputStream sis = (requestOrPart instanceof HttpServletRequest)
                    ? (InputStream) ((HttpServletRequest) requestOrPart).getInputStream()
                    : ((Part) requestOrPart).getInputStream()) {
                byteArray = new byte[length];
                int i = 0;
                for (int c; (c = sis.read()) != -1;) {
                    byteArray[i] = (byte) c;
                    i++;
                }
            } catch (final Exception e) {
                this.log.error(this.exceptionHandler.getErrorMeldung(e, request));
            }
        }
        return byteArray;
    }

    /**
     * der IE gibt den ganzen Pfad, waehrend FF und Chrome nur den Dateinamen in
     * die param map schreiben. Diese Methode vereinheitlicht dies.
     */
    public String[] einheitlicheDateNamen(final Map<String, String[]> map) {
        final List<String> dateiNamen = new ArrayList<>();
        for (final Map.Entry<String, String[]> entry : map.entrySet()) {
            String thisDateiname = entry.getValue()[0];
            final int letzterPfadSeperator = thisDateiname.lastIndexOf('\\');
            if (!entry.getKey().contains("contentForm") && !entry.getKey().contains("javax.faces.ViewState")) {
                if ((letzterPfadSeperator > 0) && (thisDateiname.length() > letzterPfadSeperator)) {
                    thisDateiname = thisDateiname.substring(letzterPfadSeperator + 1);
                }
                dateiNamen.add(thisDateiname);
                this.log.debug(entry.getKey() + " / " + entry.getValue() + " / " + entry.getValue()[0] + " / "
                        + thisDateiname);
            }
        }
        return dateiNamen.toArray(new String[dateiNamen.size()]);
    }
}
