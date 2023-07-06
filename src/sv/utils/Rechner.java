package sv.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.jdt.annotation.Nullable;

/**
 * Hilfsklasse fuer diverse Rechenoperationen mit Objekten
 *
 * @author SV-Benutzer
 */
public class Rechner {
    private final HelperAllgemein helperAllgemein;

    private final TypConverter typConverter;

    /**
     * Default-Konstruktor
     */
    public Rechner() {
        this.helperAllgemein = new HelperAllgemein();
        this.typConverter = new TypConverter();
    }

    /**
     * addiert die beiden uebergebenen Integer-Werte und gibt das Ergebnis als
     * neuer Integer zurueck, uebergebene Nullwerte werden als
     * Integer.valueOf(0) behandelt
     *
     * @return Summe der beiden uebergebenen Integer-Werte
     */
    public Integer addiere(final Integer integer1, final Integer integer2) {
        Integer integer1a = integer1;
        Integer integer2a = integer2;
        if (integer1a == null) {
            integer1a = Integer.valueOf(0);
        }
        if (integer2a == null) {
            integer2a = Integer.valueOf(0);
        }
        return (Integer.valueOf(integer1a.intValue() + integer2a.intValue()));
    }

    /**
     * addiert die beiden uebergebenen Integer-Werte und gibt das Ergebnis als
     * neuer Integer zurueck, uebergebene Nullwerte werden als
     * Integer.valueOf(0) behandelt
     *
     * @return Summe der beiden uebergebenen Integer-Werte
     */
    public Integer addiere(final Integer integer1, final int int2) {
        return (addiere(integer1, Integer.valueOf(int2)));
    }

    /**
     * addiert die beiden uebergebenen Long-Werte und gibt das Ergebnis als
     * neuer Long zurueck, uebergebene Nullwerte werden als Long.valueOf(0)
     * behandelt
     *
     * @return Summe der beiden uebergebenen Long-Werte
     */
    public Long addiere(final Long long1, final Long long2) {
        Long long1a = long1;
        Long long2a = long2;
        if (long1a == null) {
            long1a = Long.valueOf(0);
        }
        if (long2a == null) {
            long2a = Long.valueOf(0);
        }
        return (Long.valueOf(long1a.longValue() + long2a.longValue()));
    }

    /**
     * addiert die beiden uebergebenen Long-Werte und gibt das Ergebnis als
     * neuer Long zurueck, uebergebene Nullwerte werden als Long.valueOf(0)
     * behandelt
     *
     * @return Summe der beiden uebergebenen Long-Werte
     */
    public Long addiere(final Long long1, final long long2) {
        return (addiere(long1, Long.valueOf(long2)));
    }

    /**
     * addiert die beiden uebergebenen Double-Werte und gibt das Ergebnis als
     * neuer Double zurueck, uebergebene Nullwerte werden als
     * Double.valueOf(0.0) behandelt
     *
     * @return Summe der beiden uebergebenen Double-Werte
     */
    public Double addiere(final Double double1, final Double double2) {
        Double double1a = double1;
        Double double2a = double2;
        if (double1a == null) {
            double1a = Double.valueOf(0.0);
        }
        if (double2a == null) {
            double2a = Double.valueOf(0.0);
        }
        return (Double.valueOf(double1a.doubleValue() + double2a.doubleValue()));
    }

    /**
     * addiert die beiden uebergebenen Double-Werte und gibt das Ergebnis als
     * neuer Double zurueck, uebergebene Nullwerte werden als
     * Double.valueOf(0.0) behandelt
     *
     * @return Summe der beiden uebergebenen Double-Werte
     */
    public Double addiere(final Double double1, final double double2) {
        return (addiere(double1, Double.valueOf(double2)));
    }

    /**
     * addiert die beiden uebergebenen BigInteger-Werte und gibt das Ergebnis
     * als neuer BigInteger zurueck, uebergebene Nullwerte werden als
     * BigInteger.ZERO behandelt
     *
     * @return Summe der beiden uebergebenen BigInteger-Werte
     */
    public BigInteger addiere(BigInteger bigInteger1, BigInteger bigInteger2) {
        if (bigInteger1 == null) {
            bigInteger1 = BigInteger.ZERO;
        }
        if (bigInteger2 == null) {
            bigInteger2 = BigInteger.ZERO;
        }
        return (bigInteger1.add(bigInteger2));
    }

    /**
     * addiert die beiden uebergebenen BigDecimal-Werte und gibt das Ergebnis
     * als neuer BigDecimal zurueck, uebergebene Nullwerte werden als
     * BigDecimal.ZERO behandelt
     *
     * @return Summe der beiden uebergebenen BigDecimal-Werte
     */
    public BigDecimal addiere(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        if (bigDecimal1 == null) {
            bigDecimal1 = BigDecimal.ZERO;
        }
        if (bigDecimal2 == null) {
            bigDecimal2 = BigDecimal.ZERO;
        }
        return (bigDecimal1.add(bigDecimal2));
    }

    /**
     * zieht den Wert integer2 vom Wert integer1 ab und gibt das Ergebnis als
     * neuer Integer zurueck, uebergebene Nullwerte werden als
     * Integer.valueOf(0) behandelt
     *
     * @return Integer.valueOf(integer1 - integer2)
     */
    public Integer subtrahiere(final Integer integer1, final Integer integer2) {
        Integer integer1a = integer1;
        Integer integer2a = integer2;
        if (integer1a == null) {
            integer1a = Integer.valueOf(0);
        }
        if (integer2a == null) {
            integer2a = Integer.valueOf(0);
        }
        return (Integer.valueOf(integer1a.intValue() - integer2a.intValue()));
    }

    /**
     * zieht den Wert double2 vom Wert double1 ab und gibt das Ergebnis als
     * neuer Double zurueck, uebergebene Nullwerte werden als
     * Double.valueOf(0.0) behandelt
     *
     * @return Double.valueOf(double1 - double2)
     */
    public Double subtrahiere(final Double double1, final Double double2) {
        Double double1a = double1;
        Double double2a = double2;
        if (double1a == null) {
            double1a = Double.valueOf(0.0);
        }
        if (double2a == null) {
            double2a = Double.valueOf(0.0);
        }
        return (Double.valueOf(double1a.doubleValue() - double2a.doubleValue()));
    }

    /**
     * zieht den Wert bigDecimal2 vom Wert bigDecimal1 ab und gibt das Ergebnis
     * als neuer BigDecimal zurueck, uebergebene Nullwerte werden als
     * BigDecimal.ZERO behandelt
     *
     * @return new BigDecimal(bigDecimal1 - bigDecimal2)
     */
    public BigDecimal subtrahiere(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        if (bigDecimal1 == null) {
            bigDecimal1 = BigDecimal.ZERO;
        }
        if (bigDecimal2 == null) {
            bigDecimal2 = BigDecimal.ZERO;
        }
        return (bigDecimal1.subtract(bigDecimal2));
    }

