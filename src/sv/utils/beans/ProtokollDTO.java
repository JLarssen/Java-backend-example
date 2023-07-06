package sv.utils.beans;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import constants.Constants_Allgemein;

/**
 * @author SV-Benutzer
 *
 */
public class ProtokollDTO {
    private Map<String, Long> zaehlen = new LinkedHashMap<>();

    private Map<String, String> zaehlen_beschreibung = new LinkedHashMap<>();

    /**
     *
     * @param zaehlen_txt
     *            definierte position die um 1 erhoeht wird
     */
    public synchronized void plus(final String zaehlen_txt) {
        plus(zaehlen_txt, 1);
    }

    /**
     *
     * @param zaehlen_txt
     *            definierte position die um die WERT erhoeht wird
     *
     */
    public synchronized void plus(final String zaehlen_txt, final int wert) {
        if (this.zaehlen == null) {
            this.zaehlen = new LinkedHashMap<>();
            this.zaehlen.put(zaehlen_txt, Long.valueOf(0));
        }
        Long anz = this.zaehlen.get(zaehlen_txt);
        if (anz == null) {
            anz = Long.valueOf(0);
        }
        this.zaehlen.put(zaehlen_txt, Long.valueOf(anz.longValue() + wert));
    }

    /**
     * liefert die gezaehlte wert zu definierte position zurueck
     *
     * @return anzahl
     */
    public long getzaehlen(final String zaehlen_txt) {
        if (this.zaehlen == null) {
            this.zaehlen = new LinkedHashMap<>();
            this.zaehlen.put(zaehlen_txt, Long.valueOf(0));
        }
        Long anz = this.zaehlen.get(zaehlen_txt);
        if (anz == null) {
            anz = Long.valueOf(0);
        }
        return (anz.longValue());
    }

    /**
     * Ordnet eine Beschreibung fuer eine definierte Position
     *
     * @param zaehlen_txt
     *            gezaehlte
     * @param beschreibung
     *            Text
     */
    public void setBeschreibung(final String zaehlen_txt, final String beschreibung) {
        if (this.zaehlen_beschreibung == null) {
            this.zaehlen_beschreibung = new LinkedHashMap<>();
        }
        this.zaehlen_beschreibung.put(zaehlen_txt, beschreibung);
    }

    /**
     * Protokoll anzeigen
     */
    public String getProtokoll() {
        String string = null;
        if ((this.zaehlen_beschreibung != null) && (this.zaehlen != null)) {
            final StringBuffer sb = new StringBuffer();
            final Set<Entry<String, String>> zb = this.zaehlen_beschreibung.entrySet();
            if (zb != null) {
                for (final Entry<String, String> ezb : zb) {
                    if (ezb != null) {
                        final String key = ezb.getKey();
                        sb.append(ezb.getValue());
                        sb.append(getzaehlen(key));
                        sb.append(Constants_Allgemein.ENDL);
                    }
                }
            }
            string = sb.toString();
        }
        return (string);
    }

    /**
     * Protokoll anzeigen
     */
    public String protokollAnzeigen() {
        String string = null;
        if (this.zaehlen != null) {
            final StringBuffer sb = new StringBuffer();
            final Set<Entry<String, Long>> zaehlenSet = this.zaehlen.entrySet();
            if (zaehlenSet != null) {
                sb.append(Constants_Allgemein.ENDL);
                for (final Entry<String, Long> z : zaehlenSet) {
                    if (z != null) {
                        sb.append(z.getKey());
                        sb.append(z.getValue());
                        sb.append(Constants_Allgemein.ENDL);
                    }
                }
            }
            string = sb.toString();
        }
        return (string);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ProtokollDTO [zaehlen=");
        builder.append(this.zaehlen);
        builder.append("]");
        return builder.toString();
    }
}
