package constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author SV-Benutzer
 *
 */
public class Constants_Leistungspfade {
    /**
     * Anstaltspflege, med. Hauskrankenpflege
     */
    public static final String GRUPPE_ANSTALTSPFLEGE = "1102";

    /**
     * Heilbehelfe, Hilfsmittel
     */
    public static final String GRUPPE_HEILBEHELFE = "110103";

    /**
     * HEILMITTEL
     */
    public static final String GRUPPE_HEILMITTEL = "110102";

    /**
     * Aerzte, Ambulatorien und Gleichgestellte (Physio-, Ergo, Psychotherapie,
     * Logopaedie), Transporte
     */
    public static final List<String> GRUPPE_LIST_ARZTE_AMBU_GLEICH = Arrays.asList("110101", "50", "58", "52", "1104",
            "1105", "1106", "410103", "410104", "1201", "13");

    /**
     * Vorsorge, Praevention, Rehabilitation, Kurheilverfahren
     */
    public static final List<String> GRUPPE_LIST_VORSORGE = Arrays.asList("45", "05", "51", "07", "3601", "3602",
            "3603", "31", "54", "55", "56", "57", "2101", "2501");

    /**
     * Zahnbehandlung, Kieferorthopaedie, Zahnersatz
     */
    public static final List<String> GRUPPE_LIST_ZANHBEHANDLUNG = Arrays.asList("91", "92", "93", "94", "95", "96",
            "99");

    /**
     * Zahnbehandlung, Kieferorthopaedie, Zahnersatz Gruppe1 "91", "93", "95",
     * "96", "99"
     */
    public static final List<String> GRUPPE_LIST_ZANHBEHANDLUNG_MGK_GR1 = Arrays.asList("91", "93", "95", "96", "99");

    /**
     * Zahnbehandlung, Kieferorthopaedie, Zahnersatz Gruppe2 "92", "94"
     */
    public static final List<String> GRUPPE_LIST_ZANHBEHANDLUNG_MGK_GR2 = Arrays.asList("92", "94");

    /**
     * LKF_AMBULANZ = "110101020600"
     */
    public static final String LKF_AMBULANZ = "110101020600";

    /**
     * SEHBEHELFE_1101030101 = "1101030101"
     */
    public static final String SEHBEHELFE_1101030101 = "1101030101";

    /**
     * TRANSPORTE_50 = "50"
     */
    public static final String TRANSPORTE_50 = "50";

    /**
     * TRANSPORTE_5401 = "5401"
     */
    public static final String TRANSPORTE_5401 = "5401";

    /**
     * TRANSPORTE_5501 = "5501"
     */
    public static final String TRANSPORTE_5501 = "5501";

    /**
     * INDIV_EINGABE_1708_VORSCHREIBEN = "760103020000"
     */
    public static final String INDIV_EINGABE_1708_VORSCHREIBEN = "760103020000";

    /**
     * INDIV_EINGABE_1708_KOA_AUTOMATIK = "760103030000"
     */
    public static final String INDIV_EINGABE_1708_KOA_AUTOMATIK = "760103030000";

    /**
     * KIEFERORTHOPAEDIE_920102 = "920102"
     */
    public static final String KIEFERORTHOPAEDIE_920102 = "920102";

    /**
     * METALLGERUEST_940201 = "940201"
     */
    public static final String METALLGERUEST_940201 = "940201";

    /**
     * KOA_BEFREITE_LEISTUNGSSPARTE = "999999999999"
     */
    public static final String KOA_BEFREITE_LEISTUNGSSPARTE = "999999999999";

    /**
     * NICHTANZEIGE_LEISTUNGSPFAD_ENDE = "99"
     */
    public static final String NICHTANZEIGE_LEISTUNGSPFAD_ENDE = "99";
}