    /**
     * multipliziert die beiden uebergebenen Double-Werte und gibt das Ergebnis
     * als neuer Double zurueck, uebergebene Nullwerte werden als
     * Double.valueOf(0.0) behandelt
     *
     * @return Produkt der beiden uebergebenen Double-Werte
     */
    public Double multipliziere(final Double double1, final Double double2) {
        Double double1a = double1;
        Double double2a = double2;
        if (double1a == null) {
            double1a = Double.valueOf(0.0);
        }
        if (double2a == null) {
            double2a = Double.valueOf(0.0);
        }
        return (Double.valueOf(double1a.doubleValue() * double2a.doubleValue()));
    }

    /**
     * multipliziert die beiden uebergebenen BigDecimal-Werte und gibt das
     * Ergebnis als neuer BigDecimal zurueck, uebergebene Nullwerte werden als
     * BigDecimal.ZERO) behandelt
     *
     * @return Produkt der beiden uebergebenen BigDecimal-Werte
     */
    public BigDecimal multipliziere(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        if (bigDecimal1 == null) {
            bigDecimal1 = BigDecimal.ZERO;
        }
        if (bigDecimal2 == null) {
            bigDecimal2 = BigDecimal.ZERO;
        }
        return (bigDecimal1.multiply(bigDecimal2));
    }

    /**
     * dividiert d1 durch d2 und gibt das Ergebnis als neuer Double zurueck, bei
     * uebergebenen Nullwerten wird 0.0 zurueckgegeben
     *
     * @return d1 dividiert durch d2; oder 0.0 bei zumindest einem uebergebenen
     *         Nullwert
     */
    public Double dividiere(final Double double1, final Double double2) {
        double rueckgabe = 0.0;
        if ((double1 == null) || (double1.equals(Double.valueOf(0.0))) || (double2 == null)
                || (double2.equals(Double.valueOf(0.0)))) {
            rueckgabe = 0.0;
        } else {
            rueckgabe = double1.doubleValue() / double2.doubleValue();
        }
        return (Double.valueOf(rueckgabe));
    }

    /**
     * dividiert "dividend" durch "divisor" und gibt das Ergebnis als neuer
     * BigDecimal zurueck, gerundet auf die Anzahl an Nachkommastellen; bei
     * uebergebenen Nullwerten wird BigDecimal.ZERO zurueckgegeben
     *
     * @param nachkommastellen
     *            Anzahl an Nachkommastellen im gerundeten Wert (muss >= 0 sein)
     * @param roundingMode
     *            wie soll gerundet werden? Bei Uebergabe von null wird
     *            RoundingMode.HALF_UP verwendet.
     * @return Quotient von dividend geteilt durch divisor auf die Anzahl an
     *         Nachkommastellen gerundet
     */
    public BigDecimal dividiere(final BigDecimal dividend, final BigDecimal divisor, int nachkommastellen,
            RoundingMode roundingMode) {
        BigDecimal quotient = BigDecimal.ZERO;
        if (roundingMode == null) {
            roundingMode = RoundingMode.HALF_UP;
        }
        if (nachkommastellen < 0) {
            nachkommastellen = 0;
        }
        if ((!istWertNullOder0(dividend)) && (!istWertNullOder0(divisor))) {
            quotient = dividend.divide(divisor, nachkommastellen, roundingMode);
        }
        return (quotient);
    }

    /**
     * dividiert "dividend" durch "divisor" und gibt das Ergebnis als neuer
     * BigDecimal zurueck, gerundet auf die Anzahl an Nachkommastellen; bei
     * uebergebenen Nullwerten wird BigDecimal.ZERO zurueckgegeben
     *
     * @param nachkommastellen
     *            Anzahl an Nachkommastellen im gerundeten Wert (muss >= 0 sein)
     * @return Quotient von dividend geteilt durch divisor auf die Anzahl an
     *         Nachkommastellen gerundet
     */
    public BigDecimal dividiere(final BigDecimal dividend, final BigDecimal divisor, final int nachkommastellen) {
        return (dividiere(dividend, divisor, nachkommastellen, RoundingMode.HALF_UP));
    }

    /**
     * gibt den kleineren uebergebenen Wert als neuer Integer zurueck,
     * uebergebene Nullwerte werden als Integer.valueOf(0) behandelt
     *
     * @return Integer.valueOf(Math.min(integer1, integer2))
     */
    public Integer getMinimum(final Integer integer1, final Integer integer2) {
        Integer integer1a = integer1;
        Integer integer2a = integer2;
        if (integer1a == null) {
            integer1a = Integer.valueOf(0);
        }
        if (integer2a == null) {
            integer2a = Integer.valueOf(0);
        }
        return (Integer.valueOf(Math.min(integer1a.intValue(), integer2a.intValue())));
    }

    /**
     * gibt den kleineren uebergebenen Wert als neuer Long zurueck, uebergebene
     * Nullwerte werden als Long.valueOf(0) behandelt
     *
     * @return Long.valueOf(Math.min(long1, long2))
     */
    public Long getMinimum(Long long1, Long long2) {
        if (long1 == null) {
            long1 = Long.valueOf(0);
        }
        if (long2 == null) {
            long2 = Long.valueOf(0);
        }
        return (Long.valueOf(Math.min(long1.longValue(), long2.longValue())));
    }

    /**
     * gibt den kleineren uebergebenen Wert als neuer Double zurueck,
     * uebergebene Nullwerte werden als Double.valueOf(0.0) behandelt
     *
     * @return Double.valueOf(Math.min(double1, double2))
     */
    public Double getMinimum(Double double1, Double double2) {
        if (double1 == null) {
            double1 = Double.valueOf(0.0);
        }
        if (double2 == null) {
            double2 = Double.valueOf(0.0);
        }
        return (Double.valueOf(Math.min(double1.doubleValue(), double2.doubleValue())));
    }

    /**
     * gibt den kleineren uebergebenen Wert als neuer BigDecimal zurueck,
     * uebergebene Nullwerte werden als BigDecimal.ZERO behandelt
     *
     * @return BigDecimal
     */
    public BigDecimal getMinimum(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        BigDecimal minimum = null;
        if (bigDecimal1 == null) {
            bigDecimal1 = BigDecimal.ZERO;
        }
        if (bigDecimal2 == null) {
            bigDecimal2 = BigDecimal.ZERO;
        }
        if (bigDecimal1.compareTo(bigDecimal2) < 0) {
            minimum = bigDecimal1;
        } else {
            minimum = bigDecimal2;
        }
        return (minimum);
    }

    /**
     * gibt den groesseren uebergebenen Wert als neuer Integer zurueck,
     * uebergebene Nullwerte werden als Integer.valueOf(0) behandelt
     *
     * @return Integer.valueOf(Math.max(integer1, integer2))
     */
    public Integer getMaximum(Integer integer1, Integer integer2) {
        if (integer1 == null) {
            integer1 = Integer.valueOf(0);
        }
        if (integer2 == null) {
            integer2 = Integer.valueOf(0);
        }
        return (Integer.valueOf(Math.max(integer1.intValue(), integer2.intValue())));
    }

