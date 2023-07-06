package sv.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.Constants_Allgemein;
import constants.Constants_XmlUtils;

/**
 * Der DateiHandler ermoeglicht das Lesen und Schreiben einer Datei. Des
 * weiteren kann man die Anzahl der Zeilen einer Datei bestimmen.
 *
 * @author SV-Benutzer
 *
 */
public class DateiHandler {
    private static Logger log = LoggerFactory.getLogger(DateiHandler.class);

    private final HelperAllgemein helperAllgemein;

    private final ExceptionHandlerAllgemein exceptionHandlerAllgemein;

    private LineNumberReader lineNumberReader;

    private BufferedWriter bufferedWriter;

    private int zeilenposition;

    private File file;

    /**
     * Default-Konstruktor
     */
    public DateiHandler() {
        this.helperAllgemein = new HelperAllgemein();
        this.exceptionHandlerAllgemein = new ExceptionHandlerAllgemein();
        this.zeilenposition = -1;
    }

    /**
     * Konstruktor
     */
    public DateiHandler(final File file) {
        this();
        this.file = file;
    }

    /**
     * Retourniert eine Instanz des LineNumberReader. Zuvor muss jedoch die
     * Funktion <code>oeffneDateiZumLesen</code> aufgerufen werden, sonst wird
     * <code>null</code> retourniert. Konnte die Datei zuvor mit allen
     * Bedingungen nicht geoeffnet werden, wird ebenfalls <code>null</code>
     * retourniert.
     *
     * @return Instanz von LineNumberReader, wenn zuvor
     *         <code>oeffneDateiZumLesen</code> aufgerufen wurde, sonst
     *         <code>null</code>.
     */
    public LineNumberReader getLineNumberReader() {
        return (this.lineNumberReader);
    }

    /**
     * Retourniert die Instanz des BufferedWriter. Zuvor muss die Methode
     * <code>oeffneDateiZumSchreiben</code> aufgerufen werden, sonst wird
     * <code>null</code> retourniert. Konnte die Datei zuvor mit allen
     * Bedingungen nicht geoeffnet werden, wird ebenfalls <code>null</code>
     * retourniert.
     *
     * @return Instanz von BufferedWriter, wenn zuvor
     *         <code>oeffneDateiZumSchreiben</code> aufgerufen wurde, sonst
     *         <code>null</code>.
     */
    public BufferedWriter getBufferedWriter() {
        return (this.bufferedWriter);
    }

    /**
     * Retourniert zur aktuellen Zeile die Zeilenposition.<br />
     * <br />
     * <b>Hinweise:</b>
     * <ul>
     * <li>Um die aktuelle Zeilenposition zu bekommen, muss vorher die Methode
     * <code>getNaechsteZeile(request)</code> oder
     * <code>getNaechstenZeileninhaltUndZeilenposition(request)</code>
     * aufgerufen werden.</li>
     * <li>Will man auch beim Schreiben in eine Datei die aktuelle
     * Zeilenposition uebermittelt bekommen, so muss die bei der Methode
     * <code>oeffneDateiZumSchreiben</code> der Uegergabeparameter
     * <code>zeilenpositionErforderlich</code> auf <code>true</code> gesetzt
     * werden.</li>
     * <li>Ist die Zeilenposition unbekannt, so wird -1 retourniert.</li>
     * </ul>
     *
     *
     * @return zur aktuellen Zeile die Zeilenposition. Ist die Zeilenposition
     *         unbekannt, so wird -1 retourniert.
     */
    public int getZeilenpositionZurAktuellenZeile() {
        return (this.zeilenposition);
    }

