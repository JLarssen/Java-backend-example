package sv.utils.beans;

import java.io.FileInputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.UUID;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.Constants_Allgemein;
import constants.Constants_Wsdl;
import sv.utils.HelperAllgemein;
import sv.utils.TypConverter;
import sv.utils.beans.KonfigurationAllgemein;
import sv.utils.logic.performance.PerformanceLogInInterceptor;
import sv.utils.logic.performance.PerformanceLogOutInterceptor;
import sv.utils.soap.appverstaendigungen.VerstaendigungenService;
import sv.utils.soap.appverstaendigungen.VerstaendigungenServicePort;
import sv.utils.soap.archiv.ArchivService;
import sv.utils.soap.archiv.ArchivServicePort;
import sv.utils.soap.beve.Bearbeiter;
import sv.utils.soap.beve.BearbeiterverzeichnisService;
import sv.utils.soap.beve.BearbeiterverzeichnisServicePort;
import sv.utils.soap.commons.AnfragendeStelle;
import sv.utils.soap.commons.SoapAnfrage;
import sv.utils.soap.commons.SoapBearbeiter;
import sv.utils.soap.commons.SoapPerson;
import sv.utils.soap.dika.SOAPReceiverServiceDika;
import sv.utils.soap.dika.SOAPReceiverServiceDikaService;
import sv.utils.soap.dsl.v2.SOAPReceiverServiceDsl;
import sv.utils.soap.dsl.v2.SOAPReceiverServiceDslService;
import sv.utils.soap.ebdb.LoginExternPortType;
import sv.utils.soap.ebdb.LoginExternService;
import sv.utils.soap.eko.SOAPReceiverServiceEko;
import sv.utils.soap.eko.SOAPReceiverServiceEkoService;
import sv.utils.soap.faxproxy.SOAPReceiverServicePort;
import sv.utils.soap.faxproxy.SOAPReceiverServicePortImplService;
import sv.utils.soap.fixwerte.SOAPReceiverServiceFixwerte;
import sv.utils.soap.fixwerte.SOAPReceiverServiceFixwerteService;
import sv.utils.soap.gesundheitskonto.SOAPReceiverServiceGesundheitskonto;
import sv.utils.soap.gesundheitskonto.SOAPReceiverServiceGesundheitskontoService;
import sv.utils.soap.infinica.ProcessExecutionManager;
import sv.utils.soap.infinica.ProcessExecutionManagerService;
import sv.utils.soap.kanst.SOAPReceiverServiceKanst;
import sv.utils.soap.kanst.SOAPReceiverServiceKanstService;
import sv.utils.soap.koa.SOAPReceiverServiceKoa;
import sv.utils.soap.koa.SOAPReceiverServiceKoaService;
import sv.utils.soap.lea.typen.Verstaendigung;
import sv.utils.soap.lea.typen.VerstaendigungPortType;
import sv.utils.soap.lotusnotes.formular.SOAPReceiverServiceFormulare;
import sv.utils.soap.lotusnotes.formular.SOAPReceiverServiceFormulare_Service;
import sv.utils.soap.lw.onlineservices.ISvOnlineService;
import sv.utils.soap.lw.onlineservices.SvOnlineServiceWS;
import sv.utils.soap.lw.uvinfoservice.ISvbUvInfoService;
import sv.utils.soap.lw.uvinfoservice.SvbUvInfoServiceWS;
import sv.utils.soap.massenbewilligung.SOAPReceiverServiceMassenbewilligung;
import sv.utils.soap.massenbewilligung.SOAPReceiverServiceMassenbewilligungService;
import sv.utils.soap.pati.v2.SOAPReceiverServicePati;
import sv.utils.soap.pati.v2.SOAPReceiverServicePatiService;
import sv.utils.soap.pati.v2.SOAPReceiverServiceUlei;
import sv.utils.soap.pati.v2.SOAPReceiverServiceUleiService;
import sv.utils.soap.pdferzeugung.PdfErzeugungService;
import sv.utils.soap.pdferzeugung.PdfErzeugungServicePort;
import sv.utils.soap.stammdaten.v2.StammdatenService;
import sv.utils.soap.stammdaten.v2.StammdatenServicePort;
import sv.utils.soap.standesdaten.StandesdatenService;
import sv.utils.soap.standesdaten.StandesdatenServicePort;
import sv.utils.soap.svegda.SOAPReceiverServicesvegda;
import sv.utils.soap.svegda.SOAPReceiverServicesvegdaService;
import sv.utils.soap.svkai.antrag.SOAPReceiverServiceSvkai;
import sv.utils.soap.svkai.antrag.SOAPReceiverServiceSvkaiService;
import sv.utils.soap.svkai.meldung.SOAPReceiverServiceKai;
import sv.utils.soap.svkai.meldung.SOAPReceiverServiceKaiService;
import sv.utils.soap.svkai.verguetungdaten.KAIVerguetung;
import sv.utils.soap.svkai.verguetungdaten.KAIVerguetungPartner;
import sv.utils.soap.tarifkatalog.TarifkatalogService;
import sv.utils.soap.tarifkatalog.TarifkatalogServicePort;
import sv.utils.soap.verrechnung.SOAPReceiverServiceVerrechnung;
import sv.utils.soap.verrechnung.SOAPReceiverServiceVerrechnungService;
import sv.utils.soap.vertragspartner.VertragspartnerService;
import sv.utils.soap.vertragspartner.VertragspartnerServicePort;
import sv.utils.soap.woke.v2.SOAPReceiverServiceWoke;
import sv.utils.soap.woke.v2.SOAPReceiverServiceWokeService;
import sv.utils.soap.zepta.benutzerdaten.USIDMBenutzerdaten;
import sv.utils.soap.zepta.benutzerdaten.USIDMBenutzerdatenPartnertraeger;
import sv.utils.util.ExceptionHandlerAllgemein;

/**
 * allgemeine Erklaerung zu den init*ServicePort-Methoden: jede Methode unterstuetzt folgende Konfigurations-Properties:
 * - <property_praefix>_service_url => Pflicht
 * - <property_praefix>_benutzername => optional
 * - <property_praefix>_passwort => optional
 * - <property_praefix>_schema_validation => optional; ist es nicht gesetzt, wird "true" genommen
 * - <property_praefix>_disable_cn_check => optional; ist es nicht gesetzt, wird "false" genommen
 * - <property_praefix>_truststore_pfad => optional
 * - <property_praefix>_truststore_passwort => optional
 * Der verwendete Wert fuer "<property_praefix>" ist im Kommentar von jeder init-Methode angefuehrt.
 *
 * @author SV-Benutzer
 */
public class LogikSoap {
    private static Logger log = LoggerFactory.getLogger(LogikSoap.class);

    private static final PerformanceLogInInterceptor PERFORMANCE_LOG_IN_INTERCEPTOR = new PerformanceLogInInterceptor();

    private static final PerformanceLogOutInterceptor PERFORMANCE_LOG_OUT_INTERCEPTOR = new PerformanceLogOutInterceptor();

    private static VerstaendigungenServicePort appVerstaendigungenServicePort = null;

    private static ArchivServicePort archivServicePort = null;

    private static BearbeiterverzeichnisServicePort bearbeiterverzeichnisServicePort = null;

