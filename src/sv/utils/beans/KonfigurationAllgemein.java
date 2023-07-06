package sv.utils.beans;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import constants.Constants_Allgemein;
import sv.utils.HelperAllgemein;
import sv.utils.TypConverter;

/**
 * Klasse mit Konfigurationseinstellungen
 *
 * @author Markus Daberger
 */
public class KonfigurationAllgemein {
    private static Logger log = LoggerFactory.getLogger(KonfigurationAllgemein.class);

    /**
     * speichert Mapping Konfigurationsschluessel => Wert
     */
    protected static Map<String, String> propertiesMap = new HashMap<>();

    /**
     * Referenz auf den ServletContext
     */
    private static ServletContext context = null;

    /**
     * Systembearbeiter
     */
    private static volatile Bearbeiter systembearbeiter = null;

    /**
     * Systembearbeiter
     */
    private static volatile AnfragendeStelle systembearbeiterAnfragendeStelle = null;

    /**
     * Liste der ins Projekt importierten Fixwerte-Typen
     */
    private static List<String> fixwerteTypen = new LinkedList<>();

    /**
     * Liste der ins Projekt importierten Fixwerte-Mapping-Typen
     */
    private static List<String> fixwerteMappingTypen = new LinkedList<>();

    /**
     * Name der Anwendung
     */
    private static String anwendung;

    /**
     * Versionsnummer der Anwendung
     */
    private static String version;

    /**
     * IP-Adresse des Servers
     */
    private static String serverIP;

    /**
     * wird die Anwendung online oder im Batch ausgefuehrt?
     */
    private static Systemmodus systemmodus;

    private static HelperAllgemein helperAllgemein = new HelperAllgemein();

