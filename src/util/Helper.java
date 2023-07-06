package util;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.sql.Date;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.enterprise.context.BusyConversationException;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.NonexistentConversationException;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.weld.context.ManagedConversation;

import beans.dto.LeistungspfadDTO;
import beans.enums.Katalog;
import beans.form.FormTarifKatalog;
import logic.LogikTarifkatalog;
import constants.Constants_Scope_Request;
import constants.Constants_Scope_Session;
import constants.Constants_Typ;
import sv.utils.beans.KonfigurationAllgemein;
import sv.utils.beans.KonfigurationBasic;
import sv.utils.beans.SelectPseudoBean;
import constants.Constants_Allgemein;
import constants.Constants_Session;
import sv.utils.logic.principal.PrincipalConverter;
import sv.utils.soap.beve.Bearbeiter;
import sv.utils.soap.beve.ProfilWert;
import sv.utils.ExceptionHandler;
import sv.utils.Rechner;
import sv.utils.TypConverter;

/**
 * @author SV-Benutzer
 */
public class Helper extends sv.utils.Helper {
    /**
     *
     * Default-Konstruktor
     *
     */
    public Helper() {
        super();
        KonfigurationAllgemein.setAnwendung("Tarifkatalog");
        KonfigurationBasic
                .setApplicationContextStandalone("resources/spring/tarifkatalogApplicationContextStandalone.xml");
    }

    /**
     * prueft ob fuer die Aktion eine Validierung notwendig ist
     */
    public boolean isZuValidierendeAktion(String aktion) {
        boolean rueckgabe = false;
        this.log.debug("aktion: '" + aktion + "'");
        if (aktion != null) {
            aktion = aktion.trim();
            if (isEmptyString(aktion)) {
                rueckgabe = true;
            }
        }
        return (rueckgabe);
    }

    /**
     * holt ProfilwertText aus BEVE
     */
    public String getBearbeiterProfilWertText(final String eigenschaft, final String defaultWert,
            final HttpSession session) {
        String returnValue = null;
        final Bearbeiter bearbeiter = (Bearbeiter) session.getAttribute(Constants_Session.SESSION_BEARBEITER);
        final TypConverter typConverter = new TypConverter();
        if (bearbeiter != null) {
            final List<ProfilWert> profilWertList = bearbeiter.getProfil();
            if ((profilWertList != null) && (!profilWertList.isEmpty())) {
                final Iterator<ProfilWert> profilIt = profilWertList.iterator();
                while ((profilIt.hasNext()) && (returnValue == null)) {
                    final ProfilWert profilWert = profilIt.next();
                    if (typConverter.getNotNullString(profilWert.getTyp()).equalsIgnoreCase(eigenschaft)) {
                        returnValue = profilWert.getWertText();
                    }
                }
            }
        }
        if (returnValue == null) {
            returnValue = defaultWert;
        }
        this.log.debug(
                "getBearbeiterProfilWertText (" + eigenschaft + "," + defaultWert + ",-) liefert " + returnValue);
        return (returnValue);
    }

    /**
     * holt Forwarder aus Request
     */
    public String getForwarder(final HttpServletRequest request) {
        String forwarder = "portal";
        if (request != null) {
            final TypConverter typConverter = new TypConverter();
            forwarder = typConverter.getNotNullString(request.getParameter(Constants_Scope_Request.REQUEST_FORWARDER));
            if (this.isEmptyString(forwarder)) {
                forwarder = typConverter
                        .getNotNullString((String) request.getAttribute(Constants_Scope_Request.REQUEST_FORWARDER));
            }
        }
        return (forwarder);
    }

    /**
     * gibt ein session-attribute zurueck
     */
    public <T> T getSessionAttributeZuVsnr(final String session_key, final String attribute,
            final HttpServletRequest request, final String requestString) {
        Object wert = null;
        if (request != null) {
            final HttpSession session = request.getSession();
            if (session != null) {
                if (!this.isEmptyString(session_key)) {
                    if (!this.isEmptyString(attribute)) {
                        final Map<String, Map<String, Object>> myVsnrSessionValueMap = this.getSessionAttribute(session,
                                Constants_Scope_Session.SESSION_VSNR_MAP, requestString);
                        if ((myVsnrSessionValueMap != null) && !myVsnrSessionValueMap.isEmpty()) {
                            final Map<String, Object> vsnrValueMap = myVsnrSessionValueMap.get(session_key);
                            if ((vsnrValueMap != null) && !vsnrValueMap.isEmpty()) {
                                wert = vsnrValueMap.get(attribute);
                                vsnrValueMap.put(Constants_Scope_Session.SESSION_TIMESTAMP_PROPERTY,
                                        Long.valueOf(System.currentTimeMillis()));
                            }
                        }
                    }
                }
            }
        }
        cleanupSessionByTimestamp(session_key, request, requestString);
        return getGenericObject(wert);
    }

