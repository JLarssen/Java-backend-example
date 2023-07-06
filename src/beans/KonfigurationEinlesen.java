package beans;

import beans.Konfiguration;
import sv.utils.ConfigurationParser;
import sv.utils.ExceptionHandler;
import sv.utils.beans.KonfigurationAllgemein;

/**
 * @author SV-Benutzer
 *
 */
public class KonfigurationEinlesen extends sv.utils.beans.KonfigurationEinlesen {
    private final ConfigurationParser configurationParser;

    private final ExceptionHandler exceptionHandler;

    /**
     * Default-Konstruktor
     */
    public KonfigurationEinlesen() {
        super();
        this.configurationParser = new ConfigurationParser();
        this.exceptionHandler = new ExceptionHandler();
    }

    /**
     * zentraler Aufruf aller in der Klasse zum XML-Auslesen enthaltenen
     * Methoden
     */
    public synchronized boolean allesNeuEinlesenUndVerarbeiten(final String request) {
        boolean rueckgabe = true;
        try {
            // Konfigurationsdatei muss unbedingt zuerst eingelessen werden!!!
            rueckgabe &= parseKonfigurationsDatei(request);
            rueckgabe &= parseKonfigurationMailversandDatei(request);
            rueckgabe &= parseStandaloneDatasourceDatei(request);
            KonfigurationAllgemein.setVersion(this.configurationParser.getProperty("versionsnummer", request));
            new KonfigurationVerarbeiten().reloadKonfiguration(request);
        } catch (final Exception e) {
            rueckgabe = false;
            this.log.error(this.exceptionHandler.getErrorMeldung(e, request));
        }
        this.log.info("erfolgreich? " + rueckgabe + "; " + Konfiguration.konfigurationToString());
        return (rueckgabe);
    }
}
