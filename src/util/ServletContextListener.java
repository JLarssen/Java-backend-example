package util;

import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sv.utils.beans.KonfigurationEinlesen;
import sv.utils.beans.KonfigurationAllgemein;
import constants.Constants_Datumsformat;
import sv.utils.JPAUtil;
import sv.utils.TypConverter;
import sv.utils.soap.dsl.v2.typen.schreibedatenschutzlog.Systemmodus;
import sv.utils.ExceptionHandler;

/**
 * @author SV-Benutzer
 *
 */
public class ServletContextListener implements javax.servlet.ServletContextListener {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Helper helper;

    private final ExceptionHandler exceptionHandler;

    /**
     *
     * Default-Konstruktor
     *
     */
    public ServletContextListener() {
        this.helper = new Helper();
        this.exceptionHandler = new ExceptionHandler();
    }

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        this.log.info("starte " + KonfigurationAllgemein.getAnwendung() + "...");
        try {
            KonfigurationAllgemein.setSystemmodus(Systemmodus.O);
            // Umgebungsvariablen anzeigen
            try {
                this.helper.propertiesAnzeigen(System.getProperties());
            } catch (final Exception e) {
                this.log.error(this.exceptionHandler.getErrorMeldung(e, (String) null));
            }
            try {
                final ServletContext context = sce.getServletContext();
                // Speichern wann die Anwendung gestartet wurde
                try {
                    context.setAttribute("zeitpunkt_tarifkatalog_init",
                            new TypConverter().getNotNullString(new Timestamp(System.currentTimeMillis()),
                                    Constants_Datumsformat.DATUMSFORMAT_MIT_UHRZEIT));
                } catch (final Exception e) {
                    this.log.error(this.exceptionHandler.getErrorMeldung(e, (String) null));
                }
                KonfigurationAllgemein.setContext(context);
                this.log.info("Referenz auf ServletContext gespeichert: " + KonfigurationAllgemein.getContext());
            } catch (final Exception e) {
                this.log.error(this.exceptionHandler.getErrorMeldung(e, (String) null));
            }
            // Einlesen der Konfigurationseinstellungen
            try {
                this.log.info("lese XML-Dateien ein...");
                new KonfigurationEinlesen().allesNeuEinlesenUndVerarbeiten(null);
            } catch (final Exception e) {
                this.log.error(this.exceptionHandler.getErrorMeldung(e, (String) null));
            }
            // Initialisieren der PerformanceAnalyse
            try {
                util.PerformanceAnalyse.initLetzteAnalyseZeitpunkt();
            } catch (final Exception e) {
                this.log.error(this.exceptionHandler.getErrorMeldung(e, (String) null));
            }
        } catch (final Exception e) {
            this.log.error(this.exceptionHandler.getErrorMeldung(e, (String) null));
        }
        this.log.info(KonfigurationAllgemein.getAnwendung() + " gestartet.");
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        this.log.info("beende " + KonfigurationAllgemein.getAnwendung() + "...");
        try {
            JPAUtil.shutdown();
        } catch (final Exception e) {
            this.log.error(this.exceptionHandler.getErrorMeldung(e, (String) null));
        }
        // Erstellen der PerformanceAnalyse
        try {
            new PerformanceAnalyse().erstelleAnalyse(null);
        } catch (final Exception e) {
            this.log.error(this.exceptionHandler.getErrorMeldung(e, (String) null));
        }
        this.log.info(KonfigurationAllgemein.getAnwendung() + " beendet.");
    }
}
