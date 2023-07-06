package sv.utils.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author SV-Benutzer
 *
 */
public abstract class KonfigurationEinlesenAllgemein {
    protected Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Default-Konstruktor
     */
    public KonfigurationEinlesenAllgemein() {
        super();
    }

    /**
     * liest die Konfigurationsdatei eines Projektes ein
     */
    protected abstract boolean parseKonfigurationsDatei(final String request);
}
