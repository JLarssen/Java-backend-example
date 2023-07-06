package constants;

import java.io.Serializable;

import javax.inject.Named;

/**
 *
 *
 * GENERATED CONTENT - DO NOT EDIT!
 *
 * Disclaimer: this content gets replaced with every run of the generator!
 *
 * @author sca-mda-generator
 *
 */
@Named
public class Constants implements Serializable {
    private static final long serialVersionUID = 933079018500229348L;

    /**
     * MAX_HITS_LEISTUNGEN = 50
     */
    public static final int MAX_HITS_LEISTUNGEN = 50;

    /**
     * MAX_HITS_KAPITEL = 300
     */
    public static final int MAX_HITS_KAPITEL = 300;

    /**
     * MAX_SUBKAPITEL_OFFEN = Integer.valueOf(25)
     */
    public static final Integer MAX_SUBKAPITEL_OFFEN = Integer.valueOf(25);

    /**
     * TARIF_INDEX_NEU = "true"
     */
    public static final String TARIF_INDEX_NEU = "true";

    /**
     * MODUS_BEARBEITEN = "B"
     */
    public static final String MODUS_BEARBEITEN = "B";

    /**
     * EURO_DATUM = new java.sql.Date( new java.util.GregorianCalendar(2002,
     * java.util.Calendar.JANUARY, 1) .getTimeInMillis())
     */
    public static final java.sql.Date EURO_DATUM = new java.sql.Date(
            new java.util.GregorianCalendar(2002, java.util.Calendar.JANUARY, 1).getTimeInMillis());

    /**
     * MAX_ANZAHL_GELESENE_DATEN = 300
     */
    public static final int MAX_ANZAHL_GELESENE_DATEN = 300;

    /**
     * MEHRFACHUEBERNAHME = "mehrfachUebernahme"
     */
    public static final String MEHRFACHUEBERNAHME = "mehrfachUebernahme";

    /**
     * PROFIL = "profil"
     */
    public static final String PROFIL = "profil";

    /**
     * RESOURCE_BUNDLE_APPLICATION = "resources.application"
     */
    public static final String RESOURCE_BUNDLE_APPLICATION = "resources.application";

    /**
     * DATENFELD_SEPARATOR = ";";
     */
    public static final String DATENFELD_SEPARATOR = ";";

    /**
     * TARIFKATALOG_KONFIGURATION = "tarif_import_ablage";
     */
    public static final String TARIFKATALOG_KONFIGURATION = "tarif_import_ablage";

    /**
     * Dateiendung fuer eine Textdatei (='.txt').
     */
    public final static String DATEIENDUNG_TXT = ".txt";

    /**
     * IMPORT_ABLAGE_DATEINAME = "posnrImport_";
     */
    public final static String IMPORT_ABLAGE_DATEINAME = "posnrImport_";

    /**
     * TRENNZEICHEN_POSITIONBUNDESLAND = ",";
     */
    public final static String TRENNZEICHEN_POSITIONBUNDESLAND = ",";
}
