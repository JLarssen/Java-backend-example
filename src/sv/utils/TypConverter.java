package sv.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.jdt.annotation.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.Constants_Datumsformat;
import constants.Constants_Werte;
import util.Helper;

/**
 * Hilfsklasse zum Konvertieren von Datentypen
 *
 * @author SV-Benutzer
 */
public class TypConverter {
    private final static DecimalFormat dfNachkommastellenOptional = new DecimalFormat("0.##",
            new DecimalFormatSymbols(Locale.GERMAN));

    private final static DecimalFormat df_jsp_anzeige = new DecimalFormat("0.00",
            new DecimalFormatSymbols(Locale.GERMAN));

    /**
     * Logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    private final HelperAllgemein helperAllgemein;

    /**
     * Default-Konstruktor
     */
    public TypConverter() {
        this.helperAllgemein = new HelperAllgemein();
    }

    /**
     * wandelt den uebergebenen String im uebergebenen Datumsformat in ein
     * <code>java.util.Date</code> um
     *
     * @param datumsformat
     *            ein den Vorschriften der Klasse
     *            <code>java.text.SimpleDateFormat</code> entsprechender String
     * @return String als <code>java.util.Date</code>, oder null, falls der
     *         String nicht konvertiert werden kann
     */
    public java.util.Date getNullUtilDate(final String string, final String datumsformat) {
        java.util.Date datum = null;
        try {
            if ((!this.helperAllgemein.isEmptyString(string)) && (!this.helperAllgemein.isEmptyString(datumsformat))) {
                final SimpleDateFormat sdf = new SimpleDateFormat(datumsformat.trim());
                sdf.setLenient(false);
                datum = sdf.parse(string.trim());
            }
        } catch (final Exception e) {
            datum = null;
            this.log.warn("Datum konnte nicht gelesen werden. Fehler: " + e);
        }
        return (datum);
    }

    /**
     * wandelt den uebergebenen String im uebergebenen Datumsformat in ein
     * <code>java.sql.Timestamp</code> um
     *
     * @param datumsformat
     *            ein den Vorschriften der Klasse
     *            <code>java.text.SimpleDateFormat</code> entsprechender String
     *
     * @return String als <code>java.sql.Timestamp</code>, oder null, falls der
     *         String nicht konvertiert werden kann
     */
    public Timestamp getNullTimestamp(final String string, final String datumsformat) {
        Timestamp timestamp = null;
        final java.util.Date datum = getNullUtilDate(string, datumsformat);
        if (datum != null) {
            timestamp = new Timestamp(datum.getTime());
        }
        return (timestamp);
    }

    /**
     * wandelt den uebergebenen String im uebergebenen Datumsformat in ein
     * <code>java.sql.Date</code> um
     *
     * @param datumsformat
     *            ein den Vorschriften der Klasse
     *            <code>java.text.SimpleDateFormat</code> entsprechender String
     *
     * @return String als <code>java.sql.Date</code>, oder null, falls der
     *         String nicht konvertiert werden kann
     */
    public java.sql.Date getNullSqlDate(final String string, final String datumsformat) {
        return (utilDateZuSqlDate(getNullUtilDate(string, datumsformat)));
    }

    /**
     * wandelt den uebergebenen String im uebergebenen Datumsformat in ein
     * <code>java.util.Calendar</code> um
     *
     * @param datumsformat
     *            ein den Vorschriften der Klasse
     *            <code>java.text.SimpleDateFormat</code> entsprechender String
     * @return String als <code>java.util.GregorianCalendar</code>, oder null,
     *         falls der String nicht konvertiert werden kann
     */
    public Calendar getNullCalendar(final String string, final String datumsformat) {
        GregorianCalendar gregorianCalendar = null;
        final java.util.Date datum = getNullUtilDate(string, datumsformat);
        if (datum != null) {
            gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(datum);
        }
        return (gregorianCalendar);
    }

    /**
     * wandelt das uebergebene <code>java.util.Date</code> in ein
     * <code>java.sql.Date</code> um
     *
     * @return <code>java.util.Date</code> als <code>java.sql.Date</code> oder
     *         null, falls utilDate auch null ist
     */
    public java.sql.Date utilDateZuSqlDate(final java.util.Date utilDate) {
        java.sql.Date sqlDate = null;
        if (utilDate != null) {
            sqlDate = new java.sql.Date(utilDate.getTime());
        }
        return (sqlDate);
    }

    /**
     * wandelt den uebergebenen <code>java.util.Calendar</code> in ein
     * <code>java.sql.Date</code> um
     *
     * @return <code>java.util.Calendar</code> als <code>java.sql.Date</code>
     *         oder null, falls calendar auch null ist
     */
    public java.sql.Date calendarZuSqlDate(final Calendar calendar) {
        java.sql.Date sqlDate = null;
        if (calendar != null) {
            sqlDate = new java.sql.Date(calendar.getTimeInMillis());
        }
        return sqlDate;
    }

    /**
     * wandelt den uebergebenen <code>java.util.Calendar</code> in ein
     * <code>java.util.Date</code> um
     *
     * @return <code>java.util.Calendar</code> als <code>java.util.Date</code>
     *         oder null, falls calendar auch null ist
     */
    public java.util.Date calendarZuUtilDate(final Calendar calendar) {
        java.util.Date utilDate = null;
        if (calendar != null) {
            utilDate = new java.util.Date(calendar.getTimeInMillis());
        }
        return (utilDate);
    }

    /**
     * wandelt den uebergebenen <code>java.util.Calendar</code> in ein
     * <code>java.sql.Timestamp</code> um
     *
     * @return <code>java.util.Calendar</code> als <code>java.util.Date</code>
     *         oder null, falls calendar auch null ist
     */
    public Timestamp calendarZuTimestamp(final Calendar calendar) {
        Timestamp timestamp = null;
        if (calendar != null) {
            timestamp = new Timestamp(calendar.getTimeInMillis());
        }
        return timestamp;
    }

    /**
     * wandelt den uebergebenen <code>java.util.Date</code> in ein
     * <code>java.sql.Timestamp</code> um
     *
     * @return <code>java.util.Date</code> als <code>java.sql.Timestamp</code>
     *         oder null, falls calendar auch null ist
     */
    public Timestamp utilDateZuTimestamp(final java.util.Date date) {
        Timestamp timestamp = null;
        if (date != null) {
            timestamp = new Timestamp(date.getTime());
        }
        return timestamp;
    }

    /**
     * wandelt das uebergebene <code>java.sql.Date</code> in ein
     * <code>java.sql.Timestamp</code> um (die Zeit wird als 00:00:00.0
     * initialisiert)
     *
     * @return <code>java.sql.Date</code> als <code>java.sql.Timestamp</code>
     *         oder null, falls datum auch null ist
     */
    public Timestamp sqlDateZuTimestamp(final java.sql.Date sqlDate) {
        Timestamp sqlTimestamp = null;
        if (sqlDate != null) {
            final GregorianCalendar cal = new GregorianCalendar();
            cal.setTimeInMillis(sqlDate.getTime());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            sqlTimestamp = new Timestamp(cal.getTimeInMillis());
        }
        return (sqlTimestamp);
    }

    /**
     * wandelt das uebergebene <code>java.sql.Date</code> in einen
     * <code>java.util.GregorianCalendar</code> um
     *
     * @return <code>java.sql.Date</code> als
     *         <code>java.util.GregorianCalendar</code> oder null, falls sqlDate
     *         auch null ist
     */
    public GregorianCalendar sqlDateZuGregorianCalendar(final java.sql.Date sqlDate) {
        GregorianCalendar cal = null;
        if (sqlDate != null) {
            cal = new GregorianCalendar();
            cal.setTimeInMillis(sqlDate.getTime());
        }
        return (cal);
    }

    /**
     * wandelt das uebergebene <code>java.util.Date</code> in einen
     * <code>java.util.GregorianCalendar</code> um
     *
     * @return <code>java.util.Date</code> als
     *         <code>java.util.GregorianCalendar</code> oder null, falls
     *         udilDate auch null ist
     */
    public GregorianCalendar utilDateZuGregorianCalendar(final java.util.Date utilDate) {
        GregorianCalendar cal = null;
        if (utilDate != null) {
            cal = new GregorianCalendar();
            cal.setTimeInMillis(utilDate.getTime());
        }
        return (cal);
    }

    /**
     * wandelt das uebergebene <code>java.sql.Date</code> in einen
     * <code>javax.xml.datatype.XMLGregorianCalendar</code> um
     *
     * @return <code>java.sql.Date</code> als
     *         <code>javax.xml.datatype.XMLGregorianCalendar</code> oder null,
     *         falls sqlDate auch null ist
     */
    public XMLGregorianCalendar sqlDateZuXMLGregorianCalendar(final java.sql.Date sqlDate) {
        return (gregorianCalendarZuXMLGregorianCalendar(sqlDateZuGregorianCalendar(sqlDate)));
    }