    /**
     * berechnet timeout
     */
    public void cleanupSessionByTimestamp(final String session_key, final HttpServletRequest request,
            final String requestString) {
        if (request != null) {
            final HttpSession session = request.getSession();
            if (session != null) {
                final Map<String, Map<String, Object>> myVsnrSessionValueMap = this.getSessionAttribute(session,
                        Constants_Scope_Session.SESSION_VSNR_MAP, requestString);
                if ((myVsnrSessionValueMap != null)) {
                    final List<String> keyList = new ArrayList<>();
                    for (final Map.Entry<String, Map<String, Object>> entry : myVsnrSessionValueMap.entrySet()) {
                        if (!entry.getKey().equalsIgnoreCase(session_key)) {
                            final Map<String, Object> vsnrValueMap = entry.getValue();
                            if (vsnrValueMap != null) {
                                final Long timestamp = this.getGenericObject(
                                        vsnrValueMap.get(Constants_Scope_Session.SESSION_TIMESTAMP_PROPERTY));
                                if (timestamp != null) {
                                    final long timestamp_long = timestamp.longValue();
                                    final long now = System.currentTimeMillis();
                                    if ((now - timestamp_long) > Constants_Allgemein.SESSION_TIMEOUT) {
                                        keyList.add(entry.getKey());
                                    }
                                }
                            }
                        }
                    }
                    for (final String key : keyList) {
                        myVsnrSessionValueMap.remove(key);
                    }
                }
            }
        }
    }

       /**
     * prueft, ob ein nichtdarstellbares Zeichen in dem uebergebenen String
     * enthalten ist
     *
     */
    public boolean pruefeTextaufUngueltigeZeichen(final String text) {
        boolean gefunden = false;
        if (!isEmptyString(text)) {
            final char[] chars = text.toCharArray();
            for (int i = 0; ((i < text.length()) && !gefunden); i++) {
                final char c = chars[i];
                if ((c >= 128) && (c <= 159)) {
                    gefunden = true;
                    this.log.warn("ungueltiges Zeichen gefunden: " + c + "; hex-Zeichen: " + Integer.toHexString(c)
                            + "; ascii-Code: " + (int) c);
                }
            }
        }
        return (gefunden);
    }

    /**
     * liefert Leistungspfad als max. 12-stelligen String
     */
    public String getLeistungPfad(LeistungspfadDTO leistungspfadDTO) {
        String leistungspfad = null;
        if (leistungspfadDTO != null) {
            final StringBuilder sb = new StringBuilder();
            do {
                sb.insert(0, leistungspfadDTO.getLeistungspfad_code());
                leistungspfadDTO = leistungspfadDTO.getLeistungspfadParent();
            } while (leistungspfadDTO != null);
            leistungspfad = sb.toString();
        }
        return (leistungspfad);
    }

