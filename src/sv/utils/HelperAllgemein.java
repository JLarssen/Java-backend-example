package sv.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sv.utils.beans.FixwertDTO;
import sv.utils.beans.KonfigurationAllgemein;
import sv.utils.beans.SelectPseudoBean;
import sv.utils.beans.xml.allgemeinblock.Titel;
import sv.utils.beans.xml.allgemeinblock.TitelListe;
import sv.utils.beans.xml.allgemeinblock.TitelTyp;
import constants.Constants_Allgemein;
import constants.Constants_Datumsformat;
import constants.Constants_Fehlercode;
import constants.Constants_Fixwerte;
import constants.Constants_Request;
import constants.Constants_Session;
import constants.Constants_Sicherheitsklasse;
import exceptions.svWebServiceException;
import sv.utils.logic.LogikSoap;
import sv.utils.schnittstellen.BearbeiterAbfrage;
import sv.utils.soap.beve.Bearbeiter;
import sv.utils.soap.beve.Organisationeinheit;
import sv.utils.soap.beve.ProfilWert;
import sv.utils.soap.commons.AnfragendeStelle;
import sv.utils.soap.dsl.v2.typen.schreibedatenschutzlog.Systemmodus;
import sv.utils.soap.standesdaten.typen.ZpvTitel;

/**
 * Hilfsklasse mit allen moeglichen (brauchbaren) Methoden
 *
 * @author SV-Benutzer
 */
public class HelperAllgemein {
    protected Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Default-Konstruktor
     */
    public HelperAllgemein() {
        super();
    }

    /**
     * ist der uebergebene String null oder leer?
     *
     * @return true, falls der uebergebene String null oder leer ist, sonst
     *         false
     */
    public boolean isEmptyString(final String string) {
        return ((string == null) || (string.trim().length() < 1));
    }

    /**
     * ist die uebergebene Map null oder leer?
     *
     * @return true, falls die uebergebene Map null oder leer ist, sonst false
     */
    public boolean isEmptyMap(final Map<?, ?> map) {
        return ((map == null) || (map.isEmpty()));
    }

    /**
     *
     * prueft ob in eine Liste zumindest 1 antrag vorhanden ist
     *
     * @return boolean
     */
    public <T> boolean isEmptyList(final List<T> list) {
        boolean antwort = true;
        if (!isEmptyCollection(list)) {
            final Iterator<T> it = list.iterator();
            if (it != null) {
                while (it.hasNext() && antwort) {
                    final T next = it.next();
                    if (next != null) {
                        if (next instanceof String) {
                            antwort = isEmptyString((String) next);
                        } else {
                            antwort = false;
                        }
                    }
                }
            }
        }
        return (antwort);
    }

    /**
     * ist die uebergebene Set null oder leer?
     *
     * @return true, falls die uebergebene Set null oder leer ist, sonst false
     */
    public boolean isEmptySet(final Set<?> set) {
        return (isEmptyCollection(set));
    }

    /**
     * ist die uebergebene Collection null oder leer?
     *
     * @return true, falls die uebergebene Collection null oder leer ist, sonst
     *         false
     */
    public boolean isEmptyCollection(final Collection<?> collection) {
        return ((collection == null) || (collection.isEmpty()));
    }

    /**
     * ist das uebergebene Array null oder leer?
     *
     * @return true, falls das uebergebene Array null oder leer ist, sonst false
     */
    public <T> boolean isEmptyArray(final T[] array) {
        return ((array == null) || (array.length < 1));
    }

    /**
     * gibt den aktuellen Klickstream zurueck
     */
    public String getKlickStream(final HttpSession session) {
        String rueckgabe = null;
        if (session != null) {
            rueckgabe = (String) session.getAttribute(Constants_Allgemein.KLICKSTREAM);
        }
        return (rueckgabe);
    }

    /**
     * dient zur Verfolgung von Forwards (Action- und JSP-Zugriffe) durch die
     * Applikation
     */
    public HttpSession setKlickStream(final HttpSession session, final String anhang) {
        if (session != null) {
            session.setAttribute(Constants_Allgemein.KLICKSTREAM, getKlickStream(session) + ";" + anhang);
        }
        return (session);
    }

    /**
     * entfernt den Klickstream aus der Session
     */
    public HttpSession removeKlickStream(final HttpSession session) {
        if (session != null) {
            session.removeAttribute(Constants_Allgemein.KLICKSTREAM);
        }
        return (session);
    }

    /**
     * ueberprueft, ob die eingegebene VSNR vollstaendig den Regeln einer VSNR
     * gehorcht (Laenge: 10 Stellen, Pruefziffernberechnung, etc.); VSNRs, die
     * mit 0000 beginnen, werden bis auf die Laenge nicht weiter ueberprueft
     *
     * @param vsnr
     *            zu pruefende VSNR
     * @return Fehlercodes aus der Klasse
     *         <code>sv.utils.constants.Constants_Fehlercode</code>;
     *         sollten keine Fehler vorkommen, wird eine leere Liste
     *         zurueckgegeben
     */
    public List<Integer> checkVSNR(final String vsnr) {
        final List<Integer> fehlercodes = new LinkedList<>();
        // Anzahl der Stellen der VSNR
        final int VSNR_LAENGE = 10;
        // Konstanten zur Berechnung der Pruefziffer
        final int[] konstanten = { 3, 7, 9, 0, 5, 8, 4, 2, 1, 6 };
        if ((vsnr != null) && (vsnr.length() == VSNR_LAENGE)) {
            this.log.debug("VSNR ueberpruefen: " + vsnr);
            // Aufteilen auf LLL, P, TT, MM, JJ
            final String strLll = vsnr.substring(0, 3);
            final String strP = vsnr.substring(3, 4);
            final String strTag = vsnr.substring(4, 6);
            final String strMonat = vsnr.substring(6, 8);
            final String strJahr = vsnr.substring(8, 10);
            if ((strLll != null) && (strLll.length() == 3) && (strP != null) && (strP.length() == 1) && (strTag != null)
                    && (strTag.length() == 2) && (strMonat != null) && (strMonat.length() == 2) && (strJahr != null)
                    && (strJahr.length() == 2)) {
                try {
                    // Umwandeln der Bestandteile der VSNR in int
                    final int lll = Integer.parseInt(strLll);
                    final int p = Integer.parseInt(strP);
                    // VSNR im Format 0000NNNNNN wird nicht weiter ueberprueft,
                    // sondern gilt als gueltig
                    if (!((lll == 0) && (p == 0))) {
                        final int tag = Integer.parseInt(strTag);
                        final int monat = Integer.parseInt(strMonat);
                        final int jahr = Integer.parseInt(strJahr);
                        // Umwandeln der VSNR in ein char-Array zur
                        // zeichenweisen
                        // Multiplikation mit den Konstanten
                        final char[] chVsnr = vsnr.toCharArray();
                        // VSNR hat N Stellen
                        if (chVsnr.length == VSNR_LAENGE) {
                            final int[] intVsnr = new int[VSNR_LAENGE];
                            final int[] multiplikation = new int[VSNR_LAENGE];
                            int summe = 0;
                            // Errechnen der Summe der Produkte
                            for (int i = 0; i < VSNR_LAENGE; i++) {
                                intVsnr[i] = Character.getNumericValue(chVsnr[i]);
                                multiplikation[i] = konstanten[i] * intVsnr[i];
                                summe += multiplikation[i];
                            }
                            // Pruefen der Pruefziffer
                            final int pruef = summe % 11;
                            if (pruef != p) {
                                fehlercodes.add(Constants_Fehlercode.FEHLERCODE_VSNR_PRUEFZIFFER);
                            }
                        }
                        // VSNR hat nicht die richtige Laenge
                        else {
                            fehlercodes.add(Constants_Fehlercode.FEHLERCODE_VSNR_LAENGE);
                        }
                        // ungueltige Werte fuer TT
                        if ((tag < 1) || (tag > 31)) {
                            fehlercodes.add(Constants_Fehlercode.FEHLERCODE_VSNR_TAG);
                        }
                        switch (monat) {
                            // Jaenner, Maerz, Mai, Juli, August, Oktober,
                            // Dezember,
                            // 13,
                            // 14, 15
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                            case 8:
                            case 10:
                            case 12:
                            case 13:
                            case 14:
                            case 15: {
                                break;
                            }
                            // April, Juni, September, November haben 30
                            // Tage
                            case 4:
                            case 6:
                            case 9:
                            case 11: {
                                if (tag > 30) {
                                    fehlercodes.add(Constants_Fehlercode.FEHLERCODE_VSNR_TAG_IM_MONAT);
                                }
                                break;
                            }
                            // Februar hat 28 oder 29 Tage
                            case 2: {
                                if (tag > 29) {
                                    fehlercodes.add(Constants_Fehlercode.FEHLERCODE_VSNR_TAG_IM_MONAT);
                                } else if (tag == 29) {
                                    if ((jahr % 4) != 0) {
                                        fehlercodes.add(Constants_Fehlercode.FEHLERCODE_VSNR_TAG_IM_MONAT);
                                    }
                                }
                                break;
                            }
                            default: {
                                fehlercodes.add(Constants_Fehlercode.FEHLERCODE_VSNR_MONAT);
                                break;
                            }
                        }
                    }
                }
                // VSNR besteht nicht nur aus Ziffern
                catch (final Exception e) {
                    fehlercodes.add(Constants_Fehlercode.FEHLERCODE_VSNR_UNGUELTIGES_ZEICHEN);
                    final StringBuffer fehler = new StringBuffer();
                    fehler.append("strLll: '").append(strLll);
                    fehler.append("'; strP: '").append(strP);
                    fehler.append("'; strTag: '").append(strTag);
                    fehler.append("'; strMonat: '").append(strMonat);
                    fehler.append("'; strJahr: '").append(strJahr).append("'");
                    this.log.error("Fehler (" + fehler.toString() + "): " + e.toString());
                }
            }
            // VSNR hat eine falsche Anzahl an Stellen
            else {
                fehlercodes.add(Constants_Fehlercode.FEHLERCODE_VSNR_LAENGE);
            }
        }
        // VSNR hat eine falsche Anzahl an Stellen
        else {
            fehlercodes.add(Constants_Fehlercode.FEHLERCODE_VSNR_LAENGE);
        }
        return (fehlercodes);
    }

    /**
     * prueft ob die Vsnr fiktiv ist und ob ein Leerzeichen, falls ueberhaupt
     * vorhanden, an der 5. Stelle steht.
     *
     * @param vsnr
     *            zu pruefende VSNR
     * @param vsnrFormatiertErlaubt
     *            true: an der 5. Stelle darf ein Leerzeichen stehen; false: die
     *            VSNR darf keine Leerzeichen enthalten
     *
     * @return boolean true: es handelt sich um eine gueltige VSNR, sonst false
     */
    public boolean isGueltigeVsnr(String vsnr, final boolean vsnrFormatiertErlaubt) {
        boolean isGueltig = false;
        vsnr = new TypConverter().getNullString(vsnr);
        if (!isEmptyString(vsnr)) {
            final int laenge = vsnr.length();
            if ((vsnrFormatiertErlaubt) && (laenge == 11)) {
                isGueltig = vsnr.matches("^(\\d{4}\\s{1}\\d{6})$");
            } else if (laenge == 10) {
                isGueltig = vsnr.matches("^(\\d{10})$");
            } else {
                isGueltig = false;
            }
        }
        return (isGueltig);
    }

    /**
     * esetzt im uebergebenen String Komma durch Punkt, falls vorhanden
     *
     * @return String, der statt Beistrichen Punkte enthaelt, oder Leerstring,
     *         wenn der uebergebene String null oder leer war
     */
    public String kommaZuPunkt(final String string) {
        String s = "";
        if (!isEmptyString(string)) {
            s = string.replace(',', '.');
        }
        return (s);
    }

