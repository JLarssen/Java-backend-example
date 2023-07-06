package sv.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.enterprise.context.Conversation;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.cli.CommandLine;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.security.SecurityContext;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.ServletContextResource;

import constants.Constants_Allgemein;
import constants.Constants_Fixwerte;
import constants.Constants_Leistungspfade;
import sv.utils.beans.KonfigurationAllgemein;
import sv.utils.beans.KonfigurationBasic;
import sv.utils.beans.xml.XMLIterator;
import sv.utils.soap.beve.Bearbeiter;
import sv.utils.soap.tarifkatalog.typen.MgkLive;

/**
 * @author SV-Benutzer
 */
public class Helper extends HelperAllgemein {
    private static Helper helper = new Helper();

    /**
     * Default-Konstruktor
     */
    public Helper() {
        super();
    }

    /**
     * sucht die uebergebene Datei im Context und gibt einen InputStream darauf
     * zurueck, falls sie gefunden werden kann
     */
    public InputStream getResourceAsStream(final String dateiname, final String request) {
        InputStream is = null;
        try {
            is = getResourceAsStream(dateiname, new ConfigurationParser().getProperty("classes", request),
                    KonfigurationAllgemein.getContext());
        } catch (final Exception e) {
            this.log.error(new ExceptionHandler().getErrorMeldung(e, request));
        }
        return (is);
    }

    /**
     * gibt einen InputStream auf den uebergebenen Dateinamen zurueck
     *
     * @throws IOException
     *             wird geworfen, wenn die gewuenschte Datei nicht existiert
     */
    public InputStream getResourceAsStream(final String dateiname, final String datei_praefix,
            final ServletContext context) throws IOException {
        InputStream is = null;
        final Resource resource = getResource(dateiname, datei_praefix, context);
        if (resource != null) {
            is = new UnicodeInputStream(resource.getInputStream(), Constants_Allgemein.ENCODING_FILE);
        }
        return (is);
    }

    /**
     * gibt einen InputStream auf den uebergebenen Dateinamen zurueck, versucht
     * keine Encoding-Konvertierung
     *
     * @throws IOException
     *             wird geworfen, wenn die gewuenschte Datei nicht existiert
     */
    public InputStream getResourceAsStreamOriginal(final String dateiname, final String datei_praefix,
            final ServletContext context) throws IOException {
        InputStream is = null;
        final Resource resource = getResource(dateiname, datei_praefix, context);
        if (resource != null) {
            is = resource.getInputStream();
        }
        return (is);
    }

    /**
     * gibt ein Resource-Objekt zum uebergebenen Dateinamen zurueck
     */
    private Resource getResource(String dateiname, final String datei_praefix, final ServletContext context) {
        Resource resource = null;
        if (!isEmptyString(dateiname)) {
            dateiname = dateiname.trim();
            if (context != null) {
                dateiname = datei_praefix + dateiname;
                this.log.debug("lade Datei '" + dateiname + "'" + " aus ServletContext '" + context + "'");
                resource = new ServletContextResource(context, dateiname);
            } else {
                this.log.debug("lade Datei '" + dateiname + "'" + " aus Classpath");
                resource = new ClassPathResource(dateiname);
            }
            this.log.debug("Resource: " + resource);
        }
        return (resource);
    }

    /**
     * Retourniert den HttpServletRequest einer JSF-Anfrage.
     *
     * @param facesContext
     *            FacesContext einer JSF-Anfrage
     * @return HttpServletRequest
     */
    public HttpServletRequest getHttpServletRequest(final FacesContext facesContext) {
        HttpServletRequest request = null;
        final ExceptionHandler exceptionHandler = new ExceptionHandler();
        try {
            if (facesContext != null) {
                final ExternalContext externalContext = facesContext.getExternalContext();
                if (externalContext != null) {
                    request = (HttpServletRequest) externalContext.getRequest();
                }
            }
        } catch (final Exception e) {
            this.log.error(exceptionHandler.getErrorMeldung(e, exceptionHandler.getRequestString(request)));
        }
        return (request);
    }