    /**
     * @param textInput
     *            String mit Markup
     * @return String ohne Markup
     */
    public String removeMarkup(final String textInput) {
        String text = textInput;
        this.log.debug("Eingegeben: " + text);
        if (!isEmptyString(text)) {
            int bracketOpen = text.indexOf("<");
            if (bracketOpen >= 0) {
                int bracketClose = text.indexOf(">", bracketOpen);
                String partB4 = "";
                String partBack = "";
                String slashN = "";
                while (bracketOpen >= 0) {
                    if (bracketOpen != 0) {
                        partB4 = text.substring(0, bracketOpen);
                    }
                    if ((bracketClose < text.length()) && (bracketClose >= 0)) {
                        partBack = text.substring(bracketClose + 1);
                        if (((bracketClose - bracketOpen) == 3)
                                && (text.substring(bracketOpen + 1, bracketClose).equalsIgnoreCase("BR"))) {
                            slashN = "\r\n\t";
                        } else if ((bracketClose - bracketOpen) >= 4) {
                            String element = text.substring(bracketOpen + 1, bracketClose).trim();
                            if ((element != null) && element.toUpperCase().startsWith("BR") && element.endsWith("/")) {
                                element = element.substring(2, element.length() - 1).trim();
                                if ((element == null) || (element.length() == 0)) {
                                    slashN = "\r\n\t";
                                }
                            }
                        }
                    }
                    text = (new StringBuilder(partB4)).append(slashN).append(partBack).toString();
                    bracketOpen = text.indexOf("<");
                    if (bracketOpen >= 0) {
                        bracketClose = text.indexOf(">", bracketOpen);
                    } else {
                        bracketClose = -1;
                    }
                    partB4 = "";
                    partBack = "";
                    slashN = "";
                }
            }
            if (!isEmptyString(text)) {
                if (text.contains("&")) {
                    text = text.replace("&szlig;", String.valueOf((char) 223));
                    text = text.replace("&Auml;", String.valueOf((char) 196));
                    text = text.replace("&Ouml;", String.valueOf((char) 214));
                    text = text.replace("&Uuml;", String.valueOf((char) 220));
                    text = text.replace("&auml;", String.valueOf((char) 228));
                    text = text.replace("&ouml;", String.valueOf((char) 246));
                    text = text.replace("&uuml;", String.valueOf((char) 252));
                }
            }
        }
        this.log.debug("After removeMarkup: " + text);
        return text;
    }

