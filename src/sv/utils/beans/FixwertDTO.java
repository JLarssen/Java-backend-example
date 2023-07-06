package sv.utils.beans;

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
public class FixwertDTO {
    /**
     * serialVersionUID = 38467869L
     */
    private static final long serialVersionUID = 38467869L;

    /**
     * zeitraumSet
     */
    private java.util.Set<FixwertZeitraumDTO> zeitraumSet;

    /**
     * fixwert_typ
     */
    private String fixwert_typ;

    /**
     * fixwert_position
     */
    private Integer fixwert_position;

    /**
     * fixwert_code
     */
    private String fixwert_code;

    /**
     * fixwert_ktxt
     */
    private String fixwert_ktxt;

    /**
     * fixwert_ltxt
     */
    private String fixwert_ltxt;

    /**
     * fixwert_ztxt
     */
    private String fixwert_ztxt;

    /**
     * gueltig_hv
     */
    private Boolean gueltig_hv;

    /**
     * gueltig_ang
     */
    private Boolean gueltig_ang;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable zeitraumSet zurueck
     */
    public java.util.Set<FixwertZeitraumDTO> getZeitraumSet() {
        return this.zeitraumSet;
    }

    /**
     * setzt die Variable zeitraumSet
     */
    public void setZeitraumSet(final java.util.Set<FixwertZeitraumDTO> newvalue) {
        this.zeitraumSet = newvalue;
    }

    /**
     * @return gibt die Variable fixwert_typ zurueck
     */
    public String getFixwert_typ() {
        return this.fixwert_typ;
    }

    /**
     * setzt die Variable fixwert_typ
     */
    public void setFixwert_typ(final String newvalue) {
        this.fixwert_typ = newvalue;
    }

    /**
     * @return gibt die Variable fixwert_position zurueck
     */
    public Integer getFixwert_position() {
        return this.fixwert_position;
    }

    /**
     * setzt die Variable fixwert_position
     */
    public void setFixwert_position(final Integer newvalue) {
        this.fixwert_position = newvalue;
    }

    /**
     * @return gibt die Variable fixwert_code zurueck
     */
    public String getFixwert_code() {
        return this.fixwert_code;
    }

    /**
     * setzt die Variable fixwert_code
     */
    public void setFixwert_code(final String newvalue) {
        this.fixwert_code = newvalue;
    }

    /**
     * @return gibt die Variable fixwert_ktxt zurueck
     */
    public String getFixwert_ktxt() {
        return this.fixwert_ktxt;
    }

    /**
     * setzt die Variable fixwert_ktxt
     */
    public void setFixwert_ktxt(final String newvalue) {
        this.fixwert_ktxt = newvalue;
    }

    /**
     * @return gibt die Variable fixwert_ltxt zurueck
     */
    public String getFixwert_ltxt() {
        return this.fixwert_ltxt;
    }

    /**
     * setzt die Variable fixwert_ltxt
     */
    public void setFixwert_ltxt(final String newvalue) {
        this.fixwert_ltxt = newvalue;
    }

    /**
     * @return gibt die Variable fixwert_ztxt zurueck
     */
    public String getFixwert_ztxt() {
        return this.fixwert_ztxt;
    }

    /**
     * setzt die Variable fixwert_ztxt
     */
    public void setFixwert_ztxt(final String newvalue) {
        this.fixwert_ztxt = newvalue;
    }

    /**
     * @return gibt die Variable gueltig_hv zurueck
     */
    public Boolean getGueltig_hv() {
        return this.gueltig_hv;
    }

    /**
     * setzt die Variable gueltig_hv
     */
    public void setGueltig_hv(final Boolean newvalue) {
        this.gueltig_hv = newvalue;
    }

    /**
     * @return gibt die Variable gueltig_ang zurueck
     */
    public Boolean getGueltig_ang() {
        return this.gueltig_ang;
    }

    /**
     * setzt die Variable gueltig_ang
     */
    public void setGueltig_ang(final Boolean newvalue) {
        this.gueltig_ang = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("FixwertDTO [");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("zeitraumSet = ").append(this.zeitraumSet).append(", ");
        buffer.append("fixwert_typ = ").append(this.fixwert_typ).append(", ");
        buffer.append("fixwert_position = ").append(this.fixwert_position).append(", ");
        buffer.append("fixwert_code = ").append(this.fixwert_code).append(", ");
        buffer.append("fixwert_ktxt = ").append(this.fixwert_ktxt).append(", ");
        buffer.append("fixwert_ltxt = ").append(this.fixwert_ltxt).append(", ");
        buffer.append("fixwert_ztxt = ").append(this.fixwert_ztxt).append(", ");
        buffer.append("gueltig_hv = ").append(this.gueltig_hv).append(", ");
        buffer.append("gueltig_ang = ").append(this.gueltig_ang).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
