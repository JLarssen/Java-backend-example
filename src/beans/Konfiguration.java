package beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.xml.bind.Marshaller;

import db.IPersistenceService;
import sv.utils.beans.KonfigurationBasic;
import constants.Constants_Allgemein;

/**
 * Klasse mit Konfigurationseinstellungen
 *
 * @author SV-Benutzer
 */
@Named
@ApplicationScoped
public class Konfiguration extends sv.utils.beans.KonfigurationBasic {
    private static IPersistenceService persistenceService;

    private static Marshaller marshallerEsvtarifuebergabe = null;

    private static Marshaller marshallerTarifAntwort = null;

    private static Marshaller marshallerTarifZusammenfassung = null;

    /**
     * Default-Konstruktor
     */
    public Konfiguration() {
        super();
    }

    /**
     * erzeugt einen String mit allen Konfigurationseinstellungen
     */
    public static String konfigurationToString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Konfiguration[");
        sb.append(Constants_Allgemein.ENDL).append(konfigurationBasicToString()).append(", ");
        sb.append(Constants_Allgemein.ENDL).append("marshallerEsvtarifuebergabe = ").append(marshallerEsvtarifuebergabe)
                .append(", ");
        sb.append(Constants_Allgemein.ENDL).append("marshallerTarifAntwort = ").append(marshallerTarifAntwort)
                .append(", ");
        sb.append(Constants_Allgemein.ENDL).append("marshallerTarifZusammenfassung = ")
                .append(marshallerTarifZusammenfassung).append(", ");
        sb.append("]");
        return (sb.toString());
    }

    /**
     * @return the persistenceService
     */
    public static IPersistenceService getPersistenceService() {
        return (persistenceService);
    }

    /**
     * @param persistenceService
     *            the persistenceService to set
     */
    public static void setPersistenceService(final IPersistenceService persistenceService) {
        Konfiguration.persistenceService = persistenceService;
    }

    /**
     * @return the marshallerEsvtarifuebergabe
     */
    public static Marshaller getMarshallerEsvtarifuebergabe() {
        return (marshallerEsvtarifuebergabe);
    }

    /**
     * @param marshallerEsvtarifuebergabe
     *            the marshallerEsvtarifuebergabe to set
     */
    public static void setMarshallerEsvtarifuebergabe(final Marshaller marshallerEsvtarifuebergabe) {
        Konfiguration.marshallerEsvtarifuebergabe = marshallerEsvtarifuebergabe;
    }

    /**
     * @return the marshallerTarifAntwort
     */
    public static Marshaller getMarshallerTarifAntwort() {
        return (marshallerTarifAntwort);
    }

    /**
     * @param marshallerTarifAntwort
     *            the marshallerTarifAntwort to set
     */
    public static void setMarshallerTarifAntwort(final Marshaller marshallerTarifAntwort) {
        Konfiguration.marshallerTarifAntwort = marshallerTarifAntwort;
    }

    /**
     * @return the marshallerTarifZusammenfassung
     */
    public static Marshaller getMarshallerTarifZusammenfassung() {
        return (marshallerTarifZusammenfassung);
    }

    /**
     * @param marshallerTarifZusammenfassung
     *            the marshallerTarifZusammenfassung to set
     */
    public static void setMarshallerTarifZusammenfassung(final Marshaller marshallerTarifZusammenfassung) {
        Konfiguration.marshallerTarifZusammenfassung = marshallerTarifZusammenfassung;
    }
}