    /**
     * wandelt das uebergebene <code>java.util.Date</code> in einen
     * <code>javax.xml.datatype.XMLGregorianCalendar</code> um
     *
     * @return <code>java.util.Date</code> als
     *         <code>javax.xml.datatype.XMLGregorianCalendar</code> oder null,
     *         falls utilDate auch null ist
     */
    public XMLGregorianCalendar utilDateZuXMLGregorianCalendar(final java.util.Date utilDate) {
        return (gregorianCalendarZuXMLGregorianCalendar(utilDateZuGregorianCalendar(utilDate)));
    }

    /**
     * wandelt den uebergebenen <code>java.util.GregorianCalendar</code> in
     * einen <code>javax.xml.datatype.XMLGregorianCalendar</code> um
     *
     * @return <code>java.util.GregorianCalendar</code> als
     *         <code>javax.xml.datatype.XMLGregorianCalendar</code> oder null,
     *         falls gregorianCalendar auch null ist
     */
    public XMLGregorianCalendar gregorianCalendarZuXMLGregorianCalendar(final GregorianCalendar gregorianCalendar) {
        XMLGregorianCalendar xmlGregorianCalendar = null;
        try {
            if (gregorianCalendar != null) {
                final DatatypeFactory df = DatatypeFactory.newInstance();
                if (df != null) {
                    xmlGregorianCalendar = df.newXMLGregorianCalendar(gregorianCalendar);
                }
            }
        } catch (final Exception e) {
            xmlGregorianCalendar = null;
            this.log.warn("Fehler: " + e);
        }
        return (xmlGregorianCalendar);
    }

    /**
     * wandelt den uebergebenen
     * <code>javax.xml.datatype.XMLGregorianCalendar</code> in ein
     * <code>java.sql.Date</code> um
     *
     * @return <code>javax.xml.datatype.XMLGregorianCalendar</code> als
     *         <code>java.sql.Date</code> oder null, falls xmlGregorianCalendar
     *         auch null ist
     */
    public java.sql.Date xmlGregorianCalendarZuSqlDate(final XMLGregorianCalendar xmlGregorianCalendar) {
        java.sql.Date sqlDate = null;
        if (xmlGregorianCalendar != null) {
            sqlDate = (calendarZuSqlDate(xmlGregorianCalendar.toGregorianCalendar()));
        }
        return (sqlDate);
    }

    /**
     * wandelt den uebergebenen
     * <code>javax.xml.datatype.XMLGregorianCalendar</code> in ein
     * <code>java.util.Date</code> um
     *
     * @return <code>javax.xml.datatype.XMLGregorianCalendar</code> als
     *         <code>java.util.Date</code> oder null, falls xmlGregorianCalendar
     *         auch null ist
     */
    public java.util.Date xmlGregorianCalendarZuUtilDate(final XMLGregorianCalendar xmlGregorianCalendar) {
        return (xmlGregorianCalendarZuSqlDate(xmlGregorianCalendar));
    }

    /**
     * wandelt den uebergebenen
     * <code>javax.xml.datatype.XMLGregorianCalendar</code> in ein
     * <code>java.sql.Timestamp</code> um
     *
     * @return <code>javax.xml.datatype.XMLGregorianCalendar</code> als
     *         <code>java.sql.Timestamp</code> oder null, falls
     *         xmlGregorianCalendar auch null ist
     */
    public Timestamp xmlGregorianCalendarZuTimestamp(final XMLGregorianCalendar xmlGregorianCalendar) {
        Timestamp timestamp = null;
        if (xmlGregorianCalendar != null) {
            timestamp = (calendarZuTimestamp(xmlGregorianCalendar.toGregorianCalendar()));
        }
        return (timestamp);
    }

    /**
     * gibt ein <code>java.sql.Date</code> zum uebergebenen Datum zurueck
     */
    public java.sql.Date getSqlDate(final int jahr, final int monat, final int tag) {
        final GregorianCalendar gc = new GregorianCalendar();
        gc.set(jahr, monat - 1, tag);
        return (utilDateZuSqlDate(gc.getTime()));
    }

    /**
     * wandelt das uebergebene <code>java.util.Date</code> in einen String im
     * uebergebenen Datumsformat um
     *
     * @param datumsformat
     *            ein den Vorschriften der Klasse
     *            <code>java.text.SimpleDateFormat</code> entsprechender String
     * @return Datum als String, oder null, falls das Datum nicht konvertiert
     *         werden kann
     */
    public String getNullString(final java.util.Date datum, final String datumsformat) {
        String string = null;
        try {
            if ((datum != null) && (!this.helperAllgemein.isEmptyString(datumsformat))) {
                final SimpleDateFormat sdf = new SimpleDateFormat(datumsformat.trim());
                sdf.setLenient(false);
                string = sdf.format(datum);
            }
        } catch (final Exception e) {
            string = null;
            this.log.warn("Fehler: " + e);
        }
        return (string);
    }

    /**
     * wandelt das uebergebene <code>java.util.Date</code> in einen String im
     * uebergebenen Datumsformat um
     *
     * @param datumsformat
     *            ein den Vorschriften der Klasse
     *            <code>java.text.SimpleDateFormat</code> entsprechender String
     * @return Datum als String, oder Leerstring, falls das Datum nicht
     *         konvertiert werden kann
     */
    public @NonNull String getNotNullString(final java.util.Date datum, final String datumsformat) {
        return (this.getNotNullString(getNullString(datum, datumsformat)));
    }

    /**
     * wandelt den uebergebenen
     * <code>javax.xml.datatype.XMLGregorianCalendar</code> in einen String im
     * uebergebenen Datumsformat um
     *
     * @param datumsformat
     *            ein den Vorschriften der Klasse
     *            <code>java.text.SimpleDateFormat</code> entsprechender String
     * @return Datum als String, oder null, falls das Datum nicht konvertiert
     *         werden kann
     */
    public String getNullString(final XMLGregorianCalendar datum, final String datumsformat) {
        return (getNullString(xmlGregorianCalendarZuUtilDate(datum), datumsformat));
    }

    /**
     * wandelt den uebergebenen
     * <code>javax.xml.datatype.XMLGregorianCalendar</code> in einen String im
     * uebergebenen Datumsformat um
     *
     * @param datumsformat
     *            ein den Vorschriften der Klasse
     *            <code>java.text.SimpleDateFormat</code> entsprechender String
     * @return Datum als String, oder Leerstring, falls das Datum nicht
     *         konvertiert werden kann
     */
    public @NonNull String getNotNullString(final XMLGregorianCalendar datum, final String datumsformat) {
        return (this.getNotNullString(getNullString(datum, datumsformat)));
    }

    /**
     * wandelt die uebergebene Zeit in Millisekunden in einen String im Format
     * hh:mm:ss[.millis] um
     *
     * @param millisekunden
     *            zu formatierende Millisekunden
     * @param showMillis
     *            soll der Ausgabe-String auch [.millis] enthalten?
     *
     * @return String im Format hh:mm:ss[.millis]
     */
    public @NonNull String getNotNullString(final long millisekunden, final boolean showMillis) {
        long sekunden = millisekunden / 1000;
        long minuten = sekunden / 60;
        if (minuten > 0) {
            sekunden = sekunden % 60;
        }
        final long stunden = minuten / 60;
        if (stunden > 0) {
            minuten = minuten % 60;
        }
        DecimalFormat df = new DecimalFormat("00");
        final StringBuffer sb = new StringBuffer();
        if (millisekunden < 0) {
            sb.append("-");
        }
        sb.append(df.format(stunden)).append(":");
        sb.append(df.format(minuten)).append(":");
        sb.append(df.format(sekunden));
        if (showMillis) {
            df = new DecimalFormat("000");
            sb.append(".").append(df.format(millisekunden % 1000));
        }
        final String string = sb.toString();
        if (string == null) {
            throw new RuntimeException();
        }
        return (string);
    }

    /**
     * trimmt den uebergebenen String
     *
     * @param string
     *            String, der getrimmt werden soll
     * @return getrimmter String, oder null, falls null bzw. ein Leerstring
     *         uebergeben wurde
     */
    public String getNullString(final String string) {
        String string1 = null;
        if (!this.helperAllgemein.isEmptyString(string)) {
            string1 = string.trim();
        }
        return (string1);
    }

    /**
     * trimmt den uebergebenen String
     *
     * @param string
     *            String, der getrimmt werden soll
     * @return getrimmter String, oder null, falls null bzw. ein Leerstring
     *         uebergeben wurde
     */
    public String getNullString(final Optional<String> string) {
        String string1 = null;
        if ((string != null) && (string.isPresent())) {
            string1 = string.get();
        }
        return (getNullString(string1));
    }

