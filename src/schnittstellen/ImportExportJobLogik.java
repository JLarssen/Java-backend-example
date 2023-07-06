package schnittstellen;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import beans.dto.PosnrBinfDTO;
import beans.dto.TarifwertDTO;
import sv.utils.TypConverter;
import constants.Constants_Typ;
import constants.Constants_Werte;
import sv.utils.DateiHandler;
import sv.utils.ExceptionHandler;
import sv.utils.Helper;
import sv.utils.beans.ProtokollDTO;
import constants.Constants_Allgemein;
import constants.Constants_Datumsformat;
import constants.Constants_Protokoll;

/**
 * @author larsju
 */
public class ImportExportJobLogik {
    private static Logger log = LoggerFactory.getLogger(ImportExportJobLogik.class);

    private final static ExceptionHandler exceptionHandler = new ExceptionHandler();

    private static TypConverter typConverter = new TypConverter();

    private final Helper helper;

    private final ProtokollDTO protokollDTO;

    private final DateiHandler fehlerDateiHandler = new DateiHandler();

    /**
     * Default-Konstruktor
     */
    public ImportExportJobLogik() {
        this.helper = new Helper();
        this.protokollDTO = new ProtokollDTO();
    }

    /**
     * liefert die Ueberschrift fuer die Excel-Tabelle (Tarifdatenbankexport),
     *
     * @return String
     */
    public String getTabellenheader() {
        final StringBuffer sb = new StringBuffer();
        // sb.append("Update").append(Constants_Werte.STRICHPUNKT);
        sb.append("Pos.Nr.").append(Constants_Werte.STRICHPUNKT);
        sb.append("Kurztext").append(Constants_Werte.STRICHPUNKT);
        // sb.append("Tarifwert_ID").append(Constants_Werte.STRICHPUNKT);
        sb.append("Tariftyp").append(Constants_Werte.STRICHPUNKT);
        // sb.append("SL Vergütung").append(Constants_Werte.STRICHPUNKT);
        // sb.append("GL Vergütung").append(Constants_Werte.STRICHPUNKT);
        // sb.append("GL KoaHalbe").append(Constants_Werte.STRICHPUNKT);
        sb.append("Gueltig ab").append(Constants_Werte.STRICHPUNKT);
        // sb.append("Gueltig bis").append(Constants_Werte.STRICHPUNKT);
        sb.append("Punktewert").append(Constants_Werte.STRICHPUNKT);
        sb.append("Punkte").append(Constants_Werte.STRICHPUNKT);
        sb.append("Tarifwert").append(Constants_Werte.STRICHPUNKT);
        sb.append("Waehrung");// .append(Constants_Werte.STRICHPUNKT);
        return (sb.toString());
    }

    /**
     * @param tempSplitZeile
     *            eine aus .csv importierte Zeile
     * @param zeilenNr
     *            die Zeilennummer in der .csv
     * @return die siebenstellige Positionsnummer oder 'empty String' falls Posnr formal schlecht ist
     */
    public String pruefePosnr(final String[] tempSplitZeile, final int zeilenNr) {
        String tempStr = "";
        // Spalte mit Index 0 validieren (Posnr)
        if (tempSplitZeile != null) {
            tempStr = tempSplitZeile[Constants_Werte.SPALTENINDEX_POSNR].trim();
            if (!this.helper.isEmptyString(tempStr)) {
                if ((tempStr.length() <= 7) && this.helper.isStringNumerisch(tempStr)
                        && !this.helper.isStringAusgenullt(tempStr)) {
                    if (tempStr.length() < 7) {
                        // fuehrende Nullen erzeugen, da Excel beim Oeffnen der Datei diese automatisch entfernt
                        tempStr = this.helper.linksFuellen(tempStr, 7, "0");
                    }
                } else {
                    log.info("FEHLER bei Tabellenzeile " + zeilenNr
                            + ":  Es gibt zu dieser Zeile keine gueltige Pos.nr.");
                }
            }
        }
        return tempStr;
    }

    /**
     * @param fehlerDatei
     *            der Name der Fehlerdatei
     * @param request
     *            ein String
     */
    public void initFehlerDatei(final String fehlerDatei, final String request) {
        if (!this.helper.isEmptyString(fehlerDatei)) {
            this.fehlerDateiHandler.oeffneDateiZumSchreiben(fehlerDatei, false, Constants_Allgemein.ENCODING_EXCEL,
                    false, request);
            this.fehlerDateiHandler.schreibeInDatei(this.getTabellenheader(), request);
        }
    }

