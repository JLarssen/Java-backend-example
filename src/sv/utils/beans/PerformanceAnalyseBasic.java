package sv.utils.beans;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicLongArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sv.utils.beans.KonfigurationAllgemein;
import constants.Constants_Allgemein;
import constants.Constants_Datumsformat;
import sv.utils.logic.SvSoapFaultInterceptorIn;
import sv.utils.logic.SvSoapFaultInterceptorOut;
import sv.utils.logic.UnexpectedElementEventHandler;
import sv.utils.beans.xml.XmlUtilsAllgemein;
import sv.utils.schnittstellen.TarifkatalogAbfrage;

import sv.utils.ExceptionHandlerAllgemein;
import sv.utils.HelperAllgemein;
import sv.utils.MailService;
import sv.utils.TypConverter;

/**
 * Basisklasse zum Erstellen einer Performanceanalyse
 *
 */
public class PerformanceAnalyseBasic {
    protected Logger log = LoggerFactory.getLogger(getClass());

    protected static Timestamp letzteAnalyse = null;

    protected TypConverter typConverter;

    private final ExceptionHandlerAllgemein exceptionHandlerAllgemein;

    /**
     * Default-Konstruktor
     */
    public PerformanceAnalyseBasic() {
        initLetzteAnalyseZeitpunkt();
        this.typConverterAllgemein = new TypConverterAllgemein();
        this.exceptionHandlerAllgemein = new ExceptionHandlerAllgemein();
    }

    protected MailService getMailService() {
        return new MailService();
    }

    /**
     * initialisiert den von-Zeitstempel in der Performance-Analyse
     */
    public static synchronized void initLetzteAnalyseZeitpunkt() {
        if (letzteAnalyse == null) {
            letzteAnalyse = new Timestamp(System.currentTimeMillis());
        }
    }

    /**
     * Hilfsmethode fuer die Performance-Analyse
     *
     * @param anzahl_aufrufe
     *            jeweilige counter-Variable
     * @param gesamte_rechenzeit
     *            zur counter-Variable gehoerende laufzeit-Variable in
     *            Millisekunden
     * @return durchschnittliche Rechenzeit in Millisekunden
     */
    public double berechneDurchschnittlicheRechenzeit(final double anzahl_aufrufe, final double gesamte_rechenzeit) {
        double durchschnitt = 0.0;
        if (anzahl_aufrufe > 0) {
            durchschnitt = gesamte_rechenzeit / anzahl_aufrufe;
        }
        return (durchschnitt);
    }

    /**
     * setzt die Werte im uebergebenen Array (Zaehler oder Laufzeit) auf 0
     * zurueck
     */
    public long[] resetWerte(final long[] werte) {
        if (werte != null) {
            final int length = werte.length;
            for (int i = 0; i < length; i++) {
                werte[i] = 0;
            }
        }
        return (werte);
    }

    /**
     * setzt die Werte im uebergebenen Array (Zaehler oder Laufzeit) auf 0
     * zurueck
     */
    public AtomicLongArray resetWerte(final AtomicLongArray werte) {
        if (werte != null) {
            final int length = werte.length();
            for (int i = 0; i < length; i++) {
                werte.getAndSet(i, 0);
            }
        }
        return (werte);
    }