    /**
     * holt aus der uebergebenen <code>java.util.Map</code> den Wert zum
     * uebergebenen Key
     *
     * @param map
     *            <code>java.util.Map</code>, in der gesucht werden soll
     * @param key
     *            Schluessel, zu dem der Wert gesucht wird
     * @return gefundener Wert oder null
     */
    @Nullable
    public <K, V> V getValue(final Map<K, V> map, K key) {
        @Nullable
        V value = null;
        if ((map != null) && (key != null)) {
            final TypConverter typConverter = new TypConverter();
            if (key instanceof String) {
                key = this.<K> getGenericObject(typConverter.getNullString((String) key));
            }
            if (key != null) {
                value = map.get(key);
                if (value instanceof String) {
                    value = this.<V> getGenericObject(typConverter.getNullString((String) value));
                }
            }
        }
        return (value);
    }

    /**
     * macht aus der uebergebenen Map einen formatierten String
     *
     * @param map
     *            Map, die in einen String umgewandelt werden soll
     * @param map_beschreibung
     *            Bezeichnung fuer jedes einzelne Element der Map
     * @return uebergebene Map als String
     */
    public <K, V> String mapToString(final Map<K, V> map, final String map_beschreibung) {
        final StringBuffer sb = new StringBuffer();
        if (map != null) {
            final TypConverter typConverter = new TypConverter();
            final Set<Map.Entry<K, V>> set = new TreeMap<>(map).entrySet();
            if (set != null) {
                final Iterator<Map.Entry<K, V>> it = set.iterator();
                if (it != null) {
                    int i = 1;
                    sb.append(Constants_Allgemein.ENDL);
                    while (it.hasNext()) {
                        final Map.Entry<K, V> next = it.next();
                        if (next != null) {
                            final K key = next.getKey();
                            final V value = next.getValue();
                            // Passwoerter duerfen nicht im Klartext ausgegeben werden
                            String valueString = null;
                            if ((key instanceof String) && (value instanceof String)) {
                                String keyString = typConverter.getNullString((String) key);
                                valueString = typConverter.getNullString((String) value);
                                if ((!isEmptyString(keyString)) && (!isEmptyString(valueString))) {
                                    keyString = keyString.toLowerCase();
                                    if ((keyString.contains("pass")) || (keyString.contains("kennwort"))) {
                                        valueString = String.valueOf(valueString.length());
                                    }
                                }
                            }
                            sb.append(i).append(". ").append(map_beschreibung).append(": '").append(key).append("' // ")
                                    .append((valueString != null) ? valueString : ("'" + value + "'"))
                                    .append(Constants_Allgemein.ENDL);
                        }
                        i++;
                    }
                }
            }
        }
        return (sb.toString());
    }

    /**
     * macht aus der uebergebenen Collection einen formatierten String
     *
     * @param collection
     *            Collection, die in einen String umgewandelt werden soll
     * @param collection_beschreibung
     *            Bezeichnung fuer jedes einzelne Element der Collection
     * @return uebergebene Collection als String
     */
    public <T> String listToString(final Collection<T> collection, final String collection_beschreibung) {
        final StringBuffer sb = new StringBuffer();
        if (collection != null) {
            int i = 1;
            sb.append(Constants_Allgemein.ENDL);
            for (final T next : collection) {
                if (next != null) {
                    sb.append(i).append(". ").append(collection_beschreibung).append(": '").append(next).append("'")
                            .append(Constants_Allgemein.ENDL);
                }
                i++;
            }
        }
        return (sb.toString());
    }

    /**
     * macht aus der uebergebenen Collection einen formatierten String
     *
     * @param array
     *            Array, das in einen String umgewandelt werden soll
     * @param array_beschreibung
     *            Bezeichnung fuer jedes einzelne Element des Array
     * @return uebergebene Collection als String
     */
    public <T> String arrayToString(final T[] array, final String array_beschreibung) {
        final StringBuffer sb = new StringBuffer();
        if (array != null) {
            int i = 1;
            sb.append(Constants_Allgemein.ENDL);
            for (final T next : array) {
                if (next != null) {
                    sb.append(i).append(". ").append(array_beschreibung).append(": '").append(next).append("'")
                            .append(Constants_Allgemein.ENDL);
                }
                i++;
            }
        }
        return (sb.toString());
    }

    /**
     * erzeugt aus den Elementen der uebergebenen Collection einen String, der
     * direkt als options im HTML-Code in ein Select eingehaengt werden kann
     *
     * @param collection
     *            Collection, aus der HTML-Options erzeugt werden sollen
     * @return String in der Form
     *         <option value="Collection-Eintrag">Collection-Eintrag</option>,
     *         hintereinander fuer alle Collection-Eintraege
     */
    public <T> String collectionToHtmlOptions(final Collection<T> collection) {
        final StringBuffer sb = new StringBuffer();
        if (collection != null) {
            final Iterator<T> it = collection.iterator();
            if (it != null) {
                while (it.hasNext()) {
                    final T next = it.next();
                    if (next != null) {
                        sb.append("<option value=\"").append(next + "\">").append(next).append("</option>")
                                .append(Constants_Allgemein.ENDL);
                    }
                }
            }
        }
        return (sb.toString());
    }

    /**
     * erzeugt aus den Elementen der uebergebenen Map einen String, der direkt
     * als options im HTML-Code in ein Select eingehaengt werden kann
     *
     * @param map
     *            Map, aus der HTML-Options erzeugt werden sollen
     * @return String in der Form <option value="Key eines Map-Eintrages">Value
     *         eines Map-Eintrages</option>, hintereinander fuer alle
     *         Map-Eintraege
     */
    public <K, V> String mapToHtmlOptions(final Map<K, V> map) {
        final StringBuffer sb = new StringBuffer();
        if (map != null) {
            final Set<Map.Entry<K, V>> set = map.entrySet();
            if (set != null) {
                final Iterator<Map.Entry<K, V>> it = set.iterator();
                if (it != null) {
                    while (it.hasNext()) {
                        final Map.Entry<K, V> next = it.next();
                        if (next != null) {
                            sb.append("<option value=\"").append(next.getKey() + "\">").append(next.getValue())
                                    .append("</option>").append(Constants_Allgemein.ENDL);
                        }
                    }
                }
            }
        }
        return (sb.toString());
    }

    /**
     * erzeugt aus den Elementen der uebergebenen Map einen String, der direkt
     * als options im HTML-Code in ein Select eingehaengt werden kann; Key und
     * Value werden dabei wechselseitig vertauscht
     *
     * @param map
     *            Map, aus der HTML-Options erzeugt werden sollen
     * @return String in der Form <option value="Value eines Map-Eintrages">Key
     *         eines Map-Eintrages</option>, hintereinander fuer alle
     *         Map-Eintraege
     */
    public <K, V> String mapToHtmlOptionsKeyValueVertauscht(final Map<K, V> map) {
        final StringBuffer sb = new StringBuffer();
        if (map != null) {
            final Set<Map.Entry<K, V>> set = map.entrySet();
            if (set != null) {
                final Iterator<Map.Entry<K, V>> it = set.iterator();
                if (it != null) {
                    while (it.hasNext()) {
                        final Map.Entry<K, V> next = it.next();
                        if (next != null) {
                            sb.append("<option value=\"").append(next.getValue() + "\">").append(next.getKey())
                                    .append("</option>").append(Constants_Allgemein.ENDL);
                        }
                    }
                }
            }
        }
        return (sb.toString());
    }

    /**
     * erzeugt aus den Elementen der uebergebenen Map aufsteigend sortiert nach
     * der Keyspalte einen String, der direkt als options im HTML-Code in ein
     * Select eingehaengt werden kann
     *
     * @param map
     *            Map, aus der HTML-Options erzeugt werden sollen
     *
     * @return String in der Form <option value="Key eines Map-Eintrages">Value
     *         eines Map-Eintrages</option>, hintereinander fuer alle
     *         Map-Eintraege
     */
    public <K extends Comparable<K>, V> String mapToHtmlOptionsSortiertNachKey(final Map<K, V> map) {
        final StringBuffer sb = new StringBuffer();
        if (map != null) {
            sb.append(mapToHtmlOptions(new TreeMap<>(map)));
        }
        return (sb.toString());
    }

    /**
     * erzeugt aus den Elementen der uebergebenen Map einen String, der direkt
     * als options im HTML-Code in ein Select eingehaengt werden kann
     *
     * @param map
     *            Map, aus der HTML-Options erzeugt werden sollen
     * @return String in der Form <option value="Key eines Map-Eintrages">Key
     *         eines Map-Eintrages</option>, hintereinander fuer alle
     *         Map-Eintraege
     */
    public <K, V> String mapToHtmlOptionsMitKeyInAnzeige(final Map<K, V> map) {
        final StringBuffer sb = new StringBuffer();
        if (map != null) {
            final Set<Map.Entry<K, V>> set = map.entrySet();
            if (set != null) {
                final Iterator<Map.Entry<K, V>> it = set.iterator();
                if (it != null) {
                    while (it.hasNext()) {
                        final Map.Entry<K, V> next = it.next();
                        if (next != null) {
                            sb.append("<option value=\"").append(next.getKey() + "\">").append(next.getKey())
                                    .append("</option>").append(Constants_Allgemein.ENDL);
                        }
                    }
                }
            }
        }
        return (sb.toString());
    }

    /**
     * erzeugt aus den Elementen der uebergebenen Map aufsteigend sortiert nach
     * der Keyspalte einen String, der direkt als options im HTML-Code in ein
     * Select eingehaengt werden kann
     *
     * @param map
     *            Map, aus der HTML-Options erzeugt werden sollen
     * @return String in der Form <option value="Key eines Map-Eintrages">Key
     *         eines Map-Eintrages</option>, hintereinander fuer alle
     *         Map-Eintraege
     */
    public <K extends Comparable<K>, V> String mapToHtmlOptionsMitKeyInAnzeigeSortiertNachKey(final Map<K, V> map) {
        final StringBuffer sb = new StringBuffer();
        if (map != null) {
            sb.append(mapToHtmlOptionsMitKeyInAnzeige(new TreeMap<>(map)));
        }
        return (sb.toString());
    }

    /**
     * liefert aus einer List&lt;FixwertDTO&gt; eine
     * List&lt;SelectPseudoBean&lt;key, value&gt;&gt; zurueck.<br>
     * <li>mit_code
     * <ul>
     * <li>true -> SelectPseudoBean&lt;fixwert_code, value&gt;</li>
     * <li>false -> SelectPseudoBean&lt;fixwert_ktxt, value&gt;</li>
     * </ul>
     * </li>
     * <li>text
     * <ul>
     * <li>1 => SelectPseudoBean&lt;key, fixwert_ltxt&gt;</li>
     * <li>2 => SelectPseudoBean&lt;key, fixwert_ztxt&gt;</li>
     * <li>3 => SelectPseudoBean&lt;key, fixwert_code&gt;</li>
     * <li>sonst => SelectPseudoBean&lt;key, fixwert_ktxt&gt;</li>
     * </ul>
     * </li>
     *
     * @return List&lt;SelectPseudoBean&gt;
     */
    public List<SelectPseudoBean> getSelectPseudoBeanList(final List<FixwertDTO> fixwertDTOList, final boolean mit_code,
            final int text) {
        List<SelectPseudoBean> list = null;
        if (!isEmptyCollection(fixwertDTOList)) {
            final Iterator<FixwertDTO> it = fixwertDTOList.iterator();
            if (it != null) {
                list = new ArrayList<>();
                while (it.hasNext()) {
                    final FixwertDTO fixwertDTO = it.next();
                    if (fixwertDTO != null) {
                        final SelectPseudoBean selectPseudoBean = new SelectPseudoBean();
                        if (mit_code) {
                            if (text == 1) {
                                selectPseudoBean.setKey(fixwertDTO.getFixwert_code());
                                selectPseudoBean.setValue(fixwertDTO.getFixwert_ltxt());
                            } else if (text == 2) {
                                selectPseudoBean.setKey(fixwertDTO.getFixwert_code());
                                selectPseudoBean.setValue(fixwertDTO.getFixwert_ztxt());
                            } else if (text == 3) {
                                selectPseudoBean.setKey(fixwertDTO.getFixwert_code());
                                selectPseudoBean.setValue(fixwertDTO.getFixwert_code());
                            } else {
                                selectPseudoBean.setKey(fixwertDTO.getFixwert_code());
                                selectPseudoBean.setValue(fixwertDTO.getFixwert_ktxt());
                            }
                        } else {
                            if (text == 1) {
                                selectPseudoBean.setKey(fixwertDTO.getFixwert_ktxt());
                                selectPseudoBean.setValue(fixwertDTO.getFixwert_ltxt());
                            } else if (text == 2) {
                                selectPseudoBean.setKey(fixwertDTO.getFixwert_ktxt());
                                selectPseudoBean.setValue(fixwertDTO.getFixwert_ztxt());
                            } else if (text == 3) {
                                selectPseudoBean.setKey(fixwertDTO.getFixwert_ktxt());
                                selectPseudoBean.setValue(fixwertDTO.getFixwert_code());
                            } else {
                                selectPseudoBean.setKey(fixwertDTO.getFixwert_ktxt());
                                selectPseudoBean.setValue(fixwertDTO.getFixwert_ktxt());
                            }
                        }
                        list.add(selectPseudoBean);
                    }
                }
            }
        }
        return (list);
    }

