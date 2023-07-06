package sv.utils;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sv.utils.HelperAllgemein;
import sv.utils.TypConverter;
import sv.utils.beans.KonfigurationAllgemein;
import util.PerformanceAnalyse;


public class JPAUtil {
    private static Logger log = LoggerFactory.getLogger(JPAUtil.class);

    private static String[] bezeichnungen = new String[] { "getEntityManagerFactory()", "getDataSource()",
            "shutdown()" };

    private static long[] zaehler = new long[bezeichnungen.length];

    private static long[] laufzeiten = new long[bezeichnungen.length];

    private final static int INDEX_GET_ENTITYMANAGERFACTORY = 0;

    private final static int INDEX_GET_DATASOURCE = 1;

    private final static int INDEX_SHUTDOWN = 2;

    private static HelperAllgemein helperAllgemein = new HelperAllgemein();

    private static TypConverter typConverter = new TypConverter();

    private static EntityManagerFactory entityManagerFactory = null;

    /**
     * setzt die Werte fuer die Performanceanalyse zurueck
     */
    public static void resetWerte() {
        final PerformanceAnalyse performanceAnalyse = new PerformanceAnalyse();
        zaehler = performanceAnalyse.resetWerte(zaehler);
        laufzeiten = performanceAnalyse.resetWerte(laufzeiten);
    }

    /**
     * erstellt einen String mit den Werten fuer die Performanceanalyse
     */
    public static String performanceAnalyse() {
        return (new PerformanceAnalyse().performanceAnalyse("JPAUtil", bezeichnungen, zaehler, laufzeiten));
    }

    /**
     * Default-Konstruktor; private, da keine Instanzen der Klasse existieren sollen.
     */
    private JPAUtil() {
        super();
    }

    /**
     * liest aus der persistence.xml des Projekts entweder die Konfiguration "persistence_container" oder die
     * Konfiguration "persistence_standalone" aus.
     *
     * Unterscheidungskriterium: Ist <code>KonfigurationAllgemein.getContext()</code> nicht null, so wird
     * "persistence_container" verwendet, sonst "persistence_standalone"
     *
     * "persistence_standalone" wird um eine DBCP2-Datasource angereichert. Die dafuer notwendigen Einstellungen werden
     * aus der <code>Konfiguration</code> ausgelesen.
     *
     * @return the EntityManagerFactory
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        (zaehler[INDEX_GET_ENTITYMANAGERFACTORY])++;
        final long jetzt = System.currentTimeMillis();
        if (entityManagerFactory == null) {
            // damit nicht versehentlich mehrere EntityManagerFactory-Instanzen im Umlauf sind
            synchronized (JPAUtil.class) {
                if (entityManagerFactory == null) {
                    log.info("initialisiere EntityManagerFactory...");
                    final Map<String, Object> props = new HashMap<>();
                    String showSql = "false";
                    if (typConverter.getNotNullString(KonfigurationAllgemein.getProperty("betriebsumgebung"))
                            .equalsIgnoreCase("lokal")) {
                        showSql = "true";
                    }
                    props.put(AvailableSettings.SHOW_SQL, showSql);
                    final String db_default_schema = KonfigurationAllgemein.getProperty("db_default_schema");
                    log.info("JPA-Einstellungen: showSql=" + showSql + "; db_default_schema=" + db_default_schema);
                    if (!helperAllgemein.isEmptyString(db_default_schema)) {
                        props.put(AvailableSettings.DEFAULT_SCHEMA, db_default_schema);
                    }
                    if (KonfigurationAllgemein.getContext() != null) {
                        log.info("EntityManagerFactory verwendet persistence_container.");
                        entityManagerFactory = Persistence.createEntityManagerFactory("persistence_container", props);
                    } else {
                        log.info("EntityManagerFactory verwendet persistence_standalone.");
                        props.put(AvailableSettings.DATASOURCE, getDataSource());
                        entityManagerFactory = Persistence.createEntityManagerFactory("persistence_standalone", props);
                    }
                    log.info(helperAllgemein.mapToString(entityManagerFactory.getProperties(),
                            "EntityManagerFactory-Property"));
                }
            }
        }
        (laufzeiten[INDEX_GET_ENTITYMANAGERFACTORY]) += (System.currentTimeMillis() - jetzt);
        return (entityManagerFactory);
    }

    /**
     * erzeugt eine DataSource fuer Batchprogramme
     */
    private static DataSource getDataSource() {
        (zaehler[INDEX_GET_DATASOURCE])++;
        final long jetzt = System.currentTimeMillis();
        BasicDataSource dataSource = null;
        final String url = KonfigurationAllgemein.getProperty("datasource_url");
        if (!helperAllgemein.isEmptyString(url)) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(KonfigurationAllgemein.getProperty("datasource_driverClassName"));
            dataSource.setUrl(url);
            dataSource.setUsername(KonfigurationAllgemein.getProperty("datasource_username"));
            dataSource.setPassword(KonfigurationAllgemein.getProperty("datasource_password"));
            dataSource.setInitialSize(
                    typConverter.parseInt(KonfigurationAllgemein.getProperty("datasource_initialSize")));
            dataSource.setMinIdle(
                    typConverter.parseInt(KonfigurationAllgemein.getProperty("datasource_minIdle")));
            dataSource.setMaxIdle(
                    typConverter.parseInt(KonfigurationAllgemein.getProperty("datasource_maxIdle")));
            dataSource.setMaxTotal(
                    typConverter.parseInt(KonfigurationAllgemein.getProperty("datasource_maxTotal")));
            dataSource.setMaxWaitMillis(
                    typConverter.parseLong(KonfigurationAllgemein.getProperty("datasource_maxWaitMillis")));
            dataSource.setValidationQuery(KonfigurationAllgemein.getProperty("datasource_validationQuery"));
            dataSource.setRemoveAbandonedOnBorrow(
                    Boolean.parseBoolean(KonfigurationAllgemein.getProperty("datasource_removeAbandonedOnBorrow")));
            dataSource.setRemoveAbandonedTimeout(typConverter
                    .parseInt(KonfigurationAllgemein.getProperty("datasource_removeAbandonedTimeout")));
            dataSource.setLogAbandoned(
                    Boolean.parseBoolean(KonfigurationAllgemein.getProperty("datasource_logAbandoned")));
        } else {
            log.error("Datasource-Konfiguration fehlt!");
        }
        (laufzeiten[INDEX_GET_DATASOURCE]) += (System.currentTimeMillis() - jetzt);
        return (dataSource);
    }

    /**
     * shutdown EntityManagerFactory
     */
    public static synchronized void shutdown() {
        (zaehler[INDEX_SHUTDOWN])++;
        final long jetzt = System.currentTimeMillis();
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
        (laufzeiten[INDEX_SHUTDOWN]) += (System.currentTimeMillis() - jetzt);
    }
}
