package sv.utils.beans;

import java.math.BigDecimal;

/**
 *
 *
 * GENERATED CONTENT - DO NOT EDIT!
 *
 * Disclaimer: this content gets replaced with every run of the generator!
 *
 * @author sca-mda-generator
 *
 */
public class FixwertZeitraumDTO {
    /**
     * serialVersionUID = 38467869L
     */
    private static final long serialVersionUID = 38467869L;

    /**
     * von_am
     */
    private java.sql.Date von_am;

    /**
     * bis
     */
    private java.sql.Date bis;

    /**
     * betrag
     */
    private BigDecimal betrag;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable von_am zurueck
     */
    public java.sql.Date getVon_am() {
        return this.von_am;
    }

    /**
     * setzt die Variable von_am
     */
    public void setVon_am(final java.sql.Date newvalue) {
        this.von_am = newvalue;
    }

    /**
     * @return gibt die Variable bis zurueck
     */
    public java.sql.Date getBis() {
        return this.bis;
    }

    /**
     * setzt die Variable bis
     */
    public void setBis(final java.sql.Date newvalue) {
        this.bis = newvalue;
    }

    /**
     * @return gibt die Variable betrag zurueck
     */
    public BigDecimal getBetrag() {
        return this.betrag;
    }

    /**
     * setzt die Variable betrag
     */
    public void setBetrag(final BigDecimal newvalue) {
        this.betrag = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("FixwertZeitraumDTO [");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("von_am = ").append(this.von_am).append(", ");
        buffer.append("bis = ").append(this.bis).append(", ");
        buffer.append("betrag = ").append(this.betrag).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