    /**
     * oeffnet eine Datei und ueberprueft ob Leserechte vorhanden sind. Wenn die
     * Datei vorhanden ist und alle Anforderungen erfuellt sind, wird eine
     * Instanz des <code>LineNumberReader</code> erstellt.
     *
     * @param dateiname
     *            Dateiname (inkl. Pfad), welche zum Lesen geoeffnet werden soll
     * @param request
     *            String
     */
    private void oeffneDateiZumLesen(String dateiname, final String request) {
        try {
            if (!this.helperAllgemein.isEmptyString(dateiname)) {
                dateiname = dateiname.trim();
                final File datei = new File(dateiname);
                if ((datei.exists()) && (datei.isFile()) && (datei.canRead())) {
                    this.lineNumberReader = new LineNumberReader(new FileReader(dateiname));
                } else {
                    log.error(this.exceptionHandlerAllgemein.getErrorMeldung(
                            new Exception("Datei '" + dateiname
                                    + "' existiert nicht bzw. ist nicht verwendbar und kann nicht gelesen werden! "),
                            request));
                }
            } else {
                log.error(this.exceptionHandlerAllgemein.getErrorMeldung(new Exception("Kein Dateiname uebergeben!"),
                        request));
            }
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
    }

    /**
     * oeffnet eine Datei und ueberprueft ob Leserechte vorhanden sind. Wenn die
     * Datei vorhanden ist und alle Anforderungen erfuellt sind, wird eine
     * Instanz des <code>LineNumberReader</code> erstellt.
     *
     * @param dateiname
     *            Dateiname (inkl. Pfad), welche zum Lesen geoeffnet werden soll
     * @param encoding
     *            Es kann die Codierung fuer die Datei mitgegeben weden.
     *            Standardmaessig wird Constants_Allgemein.ENCODING_FILE
     *            verwendet
     * @param request
     *            String
     */
    public void oeffneDateiZumLesen(String dateiname, String encoding, final String request) {
        try {
            if (!this.helperAllgemein.isEmptyString(dateiname)) {
                dateiname = dateiname.trim();
                final File datei = new File(dateiname);
                if ((datei.exists()) && (datei.isFile()) && (datei.canRead())) {
                    if (this.helperAllgemein.isEmptyString(encoding)) {
                        encoding = Constants_Allgemein.ENCODING_FILE;
                    }
                    this.lineNumberReader = new LineNumberReader(
                            new InputStreamReader(new FileInputStream(datei), encoding));
                } else {
                    log.error(this.exceptionHandlerAllgemein.getErrorMeldung(
                            new Exception("Datei '" + dateiname
                                    + "' existiert nicht bzw. ist nicht verwendbar und kann nicht gelesen werden! "),
                            request));
                }
            } else {
                log.error(this.exceptionHandlerAllgemein.getErrorMeldung(new Exception("Kein Dateiname uebergeben!"),
                        request));
            }
        } catch (final UnsupportedEncodingException e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
    }

    /**
     * oeffnet eine Datei zum Schreiben und ueberprueft ob in die Datei
     * geschrieben werden kann. Wenn die Datei vorhanden ist und alle
     * Vorraussetzungen erfuellt sind, wird eine Instanz von
     * <code>BufferedWriter</code> erstellt.
     *
     * @param dateiname
     *            Dateiname (inkl. Pfad), in welche geschrieben werden soll.
     * @param zeilenpositionErforderlich
     *            true, wenn die Zeilenposition der zu schreibenden Zeile
     *            erforderlich ist
     * @param encoding
     *            Es kann die Codierung fuer die Datei mitgegeben weden.
     *            Standardmaessig wird Constants_Allgemein.ENCODING_FILE
     *            verwendet
     * @param textAnhaengen
     *            <b>True</b> Text wird in die Datei hinzugefuegt; <b>False</b>
     *            Die vorhandene Datei wird neu erstellt
     * @param request
     *            String
     */
    public void oeffneDateiZumSchreiben(String dateiname, final boolean zeilenpositionErforderlich, String encoding,
            final boolean textAnhaengen, final String request) {
        try {
            if (!this.helperAllgemein.isEmptyString(dateiname)) {
                dateiname = dateiname.trim();
                final File datei = new File(dateiname);
                boolean oeffneWriter = true;
                erstelleOrdnerstruktur(datei.getParentFile(), request);
                if (!datei.exists()) {
                    if (!datei.createNewFile()) {
                        oeffneWriter = false;
                        log.error(this.exceptionHandlerAllgemein.getErrorMeldung(
                                new Exception("Datei '" + dateiname + "' konnte nicht angelegt werden!"), request));
                    }
                }
                if ((oeffneWriter) && (datei.isFile()) && (datei.canWrite())) {
                    if (zeilenpositionErforderlich) {
                        this.zeilenposition = 0;
                    }
                    if (this.helperAllgemein.isEmptyString(encoding)) {
                        encoding = Constants_Allgemein.ENCODING_FILE;
                    }
                    this.bufferedWriter = new BufferedWriter(
                            new OutputStreamWriter(new FileOutputStream((datei), textAnhaengen), encoding));
                } else {
                    log.error(this.exceptionHandlerAllgemein.getErrorMeldung(new Exception("Datei '" + dateiname
                            + "' existiert nicht bzw. ist nicht verwendbar und kann nicht beschrieben werden! "),
                            request));
                }
            }
        } catch (final UnsupportedEncodingException e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
    }

    /**
     * Erstellt die Unterordner, welche fuer die Datei benoetigt.
     *
     * @param ordner
     *            gewuenschte Ordnerstruktur
     * @param request
     *            String
     */
    public void erstelleOrdnerstruktur(final File ordner, final String request) {
        try {
            if (ordner != null) {
                if (!ordner.exists()) {
                    if (!ordner.mkdirs()) {
                        log.error(this.exceptionHandlerAllgemein.getErrorMeldung(
                                new Exception("Es konnten die Unterverzeichnisse nicht erstellt werden."), request));
                    }
                }
            }
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
    }

    /**
     * Schliesst die Datei, welche zum Lesen bzw. Schreiben geoeffnet wurde. und
     * setzt den LineNumberReader bzw. den BufferedWriter auf <code>null</code>
     * zurueck.
     *
     * @param request
     *            String
     */
    public void schliesseDatei(final String request) {
        try {
            if (this.lineNumberReader != null) {
                this.lineNumberReader.close();
                this.lineNumberReader = null;
            }
            if (this.bufferedWriter != null) {
                this.bufferedWriter.flush();
                this.bufferedWriter.close();
                this.bufferedWriter = null;
            }
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
    }

    /**
     * Liest aus der zuvor geoeffneten Datei die naechste Zeile aus und
     * retourniert diese. <br />
     * <b>Hinweis:</b> Wurde die Datei zuvor nicht geoeffnet, konnte sie nicht
     * geoeffnet werden, oder ist <b>keine weitere Zeile mehr vorhanden</b>, so
     * wird <code>null</code> retourniert.
     *
     * @param request
     *            String
     * @return Zeileninhalt <br />
     *         Konnte die Datei nicht geoeffnet werden bzw. wurde sie nicht
     *         geoeffnet oder ist <b>keine weitere Zeile mehr vorhanden</b>, so
     *         wird <code>null</code> retourniert.
     */
    public String getNaechsteZeile(final String request) {
        String zeile = null;
        try {
            if (this.lineNumberReader != null) {
                zeile = this.lineNumberReader.readLine();
                this.zeilenposition = this.lineNumberReader.getLineNumber();
            } else {
                log.error(this.exceptionHandlerAllgemein
                        .getErrorMeldung(new Exception("Es wurde keine Datei zum Lesen geoeffnet."), request));
            }
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
        return (zeile);
    }

    /**
     * Liest aus der zuvor geoeffneten Datei die Zeile aus und speichert diese
     * mit der Zeilenposition als Schluessel in einer HashMap. <br />
     * <b>Hinweis:</b> Wurde die Datei zuvor nicht geoeffnet, konnte sie nicht
     * geoeffnet werden oder ist <b>keine weitere Zeile mehr vorhanden</b>, so
     * wird <code>null</code> retourniert.
     *
     * @param request
     *            HttpServletReqeust
     * @return Zeilenposition (Key) mit Zeileninhalt (Value). <br />
     *         Konnte die Datei nicht geoeffnet werden bzw. wurde sie nicht
     *         geoeffnet oder ist <b>keine weitere Zeile mehr vorhanden</b>, so
     *         wird <code>null</code> retourniert.
     */
    public Map<Integer, String> getNaechstenZeileninhaltUndZeilenposition(final String request) {
        Map<Integer, String> zeilenMap = null;
        try {
            if (this.lineNumberReader != null) {
                final String zeileninhalt = getNaechsteZeile(request);
                if (zeileninhalt != null) {
                    zeilenMap = new HashMap<>();
                    zeilenMap.put(Integer.valueOf(this.zeilenposition), zeileninhalt);
                }
            } else {
                log.error(this.exceptionHandlerAllgemein
                        .getErrorMeldung(new Exception("Es wurde keine Datei zum Lesen geoeffnet."), request));
            }
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
        return (zeilenMap);
    }

    /**
     * Schreibt mehrere Zeilen in die zuvor geoeffnete Datei.
     *
     * @param zeilen
     *            Zeilen, welche in die Datei geschrieben werden sollen.
     * @param request
     *            String
     */
    public int schreibeInDatei(final List<String> zeilen, final String request) {
        try {
            if ((this.bufferedWriter != null) && (!this.helperAllgemein.isEmptyCollection(zeilen))) {
                for (final String zeile : zeilen) {
                    try {
                        if (zeile != null) {
                            schreibeInDatei(zeile, request);
                        }
                    } catch (final Exception e) {
                        log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
                    }
                }
            }
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
        return (this.zeilenposition);
    }

    /**
     * Schreibt eine Zeile in die zuvor geoeffnete Datei.
     *
     * @param zeile
     *            Zeile, welche in die Datei geschrieben werden soll.
     * @param request
     *            String
     */
    public int schreibeInDatei(final String zeile, final String request) {
        try {
            // helperAllgemein.isEmptyString(zeile) ist hier nicht moeglich, da
            // man
            // moeglicherweise einen Leerstring Schreiben moechte.
            if ((this.bufferedWriter != null) && (zeile != null)) {
                this.bufferedWriter.write(zeile);
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
                if (this.zeilenposition != -1) {
                    this.zeilenposition++;
                }
            } else {
                log.warn("BufferedWriter bzw. die Zeile ist null! ('" + this.bufferedWriter + "'; '" + zeile + "'");
            }
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
        return (this.zeilenposition);
    }

    /**
     * Importiert den Inhalt der assozierten Datei mit uebergebenen Encoding;
     * wird kein Encoding uebergeben versucht die Methode das Encoding zu
     * ermitteln. Achtung: Die Datei wird neu geoeffnet und auch geschlossen,
     * vorherige Lese/Schreibvorgaenge werden verworfen!
     *
     */
    public String importDateiWithEncoding(final String request) {
        String string = null;
        try {
            String encoding = null;
            if (this.file != null) {
                this.oeffneDateiZumLesen(this.file.getAbsolutePath(), request);
            }
            if (this.lineNumberReader != null) {
                String zeile = null;
                boolean hadContent = false;
                // suche encoding in der XML-Datei
                final StringBuffer sb = new StringBuffer();
                while (((zeile = this.lineNumberReader.readLine()) != null)) {
                    sb.append(zeile).append(Constants_Allgemein.ENDL);
                    if (!hadContent && this.helperAllgemein.isEmptyString(encoding)) {
                        int beginnIndex = zeile.indexOf(Constants_XmlUtils.XML_ENCODING_SUCHBEGRIFF);
                        if (beginnIndex >= 0) {
                            beginnIndex += Constants_XmlUtils.XML_ENCODING_SUCHBEGRIFF.length() + 1;
                            final int endeIndex = zeile.indexOf("\"", beginnIndex);
                            if (endeIndex > beginnIndex) {
                                encoding = zeile.substring(beginnIndex, endeIndex);
                            }
                        }
                    }
                    hadContent = !this.helperAllgemein.isEmptyString(zeile);
                }
                this.schliesseDatei(request);
                log.debug("Datei '" + this.file.getAbsolutePath() + "': gefundenes Encoding: " + encoding);
                // falls kein Encoding gefunden wurde => Defaultwert
                if (this.helperAllgemein.isEmptyString(encoding)) {
                    encoding = Constants_Allgemein.ENCODING_FILE;
                    log.debug("Datei '" + this.file.getAbsolutePath() + "': verwende Encoding-Default: " + encoding);
                }
                string = new String(sb.toString().getBytes(), Charset.forName(encoding));
            }
        } catch (final Exception e) {
            log.error("Datei '" + this.file.getAbsolutePath() + "': "
                    + this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
        return (string);
    }

    /**
     * schliesset die Schreibedatei, falls sie geoeffnet wurde
     */
    public void schliesseSchreibedatei(final String request) {
        try {
            if (this.bufferedWriter != null) {
                this.bufferedWriter.flush();
                this.bufferedWriter.close();
                this.bufferedWriter = null;
            }
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
    }

    /**
     * schliesset die Lesedatei, falls sie geoeffnet wurde
     */
    public void schliesseLesedatei(final String request) {
        try {
            if (this.lineNumberReader != null) {
                this.lineNumberReader.close();
                this.lineNumberReader = null;
            }
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
    }
}
