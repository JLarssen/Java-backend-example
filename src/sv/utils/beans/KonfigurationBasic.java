package sv.utils.beans;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import constants.Constants_Allgemein;

/**
 * Klasse mit Konfigurationseinstellungen
 *
 * @author SV-Benutzer
 */
public class KonfigurationBasic extends KonfigurationAllgemein implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    /**
     * Pfad zum Standalone-ApplicationContext
     */
    private static String applicationContextStandalone;

    /**
     * Pfad zur Standalone-Fixwerte-Datei
     */
    private static String standaloneFixwerteDatei;

    /**
     * Pfad zur Standalone-Fixwerte-Mapping-Datei
     */
    private static String standaloneFixwerteMappingDatei;

    /**
     * Pfad zur Standalone-Fixwerte-Kontakte-Datei
     */
    private static String standaloneFixwerteKontakteDatei;

    /**
     * @return String
     */
    protected static String konfigurationBasicToString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("KonfigurationBasic[");
        sb.append(Constants_Allgemein.ENDL).append(KonfigurationAllgemein.konfigurationAllgemeinToString());
        sb.append(Constants_Allgemein.ENDL).append("applicationContext = ").append(applicationContext).append(", ");
        sb.append(Constants_Allgemein.ENDL).append("applicationContextStandalone = ")
                .append(applicationContextStandalone).append(", ");
        sb.append(Constants_Allgemein.ENDL).append("standaloneFixwerteDatei = ").append(standaloneFixwerteDatei)
                .append(", ");
        sb.append(Constants_Allgemein.ENDL).append("standaloneFixwerteMappingDatei = ")
                .append(standaloneFixwerteMappingDatei).append(", ");
        sb.append(Constants_Allgemein.ENDL).append("standaloneFixwerteKontakteDatei = ")
                .append(standaloneFixwerteKontakteDatei).append(", ");
        sb.append("]");
        return (sb.toString());
    }

    /**
     * @return the applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return (applicationContext);
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        KonfigurationBasic.applicationContext = applicationContext;
    }

    /**
     * @return the applicationContextStandalone
     */
    public static String getApplicationContextStandalone() {
        return applicationContextStandalone;
    }

    /**
     * @param applicationContextStandalone
     *            the applicationContextStandalone to set
     */
    public static void setApplicationContextStandalone(final String applicationContextStandalone) {
        KonfigurationBasic.applicationContextStandalone = applicationContextStandalone;
    }

    /**
     * @return the standaloneFixwerteMappingDatei
     */
    public static String getStandaloneFixwerteMappingDatei() {
        return (standaloneFixwerteMappingDatei);
    }

    /**
     * @param standaloneFixwerteMappingDatei
     *            the standaloneFixwerteMappingDatei to set
     */
    public static void setStandaloneFixwerteMappingDatei(final String standaloneFixwerteMappingDatei) {
        KonfigurationBasic.standaloneFixwerteMappingDatei = standaloneFixwerteMappingDatei;
    }

    /**
     * @return the standaloneFixwerteDatei
     */
    public static String getStandaloneFixwerteDatei() {
        return (standaloneFixwerteDatei);
    }

    /**
     * @param standaloneFixwerteDatei
     *            the standaloneFixwerteDatei to set
     */
    public static void setStandaloneFixwerteDatei(final String standaloneFixwerteDatei) {
        KonfigurationBasic.standaloneFixwerteDatei = standaloneFixwerteDatei;
    }

    /**
     * @return the standaloneFixwerteKontakteDatei
     */
    public static String getStandaloneFixwerteKontakteDatei() {
        return (standaloneFixwerteKontakteDatei);
    }

    /**
     * @param standaloneFixwerteKontakteDatei
     *            the standaloneFixwerteKontakteDatei to set
     */
    public static void setStandaloneFixwerteKontakteDatei(final String standaloneFixwerteKontakteDatei) {
        KonfigurationBasic.standaloneFixwerteKontakteDatei = standaloneFixwerteKontakteDatei;
    }
}