    /**
     * trimmt den uebergebenen String
     *
     * @param string
     *            String, der getrimmt werden soll
     * @return getrimmter String, oder null, falls null uebergeben wurde;
     *         Unterschied zu <code>getNullString</code>: Leerstrings werden
     *         wieder zurueckgegeben und nicht in null umgewandelt
     */
    public String getGetrimmtenString(final String string) {
        String string1 = null;
        if (string != null) {
            string1 = string.trim();
        }
        return (string1);
    }

    /**
     * trimmt den uebergebenen String
     *
     * @param string
     *            String, der getrimmt werden soll
     * @return getrimmter String, oder Leerstring, falls null uebergeben wurde
     */
    @NonNull
    public String getNotNullString(final String string) {
        return (getNotNullString(string, ""));
    }

    /**
     * trimmt den uebergebenen String
     *
     * @param string
     *            String, der getrimmt werden soll
     * @return getrimmter String, oder Leerstring, falls null uebergeben wurde
     */
    public @NonNull String getNotNullString(final Optional<String> string) {
        return (getNotNullString(getNullString(string)));
    }

    /**
     * trimmt den uebergebenen String
     *
     * @param string
     *            String, der getrimmt werden soll
     *
     * @return getrimmter String, oder default_wert, falls der String null oder
     *         leer ist
     */
    public @NonNull String getNotNullString(final String string, final String default_wert) {
        String string1 = getNullString(string);
        if (string1 == null) {
            string1 = getNullString(default_wert);
            if (string1 == null) {
                string1 = "";
            }
        }
        return (string1);
    }
    
    /**
     * convertiert String in ein BigDecimal mit (dezimalpunkt)Stellen
     */
    public BigDecimal getNullBigDecimal(final String string, final int dezimalpunkt) {
        return (getNullBigDecimal(new Helper().dezimalpunktEinfuegen(string, dezimalpunkt)));
    }