    private static LoginExternPortType bearbeiterverzeichnisEbdbServicePort = null;

    private static sv.utils.soap.heidi.LoginExternPortType bearbeiterverzeichnisHeidiServicePort = null;

    private static sv.utils.soap.lw.autorisierung.LoginExternPortType bearbeiterverzeichnisLWServicePort = null;

    private static USIDMBenutzerdatenPartnertraeger bearbeiterverzeichnisZeptaServicePort = null;

    private static SOAPReceiverServiceDsl dslServicePort = null;

    private static SOAPReceiverServiceDika dikaServicePort = null;

    private static SOAPReceiverServiceEko ekoServicePort = null;

    private static SOAPReceiverServicePort faxServicePort = null;

    private static SOAPReceiverServiceFixwerte fixwerteServicePort = null;

    private static SOAPReceiverServiceGesundheitskonto gesundheitskontoServicePort = null;

    private static ProcessExecutionManager infinicaServicePort = null;

    private static SOAPReceiverServiceKanst kanstServicePort = null;

    private static SOAPReceiverServiceKoa koaServicePort = null;

    private static SOAPReceiverServiceFormulare lnFormularServicePort = null;

    private static SOAPReceiverServiceFormulare[] lnFormularServicePortLst = null;

    private static ISvOnlineService lwonlineservicesPort = null;

    private static ISvbUvInfoService svbUvInfoServiceWSPort = null;

    private static SOAPReceiverServiceMassenbewilligung massenbewilligungServicePort = null;

    private static SOAPReceiverServicePati patiServicePort = null;

    private static SOAPReceiverServiceUlei uleiServicePort = null;

    private static PdfErzeugungServicePort pdfErzeugungServicePort = null;

    private static StammdatenServicePort stammdatenServicePort = null;

    private static StandesdatenServicePort standesdatenServicePort = null;

    private static SOAPReceiverServiceSvegda svegdaServicePort = null;

    private static SOAPReceiverServiceSvkai svkaiServicePort = null;

    private static KAIVerguetungPartner svkaiVerguetungpartnerServicePort = null;

    private static TarifkatalogServicePort tarifkatalogServicePort = null;

    private static SOAPReceiverServiceVerrechnung verrechnungServicePort = null;

    private static VertragspartnerServicePort vertragspartnerServicePort = null;

    private static VerstaendigungPortType vertragspartnerLeaServicePort = null;

    private static SOAPReceiverServiceWoke wokeServicePort = null;

    private static SOAPReceiverServiceKai wokeKaiServicePort = null;

    private final ExceptionHandlerAllgemein exceptionHandlerAllgemein;

    private final HelperAllgemein helperAllgemein;

    private final TypConverter typConverter;

    /**
     * Default-Konstruktor
     */
    public LogikSoap() {
        this.exceptionHandlerAllgemein = new ExceptionHandlerAllgemein();
        this.helperAllgemein = new HelperAllgemein();
        this.typConverter = new TypConverter();
    }

    /**
     * initialisiert den AppVerstaendigungenServicePort
     *
     * <property_praefix> = app_verstaendigungen
     */
    public void initAppVerstaendigungenServicePort(final String request) {
        log.info("initAppVerstaendigungenServicePort");
        if (appVerstaendigungenServicePort == null) {
            synchronized (LogikSoap.class) {
                if (appVerstaendigungenServicePort == null) {
                    appVerstaendigungenServicePort = initServicePort(
                            new VerstaendigungenService(null).getVerstaendigungenServicePort(), "app_verstaendigungen",
                            request);
                }
            }
        }
    }

    /**
     * initialisiert den ArchivServicePort
     *
     * <property_praefix> = archiv
     */
    public void initArchivServicePort(final String request) {
        log.info("initArchivServicePort");
        if (archivServicePort == null) {
            synchronized (LogikSoap.class) {
                if (archivServicePort == null) {
                    archivServicePort = initServicePort(new ArchivService(null).getArchivServicePort(), "archiv",
                            request);
                }
            }
        }
    }

    /**
     * initialisiert den BearbeiterverzeichnisServicePort
     *
     * <property_praefix> = bearbeiterverzeichnis
     */
    public void initBearbeiterverzeichnisServicePort(final String request) {
        log.info("initBearbeiterverzeichnisServicePort");
        if (bearbeiterverzeichnisServicePort == null) {
            synchronized (LogikSoap.class) {
                if (bearbeiterverzeichnisServicePort == null) {
                    bearbeiterverzeichnisServicePort = initServicePort(
                            new BearbeiterverzeichnisService(null).getBearbeiterverzeichnis(), "bearbeiterverzeichnis",
                            request);
                }
            }
        }
    }

    /**
     * initialisiert den BearbeiterverzeichnisEbdbServicePort
     *
     * <property_praefix> = bearbeiterverzeichnis_ebdb
     */
    public void initBearbeiterverzeichnisEbdbServicePort(final String request) {
        log.info("initBearbeiterverzeichnisEbdbServicePort");
        if (bearbeiterverzeichnisEbdbServicePort == null) {
            synchronized (LogikSoap.class) {
                if (bearbeiterverzeichnisEbdbServicePort == null) {
                    bearbeiterverzeichnisEbdbServicePort = initServicePort(
                            new LoginExternService(null).getLoginExternPort(), "bearbeiterverzeichnis_ebdb", request);
                }
            }
        }
    }

    /**
     * initialisiert den BearbeiterverzeichnisHeidiServicePort
     *
     * <property_praefix> = bearbeiterverzeichnis_heidi
     */
    public void initBearbeiterverzeichnisHeidiServicePort(final String request) {
        log.info("initBearbeiterverzeichnisHeidiServicePort");
        if (bearbeiterverzeichnisHeidiServicePort == null) {
            synchronized (LogikSoap.class) {
                if (bearbeiterverzeichnisHeidiServicePort == null) {
                    bearbeiterverzeichnisHeidiServicePort = initServicePort(
                            new sv.utils.soap.heidi.LoginExternService(null).getLoginExternPort(),
                            "bearbeiterverzeichnis_heidi", request);
                }
            }
        }
    }

    /**
     * initialisiert den BearbeiterverzeichnisLWServicePort
     *
     * <property_praefix> = bearbeiterverzeichnis_lw
     */
    public void initBearbeiterverzeichnisLWServicePort(final String request) {
        log.info("initBearbeiterverzeichnisLWServicePort");
        if (bearbeiterverzeichnisLWServicePort == null) {
            synchronized (LogikSoap.class) {
                if (bearbeiterverzeichnisLWServicePort == null) {
                    bearbeiterverzeichnisLWServicePort = initServicePort(
                            new sv.utils.soap.lw.autorisierung.LoginExternService(null)
                                    .getLoginExternPort(),
                            "bearbeiterverzeichnis_lw", request);
                }
            }
        }
    }

