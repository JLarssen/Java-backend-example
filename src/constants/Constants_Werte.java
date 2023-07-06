package constants;

import java.math.BigDecimal;

/**
 *
 * @author larsju
 *
 */
public class Constants_Werte {
    /**
     * Default-Wert fuer die Leistungssparte bei erstmaligem Systemaufruf
     */
    public static final Integer LEISTUNGSSPARTE_TARIFANZEIGE = Integer.valueOf(0);

    /**
     * Initialwert, ab welcher Zeile eine DB-Abfrage die Resultate uebermitteln
     * soll
     */
    public static final int FIRST_RESULT = 0;

    /**
     * Schritte, in welchen eine Liste (zB mit PosnrStrings) verarbeitet werden
     * soll (zB einem IndexWriter hinzufuegen).
     */
    public static final int CACHING_SCHRITTE_DB_TARIFE_AUSLESEN = 2000;

    /**
     * Schritte, in welchen eine Liste (zB mit PosnrStrings) verarbeitet werden
     * soll (zB einem IndexWriter hinzufuegen).
     */
    public static final int CACHING_SCHRITTE_INDEX_DOC_SCHREIBER = 500;

    /**
     * Limit, ab welchem der Tarifindex nicht mehr komplett im RAM abgearbeitet
     * werden soll.
     */
    public static final int LIMIT_ANZAHL_POSNR_IN_DB = 100000;

    /**
     * Stringwert fuer einen wahren Boolean (zB fuer Webformular)
     */
    public static final String BOOLEAN_TRUE = "true";

    /**
     * Stringwert fuer einen falschen Boolean (zB fuer Webformular)
     */
    public static final String BOOLEAN_FALSE = "false";

    /**
     * Array mit Woertern, welche Suchfelder benennen und nicht fuer den
     * eigentlichen Suchtext bestimmt sind.
     */
    public static final String[] INDEX_KEYWORDS = { "bundesland:", "notesktxt", "posnr:", "krzpos:", "alpha:",
            "kv_vpnr:", "tarifgl", "tarifsl", "00 TO 99", "exakt" };

    /**
     * Array mit Woertern, welche Suchfelder benennen und nicht fuer den
     * eigentlichen Suchtext bestimmt sind.
     */
    public static final String[] INDEX_QUERY_FELDER = { "posnr", "alpha", "vpnr", "suchtextfeld", "bundesland",
            "exakt" };

    /**
     * Key-Word fuer eine exakte Suche gemaess der Eingabe
     */
    public static final String EXAKTE_SUCHE = "+exakt";

    /**
     * EURO_IN_ATS = 13.7603
     */
    public static final BigDecimal EURO_IN_ATS = BigDecimal.valueOf(13.7603);

    /**
     * BEREICH_SA210 = Integer.valueOf(1)
     */
    public static final Integer BEREICH_SA210 = Integer.valueOf(1);

    /**
     * BEREICH_SA220 = Integer.valueOf(1)
     */
    public static final Integer BEREICH_SA220 = Integer.valueOf(1);

    /**
     * BEREICH_SA240 = Integer.valueOf(3)
     */
    public static final Integer BEREICH_SA240 = Integer.valueOf(3);

    /**
     * BEREICH_SA260 = Integer.valueOf(34)
     */
    public static final Integer BEREICH_SA260 = Integer.valueOf(34);

    /**
     * ZEILENANZAHL_UEBERSICHTEN_DEFAULT = 8;
     */
    public static final int ZEILENANZAHL_UEBERSICHTEN_DEFAULT = 8;

    /**
     * HONORAR_ORDNUNG = "honorarordnung"
     */
    public static final String HONORAR_ORDNUNG = "honorarordnung";

    /**
     * ZEILENANZAHL_POSITIONSNUMMER_HINZUFUEGEN = 10
     */
    public static final int ZEILENANZAHL_POSITIONSNUMMER_HINZUFUEGEN = 10;

    /**
     * Text fuer Synonyme
     */
    public static final String TEXT_SYNONYME = "Synonyme:";

    /**
     * TEXT_LAENGE_ANMERKUNG = Integer.valueOf(2000)
     */
    public static final Integer TEXT_LAENGE_SB_ANMERKUNG = Integer.valueOf(2000);

    /**
     * POSNR_BEREICH = 10 000;
     */
    public static final int POSNR_BEREICH = 10000;

    /**
     * TEXT_LAENGE_LSP_WERT = 4;
     */
    public static final int TEXT_LAENGE_LSP_WERT = 4;

    /**
     * DEFAULT_FACHEBIET = "1";
     */
    public static final String DEFAULT_FACHEBIET = "1";

    /**
     * DEFAULT_UNTERSUCHUNGSART = "CT";
     */
    public static final String DEFAULT_UNTERSUCHUNGSART = "CT";

    /**
     * IMPORT_BIGDECIMAL_FAIL wird als Key im ImportJob-Thread verwendet, um
     * aus einer BigDecimal Pruef-Methode einen geeigneten Rueckgabetyp zu bekommen
     */
    public static final Boolean IMPORT_BIGDECIMAL_FAIL = Boolean.valueOf(false);

    /**
     * IMPORT_BIGDECIMAL_OK wird als Key im ImportJob-Thread verwendet, um
     * aus einer BigDecimal Pruef-Methode einen geeigneten Rueckgabetyp zu bekommen
     */
    public static final Boolean IMPORT_BIGDECIMAL_OK = Boolean.valueOf(true);

    /**
     * EXPORT_CSV_APPEND boolean fuer DateiHandler-Parameter
     */
    public static final boolean EXPORT_CSV_APPEND = false;

    /**
     * Index fuer TarifkatalogImportJob
     */
    public static final int SPALTENINDEX_POSNR = 0;

    /**
     * Index fuer TarifkatalogImportJob
     */
    public static final int SPALTENINDEX_KTXT = 1;

    /**
     * Index fuer TarifkatalogImportJob
     */
    public static final int SPALTENINDEX_TARIFTYP = 2;

    /**
     * Index fuer TarifkatalogImportJob
     */
    public static final int SPALTENINDEX_GUELTIG_AB = 3;

    /**
     * Index fuer TarifkatalogImportJob
     */
    public static final int SPALTENINDEX_PUNKTEWERT = 4;

    /**
     * Index fuer TarifkatalogImportJob
     */
    public static final int SPALTENINDEX_PUNKTE = 5;

    /**
     * Index fuer TarifkatalogImportJob
     */
    public static final int SPALTENINDEX_TARIFWERT = 6;

    /**
     * Index fuer TarifkatalogImportJob
     */
    public static final int SPALTENINDEX_WAEHRUNG = 7;

    /**
     * Anzahl der Spalten der .csv Datei fuer TarifkatalogImportJob
     */
    public static final int SPALTENANZAHL = 8;
    
    /**
     * Bindestrich
     */
    public static final String BINDESTRICH = "-";

    /**
     * Strichpunkt
     */
    public static final String STRICHPUNKT = ";";

    /**
     * Erzwungenes Leerzeichen
     */
    public static final String HTML_NBSP = "&#160;";

    /**
     * EUR_ISO_CODE = "EUR";
     */
    public static final String EUR_ISO_CODE = "EUR";
}