    /**
     * @param request
     *            ein String
     */
    public void schliesseFehlerDatei(final String request) {
        this.fehlerDateiHandler.schliesseDatei(request);
    }

    /**
     * @param tempSplitZeile
     *            der zerlegte String, der wieder zusammengesetzt wird
     *            und in die Fehlerdatei geschrieben wird (von den threads aufgerufen)
     * @param request
     *            ein String
     */
    public synchronized void loggeFehlerDatensatz(final String[] tempSplitZeile, final String request) {
        String tempZeile = "";
        if (tempSplitZeile != null) {
            for (final String s : tempSplitZeile) {
                tempZeile = tempZeile + s.concat(Constants_Werte.STRICHPUNKT);
            }
        }
        this.fehlerDateiHandler.schreibeInDatei(tempZeile, request);
        this.getProtokollDTO()
                .plus(Constants_Protokoll.ANZAHL_DATEN_FEHLER_DATEI + " (Tabellenzeilen in Fehlerdatei: ");
    }

    /**
     * @return the protokollDTO
     */
    public ProtokollDTO getProtokollDTO() {
        return (this.protokollDTO);
    }

    /**
     * gibt die Anzahl der Nachkommastellen eines Strings zurueck
     * ACHTUNG Nullen werden mitgezaehlt
     *
     * @param test_string
     *            mit Komma als Separator
     * @param delimiter
     *            das Zeichen fuer das Komma
     * @return int mit Anzahl der Nachkommastellen
     */
    public int anzahlDezimalstellen(String test_string, final String delimiter) {
        int anz_dezimalst = 0;
        if (!test_string.isEmpty()) {
            final int kommastelle = test_string.indexOf(delimiter);
            if (kommastelle > 0) {
                test_string = test_string.substring(kommastelle);
                anz_dezimalst = test_string.length() - 1;
            }
        }
        return anz_dezimalst;
    }

    /**
     * liefert true zurueck, wenn das Sql_pruef_datum >= datum_von, sowie Sql_pruef_datum < datum_bis ist
     * und keines der uebergebenen Daten null ist
     */
    public boolean isInbetweenSqlDatesNotNull(final java.sql.Date datum_von, final java.sql.Date datum_bis,
            final java.sql.Date pruef_datum) {
        if ((pruef_datum != null) && (datum_von != null) && (datum_bis != null)) {
            return ((datum_von.before(pruef_datum) || datum_von.equals(pruef_datum)) && datum_bis.after(pruef_datum));
        } else {
            return false;
        }
    }

