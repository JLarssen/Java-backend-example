package sv.utils.beans;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import constants.Constants_Allgemein;
import sv.utils.ConfigurationParser;
import sv.utils.ExceptionHandler;
import sv.utils.Helper;
import sv.utils.beans.KonfigurationAllgemein;
import sv.utils.beans.KonfigurationMailversand;
import sv.utils.TypConverter;

/**
 * @author SV-Benutzer
 *
 */
public abstract class KonfigurationEinlesen extends KonfigurationEinlesenAllgemein {
    private final ConfigurationParser configurationParser;

    private final ExceptionHandler exceptionHandler;

    private final Helper helper;

    private final TypConverter typConverter;

    /**
     * Default-Konstruktor
     */
    public KonfigurationEinlesen() {
        this.configurationParser = new ConfigurationParser();
        this.exceptionHandler = new ExceptionHandler();
        this.helper = new Helper();
        this.typConverter = new TypConverter();
    }

    /**
     * liest die XML-Konfigurationsdatei (configurationParser.getProperty("conf_xml")) und die
     * Webservice-XML-Konfigurationsdatei (KonfigurationAllgemein.getProperty("config_xml")) + "webserviceconf.xml")
     * eines
     * Projektes ein
     *
     * DTD: <!DOCTYPE Konfiguration[ <!ELEMENT Konfiguration (Mapping*)>
     * <!ELEMENT Mapping (Beschreibung?, Eigenschaft, Wert)> <!ELEMENT
     * Beschreibung (#PCDATA)> <!ELEMENT Eigenschaft (#PCDATA)> <!ELEMENT Wert
     * (#PCDATA)> ]>
     *
     * @return false bei einem invaliden XML-Dokument, true in allen anderen
     *         Faellen (valides XML, Datei nicht vorhanden, ...)
     */
    @Override
    protected boolean parseKonfigurationsDatei(final String request) {
        boolean rueckgabe = true;
        try {
            final String pfad1 = this.configurationParser.getProperty("conf_xml", request);
            this.log.info("lade Konfiguration aus: " + pfad1 + "...");
            parseKonfigurationsDateiAllgemein(
                    this.helper.getDateiAlsString(pfad1, null, Constants_Allgemein.ENCODING_FILE, request), true);
            final String pfad2 = KonfigurationAllgemein.getProperty("config_xml") + "webserviceconf.xml";
            this.log.info("lade Webservice-Konfiguration aus: " + pfad2 + "...");
            parseKonfigurationsDateiAllgemein(
                    this.helper.getDateiAlsString(pfad2, null, Constants_Allgemein.ENCODING_FILE, request), false);
        } catch (final Exception e) {
            rueckgabe = false;
            this.log.error(this.exceptionHandler.getErrorMeldung(e, request));
        }
        return (rueckgabe);
    }