    /**
     * erstellt aus den uebergebenen Werten einen Eintrag fuer die
     * Performanceanalyse
     *
     * @param ueberschrift
     *            Bezeichnung der Klasse, fuer die eine Performanceanalyse
     *            erstellt wird
     * @param methodenBezeichnungen
     *            Bezeichnung der Methoden
     * @param zaehler
     *            wie oft wurde jede Methode aufgerufen? muss die gleiche Anzahl
     *            an Elementen wie das Array methodenBezeichnungen enthalten
     * @param laufzeiten
     *            welche Gesamtlaufzeit hatten alle Aufrufe jeder Methode? muss
     *            die gleiche Anzahl an Elementen wie das Array
     *            methodenBezeichnungen enthalten
     * @return Performanceanalyse fuer die gewuenschten Daten
     */
    public String performanceAnalyse(final String ueberschrift, final String[] methodenBezeichnungen,
            final long[] zaehler, final long[] laufzeiten) {
        final StringBuffer sb = new StringBuffer();
        sb.append(ueberschrift).append(Constants_Allgemein.ENDL);
        sb.append("==========================================================================")
                .append(Constants_Allgemein.ENDL);
        if ((methodenBezeichnungen != null) && (zaehler != null) && (laufzeiten != null)) {
            final int length = methodenBezeichnungen.length;
            if ((zaehler.length == length) && (laufzeiten.length == length)) {
                for (int i = 0; i < length; i++) {
                    sb.append(methodenBezeichnungen[i]).append(": ").append(zaehler[i]);
                    if ((laufzeiten[i]) != 0) {
                        sb.append(String.format(", durchschnittliche Rechenzeit: %.2f Millisekunden",
                                Double.valueOf(berechneDurchschnittlicheRechenzeit(zaehler[i], laufzeiten[i]))));
                    }
                    sb.append(Constants_Allgemein.ENDL);
                }
            } else {
                this.log.warn(
                        ueberschrift + ": Die uebergebenen Arrays enthalten nicht die gleiche Anzahl an Elementen!");
            }
        } else {
            this.log.warn(ueberschrift + ": Mindestens 1 uebergebenes Array ist null!");
        }
        sb.append("--------------------------------------------------------------------------")
                .append(Constants_Allgemein.ENDL).append(Constants_Allgemein.ENDL);
        return (sb.toString());
    }

    /**
     * erstellt aus den uebergebenen Werten einen Eintrag fuer die
     * Performanceanalyse
     *
     * @param ueberschrift
     *            Bezeichnung der Klasse, fuer die eine Performanceanalyse
     *            erstellt wird
     * @param methodenBezeichnungen
     *            Bezeichnung der Methoden
     * @param zaehler
     *            wie oft wurde jede Methode aufgerufen? muss die gleiche Anzahl
     *            an Elementen wie das Array methodenBezeichnungen enthalten
     * @param laufzeiten
     *            welche Gesamtlaufzeit hatten alle Aufrufe jeder Methode? muss
     *            die gleiche Anzahl an Elementen wie das Array
     *            methodenBezeichnungen enthalten
     * @return Performanceanalyse fuer die gewuenschten Daten
     */
    public String performanceAnalyse(final String ueberschrift, final String[] methodenBezeichnungen,
            final AtomicLongArray zaehler, final AtomicLongArray laufzeiten) {
        final StringBuffer sb = new StringBuffer();
        sb.append(ueberschrift).append(Constants_Allgemein.ENDL);
        sb.append("==========================================================================")
                .append(Constants_Allgemein.ENDL);
        if ((methodenBezeichnungen != null) && (zaehler != null) && (laufzeiten != null)) {
            final int length = methodenBezeichnungen.length;
            if ((zaehler.length() == length) && (laufzeiten.length() == length)) {
                for (int i = 0; i < length; i++) {
                    sb.append(methodenBezeichnungen[i]).append(": ").append(zaehler.get(i));
                    if (laufzeiten.get(i) != 0) {
                        sb.append(String.format(", durchschnittliche Rechenzeit: %.2f Millisekunden", Double
                                .valueOf(berechneDurchschnittlicheRechenzeit(zaehler.get(i), laufzeiten.get(i)))));
                    }
                    sb.append(Constants_Allgemein.ENDL);
                }
            } else {
                this.log.warn(
                        ueberschrift + ": Die uebergebenen Arrays enthalten nicht die gleiche Anzahl an Elementen!");
            }
        } else {
            this.log.warn(ueberschrift + ": Mindestens 1 uebergebenes Array ist null!");
        }
        sb.append("--------------------------------------------------------------------------")
                .append(Constants_Allgemein.ENDL).append(Constants_Allgemein.ENDL);
        return (sb.toString());
    }

