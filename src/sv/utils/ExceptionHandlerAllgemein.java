package sv.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sv.utils.beans.KonfigurationAllgemein;
import constants.Constants_Allgemein;
import constants.Constants_Datumsformat;

/**
 * Klasse zum Erzeugen von Strings aus Exceptions
 *
 * @author SV-Benutzer
 */
public class ExceptionHandlerAllgemein {
    protected Logger log = LoggerFactory.getLogger(getClass());

    private final HelperAllgemein helperAllgemein;

    private final TypConverter typConverter;

    /**
     * Zaehlt die aufgetretenen Fehler. Der zaehler wird im Log eingetragen und
     * auch per E-Mail versendet, damit Mail und Log zusammengefunden werden
     * koennen.
     */
    private static long zaehlerFehler = 0;

    /**
     * Default-Konstruktor
     */
    public ExceptionHandlerAllgemein() {
        this.helperAllgemein = new HelperAllgemein();
        this.typConverter = new TypConverter();
    }

    /**
     * erzeugt eine eindeutige Fehler-ID
     */
    public String getFehlerId() {
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(KonfigurationAllgemein.getAnwendung()).append("-").append(KonfigurationAllgemein.getVersion())
                    .append("-").append(System.currentTimeMillis()).append("-").append(this.typConverter
                            .getExaktLangenString(String.valueOf(erhoeheZaehlerFehler()), 20, '0', false));
        } catch (final Exception e) {
            this.log.error("Fehler: " + e + " - " + getStackTrace(e));
        }
        return (sb.toString());
    }

    /**
     * erhoeht zaehlerFehler um 1 und gibt den neuen Wert zurueck
     */
    private synchronized long erhoeheZaehlerFehler() {
        return (++zaehlerFehler);
    }

    /**
     * gibt den Stacktrace der uebergebenen Exception als String zurueck
     */
    public String getStackTrace(final Throwable throwable) {
        String string = null;
        try {
            try (final StringWriter bodyWriter = new StringWriter()) {
                try (final PrintWriter stackTrace = new PrintWriter(bodyWriter)) {
                    if (throwable != null) {
                        throwable.printStackTrace(stackTrace);
                    }
                    string = bodyWriter.toString();
                    stackTrace.flush();
                    stackTrace.close();
                }
                bodyWriter.flush();
                bodyWriter.close();
            }
        } catch (final Exception e) {
            this.log.error("Fehler: " + e);
            e.printStackTrace();
        }
        return (string);
    }

    /**
     * erzeugt aus der Exception eine Standard-Fehlermeldung und verschickt
     * diese als E-Mail
     */
    public String getErrorMeldung(final Throwable throwable, final String request) {
        return (getErrorMeldung(throwable, true, request));
    }

    /**
     * erzeugt aus der Exception eine Standard-Fehlermeldung
     *
     * @param als_email_versenden
     *            true: Fehlermeldung wird auch per E-Mail verschickt
     */
    public String getErrorMeldung(final Throwable throwable, final boolean als_email_versenden, final String request) {
        String throwableMessageString = null;
        String throwableStacktraceString = null;
        if (throwable != null) {
            throwableMessageString = throwable.getMessage();
            throwableStacktraceString = getStackTrace(throwable);
        }
        return (getErrorMeldung(getFehlerId(), new Timestamp(System.currentTimeMillis()), throwableMessageString,
                throwableStacktraceString, request,
                this.helperAllgemein.mapToString(System.getProperties(), "Property"), als_email_versenden,
                KonfigurationAllgemein.getAnwendung(), null));
    }

    /**
     * erzeugt aus der Exception eine Standard-Fehlermeldung
     *
     * @param als_email_versenden
     *            true: Fehlermeldung wird auch per E-Mail verschickt
     * @param fehlerAn
     *            falls befuellt, wird das Fehler-Email nicht an die
     *            Standard-Empfaenger-Liste dieser Anwendung sondern an die
     *            uebergebenen Empfaenger verschickt
     */
    public String getErrorMeldung(final String fehlerId, final Timestamp zeitpunkt, final String throwableMessageString,
            final String throwableStacktraceString, final String requestString, final String systemPropertiesString,
            final boolean als_email_versenden, final String anwendung, final List<String> fehlerAn) {
        String string = null;
        try {
            final String mailBody = getErrorMeldungString(fehlerId, zeitpunkt, throwableMessageString,
                    throwableStacktraceString, systemPropertiesString);
            if (als_email_versenden) {
                // Achtung: Mailservice darf keine Instanzvariable sein, da im
                // Konstruktor von Mailservice eine Instanz von
                // ExceptionHandlerAllgemein angelegt wird => gegenseitiger
                // Aufruf fuehrt zu einem StackOverflowError!
                final MailService mailService = new MailService();
                // die Fehlermeldung soll auch im Betreff stehen, damit dieser
                // nicht zu lang wird, werden nur die ersten 50 Zeichen der
                // Fehlermeldung genommen (da z.B. Spring extrem lange Meldungen
                // hat) versende Mail mit kleinem Body
                final String subject = "Fehler in " + anwendung + ": " + this.typConverter.getNotNullString(
                        this.typConverter.getMaximalLangenString(throwableMessageString, 50));
                if (this.helperAllgemein.isEmptyCollection(fehlerAn)) {
                    mailService.versendeFehlerEmail(subject, mailBody, false, null);
                } else {
                    this.log.info("verschicke Fehler-E-Mail an abweichende(n) Empfaenger: " + fehlerAn);
                    mailService.versendeFehlerEmail(subject, mailBody, fehlerAn, null, null, false, null);
                }
            }
            // nach dem Mailversand wird die Fehlermeldung noch massiv fuer
            // das Log erweitert
            final StringBuffer sb = new StringBuffer();
            sb.append(mailBody);
            try {
                sb.append(this.typConverter.getNotNullString(requestString));
            } catch (final Exception e) {
                this.log.error("Fehler: " + e + " - " + getStackTrace(e));
            }
            string = sb.toString();
        } catch (final Exception e1) {
            this.log.error(getErrorMeldung(e1, false, requestString));
        }
        return (string);
    }

    /**
     * erzeugt aus der Exception eine Standard-Fehlermeldung und verschickt
     * diese als E-Mail
     */
    private String getErrorMeldungString(final String fehlerId, final Timestamp zeitpunkt,
            final String throwableMessageString, final String throwableStacktraceString,
            final String systemPropertiesString) {
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append("Fehler-ID: ").append(fehlerId).append(Constants_Allgemein.ENDL).append(Constants_Allgemein.ENDL);
            sb.append("Zeit: ")
                    .append(this.typConverter.getNullString(zeitpunkt,
                            Constants_Datumsformat.DATUMSFORMAT_MIT_UHRZEIT))
                    .append(Constants_Allgemein.ENDL).append(Constants_Allgemein.ENDL);
            sb.append("Fehler: ").append(throwableMessageString).append(Constants_Allgemein.ENDL)
                    .append(Constants_Allgemein.ENDL);
            sb.append("Stacktrace: ").append(throwableStacktraceString).append(Constants_Allgemein.ENDL)
                    .append(Constants_Allgemein.ENDL);
            sb.append("Properties des Systems, auf dem der Fehler aufgetreten ist: ").append(Constants_Allgemein.ENDL)
                    .append(systemPropertiesString).append(Constants_Allgemein.ENDL).append(Constants_Allgemein.ENDL);
        } catch (final Exception e) {
            this.log.error("Fehler: " + e + " - " + getStackTrace(e));
        }
        return (sb.toString());
    }

    /**
     * macht aus allen Daten des uebergebenen Request einen String
     */
    public String getRequestString(final HttpServletRequest request) {
        String principalString = null;
        try {
            if (request != null) {
                principalString = principalToString(request.getUserPrincipal());
            }
        } catch (final Exception e) {
            this.log.error("Fehler: " + e + " - " + getStackTrace(e));
        }
        return (getRequestString(request, principalString));
    }

    /**
     * macht aus allen Daten des uebergebenen Request einen String
     */
    protected String getRequestString(final HttpServletRequest request, final String principalString) {
        final StringBuffer sb = new StringBuffer();
        try {
            if (request != null) {
                HttpSession session = null;
                ServletContext context = null;
                try {
                    session = request.getSession(false);
                    if (session != null) {
                        context = session.getServletContext();
                        sb.append("Klickstream: ").append(this.helperAllgemein.getKlickStream(session))
                                .append(Constants_Allgemein.ENDL).append(Constants_Allgemein.ENDL);
                    }
                } catch (final Exception e) {
                    this.log.error("Fehler: " + e + " - " + getStackTrace(e));
                }
                try {
                    sb.append(principalString);
                } catch (final Exception e) {
                    this.log.error("Fehler: " + e + " - " + getStackTrace(e));
                }
                try {
                    sb.append(getRequestVariablen(request));
                } catch (final Exception e) {
                    this.log.error("Fehler: " + e + " - " + getStackTrace(e));
                }
                try {
                    if (session != null) {
                        sb.append("Session-Variablen: ").append(Constants_Allgemein.ENDL);
                        sb.append("CreationTime: ").append(session.getCreationTime()).append(Constants_Allgemein.ENDL);
                        sb.append("LastAccessedTime: ").append(session.getLastAccessedTime())
                                .append(Constants_Allgemein.ENDL);
                        sb.append("MaxInactiveInterval: ").append(session.getMaxInactiveInterval())
                                .append(Constants_Allgemein.ENDL);
                        sb.append("Id: ").append(session.getId()).append(Constants_Allgemein.ENDL);
                        sb.append(Constants_Allgemein.ENDL);
                    }
                } catch (final Exception e) {
                    this.log.error("Fehler: " + e + " - " + getStackTrace(e));
                }
                try {
                    if (context != null) {
                        sb.append("Servlet-Context-Variablen: ").append(Constants_Allgemein.ENDL);
                        sb.append("MajorVersion: ").append(context.getMajorVersion()).append(Constants_Allgemein.ENDL);
                        sb.append("MinorVersion: ").append(context.getMinorVersion()).append(Constants_Allgemein.ENDL);
                        sb.append("ServerInfo: ").append(context.getServerInfo()).append(Constants_Allgemein.ENDL);
                        sb.append("ServletContextName: ").append(context.getServletContextName())
                                .append(Constants_Allgemein.ENDL);
                    }
                    sb.append(Constants_Allgemein.ENDL);
                } catch (final Exception e) {
                    this.log.error("Fehler: " + e + " - " + getStackTrace(e));
                }
                try {
                    sb.append(getRequestParameter(request));
                } catch (final Exception e) {
                    this.log.error("Fehler: " + e + " - " + getStackTrace(e));
                }
                try {
                    sb.append(getRequestAttributes(request));
                } catch (final Exception e) {
                    this.log.error("Fehler: " + e + " - " + getStackTrace(e));
                }
                try {
                    sb.append("Header im Request: ");
                    final Enumeration<String> en = this.helperAllgemein.getGenericObject(request.getHeaderNames());
                    if (en != null) {
                        int i = 1;
                        sb.append(Constants_Allgemein.ENDL);
                        while (en.hasMoreElements()) {
                            final String next = en.nextElement();
                            if (!this.helperAllgemein.isEmptyString(next)) {
                                sb.append(i).append(". Header: '").append(next)
                                        .append("' // '" + request.getHeader(next)).append("'")
                                        .append(Constants_Allgemein.ENDL);
                            }
                            i++;
                        }
                    }
                    sb.append(Constants_Allgemein.ENDL);
                } catch (final Exception e) {
                    this.log.error("Fehler: " + e + " - " + getStackTrace(e));
                }
                try {
                    if (session != null) {
                        sb.append("Attribute in der Session: ");
                        final Enumeration<String> en = this.helperAllgemein
                                .getGenericObject(session.getAttributeNames());
                        if (en != null) {
                            int i = 1;
                            sb.append(Constants_Allgemein.ENDL);
                            while (en.hasMoreElements()) {
                                final String next = en.nextElement();
                                if (!this.helperAllgemein.isEmptyString(next)) {
                                    sb.append(i).append(". Attribut: '").append(next)
                                            .append("' // '" + session.getAttribute(next)).append("'")
                                            .append(Constants_Allgemein.ENDL);
                                }
                                i++;
                            }
                        }
                        sb.append(Constants_Allgemein.ENDL);
                    }
                } catch (final Exception e) {
                    this.log.error("Fehler: " + e + " - " + getStackTrace(e));
                }
                try {
                    if (context != null) {
                        sb.append("Attribute im Servlet-Context: ");
                        final Enumeration<String> en = this.helperAllgemein
                                .getGenericObject(context.getAttributeNames());
                        if (en != null) {
                            int i = 1;
                            sb.append(Constants_Allgemein.ENDL);
                            while (en.hasMoreElements()) {
                                final String next = en.nextElement();
                                // "csfcff" Element auslassen, wirft mit EAP7 eine NPE
                                if (!this.helperAllgemein.isEmptyString(next) && !"csfcff".equals(next)
                                        && !next.startsWith("com.sun.faces.")) {
                                    sb.append(i).append(". Attribut: '").append(next)
                                            .append("' // '" + context.getAttribute(next)).append("'")
                                            .append(Constants_Allgemein.ENDL);
                                }
                                i++;
                            }
                        }
                        sb.append(Constants_Allgemein.ENDL);
                    }
                } catch (final Exception e) {
                    this.log.error("Fehler: " + e + " - " + getStackTrace(e));
                }
                try {
                    if (context != null) {
                        sb.append("InitParameter im Servlet-Context: ");
                        final Enumeration<String> en = this.helperAllgemein
                                .getGenericObject(context.getInitParameterNames());
                        if (en != null) {
                            int i = 1;
                            sb.append(Constants_Allgemein.ENDL);
                            while (en.hasMoreElements()) {
                                final String next = en.nextElement();
                                if (!this.helperAllgemein.isEmptyString(next)) {
                                    sb.append(i).append(". InitParameter: '").append(next)
                                            .append("' // '" + context.getInitParameter(next)).append("'")
                                            .append(Constants_Allgemein.ENDL);
                                }
                                i++;
                            }
                        }
                        sb.append(Constants_Allgemein.ENDL);
                    }
                } catch (final Exception e) {
                    this.log.error("Fehler: " + e + " - " + getStackTrace(e));
                }
            }
        } catch (final Exception e) {
            this.log.error("Fehler: " + e + " - " + getStackTrace(e));
        }
        return (sb.toString());
    }

    /**
     * gibt einen String mit dem uebergebenen Principal zurueck
     */
    protected String principalToString(final Principal principal) {
        final StringBuffer sb = new StringBuffer();
        sb.append("UserPrincipal: ").append(principal).append(Constants_Allgemein.ENDL)
                .append(Constants_Allgemein.ENDL);
        return (sb.toString());
    }

    /**
     * gibt einen String mit allen im uebergebenen Request enthaltenen
     * Parametern zurueck
     */
    protected String getRequestParameter(final HttpServletRequest request) {
        final StringBuffer sb = new StringBuffer();
        try {
            if (request != null) {
                sb.append("Parameter im Request: ");
                final Enumeration<String> en = this.helperAllgemein.getGenericObject(request.getParameterNames());
                if (en != null) {
                    int i = 1;
                    sb.append(Constants_Allgemein.ENDL);
                    while (en.hasMoreElements()) {
                        final String next = en.nextElement();
                        if (!this.helperAllgemein.isEmptyString(next)) {
                            sb.append(i).append(". Parameter: '").append(next).append("' // '")
                                    .append(this.helperAllgemein.arrayToString(request.getParameterValues(next),
                                            "Parameter-Wert"))
                                    .append("'").append(Constants_Allgemein.ENDL);
                        }
                        i++;
                    }
                }
                sb.append(Constants_Allgemein.ENDL);
            }
        } catch (final Exception e) {
            this.log.error("Fehler: " + e + " - " + getStackTrace(e));
        }
        return (sb.toString());
    }

    /**
     * gibt einen String mit allen im uebergebenen Request enthaltenen
     * Attributen zurueck
     */
    protected String getRequestAttributes(final HttpServletRequest request) {
        final StringBuffer sb = new StringBuffer();
        try {
            if (request != null) {
                sb.append("Attribute im Request: ");
                final Enumeration<String> en = this.helperAllgemein.getGenericObject(request.getAttributeNames());
                if (en != null) {
                    int i = 1;
                    sb.append(Constants_Allgemein.ENDL);
                    while (en.hasMoreElements()) {
                        final String next = en.nextElement();
                        if (!this.helperAllgemein.isEmptyString(next)) {
                            sb.append(i).append(". Attribut: '").append(next)
                                    .append("' // '" + request.getAttribute(next)).append("'")
                                    .append(Constants_Allgemein.ENDL);
                        }
                        i++;
                    }
                }
                sb.append(Constants_Allgemein.ENDL);
            }
        } catch (final Exception e) {
            this.log.error("Fehler: " + e + " - " + getStackTrace(e));
        }
        return (sb.toString());
    }

    /**
     * gibt einen String mit allen im uebergebenen Request enthaltenen Werten
     * zurueck
     */
    protected String getRequestVariablen(final HttpServletRequest request) {
        final StringBuffer sb = new StringBuffer();
        try {
            if (request != null) {
                sb.append("Request-Variablen: ").append(Constants_Allgemein.ENDL);
                sb.append("AuthType: ").append(request.getAuthType()).append(Constants_Allgemein.ENDL);
                sb.append("LocalPort: ").append(request.getLocalPort()).append(Constants_Allgemein.ENDL);
                sb.append("RemotePort: ").append(request.getRemotePort()).append(Constants_Allgemein.ENDL);
                sb.append("ServerPort: ").append(request.getServerPort()).append(Constants_Allgemein.ENDL);
                sb.append("CharacterEncoding: ").append(request.getCharacterEncoding())
                        .append(Constants_Allgemein.ENDL);
                sb.append("ContentType: ").append(request.getContentType()).append(Constants_Allgemein.ENDL);
                sb.append("ContextPath: ").append(request.getContextPath()).append(Constants_Allgemein.ENDL);
                sb.append("LocalAddr: ").append(request.getLocalAddr()).append(Constants_Allgemein.ENDL);
                sb.append("LocalName: ").append(request.getLocalName()).append(Constants_Allgemein.ENDL);
                sb.append("Method: ").append(request.getMethod()).append(Constants_Allgemein.ENDL);
                sb.append("PathInfo: ").append(request.getPathInfo()).append(Constants_Allgemein.ENDL);
                sb.append("PathTranslated: ").append(request.getPathTranslated()).append(Constants_Allgemein.ENDL);
                sb.append("Protocol: ").append(request.getProtocol()).append(Constants_Allgemein.ENDL);
                sb.append("QueryString: ").append(request.getQueryString()).append(Constants_Allgemein.ENDL);
                sb.append("RemoteAddr: ").append(request.getRemoteAddr()).append(Constants_Allgemein.ENDL);
                sb.append("RemoteHost: ").append(request.getRemoteHost()).append(Constants_Allgemein.ENDL);
                sb.append("RemoteUser: ").append(request.getRemoteUser()).append(Constants_Allgemein.ENDL);
                sb.append("RequestedSessionId: ").append(request.getRequestedSessionId())
                        .append(Constants_Allgemein.ENDL);
                sb.append("RequestURI: ").append(request.getRequestURI()).append(Constants_Allgemein.ENDL);
                sb.append("RequestURL: ").append(request.getRequestURL()).append(Constants_Allgemein.ENDL);
                sb.append("Scheme: ").append(request.getScheme()).append(Constants_Allgemein.ENDL);
                sb.append("ServerName: ").append(request.getServerName()).append(Constants_Allgemein.ENDL);
                sb.append("ServletPath: ").append(request.getServletPath()).append(Constants_Allgemein.ENDL);
                sb.append(Constants_Allgemein.ENDL);
            }
        } catch (final Exception e) {
            this.log.error("Fehler: " + e + " - " + getStackTrace(e));
        }
        return (sb.toString());
    }
}