    /**
     * FacesMessage hinzufuegen
     */
    public void addFacesMessageSimple(final Severity fms, final String str) {
        this.log.info(str);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fms, removeMarkup(str), null));
    }

    /**
     * @param identifier
     *            Kennung fuer Error-Message
     */
    public FacesMessage addFacesErrorMessage(final FacesContext fContext, final String identifier,
            final boolean calledByValidator) {
        final FacesContext facesContext = (fContext == null) ? FacesContext.getCurrentInstance() : fContext;
        final ResourceBundle mBundle = facesContext.getApplication().getResourceBundle(facesContext, "messages");
        this.log.debug("ResourceBundle=" + mBundle);
        final String msgValue = mBundle.getString(identifier);
        final String msgValueWOMarkup = removeMarkup(msgValue);
        final FacesMessage fMesg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgValueWOMarkup, null);
        final List<FacesMessage> fmesgList = facesContext.getMessageList(null);
        boolean found = false;
        for (final FacesMessage mesg : fmesgList) {
            if (!found && (mesg.getSummary().equalsIgnoreCase(msgValueWOMarkup)
                    || mesg.getDetail().equalsIgnoreCase(msgValueWOMarkup))) {
                found = true;
            }
        }
        if (!found && !calledByValidator) {
            facesContext.addMessage(null, fMesg);
        }
        return fMesg;
    }

    /**
     *
     * @return ResourceBundle
     */
    public ResourceBundle getMessageBundle() {
        final FacesContext fc = FacesContext.getCurrentInstance();
        return getMessageBundle(fc);
    }

    /**
     *
     * @return ResourceBundle
     */
    public ResourceBundle getMessageBundle(final FacesContext fc) {
        return fc.getApplication().getResourceBundle(fc, "messages");
    }

    /**
     * @param request
     *            HttpServletRequest Objekt
     * @param session
     *            HttpSession Objekt
     */
    public boolean isUpdateOfTimeoutsPossible(final String request, final HttpSession session) {
        boolean isConversationAlive = true;
        final long timeSinceLastAccess = System.currentTimeMillis() - session.getLastAccessedTime();
        final long maxInactMs = 1000 * session.getMaxInactiveInterval();
        if (timeSinceLastAccess < maxInactMs) {
            try {
                final Map<String, ManagedConversation> mngConv = getGenericObject(
                        session.getAttribute("org.jboss.weld.context.ConversationContext.conversations"));
                // this.log.debug("Map=" + mngConv);
                if (!isEmptyMap(mngConv)) {
                    final List<String> list = new ArrayList<>(mngConv.keySet());
                    for (final String keyMngConv : list) {
                        // this.log.debug("Key = " + keyMngConv);
                        final ManagedConversation cv = mngConv.get(keyMngConv);
                        try {
                            cv.touch();
                            this.log.debug("Id = " + cv.getId() + ", last used = " + cv.getLastUsed() + ", timeout = "
                                    + cv.getTimeout() + ", isTransient = " + cv.isTransient());
                            updateConversationTimeout(cv, request, session);
                        } catch (final IllegalStateException e) {
                            // nichts zu tun, Update sollte in aktiver
                            // Conversation stattfinden
                            this.log.info("Conversation war bereits aktiv. " + e);
                        } catch (final BusyConversationException bcEx) {
                            // nichts zu tun, Update sollte in aktiver
                            // Conversation stattfinden
                            this.log.info("Die Konversation ist im Status \"Busy\", kein Update des Timeouts noetig. "
                                    + bcEx);
                        } catch (final NonexistentConversationException necExc) {
                            this.log.info("Konversations-Timeout fuer " + cv.getId() + "! " + necExc);
                            isConversationAlive = false;
                        }
                    }
                } else {
                    // z.B. Einstieg mit panzeige.xhtml?posnr=<POSNR>
                    isConversationAlive = true;
                }
            } catch (final Exception e) {
                isConversationAlive = false;
                this.log.warn(new ExceptionHandler().getErrorMeldung(e, request));
            }
        }
        return isConversationAlive;
    }

    /**
     * @param nameOfConvId
     *            Name der Conversation Id im Request
     * @param request
     *            Request
     */
    public void doCheckAndUpdateOfTimeouts(final String nameOfConvId, final HttpServletRequest request,
            final String requestString) {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final Map<String, String> reqPar = facesContext.getExternalContext().getRequestParameterMap();
        // this.log.debug("Parameters: " + reqPar);
        if (!isEmptyMap(reqPar)) {
            final String convId = reqPar.get(nameOfConvId);
            if (!isEmptyString(convId)) {
                // Checke nur, wenn Conversation-Id bekannt
                isUpdateOfTimeoutsPossible(convId, requestString, request.getSession(false));
            }
        }
    }

    /**
     * @param request
     *            HttpServletRequest Objekt
     * @param session
     *            HttpSession Objekt
     */
    public boolean isUpdateOfTimeoutsPossible(final String conversationId, final String request,
            final HttpSession session) {
        boolean isConversationAlive = false;
        final long timeSinceLastAccess = System.currentTimeMillis() - session.getLastAccessedTime();
        final long maxInactMs = 1000 * session.getMaxInactiveInterval();
        if ((conversationId != null) && (timeSinceLastAccess < maxInactMs)) {
            try {
                final Map<String, ManagedConversation> mngConv = getGenericObject(
                        session.getAttribute("org.jboss.weld.context.ConversationContext.conversations"));
                // this.log.debug("Map=" + mngConv);
                if (!isEmptyMap(mngConv)) {
                    final List<String> list = new ArrayList<>(mngConv.keySet());
                    for (final String keyMngConv : list) {
                        // this.log.debug("Key = " + keyMngConv);
                        if (conversationId.equals(keyMngConv)) {
                            final ManagedConversation cv = mngConv.get(keyMngConv);
                            try {
                                cv.touch();
                                // this.log.debug("Id = " + cv.getId() + ", last used = " + cv.getLastUsed()
                                // + ", timeout = " + cv.getTimeout() + ", isTransient = " + cv.isTransient());
                                updateConversationTimeout(cv, request, session);
                            } catch (final IllegalStateException e) {
                                // nichts zu tun, Update sollte in aktiver
                                // Conversation stattfinden
                                this.log.info("Conversation war bereits aktiv. " + e);
                            } catch (final BusyConversationException bcEx) {
                                // nichts zu tun, Update sollte in aktiver
                                // Conversation stattfinden
                                this.log.info(
                                        "Die Konversation ist im Status \"Busy\", kein Update des Timeouts noetig. "
                                                + bcEx);
                            }
                            isConversationAlive = true;
                        }
                    }
                } else {
                    // z.B. Einstieg mit panzeige.xhtml?posnr=<POSNR>
                    isConversationAlive = true;
                }
            } catch (final Exception e) {
                isConversationAlive = false;
                this.log.warn(new ExceptionHandler().getErrorMeldung(e, request));
            }
        }
        return isConversationAlive;
    }

    /**
     * @param conversation
     *            Conversation, deren Expiry verlaengert werden soll
     */
    @Override
    public void updateConversationTimeout(final Conversation conversation) {
        if ((conversation != null) && (conversation.getId() != null)) {
            final HttpServletRequest request = getHttpServletRequest(FacesContext.getCurrentInstance());
            updateConversationTimeout(conversation, request, new ExceptionHandler().getRequestString(request));
        } else {
            this.log.debug("Conversation null");
        }
    }

    /**
     * @param conversation
     *            Conversation, deren Expiry verlaengert werden soll
     * @param request
     *            HttpRequest Objekt
     */
    public void updateConversationTimeout(final Conversation conversation, final HttpServletRequest request,
            final String requestString) {
        if ((conversation != null) && (request != null)) {
            updateConversationTimeout(conversation, requestString, request.getSession(false));
        } else {
            this.log.debug("Conversation oder Request null");
        }
    }

    /**
     * @param conversation
     *            Conversation, deren Expiry verlaengert werden soll
     * @param request
     *            HttpServletRequest Objekt
     * @param session
     *            HttpSession Objekt
     */
    public void updateConversationTimeout(final Conversation conversation, final String request,
            final HttpSession session) {
        if ((conversation != null) && (request != null) && (session != null)) {
            final long jetzt = System.currentTimeMillis();
            final long timeSinceLastAccess = jetzt - session.getLastAccessedTime();
            final long maxInactMs = 1000 * session.getMaxInactiveInterval();
            if (timeSinceLastAccess < maxInactMs) {
                // this.log.debug("Zeit seit letztem Session-Access (ms): "
                // + timeSinceLastAccess + " max inaktiv in ms = "
                // + maxInactMs + " => Session noch nicht ausgelaufen");
                conversation.setTimeout(1000 * session.getMaxInactiveInterval());
                updateSessionAccessTime(request, session);
            } else {
                this.log.debug("Session ausgelaufen");
            }
        } else {
            this.log.debug("Conversation, Request oder Session null");
        }
    }

    private void updateSessionAccessTime(final String request, final HttpSession session) {
        try {
            // gefunden in
            // http://www.coderanch.com/t/543330/JSP/java/Programmatically-simulate-user-activity-server
            // this.log.debug("Session lastAccessedTime vorher: "
            // + session.getLastAccessedTime());
            final Field sessionField = session.getClass().getDeclaredField("session");
            sessionField.setAccessible(true);
            final Object o = sessionField.get(session);
            if (o instanceof HttpSession) {
                final HttpSession impSession = (HttpSession) o;
                impSession.getClass().getMethod("access").invoke(impSession);
            } else {
                // EAP7
                // https://github.com/undertow-io/undertow/blob/1.3.x/core/src/main/java/io/undertow/server/session/InMemorySessionManager.java#L458
                session.setMaxInactiveInterval(session.getMaxInactiveInterval());
                // this.log.debug("Session lastAccessedTime nachher: "
                // + session.getLastAccessedTime());
            }
        } catch (final Exception e) {
            this.log.error(new ExceptionHandler().getErrorMeldung(e, request));
        }
    }

       private String verarbeiteKatalogWert(final String anzeigeKatalogWert, final FormTarifKatalog formTarifkatalog,
            final LogikTarifkatalog logikTarifkatalog, final TypConverter typConverter, String katalogTyp,
            final Bearbeiter bearbeiter) {
        if ("1".equalsIgnoreCase(anzeigeKatalogWert)) {
            // 1 Alle Bundesweiten und eigener Reginaltarif wird
            // auf 11 gemappt
            if (formTarifkatalog != null) {
                formTarifkatalog.setFormtarifkatalog_katalogtyp("11");
            }
            katalogTyp = "11";
        } else if ("3".equalsIgnoreCase(anzeigeKatalogWert)) {
            // 3 Nur bundesweite Tarife wird auf 12 gemappt
            if (formTarifkatalog != null) {
                formTarifkatalog.setFormtarifkatalog_katalogtyp("12");
            }
            katalogTyp = "12";
        } else if ("2".equalsIgnoreCase(anzeigeKatalogWert)) {
            // 2 = Gesamtkatalog
            if (formTarifkatalog != null) {
                formTarifkatalog.setFormtarifkatalog_katalogtyp(
                        typConverter.getNotNullString(Katalog.GESAMTKATALOG.getKatalog_code()));
            }
            katalogTyp = "0";
        } else {
            final String ldst = typConverter.getNullString(logikTarifkatalog.getWertBearbeiterLandesstelle(bearbeiter));
            if (ldst != null) {
                if (formTarifkatalog != null) {
                    formTarifkatalog.setFormtarifkatalog_katalogtyp(ldst);
                }
                katalogTyp = ldst;
            }
        }
        return katalogTyp;
    }

    private void ermittleWertVonAbschnitteAusgewaehlt(final FormTarifKatalog formTarifkatalog,
            final String anzeigeDetailansicht) {
        if (formTarifkatalog != null) {
            final Boolean sindAbschnitteAusgewaehlt = Boolean.valueOf("1".equals(anzeigeDetailansicht));
            if (formTarifkatalog.getFormtarifkatalog_abschnitte_ausgewaehlt() == null) {
                formTarifkatalog.setFormtarifkatalog_abschnitte_ausgewaehlt(sindAbschnitteAusgewaehlt);
                this.log.debug("anzeigeDetailansicht=" + anzeigeDetailansicht
                        + ". formtarifkatalog_abschnitte_ausgewaehlt = " + sindAbschnitteAusgewaehlt);
            } else {
                this.log.debug(
                        "anzeigeDetailansicht=" + anzeigeDetailansicht + ". formtarifkatalog_abschnitte_ausgewaehlt = "
                                + formTarifkatalog.getFormtarifkatalog_abschnitte_ausgewaehlt());
            }
        }
    }

        private String ermittleAnzeigeKatalogWert(final LogikTarifkatalog logikTarifkatalog, final Bearbeiter bearbeiter,
            final String httpRequest) {
        // Lade ProfilWerte laut Profil
        String anzeigeKatalogWert = null;
        final ProfilWert profilWertKatalog = logikTarifkatalog.getProfilWertAusProfilTyp(bearbeiter,
                Constants_Typ.PROFIL_TYP_TARIFKATALOG_ANZEIGE_KATALOG, httpRequest);
        if (profilWertKatalog != null) {
            anzeigeKatalogWert = profilWertKatalog.getWertText();
            this.log.debug("anzeigeKatalogWert=" + anzeigeKatalogWert);
        } else {
            this.log.debug("profilWertKatalog=null");
        }
        return anzeigeKatalogWert;
    }

    private String ermittleAnzeigeDetailansicht(final LogikTarifkatalog logikTarifkatalog, final Bearbeiter bearbeiter,
            final HttpSession session, final String httpRequest) {
        String anzeigeDetailansicht = "1";
        final ProfilWert profilWertDetailansicht = logikTarifkatalog.getProfilWertAusProfilTyp(bearbeiter,
                Constants_Typ.PROFIL_TYP_TARIFKATALOG_DETAILANSICHT, httpRequest);
        if (profilWertDetailansicht != null) {
            this.log.debug("profilWertDetailansicht=" + profilWertDetailansicht);
            anzeigeDetailansicht = profilWertDetailansicht.getWertText();
        }
        if (session != null) {
            session.setAttribute("detailansicht", anzeigeDetailansicht);
        }
        return anzeigeDetailansicht;
    }

    /**
     * Entfernt doppelte/mehrfache Elemente aus einer Liste
     */
    public <T> List<T> removeDuplicatesFromList(final List<T> list) {
        if (!isEmptyCollection(list)) {
            final Set<T> set = new HashSet<>(list);
            list.clear();
            list.addAll(set);
        }
        return list;
    }

    /**
     * Setze Fehlermeldung in die Liste
     */
    public void setzeFehlermeldung(final String msgKey, final ResourceBundle resourceBundle,
            final List<String> fehlermeldungen, final Object... object) {
        if ((msgKey != null) && (fehlermeldungen != null) && (resourceBundle != null)) {
            final String msgValue = resourceBundle.getString(msgKey);
            if ((object != null) && (object.length > 0)) {
                final MessageFormat mf = new MessageFormat(msgValue);
                fehlermeldungen.add(mf.format(object));
            } else {
                fehlermeldungen.add(msgValue);
            }
            this.log.debug("Zu fehlermeldungen hinzugefuegt : " + msgValue);
        }
    }

    /**
     * befuellt ein JSF SelectItem-DropDown menue
     * mit den Fixwerten der uebergebenen List<SelectPseudoBean>
     */
    public List<SelectItem> getSelectItemList(final List<SelectPseudoBean> fixwert_list) {
        final List<SelectItem> list = new ArrayList<>();
        if (fixwert_list != null) {
            for (final SelectPseudoBean fixwert_mapitems : fixwert_list) {
                final String fixwert_key = fixwert_mapitems.getKey();
                final String fixwert_value = fixwert_mapitems.getValue();
                list.add(new SelectItem(fixwert_key, fixwert_value));
            }
        }
        return list;
    }
}