    /**
     * liest die XML-Konfigurationsdatei eines Projektes ein
     *
     * DTD: <!DOCTYPE Konfiguration[ <!ELEMENT Konfiguration (Mapping*)>
     * <!ELEMENT Mapping (Beschreibung?, Eigenschaft, Wert)> <!ELEMENT
     * Beschreibung (#PCDATA)> <!ELEMENT Eigenschaft (#PCDATA)> <!ELEMENT Wert
     * (#PCDATA)> ]>
     *
     * @param clear
     *            soll die bestehende Konfiguration zuerst geloescht werden?
     *
     * @throws DocumentException
     *             falls der uebergebene String kein wohlgeformtes XML-Dokument
     *             ist
     */
    private void parseKonfigurationsDateiAllgemein(final String xmlFileAsString, final boolean clear)
            throws DocumentException {
        if (!this.helper.isEmptyString(xmlFileAsString)) {
            // Liste der Knoten im XML-Dokument, nach denen gesucht wird
            // globale Knoten
            final String MAPPING = "//Mapping";
            // lokale Knoten
            final String EIGENSCHAFT = "Eigenschaft";
            final String WERT = "Wert";
            // lies das source xml und erzeuge ein neues dokument
            final Document doc = DocumentHelper.parseText(xmlFileAsString);
            if (doc != null) {
                final Element root = doc.getRootElement();
                final Map<String, String> propertiesMap = new HashMap<>();
                if (root != null) {
                    final List<Node> mappingNodes = this.helper.getGenericObject(doc.selectNodes(MAPPING));
                    if (mappingNodes != null) {
                        final Iterator<Node> it = mappingNodes.iterator();
                        if (it != null) {
                            while (it.hasNext()) {
                                final Node next = it.next();
                                if (next != null) {
                                    String eigenschaft = null;
                                    String wert = null;
                                    final Node eigenschaftNode = next.selectSingleNode(EIGENSCHAFT);
                                    if (eigenschaftNode != null) {
                                        eigenschaft = this.typConverter.getNullString(eigenschaftNode.getText());
                                    }
                                    final Node wertNode = next.selectSingleNode(WERT);
                                    if (wertNode != null) {
                                        wert = this.typConverter.getNullString(wertNode.getText());
                                    }
                                    if (!this.helper.isEmptyString(eigenschaft)) {
                                        propertiesMap.put(eigenschaft, wert);
                                    }
                                }
                            }
                        }
                    }
                }
                if (clear) {
                    KonfigurationAllgemein.setPropertiesMap(propertiesMap);
                } else {
                    final Map<String, String> map = KonfigurationAllgemein.getPropertiesMap();
                    // gibt es ueberhaupt schon eine Konfiguration?
                    // - nein => Map wird einfach so eingetragen
                    if (map == null) {
                        KonfigurationAllgemein.setPropertiesMap(propertiesMap);
                    }
                    // - ja => bestehende Map wird erweitert
                    else {
                        map.putAll(propertiesMap);
                        KonfigurationAllgemein.setPropertiesMap(map);
                    }
                }
            }
        } else {
            this.log.error("keine Datei erhalten!");
        }
    }

    /**
     * liest die Einstellungen fuer den Mailversand ein
     *
     * DTD: <!DOCTYPE Mailkonfiguration [ <!ELEMENT Mailkonfiguration
     * (SMTPHost?, SMTPPort?, Von, VonExtern?, Fehler?, PerformanceStatistik?)> <!ELEMENT
     * SMTPHost (#PCDATA)> <!ELEMENT SMTPPort (#PCDATA)> <!ELEMENT Von
     * (#PCDATA)> <!ELEMENT Fehler (FehlerAn*)> <!ELEMENT FehlerAn (#PCDATA)>
     * <!ELEMENT PerformanceStatistik (PerformanceStatistikAn*)> <!ELEMENT
     * PerformanceStatistikAn (#PCDATA)> ]>
     *
     * @return false bei einem invaliden XML-Dokument, true in allen anderen
     *         Faellen (valides XML, Datei nicht vorhanden, ...)
     */
    protected boolean parseKonfigurationMailversandDatei(final String request) {
        boolean rueckgabe = true;
        try {
            final String pfad = KonfigurationAllgemein.getProperty("config_xml") + "mailkonfiguration.xml";
            this.log.info("lade Mailversand-Konfiguration aus: " + pfad + "...");
            final String xmlFileAsString = this.helper.getDateiAlsString(pfad, null, Constants_Allgemein.ENCODING_FILE,
                    request);
            if (!this.helper.isEmptyString(xmlFileAsString)) {
                // Liste der Knoten im XML-Dokument, nach denen gesucht wird
                // globale Knoten
                final String SMTP_HOST = "//SMTPHost";
                final String SMTP_PORT = "//SMTPPort";
                final String VON = "//Von";
                final String VON_EXTERN = "//VonExtern";
                final String ERROR_AN = "//FehlerAn";
                final String PERFORMANCE_AN = "//PerformanceStatistikAn";
                // lies das source xml und erzeuge ein neues dokument
                final Document doc = DocumentHelper.parseText(xmlFileAsString);
                if (doc != null) {
                    final Element root = doc.getRootElement();
                    if (root != null) {
                        // SMTP-Host
                        final Node hostNode = doc.selectSingleNode(SMTP_HOST);
                        if (hostNode != null) {
                            KonfigurationMailversand.setSmtp_host(this.typConverter.getNullString(hostNode.getText()));
                        }
                        // SMTP-Port
                        final Node portNode = doc.selectSingleNode(SMTP_PORT);
                        if (portNode != null) {
                            KonfigurationMailversand.setSmtp_port(this.typConverter.getNullInteger(portNode.getText()));
                        }
                        // Absende-E-Mail-Adresse (fuer interne Mails)
                        final Node vonNode = doc.selectSingleNode(VON);
                        if (vonNode != null) {
                            KonfigurationMailversand.setVon(this.typConverter.getNullString(vonNode.getText()));
                        }
                        // Absende-E-Mail-Adresse (fuer Mails nach aussen)
                        final Node vonExternNode = doc.selectSingleNode(VON_EXTERN);
                        if (vonExternNode != null) {
                            KonfigurationMailversand
                                    .setVonExtern(this.typConverter.getNullString(vonExternNode.getText()));
                        }
                        // Fehlermail-Empfaenger
                        final List<String> errorAn = new LinkedList<>();
                        final List<Element> errorAnNodes = this.helper.getGenericObject(doc.selectNodes(ERROR_AN));
                        if (errorAnNodes != null) {
                            final Iterator<Element> it = errorAnNodes.iterator();
                            if (it != null) {
                                while (it.hasNext()) {
                                    final Element next = it.next();
                                    if (next != null) {
                                        final String error = this.typConverter.getNullString(next.getText());
                                        if (!this.helper.isEmptyString(error)) {
                                            errorAn.add(error);
                                        }
                                    }
                                }
                            }
                        }
                        KonfigurationMailversand.setFehlerAn(errorAn);
                        // Performance-Statistik-Empfaenger
                        final List<String> performanceAn = new LinkedList<>();
                        final List<Element> performanceAnNodes = this.helper
                                .getGenericObject(doc.selectNodes(PERFORMANCE_AN));
                        if (performanceAnNodes != null) {
                            final Iterator<Element> it = performanceAnNodes.iterator();
                            if (it != null) {
                                while (it.hasNext()) {
                                    final Element next = it.next();
                                    if (next != null) {
                                        final String performance = this.typConverter.getNullString(next.getText());
                                        if (!this.helper.isEmptyString(performance)) {
                                            performanceAn.add(performance);
                                        }
                                    }
                                }
                            }
                        }
                        KonfigurationMailversand.setPerformanceStatistikAn(performanceAn);
                    }
                }
            } else {
                this.log.error("keine Datei erhalten!");
            }
        } catch (final Exception e) {
            rueckgabe = false;
            this.log.error(this.exceptionHandler.getErrorMeldung(e, request));
        }
        return (rueckgabe);
    }

