package sv.utils.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author SV-Benutzer
 */
public class KonfigurationMailversand implements Serializable {
    private static final long serialVersionUID = 117497813245864966L;

    private static String smtp_host = null;

    private static Integer smtp_port = null;

    private static String von = null;

    private static String vonExtern = null;

    private static List<String> fehlerAn = null;

    private static List<String> performanceStatistikAn = null;

    /**
     * @return the smtp_host
     */
    public static String getSmtp_host() {
        return (smtp_host);
    }

    /**
     * @param smtp_host
     *            the smtp_host to set
     */
    public static void setSmtp_host(final String smtp_host) {
        KonfigurationMailversand.smtp_host = smtp_host;
    }

    /**
     * @return the smtp_port
     */
    public static Integer getSmtp_port() {
        return (smtp_port);
    }

    /**
     * @param smtp_port
     *            the smtp_port to set
     */
    public static void setSmtp_port(final Integer smtp_port) {
        KonfigurationMailversand.smtp_port = smtp_port;
    }

    /**
     * @return the performanceStatistikAn
     */
    public static List<String> getPerformanceStatistikAn() {
        return (performanceStatistikAn);
    }

    /**
     * @param performanceStatistikAn
     *            the performanceStatistikAn to set
     */
    public static void setPerformanceStatistikAn(final List<String> performanceStatistikAn) {
        KonfigurationMailversand.performanceStatistikAn = performanceStatistikAn;
    }

    /**
     * @return the von
     */
    public static String getVon() {
        return (von);
    }

    /**
     * @param von
     *            the von to set
     */
    public static void setVon(final String von) {
        KonfigurationMailversand.von = von;
    }

    /**
     * @return the vonExtern
     */
    public static String getVonExtern() {
        return (vonExtern);
    }

    /**
     * @param vonExtern
     *            the vonExtern to set
     */
    public static void setVonExtern(final String vonExtern) {
        KonfigurationMailversand.vonExtern = vonExtern;
    }

    /**
     * @return the fehlerAn
     */
    public static List<String> getFehlerAn() {
        return (fehlerAn);
    }

    /**
     * @param fehlerAn
     *            the fehlerAn to set
     */
    public static void setFehlerAn(final List<String> fehlerAn) {
        KonfigurationMailversand.fehlerAn = fehlerAn;
    }

    /**
     * macht aus der KonfigurationMailversand einen String
     */
    public static String konfigurationMailversandToString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("KonfigurationMailversand[");
        sb.append("fehlerAn = ").append(fehlerAn);
        sb.append(", performanceStatistikAn = ").append(performanceStatistikAn);
        sb.append(", serialVersionUID = ").append(serialVersionUID);
        sb.append(", smtp_host = ").append(smtp_host);
        sb.append(", smtp_port = ").append(smtp_port);
        sb.append(", von = ").append(von);
        sb.append(", vonExtern = ").append(vonExtern);
        sb.append("]");
        return sb.toString();
    }
}
