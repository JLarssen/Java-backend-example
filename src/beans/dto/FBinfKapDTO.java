package beans.dto;

import java.io.Serializable;

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
public class FBinfKapDTO implements Serializable {
    /**
     * kaplfdnr
     */
    private Integer kaplfdnr;

    /**
     * abslfdnr
     */
    private Integer abslfdnr;

    /**
     * fbinfSubkapSet
     */
    private java.util.Set<FBinfSubkapDTO> fbinfSubkapSet;

    /**
     * kapitel_pre
     */
    private String kapitel_pre;

    /**
     * kapitel_name
     */
    private String kapitel_name;

    /**
     * kapitel_pre_arab
     */
    private Integer kapitel_pre_arab;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable kaplfdnr zurueck
     */
    public Integer getKaplfdnr() {
        return this.kaplfdnr;
    }

    /**
     * setzt die Variable kaplfdnr
     */
    public void setKaplfdnr(final Integer newvalue) {
        this.kaplfdnr = newvalue;
    }

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
     * @return gibt die Variable fbinfSubkapSet zurueck
     */
    public java.util.Set<FBinfSubkapDTO> getFbinfSubkapSet() {
        return this.fbinfSubkapSet;
    }

    /**
     * setzt die Variable fbinfSubkapSet
     */
    public void setFbinfSubkapSet(final java.util.Set<FBinfSubkapDTO> newvalue) {
        this.fbinfSubkapSet = newvalue;
    }

    /**
     * @return gibt die Variable kapitel_pre zurueck
     */
    public String getKapitel_pre() {
        return this.kapitel_pre;
    }

    /**
     * setzt die Variable kapitel_pre
     */
    public void setKapitel_pre(final String newvalue) {
        this.kapitel_pre = newvalue;
    }

    /**
     * @return gibt die Variable kapitel_name zurueck
     */
    public String getKapitel_name() {
        return this.kapitel_name;
    }

    /**
     * setzt die Variable kapitel_name
     */
    public void setKapitel_name(final String newvalue) {
        this.kapitel_name = newvalue;
    }

    /**
     * @return gibt die Variable kapitel_pre_arab zurueck
     */
    public Integer getKapitel_pre_arab() {
        return this.kapitel_pre_arab;
    }

    /**
     * setzt die Variable kapitel_pre_arab
     */
    public void setKapitel_pre_arab(final Integer newvalue) {
        this.kapitel_pre_arab = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("FBinfKapDTO [");
        buffer.append("kaplfdnr = ").append(this.kaplfdnr).append(", ");
        buffer.append("abslfdnr = ").append(this.abslfdnr).append(", ");
        buffer.append("fbinfSubkapSet = ").append(this.fbinfSubkapSet).append(", ");
        buffer.append("kapitel_pre = ").append(this.kapitel_pre).append(", ");
        buffer.append("kapitel_name = ").append(this.kapitel_name).append(", ");
        buffer.append("kapitel_pre_arab = ").append(this.kapitel_pre_arab).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
