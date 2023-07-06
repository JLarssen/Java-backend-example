package constants;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import constants.Constants_Fixwerte;

/**
 * @author SV-Benutzer
 *
 */
@Named(value = "constants_Typ")
@ApplicationScoped
public class Constants_Typ {
    /**
     * PROFIL_TYP_TARIFKATALOG_ANZEIGE_KATALOG = "anzeige_katalog"
     */
    public static final String PROFIL_TYP_TARIFKATALOG_ANZEIGE_KATALOG = "anzeige_katalog";

    /**
     * PROFIL_TYP_ZEILENANZAHL_UEBERSICHTEN = "zeilen_uebers"
     */
    public static final String PROFIL_TYP_ZEILENANZAHL_UEBERSICHTEN = "zeilen_uebers";

    /**
     * PROFIL_TYP_TARIFKATALOG_DETAILANSICHT = "detailansicht"
     */
    public static final String PROFIL_TYP_TARIFKATALOG_DETAILANSICHT = "detailansicht";

    /**
     * TARIF_IMPORT_LEISTUNGSPFAD = "leistungspfad";
     */
    public static final String TARIF_IMPORT_LEISTUNGSPFAD = "leistungspfad";

    /**
     * TARIF_IMPORT_POSNR = "positionsnummer"
     */
    public static final String TARIF_IMPORT_POSNR = "positionsnummer";

    /**
     * TARIF_IMPORT_VERORDNUNGSREFERENZ = "verordnungsreferenz";
     */
    public static final String TARIF_IMPORT_VERORDNUNGSREFERENZ = "verordnungsreferenz";

    /**
     * TARIF_IMPORT_MDIF = "mdifPaket";
     */
    public static final String TARIF_IMPORT_MDIF = "mdifPaket";

    /**
     * TARIF_TYP_SACHLEISTUNG = "sachleistung"
     */
    public static final String TARIF_TYP_SACHLEISTUNG = "sachleistung";

    /**
     * TARIF_TYP_SACHLEISTER = "sachleister"
     */
    public static final String TARIF_TYP_SACHLEISTER = "sachleister";

    /**
     * TARIF_TYP_GELDLEISTER = "geldleister"
     */
    public static final String TARIF_TYP_GELDLEISTER = "geldleister";

    /**
     * TARIF_TYP_GELDLEISTER_MGZ = "geldleister_mgz"
     */
    public static final String TARIF_TYP_GELDLEISTER_MGZ = "geldleister_mgz";

    /**
     * TARIF_WAEHRUNG = "EURO";
     */
    public static final String TARIF_WAEHRUNG = "EURO";

    /**
     * APPLIKATION_WOKE = "WOKE";
     */
    public static final String APPLIKATION_WOKE = "WOKE";

    /**
     * APPLIKATION_PATI = "KISS";
     */
    public static final String APPLIKATION_PATI = "KISS";

    /**
     * PATI_AUWAHL = "patiAuswahl";
     */
    public static final String PATI_AUWAHL = "patiAuswahl";

    /**
     * WEBSERVICE_ESV_ANWENDUNG = "SV-KoE (eSV)";
     */
    public static final String WEBSERVICE_ESV_ANWENDUNG = "SV-KoE (eSV)";

    /**
     * @return the patiAuwahl
     */
    public static String getPatiAuwahl() {
        return (PATI_AUWAHL);
    }

    /**
     * @return Constants_Fixwerte.TYP_SV_GS_APPLIKATION_BEZEICHNUNG;
     */
    public String getApplikationBezeichnung() {
        return Constants_Fixwerte.TYP_SV_APPLIKATION_BEZEICHNUNG;
    }

    /**
     * Constants_Fixwerte.TYP_SV_GS_FACHGEBIET;
     */
    public String getFachgebiet() {
        return Constants_Fixwerte.TYP_SV_FACHGEBIET;
    }

    /**
     * @return Constants_Fixwerte.TYP_SV_GS_UNTERSUCHUNGSART;
     */
    public String getUntersuchugnsart() {
        return Constants_Fixwerte.TYP_SV_UNTERSUCHUNGSART;
    }
}