    /**
     * erzeugt aus den Elementen der uebergebenen Map einen String, der direkt
     * im HTML-Code als table eingehaengt werden kann
     *
     * @param map
     *            Map, aus der eine HTML-Tabelle erzeugt werden soll
     *
     * @return String in der Form
     *         <table border=1>
     *         <tr>
     *         <td>Key eines Map-Eintrages</td>
     *         </tr>
     *         <tr>
     *         <td>Value eines Map-Eintrages</td>
     *         </tr>
     *         , hintereinander fuer alle Map-Eintraege
     *         </table>
     */
    public <K, V> String mapToHtmlTableString(final Map<K, V> map) {
        return (mapToHtmlTableString(map, 1, null, null, null, null, null));
    }

    /**
     * erzeugt aus den Elementen der uebergebenen Map einen String, der direkt
     * im HTML-Code als table eingehaengt werden kann
     *
     * @param map
     *            Map, aus der eine HTML-Tabelle erzeugt werden sollen
     * @param border
     *            Wert fuer border-Attribut der table
     * @param table_attribute
     *            String mit beliebigen weitere Attributen der Table, z.B.
     *            "class=\"Formularbaustein\""
     * @param ueberschrift_key
     *            optional: Ueberschrift fuer Key-Spalte
     * @param ueberschrift_value
     *            optional: Ueberschrift fuer Value-Spalte
     * @param width_key
     *            optional: Befuellung fuer width-Attribut der Key-Spalte
     * @param width_value
     *            optional: Befuellung fuer width-Attribut der Value-Spalte
     *
     * @return String in der Form <table border=1 {table_attribute}> [
     *         <tr>
     *         <th>ueberschrift_key</th>
     *         <th>ueberschrift_value</th>
     *         </tr>
     *         ] {
     *         <tr>
     *         <td {width_key}>Key eines Map-Eintrages</td>
     *         </tr>
     *         <tr {width_value}>
     *         <td>Value eines Map-Eintrages</td>
     *         </tr>
     *         </table>
     */
    public <K, V> String mapToHtmlTableString(final Map<K, V> map, final int border, final String table_attribute,
            final String ueberschrift_key, final String ueberschrift_value, final String width_key,
            final String width_value) {
        final StringBuffer sb = new StringBuffer();
        if (map != null) {
            final Set<Map.Entry<K, V>> set = map.entrySet();
            final TypConverter typConverter = new TypConverter();
            if (set != null) {
                sb.append("<table border=" + border + " " + typConverter.getNotNullString(table_attribute)
                        + ">");
                if ((!isEmptyString(ueberschrift_key)) || (!isEmptyString(ueberschrift_value))) {
                    sb.append("<tr>");
                    sb.append("<th>").append(typConverter.getNotNullString(ueberschrift_key)).append("</th>");
                    sb.append("<th>").append(typConverter.getNotNullString(ueberschrift_value))
                            .append("</th>");
                    sb.append("</tr>");
                }
                final Iterator<Map.Entry<K, V>> it = set.iterator();
                if (it != null) {
                    while (it.hasNext()) {
                        final Map.Entry<K, V> next = it.next();
                        if (next != null) {
                            sb.append("<tr>");
                            sb.append("<td");
                            if (!isEmptyString(width_key)) {
                                sb.append(" width=" + typConverter.getNotNullString(width_key) + " ");
                            }
                            sb.append(">").append(next.getKey()).append("</td>");
                            sb.append("<td");
                            if (!isEmptyString(width_value)) {
                                sb.append(" width=" + typConverter.getNotNullString(width_value) + " ");
                            }
                            sb.append(">").append(next.getValue()).append("</td>");
                            sb.append("</tr>");
                        }
                    }
                }
                sb.append("</table>");
            }
        }
        return (sb.toString());
    }

    /**
     * gibt die uebergebenen Properties mit log.info aus
     */
    public void propertiesAnzeigen(final Properties props) {
        int size = 0;
        if (props != null) {
            size = props.size();
        }
        this.log.info(size + " Properties gefunden" + ((size > 0) ? (":") : ("!")));
        if (props != null) {
            final Set<Object> set = new TreeSet<>(props.keySet());
            final Iterator<Object> it = set.iterator();
            if (it != null) {
                int i = 1;
                while (it.hasNext()) {
                    final String nextKey = (String) it.next();
                    if (nextKey != null) {
                        this.log.info(i + ". Property: '" + nextKey + "' = '" + props.getProperty(nextKey) + "'");
                    }
                    i++;
                }
            }
        }
    }

    /**
     * Strings aus der Liste werden in ein <code>String</code>-Array uebertragen
     *
     * @throws ClassCastException
     *             wird geworfen, wenn die Liste einen anderen Datentyp als
     *             <code>String</code> enthaelt
     */
    public String[] getArray(final List<String> list) throws ClassCastException {
        String[] array = null;
        if (list != null) {
            array = new String[list.size()];
            final Iterator<String> it = list.iterator();
            if (it != null) {
                int i = 0;
                while (it.hasNext()) {
                    array[i] = it.next();
                    i++;
                }
            }
        }
        return (array);
    }

    /**
     * traegt den uebergebenen String am uebergebenen Index im uebergebenen
     * Array ein, falls das Array null ist, wird vorher ein neues Array mit der
     * uebergebenen Groesse erzeugt
     *
     * @param array
     *            Array, in das der String eingetragen werden soll
     * @param size
     *            Groesse eines neu anzulegenden Arrays, falls als Array null
     *            uebergeben wurde
     * @param index
     *            an welcher Position (mit 0 beginnend) soll der String im Array
     *            eingetragen werden
     * @param wert
     *            String, der ins Array eingetragen werden soll
     * @param defaultWert
     *            wenn als Array null uebergeben und somit ein neues Array
     *            erzeugt wurde: dieser Wert soll in allen anderen Elementen des
     *            Arrays stehen
     *
     * @throws ArrayIndexOutOfBoundsException
     *             wenn der uebergebene Index nicht im Array enthalten ist
     *
     * @return Array, bei dem an der Stelle index der String wert steht
     */
    public String[] getStringArray(String[] array, final int size, final int index, final String wert,
            final String defaultWert) throws ArrayIndexOutOfBoundsException {
        if (array == null) {
            array = new String[size];
            for (int i = 0; i < size; i++) {
                array[i] = defaultWert;
            }
        }
        array[index] = wert;
        return (array);
    }

    /**
     * wenn der uebergebene String nicht die uebergebene Laenge hat, dann wird
     * entweder das uebergebene Fuellzeichen links so oft eingetragen, bis er
     * die uebergebene Laenge erreicht hat oder es werden links ueberschuessige
     * Zeichen weggeschnitten
     *
     * @param string
     *            String, der auf eine bestimmte Laenge gebracht werden soll,
     *            bei Uebergabe von null wird ein Leerstring verwendet
     * @param laenge
     *            gewuenschte Laenge des Strings
     * @param fuellzeichen
     *            String, mit dem ein zu kurzer String aufgefuellt werden soll,
     *            bei Uebergabe von null wird ein Leerstring verwendet
     *
     * @return String mit exakt der gewuenschten Laenge
     */
    public String linksFuellen(String string, final int laenge, String fuellzeichen) {
        final StringBuffer sb = new StringBuffer();
        if (string == null) {
            string = "";
        }
        if (fuellzeichen == null) {
            fuellzeichen = "";
        }
        final int string_laenge = string.length();
        if (string_laenge > laenge) {
            sb.append(string.substring(string_laenge - laenge, string_laenge));
        } else {
            for (int i = laenge; i > string_laenge; i--) {
                sb.append(fuellzeichen);
            }
            sb.append(string);
        }
        return (sb.toString());
    }

    /**
     * wenn der uebergebene String nicht die uebergebene Laenge hat, dann wird
     * entweder das uebergebene Fuellzeichen rechts so oft eingetragen, bis er
     * die uebergebene Laenge erreicht hat oder es werden rechts ueberschuessige
     * Zeichen weggeschnitten
     *
     * @param string
     *            String, der auf eine bestimmte Laenge gebracht werden soll,
     *            bei Uebergabe von null wird ein Leerstring verwendet
     * @param laenge
     *            gewuenschte Laenge des Strings
     * @param fuellzeichen
     *            String, mit dem ein zu kurzer String aufgefuellt werden soll,
     *            bei Uebergabe von null wird ein Leerstring verwendet
     *
     * @return String mit exakt der gewuenschten Laenge
     */
    public String rechtsFuellen(String string, final int laenge, String fuellzeichen) {
        final StringBuffer sb = new StringBuffer();
        if (string == null) {
            string = "";
        }
        if (fuellzeichen == null) {
            fuellzeichen = "";
        }
        final int string_laenge = string.length();
        if (string_laenge > laenge) {
            sb.append(string.substring(0, laenge));
        } else {
            sb.append(string);
            for (int i = laenge; i > string_laenge; i--) {
                sb.append(fuellzeichen);
            }
        }
        return (sb.toString());
    }

