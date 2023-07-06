package sv.utils;

import java.io.File;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author SV-Benutzer
 *
 */
public class KommandozeilenParameter {
    protected Logger log = LoggerFactory.getLogger(getClass());

    protected final Options options;

    private final Helper helper;

    /**
     * Default-Konstruktor
     */
    public KommandozeilenParameter() {
        this.helper = new Helper();
        this.options = new Options();
    }

    /**
     * KommandLiner fuer der EncodingConverter
     */
    public CommandLine getKommandLineEncodingConverter(final String[] args) {
        CommandLine line = null;
        final Option eingabeDatei = new Option(constants.Constants_Parameter.EINGABE_DATEI, true,
                "Eingabedatei fuer Converter");
        final Option eingabeDateiEncoding = new Option(
                constants.Constants_Parameter.EINGABE_DATEI_ENCODING, true,
                "Encoding der Datei (zB: ISO-8859-1, UTF-8, ..)");
        final Option ausgabeDatei = new Option(constants.Constants_Parameter.AUSGABE_DATEI, true,
                "Eingabedatei fuer Converter");
        final Option ausgabeDateiEncoding = new Option(
                constants.Constants_Parameter.AUSGABE_DATEI_ENCODING, true,
                "Encoding der Datei (zB: ISO-8859-1, UTF-8, ..)");
        //
        eingabeDatei.setRequired(true);
        eingabeDateiEncoding.setRequired(true);
        ausgabeDatei.setRequired(true);
        ausgabeDateiEncoding.setRequired(true);
        this.options.addOption(eingabeDatei);
        this.options.addOption(eingabeDateiEncoding);
        this.options.addOption(ausgabeDatei);
        this.options.addOption(ausgabeDateiEncoding);
        final CommandLineParser parser = new BasicParser();
        final HelpFormatter lvFormater = new HelpFormatter();
        try {
            // parse the command line arguments
            line = parser.parse(this.options, args);
        } catch (final UnrecognizedOptionException e1) {
            this.log.error("Fehler: " + e1);
            this.log.error("Argument ist nicht vorhanden: " + e1.getMessage());
            lvFormater.printHelp("EncodingConverter", this.options);
        } catch (final MissingOptionException e2) {
            this.log.error("Fehler: " + e2);
            this.log.error("Pflicht-Argument(e) wurde(n) nicht eingegeben: " + e2.getMessage());
            lvFormater.printHelp("EncodingConverter", this.options);
        } catch (final ParseException e3) {
            this.log.error("Fehler: " + e3);
            this.log.error("Parsing failed.  Reason: " + e3.getMessage());
            lvFormater.printHelp("EncodingConverter", this.options);
        }
        return (line);
    }

    /**
     * setzt die jeweiligen Optionen auf required und fuegt die Option hinzu
     */
    protected void setRequiredAndAddOption(final Option... options) {
        if (options != null) {
            for (final Option option : options) {
                option.setRequired(true);
                this.options.addOption(option);
            }
        }
    }

    /**
     * prueft eine eingabeDatei, ob die Dateiname eingegeben wurde und die Datei
     * vorhanden und lesbar ist
     */
    public boolean istEingabeDateiOK(final String eingabeDatei) {
        boolean antwort = false;
        if (!this.helper.isEmptyString(eingabeDatei)) {
            final File file = new File(eingabeDatei);
            if (file.exists() && file.isFile() && file.canRead()) {
                antwort = true;
            }
        }
        return (antwort);
    }

    /**
     * prueft eine ausgabeDatei, ob Dateiname eingegeben wurde und die Datei
     * nicht vorhanden ist
     */
    public boolean istAusgabeDateiOK(final String ausgabeDatei) {
        boolean antwort = false;
        if (!this.helper.isEmptyString(ausgabeDatei)) {
            final File file = new File(ausgabeDatei);
            if (!file.exists()) {
                antwort = true;
            }
        }
        return (antwort);
    }

    /**
     * prueft einen eingabePfad, ob der Pfad eingegeben wurde, ein Ordner ist,
     * lesbar und Dateien enthaelt
     */
    public boolean istEingabePfadOK(final String eingabePfad) {
        boolean antwort = false;
        if (!this.helper.isEmptyString(eingabePfad)) {
            final File file = new File(eingabePfad);
            if (file.exists() && file.isDirectory() && file.canRead()) {
                final File[] f = file.listFiles();
                if (f != null) {
                    if (f.length > 0) {
                        antwort = true;
                    }
                }
            }
        }
        return (antwort);
    }

    /**
     * prueft einen ausgabePfad, ob der Pfad eingegeben wurde, der Ordner
     * vorhanden und writable ist
     */
    public boolean istAusgabePfadOK(final String ausgabePfad) {
        boolean antwort = false;
        if (!this.helper.isEmptyString(ausgabePfad)) {
            final File file = new File(ausgabePfad);
            if (file.exists() && file.isDirectory() && file.canWrite()) {
                antwort = true;
            }
        }
        return (antwort);
    }
}