    /**
     * Erstelle zu der uebergebenen Datei einen XmlIterator. Pruefe die
     * Dateigroesse > 0.
     *
     * @param ordner
     *            Verzeichnis
     * @param datei
     *            datei
     * @param clazz
     *            Classe
     * @return XmlIterator oder NULL
     */
    public <T> XMLIterator<T> getXmlIteratorZuDatei(final String ordner, final String datei, final Class<T> clazz,
            final String request) {
        XMLIterator<T> xmlIterator = null;
        if ((clazz != null) && !isEmptyString(ordner) && !isEmptyString(datei)) {
            try {
                final String pfadMitDatei = fuegePfadSeperatorAnPfadAn(ordner).concat(datei);
                if (!isEmptyString(pfadMitDatei)) {
                    final File file = new File(pfadMitDatei);
                    if ((file.exists()) && (file.length() > 0)) {
                        xmlIterator = new XMLIterator<>(pfadMitDatei, clazz, request);
                    } else {
                        this.log.info("Datei '" + pfadMitDatei + "' existiert nicht oder ist leer!");
                    }
                }
            } catch (final Exception e) {
                this.log.error(new ExceptionHandler().getErrorMeldung(e, request));
            }
        }
        return xmlIterator;
    }

    /**
     * Suche im Ordner eine Datei die mit der VsnrGruppe uerbeinstimmt. Pruefe
     * die Dateigroesse > 0 und erstelle danach den XMLIterator
     *
     * @param ordner
     *            Verzeichnis
     * @param vsnrGruppe
     *            VsnrGruppe
     * @param clazz
     *            Classe
     * @return XmlIterator oder NULL
     */
    public <T> XMLIterator<T> getXmlIteratorZuDateiUndVsnrGruppe(final String ordner, final String vsnrGruppe,
            final Class<T> clazz, final String request) {
        XMLIterator<T> xmlIterator = null;
        if ((clazz != null) && !isEmptyString(ordner) && !isEmptyString(vsnrGruppe)) {
            try {
                final String pfadMitDatei = getDateiMitVsnrGruppeAusPfad(ordner, vsnrGruppe);
                if (!isEmptyString(pfadMitDatei)) {
                    final File file = new File(pfadMitDatei);
                    if ((file.exists()) && (file.length() > 0)) {
                        xmlIterator = new XMLIterator<>(pfadMitDatei, clazz, request);
                    } else {
                        this.log.info("Datei '" + pfadMitDatei + "' existiert nicht oder ist leer!");
                    }
                }
            } catch (final Exception e) {
                this.log.error(new ExceptionHandler().getErrorMeldung(e, request));
            }
        }
        return xmlIterator;
    }

    /**
     *
     */
    public static <T> Class<@Nullable T> getNullableClass(final Class<@NonNull T> klass) {
        final Class<@Nullable T> rueckgabe = helper.getGenericObject(klass);
        return (rueckgabe);
    }

    /**
     * liefert true, wenn die Leistung nicht im Gesundheitskonto angezeigt werden soll: entweder die letzten 2 Stellen
     * im Leistungspfad = "99" oder der entsprechende MgkLive-Wert
     */
    public boolean isPositionMgkNichtanzeige(final MgkLive mgkLive, final String leistungspfad) {
        boolean rueckgabe = false;
        if (mgkLive != null) {
            rueckgabe = ((mgkLive.equals(MgkLive.LIVE_N_MGK_N)) || (mgkLive.equals(MgkLive.LIVE_J_MGK_N)));
        }
        if (!rueckgabe) {
            rueckgabe = ((!isEmptyString(leistungspfad))
                    && (new Rechner().istGleich(new TypConverter().getNotNullSubstring(leistungspfad, 10, 12),
                            Constants_Leistungspfade.NICHTANZEIGE_LEISTUNGSPFAD_ENDE)));
        }
        return (rueckgabe);
    }

    /**
     * Nur fuer CXF3.1.4/EAP7!
     */
    public String getWsSecurityBenutzername() {
        String benutzername = null;
        final Message message = PhaseInterceptorChain.getCurrentMessage();
        final SecurityContext context = message
                .get(sv.utils.Helper.getNullableClass(SecurityContext.class));
        if (context != null) {
            benutzername = context.getUserPrincipal().getName();
        }
        this.log.debug("benutzername = " + benutzername);
        return (benutzername);
    }

    /**
     * @param conversation
     *            Conversation
     *            verlaengert die Conversation um Session Timeout
     */
    public void updateConversationTimeout(final Conversation conversation) {
        if (conversation != null) {
            Optional.ofNullable(FacesContext.getCurrentInstance()).map(o -> getHttpServletRequest(o))
                    .map(o -> o.getSession(false)).ifPresent(o -> {
                        conversation.setTimeout(1000 * o.getMaxInactiveInterval());
                        this.log.debug("Conversation Id=" + conversation.getId() + ", Lebensdauer = "
                                + conversation.getTimeout());
                    });
        } else {
            this.log.info("Conversation = null");
        }
    }
}