    /**
     * @return String
     */
    protected static String konfigurationAllgemeinToString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("KonfigurationAllgemein[");
        sb.append(Constants_Allgemein.ENDL).append("Properties [")
                .append(helperAllgemein.mapToString(propertiesMap, "Property")).append("], ");
        sb.append(Constants_Allgemein.ENDL).append("systembearbeiter = ").append(getSystembearbeiter()).append(", ");
        sb.append(Constants_Allgemein.ENDL).append("systembearbeiterAnfragendeStelle = ")
                .append(getSystembearbeiterAnfragendeStelle()).append(", ");
        sb.append(Constants_Allgemein.ENDL).append("Fixwerte-Typen [")
                .append(helperAllgemein.listToString(fixwerteTypen, "Fixwerte-Typ")).append("], ");
        sb.append(Constants_Allgemein.ENDL).append("Fixwerte-Mapping-Typen [")
                .append(helperAllgemein.listToString(fixwerteMappingTypen, "Fixwerte-Mapping-Typ")).append("], ");
        sb.append(Constants_Allgemein.ENDL).append("anwendung = ").append(anwendung).append(", ");
        sb.append(Constants_Allgemein.ENDL).append("version = ").append(version).append(", ");
        sb.append(Constants_Allgemein.ENDL).append(KonfigurationMailversand.konfigurationMailversandToString())
                .append(", ");
        sb.append(Constants_Allgemein.ENDL).append("context = ").append(context).append(", ");
        sb.append(Constants_Allgemein.ENDL).append("serverIP = ").append(getServerIP()).append(", ");
        sb.append(Constants_Allgemein.ENDL).append("systembearbeiter = ").append(getSystembearbeiter()).append(", ");
        sb.append(Constants_Allgemein.ENDL).append("systemmodus = ").append(systemmodus).append(", ");
        sb.append(Constants_Allgemein.ENDL).append("testumgebung = ").append(isTestumgebung()).append(", ");
        sb.append("]");
        return (sb.toString());
    }

    /**
     * @return the systembearbeiter
     */
    public static Bearbeiter getSystembearbeiter() {
        if (systembearbeiter == null) {
            synchronized (KonfigurationAllgemein.class) {
                if (systembearbeiter == null) {
                    systembearbeiter = new Bearbeiter();
                    systembearbeiter.setVSNR(Constants_Allgemein.APPLIKATION_BEARBEITER_VSNR);
                    systembearbeiter.setZuname("SYSTEM");
                    systembearbeiter.setVorname("SYSTEM");
                    systembearbeiter.setTraeger("40");
                    systembearbeiter.setLandesstellenCode("40");
                    systembearbeiter.setAbteilung("V Organisation und IKT");
                    systembearbeiter.setBenutzername(Constants_Allgemein.APPLIKATION_BEARBEITER_BENUTZERNAME);
                }
            }
        }
        return (systembearbeiter);
    }

    /**
     * @return the systembearbeiterAnfragendeStelle
     */
    public static AnfragendeStelle getSystembearbeiterAnfragendeStelle() {
        if (systembearbeiterAnfragendeStelle == null) {
            synchronized (KonfigurationAllgemein.class) {
                if (systembearbeiterAnfragendeStelle == null) {
                    systembearbeiterAnfragendeStelle = new LogikSoap().getAnfragendeStelle(getSystembearbeiter(), null,
                            null, null, null, getServerIP(),
                            Constants_Sicherheitsklasse.BENUTZERNAME_KENNWORT_OHNE_AUSWEIS);
                }
            }
        }
        return (systembearbeiterAnfragendeStelle);
    }

    /**
     * @return the serverIP
     */
    public static String getServerIP() {
        if (helperAllgemein.isEmptyString(serverIP)) {
            synchronized (KonfigurationAllgemein.class) {
                if (helperAllgemein.isEmptyString(serverIP)) {
                    try {
                        final InetAddress localHost = InetAddress.getLocalHost();
                        if (localHost != null) {
                            serverIP = localHost.getHostAddress();
                        }
                    } catch (final Exception e) {
                        log.error("Fehler: " + e);
                    }
                }
            }
        }
        return (serverIP);
    }

    /**
     * @return the propertiesMap
     */
    public static Map<String, String> getPropertiesMap() {
        return (propertiesMap);
    }

    /**
     * @param propertiesMap
     *            Map<String, String> mit den Konfigurationswerten
     */
    public static void setPropertiesMap(final Map<String, String> propertiesMap) {
        KonfigurationAllgemein.propertiesMap = propertiesMap;
    }

    /**
     * @return gibt den Wert zum uebergebenen Property zurueck; falls das
     *         Property nicht existiert: einen leeren String
     */
    public static String getProperty(final String property) {
        return (helperAllgemein.getValue(propertiesMap, property));
    }

    /**
     * @return the context
     */
    public static ServletContext getContext() {
        return (context);
    }

    /**
     * @param context
     *            the context to set
     */
    public static void setContext(final ServletContext context) {
        KonfigurationAllgemein.context = context;
    }

    /**
     * @return the fixwerteTypen
     */
    public static List<String> getFixwerteTypen() {
        return (fixwerteTypen);
    }

    /**
     * @param fixwerteTypen
     *            the fixwerteTypen to set
     */
    public static void setFixwerteTypen(final List<String> fixwerteTypen) {
        KonfigurationAllgemein.fixwerteTypen = fixwerteTypen;
    }

    /**
     * @return the fixwerteMappingTypen
     */
    public static List<String> getFixwerteMappingTypen() {
        return (fixwerteMappingTypen);
    }

    /**
     * @param fixwerteMappingTypen
     *            the fixwerteMappingTypen to set
     */
    public static void setFixwerteMappingTypen(final List<String> fixwerteMappingTypen) {
        KonfigurationAllgemein.fixwerteMappingTypen = fixwerteMappingTypen;
    }

    /**
     * @return the anwendung
     */
    public static String getAnwendung() {
        return anwendung;
    }

    /**
     * @param anwendung
     *            the anwendung to set
     */
    public static void setAnwendung(final String anwendung) {
        KonfigurationAllgemein.anwendung = anwendung;
    }

    /**
     * @return the version
     */
    public static String getVersion() {
        return (version);
    }

    /**
     * @param version
     *            the version to set
     */
    public static void setVersion(final String version) {
        KonfigurationAllgemein.version = version;
    }

    /**
     * @return the systemmodus
     */
    public static Systemmodus getSystemmodus() {
        return (systemmodus);
    }

    /**
     * @param systemmodus
     *            the systemmodus to set
     */
    public static void setSystemmodus(final Systemmodus systemmodus) {
        KonfigurationAllgemein.systemmodus = systemmodus;
    }

    /**
     * @return laeuft die Anwendung auf irgendeiner Testumgebung?
     */
    public static boolean isTestumgebung() {
        final String betriebsumgebung = new TypConverter()
                .getNotNullString(KonfigurationAllgemein.getProperty("betriebsumgebung"));
        final boolean isTestumgebung = isEntwicklungsumgebung() || isFunktionstestumgebung() || isSystemtestumgebung();
        log.debug("betriebsumgebung=" + betriebsumgebung + "; isTestumgebung=" + isTestumgebung);
        return (isTestumgebung);
    }

    /**
     * @return laeuft die Anwendung in der lokalen Entwicklungsumgebung?
     */
    public static boolean isEntwicklungsumgebung() {
        final String betriebsumgebung = new TypConverter()
                .getNotNullString(KonfigurationAllgemein.getProperty("betriebsumgebung"));
        final boolean isEntwicklungsumgebung = (betriebsumgebung.equalsIgnoreCase("lokal"));
        log.debug("betriebsumgebung=" + betriebsumgebung + "; isEntwicklungsumgebung=" + isEntwicklungsumgebung);
        return (isEntwicklungsumgebung);
    }

    /**
     * @return laeuft die Anwendung in der Funktionstestumgebung?
     */
    public static boolean isFunktionstestumgebung() {
        final String betriebsumgebung = new TypConverter()
                .getNotNullString(KonfigurationAllgemein.getProperty("betriebsumgebung"));
        final boolean isFunktionstestumgebung = (betriebsumgebung.equalsIgnoreCase("funktionstest"));
        log.debug("betriebsumgebung=" + betriebsumgebung + "; isFunktionstestumgebung=" + isFunktionstestumgebung);
        return (isFunktionstestumgebung);
    }

    /**
     * @return laeuft die Anwendung in der Systemtestumgebung?
     */
    public static boolean isSystemtestumgebung() {
        final String betriebsumgebung = new TypConverter()
                .getNotNullString(KonfigurationAllgemein.getProperty("betriebsumgebung"));
        final boolean isSystemtestumgebung = (betriebsumgebung.equalsIgnoreCase("systemtest"));
        log.debug("betriebsumgebung=" + betriebsumgebung + "; isSystemtestumgebung=" + isSystemtestumgebung);
        return (isSystemtestumgebung);
    }
}