    protected String erstelleSubject(final Timestamp jetzt) {
        final StringBuffer subject = new StringBuffer();
        subject.append("Performance-Analyse fuer den Zeitraum ")
                .append(this.typConverter.getNotNullString(letzteAnalyse,
                        Constants_Datumsformat.DATUMSFORMAT_MIT_UHRZEIT))
                .append(" - ")
                .append(this.typConverter.getNotNullString(jetzt,
                        Constants_Datumsformat.DATUMSFORMAT_MIT_UHRZEIT))
                .append(" fuer die Anwendung '")
                .append(this.typConverter.getNotNullString(KonfigurationAllgemein.getAnwendung())).append("'");
        return (subject.toString());
    }

    protected String erstelleBody(final String subject) {
        final StringBuffer body = new StringBuffer();
        body.append(subject).append(Constants_Allgemein.ENDL).append(Constants_Allgemein.ENDL)
                .append(erstelleAnalyse());
        return (body.toString());
    }

    /**
     * setzt alle Zaehler auf 0 zurueck
     */
    protected void resetWerte() {
        this.log.info("resetUtilsWerte");
        TarifkatalogAbfrage.resetWerte();
        XmlUtilsAllgemein.resetWerte();
        PerformanceLogInInterceptor.resetWerte();
        SvSoapFaultInterceptorIn.resetWerte();
        SvSoapFaultInterceptorOut.resetWerte();
        UnexpectedElementEventHandler.resetWerte();
    }

    /**
     * fasst die Performance-Analysen aller relevanten Klassen zu einem String
     * zusammen
     */
    protected String erstelleAnalyse() {
        this.log.info("erstelleUtilsAnalyse");
        final StringBuffer sb = new StringBuffer();
        sb.append(TarifkatalogAbfrage.performanceAnalyse());
        sb.append(XmlUtilsAllgemein.performanceAnalyse());
        sb.append(PerformanceLogInInterceptor.performanceAnalyse());
        sb.append(SvSoapFaultInterceptorIn.performanceAnalyse());
        sb.append(SvSoapFaultInterceptorOut.performanceAnalyse());
        sb.append(UnexpectedElementEventHandler.performanceAnalyse());
        return (sb.toString());
    }

    /**
     * erstellt eine aktuelle PerformanceAnalyse und verschickt sie als E-Mail
     */
    public synchronized boolean erstelleAnalyse(final String request) {
        return (erstelleAnalyse(true, request));
    }

    /**
     * erstellt eine aktuelle PerformanceAnalyse
     *
     * @param als_mail_versenden
     *            true, falls ein E-Mail mit der PerformanceAnalyse an den
     *            definierten Empfaenger verschickt werden soll; false, falls
     *            kein E-Mail verschickt werden soll
     */
    public synchronized boolean erstelleAnalyse(final boolean als_mail_versenden, final String request) {
        boolean rueckgabe = false;
        try {
            final Timestamp jetzt = new Timestamp(System.currentTimeMillis());
            final String subject = erstelleSubject(jetzt);
            final String body = erstelleBody(subject) + Constants_Allgemein.ENDL
                    + new HelperAllgemein().mapToString(System.getProperties(), "Property");
            this.log.info(subject + " erstellt: " + Constants_Allgemein.ENDL + body);
            letzteAnalyse = jetzt;
            resetWerte();
            this.log.debug("als_mail_versenden = " + als_mail_versenden);
            if (als_mail_versenden) {
                getMailService().versendePerformanceAnalyse(subject, body, request);
            } else {
                this.log.info("PerformanceAnalyse wird nicht als Mail versendet!");
            }
            rueckgabe = true;
        } catch (final Exception e) {
            rueckgabe = false;
            this.log.error(this.exceptionHandlerAllgemein.getErrorMeldung(e, request));
        }
        return (rueckgabe);
    }
}