    /**
     * liest einen InputStream zeilenweise aus und haengt dabei an jede Zeile
     * ein beliebiges Zeichen an
     *
     * @param isr
     *            InputStream, der verarbeitet werden soll
     * @param postfix
     *            String, der am Ende jeder Zeile angehaengt wird; bei Uebergabe
     *            von null wird ein einzelnes Leerzeichen verwendet
     *
     * @return Inhalt der Datei als String, wobei jede Zeile von der naechsten
     *         nur durch den Inhalt von postfix getrennt wird (d.h. nicht
     *         automatisch durch ein Newline); Leerstring, wenn die Datei nicht
     *         verarbeitet werden konnte
     */
    private String getDateiAlsString(final InputStreamReader isr, String postfix, final String request) {
        final StringBuffer sb = new StringBuffer();
        try {
            if (isr != null) {
                final TypConverter typConverter = new TypConverter();
                if (postfix == null) {
                    postfix = " ";
                }
                try (final BufferedReader br = new BufferedReader(isr)) {
                    String zeile = null;
                    // lese Zeile fuer Zeile ein
                    while ((zeile = br.readLine()) != null) {
                        sb.append(typConverter.getNotNullString(zeile)).append(postfix);
                    }
                    br.close();
                }
            }
        } catch (final Exception e) {
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (StringPropertyReplacer.replaceProperties(sb.toString()));
    }

    /**
     * liest einen InputStream zeilenweise aus und haengt dabei an jede Zeile
     * ein beliebiges Zeichen an
     *
     * @param is
     *            InputStream, der verarbeitet werden soll
     * @param postfix
     *            String, der am Ende jeder Zeile angehaengt wird; bei Uebergabe
     *            von null wird ein einzelnes Leerzeichen verwendet
     * @param encoding
     *            zu verwendendes Encoding, Pflichtfeld
     * @return Inhalt der Datei als String, wobei jede Zeile von der naechsten
     *         nur durch den Inhalt von postfix getrennt wird (d.h. nicht
     *         automatisch durch ein Newline); Leerstring, wenn die Datei nicht
     *         verarbeitet werden konnte
     */
    public String getDateiAlsString(final InputStream is, final String postfix, final String encoding,
            final String request) {
        String dateiinhalt = "";
        try {
            if ((is != null) && (!this.isEmptyString(encoding))) {
                try (final InputStreamReader isr = getInputStreamReaderAufDatei(is, encoding, request)) {
                    dateiinhalt = getDateiAlsString(isr, postfix, request);
                    isr.close();
                }
            }
        } catch (final Exception e) {
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (dateiinhalt);
    }

    /**
     * gibt einen InputStreamReader auf den uebergebenen, globalen Dateipfad
     * unter Beruecksichtigung des Encodings zurueck
     */
    public InputStreamReader getInputStreamReaderAufDatei(final String pfad, final String encoding,
            final String request) {
        InputStreamReader isr = null;
        try {
            // MD: Warning laesst sich nicht vermeiden, da der Datenstrom offen bleiben muss
            @SuppressWarnings("resource")
            final FileInputStream fis = new FileInputStream(pfad);
            isr = getInputStreamReaderAufDatei(fis, encoding, request);
        } catch (final Exception e) {
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (isr);
    }

    /**
     * gibt einen InputStreamReader auf den uebergebenen InputStream unter
     * Beruecksichtigung des Encodings zurueck
     */
    public InputStreamReader getInputStreamReaderAufDatei(final InputStream is, String encoding, final String request) {
        InputStreamReader isr = null;
        try {
            if (isEmptyString(encoding)) {
                encoding = Constants_Allgemein.ENCODING_FILE;
            }
            isr = new InputStreamReader(is, encoding);
        } catch (final Exception e) {
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (isr);
    }

    /**
     * liest eine Datei zeilenweise aus und haengt dabei an jede Zeile ein
     * beliebiges Zeichen an
     *
     * @param dateiname
     *            vollstaendiger Pfad + Name der Datei, die verarbeitet werden
     *            soll
     * @param postfix
     *            String, der am Ende jeder Zeile angehaengt wird; bei Uebergabe
     *            von null wird ein einzelnes Leerzeichen verwendet
     *
     * @return Inhalt der Datei als String, wobei jede Zeile von der naechsten
     *         nur durch den Inhalt von postfix getrennt wird (d.h. nicht
     *         automatisch durch ein Newline); Leerstring, wenn die Datei nicht
     *         verarbeitet werden konnte
     */
    public String getDateiAlsString(final String dateiname, final String postfix, final String encoding,
            final String request) {
        String dateiinhalt = "";
        try {
            if (!isEmptyString(dateiname)) {
                final File file = new File(dateiname.trim());
                if (file.exists()) {
                    this.log.debug("verarbeite Datei: '" + file + "'");
                    try (final FileInputStream fis = new FileInputStream(file)) {
                        dateiinhalt = getDateiAlsString(fis, postfix, encoding, request);
                        fis.close();
                    }
                } else {
                    this.log.error(new ExceptionHandlerAllgemein()
                            .getErrorMeldung(new Exception("Datei '" + file + "' existiert nicht!"), request));
                }
            }
        } catch (final Exception e) {
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (dateiinhalt);
    }

    /**
     * bearbeitet einen uebergebenen String so, dass er mit einem
     * Grossbuchstaben beginnt und nach jedem '.', Leerzeichen und '-' einen
     * Grossbuchstaben, sonst nur Kleinbuchstaben enthaelt
     *
     * @param string
     *            der zu bearbeitende String
     *
     * @return String, der mit einem Grossbuchstaben beginnt, nach jedem '.', '
     *         ' und '-' einen Grossbuchstaben hat und sonst nur
     *         Kleinbuchstaben, bei Uebergabe von null oder einem Leerstring
     *         wird ein Leerstring zurueckgegeben
     */
    public String upperLowerCase(String string) {
        final StringBuffer sb = new StringBuffer();
        if (!isEmptyString(string)) {
            string = string.trim();
            // wird auf true gesetzt, wenn das naechste Zeichen gross
            // geschrieben werden soll
            boolean flag = false;
            for (int i = 0; i < string.length(); i++) {
                if ((flag) || (i == 0)) {
                    sb.append(string.substring(i, i + 1).toUpperCase());
                    flag = false;
                } else {
                    sb.append(string.substring(i, i + 1).toLowerCase());
                }
                if ((string.charAt(i) == '.') || (string.charAt(i) == ' ') || (string.charAt(i) == '-')) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        }
        return (sb.toString());
    }

    /**
     * Kopiert alle Dateien eines Verzeichnisses in ein neues Zielverzeichnis.
     * Unterverzeichnisse werden nicht verarbeitet.
     *
     * @param quellpfad
     *            Quellpfad
     * @param zielpfad
     *            Zielpfad
     * @param request
     *            String
     * @return List<String> mit den neuen Pfaden (Zielverzeichnis + Dateiname)
     */
    public List<String> kopiereDateien(String quellpfad, String zielpfad, final String request) {
        List<String> list = null;
        if ((!isEmptyString(quellpfad)) && (!isEmptyString(zielpfad))) {
            final TypConverter typConverter = new TypConverter();
            quellpfad = quellpfad.trim();
            zielpfad = zielpfad.trim();
            final File quellverzeichnis = new File(quellpfad);
            if ((quellverzeichnis.exists()) && (quellverzeichnis.isDirectory())) {
                final File[] files = quellverzeichnis.listFiles();
                if (files != null) {
                    list = new LinkedList<>();
                    for (final File nextFile : files) {
                        if ((nextFile != null) && (nextFile.exists()) && (!nextFile.isDirectory())) {
                            final String name = typConverter.getNullString(nextFile.getName());
                            if (!isEmptyString(name)) {
                                if (kopiereDatei(nextFile, zielpfad, false, request)) {
                                    list.add(zielpfad + name);
                                }
                            }
                        }
                    }
                }
            }
        }
        return (list);
    }

    /**
     * kopiert die uebergebene Datei mit dem gleichen Dateinamen in das
     * angegebene Verzeichnis
     *
     * @param file
     *            zu kopierende Datei
     * @param zielpfad
     *            Verzeichnis, in dem die Kopie erstellt werden soll
     * @param forceCopy
     *            soll die Datei kopiert werden, selbst wenn die Zieldatei schon
     *            existiert?
     *
     * @return true, wenn die Datei erfolgreich kopiert wurde, sonst false
     */
    public boolean kopiereDatei(final File file, final String zielpfad, final boolean forceCopy, final String request) {
        boolean rueckgabe = false;
        try {
            if (file != null) {
                return (kopiereDatei(file, zielpfad, file.getName(), forceCopy, request));
            }
        } catch (final Exception e) {
            rueckgabe = false;
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (rueckgabe);
    }

    /**
     * kopiert die uebergebene Datei unter dem uebergebenen Dateinamen in das
     * uebergebene Verzeichnis
     *
     * @param file
     *            zu kopierende Datei
     * @param zielpfad
     *            Verzeichnis, in dem die Kopie erstellt werden soll
     * @param zieldateiname
     *            Name der Zieldatei
     * @param forceCopy
     *            soll die Datei kopiert werden, selbst wenn die Zieldatei schon
     *            existiert?
     *
     * @return true, wenn die Datei erfolgreich kopiert wurde, sonst false
     */
    public boolean kopiereDatei(final File file, final String zielpfad, final String zieldateiname,
            final boolean forceCopy, final String request) {
        boolean rueckgabe = false;
        try {
            if ((file != null) && (file.exists()) && (!isEmptyString(zielpfad))) {
                final TypConverter typConverter = new TypConverter();
                this.log.debug("zu kopierende Datei: '" + file.getAbsolutePath() + "'");
                if (!typConverter.getNotNullString(file.getParent()).equalsIgnoreCase(zielpfad.trim())) {
                    try (final FileInputStream fis = new FileInputStream(file)) {
                        rueckgabe = kopiereDatei(fis, zielpfad, zieldateiname, forceCopy, request);
                        fis.close();
                    }
                } else {
                    this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(
                            new Exception("Datei kann nicht kopiert werden, da Quell- und Zielpfad identisch sind!"),
                            request));
                }
            }
        } catch (final Exception e) {
            rueckgabe = false;
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (rueckgabe);
    }

    /**
     * kopiert die uebergebene Datei unter dem uebergebenen Dateinamen in das
     * angegebene Verzeichnis
     *
     * @param is
     *            InputStream auf die zu kopierende Datei
     * @param zielpfad
     *            Verzeichnis, in dem die Kopie erstellt werden soll
     * @param zieldateiname
     *            Name der Zieldatei
     * @param forceCopy
     *            soll die Datei kopiert werden, selbst wenn die Zieldatei schon
     *            existiert?
     *
     * @return true, wenn die Datei erfolgreich kopiert wurde, sonst false
     */
    public boolean kopiereDatei(final InputStream is, String zielpfad, String zieldateiname, final boolean forceCopy,
            final String request) {
        boolean rueckgabe = false;
        try {
            if ((is != null) && (!isEmptyString(zielpfad)) && (!isEmptyString(zieldateiname))) {
                zieldateiname = zieldateiname.trim();
                zielpfad = zielpfad.trim();
                if ((!zielpfad.endsWith("/")) && (!zielpfad.endsWith("\\"))) {
                    zielpfad += Constants_Allgemein.FILE_SEPARATOR;
                }
                final File neues_file = new File(zielpfad + zieldateiname);
                if (forceCopy || (!neues_file.exists())) {
                    this.log.debug("lege neue Datei an: '" + neues_file.getAbsolutePath() + "'");
                    neues_file.createNewFile();
                    try (final BufferedInputStream in = new BufferedInputStream(is)) {
                        try (final BufferedOutputStream out = new BufferedOutputStream(
                                new FileOutputStream(neues_file))) {
                            final byte[] buf = new byte[1024];
                            int i = 0;
                            while ((i = in.read(buf)) != -1) {
                                out.write(buf, 0, i);
                            }
                            out.flush();
                            out.close();
                        }
                        in.close();
                    }
                    rueckgabe = true;
                } else {
                    this.log.debug("Datei '" + neues_file.getAbsolutePath()
                            + " existiert bereits und soll nicht ueberschrieben werden!");
                }
            }
        } catch (final Exception e) {
            rueckgabe = false;
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (rueckgabe);
    }

    /**
     * holt alle Daten aus dem uebergebenen InputStream und gibt sie als
     * byte-Array zurueck
     */
    public byte[] getInputAsByteArray(final InputStream is, final String request) {
        byte[] bytes = null;
        try {
            if (is != null) {
                final BufferedInputStream buf = new BufferedInputStream(is);
                try (final ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
                    final byte[] buffer = new byte[1024];
                    int i = 0;
                    while ((i = buf.read(buffer)) != -1) {
                        bout.write(buffer, 0, i);
                    }
                    bout.flush();
                    bytes = bout.toByteArray();
                    bout.close();
                }
            }
        } catch (final Exception e) {
            bytes = null;
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (bytes);
    }

    /**
     * holt alle Daten aus dem uebergebenen Dateipfad und gibt sie als
     * byte-Array zurueck
     */
    public byte[] getInputAsByteArray(final String dateiPfad, final String request) {
        byte[] bytes = null;
        try {
            if (dateiPfad != null) {
                final File file = new File(dateiPfad);
                try (final InputStream fis = new FileInputStream(file)) {
                    bytes = getInputAsByteArray(fis, request);
                    fis.close();
                }
            }
        } catch (final Exception e) {
            bytes = null;
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (bytes);
    }

    /**
     * holt alle Daten aus der uebergebenen Datei und gibt sie als byte-Array
     * zurueck
     */
    public byte[] getFileAsByteArray(final File file, final String request) {
        byte[] bytes = null;
        try {
            if (file != null) {
                try (final InputStream fis = new FileInputStream(file)) {
                    bytes = getInputAsByteArray(fis, request);
                    fis.close();
                }
            }
        } catch (final Exception e) {
            bytes = null;
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (bytes);
    }

    /**
     * traegt an der 1. Stelle der uebergebenen Liste bzw. bei Uebergabe von
     * null in einer neuen Liste den Text "bitte auswaehlen" ein und gibt diese
     * Liste zurueck
     *
     * @param list
     *            List<SelectPseudoBean>, die um "bitte auswaehlen" erweitert
     *            werden soll
     *
     * @return mit "bitte auswaehlen" befuellte Liste, auch bei Uebergabe von
     *         null
     */
    public List<SelectPseudoBean> selectPseudoBeanListInit(List<SelectPseudoBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        final SelectPseudoBean selectPseudoBean = new SelectPseudoBean();
        selectPseudoBean.setKey(Constants_Allgemein.BITTE_AUSWAEHLEN);
        selectPseudoBean.setValue(Constants_Allgemein.BITTE_AUSWAEHLEN_TEXT);
        list.add(0, selectPseudoBean);
        return (list);
    }

    /**
     * haengt die Elemente der subList an die list an
     *
     * @return list, erweitert um die Elemente von subList
     */
    public <T> List<T> fuegtListInList(List<T> list, final List<? extends T> subList) {
        if (subList != null) {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.addAll(subList);
        }
        return (list);
    }

    /**
     * gibt einen InputStream auf die uebergebene URL zurueck
     *
     * @return InputStream auf die uebergebene URL oder null, falls kein
     *         InputStream geoeffnet werden kann
     */
    public InputStream getInputStream(final URL url, final String request) {
        InputStream is = null;
        try {
            if (url != null) {
                final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                final int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    is = connection.getInputStream();
                }
            }
        } catch (final Exception e) {
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (is);
    }

    /**
     * gibt den Inhalt der uebergebenen URL als String zurueck
     *
     * @param url
     *            abzufragende URL
     * @param postfix
     *            String, der am Ende jeder Zeile angehaengt wird; bei Uebergabe
     *            von null wird ein einzelnes Leerzeichen verwendet
     * @param encoding
     *            zu verwendendes Encoding, Pflichtfeld
     *
     * @return Inhalt der uebergebenen URL oder null, falls kein Inhalt gefunden
     *         werden kann
     */
    public String getInhalt(final URL url, final String postfix, final String encoding, final String request) {
        String string = null;
        try {
            try (final InputStream is = getInputStream(url, request)) {
                if (is != null) {
                    string = getDateiAlsString(is, postfix, encoding, request);
                    is.close();
                }
            }
        } catch (final Exception e) {
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (string);
    }

    /**
     * holt das uebergebene Attribut aus der Session
     *
     * @param session
     *            darf nicht null sein
     * @param attribute
     *            in der Session gesuchtes Attribut, darf nicht null sein
     * @return in der Session gefundener Wert, in den Zieldatentyp gecastet,
     *         oder null
     */
    @Nullable
    public <T> T getSessionAttribute(final HttpSession session, final String attribute, final String request) {
        @Nullable
        T wert = null;
        try {
            if ((session != null) && (!isEmptyString(attribute))) {
                wert = this.<T> getGenericObject(session.getAttribute(attribute.trim()));
            }
        } catch (final Exception e) {
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (wert);
    }

    /**
     * konvertiert das uebergebene Object in den gewuenschten JDK1.5+ konformen
     * Zieldatentyp
     */
    // MD: Warning laesst sich nicht vermeiden
    // Kapselung dieses Warnings ist Daseinszweck der Methode
    @SuppressWarnings("unchecked")
    public <T> T getGenericObject(final Object o) {
        return ((T) o);
    }

    /**
     * loescht alle Dateien im uebergebenen Verzeichnis, die vor dem
     * uebergebenen Zeitpunkt zuletzt modifiziert wurden
     *
     * @param alter
     *            letzte Modifizierung einer Datei muss vor alter stattgefunden
     *            haben, damit die Datei geloescht wird; bei Uebergabe von null
     *            wird das Alter nicht geprueft
     */
    public void verzeichnisInhaltLoeschen(final File verzeichnis, final Timestamp alter, final String request) {
        final ExceptionHandlerAllgemein exceptionHandlerAllgemein = new ExceptionHandlerAllgemein();
        try {
            final TypConverter typConverter = new TypConverter();
            if ((verzeichnis != null) && (verzeichnis.isDirectory())) {
                final String pfad = verzeichnis.getAbsolutePath();
                int zaehlerGesamt = 0;
                int zaehlerOk = 0;
                int zaehlerFail = 0;
                int zaehlerIgnoriert = 0;
                this.log.debug("lade Dateien im Pfad '" + pfad + "' mit letzter Aenderung bis " + alter);
                // hole alle Dateien aus dem uebergebenen Verzeichnis
                final File[] dateien = verzeichnis.listFiles();
                if (dateien != null) {
                    for (int i = 0; i < dateien.length; i++) {
                        try {
                            zaehlerGesamt++;
                            final File datei = dateien[i];
                            this.log.debug(i + ". Datei: " + datei);
                            if ((datei != null) && (datei.exists())) {
                                final String dateiname = typConverter.getNotNullString(datei.getName());
                                // SVN-Verzeichnisse sollen nicht geloescht
                                // werden
                                if ((dateiname.toLowerCase().indexOf(".svn")) < 0) {
                                    // eine Datei, die den Alters-Kriterien
                                    // entspricht, wird geloescht
                                    if ((alter == null) || (datei.lastModified() < alter.getTime())) {
                                        this.log.debug(
                                                "loesche " + zaehlerGesamt + ". Datei: '" + datei.getName() + "'");
                                        if (!datei.delete()) {
                                            datei.deleteOnExit();
                                        }
                                        zaehlerOk++;
                                    } else {
                                        zaehlerIgnoriert++;
                                    }
                                } else {
                                    zaehlerIgnoriert++;
                                }
                            } else {
                                zaehlerIgnoriert++;
                            }
                        } catch (final Exception e) {
                            zaehlerFail++;
                            this.log.error(exceptionHandlerAllgemein.getErrorMeldung(e, request));
                        }
                    }
                }
                this.log.debug("habe " + zaehlerGesamt + " Datei(en) im Verzeichnis '" + pfad
                        + "' gefunden, davon wurde(n) " + zaehlerOk + " erfolgreich geloescht, " + zaehlerFail
                        + " konnte(n) nicht geloescht werden und " + zaehlerIgnoriert + " wurde(n) ignoriert.");
            } else {
                this.log.error(new ExceptionHandlerAllgemein()
                        .getErrorMeldung(new Exception("kein Verzeichnis uebergeben: " + verzeichnis), request));
            }
        } catch (final Exception e) {
            this.log.error(exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
    }

    /**
     * zippt das uebergebene Byte-Array mit der Klasse
     * <code>java.util.zip.Deflater</code>
     */
    public byte[] zipByteArray(final byte input[]) throws IOException {
        byte[] compressedData = null;
        if (input != null) {
            final Deflater compressor = new Deflater();
            compressor.setLevel(Deflater.BEST_COMPRESSION);
            compressor.setInput(input);
            compressor.finish();
            try (final ByteArrayOutputStream bous = new ByteArrayOutputStream(input.length)) {
                final byte[] buf = new byte[1024];
                while (!compressor.finished()) {
                    final int count = compressor.deflate(buf);
                    bous.write(buf, 0, count);
                }
                bous.flush();
                compressedData = bous.toByteArray();
                bous.close();
            }
        }
        return (compressedData);
    }

    /**
     * entzippt das uebergebene Byte-Array mit der Klasse
     * <code>java.util.zip.Inflater</code>
     */
    public byte[] unzipByteArray(final byte compressedData[]) throws IOException, DataFormatException {
        byte[] output = null;
        if (compressedData != null) {
            final Inflater decompressor = new Inflater();
            decompressor.setInput(compressedData);
            try (final ByteArrayOutputStream bous = new ByteArrayOutputStream(compressedData.length)) {
                final byte[] buf = new byte[1024];
                while (!decompressor.finished()) {
                    final int count = decompressor.inflate(buf);
                    bous.write(buf, 0, count);
                }
                bous.flush();
                output = bous.toByteArray();
                bous.close();
            }
        }
        return (output);
    }

    /**
     * wandelt die uebergebene Liste in eine Map fuer eine seitenweise Anzeige
     * um
     *
     * @param list
     *            Liste, die in eine Map konvertiert werden soll
     * @param listenLaenge
     *            Laenge jeder Seite
     *
     * @return in eine Map von Seiten umgewandelte Liste
     */
    public <T> java.util.Map<Integer, List<T>> uebersichtMapErstellen(final List<T> list, final int listenLaenge) {
        final TreeMap<Integer, List<T>> map = new TreeMap<>();
        if (!isEmptyCollection(list)) {
            int index = 0;
            if ((listenLaenge <= 0) || (list.size() < listenLaenge)) {
                map.put(Integer.valueOf(index), list);
            } else {
                while (((index + 1) * listenLaenge) < list.size()) {
                    map.put(Integer.valueOf(index), list.subList((index * listenLaenge), ((index + 1) * listenLaenge)));
                    index++;
                }
                if ((index * listenLaenge) <= list.size()) {
                    map.put(Integer.valueOf(index++), list.subList((((index - 1) * listenLaenge)), list.size()));
                }
            }
        }
        return (map);
    }

    /**
     * Retourniert die aktuelle Session.
     *
     * @param request
     *            Request, aus welchem die Session ausgelesen werden soll.
     * @return HttpSession
     */
    public HttpSession getHttpSession(final HttpServletRequest request) {
        HttpSession session = null;
        try {
            if (request != null) {
                session = request.getSession();
            }
        } catch (final Exception e) {
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e,
                    new ExceptionHandlerAllgemein().getRequestString(request)));
        }
        return (session);
    }

    /**
     * erstellt die Protokolldatei in ausgabePfad
     *
     * @param dateityp
     *            Typ von Dateien die ertellt wurden
     * @param dateinamenListe
     *            Liste alle Dateinamen die erstellt wurden
     * @return boolean
     */
    public boolean protokolldateiErstellen(final String ausgabePfad, final String applikation, final String dateityp,
            final List<String> dateinamenListe, final String request) {
        boolean erstellt = false;
        try {
            if ((!isEmptyString(ausgabePfad)) && (!isEmptyString(applikation))
                    && (!isEmptyCollection(dateinamenListe))) {
                final Date datum = new Date(System.currentTimeMillis());
                final StringBuffer protokollname = new StringBuffer();
                protokollname.append(ausgabePfad);
                protokollname.append("protokoll_SV_");
                protokollname.append(applikation).append("_");
                if (!isEmptyString(dateityp)) {
                    protokollname.append(dateityp).append("_");
                }
                protokollname
                        .append(new TypConverter().getNotNullString(datum,
                                Constants_Datumsformat.DATUMSFORMAT_MIT_UHRZEIT_OHNE_PUNKTE_OHNE_SEKUNDEN))
                        .append(".txt");
                final DateiHandler dateiHandler = new DateiHandler();
                dateiHandler.oeffneDateiZumSchreiben(protokollname.toString(), false, Constants_Allgemein.ENCODING_FILE,
                        false, request);
                final StringBuffer inhalt = new StringBuffer();
                inhalt.append("Anzahl Dateien: ").append(dateinamenListe.size()).append(Constants_Allgemein.ENDL);
                for (final String dateiname : dateinamenListe) {
                    inhalt.append(dateiname).append(Constants_Allgemein.ENDL);
                }
                dateiHandler.schreibeInDatei(inhalt.toString(), request);
                dateiHandler.schliesseDatei(request);
                erstellt = true;
            } else {
                this.log.info("FEHLER: Ein notwendiger Parameter ist nicht vorhanden: ausgabePfad=" + ausgabePfad
                        + "; applikation=" + applikation + "; dateityp=" + dateityp + "; dateinamenListe="
                        + dateinamenListe);
            }
        } catch (final Exception e) {
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (erstellt);
    }

    /**
     * Liefert aus den einzelnen Informationen (aus dem LDAP) das
     * Bearbeiter-Objekt aus Beve. Wenn es zu Aenderungen im LDAP gekommen ist,
     * wird der Bearbeiter in Beve aktualisiert.
     *
     * ACHTUNG: LogikSoap.bearbeiterverzeichnisServicePort muss initialisiert
     * sein!
     *
     * @param vsnr
     *            VSNR des Bearbeiters
     * @param vorname
     *            Vorname des Bearbeiters
     * @param zuname
     *            Zuname des Bearbeiters
     * @param organisationseinheiten
     *            Organisationseinheiten aus dem SV-Principal
     * @param bearbeiterBenutzername
     *            Benutzername des Bearbeiters
     * @param request
     *            String
     * @return Bearbeiter
     * @throws SvWebServiceException
     *             im Fehlerfall
     */
    public Bearbeiter getBearbeiter(final String vsnr, final String vorname, final String zuname,
            final List<Organisationeinheit> organisationseinheiten, final String bearbeiterBenutzername,
            final HttpServletRequest request) throws SvWebServiceException {
        Bearbeiter bearbeiter = null;
        final ExceptionHandlerAllgemein exceptionHandlerAllgemein = new ExceptionHandlerAllgemein();
        String requestString = null;
        try {
            requestString = exceptionHandlerAllgemein.getRequestString(request);
            Bearbeiter anfrageBearbeiter = null;
            if (request != null) {
                final HttpSession session = request.getSession();
                if (session != null) {
                    anfrageBearbeiter = (Bearbeiter) session.getAttribute(Constants_Session.SESSION_BEARBEITER);
                }
            }
            if (anfrageBearbeiter == null) {
                anfrageBearbeiter = KonfigurationAllgemein.getSystembearbeiter();
            }
            bearbeiter = new BearbeiterAbfrage().sucheBearbeiterMitUpdate(vsnr, vorname, zuname, organisationseinheiten,
                    bearbeiterBenutzername, getAnfragendeStelle(anfrageBearbeiter, request), requestString);
        } catch (final SvWebServiceException e) {
            this.log.error(exceptionHandlerAllgemein.getErrorMeldung(e, false, requestString));
            throw (e);
        }
        return (bearbeiter);
    }

    /**
     * holt die IP-Adresse des Aufrufers aus dem uebergebenen Request
     *
     * @return request.getRemoteAddr()
     */
    public String getClientIP(final HttpServletRequest request) {
        String clientIP = null;
        if (request != null) {
            clientIP = request.getRemoteAddr();
        }
        return (clientIP);
    }

    /**
     * Liefert den aktuellen Session-Key (fuer eine Struts-Applikation). Wenn
     * der Session-Key in der Session nicht gefunden wird, wird er im Request
     * gesucht
     *
     * @param request
     *            String
     * @return der aktuelle Session-Key
     */
    public String getRequestSessionKey(final HttpServletRequest request) {
        String session_key = (String) request.getAttribute(Constants_Request.REQUEST_VSNR_SESSION_KEY);
        if (this.isEmptyString(session_key)) {
            session_key = request.getParameter("sessionKey");
        }
        request.setAttribute(Constants_Request.REQUEST_VSNR_SESSION_KEY, session_key);
        return (session_key);
    }

    /**
     * Ersetzt ungueltige Zeichen fuer einen Dateiname mit replacement. Ist
     * replacement null, wird ein Leerstring verwendet. Achtung: NUR den
     * Dateinamen uebergeben, bei Pfaden werden sonst die
     * Verzeichnis-Separatoren (/) entfernt!
     *
     * @return String
     */
    public String ersetzeUngueltigeZeichenInDateinamen(String dateiname, String replacement) {
        if (!this.isEmptyString(dateiname)) {
            if (replacement == null) {
                replacement = "";
            }
            dateiname = dateiname.replace("\\", replacement);
            dateiname = dateiname.replace("\"", replacement);
            dateiname = dateiname.replace("/", replacement);
            dateiname = dateiname.replace(":", replacement);
            dateiname = dateiname.replace("*", replacement);
            dateiname = dateiname.replace("?", replacement);
            dateiname = dateiname.replace("<", replacement);
            dateiname = dateiname.replace(">", replacement);
            dateiname = dateiname.replace("|", replacement);
        }
        return (dateiname);
    }

    /**
     * Fuegt am Ende des uebergebenen Pfades einen Datei-Separator an, wenn
     * dieser noch nicht vorhanden ist.
     *
     * @param pfad
     *            Pfad, an welchem der Datei-Separator angefuegt werden soll.
     * @return Pfad mit einem Separator am Ende des Pfades
     */
    public String fuegePfadSeperatorAnPfadAn(String pfad) {
        if (!isEmptyString(pfad)) {
            if (!pfad.endsWith(Constants_Allgemein.FILE_SEPARATOR)) {
                pfad += Constants_Allgemein.FILE_SEPARATOR;
            }
        }
        return (pfad);
    }

    /**
     * Iteriert ueber Kommandozeilenparameter (args[]) und bereitet den Inhalt
     * optisch fuer eine Logausgabe auf.
     *
     * @return Inhalt von args[] als String;
     */
    public String ausgabeKommandozeilenparameter(final String[] args) {
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(Constants_Allgemein.ENDL + "Kommandozeilenparameter: " + Constants_Allgemein.ENDL);
            if ((args == null) || (args.length == 0)) {
                sb.append("Keine Parameter vorhanden!");
            } else {
                for (int a = 0; a < args.length; a++) {
                    sb.append("Parameter " + (a + 1) + ": " + args[a]);
                    if (a != (args.length - 1)) {
                        sb.append(Constants_Allgemein.ENDL);
                    }
                }
            }
        } catch (final Exception e) {
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, (String) null));
        }
        return (sb.toString());
    }

    /**
     * Prueft ob das als Parameter uebergebene File vom gewuenschten Typ ist,
     * welcher als Parameter dateiArt mitgegeben wird
     *
     * @param file
     *            uebergebenes File
     * @param dateiArt
     *            uebergebener String
     * @return boolean
     */
    public boolean isDateiEndungRichtig(final File file, final String dateiArt) {
        boolean returnValue = false;
        final TypConverter typConverter = new TypConverter();
        if ((file != null) && (!isEmptyString(dateiArt))) {
            final String fileName = typConverter.getNotNullString(file.getName());
            final int posOfLastPoint = fileName.lastIndexOf('.');
            if (posOfLastPoint != -1) {
                final String dateiEndung = typConverter.getNotNullString(fileName.substring(posOfLastPoint));
                this.log.debug("Datei-Endung: " + dateiEndung);
                if (dateiEndung.equalsIgnoreCase("." + dateiArt)) {
                    returnValue = true;
                } else {
                    this.log.debug("Achtung! Die gefundene Dateiart entspricht nicht der gesuchten Dateiart!");
                }
            } else {
                this.log.debug("Achtung! Es wurde die Dateiendung nicht gefunden!");
            }
        }
        return (returnValue);
    }

    /**
     * prueft, ob ein String numerisch ist.
     *
     * @param string
     *            String, welcher ueberprueft werden soll
     * @return <b>true:</b> String ist numerisch<br />
     *         <b>false:</b> String ist nicht numerisch
     */
    public boolean isStringNumerisch(String string) {
        boolean stringNumerisch = true;
        if (!isEmptyString(string)) {
            string = string.trim();
            final char[] stringArray = string.toCharArray();
            for (int i = 0; (i < stringArray.length) && (stringNumerisch); i++) {
                if ((stringArray[i] < '0') || (stringArray[i] > '9')) {
                    stringNumerisch = false;
                }
            }
        } else {
            stringNumerisch = false;
        }
        return (stringNumerisch);
    }

    /**
     *
     * gibt die Bearbeiterinformationen aus Bearbeiter und RemoteAddr und
     * RemoteHost aus request (falls vorhanden) als String zurueck
     */
    public String getBearbeiterInfo(final Bearbeiter bearbeiter, final HttpServletRequest request) {
        final StringBuffer sb = new StringBuffer();
        sb.append("Bearbeiter: " + bearbeiter);
        if (request != null) {
            sb.append(" [RemoteAddr = " + request.getRemoteAddr() + " / RemoteHost = " + request.getRemoteHost() + "]");
        }
        return (sb.toString());
    }

    /**
     * Prueft ob Vpnr-String 6-stellig und nummerisch ist
     */
    public boolean checkVPNR(final String vpnr) {
        boolean valid = false;
        if ((!this.isEmptyString(vpnr)) && (vpnr.matches("[0-9]{6}"))) {
            valid = true;
        }
        return (valid);
    }

    /**
     * Fuegt einen Fehlertext in die Fehlermeldung hinzu
     *
     * @param fehlermeldung
     *            Fehlermeldung, welche nach der Validierung ausgegeben werden
     *            soll
     * @param fehlertext
     *            Fehlertext, welcher soeben zutrifft
     */
    public StringBuffer addFehlermeldung(StringBuffer fehlermeldung, final String fehlertext,
            final Integer zeilenposition, final String request) {
        try {
            if (!this.isEmptyString(fehlertext)) {
                if (fehlermeldung == null) {
                    fehlermeldung = new StringBuffer();
                    if (zeilenposition != null) {
                        fehlermeldung
                                .append("Folgende(r) Fehler trat(en) bei Zeilenposition " + zeilenposition + " auf: ");
                    }
                }
                fehlermeldung.append(fehlertext);
                fehlermeldung.append("; ");
            }
        } catch (final Exception e) {
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (fehlermeldung);
    }

    /**
     * Gibt die Listenlaenge je nach Ansicht als int zurueck
     */
    public int getProfilAnzahlZeilen(final Bearbeiter bearbeiter, final int defaultWert, String uebersichtsArt) {
        int returnValue = defaultWert;
        if ((bearbeiter != null) && (!isEmptyString(uebersichtsArt))) {
            uebersichtsArt = uebersichtsArt.trim();
            final List<ProfilWert> list = bearbeiter.getProfil();
            if (list != null) {
                final Iterator<ProfilWert> it = list.iterator();
                if (it != null) {
                    while (it.hasNext()) {
                        final ProfilWert profilWert = it.next();
                        if (profilWert != null) {
                            if (new TypConverter().getNotNullString(profilWert.getTyp())
                                    .equalsIgnoreCase(uebersichtsArt)) {
                                returnValue = profilWert.getWertZahl();
                            }
                        }
                    }
                }
            }
        }
        return (returnValue);
    }

    /**
     * ersetzt alle diakritischen Zeichen im uebergebenen String durch
     * Standard-Ascii-Zeichen, z.B. č -> c
     *
     * @param umlauteErsetzen
     *            true => ä, Ä, ö, Ö, ü, Ü und ß werden auch ersetzt; false =>
     *            die vorher angefuehrten Zeichen werden nicht ersetzt
     */
    public String replaceDiakritischeZeichen(String text, final boolean umlauteErsetzen) {
        if (!isEmptyString(text)) {
            if (umlauteErsetzen) {
                text = text.replace("ä", "ae").replace("Ä", "Ae").replace("ö", "oe").replace("Ö", "Oe")
                        .replace("ü", "ue").replace("Ü", "Ue").replace("ß", "ss");
            }
            text = text.replace("À", "A").replace("à", "a").replace("Á", "A").replace("á", "a").replace("Â", "A")
                    .replace("â", "a").replace("Ã", "A").replace("ã", "a").replace("Å", "A").replace("å", "a")
                    .replace("Æ", "Ae").replace("æ", "ae").replace("Ā", "A").replace("ā", "a").replace("Ă", "A")
                    .replace("ă", "a").replace("Ą", "A").replace("ą", "a").replace("Ǻ", "A").replace("ǻ", "a")
                    .replace("Ǽ", "Ae").replace("ǽ", "ae").replace("Ç", "C").replace("ç", "c").replace("Ć", "C")
                    .replace("ć", "c").replace("Ĉ", "C").replace("ĉ", "c").replace("Ċ", "C").replace("ċ", "c")
                    .replace("Č", "C").replace("č", "c").replace("Ð", "D").replace("ð", "d").replace("Ď", "D")
                    .replace("ď", "d").replace("Đ", "D").replace("đ", "d").replace("È", "E").replace("è", "e")
                    .replace("É", "E").replace("é", "e").replace("Ê", "E").replace("ê", "e").replace("Ë", "E")
                    .replace("ë", "e").replace("Ē", "E").replace("ē", "e").replace("Ĕ", "E").replace("ĕ", "e")
                    .replace("Ė", "E").replace("ė", "e").replace("Ę", "E").replace("ę", "e").replace("Ě", "E")
                    .replace("ě", "e").replace("ƒ", "F").replace("Ĝ", "G").replace("ĝ", "g").replace("Ğ", "G")
                    .replace("ğ", "g").replace("Ġ", "G").replace("ġ", "g").replace("Ģ", "G").replace("ģ", "g")
                    .replace("Ĥ", "H").replace("ĥ", "h").replace("Ħ", "H").replace("ħ", "h").replace("Ì", "i")
                    .replace("ì", "i").replace("Í", "i").replace("í", "i").replace("Î", "i").replace("î", "i")
                    .replace("Ï", "i").replace("ï", "i").replace("Ĩ", "i").replace("ĩ", "i").replace("Ī", "i")
                    .replace("ī", "i").replace("Ĭ", "i").replace("ĭ", "i").replace("Į", "i").replace("į", "i")
                    .replace("İ", "i").replace("ı", "i").replace("Ĳ", "IJ").replace("ĳ", "ij").replace("Ĵ", "J")
                    .replace("ĵ", "j").replace("Ķ", "K").replace("ķ", "k").replace("ĸ", "k").replace("Ĺ", "L")
                    .replace("ĺ", "l").replace("Ļ", "L").replace("ļ", "l").replace("Ľ", "L").replace("ľ", "l")
                    .replace("Ŀ", "L").replace("ŀ", "l").replace("Ł", "L").replace("ł", "l").replace("Ñ", "N")
                    .replace("ñ", "n").replace("Ń", "N").replace("ń", "n").replace("Ņ", "N").replace("ņ", "n")
                    .replace("Ň", "N").replace("ň", "n").replace("ŉ", "n").replace("Ŋ", "N").replace("ŋ", "n")
                    .replace("Ò", "O").replace("ò", "o").replace("Ó", "O").replace("ó", "o").replace("Ô", "O")
                    .replace("ô", "o").replace("Õ", "O").replace("õ", "o").replace("Ø", "O").replace("ø", "o")
                    .replace("Ō", "O").replace("ō", "o").replace("Ŏ", "O").replace("ŏ", "o").replace("Ő", "O")
                    .replace("ő", "o").replace("Œ", "OE").replace("œ", "oe").replace("Ǿ", "O").replace("ǿ", "o")
                    .replace("Ŕ", "R").replace("ŕ", "r").replace("Ŗ", "R").replace("ŗ", "r").replace("Ř", "R")
                    .replace("ř", "r").replace("ſ", "s").replace("Ś", "S").replace("ś", "s").replace("Ŝ", "S")
                    .replace("ŝ", "s").replace("Ş", "S").replace("ş", "s").replace("Š", "S").replace("š", "s")
                    .replace("Ţ", "T").replace("ţ", "t").replace("Ť", "T").replace("ť", "t").replace("Ŧ", "F")
                    .replace("ŧ", "f").replace("Ù", "U").replace("ù", "u").replace("Ú", "U").replace("ú", "u")
                    .replace("Û", "U").replace("û", "u").replace("Ũ", "U").replace("ũ", "u").replace("Ū", "U")
                    .replace("ū", "u").replace("Ŭ", "U").replace("ŭ", "u").replace("Ů", "U").replace("ů", "u")
                    .replace("Ű", "U").replace("ű", "u").replace("Ų", "U").replace("ų", "u").replace("Ŵ", "W")
                    .replace("ŵ", "w").replace("Ẁ", "W").replace("ẁ", "w").replace("Ẃ", "W").replace("ẃ", "w")
                    .replace("Ẅ", "W").replace("ẅ", "w").replace("Ỳ", "Y").replace("ỳ", "y").replace("Ý", "Y")
                    .replace("ý", "y").replace("Ŷ", "Y").replace("ŷ", "y").replace("Ÿ", "Y").replace("ÿ", "y")
                    .replace("Ź", "Z").replace("ź", "z").replace("Ż", "Z").replace("ż", "z").replace("Ž", "Z")
                    .replace("ž", "z").replace("Þ", "P").replace("þ", "p");
        }
        return (text);
    }

    /**
     * liefert true zurueck, wenn string nur mit '0' befuellt ist
     */
    public boolean isStringAusgenullt(String string) {
        boolean stringAusgenullt = true;
        if (!isEmptyString(string)) {
            string = string.trim();
            final char stringArray[] = string.toCharArray();
            for (int i = 0; (i < stringArray.length) && stringAusgenullt; i++) {
                if (stringArray[i] != '0') {
                    stringAusgenullt = false;
                }
            }
        } else {
            stringAusgenullt = false;
        }
        return (stringAusgenullt);
    }

    /**
     * liefert true zurueck, wenn ein String bis zur 0.Stelle numerisch ist und
     * einen Vorzeichen '-' an der 0.Stelle hat
     */
    public boolean isStringNumerischMitVorzeichen(final String string) {
        return ((!this.isEmptyString(string)) && (string.substring(0, 1).equals("-"))
                && (this.isStringNumerisch(string.substring(1))));
    }

    /**
     * liefert true zurueck, wenn ein String numerisch ist oder bis zur 0.Stelle
     * numerisch ist und einen Vorzeichen '-' an der 0.Stelle hat
     */
    public boolean isStringNumerischEvMitVorzeichen(final String string) {
        return ((this.isStringNumerisch(string)) || (this.isStringNumerischMitVorzeichen(string)));
    }

    /**
     * dreht die Reihenfolge der Elemente einer VSNR um; notwendig fuer
     * Sortierreihenfolge der Angehoerigen in einem Fall; Eingabe: LLLPTTMMJJ;
     * Ausgabe: JJJJMMTTLLLP
     *
     */
    public String getVsnrUmgekehrt(final String vsnr) {
        final TypConverter typConverter = new TypConverter();
        String vsnrUmgekehrt = null;
        if ((vsnr != null) && (vsnr.length() == 10)) {
            final String lllp = vsnr.substring(0, 4);
            final String tt = vsnr.substring(4, 6);
            final String mm = vsnr.substring(6, 8);
            final String jj = vsnr.substring(8);
            // Jahr wird mit 20 Jahr Hundert initialisiert
            int jahr = typConverter.parseInt(jj) + 2000;
            final GregorianCalendar cal = new GregorianCalendar();
            // Wenn jahr > Heutiges Jahr, dann Jahr = 19 Jahr Hundert
            if (jahr > cal.get(Calendar.YEAR)) {
                jahr -= 100;
            }
            vsnrUmgekehrt = typConverter.getNotLeerString(Long.valueOf(jahr));
            vsnrUmgekehrt += mm;
            vsnrUmgekehrt += tt;
            vsnrUmgekehrt += lllp;
        } else {
            this.log.error("Ungueltige VSNR (" + vsnr + ")");
        }
        return (vsnrUmgekehrt);
    }

    /**
     * @return VSNR-Gruppe zur uebergebenen VSNR (3. und 4. Stelle), sonst null
     */
    public String getVsnrGruppe(final String vsnr) {
        String pruefziffer = null;
        if (isGueltigeVsnr(vsnr, false)) {
            pruefziffer = vsnr.substring(2, 4);
        }
        return (pruefziffer);
    }

    /**
     * bereinigt die Benutzer-Session
     *
     * @param session
     *            Pflichtfeld!
     * @param bearbeiter_leeren
     *            sollen Bearbeiterinformationen entfernt werden?
     * @param alles_leeren
     *            soll absolut alles aus der Session entfernt werden
     */
    public void cleanSession(final HttpSession session, final boolean alles_leeren, final boolean bearbeiter_leeren) {
        if (session != null) {
            if (bearbeiter_leeren) {
                session.removeAttribute(Constants_Session.SESSION_BEARBEITER_ABTEILUNG);
                session.removeAttribute(Constants_Session.SESSION_BEARBEITER_LANDESSTELLE);
                session.removeAttribute(Constants_Session.SESSION_BEARBEITER_LANDESSTELLE_ZTXT);
                session.removeAttribute(Constants_Session.SESSION_BEARBEITER);
            }
            if (alles_leeren) {
                final Enumeration<String> en = getGenericObject(session.getAttributeNames());
                if (en != null) {
                    int i = 1;
                    while (en.hasMoreElements()) {
                        final String attribute = en.nextElement();
                        this.log.debug("entferne " + i + ". Attribut in der Session: " + attribute);
                        session.removeAttribute(attribute);
                        i++;
                    }
                }
            }
        } else {
            this.log.error("habe keine Session!");
        }
    }

    /**
     * errechnet zu einer HDAD-Nummer (6 Stellen + 0) die zugehoerige
     * Positionsnummer (6 Stellen + Pruefziffer)
     */
    public String positionsnummerPruefen(final String string) {
        String positionsnummer = null;
        final TypConverter typConverter = new TypConverter();
        if (!this.isEmptyString(string) && (string.trim().length() == 7)) {
            final Integer p = typConverter.getNullInteger(string);
            if ((p != null) && (p.intValue() >= 0)) {
                final String pos_1_6 = string.substring(0, 6);
                final Integer pnr = typConverter.getNotNullInteger(pos_1_6);
                final int x = pnr.intValue() % 7;
                positionsnummer = pos_1_6 + x;
            }
        }
        return (positionsnummer);
    }

    /**
     * schreibt die uebergebenen Bytes in den Response (zur Anzeige von PDFs,
     * Gifs, ...)
     *
     * @param bytes
     *            anzuzeigende Bytes
     * @param mime_type
     *            Mime-Type der Bytes; unterstuetzte Formate: image/gif,
     *            image/jpeg, application/pdf, image/png, image/tiff,
     *            text/plain, text/xml
     * @param dateiname
     *            anzuzeigender Dateiname
     * @param inline
     *            true, falls versucht werden soll, die Datei direkt im Browser
     *            zu oeffnen; false, wenn die Datei jedenfalls ausserhalb des
     *            Browsers geoeffnet werden soll,
     * @param response
     *            zu erweiternde Response
     *
     * @return erweiterte Response
     */
    public HttpServletResponse schreibeBlobInResponse(final byte[] bytes, final String mime_type,
            final String dateiname, final boolean inline, final String request, final HttpServletResponse response) {
        try {
            if ((bytes != null) && (bytes.length > 0) && (!isEmptyString(mime_type)) && (!isEmptyString(dateiname))) {
                final String normalizedDateiname = Normalizer.normalize(dateiname, Form.NFC);
                response.setContentType(mime_type);
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");
                response.setHeader("Content-Disposition",
                        (inline ? "inline" : "attachment") + ";filename=" + normalizedDateiname);
                try (final ServletOutputStream sos = response.getOutputStream()) {
                    final int bufferSize = 4096;
                    final InputStream inputStream = new ByteArrayInputStream(bytes);
                    final byte[] buffer = new byte[bufferSize];
                    for (int n = 0; -1 != (n = inputStream.read(buffer));) {
                        sos.write(buffer, 0, n);
                    }
                    sos.close();
                }
            } else {
                this.log.error(
                        new ExceptionHandlerAllgemein()
                                .getErrorMeldung(
                                        new Exception("Parameter fehlt! mime_type = " + mime_type + "; dateiname = "
                                                + dateiname + "; bytes = " + ((bytes != null) ? bytes.length : 0)),
                                        request));
            }
        } catch (final Exception e) {
            this.log.error(new ExceptionHandlerAllgemein().getErrorMeldung(e, request));
        }
        return (response);
    }

    /**
     * Finde in einem Ordner die zugehoerige Datei zur VsnrGruppe
     *
     * @param ordner
     *            Verzeichnis
     * @param vsnrGruppe
     *            Vsnrgruppe
     * @return dateiMitPfad
     */
    public String getDateiMitVsnrGruppeAusPfad(String ordner, final String vsnrGruppe) {
        String dateiMitPfad = null;
        if (ordner != null) {
            ordner = fuegePfadSeperatorAnPfadAn(ordner);
            final File file = new File(ordner);
            final String[] strings = file.list();
            if ((strings != null) && (strings.length > 0)) {
                final String filename = findeXmlFileNachVsnrGruppe(Arrays.asList(strings), vsnrGruppe);
                if (filename != null) {
                    dateiMitPfad = ordner + filename;
                }
            }
        }
        return dateiMitPfad;
    }

    /**
     * aus einem Set wird nach der vsnrGruppe ein File gesucht und der Pfad als
     * String zurueckgeliefert
     *
     */
    public String findeXmlFileNachVsnrGruppe(final List<String> set, final String vsnrGruppe) {
        String xmlFile = null;
        if (!isEmptyList(set)) {
            final Iterator<String> it = set.iterator();
            while (it.hasNext() && (xmlFile == null)) {
                final String file = it.next();
                if (!isEmptyString(file)) {
                    if (file.contains("T" + vsnrGruppe)) {
                        this.log.debug("zur VsnrGruppe: " + vsnrGruppe + " wurde das File " + file + " gefunden");
                        xmlFile = file;
                    }
                }
            }
        }
        return (xmlFile);
    }

    /**
     * gibt eine Liste mit den ersten "anzahlElemente" Elementen aus iList
     * zurueck
     */
    public <T> List<T> getSubList(final Iterator<T> iList, final int anzahlElemente) {
        final List<T> subList = new LinkedList<>();
        if (iList != null) {
            int anzahl = 0;
            while ((iList.hasNext()) && (anzahl < anzahlElemente)) {
                final T next = iList.next();
                if (next != null) {
                    subList.add(next);
                }
                anzahl++;
            }
        }
        return (subList);
    }

    /**
     * Entferne alle NULL-Werte aus der Liste und gebe eine neue Liste zurueck
     *
     */
    public <T> List<T> getListWithNotNullValues(
            // MD: Warning laesst sich nicht vermeiden
            // Alternative waere @SafeVarargs + final-Methode, dann funktionieren aber die eSV-Anwendungen nicht mehr
            @SuppressWarnings("unchecked") final T... objects) {
        List<T> list = null;
        if ((objects != null) && (objects.length > 0)) {
            list = new ArrayList<>(Arrays.asList(objects));
            list.removeAll(Collections.singleton(null));
        }
        return list;
    }

    /**
     * Entferne die uebergebenen Elemente aus den Listen
     *
     * @param collection
     *            Collection<List<T>>
     * @param elemenToRemove
     *            Anzahl: 1...x
     */
    public <T> void removeElementFromInnerList(final Collection<List<T>> collection,
            // MD: Warning laesst sich nicht vermeiden
            // Alternative waere @SafeVarargs + final-Methode, dann funktionieren aber die eSV-Anwendungen nicht mehr
            @SuppressWarnings("unchecked") final T... elemenToRemove) {
        if (!isEmptyCollection(collection) && ((elemenToRemove != null) && (elemenToRemove.length > 0))) {
            final List<T> listToRemove = Arrays.asList(elemenToRemove);
            for (final List<T> list : collection) {
                if (!isEmptyList(list)) {
                    list.removeAll(listToRemove);
                }
            }
        }
    }

    /**
     * Entferne alle Elemente die in der zweite Liste vorhanden sind aus der
     * ersten Liste
     *
     * @return List<T> ohne den Elementen der zweiten Liste
     */
    public <T> List<T> subtract(final List<T> list1, final List<T> list2) {
        final ArrayList<T> result = new ArrayList<>(list1);
        if (list2 != null) {
            final Iterator<T> iterator = list2.iterator();
            while (iterator.hasNext()) {
                result.remove(iterator.next());
            }
        }
        return result;
    }

    /**
     * @param zpvTitels
     *            zpvTitels (KvPerson.getTitel*())
     * @param titelListe
     *            Falls null uebergeben wurde, wird eine Insatz davon erzeugt.
     *
     * @return Neue Instanz falls die TitelListe null war, sonst die gleiche
     *         Referenz
     */
    public TitelListe erzeugeTitelListe(final List<ZpvTitel> zpvTitels, TitelListe titelListe) {
        if (titelListe == null) {
            titelListe = new TitelListe();
        }
        if (!isEmptyList(zpvTitels)) {
            for (final ZpvTitel zpvTitel : zpvTitels) {
                // Iteriere ueber akademische Titel
                if (zpvTitel != null) {
                    titelListe.getTitel().add(this.erzeugeTitel(zpvTitel));
                }
            }
        }
        return titelListe;
    }

    /**
     * Convertiere den zpvTitel in ein Titel-Objekt
     */
    public Titel erzeugeTitel(final ZpvTitel zpvTitel) {
        final Titel titel = new Titel();
        if (zpvTitel != null) {
            final TypConverter typConverter = new TypConverter();
            titel.setTyp(TitelTyp.fromValue(zpvTitel.getTyp()));
            titel.setKurztext(typConverter.getNullString(zpvTitel.getKurzText()));
            titel.setLangtext(typConverter.getNullString(zpvTitel.getLangText()));
            final Boolean wichtigesterTitel = zpvTitel.isWichtigsterTitel();
            if (wichtigesterTitel != null) {
                titel.setWichtigsterTitel(wichtigesterTitel.booleanValue());
            }
        }
        return titel;
    }

    /**
     * liefert die Titeln (vorne/hinten) als String zurueck
     */
    public String getTitel(final List<ZpvTitel> titelListe) {
        String titel = null;
        if (!isEmptyList(titelListe)) {
            final StringBuffer sb = new StringBuffer();
            int index = 0;
            for (final ZpvTitel zpvTitel : titelListe) {
                if (zpvTitel != null) {
                    if (index > 0) {
                        sb.append(" ");
                    }
                    sb.append(zpvTitel.getKurzText());
                    index++;
                }
            }
            if (index > 0) {
                titel = sb.toString();
            }
        }
        return (titel);
    }

    /**
     * gibt die Anrede je nach geschlecht zueruck
     */
    public String getAnrede(final String geschlecht) {
        String anrede = "Frau/Herr";
        if (!isEmptyString(geschlecht)) {
            if (geschlecht.equalsIgnoreCase("W")) {
                anrede = "Frau";
            } else if (geschlecht.equalsIgnoreCase("M")) {
                anrede = "Herr";
            } else if (geschlecht.equalsIgnoreCase("F")) {
                anrede = "Firma";
            } else if (geschlecht.equalsIgnoreCase("Frau") || geschlecht.equalsIgnoreCase("Herr")
                    || geschlecht.equalsIgnoreCase("Firma") || geschlecht.equalsIgnoreCase("Frau/Herr")) {
                anrede = geschlecht;
            }
        }
        return (anrede);
    }

    /**
     * verkettet der sourceString mit die Trennzeichen (nur dann wenn
     * sourceString nicht NULL ist) und mit der addString
     */
    public String concatStrings(final String sourceString, final String trennzeichen, final String addString) {
        String stringVerkettet = sourceString;
        if (stringVerkettet == null) {
            stringVerkettet = new String();
            stringVerkettet = addString;
        } else if (!isEmptyString(addString)) {
            if (trennzeichen != null) {
                stringVerkettet.concat(trennzeichen).concat(addString);
            } else {
                stringVerkettet.concat(addString);
            }
        }
        return (stringVerkettet);
    }

    /**
     * Liefert true zurueck fall es sich um ein ungueltigesZeichen handelt
     */
    public boolean istUngueltigesZeichen(final char c) {
        return ((c < ' ') || ((c >= 0x7F) && (c <= 0x9F)));
    }

    /**
     * Unguelitge Zeichen wie in der folgenden Klasse definiert
     * com.ctc.wstx.api.InvalidCharHandler}
     *
     * @return bereinigter text
     */
    public String ersetzeUngueltigeWebServiceZeichen(String text, final char korrekturZeichen) {
        if (!isEmptyString(text)) {
            final StringBuilder sb = new StringBuilder();
            final char[] charArray = text.toCharArray();
            for (final char c : charArray) {
                if (!istUngueltigesZeichen(c)) {
                    sb.append(c);
                } else {
                    sb.append(korrekturZeichen);
                }
            }
            text = sb.toString();
        }
        return text;
    }

    /**
     * @param bytesToBeAnalyzed
     *            ByteArray
     * @return Charset
     */
    public Charset findeCharSet(final byte[] bytesToBeAnalyzed) {
        Charset retCs = Charset.forName(Constants_Allgemein.ENCODING_FILE);
        boolean found = false;
        final byte[] specialChars = new byte[] { (byte) 196, (byte) 214, (byte) 220, (byte) 223, (byte) 228, (byte) 246,
                (byte) 252 };
        for (int i = 0; (i < bytesToBeAnalyzed.length) && !found; i++) {
            if (Arrays.binarySearch(specialChars, bytesToBeAnalyzed[i]) >= 0) {
                found = true;
            }
        }
        if (found) {
            retCs = Charset.forName(Constants_Allgemein.ENCODING_ISO_8859_1);
        }
        this.log.debug("Folgendes Charset wird verwendet: " + retCs.displayName());
        return retCs;
    }

    /**
     *
     */
    public int findeNaechstenUmbruch(final byte[] byteArr, final int from) {
        boolean continueSearch = true;
        final byte[] sep = new byte[] { 13, 10 };
        int i = from + 2;
        int endIndex = from;
        // Finde Trennkennzeichen
        while (continueSearch) {
            if (i < byteArr.length) {
                if ((byteArr[i] == sep[1]) && (byteArr[i - 1] == sep[0])) {
                    continueSearch = false;
                    endIndex = i - 2;
                }
            } else {
                continueSearch = false;
                endIndex = byteArr.length - 2;
            }
            i++;
        }
        return endIndex;
    }

    /**
     * @return true, falls die Anwendung im online-Modus ausgefuhert wird, sonst
     *         false
     */
    public boolean istSystemmodusOnline() {
        final Systemmodus systemmodus = KonfigurationAllgemein.getSystemmodus();
        return ((systemmodus != null) && (systemmodus.equals(Systemmodus.O)));
    }

    /**
     * erzeugt eine <code>AnfragendeStelle</code> aus den uebergebenen Daten
     */
    public AnfragendeStelle getAnfragendeStelle(final Bearbeiter bearbeiter, final HttpServletRequest request) {
        return (new LogikSoap().getAnfragendeStelle(bearbeiter, null, null, null, null, getClientIP(request),
                Constants_Sicherheitsklasse.BENUTZERNAME_KENNWORT_MIT_AUSWEIS));
    }

    /**
     * fuegt die Dateien eines Ordners in einen Map ein <br>
     * (Dateiname, Pfad+Dateiname).
     */
    public Map<String, String> getEingabeDateiMap(final String eingabePfad) {
        Map<String, String> eingabeDateiMap = null;
        if (!isEmptyString(eingabePfad)) {
            final File eingabePfad_file = new File(eingabePfad);
            if (eingabePfad_file.isDirectory()) {
                final String[] eingabeDateiList = eingabePfad_file.list();
                if (eingabeDateiList != null) {
                    eingabeDateiMap = new TreeMap<>();
                    for (final String eingabeDatei : eingabeDateiList) {
                        if (!isEmptyString(eingabeDatei)) {
                            eingabeDateiMap.put(eingabeDatei, fuegePfadSeperatorAnPfadAn(eingabePfad) + eingabeDatei);
                        }
                    }
                }
            }
        }
        return (eingabeDateiMap);
    }

    /**
     * Prueft die Laenge eine Strings. Liefert "false" wenn die minLaenge
     * unterschritten oder die maxLaenge ueberschritten wird. Leerzeichen am
     * Anfang und Ende des Strings werden nicht mitgezaehlt.
     */
    public boolean checkLaenge(final String string, final int minLaenge, final int maxLaenge) {
        final int laenge = new TypConverter().getNotNullString(string).length();
        return (((laenge >= minLaenge) && (laenge <= maxLaenge)));
    }

    /**
     * gibt den ersten Eintrag in der uebergebenen Collection zurueck
     *
     * @return erster Eintrag oder null bei leerer Collection
     */
    public <T> T getErstenEintrag(final Collection<T> collection) {
        // MD: Warning laesst sich nicht vermeiden
        @SuppressWarnings("null")
        T rueckgabe = null;
        if (!isEmptyCollection(collection)) {
            final Iterator<T> it = collection.iterator();
            if (it != null) {
                while ((rueckgabe == null) && (it.hasNext())) {
                    rueckgabe = it.next();
                }
            }
        }
        return (rueckgabe);
    }

    /**
     * legt ein Set mit dem uebergebenen Element als Inhalt an
     *
     * @return mit t befuelltes Set oder null, falls t null ist
     */
    public <T> Set<T> asSet(final T t) {
        Set<T> set = null;
        if (t != null) {
            set = new HashSet<>();
            set.add(t);
        }
        return (set);
    }
}
