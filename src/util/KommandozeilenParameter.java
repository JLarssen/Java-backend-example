package util;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;

import constants.Constants_Jobs;
import constants.Constants_Parameter;

/**
 * @author SV-Benutzer
 */
public class KommandozeilenParameter extends sv.utils.KommandozeilenParameter {
    private final Helper helper;

    /**
     * Default-Konstruktor
     */
    public KommandozeilenParameter() {
        super();
        this.helper = new Helper();
    }

    /**
     * verarbeitet die per Kommandozeile uebergebenen Parameter
     *
     * @param args
     *            Parameterarray, welches an main uebergeben wurde
     * @param jobname
     *            Jobname, dessen Parameter geprueft werden sollen
     *
     * @return ein befuelltes <code>org.apache.commons.cli.CommandLine</code>
     *         bei erfolgreicher Pruefung der Kommandozeilenparameter, sonst
     *         false
     */
    public CommandLine getKommandozeileParameter(final String[] args, final String jobname) {
        CommandLine line = null;
        final Option suchart = new Option("suchart", true, "<POSITIONSNUMMER>, <HDAT_NUMMER>, <ALPHA_BEZEICHNUNG>");
        final Option eingabeDatei = new Option(constants.Constants_Parameter.EINGABE_DATEI, true,
                "Name der Eingabedatei in der Form <Pfad/filename.xxx>");
        final Option eingabeDateiPosnr = new Option("eingabeDateiPosnr", true,
                "Name der Eingabedatei in der Form <Pfad/filename.xml>");
        final Option eingabePfad = new Option(constants.Constants_Parameter.EINGABE_PFAD, true,
                "Pfad in den die EingabeDateien liegen");
        final Option ausgabeDateiPosnr = new Option("ausgabeDateiPosnr", true,
                "Name der Eingabedatei in der Form <Pfad/filename.xml>");
        final Option ausgabeDatei = new Option(constants.Constants_Parameter.AUSGABE_DATEI, true,
                "Name der AusgabeDatei in der Form <Pfad/filename.xxx>");
        final Option fehlerDatei = new Option("fehlerDatei", true,
                "Name der Datei fuer fehlerhafte Datensaetze in der Form <Pfad/filename.xxx>");
        final Option ausgabePfad = new Option(constants.Constants_Parameter.AUSGABE_PFAD, true,
                "Ausgabeverzeichnis fuer die Indezes.");
        final Option intervall = new Option("intervall", true,
                "Eingabe \"monat\" oder \"quartal\" (ohne Anfuehrungszeichen) ");
        final Option verarbeitungstyp = new Option(Constants_Parameter.VERARBEITUNG_TYP, true,
                "Eingabe \"leistungspfad\" oder \"positionsnummer\" (ohne Anfuehrungszeichen) ");
        final Option zeitraum = new Option("zeitraum", true,
                "Angabe des Zeitraum 6-stellig (4-Stellig das Jahr). Bei Intervall monat 2-stellig Monat JJJJMM; Bei Intervall quartal Quartal JJJJQ1-4");
        // je nach Job Pflicht-Parameter definieren
        if (this.helper.isEmptyString(jobname)) {
            this.log.error("Jobname wurde nicht uebergeben");
        } else if (jobname.equalsIgnoreCase(Constants_Jobs.TARIFWERT_TARIFTEXT_IMPORTIEREN)
                || (jobname.equalsIgnoreCase(Constants_Jobs.HDAT_IMPORTIEREN))
                || (jobname.equalsIgnoreCase(Constants_Jobs.JOB_TARIF_IMPORT_CSV))) {
            eingabeDatei.setRequired(true);
            fehlerDatei.setRequired(true);
            this.options.addOption(eingabeDatei);
            this.options.addOption(fehlerDatei);
        } else if (jobname.equalsIgnoreCase(Constants_Jobs.JOB_ESV_TARIFE_EXPORTIEREN)
                || (jobname.equalsIgnoreCase(Constants_Jobs.JOB_TARIF_EXPORT_CSV))) {
            ausgabeDatei.setRequired(true);
            this.options.addOption(ausgabeDatei);
        } else if (jobname.equalsIgnoreCase(Constants_Jobs.JOB_TARIFE_AKTUELLE_WERTE_EXPORTIEREN)) {
            ausgabeDatei.setRequired(true);
            this.options.addOption(ausgabeDatei);
        } else if (jobname.equalsIgnoreCase(Constants_Jobs.POSNR_TEXTE_VORBEREITEN)
                || (jobname.equalsIgnoreCase(Constants_Jobs.JOB_SUCHE_TARIF))
                || (jobname.equalsIgnoreCase(Constants_Jobs.JOB_POSNRS_EXPORT))) {
            suchart.setOptionalArg(true);
            eingabeDatei.setRequired(true);
            ausgabeDatei.setRequired(true);
            this.options.addOption(suchart);
            this.options.addOption(eingabeDatei);
            this.options.addOption(ausgabeDatei);
        } else if (
        jobname.equalsIgnoreCase(Constants_Jobs.JOB_FOKO_SA210_ERSTELLEN)
                || jobname.equalsIgnoreCase(Constants_Jobs.JOB_FOKO_SA220_ERSTELLEN)
                || jobname.equalsIgnoreCase(Constants_Jobs.JOB_FOKO_SA240_ERSTELLEN)
                || jobname.equalsIgnoreCase(Constants_Jobs.JOB_FOKO_SA260_ERSTELLEN)) {
            ausgabeDatei.setRequired(true);
            intervall.setRequired(true);
            zeitraum.setRequired(true);
            this.options.addOption(ausgabeDatei);
            this.options.addOption(intervall);
            this.options.addOption(zeitraum);
        } else if (jobname.equalsIgnoreCase(Constants_Jobs.JOB_TARIFE_IMPORT)) {
            eingabeDatei.setRequired(true);
            verarbeitungstyp.setRequired(true);
            this.options.addOption(eingabeDatei);
            this.options.addOption(verarbeitungstyp);
        } else if (jobname.equalsIgnoreCase(Constants_Jobs.JOB_TARIF_ZUAMMENFASSUNG)) {
            eingabeDateiPosnr.setRequired(true);
            ausgabeDateiPosnr.setRequired(true);
            this.options.addOption(eingabeDateiPosnr);
            this.options.addOption(ausgabeDateiPosnr);
        } else if (jobname.equalsIgnoreCase(Constants_Jobs.JOB_TARIF_BUCHHALTUNG_EXPORT)
                || jobname.equalsIgnoreCase(Constants_Jobs.JOB_TARIFKATALOG_BUCHHALTUNG_EXPORT)) {
            eingabeDatei.setRequired(true);
            ausgabeDatei.setRequired(true);
            this.options.addOption(eingabeDatei);
            this.options.addOption(ausgabeDatei);
        } else if (jobname.equalsIgnoreCase(Constants_Jobs.JOB_TARIF_EXPORT_BUCHHALTUNG_KATEGORIE)) {
            ausgabeDatei.setRequired(true);
            this.options.addOption(ausgabeDatei);
        } else if (jobname.equalsIgnoreCase(Constants_Jobs.JOB_TARIF_EXPORT_TABELLE_F_KTO_BH)) {
            ausgabeDatei.setRequired(true);
            this.options.addOption(ausgabeDatei);
        } else if (jobname.equalsIgnoreCase(Constants_Jobs.JOB_TARIF_INDEX_ERSTELLEN)
                || (jobname.equalsIgnoreCase(Constants_Jobs.JOB_TARIF_EXPORT_CSV))) {
            ausgabePfad.setRequired(true);
            this.options.addOption(ausgabePfad);
        } else if (jobname.equalsIgnoreCase(Constants_Jobs.HONO_VERARBEITEN)) {
            eingabePfad.setRequired(true);
            ausgabePfad.setRequired(true);
            this.options.addOption(ausgabePfad);
            this.options.addOption(eingabePfad);
        }
        final CommandLineParser parser = new BasicParser();
        final HelpFormatter lvFormater = new HelpFormatter();
        try {
            // parse the command line arguments
            line = parser.parse(this.options, args);
        } catch (final UnrecognizedOptionException e1) {
            this.log.error("Fehler: " + e1);
            this.log.error("Argument ist nicht vorhanden: " + e1.getMessage());
            lvFormater.printHelp(jobname, this.options);
        } catch (final MissingOptionException e2) {
            this.log.error("Fehler: " + e2);
            this.log.error("Pflicht-Argument(e) wurde(n) nicht eingegeben: " + e2.getMessage());
            lvFormater.printHelp(jobname, this.options);
        } catch (final ParseException e3) {
            this.log.error("Fehler: " + e3);
            this.log.error("Parsing failed.  Reason: " + e3.getMessage());
            lvFormater.printHelp(jobname, this.options);
        }
        return (line);
    }
}
