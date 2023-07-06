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
public class FBinfAbschnittDTO {
    /**
     * abslfdnr
     */
    private Integer abslfdnr;

    /**
     * fbinfKapSet
     */
    private java.util.Set<FBinfKapDTO> fbinfKapSet;

    /**
     * abschnitt_pre
     */
    private String abschnitt_pre;

    /**
     * abschnitt_name
     */
    private String abschnitt_name;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable abslfdnr zurueck
     */
    public Integer getAbslfdnr() {
        return this.abslfdnr;
    }

    /**
     * setzt die Variable abslfdnr
     */
    public void setAbslfdnr(final Integer newvalue) {
        this.abslfdnr = newvalue;
    }

    /**
     * @return gibt die Variable fbinfKapSet zurueck
     */
    public java.util.Set<FBinfKapDTO> getFbinfKapSet() {
        return this.fbinfKapSet;
    }

    /**
     * setzt die Variable fbinfKapSet
     */
    public void setFbinfKapSet(final java.util.Set<FBinfKapDTO> newvalue) {
        this.fbinfKapSet = newvalue;
    }

    /**
     * @return gibt die Variable abschnitt_pre zurueck
     */
    public String getAbschnitt_pre() {
        return this.abschnitt_pre;
    }

    /**
     * setzt die Variable abschnitt_pre
     */
    public void setAbschnitt_pre(final String newvalue) {
        this.abschnitt_pre = newvalue;
    }

    /**
     * @return gibt die Variable abschnitt_name zurueck
     */
    public String getAbschnitt_name() {
        return this.abschnitt_name;
    }

    /**
     * setzt die Variable abschnitt_name
     */
    public void setAbschnitt_name(final String newvalue) {
        this.abschnitt_name = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("FBinfAbschnittDTO [");
        buffer.append("abslfdnr = ").append(this.abslfdnr).append(", ");
        buffer.append("fbinfKapSet = ").append(this.fbinfKapSet).append(", ");
        buffer.append("abschnitt_pre = ").append(this.abschnitt_pre).append(", ");
        buffer.append("abschnitt_name = ").append(this.abschnitt_name).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