    /**
     * initialisiert den BearbeiterverzeichnisZeptaServicePort
     *
     * <property_praefix> = bearbeiterverzeichnis_zepta
     */
    public void initBearbeiterverzeichnisZeptaServicePort(final String request) {
        log.info("initBearbeiterverzeichnisZeptaServicePort");
        if (bearbeiterverzeichnisZeptaServicePort == null) {
            synchronized (LogikSoap.class) {
                if (bearbeiterverzeichnisZeptaServicePort == null) {
                    bearbeiterverzeichnisZeptaServicePort = initServicePort(
                            new USIDMBenutzerdaten(null).getUSIDMBenutzerdatenPartnertraegerPort(),
                            "bearbeiterverzeichnis_zepta", request);
                }
            }
        }
    }

    /**
     * initialisiert den DikaServicePort
     *
     * <property_praefix> = dika
     */
    public void initDikaServicePort(final String request) {
        log.info("initDikaServicePort");
        if (dikaServicePort == null) {
            synchronized (LogikSoap.class) {
                if (dikaServicePort == null) {
                    dikaServicePort = initServicePort(
                            new SOAPReceiverServiceDikaService(null).getSOAPReceiverServiceDika(), "dika", request);
                }
            }
        }
    }

    /**
     * initialisiert den DslServicePort
     *
     * <property_praefix> = dsl
     */
    public void initDslServicePort(final String request) {
        log.info("initDslServicePort");
        if (dslServicePort == null) {
            synchronized (LogikSoap.class) {
                if (dslServicePort == null) {
                    dslServicePort = initServicePort(
                            new SOAPReceiverServiceDslService(null).getSOAPReceiverServiceDsl(), "dsl", request);
                }
            }
        }
    }

    /**
     * initialisiert den EkoServicePort
     *
     * <property_praefix> = eko
     */
    public void initEkoServicePort(final String request) {
        log.info("initEkoServicePort");
        if (ekoServicePort == null) {
            synchronized (LogikSoap.class) {
                if (ekoServicePort == null) {
                    ekoServicePort = initServicePort(
                            new SOAPReceiverServiceEkoService(null).getSOAPReceiverServiceEko(), "eko", request);
                }
            }
        }
    }

    /**
     * initialisiert den FaxServicePort
     *
     * <property_praefix> = fax
     */
    public void initFaxServicePort(final String request) {
        log.info("initFaxServicePort");
        if (faxServicePort == null) {
            synchronized (LogikSoap.class) {
                if (faxServicePort == null) {
                    faxServicePort = initServicePort(
                            new SOAPReceiverServicePortImplService(null).getSOAPReceiverServicePortImplPort(), "fax",
                            request);
                }
            }
        }
    }

    /**
     * initialisiert den FixwerteServicePort
     *
     * <property_praefix> = fixwerte
     */
    public void initFixwerteServicePort(final String request) {
        log.info("initFixwerteServicePort");
        if (fixwerteServicePort == null) {
            synchronized (LogikSoap.class) {
                if (fixwerteServicePort == null) {
                    fixwerteServicePort = initServicePort(
                            new SOAPReceiverServiceFixwerteService(null).getSOAPReceiverServiceFixwerte(), "fixwerte",
                            request);
                }
            }
        }
    }

    /**
     * initialisiert den GesundheitskontoServicePort
     *
     * <property_praefix> = gesundheitskonto
     */
    public void initGesundheitskontoServicePort(final String request) {
        log.info("initGesundheitskontoServicePort");
        if (gesundheitskontoServicePort == null) {
            synchronized (LogikSoap.class) {
                if (gesundheitskontoServicePort == null) {
                    gesundheitskontoServicePort = initServicePort(new SOAPReceiverServiceGesundheitskontoService(null)
                            .getSOAPReceiverServiceGesundheitskonto(), "gesundheitskonto", request);
                }
            }
        }
    }