    /**
     * @param notesktxt
     *            Kurztext aus import .csv-Datei, kann auch leer sein
     * @param posnrBinfDTO_alt
     *            ein PosntBinfDTO welches aus der DB geholt wurde
     *            wurde bereits auf null geprueft
     * @return wurde der Kurztext geaendert?
     */
    public boolean isPosnrBinfGeaendert(final String notesktxt, final PosnrBinfDTO posnrBinfDTO_alt) {
        boolean rueckgabe;
        if (!(notesktxt.equals(posnrBinfDTO_alt.getNotesktxt().trim()))) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * valdiert die gelesene Zeile aus der .csv-Datei und
     * schreibt eventuelle Fehlerlogs
     *
     * @param tempSplitZeile
     *            die soeben gelesene .csv Zeile der Tarif-Importtabelle
     * @param zeilenNr
     *            die Zeilennummer der .csv Datei
     * @return TarifwertDTO_update mit zu importierenden Tarifwerten
     *         falls 'null' zurueckgeliefert wird, war die Validierung nicht erfolgreich
     */
    public TarifwertDTO pruefeTabellenzeile(final String[] tempSplitZeile, final int zeilenNr, final String request) {
        // folgende Spalten werden nicht validiert: 'notesktxt' und 'waehrung' sondern
        // nur auf Ungleichheit geprueft
        TarifwertDTO tarifwertDTO_update = null;
        if (tempSplitZeile != null) {
            try {
                HashMap<Boolean, BigDecimal> bigDecimalOK = new HashMap<>(1);
                boolean zeileOK = true;
                tarifwertDTO_update = new TarifwertDTO();
                // Spalte Tariftyp validieren
                final String tariftyp_aktuell = tempSplitZeile[Constants_Werte.SPALTENINDEX_TARIFTYP].trim();
                if (zeileOK) {
                    if (!(tariftyp_aktuell.equals(Constants_Typ.TARIF_TYP_GELDLEISTER)
                            || tariftyp_aktuell.equals(Constants_Typ.TARIF_TYP_SACHLEISTER)
                            || tariftyp_aktuell.equals(Constants_Typ.TARIF_TYP_GELDLEISTER_MGZ)
                            || tariftyp_aktuell.equals(Constants_Typ.TARIF_TYP_SACHLEISTUNG))) {
                        log.info("FEHLER bei Tabellenzeile " + zeilenNr
                                + ": Der Tariftyp zu dieser Zeile hat kein gueltiges Format.");
                        zeileOK = false;
                    } else {
                        tarifwertDTO_update.setTarif_typ(tariftyp_aktuell);
                    }
                }
                // Spalte Gueltig_ab validieren
                final java.sql.Date von_am_aktuell;
                if (zeileOK) {
                    /**
                     * wandelt den uebergebenen String im uebergebenen Datumsformat in ein
                     * <code>java.sql.Date</code> um
                     *
                     * @return String als <code>java.sql.Date</code>, oder null, falls der
                     *         String nicht konvertiert werden kann
                     */
                    von_am_aktuell = typConverter.getNullSqlDate(
                            tempSplitZeile[Constants_Werte.SPALTENINDEX_GUELTIG_AB].trim(),
                            Constants_Datumsformat.DATUMSFORMAT_MIT_PUNKTEN);
                    if (von_am_aktuell == null) {
                        log.info("FEHLER bei Tabellenzeile " + zeilenNr
                                + ": Das Gueltig_ab Datum zu dieser Zeile hat kein gueltiges Format.");
                        zeileOK = false;
                    } else {
                        tarifwertDTO_update.setVon_am(von_am_aktuell);
                    }
                }
                // Spalte Punktewert validieren
                if (zeileOK) {
                    bigDecimalOK = pruefeBigDecimal(tempSplitZeile[Constants_Werte.SPALTENINDEX_PUNKTEWERT], 4);
                    if (bigDecimalOK.containsKey(Constants_Werte.IMPORT_BIGDECIMAL_FAIL)) {
                        log.info("FEHLER bei Tabellenzeile " + zeilenNr
                                + ": Der Punktewert zu dieser Zeile hat kein gueltiges Format.");
                        zeileOK = false;
                    } else if (bigDecimalOK.containsKey(Constants_Werte.IMPORT_BIGDECIMAL_OK)) {
                        tarifwertDTO_update.setPunktewert(bigDecimalOK.get(Constants_Werte.IMPORT_BIGDECIMAL_OK));
                    } else {
                        zeileOK = false;
                    }
                }
                // Spalte Punkte validieren
                if (zeileOK) {
                    bigDecimalOK = pruefeBigDecimal(tempSplitZeile[Constants_Werte.SPALTENINDEX_PUNKTE], 1);
                    if (bigDecimalOK.containsKey(Constants_Werte.IMPORT_BIGDECIMAL_FAIL)) {
                        log.info("FEHLER bei Tabellenzeile " + zeilenNr
                                + ": Die Punkte zu dieser Zeile haben kein gueltiges Format.");
                        zeileOK = false;
                    } else if (bigDecimalOK.containsKey(Constants_Werte.IMPORT_BIGDECIMAL_OK)) {
                        tarifwertDTO_update.setPunkte(bigDecimalOK.get(Constants_Werte.IMPORT_BIGDECIMAL_OK));
                    } else {
                        zeileOK = false;
                    }
                }
                // Spalte Tarifwert validieren
                if (zeileOK) {
                    bigDecimalOK = pruefeBigDecimal(tempSplitZeile[Constants_Werte.SPALTENINDEX_TARIFWERT], 4);
                    if (bigDecimalOK.containsKey(Constants_Werte.IMPORT_BIGDECIMAL_FAIL)) {
                        log.info("FEHLER bei Tabellenzeile " + zeilenNr
                                + ": Der Tarifwert zu dieser Zeile hat kein gueltiges Format.");
                        zeileOK = false;
                    } else if (bigDecimalOK.containsKey(Constants_Werte.IMPORT_BIGDECIMAL_OK)) {
                        tarifwertDTO_update.setTarifwert(bigDecimalOK.get(Constants_Werte.IMPORT_BIGDECIMAL_OK));
                    } else {
                        zeileOK = false;
                    }
                }
                if (zeileOK) {
                    tarifwertDTO_update.setWaehrung(tempSplitZeile[Constants_Werte.SPALTENINDEX_WAEHRUNG].trim());
                }
                // alle Spalten wurden geprueft
                else {
                    tarifwertDTO_update = null;
                }
            } catch (final Exception e) {
                log.info("unbekannter FEHLER ist aufgetreten bei der Validierung der Tarif-Importtabelle... "
                        + exceptionHandler.getErrorMeldung(e, request));
            }
        }
        return tarifwertDTO_update;
    }// Methode pruefeTabellenzeile

    /**
     * wandelt eine Zahl in ein Big Decimal, ersetzt Komma durch Punkt falls vorhanden
     * und prueft, ob die erlaubte Anzahl Nachkommastellen passt
     * Anmerkung: die Rueckgabe der Methode 'typConverter.getNullBigDecimal' liefert nicht genau die richtige Logik
     * fuer dieses Modul, daher wurde eine HashMap ins Spiel gebracht
     *
     * @return HashMap mit Key true oder false, sowie Wert
     */
    public HashMap<Boolean, BigDecimal> pruefeBigDecimal(String tempStr, final int anzahlDezimalstellen) {
        BigDecimal tempBigDecimal = null;
        final HashMap<Boolean, BigDecimal> rueckgabeBigDecimalOK = new HashMap<>(1);
        if (!this.helper.isEmptyString(tempStr)) {
            tempStr = tempStr.trim();
            tempBigDecimal = typConverter.getNullBigDecimal(tempStr);
            if (((this.anzahlDezimalstellen(tempStr, ",") > anzahlDezimalstellen))
                    || ((this.anzahlDezimalstellen(tempStr, ".") > anzahlDezimalstellen)) || (tempBigDecimal == null)) {
                rueckgabeBigDecimalOK.put(Constants_Werte.IMPORT_BIGDECIMAL_FAIL, null);
            } else {
                rueckgabeBigDecimalOK.put(Constants_Werte.IMPORT_BIGDECIMAL_OK, tempBigDecimal);
            }
        } else {
            rueckgabeBigDecimalOK.put(Constants_Werte.IMPORT_BIGDECIMAL_OK, null);
        }
        return (rueckgabeBigDecimalOK);
    }

    /**
     * liefert eine Zeile aus der Tarifdatenbank als String aufbereitet
     * mit aktuellen Tarifwerten fuer den TarifkatalogExportJob
     *
     * @return String
     */
    public String setTabellenzeile(final String posnr, final String notesktxt, final TarifwertDTO tarifwertZeile) {
        final StringBuffer sb = new StringBuffer();
        // für jede aktuelle Zeile in Tarifwert wird eine .csv Zeile geschrieben
        // sb.append("").append(Constants_Werte.STRICHPUNKT);
        sb.append(posnr).append(Constants_Werte.STRICHPUNKT);
        sb.append(notesktxt).append(Constants_Werte.STRICHPUNKT);
        // sb.append(tarifwertZeile.getTarifwert_id()).append(Constants_Werte.STRICHPUNKT);
        sb.append(tarifwertZeile.getTarif_typ()).append(Constants_Werte.STRICHPUNKT);
        String temp_str = typConverter.getNullString(tarifwertZeile.getVon_am(),
                Constants_Datumsformat.DATUMSFORMAT_MIT_PUNKTEN);
        sb.append(temp_str).append(Constants_Werte.STRICHPUNKT); // vondatum
        // temp_str = typConverter.getNullString(tarifwertZeile.getBis(),
        // Constants_Datumsformat.DATUMSFORMAT_MIT_PUNKTEN);
        // sb.append(temp_str).append(Constants_Werte.STRICHPUNKT); // bisdatum
        final DecimalFormat decimalFormat = new DecimalFormat("0.####");
        temp_str = typConverter.getNotNullString(tarifwertZeile.getPunktewert(), decimalFormat).replace(".", ",");
        sb.append(temp_str).append(Constants_Werte.STRICHPUNKT);
        decimalFormat.applyPattern("0.#");
        temp_str = typConverter.getNotNullString(tarifwertZeile.getPunkte(), decimalFormat).replace(".", ",");
        sb.append(temp_str).append(Constants_Werte.STRICHPUNKT);
        decimalFormat.applyPattern("0.####");
        temp_str = typConverter.getNotNullString(tarifwertZeile.getTarifwert(), decimalFormat).replace(".", ",");
        sb.append(temp_str).append(Constants_Werte.STRICHPUNKT);
        sb.append(tarifwertZeile.getWaehrung());// .append(Constants_Werte.STRICHPUNKT);
        return (sb.toString());
    }
}
