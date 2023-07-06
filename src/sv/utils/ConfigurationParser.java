package sv.utils;

import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * liest conf.properties der diese Klasse verwendenden Anwendung aus
 *
 * @author SV-Benutzer
 */
public class ConfigurationParser {
    private static Logger log = LoggerFactory.getLogger(ConfigurationParser.class);

    private final HelperAllgemein helperAllgemein;

    private final ExceptionHandlerAllgemein exceptionHandlerAllgemein;

    private final TypConverter typConverter;

    private final ResourceBundle resourceBundle;

    /**
     * Default-Konstruktor
     */
    public ConfigurationParser() {
        this.helperAllgemein = new HelperAllgemein();
        this.exceptionHandlerAllgemein = new ExceptionHandlerAllgemein();
        this.typConverter = new TypConverter();
        this.resourceBundle = ResourceBundle.getBundle("conf");
    }

    /**
     * gibt den Wert zum uebergebenen Schluessel zurueck, Platzhalter werden
     * dabei mit der Klasse <code>StringPropertyReplacer</code> durch echte
     * Werte ersetzt.
     *
     * @param property
     *            Schluesseleintrag in der .properties-Datei
     * @param request
     *            darf null sein
     * @return gefundenen Wert oder null, falls der Schluessel nicht existiert
     */
    public String getProperty(final String property, final String request) {
        String wert = null;
        try {
            wert = this.typConverter.getNullString(StringPropertyReplacer.replaceProperties(
                    this.typConverter.getNotNullString(this.resourceBundle.getString(property))));
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
        return (wert);
    }

    /**
     * gibt alle Key/Value-Paare (Replaced) in der .properties-Datei in einer
     * Map zurueck
     */
    public Map<String, String> getProperties() {
        final Map<String, String> propertiesMap = new TreeMap<>();
        final Enumeration<String> en = this.resourceBundle.getKeys();
        if (en != null) {
            while (en.hasMoreElements()) {
                final String nextKey = en.nextElement();
                propertiesMap.put(nextKey, getProperty(nextKey, null));
            }
        }
        return (propertiesMap);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("ConfigurationParser[");
        try {
            sb.append(Constants_Allgemein.ENDL).append("Replaced-Properties [")
                    .append(this.helperAllgemein.mapToString(getProperties(), "Replaced-Property")).append("], ");
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, (String) null));
        }
        sb.append("]");
        return (sb.toString());
    }
}