    /**
     * initialisiert den InfinicaServicePort
     *
     * <property_praefix> = infinica
     */
    public void initInfinicaServicePort(final String request) {
        log.info("initInfinicaServicePort");
        if (infinicaServicePort == null) {
            synchronized (LogikSoap.class) {
                if (infinicaServicePort == null) {
                    infinicaServicePort = initServicePort(new ProcessExecutionManagerService(
                            getClass().getClassLoader().getResource(Constants_Wsdl.WSDL_LOCATION_INFINICA))
                                    .getProcessExecutionManagerPort(),
                            "infinica", request);
                    final String benutzername = KonfigurationAllgemein.getProperty("infinica_benutzername");
                    final String passwort = KonfigurationAllgemein.getProperty("infinica_passwort");
                    if ((infinicaServicePort != null) && (!this.helperAllgemein.isEmptyString(benutzername))
                            && (!this.helperAllgemein.isEmptyString((passwort)))) {
                        final Client client = ClientProxy.getClient(infinicaServicePort);
                        if (client != null) {
                            final HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
                            if (httpConduit != null) {
                                final AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
                                authorizationPolicy.setUserName(benutzername);
                                authorizationPolicy.setPassword(passwort);
                                httpConduit.setAuthorization(authorizationPolicy);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * initialisiert den KanstServicePort
     *
     * <property_praefix> = kanst
     */
    public void initKanstServicePort(final String request) {
        log.info("initKanstServicePort");
        if (kanstServicePort == null) {
            synchronized (LogikSoap.class) {
                if (kanstServicePort == null) {
                    kanstServicePort = initServicePort(
                            new SOAPReceiverServiceKanstService(null).getSOAPReceiverServiceKanst(), "kanst", request);
                }
            }
        }
    }

    /**
     * initialisiert den KoaServicePort
     *
     * <property_praefix> = koa
     */
    public void initKoaServicePort(final String request) {
        log.info("initKoaServicePort");
        if (koaServicePort == null) {
            synchronized (LogikSoap.class) {
                if (koaServicePort == null) {
                    koaServicePort = initServicePort(
                            new SOAPReceiverServiceKoaService(null).getSOAPReceiverServiceKoa(), "koa", request);
                }
            }
        }
    }

    /**
     * initialisiert den LnFormularServicePort
     *
     * <property_praefix> = ln_formular
     */
    public void initLnFormularServicePort(final String request) {
        if (lnFormularServicePort == null) {
            synchronized (LogikSoap.class) {
                if (lnFormularServicePort == null) {
                    lnFormularServicePort = initLnFormularServicePort(null, request);
                }
            }
        }
    }

    /**
     * initialisiert den LnFormularServicePort
     *
     * <property_praefix> = ln_formular_<bundesland>
     *
     * unterstuetzte Werte fuer <bundesland>:
     * - bgld
     * - ooe
     * - stmk
     * - ktn
     * - sbg
     * - tir
     * - vbg
     */
    public void initLnFormularServicePortLst(final String request) {
        if (lnFormularServicePortLst == null) {
            synchronized (LogikSoap.class) {
                if (lnFormularServicePortLst == null) {
                    final SOAPReceiverServiceFormulare[] servicePorts = new SOAPReceiverServiceFormulare[10];
                    servicePorts[0] = initLnFormularServicePort(null, request);
                    servicePorts[1] = initLnFormularServicePort(null, request);
                    servicePorts[2] = initLnFormularServicePort(null, request);
                    servicePorts[3] = initLnFormularServicePort("bgld", request);
                    servicePorts[4] = initLnFormularServicePort("ooe", request);
                    servicePorts[5] = initLnFormularServicePort("stmk", request);
                    servicePorts[6] = initLnFormularServicePort("ktn", request);
                    servicePorts[7] = initLnFormularServicePort("sbg", request);
                    servicePorts[8] = initLnFormularServicePort("tir", request);
                    servicePorts[9] = initLnFormularServicePort("vbg", request);
                    lnFormularServicePortLst = servicePorts;
                }
            }
        }
    }

    /**
     * initialisiert den LnFormularServicePort
     *
     * verwendete Konfigurations-Properties:
     * - wenn "bundesland" null ist: <property_praefix> = ln_formular
     * - wenn "bundesland" befuellt ist: <property_praefix> = ln_formular_<bundesland>
     */
    private SOAPReceiverServiceFormulare initLnFormularServicePort(final String bundesland, final String request) {
        log.info("initLnFormularServicePort, bundesland=" + bundesland);
        String appender = "";
        if (!this.helperAllgemein.isEmptyString(bundesland)) {
            appender = "_" + bundesland;
        }
        return (initServicePort(new SOAPReceiverServiceFormulare_Service(null).getSOAPReceiverServiceFormulare(),
                "ln_formular" + appender, request));
    }

    /**
     * initialisiert den LwonlineservicesPort
     *
     * <property_praefix> = lwonlineservices
     */
    public void initLwonlineservicesPort(final String request) {
        log.info("initLwonlineservicesPort");
        if (lwonlineservicesPort == null) {
            synchronized (LogikSoap.class) {
                if (lwonlineservicesPort == null) {
                    lwonlineservicesPort = initServicePort(new SvOnlineServiceWS(
                            getClass().getClassLoader().getResource(Constants_Wsdl.WSDL_LOCATION_LWONLINESERVICES))
                                    .getSvOnlineServiceWSPort(),
                            "lwonlineservices", request);
                    disableTLSCheck(lwonlineservicesPort);
                }
            }
        }
    }

    /**
     * initialisiert den svbUvInfoServiceWSPort
     *
     * <property_praefix> = lw_uv_info
     */
    public void initSvbUvInfoServiceWSPort(final String request) {
        log.info("svbUvInfoServiceWSPort");
        if (svbUvInfoServiceWSPort == null) {
            synchronized (LogikSoap.class) {
                if (svbUvInfoServiceWSPort == null) {
                    svbUvInfoServiceWSPort = initServicePort(new SvbUvInfoServiceWS(
                            getClass().getClassLoader().getResource(Constants_Wsdl.WSDL_LOCATION_UVINFO))
                                    .getSvbUvInfoServiceWSPort(),
                            "lw_uv_info", request);
                    final String benutzername = KonfigurationAllgemein.getProperty("lw_uv_info_benutzername");
                    final String passwort = KonfigurationAllgemein.getProperty("lw_uv_info_passwort");
                    if ((svbUvInfoServiceWSPort != null) && (!this.helperAllgemein.isEmptyString(benutzername))
                            && (!this.helperAllgemein.isEmptyString((passwort)))) {
                        final Client client = ClientProxy.getClient(svbUvInfoServiceWSPort);
                        if (client != null) {
                            final HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
                            if (httpConduit != null) {
                                final AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
                                authorizationPolicy.setUserName(benutzername);
                                authorizationPolicy.setPassword(passwort);
                                httpConduit.setAuthorization(authorizationPolicy);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * initialisiert den MassenbewilligungServicePort
     *
     * <property_praefix> = massenbewilligung
     */
    public void initMassenbewilligungServicePort(final String request) {
        log.info("initMassenbewilligungServicePort");
        if (massenbewilligungServicePort == null) {
            synchronized (LogikSoap.class) {
                if (massenbewilligungServicePort == null) {
                    massenbewilligungServicePort = initServicePort(new SOAPReceiverServiceMassenbewilligungService(null)
                            .getSOAPReceiverServiceMassenbewilligung(), "massenbewilligung", request);
                }
            }
        }
    }

    /**
     * initialisiert den PatiServicePort
     *
     * <property_praefix> = pati
     */
    public void initPatiServicePort(final String request) {
        log.info("initPatiServicePort");
        if (patiServicePort == null) {
            synchronized (LogikSoap.class) {
                if (patiServicePort == null) {
                    patiServicePort = initServicePort(
                            new SOAPReceiverServicePatiService(null).getSOAPReceiverServicePati(), "pati", request);
                }
            }
        }
    }

    /**
     * initialisiert den UleiServicePort
     *
     * <property_praefix> = ulei
     */
    public void initUleiServicePort(final String request) {
        log.info("initUleiServicePort");
        if (uleiServicePort == null) {
            synchronized (LogikSoap.class) {
                if (uleiServicePort == null) {
                    uleiServicePort = initServicePort(
                            new SOAPReceiverServiceUleiService(null).getSOAPReceiverServiceUlei(), "ulei", request);
                }
            }
        }
    }

    /**
     * initialisiert den PdfErzeugungServicePort
     *
     * <property_praefix> = pdferzeugung
     */
    public void initPdfErzeugungServicePort(final String request) {
        log.info("initPdfErzeugungServicePort");
        if (pdfErzeugungServicePort == null) {
            synchronized (LogikSoap.class) {
                if (pdfErzeugungServicePort == null) {
                    pdfErzeugungServicePort = initServicePort(new PdfErzeugungService(null).getPdfErzeugung(),
                            "pdferzeugung", request);
                }
            }
        }
    }

    /**
     * initialisiert den StammdatenServicePort
     *
     * <property_praefix> = stammdaten
     */
    public void initStammdatenServicePort(final String request) {
        initStammdatenServicePort(null, request);
    }

    /**
     * initialisiert den StammdatenServicePort
     *
     * <property_praefix> = stammdaten
     */
    public void initStammdatenServicePort(final URL wsdlLocation, final String request) {
        log.info("initStammdatenServicePort");
        if (stammdatenServicePort == null) {
            synchronized (LogikSoap.class) {
                if (stammdatenServicePort == null) {
                    stammdatenServicePort = initServicePort(
                            new StammdatenService(wsdlLocation).getStammdatenServicePort(), "stammdaten", request);
                }
            }
        }
    }

    /**
     * initialisiert den StandesdatenServicePort
     *
     * <property_praefix> = standesdaten
     */
    public void initStandesdatenServicePort(final String request) {
        log.info("initStandesdatenServicePort");
        if (standesdatenServicePort == null) {
            synchronized (LogikSoap.class) {
                if (standesdatenServicePort == null) {
                    standesdatenServicePort = initServicePort(
                            new StandesdatenService(null).getStandesdatenServicePort(), "standesdaten", request);
                }
            }
        }
    }

    /**
     * initialisiert den SvegdaServicePort
     *
     * <property_praefix> = svegda
     */
    public void initSvegdaServicePort(final String request) {
        log.info("initSvegdaServicePort");
        if (svegdaServicePort == null) {
            synchronized (LogikSoap.class) {
                if (svegdaServicePort == null) {
                    svegdaServicePort = initServicePort(
                            new SOAPReceiverServiceSvegdaService(null).getSOAPReceiverServiceSvegda(), "svegda",
                            request);
                }
            }
        }
    }

    /**
     * initialisiert den SVKAIServicePort
     *
     * <property_praefix> = svkai
     */
    public void initSvkaiServicePort(final String request) {
        log.info("initSvkaiServicePort");
        if (svkaiServicePort == null) {
            synchronized (LogikSoap.class) {
                if (svkaiServicePort == null) {
                    svkaiServicePort = initServicePort(
                            new SOAPReceiverServiceSvkaiService(null).getSOAPReceiverServiceSvkai(), "svkai",
                            request);
                }
            }
        }
    }

    /**
     * initialisiert den SvkaiVerguetungpartnerServicePort
     *
     * <property_praefix> = svkai_verguetungpartner
     */
    public void initSvkaiVerguetungpartnerServicePort(final String request) {
        log.info("initSvkaiVerguetungpartnerServicePort");
        if (svkaiVerguetungpartnerServicePort == null) {
            synchronized (LogikSoap.class) {
                if (svkaiVerguetungpartnerServicePort == null) {
                    svkaiVerguetungpartnerServicePort = initServicePort(
                            new KAIVerguetung(null).getKAIVerguetungPartnerPort(), "svkai_verguetungpartner", request);
                }
            }
        }
    }

    /**
     * initialisiert den TarifkatalogServicePort
     *
     * <property_praefix> = tarifkatalog
     */
    public void initTarifkatalogServicePort(final String request) {
        log.info("initTarifkatalogServicePort");
        if (tarifkatalogServicePort == null) {
            synchronized (LogikSoap.class) {
                if (tarifkatalogServicePort == null) {
                    tarifkatalogServicePort = initServicePort(new TarifkatalogService(null).getTarifkatalog(),
                            "tarifkatalog", request);
                }
            }
        }
    }

    /**
     * initialisiert den VerrechnungServicePort
     *
     * <property_praefix> = verrechnung
     */
    public void initVerrechnungServicePort(final String request) {
        log.info("initVerrechnungServicePort");
        if (verrechnungServicePort == null) {
            synchronized (LogikSoap.class) {
                if (verrechnungServicePort == null) {
                    verrechnungServicePort = initServicePort(
                            new SOAPReceiverServiceVerrechnungService(null).getSOAPReceiverServiceVerrechnung(),
                            "verrechnung", request);
                }
            }
        }
    }

    /**
     * initialisiert den VertragspartnerServicePort
     *
     * <property_praefix> = vertragspartner
     */
    public void initVertragspartnerServicePort(final String request) {
        log.info("initVertragspartnerServicePort");
        if (vertragspartnerServicePort == null) {
            synchronized (LogikSoap.class) {
                if (vertragspartnerServicePort == null) {
                    vertragspartnerServicePort = initServicePort(new VertragspartnerService(null).getVertragspartner(),
                            "vertragspartner", request);
                }
            }
        }
    }

    /**
     * initialisiert den VertragspartnerLeaServicePort
     *
     * <property_praefix> = vertragspartner_lea
     */
    public void initVertragspartnerLeaServicePort(final String request) {
        log.info("initVertragspartnerLeaServicePort");
        if (vertragspartnerLeaServicePort == null) {
            synchronized (LogikSoap.class) {
                if (vertragspartnerLeaServicePort == null) {
                    vertragspartnerLeaServicePort = initServicePort(
                            new Verstaendigung(null).getVerstaendigungHttpPort(), "vertragspartner_lea", request);
                }
            }
        }
    }

    /**
     * initialisiert den WokeServicePort
     *
     * <property_praefix> = woke
     */
    public void initWokeServicePort(final String request) {
        log.info("initWokeServicePort");
        if (wokeServicePort == null) {
            synchronized (LogikSoap.class) {
                if (wokeServicePort == null) {
                    wokeServicePort = initServicePort(
                            new SOAPReceiverServiceWokeService(null).getSOAPReceiverServiceWoke(), "woke", request);
                }
            }
        }
    }

    /**
     * initialisiert den WokeKaiServicePort
     *
     * <property_praefix> = woke_kai
     */
    public void initWokeKaiServicePort(final String request) {
        log.info("initWokeKaiServicePort");
        if (wokeKaiServicePort == null) {
            synchronized (LogikSoap.class) {
                if (wokeKaiServicePort == null) {
                    wokeKaiServicePort = initServicePort(
                            new SOAPReceiverServiceKaiService(null).getSOAPReceiverServiceKai(), "woke_kai", request);
                }
            }
        }
    }

    /**
     * @return the appVerstaendigungenServicePort
     */
    public static VerstaendigungenServicePort getAppVerstaendigungenServicePort() {
        return (appVerstaendigungenServicePort);
    }

    /**
     * @return the archivServicePort
     */
    public static ArchivServicePort getArchivServicePort() {
        return (archivServicePort);
    }

    /**
     * @return the bearbeiterverzeichnisServicePort
     */
    public static BearbeiterverzeichnisServicePort getBearbeiterverzeichnisServicePort() {
        return (bearbeiterverzeichnisServicePort);
    }

    /**
     * @return the bearbeiterverzeichnisEbdbServicePort
     */
    public static LoginExternPortType getBearbeiterverzeichnisEbdbServicePort() {
        return (bearbeiterverzeichnisEbdbServicePort);
    }

    /**
     * @return the bearbeiterverzeichnisHeidiServicePort
     */
    public static sv.utils.soap.heidi.LoginExternPortType getBearbeiterverzeichnisHeidiServicePort() {
        return (bearbeiterverzeichnisHeidiServicePort);
    }

    /**
     * @return the bearbeiterverzeichnisLWServicePort
     */
    public static sv.utils.soap.lw.autorisierung.LoginExternPortType getBearbeiterverzeichnisLWServicePort() {
        return (bearbeiterverzeichnisLWServicePort);
    }

    /**
     * @return the bearbeiterverzeichnisZeptaServicePort
     */
    public static USIDMBenutzerdatenPartnertraeger getBearbeiterverzeichnisZeptaServicePort() {
        return (bearbeiterverzeichnisZeptaServicePort);
    }

    /**
     * @return the dslServicePort
     */
    public static SOAPReceiverServiceDsl getDslServicePort() {
        return (dslServicePort);
    }

    /**
     * @return the dikaServicePort
     */
    public static SOAPReceiverServiceDika getDikaServicePort() {
        return (dikaServicePort);
    }

    /**
     * @return the ekoServicePort
     */
    public static SOAPReceiverServiceEko getEkoServicePort() {
        return (ekoServicePort);
    }

    /**
     * @return the faxServicePort
     */
    public static SOAPReceiverServicePort getFaxServicePort() {
        return (faxServicePort);
    }

    /**
     * @return the fixwerteServicePort
     */
    public static SOAPReceiverServiceFixwerte getFixwerteServicePort() {
        return (fixwerteServicePort);
    }

    /**
     * @return the gesundheitskontoServicePort
     */
    public static SOAPReceiverServiceGesundheitskonto getGesundheitskontoServicePort() {
        return (gesundheitskontoServicePort);
    }

    /**
     * @return the infinicaServicePort
     */
    public static ProcessExecutionManager getInfinicaServicePort() {
        return (infinicaServicePort);
    }

    /**
     * @return the kanstServicePort
     */
    public static SOAPReceiverServiceKanst getKanstServicePort() {
        return (kanstServicePort);
    }

    /**
     * @return the koaServicePort
     */
    public static SOAPReceiverServiceKoa getKoaServicePort() {
        return (koaServicePort);
    }

    /**
     * @return the lnFormularServicePort
     */
    public static SOAPReceiverServiceFormulare getLnFormularServicePort() {
        return (lnFormularServicePort);
    }

    /**
     * @return the lnFormularServicePortLst
     */
    public static SOAPReceiverServiceFormulare[] getLnFormularServicePortLst() {
        return (lnFormularServicePortLst);
    }

    /**
     * @return the lnFormularServicePortLst
     */
    public static SOAPReceiverServiceFormulare getLnFormularServicePortLst(int landesstelle) {
        if (landesstelle > 9) {
            landesstelle = landesstelle % 10;
        }
        log.debug("suche LN-ServicePort fuer LST " + landesstelle + "...");
        return (lnFormularServicePortLst[landesstelle]);
    }

    /**
     * @return the lwonlineservicesPort
     */
    public static ISvOnlineService getLwonlineservicesPort() {
        return (lwonlineservicesPort);
    }

    /**
     * @return the massenbewilligungServicePort
     */
    public static SOAPReceiverServiceMassenbewilligung getMassenbewilligungServicePort() {
        return (massenbewilligungServicePort);
    }

    /**
     * @return the patiServicePort
     */
    public static SOAPReceiverServicePati getPatiServicePort() {
        return (patiServicePort);
    }

    /**
     * @return the uleiServicePort
     */
    public static SOAPReceiverServiceUlei getUleiServicePort() {
        return (uleiServicePort);
    }

    /**
     * @return the pdfErzeugungServicePort
     */
    public static PdfErzeugungServicePort getPdfErzeugungServicePort() {
        return (pdfErzeugungServicePort);
    }

    /**
     * @return the stammdatenServicePort
     */
    public static StammdatenServicePort getStammdatenServicePort() {
        return (stammdatenServicePort);
    }

    /**
     * @return the standesdatenServicePort
     */
    public static StandesdatenServicePort getStandesdatenServicePort() {
        return (standesdatenServicePort);
    }

    /**
     * @return the svegdaServicePort
     */
    public static SOAPReceiverServiceSvegda getSvegdaServicePort() {
        return (svegdaServicePort);
    }

    /**
     * @return the SvkaiServicePort
     */
    public static SOAPReceiverServiceSvkai getSvkaiServicePort() {
        return (svkaiServicePort);
    }

    /**
     * @return the SvkaiVerguetungpartnerServicePort
     */
    public static KAIVerguetungPartner getSvkaiVerguetungpartnerServicePort() {
        return (SvaiVerguetungpartnerServicePort);
    }

    /**
     * @return the svbUvInfoServiceWSPort
     */
    public static ISvbUvInfoService getISvbUvInfoServicePort() {
        return (svbUvInfoServiceWSPort);
    }

    /**
     * @return the tarifkatalogServicePort
     */
    public static TarifkatalogServicePort getTarifkatalogServicePort() {
        return (tarifkatalogServicePort);
    }

    /**
     * @return the verrechnungServicePort
     */
    public static SOAPReceiverServiceVerrechnung getVerrechnungServicePort() {
        return (verrechnungServicePort);
    }

    /**
     * @return the vertragspartnerServicePort
     */
    public static VertragspartnerServicePort getVertragspartnerServicePort() {
        return (vertragspartnerServicePort);
    }

    /**
     * @return the vertragspartnerLeaServicePort
     */
    public static VerstaendigungPortType getVertragspartnerLeaServicePort() {
        return (vertragspartnerLeaServicePort);
    }

    /**
     * @return the wokeServicePort
     */
    public static SOAPReceiverServiceWoke getWokeServicePort() {
        return (wokeServicePort);
    }

    /**
     * @return the wokeKaiServicePort
     */
    public static SOAPReceiverServiceKai getWokeKaiServicePort() {
        return (wokeKaiServicePort);
    }

    /**
     * Erzeugt aus dem uebergebenen <code>Bearbeiter</code> einen
     * <code>SoapBearbeiter</code> fuer einen WebService-Aufruf
     *
     * @return SoapBearbeiter
     */
    private SoapBearbeiter getSoapBearbeiter(final Bearbeiter bearbeiter) {
        SoapBearbeiter soapBearbeiter = null;
        if (bearbeiter != null) {
            soapBearbeiter = new SoapBearbeiter();
            soapBearbeiter.setVSNR(this.typConverter.getNotNullString(bearbeiter.getVSNR()));
            soapBearbeiter.setVorname(this.typConverter.getNotNullString(bearbeiter.getVorname()));
            soapBearbeiter.setZuname(this.typConverter.getNotNullString(bearbeiter.getZuname()));
            soapBearbeiter.setOrganisationseinheit(this.typConverter.getNullString(bearbeiter.getAbteilung()));
            String bundesland = "0";
            final String landesstelle = this.typConverter.getNotNullString(bearbeiter.getLandesstellenCode());
            if (landesstelle.length() == 2) {
                bundesland = landesstelle.substring(1);
            } else if (landesstelle.length() == 1) {
                bundesland = landesstelle;
            }
            soapBearbeiter.setBundesland(this.typConverter.getNullInteger(bundesland));
            String traegerCode = "40";
            final String benutzername = this.typConverter.getNullString(bearbeiter.getBenutzername());
            if ((benutzername != null) && (benutzername.length() >= 2)) {
                final String praefix = benutzername.substring(0, 2);
                if (this.helperAllgemein.isStringNumerisch(praefix)) {
                    traegerCode = praefix;
                    log.debug("gefundener Traegercode: " + praefix);
                }
            }
            soapBearbeiter.setBenutzername(benutzername);
            soapBearbeiter.setTraeger(traegerCode);
        }
        return (soapBearbeiter);
    }

    /**
     * Erzeugt aus den uebergebenen Daten eine <code>AnfragendeStelle</code>
     * fuer einen WebService-Aufruf
     *
     * @param clientIP
     *            request.getRemoteAddr()
     * @param bearbeiter
     *            XOR zu vertreter, versicherter und anonym
     * @param vertreter
     *            XOR zu bearbeiter, versicherter und anonym
     * @param versicherter
     *            XOR zu bearbeiter, vertreter und anonym
     * @param versicherterVsnr
     *            darf nur uebergeben werden, wenn gleichzeitig auch ein
     *            bearbeiter oder ein vertreter uebergeben wird
     *
     * @return AnfragendeStelle
     */
    public AnfragendeStelle getAnfragendeStelle(final Bearbeiter bearbeiter, final SoapPerson vertreter,
            final String versicherterVsnr, final SoapPerson versicherter, final Boolean anonym, final String clientIP,
            final Integer sicherheitsklasse) {
        final AnfragendeStelle anfragendeStelle = new AnfragendeStelle();
        anfragendeStelle.setBearbeiter(getSoapBearbeiter(bearbeiter));
        anfragendeStelle.setVertreter(vertreter);
        anfragendeStelle.setVersicherterVSNR(this.typConverter.getNullString(versicherterVsnr));
        anfragendeStelle.setVersicherter(versicherter);
        anfragendeStelle.setAnonym(anonym);
        anfragendeStelle.setClientIP(this.typConverter.getNullString(clientIP));
        anfragendeStelle.setServerIP(this.typConverter.getNotNullString(KonfigurationAllgemein.getServerIP()));
        anfragendeStelle.setEreignisKennung(this.typConverter.getNotNullString(getEreigniskennung()));
        anfragendeStelle.setSicherheitsklasse(sicherheitsklasse);
        return (anfragendeStelle);
    }

    private String getEreigniskennung() {
        String string = this.typConverter.getNotNullString(KonfigurationAllgemein.getAnwendung() + "_"
                + UUID.randomUUID() + "_" + String.valueOf(System.currentTimeMillis()));
        final int laenge = string.length();
        if (laenge > 95) {
            string = string.substring(laenge - 95);
        }
        return (string);
    }

    /**
     * erzeugt eine mit den uebergebenen Werten befuellte
     * <code>SoapAnfrage</code>, als "Anwendung" wird dabei der in
     * <code>KonfigurationAllgemein.getAnwendung()</code> gespeicherte Wert
     * genommen, fuer Benutzername/Passwort werden in
     * <code>KonfigurationAllgemein</code> die Properties "technischer_user_name"
     * bzw. "technischer_user_passwort" gesucht
     */
    public <T extends SoapAnfrage> T initSoapAnfrage(final T soapAnfrage, final AnfragendeStelle anfragendeStelle,
            final String request) {
        if (soapAnfrage != null) {
            soapAnfrage
                    .setAnwendung(this.typConverter.getNotNullString(KonfigurationAllgemein.getAnwendung()));
            soapAnfrage.setBenutzername(this.typConverter
                    .getNotNullString(KonfigurationAllgemein.getProperty("technischer_user_name")));
            soapAnfrage.setPasswort(this.typConverter
                    .getNotNullString(KonfigurationAllgemein.getProperty("technischer_user_passwort")));
            soapAnfrage.setAnfragendeStelle(anfragendeStelle);
        }
        if (!this.helperAllgemein.istSystemmodusOnline()) {
            log.info(this.exceptionHandlerAllgemein.getErrorMeldung(
                    new Exception(
                            "Achtung: Webservice wird nicht im Online-Modus verwendet! soapAnfrage = " + soapAnfrage),
                    false, request));
        }
        return (soapAnfrage);
    }

    /**
     * setzt verschiedene wichtige Standard-Einstellungen fuer Webservice-Clients
     */
    public <T> T initServicePort(final T port, final String property_praefix, final String request) {
        log.info("initServicePort port=" + port + ", property_praefix=" + property_praefix);
        return (initServicePort(port,
                this.typConverter
                        .getNullString(KonfigurationAllgemein.getProperty(property_praefix + "_service_url")),
                this.typConverter
                        .getNullString(KonfigurationAllgemein.getProperty(property_praefix + "_benutzername")),
                this.typConverter
                        .getNullString(KonfigurationAllgemein.getProperty(property_praefix + "_passwort")),
                getBooleanProperty(property_praefix + "_schema_validation", true),
                getBooleanProperty(property_praefix + "_disable_cn_check", false),
                this.typConverter
                        .getNullString(KonfigurationAllgemein.getProperty(property_praefix + "_truststore_pfad")),
                this.typConverter.getNullString(
                        KonfigurationAllgemein.getProperty(property_praefix + "_truststore_passwort")),
                request));
    }

    /**
     * setzt verschiedene wichtige Standard-Einstellungen fuer Webservice-Clients
     *
     * ACHTUNG: nur zu verwenden, wenn die Nutzung von <code>initServicePort(T, String, boolean, String)</code> nicht
     * moeglich ist!
     */
    public <T> T initServicePort(T port, final String serviceUrl, final String benutzername, final String passwort,
            final boolean schemaValidationEnabled, final boolean disableCnCheck, final String trustStorePfad,
            final String trustStorePasswort, final String request) {
        log.info("initServicePort port=" + port + ", port.getClass()=" + (port != null ? port.getClass() : "null")
                + ", serviceUrl=" + serviceUrl + ", benutzername=" + benutzername + ", passwort="
                + ((passwort != null) ? String.valueOf(passwort.length()) : "null") + ", schemaValidationEnabled="
                + schemaValidationEnabled + ", disableCnCheck=" + disableCnCheck);
        port = setzeServiceUrl(port, serviceUrl, request);
        port = setzeBenutzerdaten(port, benutzername, passwort);
        port = setzeTimeouts(port, request);
        port = installPerformanceInterceptors(port);
        port = installSchemaValidation(port, schemaValidationEnabled);
        if (disableCnCheck) {
            port = disableCnCheck(port);
        }
        port = setzeTrustStore(port, trustStorePfad, trustStorePasswort, request);
        return (port);
    }

    /**
     * setzt die Service-URL auf den fuer diese Umgebung gueltigen Wert
     */
    private <T> T setzeServiceUrl(final T port, final String serviceUrl, final String request) {
        log.debug("setzeServiceUrl port=" + port + ", serviceUrl=" + serviceUrl);
        if (port != null) {
            if (!this.helperAllgemein.isEmptyString(serviceUrl)) {
                ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceUrl);
            } else {
                log.error(this.exceptionHandlerAllgemein.getErrorMeldung(
                        new Exception(
                                "In der Konfiguration ist keine URL fuer den gewuenschten ServicePort eingetragen!"),
                        request));
            }
        }
        return (port);
    }

    /**
     * setzt die fuer diese Umgebung gueltigen Benutzerdaten
     */
    private <T> T setzeBenutzerdaten(final T port, final String benutzername, final String passwort) {
        log.debug("setzeBenutzerdaten port=" + port + ", benutzername=" + benutzername + ", passwort="
                + ((passwort != null) ? String.valueOf(passwort.length()) : "null"));
        if (port != null) {
            if (!this.helperAllgemein.isEmptyString(benutzername)) {
                ((BindingProvider) port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, benutzername);
                ((BindingProvider) port).getRequestContext().put("ws-security.username", benutzername);
            }
            if (!this.helperAllgemein.isEmptyString(passwort)) {
                ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, passwort);
                ((BindingProvider) port).getRequestContext().put("ws-security.password", passwort);
            }
        }
        return (port);
    }

    /**
     * setzt die Verbindungstimeouts in Millisekunden!
     */
    private <T> T setzeTimeouts(final T port, final String request) {
        log.debug("setzeTimeouts port=" + port);
        if (port != null) {
            final Client client = ClientProxy.getClient(port);
            if (client != null) {
                final HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
                if (httpConduit != null) {
                    final HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
                    // Default: 30 Sekunden
                    httpClientPolicy.setConnectionTimeout(Constants_Allgemein._30_SEKUNDEN);
                    // Default: 60 Sekunden
                    httpClientPolicy.setReceiveTimeout(Constants_Allgemein._1_MINUTE);
                    // if (!allowChunking) {
                    // httpClientPolicy.setAllowChunking(false);
                    // }
                    httpConduit.setClient(httpClientPolicy);
                } else {
                    log.error(this.exceptionHandlerAllgemein.getErrorMeldung(new Exception("HTTPConduit ist null!"),
                            request));
                }
            } else {
                log.error(this.exceptionHandlerAllgemein.getErrorMeldung(new Exception("Client ist null!"), request));
            }
        }
        return (port);
    }

    /**
     * aktiviert Performancemessungen
     */
    private <T> T installPerformanceInterceptors(final T port) {
        log.debug("installPerformanceInterceptors port=" + port);
        if (port != null) {
            final Client client = ClientProxy.getClient(port);
            if (client != null) {
                client.getInFaultInterceptors().add(PERFORMANCE_LOG_IN_INTERCEPTOR);
                client.getInInterceptors().add(PERFORMANCE_LOG_IN_INTERCEPTOR);
                client.getOutInterceptors().add(PERFORMANCE_LOG_OUT_INTERCEPTOR);
                client.getOutFaultInterceptors().add(PERFORMANCE_LOG_OUT_INTERCEPTOR);
            }
        }
        return (port);
    }

    private <T> T installSchemaValidation(final T port, final boolean schemaValidationEnabled) {
        log.debug("installSchemaValidation port=" + port + ", schemaValidationEnabled=" + schemaValidationEnabled);
        if (port != null) {
            ((BindingProvider) port).getRequestContext().put("schema-validation-enabled",
                    String.valueOf(schemaValidationEnabled));
            ((BindingProvider) port).getRequestContext().put("jaxb-validation-event-handler",
                    new UnexpectedElementEventHandler());
        }
        return (port);
    }

    /**
     * Behebt bei ServicePorts, die das benoetigen, die Fehlermeldung "Marshalling Error: The https URL hostname
     * does not match the Common Name (CN) on the server certificate <...>"
     */
    private <T> T disableCnCheck(final T port) {
        log.debug("disableCnCheck port=" + port);
        if (port != null) {
            final Client client = ClientProxy.getClient(port);
            if (client != null) {
                final HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
                if (httpConduit != null) {
                    TLSClientParameters tlsClientParameters = httpConduit.getTlsClientParameters();
                    if (tlsClientParameters == null) {
                        tlsClientParameters = new TLSClientParameters();
                    }
                    tlsClientParameters.setDisableCNCheck(true);
                    httpConduit.setTlsClientParameters(tlsClientParameters);
                }
            }
        }
        return (port);
    }

    /**
     * Behebt bei ServicePorts, die das benoetigen, die Fehlermeldung
     * "sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested
     * target"
     */
    private <T> T disableTLSCheck(final T port) {
        log.debug("disableTLSCheck port=" + port);
        if (port != null) {
            final Client client = ClientProxy.getClient(port);
            if (client != null) {
                final HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
                if (httpConduit != null) {
                    TLSClientParameters params = httpConduit.getTlsClientParameters();
                    if (params == null) {
                        params = new TLSClientParameters();
                        httpConduit.setTlsClientParameters(params);
                    }
                    params.setTrustManagers(new TrustManager[] { new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(final X509Certificate[] chain, final String authType)
                                throws CertificateException {
                            //
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            {
                                return new X509Certificate[] {};
                            }
                        }

                        @Override
                        public void checkServerTrusted(final X509Certificate[] chain, final String authType)
                                throws CertificateException {
                            //
                        }
                    } });
                    params.setDisableCNCheck(true);
                }
                client.getRequestContext().put("use.async.http.conduit", Boolean.TRUE);
            }
        }
        return (port);
    }

    /**
     * traegt Informationen zum Zertifikat beim Service ein
     */
    private <T> T setzeTrustStore(final T port, final String pfad, final String passwort, final String request) {
        try {
            log.debug("setzeTrustStore pfad=" + pfad + ", passwort="
                    + ((passwort != null) ? String.valueOf(passwort.length()) : "null"));
            if (port != null) {
                if ((!this.helperAllgemein.isEmptyString(pfad)) && (passwort != null)
                        && (!this.helperAllgemein.isEmptyString(passwort))) {
                    final Client client = ClientProxy.getClient(port);
                    if (client != null) {
                        final HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
                        if (httpConduit != null) {
                            final char[] passwortChars = passwort.toCharArray();
                            final TLSClientParameters tlsClientParameters = new TLSClientParameters();
                            final KeyStore keyStore = KeyStore.getInstance("JKS");
                            if (keyStore != null) {
                                try (final FileInputStream stream = new FileInputStream(pfad)) {
                                    keyStore.load(stream, passwortChars);
                                    final SSLSocketFactory sslSocketFactory = createSSLContext(keyStore, passwort,
                                            keyStore).getSocketFactory();
                                    tlsClientParameters.setSSLSocketFactory(sslSocketFactory);
                                    httpConduit.setTlsClientParameters(tlsClientParameters);
                                }
                            } else {
                                log.warn("keyStore ist null!");
                            }
                        } else {
                            log.warn("httpConduit ist null!");
                        }
                    } else {
                        log.warn("client ist null!");
                    }
                }
            }
        } catch (final Exception e) {
            log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
        return (port);
    }

    private SSLContext createSSLContext(final KeyStore keyStore, final String passwort, final KeyStore trustStore)
            throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, KeyManagementException {
        final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);
        final KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, passwort.toCharArray());
        final SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
        return sslContext;
    }

    /**
     * @return HttpServletRequest fuer einen CXF-Webservice
     */
    public HttpServletRequest getHttpServletRequestFromCxf() {
        HttpServletRequest request = null;
        try {
            final Message message = PhaseInterceptorChain.getCurrentMessage();
            if (message != null) {
                request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
            }
        } catch (final Exception e) {
            log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, null));
        }
        return (request);
    }

    /**
     * Sucht den Wert zum uebergebenen Property in der Konfiguration und wandelt diesen in einen boolean um. Gibt es in
     * der Konfiuration keinen Wert, so wird der default_wert zurueckgegeben.
     */
    private boolean getBooleanProperty(final String property, final boolean default_wert) {
        log.debug("getBooleanProperty property=" + property + ", default_wert=" + default_wert);
        boolean rueckgabe = default_wert;
        if (!this.helperAllgemein.isEmptyString(property)) {
            final String wert = KonfigurationAllgemein.getProperty(property);
            log.debug(
                    "getBooleanProperty property=" + property + ", default_wert=" + default_wert + ", wert = " + wert);
            if (!this.helperAllgemein.isEmptyString(wert)) {
                rueckgabe = Boolean.parseBoolean(wert);
            }
        }
        return (rueckgabe);
    }

    /**
     * sucht in der uebergebenen AnfragendeStelle nach der Versicherten-VSNR (Element Versicherter.VSNR oder
     * ElementVersicherterVSNR)
     *
     * @return gefundene VSNR oder null, falls keine Versicherten-VSNR gesetzt ist
     */
    public String getVersicherterVsnr(final AnfragendeStelle anfragendeStelle) {
        String vsnr = null;
        if (anfragendeStelle != null) {
            final SoapPerson versicherter = anfragendeStelle.getVersicherter();
            if (versicherter != null) {
                vsnr = versicherter.getVSNR();
            }
            if (this.helperAllgemein.isEmptyString(vsnr)) {
                vsnr = anfragendeStelle.getVersicherterVSNR();
            }
        }
        return (this.typConverter.getNullString(vsnr));
    }
}