    /**
     * gibt den groesseren uebergebenen Wert als neuer Long zurueck, uebergebene
     * Nullwerte werden als Long.valueOf(0) behandelt
     *
     * @return Long.valueOf(Math.max(long1, long2))
     */
    public Long getMaximum(Long long1, Long long2) {
        if (long1 == null) {
            long1 = Long.valueOf(0);
        }
        if (long2 == null) {
            long2 = Long.valueOf(0);
        }
        return (Long.valueOf(Math.max(long1.longValue(), long2.longValue())));
    }

    /**
     * gibt den groesseren uebergebenen Wert als neuer Double zurueck,
     * uebergebene Nullwerte werden als Double.valueOf(0.0) behandelt
     *
     * @return Double.valueOf(Math.max(double1, double2))
     */
    public Double getMaximum(Double double1, Double double2) {
        if (double1 == null) {
            double1 = Double.valueOf(0.0);
        }
        if (double2 == null) {
            double2 = Double.valueOf(0.0);
        }
        return (Double.valueOf(Math.max(double1.doubleValue(), double2.doubleValue())));
    }

    /**
     * gibt den groesseren uebergebenen Wert als neuer BigDecimal zurueck,
     * uebergebene Nullwerte werden als BigDecimal.ZERO behandelt
     *
     * @return BigDecimal
     */
    public BigDecimal getMaximum(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        BigDecimal maximum = null;
        if (bigDecimal1 == null) {
            bigDecimal1 = BigDecimal.ZERO;
        }
        if (bigDecimal2 == null) {
            bigDecimal2 = BigDecimal.ZERO;
        }
        if (bigDecimal1.compareTo(bigDecimal2) > 0) {
            maximum = bigDecimal1;
        } else {
            maximum = bigDecimal2;
        }
        return (maximum);
    }

    /**
     * gibt zurueck, ob der uebergebene Integer null oder 0 ist
     *
     * @return true, falls der uebergebene Wert null oder 0 ist, sonst false
     */
    public boolean istWertNullOder0(final Integer integer1) {
        return ((integer1 == null) || (integer1.intValue() == 0));
    }

    /**
     * gibt zurueck, ob der uebergebene Long null oder 0 ist
     *
     * @return true, falls der uebergebene Wert null oder 0 ist, sonst false
     */
    public boolean istWertNullOder0(final Long long1) {
        return ((long1 == null) || (long1.longValue() == 0));
    }

    /**
     * gibt zurueck, ob der uebergebene Double null oder 0 ist
     *
     * @return true, falls der uebergebene Wert null oder 0 ist, sonst false
     */
    public boolean istWertNullOder0(final Double double1) {
        return ((double1 == null) || (double1.doubleValue() == 0));
    }

    /**
     *
     * @param bigDecimal
     *            bigDecimal
     * @return <b>true</b> wenn BigDecimal null oder BigDecimal.ZERO
     */
    public boolean istWertNullOder0(final BigDecimal bigDecimal) {
        return ((bigDecimal == null) || (bigDecimal.compareTo(BigDecimal.ZERO) == 0));
    }

