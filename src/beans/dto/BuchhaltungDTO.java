package beans.dto;

/**
 *
 *
 * GENERATED CONTENT - DO NOT EDIT!
 *
 * Disclaimer: this content gets replaced with every run of the generator!
 * Template: class_description.txt
 *
 * @author sca-mda-generator
 *
 */
public class BuchhaltungDTO {
    /**
     * buchhaltung_id
     */
    private Long buchhaltung_id;

    /**
     * buchhaltungskonto
     */
    private String buchhaltungskonto;

    /**
     * von_am
     */
    private java.sql.Date von_am;

    /**
     * bis
     */
    private java.sql.Date bis;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable buchhaltung_id zurueck
     */
    public Long getBuchhaltung_id() {
        return this.buchhaltung_id;
    }

    /**
     * setzt die Variable buchhaltung_id
     */
    public void setBuchhaltung_id(final Long newvalue) {
        this.buchhaltung_id = newvalue;
    }

    /**
     * @return gibt die Variable buchhaltungskonto zurueck
     */
    public String getBuchhaltungskonto() {
        return this.buchhaltungskonto;
    }

    /**
     * setzt die Variable buchhaltungskonto
     */
    public void setBuchhaltungskonto(final String newvalue) {
        this.buchhaltungskonto = newvalue;
    }

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

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("BuchhaltungDTO [");
        buffer.append("buchhaltung_id = ").append(this.buchhaltung_id).append(", ");
        buffer.append("buchhaltungskonto = ").append(this.buchhaltungskonto).append(", ");
        buffer.append("von_am = ").append(this.von_am).append(", ");
        buffer.append("bis = ").append(this.bis).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