    /**
     * Es wird ein Eintrag in der Map gesucht, ist kein Eintrag vorhanden wird
     * ein Eintrag in die Map hinzugefuegt und eine Liste zuruckgegeben.
     *
     * @throws IllegalArgumentException
     *             if map or key is null
     */
    public <E> List<E> getNotNullInnerListOfMap(final Map<Integer, List<E>> map, final Integer key)
            throws IllegalArgumentException {
        List<E> list = null;
        if ((map != null) && (key != null)) {
            list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
                map.put(key, list);
            }
        } else {
            throw new IllegalArgumentException("Map=" + map + " oder Key " + key + "sind null");
        }
        return list;
    }

    /**
     * entfernt im uebergebenen String alle unnoetigen Leerzeichen, d.h. der
     * String wird getrimmt und jedes Vorkommen von mehr als einem Leerzeichen
     * in Folge wird auf 1 Leerzeichen gekuerzt
     *
     * @param string
     *            zu bearbeitender String
     * @return bereinigter String oder null, falls null uebergeben wurde
     */
    public String getStringOhneUeberschuessigeLeerzeichen(final String string) {
        String string1 = null;
        if (string != null) {
            final StringBuffer sb = new StringBuffer();
            final char[] chars = string.trim().toCharArray();
            if (chars != null) {
                final int length = chars.length;
                boolean zuletzt_leerzeichen = false;
                for (int i = 0; i < length; i++) {
                    final char c = chars[i];
                    boolean anhaengen = true;
                    if (c == ' ') {
                        if (zuletzt_leerzeichen) {
                            anhaengen = false;
                        }
                        zuletzt_leerzeichen = true;
                    } else {
                        zuletzt_leerzeichen = false;
                    }
                    if (anhaengen) {
                        sb.append(String.valueOf(c));
                    }
                }
            }
            string1 = sb.toString();
        }
        return (string1);
    }

    /**
     * entfernt alle Leerzeichen aus dem uebergebenen String
     *
     * @param string
     *            String, aus dem alle Leerzeichen entfernt werden sollen
     *
     * @return String ohne Leerzeichen oder null, falls null uebergeben wurde
     */
    public String getStringOhneLeerzeichen(final String string) {
        String string1 = null;
        if (string != null) {
            string1 = string.trim().replaceAll(" ", "");
        }
        return (string1);
    }

    /**
     * wandelt den String um in einen String mit der uebergebenen Laenge; wenn
     * der uebergebene String kuerzer ist als der gewuenschte: werden links oder
     * rechts Fuellzeichen angehaengt; wenn der uebergeben String zu lang ist,
     * wird rechts abgeschnitten
     *
     * @param string
     *            zu bearbeitender String
     * @param laenge
     *            welche Laenge soll der Rueckgabestring haben?
     * @param fuellzeichen
     *            mit welchem Zeichen soll aufgefuellt werden?
     * @param addRechts
     *            true: Fuellzeichen werden rechts angehaengt; false:
     *            Fuellzeichen werden links angehaengt
     * @return String mit exakt der gewuenschten Laenge
     */
    public @NonNull String getExaktLangenString(String string, final int laenge, final char fuellzeichen,
            final boolean addRechts) {
        if (string == null) {
            string = "";
        } else {
            string = string.trim();
        }
        if (string.length() > laenge) {
            string = string.substring(0, laenge);
        }
        while (string.length() < laenge) {
            if (addRechts) {
                string += fuellzeichen;
            } else {
                string = fuellzeichen + string;
            }
        }
        return (string);
    }

    /**
     * wandelt den String in einen String mit hoechstens der uebergebenen Laenge
     * um; wenn der uebergebene String zu lang ist, wird rechts abgeschnitten
     *
     * @param string
     *            zu bearbeitender String
     * @param laenge
     *            welche Laenge darf der Rueckgabestring hoechstens haben?
     * @return String mit hoechstens der uebergebenen Laenge oder null, falls
     *         als String null bzw. ein Leerstring uebergeben wurde oder der
     *         String selbst, falls als laenge null uebergeben wurde
     */
    public String getMaximalLangenString(String string, final Integer laenge) {
        if (laenge != null) {
            string = getMaximalLangenString(string, laenge.intValue());
        }
        return (string);
    }

    /**
     * wandelt den String in einen String mit hoechstens der uebergebenen Laenge
     * um; wenn der uebergebene String zu lang ist, wird rechts abgeschnitten
     *
     * @param string
     *            zu bearbeitender String
     * @param laenge
     *            welche Laenge darf der Rueckgabestring hoechstens haben?
     * @return String mit hoechstens der uebergebenen Laenge oder null, falls
     *         als String null bzw. ein Leerstring uebergeben wurde
     */
    public String getMaximalLangenString(String string, final int laenge) {
        if (!this.helperAllgemein.isEmptyString(string)) {
            string = string.trim();
            if (string.length() > laenge) {
                string = string.substring(0, laenge);
            }
        }
        return (getNullString(string));
    }

    /**
     * der uebergebene Integer wird in einen String konvertiert
     *
     * @return uebergebener Integer als String, oder null, falls null uebergeben
     *         wurde
     */
    public String getNullString(final Integer integer) {
        String string = null;
        if (integer != null) {
            string = integer.toString();
        }
        return (string);
    }

    /**
     * der uebergebene Integer wird in einen String konvertiert
     *
     * @return uebergebener Integer als String, oder Leerstring, falls null
     *         uebergeben wurde
     */
    public @NonNull String getNotNullString(final Integer integer) {
        return (getNotNullString(getNullString(integer)));
    }

    /**
     * der uebergebene Integer wird in einen String konvertiert
     *
     * @return uebergebener Integer als String, oder "0", falls null uebergeben
     */
    public @NonNull String getNotLeerString(final Integer integer1) {
        String string = getNullString(integer1);
        if (string == null) {
            string = "0";
        }
        return (string);
    }

    /**
     * der uebergebene BigInteger wird in einen String konvertiert
     *
     * @return uebergebener BigInteger als String, oder null, falls null
     *         uebergeben wurde
     */
    public String getNullString(final BigInteger bigInteger) {
        String string = null;
        if (bigInteger != null) {
            string = bigInteger.toString();
        }
        return (string);
    }

    /**
     * der uebergebene BigInteger wird in einen String konvertiert
     *
     * @return uebergebener BigInteger als String, oder Leerstring, falls null
     *         uebergeben wurde
     */
    public @NonNull String getNotNullString(final BigInteger bigInteger) {
        return (getNotNullString(getNullString(bigInteger)));
    }

    /**
     * der uebergebene BigInteger wird in einen String konvertiert
     *
     * @return uebergebener BigInteger als String, oder "0", falls null
     *         uebergeben
     */
    public @NonNull String getNotLeerString(final BigInteger bigInteger) {
        String string = getNullString(bigInteger);
        if (string == null) {
            string = "0";
        }
        return (string);
    }

    /**
     * der uebergebene Long wird in einen String konvertiert
     *
     * @return uebergebener Long als String, oder null, falls null uebergeben
     *         wurde
     */
    public String getNullString(final Long long1) {
        String string = null;
        if (long1 != null) {
            string = long1.toString();
        }
        return (string);
    }

    /**
     * der uebergebene Long wird in einen String konvertiert
     *
     * @return uebergebener Long als String, oder Leerstring, falls null
     *         uebergeben wurde
     */
    public @NonNull String getNotNullString(final Long long1) {
        return (getNotNullString(getNullString(long1)));
    }

    /**
     * der uebergebene Long wird in einen String konvertiert
     *
     * @return uebergebener Long als String, oder "0", falls null uebergeben
     */
    public @NonNull String getNotLeerString(final Long long1) {
        String string = getNullString(long1);
        if (string == null) {
            string = "0";
        }
        return (string);
    }

    /**
     * der uebergebene Double wird in einen String konvertiert
     *
     * @return uebergebener Double als String, oder null, falls null uebergeben
     *         wurde
     */
    public String getNullString(final Double double1) {
        String string = null;
        if (double1 != null) {
            string = double1.toString();
        }
        return (string);
    }

    /**
     * der uebergebene Double wird mit dem uebergebenen
     * <code>java.text.DecimalFormat</code> in einen String konvertiert
     *
     * @return uebergebener Double als String, oder null, falls bei mindestens
     *         einem Parameter null uebergeben wurde
     */
    public String getNullString(final Double double1, final DecimalFormat decimalFormat) {
        String string = null;
        if ((double1 != null) && (decimalFormat != null)) {
            string = decimalFormat.format(double1.doubleValue());
        }
        return (getNullString(string));
    }

    /**
     * der uebergebene Double wird in einen String konvertiert
     *
     * @return uebergebener Double als String, oder Leerstring, falls null
     *         uebergeben wurde
     */
    public @NonNull String getNotNullString(final Double double1) {
        return (getNotNullString(getNullString(double1)));
    }

    /**
     * der uebergebene Double wird mit dem uebergebenen
     * <code>java.text.DecimalFormat</code> in einen String konvertiert
     *
     * @return uebergebener Double als String, oder Leerstring, falls bei
     *         mindestens einem Parameter null uebergeben wurde
     */
    public @NonNull String getNotNullString(final Double double1, final DecimalFormat decimalFormat) {
        String string = null;
        if ((double1 != null) && (decimalFormat != null)) {
            string = decimalFormat.format(double1.doubleValue());
        }
        return (getNotNullString(string));
    }

    /**
     * der uebergebene Double wird in einen String konvertiert
     *
     * @return uebergebener Double als String, oder "0.0", falls null uebergeben
     */
    public @NonNull String getNotLeerString(final Double double1) {
        String string = getNullString(double1);
        if (string == null) {
            string = "0.0";
        }
        return (string);
    }

    /**
     * der uebergebene BigDecimal wird in einen String konvertiert
     *
     * @return uebergebener BigDecimal als String, oder null, falls null
     *         uebergeben wurde
     */
    public String getNullString(final BigDecimal bigDecimal) {
        String string = null;
        if (bigDecimal != null) {
            string = bigDecimal.toString();
        }
        return (string);
    }

    /**
     * der uebergebene BigDecimal wird mit dem uebergebenen
     * <code>java.text.DecimalFormat</code> in einen String konvertiert
     *
     * @return uebergebener BigDecimal als String, oder null, falls bei
     *         mindestens einem Parameter null uebergeben wurde
     */
    public String getNullString(final BigDecimal bigDecimal, final DecimalFormat decimalFormat) {
        String string = null;
        if ((bigDecimal != null) && (decimalFormat != null)) {
            string = decimalFormat.format(bigDecimal);
        }
        return (getNullString(string));
    }

    /**
     * der uebergebene BigDecimal wird in einen String konvertiert
     *
     * @return uebergebener BigDecimal als String, oder Leerstring, falls null
     *         uebergeben wurde
     */
    public @NonNull String getNotNullString(final BigDecimal bigDecimal) {
        return (getNotNullString(getNullString(bigDecimal)));
    }

    /**
     * der uebergebene BigDecimal wird mit dem uebergebenen
     * <code>java.text.DecimalFormat</code> in einen String konvertiert
     *
     * @return uebergebener BigDecimal als String, oder Leerstring, falls bei
     *         mindestens einem Parameter null uebergeben wurde
     */
    public @NonNull String getNotNullString(final BigDecimal bigDecimal, final DecimalFormat decimalFormat) {
        return (getNotNullString(getNullString(bigDecimal, decimalFormat)));
    }

    /**
     * der uebergebene BigDecimal wird in einen String konvertiert
     *
     * @return uebergebener BigDecimal als String, oder "0.0", falls null
     *         uebergeben
     */
    public @NonNull String getNotLeerString(final BigDecimal bigDecimal) {
        String string = getNullString(bigDecimal);
        if (string == null) {
            string = "0.0";
        }
        return (string);
    }

    /**
     * formatiert ein BigDecimal und gibt es als String zurueck
     */
    public @NonNull String getNotLeerStringMitNachkommastellenOptional(final BigDecimal bigDecimal) {
        String string = "0";
        if (bigDecimal != null) {
            string = dfNachkommastellenOptional.format(bigDecimal);
        }
        return (string);
    }

    /**
     * wandelt alle Steuerzeichen im uebergebenen String wie folgt um:
     * Leerzeichen in "&#160;"; Zeilenumbrueche ('\r\n', '\r', '\n') in "<br />
     * "
     *
     * @param string
     *            String, dessen Steuerzeichen veraendert werden sollen
     * @param leerzeichen_ersetzen
     *            sollen Leerzeichen in &#160; umgewandelt werden?
     * @return String mit HTML statt Java-Steuerzeichen oder null, falls null
     *         uebergeben wurde
     */
    public String getNullStringMitHtmlSteuerzeichen(final String string, final boolean leerzeichen_ersetzen) {
        String string1 = null;
        if (!this.helperAllgemein.isEmptyString(string)) {
            string1 = string;
            // Spitzklammern ersetzen
            string1 = string1.replace("<", "&lt;");
            string1 = string1.replace(">", "&gt;");
            // zuerst alle Leerzeichen ersetzen
            if (leerzeichen_ersetzen) {
                string1 = string1.replaceAll(" ", "&#160;");
            }
            // anschliessend alle Zeilenumbrueche
            final String neueZeile = "<br/>";
            // Windows-Format
            string1 = string1.replaceAll("\r\n", neueZeile);
            // Unix-Format
            string1 = string1.replaceAll("\n", neueZeile);
            // Mac-Format
            string1 = string1.replaceAll("\r", neueZeile);
        }
        return (string1);
    }

    /**
     * wandelt alle Steuerzeichen im uebergebenen String wie folgt um:
     * Leerzeichen in "&#160;"; Zeilenumbrueche ('\r\n', '\r', '\n') in "<br />
     * "
     *
     * @param string
     *            String, dessen Steuerzeichen veraendert werden sollen
     * @param leerzeichen_ersetzen
     *            sollen Leerzeichen in &#160; umgewandelt werden?
     * @return String mit HTML statt Java-Steuerzeichen oder Leerstring, falls
     *         null uebergeben wurde
     */
    public @NonNull String getNotNullStringMitHtmlSteuerzeichen(final String string,
            final boolean leerzeichen_ersetzen) {
        return (getNotNullString(getNullStringMitHtmlSteuerzeichen(string, leerzeichen_ersetzen)));
    }

    /**
     * wandelt den uebergebenen String in einen Integer um
     *
     * @return String als Integer, oder null, falls der String nicht konvertiert
     *         werden kann bzw. null oder leer ist
     */
    public Integer getNullInteger(final String string) {
        Integer integer1 = null;
        try {
            if (!this.helperAllgemein.isEmptyString(string)) {
                integer1 = Integer.valueOf(string.trim());
            }
        } catch (final Exception e) {
            integer1 = null;
            this.log.warn("Fehler: " + e);
        }
        return (integer1);
    }

    /**
     * stellt sicher, dass der uebergebene Integer nicht null ist
     *
     * @return uebergebener Integer, oder Integer.valueOf(default_wert), falls
     *         der uebergebene Integer null ist
     */
    public @NonNull Integer getNotNullInteger(Integer integer1, final int default_wert) {
        if (integer1 == null) {
            integer1 = Integer.valueOf(default_wert);
            if (integer1 == null) {
                throw new RuntimeException();
            }
        }
        return (integer1);
    }

    /**
     * wandelt den uebergebenen BigInteger in einen Integer um
     *
     * @return uebergebener BigInteger als Integer, oder null, falls der
     *         uebergebene BigInteger null ist
     */
    public Integer getNullInteger(final BigInteger bigInteger) {
        Integer integer1 = null;
        if (bigInteger != null) {
            integer1 = Integer.valueOf(bigInteger.intValue());
        }
        return (integer1);
    }

    /**
     * wandelt den uebergebenen BigInteger in einen Integer um und stellt dabei
     * sicher, dass der Rueckgabewert nicht null ist
     *
     * @return uebergebener BigInteger als Integer, oder
     *         Integer.valueOf(default_wert), falls der uebergebene BigInteger
     *         null ist
     */
    public @NonNull Integer getNotNullInteger(final BigInteger bigInteger, final int default_wert) {
        Integer integer1 = getNullInteger(bigInteger);
        if (integer1 == null) {
            integer1 = Integer.valueOf(default_wert);
            if (integer1 == null) {
                throw new RuntimeException();
            }
        }
        return (integer1);
    }

    /**
     * wandelt den uebergebenen BigInteger in einen Integer um und stellt dabei
     * sicher, dass der Rueckgabewert nicht null ist
     *
     * @return uebergebener BigInteger als Integer, oder Integer.valueOf(0),
     *         falls der uebergebene BigInteger null ist
     */
    public @NonNull Integer getNotNullInteger(final BigInteger bigInteger) {
        return (getNotNullInteger(bigInteger, 0));
    }

    /**
     * stellt sicher, dass der uebergebene Integer nicht null ist
     *
     * @return uebergebener Integer, oder Integer.valueOf(0), falls der
     *         uebergebene Integer null ist
     */
    public @NonNull Integer getNotNullInteger(final Integer integer1) {
        return (getNotNullInteger(integer1, 0));
    }

    /**
     * wandelt den uebergebenen String in einen Integer um
     *
     * @return String als Integer, oder Integer.valueOf(default_wert), falls der
     *         String nicht konvertiert werden kann bzw. null oder leer ist
     */
    public @NonNull Integer getNotNullInteger(final String string, final int default_wert) {
        return (getNotNullInteger(getNullInteger(string), default_wert));
    }

    /**
     * wandelt den uebergebenen String in einen Integer um
     *
     * @return String als Integer, oder 0, falls der String nicht konvertiert
     *         werden kann bzw. null oder leer ist
     */
    public @NonNull Integer getNotNullInteger(final String string) {
        return (getNotNullInteger(string, 0));
    }

    /**
     * wandelt den uebergebenen String in einen Long um
     *
     * @return String als Long, oder null, falls der String nicht konvertiert
     *         werden kann bzw. null oder leer ist
     */
    public Long getNullLong(final String string) {
        Long long1 = null;
        try {
            if (!this.helperAllgemein.isEmptyString(string)) {
                long1 = Long.valueOf(string.trim());
            }
        } catch (final Exception e) {
            long1 = null;
            this.log.warn("Fehler: " + e);
        }
        return (long1);
    }

    /**
     * stellt sicher, dass der uebergebene Long nicht null ist
     *
     * @return uebergebener Long, oder Long.valueOf(default_wert), falls der
     *         uebergebene Long null ist
     */
    public @NonNull Long getNotNullLong(Long long1, final long default_wert) {
        if (long1 == null) {
            long1 = Long.valueOf(default_wert);
            if (long1 == null) {
                throw new RuntimeException();
            }
        }
        return (long1);
    }

    /**
     * stellt sicher, dass der uebergebene Long nicht null ist
     *
     * @return uebergebener Long, oder Long.valueOf(0), falls der uebergebene
     *         Long null ist
     */
    public @NonNull Long getNotNullLong(final Long long1) {
        return (getNotNullLong(long1, 0));
    }

    /**
     * wandelt den uebergebenen String in einen Long um
     *
     * @return String als Long, oder Long.valueOf(default_wert), falls der
     *         String nicht konvertiert werden kann bzw. null oder leer ist
     */
    public @NonNull Long getNotNullLong(final String string, final long default_wert) {
        return (getNotNullLong(getNullLong(string), default_wert));
    }

    /**
     * wandelt den uebergebenen String in einen Long um
     *
     * @return String als Long, oder 0, falls der String nicht konvertiert
     *         werden kann bzw. null oder leer ist
     */
    public @NonNull Long getNotNullLong(final String string) {
        return (getNotNullLong(string, 0));
    }

    /**
     * wandelt den uebergebenen String in einen Double um
     *
     * @return String als Double, oder null, falls der String nicht konvertiert
     *         werden kann bzw. null oder leer ist
     */
    public Double getNullDouble(final String string) {
        Double double1 = null;
        try {
            if (!this.helperAllgemein.isEmptyString(string)) {
                double1 = Double.valueOf(this.helperAllgemein.kommaZuPunkt(string.trim()));
            }
        } catch (final Exception e) {
            double1 = null;
            this.log.warn("Fehler: " + e);
        }
        return (double1);
    }

    /**
     * stellt sicher, dass der uebergebene Double nicht null ist
     *
     * @return uebergebener Double, oder Double.valueOf(default_wert), falls der
     *         uebergebene Double null ist
     */
    public @NonNull Double getNotNullDouble(Double double1, final double default_wert) {
        if (double1 == null) {
            double1 = Double.valueOf(default_wert);
        }
        return (double1);
    }

    /**
     * stellt sicher, dass der uebergebene Double nicht null ist
     *
     * @return uebergebener Double, oder Double.valueOf(0.0), falls der uebergebene
     *         Double null ist
     */
    public @NonNull Double getNotNullDouble(final Double double1) {
        return (getNotNullDouble(double1, 0.0));
    }

    /**
     * wandelt den uebergebenen String in einen Double um
     *
     * @return String als Double, oder Double(default_wert), falls der
     *         String nicht konvertiert werden kann bzw. null oder leer ist
     */
    public @NonNull Double getNotNullDouble(final String string, final double default_wert) {
        return (getNotNullDouble(getNullDouble(string), default_wert));
    }

    /**
     * wandelt den uebergebenen String in einen Double um
     *
     * @return String als Double, oder 0.0, falls der String nicht konvertiert
     *         werden kann bzw. null oder leer ist
     */
    public @NonNull Double getNotNullDouble(final String string) {
        return (getNotNullDouble(string, 0.0));
    }

    /**
     * wandelt den uebergebenen String in einen BigInteger um
     *
     * @return String als BigInteger, oder null, falls der String nicht
     *         konvertiert werden kann bzw. null oder leer ist
     */
    public BigInteger getNullBigInteger(final String string) {
        BigInteger bigInteger = null;
        try {
            if (!this.helperAllgemein.isEmptyString(string)) {
                bigInteger = new BigInteger(string.trim());
            }
        } catch (final Exception e) {
            bigInteger = null;
            this.log.warn("Fehler: " + e);
        }
        return (bigInteger);
    }

    /**
     * wandelt den uebergebenen String in einen BigInteger um
     *
     * @return String als BigInteger, oder BigInterger.ZERO, falls der String
     *         nicht konvertiert werden kann bzw. null oder leer ist
     */
    public BigInteger getNotNullBigInteger(final String string) {
        return (getNotNullBigInteger(getNullBigInteger(string)));
    }

    /**
     * wandelt den uebergebenen Integer in einen BigInteger um
     *
     * @return Integer als BigInteger, oder null, falls der Integer null ist
     */
    public BigInteger getNullBigInteger(final Integer integer) {
        BigInteger bigInteger = null;
        if (integer != null) {
            bigInteger = BigInteger.valueOf(integer.longValue());
        }
        return (bigInteger);
    }

    /**
     * wandelt den uebergebenen Integer in einen BigInteger um
     *
     * @return Integer als BigInteger, oder BigInterger.ZERO, falls der Integer
     *         null ist
     */
    public BigInteger getNotNullBigInteger(final Integer integer) {
        return (getNotNullBigInteger(getNullBigInteger(integer)));
    }

    /**
     * wandelt den uebergebenen Long in einen BigInteger um
     *
     * @return Long als BigInteger, oder null, falls der Long null ist
     */
    public BigInteger getNullBigInteger(final Long long1) {
        BigInteger bigInteger = null;
        if (long1 != null) {
            bigInteger = BigInteger.valueOf(long1.longValue());
        }
        return (bigInteger);
    }

    /**
     * wandelt den uebergebenen Long in einen BigInteger um
     *
     * @return Long als BigInteger, oder BigInteger.ZERO, falls der Long null
     *         ist
     */
    public BigInteger getNotNullBigInteger(final Long long1) {
        return (getNotNullBigInteger(getNullBigInteger(long1)));
    }

    /**
     * gibt einen BigInteger zurueck, der nicht null ist; bei Uebergabe von null
     * wird BigInteger.ZERO zurueckgegeben
     *
     * @return bigInteger, oder BigInteger.ZERO, falls bigInteger null ist
     */
    public BigInteger getNotNullBigInteger(final BigInteger bigInteger) {
        return (getNotNullBigInteger(bigInteger, BigInteger.ZERO));
    }

    /**
     * gibt einen BigInteger zurueck, der nicht null ist; bei Uebergabe von null
     * wird default_wert zurueckgegeben; ist default_wert auch null wird
     * BigInteger.ZERO zurueckgegeben
     *
     * @return bigInteger, oder default_wert, falls bigInteger null ist, oder
     *         BigInteger.ZERO, falls auch default_wert null ist
     */
    public BigInteger getNotNullBigInteger(final BigInteger bigInteger, final BigInteger default_wert) {
        BigInteger rueckgabe = default_wert;
        if (bigInteger != null) {
            rueckgabe = bigInteger;
        }
        if (rueckgabe == null) {
            rueckgabe = BigInteger.ZERO;
        }
        return (rueckgabe);
    }

    /**
     * wandelt den uebergebenen String in einen BigDecimal um
     *
     * @return String als BigDecimal, oder null, falls der String nicht
     *         konvertiert werden kann bzw. null oder leer ist
     */
    public BigDecimal getNullBigDecimal(final String string) {
        BigDecimal bigDecimal = null;
        if (!this.helperAllgemein.isEmptyString(string)) {
            try {
                bigDecimal = new BigDecimal(this.helperAllgemein.kommaZuPunkt(string));
            } catch (final Exception e) {
                bigDecimal = null;
                this.log.warn("Fehler: " + e);
            }
        }
        return (bigDecimal);
    }

    /**
     * wandelt den uebergebenen String in einen BigDecimal um
     *
     * @return String als BigDecimal, oder BigDecimal.ZERO, falls der String
     *         nicht konvertiert werden kann bzw. null oder leer ist
     */
    public BigDecimal getNotNullBigDecimal(final String string) {
        return (getNotNullBigDecimal(getNullBigDecimal(string)));
    }

    /**
     * wandelt den uebergebenen Integer in einen BigDecimal um
     *
     * @return Integer als BigDecimal, oder null, falls der Integer null ist
     */
    public BigDecimal getNullBigDecimal(final Integer integer1) {
        BigDecimal bigDecimal = null;
        if (integer1 != null) {
            bigDecimal = BigDecimal.valueOf(integer1.intValue());
        }
        return (bigDecimal);
    }

    /**
     * wandelt den uebergebenen Integer in einen BigDecimal um
     *
     * @return Integer als BigDecimal, oder BigDecimal.ZERO, falls der Integer null
     *         ist
     */
    public BigDecimal getNotNullBigDecimal(final Integer integer1) {
        return (getNotNullBigDecimal(getNullBigDecimal(integer1)));
    }

    /**
     * wandelt den uebergebenen Long in einen BigDecimal um
     *
     * @return Long als BigDecimal, oder null, falls der Long null ist
     */
    public BigDecimal getNullBigDecimal(final Long long1) {
        BigDecimal bigDecimal = null;
        if (long1 != null) {
            bigDecimal = BigDecimal.valueOf(long1.longValue());
        }
        return (bigDecimal);
    }

    /**
     * wandelt den uebergebenen Long in einen BigDecimal um
     *
     * @return Long als BigDecimal, oder BigDecimal.ZERO, falls der Long null
     *         ist
     */
    public BigDecimal getNotNullBigDecimal(final Long long1) {
        return (getNotNullBigDecimal(getNullBigDecimal(long1)));
    }

    /**
     * wandelt den uebergebenen Double in einen BigDecimal um
     *
     * @return Double als BigDecimal, oder null, falls der Double null ist
     */
    public BigDecimal getNullBigDecimal(final Double double1) {
        BigDecimal bigDecimal = null;
        if (double1 != null) {
            bigDecimal = getNullBigDecimal(getNullString(double1));
        }
        return (bigDecimal);
    }

    /**
     * wandelt den uebergebenen Double in einen BigDecimal um
     *
     * @return Double als BigDecimal, oder BigDecimal.ZERO, falls der Double
     *         null ist
     */
    public BigDecimal getNotNullBigDecimal(final Double double1) {
        return (getNotNullBigDecimal(getNullBigDecimal(double1)));
    }

    /**
     * gibt einen BigDecimal zurueck, der nicht null ist; bei Uebergabe von null
     * wird BigDecimal.ZERO zurueckgegeben
     *
     * @return bigDecimal, oder BigDecimal.ZERO, falls bigDecimal null ist
     */
    public BigDecimal getNotNullBigDecimal(final BigDecimal bigDecimal) {
        return (getNotNullBigDecimal(bigDecimal, BigDecimal.ZERO));
    }

    /**
     * gibt einen BigDecimal zurueck, der nicht null ist; bei Uebergabe von null
     * wird default_wert zurueckgegeben; ist default_wert auch null wird
     * BigDecimal.ZERO zurueckgegeben
     *
     * @return bigDecimal, oder default_wert, falls bigDecimal null ist, oder
     *         BigDecimal.ZERO, falls auch default_wert null ist
     */
    public BigDecimal getNotNullBigDecimal(final BigDecimal bigDecimal, final BigDecimal default_wert) {
        BigDecimal rueckgabe = default_wert;
        if (bigDecimal != null) {
            rueckgabe = bigDecimal;
        }
        if (rueckgabe == null) {
            rueckgabe = BigDecimal.ZERO;
        }
        return (rueckgabe);
    }

    /**
     * wandelt den uebergebenen String in einen int um
     *
     * @return String als int, oder default_wert, falls der String nicht
     *         konvertiert werden kann bzw. null oder leer ist
     */
    public int parseInt(final String string, final int default_wert) {
        return (getNotNullInteger(string, default_wert).intValue());
    }

    /**
     * wandelt den uebergebenen String in einen int um
     *
     * @return String als int, oder 0, falls der String nicht konvertiert werden
     *         kann bzw. null oder leer ist
     */
    public int parseInt(final String string) {
        return (parseInt(string, 0));
    }

    /**
     * wandelt den uebergebenen String in einen long um
     *
     * @return String als long, oder default_wert, falls der String nicht
     *         konvertiert werden kann bzw. null oder leer ist
     */
    public long parseLong(final String string, final long default_wert) {
        return (getNotNullLong(string, default_wert).longValue());
    }

    /**
     * wandelt den uebergebenen String in einen long um
     *
     * @return String als long, oder 0, falls der String nicht konvertiert
     *         werden kann bzw. null oder leer ist
     */
    public long parseLong(final String string) {
        return (parseLong(string, 0));
    }

    /**
     * wandelt den uebergebenen String in einen double um
     *
     * @return String als double, oder default_wert, falls der String nicht
     *         konvertiert werden kann bzw. null oder leer ist
     */
    public double parseDouble(final String string, final double default_wert) {
        return (getNotNullDouble(string, default_wert).doubleValue());
    }

    /**
     * wandelt den uebergebenen String in einen double um
     *
     * @return String als double, oder 0.0, falls der String nicht konvertiert
     *         werden kann bzw. null oder leer ist
     */
    public double parseDouble(final String string) {
        return (parseDouble(string, 0.0));
    }

    /**
     * gibt ein Datum ohne Zeit retour
     *
     * @param datum
     *            - java.sql.Date
     * @return - Datum ohne Uhrzeit falls datum uebergeben wurde, ansonsten null
     */
    public java.sql.Date getNullSqlDateOhneUhrzeit(final java.sql.Date datum) {
        java.sql.Date datumOhneZeit = null;
        if (datum != null) {
            final String datumsFormat = Constants_Datumsformat.DATUMSFORMAT_MIT_PUNKTEN;
            datumOhneZeit = this.getNullSqlDate(this.getNotNullString(datum, datumsFormat), datumsFormat);
        }
        return (datumOhneZeit);
    }

    /**
     * gibt ein Datum ohne Zeit retour
     *
     * @param datum
     *            - java.util.Date
     * @return - Datum ohne Uhrzeit falls datum uebergeben wurde, ansonsten null
     */
    public java.util.Date getNullUtilDateOhneUhrzeit(final java.util.Date datum) {
        return (getNullSqlDateOhneUhrzeit(utilDateZuSqlDate(datum)));
    }

    /**
     * gibt ein Datum ohne Zeit retour
     *
     * @param datum
     *            - umzuwandelnder XMLGregorianCalendar
     * @return - Datum ohne Uhrzeit falls datum uebergeben wurde, ansonsten null
     */
    public java.sql.Date getNullSqlDateOhneUhrzeit(final XMLGregorianCalendar datum) {
        return (getNullSqlDateOhneUhrzeit(xmlGregorianCalendarZuSqlDate(datum)));
    }

    /**
     * Gibt aus dem uebergebenen String den gewuenschten Substring zurueck.
     * Sollten die Parameter nicht stimmig sein (leerer Text oder Indizes
     * ausserhalb des Textes oder beginnIndex > endeIndex wird ein Leerstring
     * zurueckgegeben. Der Substring wird getrimmt.
     */
    public @NonNull String getNotNullSubstringGetrimmt(final String string, final int beginnIndex,
            final int endeIndex) {
        return (getNotNullString(getNullSubstring(string, beginnIndex, endeIndex)));
    }

    /**
     * Gibt aus dem uebergebenen String den gewuenschten Substring zurueck.
     * Sollten die Parameter nicht stimmig sein (leerer Text oder Indizes
     * ausserhalb des Textes oder beginnIndex > endeIndex wird ein Leerstring
     * zurueckgegeben. Der Substring wird NICHT getrimmt.
     */
    public @NonNull String getNotNullSubstring(final String string, final int beginnIndex, final int endeIndex) {
        String substring = getNullSubstring(string, beginnIndex, endeIndex);
        if (substring == null) {
            substring = "";
        }
        return (substring);
    }

    /**
     * Gibt aus dem uebergebenen String den gewuenschten Substring zurueck.
     * Sollten die Parameter nicht stimmig sein (leerer Text oder Indizes
     * ausserhalb des Textes oder beginnIndex > endeIndex wird null
     * zurueckgegeben. Der Substring wird getrimmt.
     */
    public String getNullSubstringGetrimmt(final String string, final int beginnIndex, final int endeIndex) {
        return (getNullString(getNullSubstring(string, beginnIndex, endeIndex)));
    }

    /**
     * Gibt aus dem uebergebenen String den gewuenschten Substring zurueck.
     * Sollten die Parameter nicht stimmig sein (leerer Text oder Indizes
     * ausserhalb des Textes oder beginnIndex > endeIndex wird null
     * zurueckgegeben. Der Substring wird NICHT getrimmt.
     */
    public String getNullSubstring(final String string, final int beginnIndex, final int endeIndex) {
        String substring = null;
        if ((string != null) && (beginnIndex < endeIndex) && (beginnIndex >= 0) && (endeIndex <= string.length())) {
            substring = string.substring(beginnIndex, endeIndex);
        }
        return (substring);
    }

    /**
     * Konvertiert als String (ohne Trennzeichen, ev. mit Vorzeichen '-')
     * uebergebenen Betrag in Double mit 0, 2 oder 4 Kommastellen und liefert
     * ihn zurueck, sonst 0.0
     */
    public @NonNull Double getNotNullDoubleAusStringOhneTrennzeichen(final String betrag,
            final int anzahl_kommastellen) {
        Double betrag_d = this.getNullDoubleAusStringOhneTrennzeichen(betrag, anzahl_kommastellen);
        if (betrag_d == null) {
            betrag_d = Double.valueOf(0.0);
        }
        return (betrag_d);
    }

    /**
     * Konvertiert als String (ohne Trennzeichen, ev. mit Vorzeichen '-')
     * uebergebenen Betrag in Double mit 0, 2 oder 4 Kommastellen und liefert
     * ihn zurueck, sonst null
     */
    public Double getNullDoubleAusStringOhneTrennzeichen(String betrag, final int anzahl_kommastellen) {
        Double betrag_d = null;
        final boolean vorzeichen = this.helperAllgemein.isStringNumerischMitVorzeichen(betrag);
        if ((this.helperAllgemein.isStringNumerisch(betrag)) || (vorzeichen)) {
            double div = 1.0;
            if (vorzeichen) {
                betrag = betrag.substring(1);
            }
            if (anzahl_kommastellen == 2) {
                div = 100.0;
            } else if (anzahl_kommastellen == 4) {
                div = 10000.0;
            }
            double wert = this.parseDouble(betrag) / div;
            if (vorzeichen) {
                wert = wert * (-1);
            }
            betrag_d = Double.valueOf(wert);
        }
        return (betrag_d);
    }

    /**
     * Konvertiert BigDecimal in einen exakt langen String mit minus-Zeichen an
     * der 0.Stelle (falls BigDecimal einen negativen Betrag aufweist) und
     * fuehrenden Nullen.
     */
    public String getExaktLangenStringOhneTrennzeichen(final BigDecimal betrag, int laenge) {
        String st = null;
        if ((betrag != null) && (laenge > 0)) {
            final Rechner rechner = new Rechner();
            final StringBuffer stringBuffer = new StringBuffer();
            BigDecimal betrag1 = betrag;
            if (rechner.isBetragNegativ(betrag)) {
                laenge--;
                betrag1 = betrag1.negate();
                stringBuffer.append("-");
            }
            String betrag_st = betrag1.toString();
            final int index = betrag_st.indexOf(".");
            if (index > -1) {
                betrag_st = betrag_st.substring(0, index) + betrag_st.substring(index + 1);
            }
            stringBuffer.append(this.getExaktLangenString(betrag_st, laenge, '0', false));
            st = stringBuffer.toString();
        }
        return (st);
    }

    /**
     * Ueberpruefe den String ob er 6-stellig ist und den Kriterien enstpricht
     * Jahr 4-Stellig und Monat 1-12 oder Quartal 1-4 z.B. 201110 oder 2011Q4
     * Falls ueberpruefung fehlerhaft ist wird null zurueckgegeben ansonsten das
     * korrekte Datum
     */
    public String getNullString(final String zeitraum, final String intervall) {
        String gueltigerZeitraum = null;
        if (!this.helperAllgemein.isEmptyString(zeitraum) && !this.helperAllgemein.isEmptyString(intervall)) {
            if (zeitraum.length() == 6) {
                final int jahr = getNotNullInteger(zeitraum.substring(0, 4)).intValue();
                if (jahr >= 1900) {
                    final String monat_quartal = zeitraum.substring(4, 6).toUpperCase();
                    if ("monat".equalsIgnoreCase(intervall)) {
                        int monat = 0;
                        monat = getNotNullInteger(monat_quartal).intValue();
                        if ((monat >= 1) && (monat <= 12)) {
                            monat = (monat + 2) / 3;
                            gueltigerZeitraum = new StringBuffer().append(jahr).append("2").append(monat).toString();
                        } else {
                            this.log.warn("Zeitraum fuer Monat wurde falsch angegeben: Moegliche Werte 1 - 12");
                        }
                    } else if ("quartal".equalsIgnoreCase(intervall)) {
                        if (monat_quartal.indexOf("Q") != -1) {
                            if (monat_quartal.length() == 2) {
                                int quartal = 0;
                                quartal = getNotNullInteger(monat_quartal.substring(1)).intValue();
                                if ((quartal >= 1) && (quartal <= 4)) {
                                    gueltigerZeitraum = new StringBuffer().append(jahr).append("2").append(quartal)
                                            .toString();
                                } else {
                                    this.log.warn(
                                            "Zeitraum fuer Intervall wurde falsch angegeben: Moeglich Werte Q1 Q2 Q3 Q4 ");
                                }
                            }
                        }
                    } else {
                        this.log.warn("Intervall wurde nicht richtig angegeben. Moegliche Werte Monat oder Quartal ");
                    }
                } else {
                    this.log.warn("Das Jahr muss groesser gleich 1900 angegeben werden =" + jahr);
                }
            } else {
                this.log.warn("Zeitraum wurde nicht 6-stellig angegeben zeitraum=" + zeitraum);
            }
        }
        return gueltigerZeitraum;
    }

    /**
     * Baut aus einer Liste einen String, getrennt duch @param Trennzeichen Bsp:
     * Aus [0]foo , [1]bar, trennzeichen: "-"
     *
     * wird: foo-bar
     *
     * @return zusammengefuegter String
     */
    public String listeToString(final List<String> stringListe, final String trennzeichen) {
        final StringBuffer sb = new StringBuffer();
        for (final String string : stringListe) {
            if ((string != null) && (trennzeichen != null)) {
                if ((stringListe.size() > 1) && (sb.length() > 1)) {
                    sb.append(trennzeichen);
                }
                sb.append(string);
            }
        }
        return sb.toString();
    }

    /**
     *
     * Substring, bei der die Stringlaenge nicht geprueft werden muss. Sollte
     * der eingelesene <code>eingabeString</code> zu kurz sein, wird bis
     * Stringende gelesen.
     *
     * @param eingabeString
     *            String aus dem gelesen werden soll
     * @param start
     *            start von Substring
     * @param length
     *            Anzahl der zeichen die gelesen werden sollen
     * @return gelesener String;
     *
     */
    public String safeSubstr(final String eingabeString, final int start, final int length) {
        return safeSubstr(eingabeString, start, length, true);
    }

    /**
     *
     * Substring, bei der die Stringlaenge nicht geprueft werden muss. Sollte
     * der eingelesene <code>eingabeString</code> zu kurz sein, wird bis
     * Stringende gelesen.
     *
     * @param eingabeString
     *            String aus dem gelesen werden soll
     * @param start
     *            start von Substring
     * @param length
     *            Anzahl der zeichen die gelesen werden sollen
     * @param trim
     *            Soll String getrimmt werden
     *
     * @return gelesener String;
     *
     */
    public String safeSubstr(final String eingabeString, int start, final int length, final boolean trim) {
        String returnVal = null;
        int newLength = length;
        if ((eingabeString != null)) {
            final int zeileLength = eingabeString.length();
            if (start >= zeileLength) {
                newLength = 0;
                start = 0;
                this.log.debug("eingabeString ist kurz[" + zeileLength + "]; lese nur " + newLength
                        + ", Zeichen anstatt " + length + " ab " + start);
            } else if ((start + newLength) > zeileLength) {
                newLength = zeileLength - start;
                this.log.debug("eingabeString ist kurz[" + zeileLength + "]; lese nur " + newLength
                        + " Zeichen anstatt " + length + " ab " + start);
            }
            if (trim) {
                returnVal = this.getNullString(eingabeString.substring(start, (start + newLength)));
            } else {
                returnVal = eingabeString.substring(start, (start + newLength));
            }
        }
        if ("".equalsIgnoreCase(returnVal)) {
            returnVal = null;
        }
        return returnVal;
    }

    /**
     * * Substring, bei der die Stringlaenge nicht geprueft werden muss. Sollte
     * der eingelesene <code>eingabeString</code> zu kurz sein, wird bis
     * Stringende gelesen.
     *
     * @param eingabeString
     *            String aus dem gelesen werden soll
     * @param start
     *            start von Substring
     * @param length
     *            Anzahl der zeichen die gelesen werden sollen
     * @param offset
     *            Anzahl an Zeichen, die uebersprungen werden, absolut zum
     *            Beginn des Stringes
     * @return gelesener String; null bei leerem String
     */
    public String safeSubstr(final String eingabeString, final int start, final int length, int offset) {
        if ((start + offset) < 0) {
            offset = 0;
            this.log.error("'start' oder 'offset' ist zu klein: Kann nicht aus negativer Zeichennummer lesen!");
        }
        return safeSubstr(eingabeString, start + offset, length);
    }

    /**
     * @param sourceString
     *            Orignialer String
     * @param fuellung
     *            String, der Eingefuegt werden soll
     * @param position
     *            Position, an der eingefuegt werden soll
     * @param ueberschreiben
     *            true: es wird der Orignaile String ueberschrieben, false er
     *            wird verlaengert
     *
     * @return String
     */
    public String safeReplace(final String sourceString, final String fuellung, final int position,
            final boolean ueberschreiben) {
        final StringBuffer returnVal = new StringBuffer();
        if (!this.helperAllgemein.isEmptyString(sourceString) && !this.helperAllgemein.isEmptyString(fuellung)) {
            final int sourceStringLength = sourceString.length();
            final int fuellungLength = fuellung.length();
            if (!ueberschreiben) {
                final String part1 = safeSubstr(sourceString, 0, position, false);
                final String part3 = safeSubstr(sourceString, position, sourceStringLength - position, false);
                returnVal.append(getStringNotNull(part1));
                returnVal.append(getStringNotNull(fuellung));
                returnVal.append(getStringNotNull(part3));
            } else {
                final String part1 = safeSubstr(sourceString, 0, position, false);
                final String part3 = safeSubstr(sourceString, position + fuellungLength,
                        sourceStringLength - (position + fuellungLength), false);
                returnVal.append(getStringNotNull(part1));
                returnVal.append(getStringNotNull(fuellung));
                returnVal.append(getStringNotNull(part3));
            }
        }
        return returnVal.toString();
    }

    /**
     * @param eingabe
     *            String
     * @return leerer String wenn <code>eingabe</code> Null, oder die Eingabe
     *         wenn sie nicht null
     */
    public String getStringNotNull(final String eingabe) {
        String returnVal;
        if (eingabe == null) {
            returnVal = "";
        } else {
            returnVal = eingabe;
        }
        return returnVal;
    }

    /**
     * Gibt anhand eines Flags (false, true) einen String der Form J oder N
     * zurueck; bei Uebergabe von null wird N zurueckgegeben
     *
     * @return N oder J, null-safe
     */
    public String getJaNein(final Boolean boolean1) {
        String rueckgabe = "N";
        if (new Rechner().istTrue(boolean1)) {
            rueckgabe = "J";
        }
        return (rueckgabe);
    }

    /**
     * Gibt anhand des String N oder J den zugehoerigen Boolean (false oder
     * true) zurueck; bei Uebergabe von null oder einem Leerstring wird false
     * zurueckgegeben
     *
     * @param janein
     *            N oder J
     * @return false oder true, null-safe
     */
    public Boolean getJaNein(final String janein) {
        return (Boolean.valueOf((janein != null) && (janein.trim().equalsIgnoreCase("J"))));
    }

    /**
     * Es wird ein Eintrag in der Map gesucht, ist kein Eintrag vorhanden wird
     * ein neuer Eintrag in die Map hinzugefuegt und ein Objekt zuruckgegeben.
     *
     */
    public <T, E> T getNotNullValueOfMap(final Map<E, T> map, final E key, final Class<T> clazz)
            throws IllegalArgumentException, InstantiationException, IllegalAccessException {
        T element;
        if ((map != null) && (key != null) && (clazz != null)) {
            element = map.get(key);
            if (element == null) {
                element = this.helperAllgemein.getGenericObject(clazz.newInstance());
                map.put(key, element);
            }
        } else {
            throw (new IllegalArgumentException("Map=" + map + " oder Key " + key + "sind null"));
        }
        return element;
    }

    /**
     * Retuniert den Wert falls ungleich NULL sonst 0
     */
    public long getKleinenLong(final Long long1) {
        return long1 != null ? long1.longValue() : 0;
    }

    /**
     * Retuniert den Wert falls ungleich NULL sonst 0
     */
    public int getKleinenInt(final Integer value) {
        return value != null ? value.intValue() : 0;
    }

    /**
     * der uebergebene BigDecimal wird in einen String konvertiert und ein Punkt
     * wird durch ein Komma ausgetauscht
     *
     * @return string
     */
    public String getNotNullStringMitKomma(final BigDecimal bd) {
        String returnValue = "0.00";
        if (bd != null) {
            returnValue = df_jsp_anzeige.format(bd.doubleValue());
        }
        returnValue = getNotNullString(returnValue).replace(".", ",");
        return (returnValue);
    }

    /**
     * liefert ein sqlDate aus Timestamp
     *
     * @return Date oder Null
     */
    public Date sqlTimestampZuSqlDate(final Timestamp timestamp) {
        Date date = null;
        if (timestamp != null) {
            date = new Date(timestamp.getTime());
        }
        return (date);
    }

    /**
     * prueft ob ein Datum richtig eingegeben wurde
     */
    public boolean isDatumEingabeKorrekt(final String datum) {
        boolean b = false;
        final Date date = this.getNullSqlDate(datum, Constants_Datumsformat.DATUMSFORMAT_MIT_PUNKTEN);
        if ((this.helperAllgemein.isEmptyString(datum)) || (isDatumEingabeKorrekt(date))) {
            b = true;
        }
        return (b);
    }

    /**
     * prueft ob ein Datum richtig eingegeben wurde
     */
    public boolean isDatumEingabeKorrekt(final java.util.Date date) {
        boolean b = false;
        if ((date == null) || (isJahrEingabeOK(utilDateZuSqlDate(date)))) {
            b = true;
        }
        return (b);
    }

    /**
     * Jahr soll >= 1900 sein
     */
    public boolean isJahrEingabeOK(final java.sql.Date date) {
        boolean b = false;
        if (getJahr(date) >= Constants_Werte.MIN_JAHR_IN_DATUM) {
            b = true;
        }
        return (b);
    }

    /**
     * liefert das Jahr aus ein Datum
     */
    public int getJahr(final java.sql.Date date) {
        int jahr = 0;
        final GregorianCalendar cal = this.sqlDateZuGregorianCalendar(date);
        if (cal != null) {
            jahr = cal.get(Calendar.YEAR);
        }
        return (jahr);
    }
}