    /**
     * liest die XML-Konfigurationsdatei fuer gs-utils-taglib ein
     *
     * ACHTUNG: sofern auch "parseKonfigurationsDateiAllgemein" aufgerufen
     * werden soll, muss "parseKonfigurationsDateiAllgemein" vor
     * "parseTaglibKonfigurationsDatei" aufgerufen werden, da sonst die
     * Ergebnisse von "parseTaglibKonfigurationsDatei" durch
     * "parseKonfigurationsDateiAllgemein" geloescht werden!
     */
    protected boolean parseTaglibKonfigurationsDatei(final String request) {
        boolean rueckgabe = true;
        try {
            final String pfad = this.helper.fuegePfadSeperatorAnPfadAn(System.getProperty("jboss.server.config.dir"))
                    + "sv/gs-utils-taglib-conf.xml";
            this.log.info("lade Taglib-Konfiguration aus: " + pfad + "...");
            if (new File(pfad).canRead()) {
                parseKonfigurationsDateiAllgemein(
                        this.helper.getDateiAlsString(pfad, null, Constants_Allgemein.ENCODING_FILE, request), false);
            } else {
                this.log.info("Taglib-Datei wurde nicht eingelesen");
            }
        } catch (final Exception e) {
            rueckgabe = false;
            this.log.error(this.exceptionHandler.getErrorMeldung(e, request));
        }
        return (rueckgabe);
    }