    /**
     * gibt zurueck, ob der Boolean true ist
     *
     * @return true, falls der Boolean true enthaelt, false bei uebergabe von
     *         null oder false
     */
    public boolean istTrue(final Boolean b) {
        return ((b != null) && (b.booleanValue()));
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Date-Instanzen den gleichen
     * Tag+Monat+Jahr enthalten
     *
     * @return true, falls beide Date-Instanzen den gleichen Wert enthalten
     *         (auch null), sonst false (wenn sie nicht den gleichen Wert
     *         enthalten oder nur einer der beiden null ist)
     */
    public boolean istGleicherTag(final Date date1, final Date date2) {
        return (istGleicherTag(this.typConverter.utilDateZuGregorianCalendar(date1),
                this.typConverter.utilDateZuGregorianCalendar(date2)));
    }

    /**
     * ueberprueft, ob die beiden uebergebenen GregorianCalendar-Instanzen den
     * gleichen Tag+Monat+Jahr enthalten
     *
     * @return true, falls beide GregorianCalendar-Instanzen den gleichen Wert
     *         enthalten (auch null), sonst false (wenn sie nicht den gleichen
     *         Wert enthalten oder nur einer der beiden null ist)
     */
    public boolean istGleicherTag(final GregorianCalendar cal1, final GregorianCalendar cal2) {
        boolean rueckgabe = false;
        if ((cal1 != null) && (cal2 != null)) {
            rueckgabe = ((cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
                    && (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
                    && (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)));
        } else if ((cal1 == null) && (cal2 == null)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Date-Instanzen den gleichen
     * Monat+Jahr enthalten (Tag egal)
     *
     * @return true, falls beide Instanzen den gleichen Wert enthalten (auch
     *         null), sonst false (wenn sie nicht den gleichen Wert enthalten
     *         oder nur einer der beiden null ist)
     */
    public boolean istGleicherMonat(final Date date1, final Date date2) {
        return (istGleicherMonat(this.typConverter.utilDateZuGregorianCalendar(date1),
                this.typConverter.utilDateZuGregorianCalendar(date2)));
    }

    /**
     * ueberprueft, ob die beiden uebergebenen GregorianCalendar-Instanzen den
     * gleichen Monat+Jahr enthalten (Tag egal)
     *
     * @return true, falls beide Instanzen den gleichen Wert enthalten (auch
     *         null), sonst false (wenn sie nicht den gleichen Wert enthalten
     *         oder nur einer der beiden null ist)
     */
    public boolean istGleicherMonat(final GregorianCalendar cal1, final GregorianCalendar cal2) {
        boolean rueckgabe = false;
        if ((cal1 != null) && (cal2 != null)) {
            rueckgabe = ((cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
                    && (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)));
        } else if ((cal1 == null) && (cal2 == null)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Date-Instanzen das gleiche Jahr
     * enthalten (Tag und Monat egal)
     *
     * @return true, falls beide Instanzen den gleichen Wert enthalten (auch
     *         null), sonst false (wenn sie nicht den gleichen Wert enthalten
     *         oder nur einer der beiden null ist)
     */
    public boolean istGleichesJahr(final Date date1, final Date date2) {
        return (istGleichesJahr(this.typConverter.utilDateZuGregorianCalendar(date1),
                this.typConverter.utilDateZuGregorianCalendar(date2)));
    }

    /**
     * ueberprueft, ob die beiden uebergebenen GregorianCalendar-Instanzen das
     * gleiche Jahr enthalten (Tag und Monat egal)
     *
     * @return true, falls beide Instanzen den gleichen Wert enthalten (auch
     *         null), sonst false (wenn sie nicht den gleichen Wert enthalten
     *         oder nur einer der beiden null ist)
     */
    public boolean istGleichesJahr(final GregorianCalendar cal1, final GregorianCalendar cal2) {
        boolean rueckgabe = false;
        if ((cal1 != null) && (cal2 != null)) {
            rueckgabe = (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR));
        } else if ((cal1 == null) && (cal2 == null)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Booleans den gleichen Wert
     * enthalten
     *
     * @return true, falls beide Booleans den gleichen Wert enthalten (auch null),
     *         sonst false (wenn sie nicht den gleichen Wert enthalten oder nur
     *         einer der beiden null ist)
     */
    public boolean istGleich(final Boolean boolean1, final Boolean boolean2) {
        boolean rueckgabe = false;
        if ((boolean1 != null) && (boolean2 != null)) {
            rueckgabe = (boolean1.booleanValue() == boolean2.booleanValue());
        } else if ((boolean1 == null) && (boolean2 == null)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob integer1 kleiner als integer2 ist
     *
     * @return true, falls integer1 < integer2 oder falls integer1 null ist und
     *         integer2 nicht, sonst false
     */
    public boolean istKleiner(final Integer integer1, final Integer integer2) {
        boolean rueckgabe = false;
        if ((integer1 != null) && (integer2 != null)) {
            rueckgabe = (integer1.intValue() < integer2.intValue());
        } else if ((integer1 == null) && (integer2 != null)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Integer den gleichen Wert
     * enthalten
     *
     * @return true, falls beide Integer den gleichen Wert enthalten (auch
     *         null), sonst false (wenn sie nicht den gleichen Wert enthalten
     *         oder nur einer der beiden null ist)
     */
    public boolean istGleich(final Integer integer1, final Integer integer2) {
        boolean rueckgabe = false;
        if ((integer1 != null) && (integer2 != null)) {
            rueckgabe = (integer1.intValue() == integer2.intValue());
        } else if ((integer1 == null) && (integer2 == null)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob integer1 groesser als integer2 ist
     *
     * @return true, falls integer1 > integer2 oder falls integer2 null ist und
     *         integer1 nicht, sonst false
     */
    public boolean istGroesser(final Integer integer1, final Integer integer2) {
        boolean rueckgabe = false;
        if ((integer1 != null) && (integer2 != null)) {
            rueckgabe = (integer1.intValue() > integer2.intValue());
        } else if ((integer1 != null) && (integer2 == null)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob long1 kleiner als long2 ist
     *
     * @return true, falls long1 < long2 oder falls long1 null ist und long2
     *         nicht, sonst false
     */
    public boolean istKleiner(final Long long1, final Long long2) {
        boolean rueckgabe = false;
        if ((long1 != null) && (long2 != null)) {
            rueckgabe = (long1.longValue() < long2.longValue());
        } else if ((long1 == null) && (long2 != null)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Longs den gleichen Wert enthalten
     *
     * @return true, falls beide Longs den gleichen Wert enthalten (auch null),
     *         sonst false (wenn sie nicht den gleichen Wert enthalten oder nur
     *         einer der beiden null ist)
     */
    public boolean istGleich(final Long long1, final Long long2) {
        boolean rueckgabe = false;
        if ((long1 != null) && (long2 != null)) {
            rueckgabe = (long1.longValue() == long2.longValue());
        } else if ((long1 == null) && (long2 == null)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob long1 groesser als long2 ist
     *
     * @return true, falls long1 > long2 oder falls long2 null ist und long1
     *         nicht, sonst false
     */
    public boolean istGroesser(final Long long1, final Long long2) {
        boolean rueckgabe = false;
        if ((long1 != null) && (long2 != null)) {
            rueckgabe = (long1.longValue() > long2.longValue());
        } else if ((long1 != null) && (long2 == null)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Doubles den gleichen Wert
     * enthalten
     *
     * @return true, falls beide Doubles den gleichen Wert enthalten (auch
     *         null), sonst false (wenn sie nicht den gleichen Wert enthalten
     *         oder nur einer der beiden null ist)
     */
    public boolean istGleich(final Double double1, final Double double2) {
        boolean rueckgabe = false;
        if ((double1 != null) && (double2 != null)) {
            rueckgabe = (double1.doubleValue() == double2.doubleValue());
        } else if ((double1 == null) && (double2 == null)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Strings den gleichen Wert
     * enthalten
     *
     * @return true, falls beide Strings den gleichen Wert enthalten (auch
     *         null), sonst false (wenn sie nicht den gleichen Wert enthalten
     *         oder nur einer der beiden null ist)
     */
    public boolean istGleich(final String string1, final String string2) {
        boolean rueckgabe = false;
        if ((!this.helperAllgemein.isEmptyString(string1)) && (!this.helperAllgemein.isEmptyString(string2))) {
            rueckgabe = (string1.trim().equalsIgnoreCase(string2.trim()));
        } else if ((this.helperAllgemein.isEmptyString(string1)) && (this.helperAllgemein.isEmptyString(string2))) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Integer den gleichen Wert
     * enthalten
     *
     * @return true, falls beide Integer den gleichen Wert enthalten, sonst
     *         false
     */
    public boolean istGleich(final Integer integer1, final int integer2) {
        return (istGleich(integer1, Integer.valueOf(integer2)));
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Integer den gleichen Wert
     * enthalten
     *
     * @return true, falls beide Integer den gleichen Wert enthalten, sonst
     *         false
     */
    public boolean istGleich(final int integer1, final Integer integer2) {
        return (istGleich(Integer.valueOf(integer1), integer2));
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Longs den gleichen Wert enthalten
     *
     * @return true, falls beide Longs den gleichen Wert enthalten, sonst false
     */
    public boolean istGleich(final Long long1, final long long2) {
        return (istGleich(long1, Long.valueOf(long2)));
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Longs den gleichen Wert enthalten
     *
     * @return true, falls beide Longs den gleichen Wert enthalten, sonst false
     */
    public boolean istGleich(final long long1, final Long long2) {
        return (istGleich(Long.valueOf(long1), long2));
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Doubles den gleichen Wert
     * enthalten
     *
     * @return true, falls beide Doubles den gleichen Wert enthalten, sonst
     *         false
     */
    public boolean istGleich(final Double double1, final double double2) {
        return (istGleich(double1, Double.valueOf(double2)));
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Doubles den gleichen Wert
     * enthalten
     *
     * @return true, falls beide Doubles den gleichen Wert enthalten, sonst
     *         false
     */
    public boolean istGleich(final double double1, final Double double2) {
        return (istGleich(Double.valueOf(double1), double2));
    }

    /**
     * liefert true zurueck, wenn datumVon <= datumBis
     */
    public boolean isDatumVonBeforeOrEqualsDatumBis(final java.util.Date datumVon, final java.util.Date datumBis) {
        return ((datumVon != null) && ((datumBis == null) || (datumBis.compareTo(datumVon) >= 0)));
    }

    /**
     * erhoeht / verringert ein Datum um plusMinusX Jahre
     *
     * @param datum
     *            Datum, bei dem Jahre hinzugerechnet bzw. abgezogen werden
     *            sollen
     * @param plusMinusX
     *            wie viele Jahre sollen hinzugerechnet (bei plusMinusX > 0)
     *            bzw. abgezogen (bei plusMinusX < 0) werden
     * @return <code>java.sql.Date</code>, bei dem plusMinusX Jahre
     *         hinzugerechnet oder abgezogen wurden
     */
    public java.sql.Date datumPlusMinusXJahre(java.sql.Date datum, final int plusMinusX) {
        if ((datum != null) && (plusMinusX != 0)) {
            final GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(datum);
            gc.add(Calendar.YEAR, plusMinusX);
            datum = new java.sql.Date(gc.getTimeInMillis());
        }
        return (datum);
    }

    /**
     * erhoeht / verringert ein Datum um plusMinusX Monate
     *
     * @param datum
     *            Datum, bei dem Monate hinzugerechnet bzw. abgezogen werden
     *            sollen
     * @param plusMinusX
     *            wie viele Monate sollen hinzugerechnet (bei plusMinusX > 0)
     *            bzw. abgezogen (bei plusMinusX < 0) werden
     * @return <code>java.sql.Date</code>, bei dem plusMinusX Monate
     *         hinzugerechnet oder abgezogen wurden
     */
    public java.sql.Date datumPlusMinusXMonate(java.sql.Date datum, final int plusMinusX) {
        if ((datum != null) && (plusMinusX != 0)) {
            final GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(datum);
            gc.add(Calendar.MONTH, plusMinusX);
            datum = new java.sql.Date(gc.getTimeInMillis());
        }
        return (datum);
    }

    /**
     * erhoeht / verringert ein Datum um plusMinusX Tage
     *
     * @param datum
     *            Datum, bei dem Tage hinzugerechnet bzw. abgezogen werden
     *            sollen
     * @param plusMinusX
     *            wie viele Tage sollen hinzugerechnet (bei plusMinusX > 0) bzw.
     *            abgezogen (bei plusMinusX < 0) werden
     * @return <code>java.sql.Date</code>, bei dem plusMinusX Tage
     *         hinzugerechnet oder abgezogen wurden
     */
    public java.sql.Date datumPlusMinusXTage(java.sql.Date datum, final int plusMinusX) {
        if ((datum != null) && (plusMinusX != 0)) {
            final GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(datum);
            gc.add(Calendar.DAY_OF_MONTH, plusMinusX);
            datum = new java.sql.Date(gc.getTimeInMillis());
        }
        return (datum);
    }

    /**
     * ermittelt zum uebergebenen Datumsobjekt das Quartal
     *
     * @param date
     *            Datum, dessen Quartal gesucht wird
     *
     * @return 0: Fehlerfall; 1: Jaenner, Februar, Maerz; 2: April, Mai, Juni;
     *         3: Juli, August, September; 4: Oktober, November, Dezember
     */
    public int getQuartal(final java.sql.Date date) {
        return (getQuartal(this.typConverter.sqlDateZuGregorianCalendar(date)));
    }

    /**
     * ermittelt zum uebergebenen Datumsobjekt das Quartal
     *
     * @param gc
     *            Datum, dessen Quartal gesucht wird
     *
     * @return 0: Fehlerfall; 1: Jaenner, Februar, Maerz; 2: April, Mai, Juni;
     *         3: Juli, August, September; 4: Oktober, November, Dezember
     */
    public int getQuartal(final GregorianCalendar gc) {
        int quartal = 0;
        if (gc != null) {
            final int monat = gc.get(Calendar.MONTH);
            switch (monat) {
                case (Calendar.JANUARY):
                case (Calendar.FEBRUARY):
                case (Calendar.MARCH): {
                    quartal = 1;
                    break;
                }
                case (Calendar.APRIL):
                case (Calendar.MAY):
                case (Calendar.JUNE): {
                    quartal = 2;
                    break;
                }
                case (Calendar.JULY):
                case (Calendar.AUGUST):
                case (Calendar.SEPTEMBER): {
                    quartal = 3;
                    break;
                }
                case (Calendar.OCTOBER):
                case (Calendar.NOVEMBER):
                case (Calendar.DECEMBER): {
                    quartal = 4;
                    break;
                }
                default: {
                    quartal = 0;
                    break;
                }
            }
        }
        return (quartal);
    }

    /**
     ** ermittelt zum uebergebenen Datumsobjekt das Jahr
     *
     * @param date
     *            Datum, dessen Jahr gesucht wird
     *
     * @return 0: Fehlerfall; sonst Jahr in der form YYYY
     */
    public int getJahr(final java.sql.Date date) {
        int jahr = 0;
        final GregorianCalendar gc = this.typConverter.sqlDateZuGregorianCalendar(date);
        if (gc != null) {
            jahr = gc.get(Calendar.YEAR);
        }
        return (jahr);
    }

    /**
     ** ermittelt zum uebergebenen Datumsobjekt den Monat
     *
     * @param date
     *            Datum, dessen Monat gesucht wird
     *
     * @return 0: Fehlerfall; sonst Monat in der form MM
     */
    public int getMonat(final java.sql.Date date) {
        int monat = 0;
        final GregorianCalendar gc = this.typConverter.sqlDateZuGregorianCalendar(date);
        if (gc != null) {
            monat = gc.get(Calendar.MONTH) + 1;
        }
        return (monat);
    }

    /**
     ** ermittelt zum uebergebenen Datumsobjekt den Tag
     *
     * @param date
     *            Datum, dessen Tag gesucht wird
     *
     * @return 0: Fehlerfall; sonst Tag in der form TT
     */
    public int getTag(final java.sql.Date date) {
        int tag = 0;
        final GregorianCalendar gc = this.typConverter.sqlDateZuGregorianCalendar(date);
        if (gc != null) {
            tag = gc.get(Calendar.DAY_OF_MONTH);
        }
        return (tag);
    }

    /**
     * Ermittelt die Differenz in Tagen
     *
     * @param datum
     *            das groessere der beiden Daten
     * @param minusDatum
     *            das kleinere der beiden Daten
     * @return int differenz
     */
    public int getDifferenzInTagen(final java.util.Date datum, final java.util.Date minusDatum) {
        int differenz = 0;
        if ((datum != null) && (minusDatum != null)) {
            final GregorianCalendar gc1 = new GregorianCalendar();
            gc1.setTime(datum);
            gc1.set(Calendar.HOUR_OF_DAY, 12);
            gc1.set(Calendar.MINUTE, 0);
            gc1.set(Calendar.SECOND, 0);
            gc1.set(Calendar.MILLISECOND, 0);
            final GregorianCalendar gc2 = new GregorianCalendar();
            gc2.setTime(minusDatum);
            gc2.set(Calendar.HOUR_OF_DAY, 12);
            gc2.set(Calendar.MINUTE, 0);
            gc2.set(Calendar.SECOND, 0);
            gc2.set(Calendar.MILLISECOND, 0);
            while (gc2.before(gc1)) {
                gc2.add(Calendar.DAY_OF_MONTH, 1);
                differenz++;
            }
        }
        return (differenz);
    }

    /**
     * liefert true zurueck, wenn pruef_date im Zeitraum datum_von - datum_bis
     * liegt
     */
    public boolean istAktiverZeitraum(final java.util.Date von, final java.util.Date bis,
            final java.util.Date pruef_date) {
        final Calendar cal = Calendar.getInstance();
        if (pruef_date != null) {
            cal.setTimeInMillis(pruef_date.getTime());
        }
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return ((von != null) && (!von.after(cal.getTime())) && ((bis == null) || (!bis.before(cal.getTime()))));
    }

    /**
     * liefert true zurueck, wenn pruef_date im Zeitraum datum_von - datum_bis
     * liegt
     */
    public boolean istAktiverZeitraum(final XMLGregorianCalendar von, final XMLGregorianCalendar bis,
            final java.util.Date pruef_date) {
        return istAktiverZeitraum(this.typConverter.xmlGregorianCalendarZuUtilDate(von),
                this.typConverter.xmlGregorianCalendarZuUtilDate(bis), pruef_date);
    }

    /**
     * liefert true zurueck, wenn pruef_date im Zeitraum datum_von - datum_bis
     * liegt
     */
    public boolean istAktiverZeitraum(final Timestamp von, final Timestamp bis, final Timestamp pruef_date) {
        final Calendar cal = Calendar.getInstance();
        if (pruef_date != null) {
            cal.setTimeInMillis(pruef_date.getTime());
        }
        return ((von != null) && (!von.after(cal.getTime())) && ((bis == null) || (!bis.before(cal.getTime()))));
    }

    /**
     *
     * gibt ein Datum mit dem ersten Tag des uebergebenen Monats zurueck
     *
     * @param jahr
     *            , monat Monat, zu dem der Monatserste gesucht wird
     *
     * @return <code>java.sql.Date</code>, welches den Monatsersten zum
     *         uebergebenen Monat enthaelt
     */
    public java.sql.Date getMonatsAnfang(final int jahr, final int monat) {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, jahr);
        cal.set(Calendar.MONTH, (monat - 1));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return (this.typConverter.getNullSqlDateOhneUhrzeit(new java.sql.Date(cal.getTimeInMillis())));
    }

    /**
     *
     * gibt ein Datum mit dem ersten Tag des uebergebenen Monats zurueck
     *
     * @param datum
     *            Monat, zu dem der Monatserste gesucht wird
     *
     * @return <code>java.sql.Date</code>, welches den Monatsersten zum
     *         uebergebenen Monat enthaelt
     */
    public java.sql.Date getMonatsAnfang(final java.sql.Date datum) {
        java.sql.Date sqlDate = null;
        final GregorianCalendar cal = this.typConverter.sqlDateZuGregorianCalendar(datum);
        if (cal != null) {
            sqlDate = getMonatsAnfang(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
        }
        return (sqlDate);
    }

    /**
     *
     * gibt ein Datum mit dem ersten Tag des uebergebenen Monats zurueck
     *
     * @param datum
     *            Monat, zu dem der Monatserste gesucht wird
     *
     * @return <code>java.util.Date</code>, welches den Monatsersten zum
     *         uebergebenen Monat enthaelt
     */
    public java.util.Date getMonatsAnfang(final java.util.Date datum) {
        return (getMonatsAnfang(this.typConverter.utilDateZuSqlDate(datum)));
    }

    /**
     *
     * gibt ein Datum mit dem letzten Tag des uebergebenen Monats zurueck
     *
     * @param jahr
     *            , monat Monat, zu dem das Monatsende gesucht wird
     *
     * @return <code>java.sql.Date</code>, welches das Monatsende zum
     *         uebergebenen Monat enthaelt
     */
    public java.sql.Date getMonatsEnde(final int jahr, final int monat) {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, jahr);
        cal.set(Calendar.MONTH, (monat - 1));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return (this.typConverter.getNullSqlDateOhneUhrzeit(new java.sql.Date(cal.getTimeInMillis())));
    }

    /**
     *
     * gibt ein Datum mit dem letzten Tag des uebergebenen Monats zurueck
     *
     * @param datum
     *            Monat, zu dem das Monatsende gesucht wird
     *
     * @return <code>java.sql.Date</code>, welches das Monatsende zum
     *         uebergebenen Monat enthaelt
     */
    public java.sql.Date getMonatsEnde(final java.sql.Date datum) {
        java.sql.Date sqlDate = null;
        final GregorianCalendar cal = this.typConverter.sqlDateZuGregorianCalendar(datum);
        if (cal != null) {
            sqlDate = getMonatsEnde(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
        }
        return (sqlDate);
    }

    /**
     *
     * gibt ein Datum mit dem letzten Tag des uebergebenen Monats zurueck
     *
     * @param datum
     *            Monat, zu dem das Monatsende gesucht wird
     *
     * @return <code>java.sql.Date</code>, welches das Monatsende zum
     *         uebergebenen Monat enthaelt
     */
    public java.util.Date getMonatsEnde(final java.util.Date datum) {
        return (getMonatsEnde(this.typConverter.utilDateZuSqlDate(datum)));
    }

    /**
     * gibt ein <code>java.sql.Date</code>, gesetzt auf den 01.01. des
     * gewuenschten Jahres zurueck
     *
     * @param jahr
     *            Jahr, dessen 01.01. gesucht wird
     *
     * @return Datumsobjekt, gesetzt auf 01.01. fuer das angegebene Jahr
     */
    public java.sql.Date getJahresAnfang(final int jahr) {
        final GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, jahr);
        cal.set(Calendar.MONTH, cal.getActualMinimum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return (this.typConverter.getNullSqlDateOhneUhrzeit(new java.sql.Date(cal.getTimeInMillis())));
    }

    /**
     * gibt ein <code>java.sql.Date</code>, gesetzt auf den 01.01. des
     * gewuenschten Jahres zurueck
     *
     * @param jahr
     *            Jahr, dessen 01.01. gesucht wird
     *
     * @return Datumsobjekt, gesetzt auf 01.01. fuer das angegebene Jahr
     */
    public java.sql.Date getJahresAnfang(final Integer jahr) {
        java.sql.Date date = null;
        if (jahr != null) {
            date = getJahresAnfang(jahr.intValue());
        }
        return (date);
    }

    /**
     * gibt den Jahresanfang zum uebergebenen Datum zurueck oder null, falls null uebergeben wurde
     */
    public java.sql.Date getJahresAnfang(final java.sql.Date datum) {
        java.sql.Date datumAntwort = null;
        final GregorianCalendar cal = this.typConverter.sqlDateZuGregorianCalendar(datum);
        if (cal != null) {
            datumAntwort = getJahresAnfang(cal.get(Calendar.YEAR));
        }
        return (datumAntwort);
    }

    /**
     * gibt ein <code>java.sql.Date</code>, gesetzt auf den 31.12. des
     * gewuenschten Jahres zurueck
     *
     * @param jahr
     *            Jahr, dessen 31.12. gesucht wird
     *
     * @return Datumsobjekt, gesetzt auf 31.12. fuer das angegebene Jahr
     */
    public java.sql.Date getJahresEnde(final int jahr) {
        final GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, jahr);
        cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return (this.typConverter.getNullSqlDateOhneUhrzeit(new java.sql.Date(cal.getTimeInMillis())));
    }

    /**
     * gibt ein <code>java.sql.Date</code>, gesetzt auf den 31.12. des
     * gewuenschten Jahres zurueck
     *
     * @param jahr
     *            Jahr, dessen 31.12. gesucht wird
     *
     * @return Datumsobjekt, gesetzt auf 31.12. fuer das angegebene Jahr
     */
    public java.sql.Date getJahresEnde(final Integer jahr) {
        java.sql.Date date = null;
        if (jahr != null) {
            date = getJahresEnde(jahr.intValue());
        }
        return (date);
    }

    /**
     * gibt das Jahresende zum uebergebenen Datum zurueck oder null, falls null uebergeben wurde
     */
    public java.sql.Date getJahresEnde(final java.sql.Date datum) {
        java.sql.Date datumAntwort = null;
        final GregorianCalendar cal = this.typConverter.sqlDateZuGregorianCalendar(datum);
        if (cal != null) {
            datumAntwort = getJahresEnde(cal.get(Calendar.YEAR));
        }
        return (datumAntwort);
    }

    /**
     * ueberprueft, ob die beiden uebergebenen BigDecimals den gleichen Wert
     * enthalten
     *
     * @return true, falls beide BigDecimal den gleichen Wert enthalten (auch
     *         null), sonst false (wenn sie nicht den gleichen Wert enthalten
     *         oder nur einer der beiden null ist)
     */
    public boolean istGleich(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2) {
        boolean rueckgabe = false;
        if ((bigDecimal1 != null) && (bigDecimal2 != null)) {
            rueckgabe = (bigDecimal1.compareTo(bigDecimal2) == 0);
        } else if ((bigDecimal1 == null) && (bigDecimal2 == null)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Parameter den gleichen Wert
     * enthalten
     *
     * @return true, falls beide Parameter den gleichen Wert enthalten, sonst
     *         false
     */
    public boolean istGleich(final BigDecimal bigDecimal1, final double double2) {
        boolean rueckgabe = false;
        if (bigDecimal1 != null) {
            rueckgabe = (bigDecimal1.compareTo(new BigDecimal(double2, MathContext.DECIMAL64)) == 0);
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob die beiden uebergebenen BigIntegers den gleichen Wert
     * enthalten
     *
     * @return true, falls beide BigInteger den gleichen Wert enthalten (auch
     *         null), sonst false (wenn sie nicht den gleichen Wert enthalten
     *         oder nur einer der beiden null ist)
     */
    public boolean istGleich(final BigInteger bigInteger1, final BigInteger bigInteger2) {
        boolean rueckgabe = false;
        if ((bigInteger1 != null) && (bigInteger2 != null)) {
            rueckgabe = (bigInteger1.compareTo(bigInteger2) == 0);
        } else if ((bigInteger1 == null) && (bigInteger2 == null)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return (rueckgabe);
    }

    /**
     * ueberprueft, ob die beiden uebergebenen Parameter den gleichen Wert
     * enthalten
     *
     * @return true, falls beide Parameter den gleichen Wert enthalten, sonst
     *         false
     */
    public boolean istGleich(final BigInteger bigInteger1, final long integer2) {
        boolean rueckgabe = false;
        if (bigInteger1 != null) {
            rueckgabe = (bigInteger1.compareTo(BigInteger.valueOf(integer2)) == 0);
        }
        return (rueckgabe);
    }

    /**
     * liefert true zurueck, wenn ein BigDecimal < 0 ist
     *
     * @return boolean
     */
    public boolean isBetragNegativ(final BigDecimal bigDecimal) {
        return ((bigDecimal != null) && (bigDecimal.compareTo(BigDecimal.ZERO) < 0));
    }

    /**
     * liefert true zurueck, wenn ein BigDecimal > 0 ist
     *
     * @return boolean
     */
    public boolean isBetragPositiv(final BigDecimal bigDecimal) {
        return ((bigDecimal != null) && (bigDecimal.compareTo(BigDecimal.ZERO) > 0));
    }

    /**
     * rundet den uebergebenen BigDecimal auf die Anzahl der uebergebenen
     * Nachkommastellen, falls null uebergeben wurde, wird mit BigDecimal.ZERO
     * gerechnet
     *
     * @param bigDecimal
     *            zu rundender BigDecimal
     * @param nachkommastellen
     *            Anzahl an Nachkommastellen im gerundeten Wert (muss >= 0 sein)
     * @param roundingMode
     *            wie soll gerundet werden? Bei Uebergabe von null wird
     *            RoundingMode.HALF_UP verwendet.
     *
     * @return auf die Anzahl an Nachkommastellen gerundeter BigDecimal
     */
    public BigDecimal runde(BigDecimal bigDecimal, int nachkommastellen, RoundingMode roundingMode) {
        if (bigDecimal == null) {
            bigDecimal = BigDecimal.ZERO;
        }
        if (nachkommastellen < 0) {
            nachkommastellen = 0;
        }
        if (roundingMode == null) {
            roundingMode = RoundingMode.HALF_UP;
        }
        return (bigDecimal.setScale(nachkommastellen, roundingMode));
    }

    /**
     * rundet den uebergebenen BigDecimal auf die Anzahl der uebergebenen
     * Nachkommastellen, falls null uebergeben wurde, wird mit BigDecimal.ZERO
     * gerechnet
     *
     * @param bigDecimal
     *            zu rundender BigDecimal
     * @param nachkommastellen
     *            Anzahl an Nachkommastellen im gerundeten Wert (muss >= 0 sein)
     *
     * @return auf die Anzahl an Nachkommastellen gerundeter BigDecimal
     */
    public BigDecimal runde(final BigDecimal bigDecimal, final int nachkommastellen) {
        return (runde(bigDecimal, nachkommastellen, RoundingMode.HALF_UP));
    }

    /**
     * rundet jeden uebergebenen BigDecimal auf die Anzahl der uebergebenen
     * Nachkommastellen und addiert beide Zahlen, falls null uebergeben wurde,
     * wird mit BigDecimal.ZERO gerechnet.
     *
     * @return BigDecimal
     */
    public BigDecimal rundeUndAddiere(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2,
            final int nachkommastellen) {
        return (addiere(this.runde(bigDecimal1, nachkommastellen), this.runde(bigDecimal2, nachkommastellen)));
    }

    /**
     * Vergleicht die zwei Daten auf gleichen Tag
     *
     * @return Wahr falls es der gleiche Tag ist
     */
    public boolean isGleicherTag(final Date date1, final Date date2) {
        boolean gleich = false;
        if ((date1 != null) && (date2 != null)) {
            final Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            final Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            gleich = isGleicherTag(cal1, cal2);
        }
        return (gleich);
    }

    /**
     * Vergleich die zwei Kalender-Objekte auf gleichen Tag
     *
     * @return Wahr falls erstes Datum kleiner als das zweite Datum
     */
    public boolean isGleicherTag(final Calendar cal1, final Calendar cal2) {
        boolean gleich = false;
        if ((cal1 != null) && (cal2 != null)) {
            gleich = ((cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA))
                    && (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
                    && (cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)));
        }
        return (gleich);
    }

    /**
     * Ist das erste Datum kleiner als das zweite Datum. Es wird nicht die Zeit
     * verglichen
     *
     * @return Wahr falls erstes Datum kleiner als das zweite Datum
     */
    public boolean istTagKleiner(final Date date1, final Date date2) {
        boolean gleich = false;
        if ((date1 != null) && (date2 != null)) {
            final Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            final Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            gleich = istTagKleiner(cal1, cal2);
        }
        return (gleich);
    }

    /**
     * Ist das erste Datum kleiner als das zweite Datum. Es wird nicht die Zeit
     * verglichen
     *
     * @return Wahr falls es der gleiche Tag ist
     */
    public boolean istTagKleiner(final Calendar cal1, final Calendar cal2) {
        boolean gleich = false;
        if ((cal1 != null) && (cal2 != null)) {
            final int jahrDifferenz = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
            gleich = ((cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)) && ((jahrDifferenz < 0)
                    || ((jahrDifferenz == 0) && (cal1.get(Calendar.DAY_OF_YEAR) < cal2.get(Calendar.DAY_OF_YEAR)))));
        }
        return (gleich);
    }

    /**
     * Ist das erste Datum groesser als das zweite Datum. Es wird nicht die Zeit
     * verglichen
     *
     * @return Wahr falls das erste Datum groesser ist
     */
    public boolean istTagGroesser(final Date date1, final Date date2) {
        boolean gleich = false;
        if ((date1 != null) && (date2 != null)) {
            final Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            final Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            gleich = istTagGroesser(cal1, cal2);
        }
        return (gleich);
    }

    /**
     * Ist das erste Datum groesser als das zweite Datum. Es wird nicht die Zeit
     * verglichen
     *
     * @return Wahr falls das erste Datum groesser ist
     */
    public boolean istTagGroesser(final Calendar cal1, final Calendar cal2) {
        boolean gleich = false;
        if ((cal1 != null) && (cal2 != null)) {
            final int jahrDifferenz = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
            gleich = ((cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)) && ((jahrDifferenz > 0)
                    || ((jahrDifferenz == 0) && (cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR)))));
        }
        return (gleich);
    }

    /**
     * Liefert zu den uebergebenen Daten das Juengste. Ein Datum das NULL ist
     * wird nicht beruecksichtigt.
     *
     * @return Juengste Datum in der Liste, falls liste.length > 0
     */
    @Nullable
    public <T extends Date> T getJuengstesDatum(
            // MD: Warning laesst sich nicht vermeiden
            // Alternative waere @SafeVarargs + final-Methode, dann funktionieren aber die eSV-Anwendungen nicht mehr
            @SuppressWarnings("unchecked") final T... daten) {
        @Nullable
        T rueckgabe = null;
        final List<T> list = this.helperAllgemein.getListWithNotNullValues(daten);
        if (!this.helperAllgemein.isEmptyList(list)) {
            rueckgabe = Collections.min(list);
        }
        return (rueckgabe);
    }

    /**
     * Liefert zu den uebergebenen Daten das Aelteste. Ein Datum das NULL ist
     * wird nicht beruecksichtigt.
     *
     * @param daten
     *            daten
     * @return Aelteste Datum in der Liste, falls liste.length > 0
     */
    @Nullable
    public <T extends Date> T getAeltestesDatum(
            // MD: Warning laesst sich nicht vermeiden
            // Alternative waere @SafeVarargs + final-Methode, dann funktionieren aber die eSV-Anwendungen nicht mehr
            @SuppressWarnings("unchecked") final T... daten) {
        @Nullable
        T rueckgabe = null;
        final List<T> list = this.helperAllgemein.getListWithNotNullValues(daten);
        if (!this.helperAllgemein.isEmptyList(list)) {
            rueckgabe = Collections.max(list);
        }
        return (rueckgabe);
    }

    /**
     * Liefert zurueck den uebergebene BigDecimal als String, ohne Zero am Ende
     *
     * @param bd
     *            ein BigDecimal
     * @return ein String
     */
    public String stripZerosAlsString(final BigDecimal bd) {
        String rueckgabe = null;
        if (bd != null) {
            rueckgabe = stripZeros(bd).toPlainString();
        }
        return (rueckgabe);
    }

    /**
     * Liefert zurueck den uebergebene BigDecimal als BigDecimal, ohne Zero am
     * Ende
     *
     * @param bd
     *            ein BigDecimal
     * @return ein BigDecimal
     */
    public BigDecimal stripZeros(final BigDecimal bd) {
        BigDecimal rueckgabe = null;
        if (bd != null) {
            if (BigDecimal.ZERO.compareTo(bd) == 0) {
                rueckgabe = BigDecimal.ZERO;
            } else {
                rueckgabe = bd.stripTrailingZeros();
            }
        }
        return (rueckgabe);
    }

    /**
     * Liefert zurueck true wenn kein Zahl nach den Komma gibt
     *
     * @param bd
     *            ein BigDecimal
     * @return boolean
     */
    public boolean istMitNachkommastelle(final BigDecimal bd) {
        boolean rueckgabe = false;
        if ((bd != null) && (BigDecimal.ZERO.compareTo(bd) != 0)) {
            rueckgabe = bd.stripTrailingZeros().toPlainString().contains(".");
        }
        return (rueckgabe);
    }

    /**
     * Pruefe ob das uebergeben Datum dem Quartalsende entspricht.
     */
    public boolean isEndOfQuarter(final java.sql.Date date) {
        boolean endOfMonth = false;
        if (date != null) {
            final Calendar cal = new GregorianCalendar();
            cal.setTime(date);
            final int month = cal.get(Calendar.MONTH);
            if ((month == 2) || (month == 11)) {
                endOfMonth = cal.get(Calendar.DAY_OF_MONTH) == 31;
            } else if ((month == 5) || (month == 8)) {
                endOfMonth = cal.get(Calendar.DAY_OF_MONTH) == 30;
            }
        }
        return endOfMonth;
    }

    /**
     * ueberprueft, ob die uebergebene String in die uebergebene Liste(mussvorhanden sein) enthalten ist
     */
    public boolean istStringInList(final String string, final List<String> stringListe) {
        boolean antwort = false;
        if ((string != null) && (stringListe != null)) {
            antwort = stringListe.contains(string);
        }
        return (antwort);
    }
}
