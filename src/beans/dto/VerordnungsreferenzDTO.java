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
public class VerordnungsreferenzDTO {
    /**
     * verordnungsreferenz_id
     */
    private Long verordnungsreferenz_id;

    /**
     * verordnungsreferenz
     */
    private String verordnungsreferenz;

    /**
     * verordnungsreferenz_txt
     */
    private String verordnungsreferenz_txt;

    /**
     * von_am
     */
    private java.sql.Date von_am;

    /**
     * bis
     */
    private java.sql.Date bis;

    /**
     * kategorie_txt
     */
    private String kategorie_txt;

    /**
     * code_ua
     */
    private String code_ua;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable verordnungsreferenz_id zurueck
     */
    public Long getVerordnungsreferenz_id() {
        return this.verordnungsreferenz_id;
    }

    /**
     * setzt die Variable verordnungsreferenz_id
     */
    public void setVerordnungsreferenz_id(final Long newvalue) {
        this.verordnungsreferenz_id = newvalue;
    }

    /**
     * @return gibt die Variable verordnungsreferenz zurueck
     */
    public String getVerordnungsreferenz() {
        return this.verordnungsreferenz;
    }

    /**
     * setzt die Variable verordnungsreferenz
     */
    public void setVerordnungsreferenz(final String newvalue) {
        this.verordnungsreferenz = newvalue;
    }

    /**
     * @return gibt die Variable verordnungsreferenz_txt zurueck
     */
    public String getVerordnungsreferenz_txt() {
        return this.verordnungsreferenz_txt;
    }

    /**
     * setzt die Variable verordnungsreferenz_txt
     */
    public void setVerordnungsreferenz_txt(final String newvalue) {
        this.verordnungsreferenz_txt = newvalue;
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

    /**
     * @return gibt die Variable kategorie_txt zurueck
     */
    public String getKategorie_txt() {
        return this.kategorie_txt;
    }

    /**
     * setzt die Variable kategorie_txt
     */
    public void setKategorie_txt(final String newvalue) {
        this.kategorie_txt = newvalue;
    }

    /**
     * @return gibt die Variable code_ua zurueck
     */
    public String getCode_ua() {
        return this.code_ua;
    }

    /**
     * setzt die Variable code_ua
     */
    public void setCode_ua(final String newvalue) {
        this.code_ua = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("VerordnungsreferenzDTO [");
        buffer.append("verordnungsreferenz_id = ").append(this.verordnungsreferenz_id).append(", ");
        buffer.append("verordnungsreferenz = ").append(this.verordnungsreferenz).append(", ");
        buffer.append("verordnungsreferenz_txt = ").append(this.verordnungsreferenz_txt).append(", ");
        buffer.append("von_am = ").append(this.von_am).append(", ");
        buffer.append("bis = ").append(this.bis).append(", ");
        buffer.append("kategorie_txt = ").append(this.kategorie_txt).append(", ");
        buffer.append("code_ua = ").append(this.code_ua).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