    /**
     * falls vorhanden, wird die Standalone-Datasource eines Projekts verarbeitet
     * (KonfigurationAllgemein.getProperty("config_xml") + "datasourceStandalone.xml" bzw. deprecated
     * KonfigurationAllgemein.getProperty("spring_datasource")).
     */
    protected boolean parseStandaloneDatasourceDatei(final String request) {
        boolean rueckgabe = true;
        try {
            final String pfadDatasourceStandalone = KonfigurationAllgemein.getProperty("config_xml")
                    + "datasourceStandalone.xml";
            this.log.info("lade Standalone-Datasource aus: " + pfadDatasourceStandalone + "...");
            if (new File(pfadDatasourceStandalone).exists()) {
                parseKonfigurationsDateiAllgemein(this.helper.getDateiAlsString(pfadDatasourceStandalone, null,
                        Constants_Allgemein.ENCODING_FILE, request), false);
            } else {
                this.log.info("Datei " + pfadDatasourceStandalone + " existiert nicht.");
                rueckgabe = parseSpringStandaloneDatasourceDatei(request);
            }
        } catch (final Exception e) {
            rueckgabe = false;
            this.log.error(this.exceptionHandler.getErrorMeldung(e, request));
        }
        return (rueckgabe);
    }

    /**
     * traegt den Inhalt einer alten Spring-DsStandalone.xml in die Konfiguration ein
     *
     * TODO: Markus: nach Modernisierung aller Projekte entfernen
     */
    private boolean parseSpringStandaloneDatasourceDatei(final String request) {
        boolean rueckgabe = true;
        try {
            final String pfadSpringDatasource = KonfigurationAllgemein.getProperty("spring_datasource");
            if (!this.helper.isEmptyString(pfadSpringDatasource)) {
                this.log.info("lade Standalone-Datasource aus Spring-Datei: " + pfadSpringDatasource + "...");
                if (new File(pfadSpringDatasource).exists()) {
                    // alte DS-Konfiguration gefunden
                    final String xmlFileAsString = this.helper.getDateiAlsString(pfadSpringDatasource, null,
                            Constants_Allgemein.ENCODING_FILE, request);
                    if (!this.helper.isEmptyString(xmlFileAsString)) {
                        // Liste der Knoten im XML-Dokument, nach denen gesucht wird
                        // globale Knoten
                        final String PROPERTY = "//*[local-name()='property']";
                        // lokale Knoten
                        final String VALUE = "*[local-name()='value']";
                        // Attribute
                        final String NAME = "name";
                        // lies das source xml und erzeuge ein neues dokument
                        final Document doc = DocumentHelper.parseText(xmlFileAsString);
                        if (doc != null) {
                            final Element root = doc.getRootElement();
                            if (root != null) {
                                final Map<String, String> propertiesMap = new HashMap<>();
                                // Fehlermail-Empfaenger
                                final List<Element> propertyNodes = this.helper
                                        .getGenericObject(doc.selectNodes(PROPERTY));
                                if (!this.helper.isEmptyCollection(propertyNodes)) {
                                    for (final Element propertyElement : propertyNodes) {
                                        if (propertyElement != null) {
                                            String key = null;
                                            String value = null;
                                            final Attribute attribute = propertyElement.attribute(NAME);
                                            if (attribute != null) {
                                                key = this.typConverter.getNullString(attribute.getText());
                                            }
                                            final Element valueElement = this.helper
                                                    .getGenericObject(propertyElement.selectSingleNode(VALUE));
                                            if (valueElement != null) {
                                                value = this.typConverter.getNullString(valueElement.getText());
                                            }
                                            if ((!this.helper.isEmptyString(key))
                                                    && (!this.helper.isEmptyString(value))) {
                                                propertiesMap.put("datasource_" + key, value);
                                            } else {
                                                this.log.error("Property ist nicht korrekt definiert! name=" + key
                                                        + ", value=" + value + "; property=" + propertyElement);
                                            }
                                        }
                                    }
                                }
                                final Map<String, String> map = KonfigurationAllgemein.getPropertiesMap();
                                // gibt es ueberhaupt schon eine Konfiguration?
                                // - nein => Map wird einfach so eingetragen
                                if (map == null) {
                                    KonfigurationAllgemein.setPropertiesMap(propertiesMap);
                                }
                                // - ja => bestehende Map wird erweitert
                                else {
                                    map.putAll(propertiesMap);
                                    KonfigurationAllgemein.setPropertiesMap(map);
                                }
                            }
                        }
                    }
                } else {
                    this.log.info("Datei " + pfadSpringDatasource + " existiert nicht.");
                }
            } else {
                this.log.info("Pfad zur Standalone-Datasource-Spring-Datei fehlt.");
            }
        } catch (final Exception e) {
            rueckgabe = false;
            this.log.error(this.exceptionHandler.getErrorMeldung(e, request));
        }
        return (rueckgabe);
    }
}
