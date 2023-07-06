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
public class BevorzugteTarifeDTO {
    /**
     * bevorzugte_tarife_id
     */
    private Long bevorzugte_tarife_id;

    /**
     * fachgebiet
     */
    private String fachgebiet;

    /**
     * applikation
     */
    private String applikation;

    /**
     * position
     */
    private beans.dto.PosnrBinfDTO position;

    /**
     * verordnungsreferenz
     */
    private beans.dto.VerordnungsreferenzDTO verordnungsreferenz;

    /**
     * kategorie
     */
    private String kategorie;

    /**
     * kategorie_wert
     */
    private String kategorie_wert;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable bevorzugte_tarife_id zurueck
     */
    public Long getBevorzugte_tarife_id() {
        return this.bevorzugte_tarife_id;
    }

    /**
     * setzt die Variable bevorzugte_tarife_id
     */
    public void setBevorzugte_tarife_id(final Long newvalue) {
        this.bevorzugte_tarife_id = newvalue;
    }

    /**
     * @return gibt die Variable fachgebiet zurueck
     */
    public String getFachgebiet() {
        return this.fachgebiet;
    }

    /**
     * setzt die Variable fachgebiet
     */
    public void setFachgebiet(final String newvalue) {
        this.fachgebiet = newvalue;
    }

    /**
     * @return gibt die Variable applikation zurueck
     */
    public String getApplikation() {
        return this.applikation;
    }

    /**
     * setzt die Variable applikation
     */
    public void setApplikation(final String newvalue) {
        this.applikation = newvalue;
    }

    /**
     * @return gibt die Variable position zurueck
     */
    public beans.dto.PosnrBinfDTO getPosition() {
        return this.position;
    }

    /**
     * setzt die Variable position
     */
    public void setPosition(final beans.dto.PosnrBinfDTO newvalue) {
        this.position = newvalue;
    }

    /**
     * @return gibt die Variable verordnungsreferenz zurueck
     */
    public beans.dto.VerordnungsreferenzDTO getVerordnungsreferenz() {
        return this.verordnungsreferenz;
    }

    /**
     * setzt die Variable verordnungsreferenz
     */
    public void setVerordnungsreferenz(final beans.dto.VerordnungsreferenzDTO newvalue) {
        this.verordnungsreferenz = newvalue;
    }

    /**
     * @return gibt die Variable kategorie zurueck
     */
    public String getKategorie() {
        return this.kategorie;
    }

    /**
     * setzt die Variable kategorie
     */
    public void setKategorie(final String newvalue) {
        this.kategorie = newvalue;
    }

    /**
     * @return gibt die Variable kategorie_wert zurueck
     */
    public String getKategorie_wert() {
        return this.kategorie_wert;
    }

    /**
     * setzt die Variable kategorie_wert
     */
    public void setKategorie_wert(final String newvalue) {
        this.kategorie_wert = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("BevorzugteTarifeDTO [");
        buffer.append("bevorzugte_tarife_id = ").append(this.bevorzugte_tarife_id).append(", ");
        buffer.append("fachgebiet = ").append(this.fachgebiet).append(", ");
        buffer.append("applikation = ").append(this.applikation).append(", ");
        buffer.append("position = ").append(this.position).append(", ");
        buffer.append("verordnungsreferenz = ").append(this.verordnungsreferenz).append(", ");
        buffer.append("kategorie = ").append(this.kategorie).append(", ");
        buffer.append("kategorie_wert = ").append(this.kategorie_wert).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
