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
public class FBinfSubkapDTO implements Serializable {
    /**
     * sublfdnr
     */
    private Integer sublfdnr;

    /**
     * kaplfdnr
     */
    private Integer kaplfdnr;

    /**
     * abslfdnr
     */
    private Integer abslfdnr;

    /**
     * subkap_pre
     */
    private String subkap_pre;

    /**
     * subkap_name
     */
    private String subkap_name;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable sublfdnr zurueck
     */
    public Integer getSublfdnr() {
        return this.sublfdnr;
    }

    /**
     * setzt die Variable sublfdnr
     */
    public void setSublfdnr(final Integer newvalue) {
        this.sublfdnr = newvalue;
    }

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
     * @return gibt die Variable subkap_pre zurueck
     */
    public String getSubkap_pre() {
        return this.subkap_pre;
    }

    /**
     * setzt die Variable subkap_pre
     */
    public void setSubkap_pre(final String newvalue) {
        this.subkap_pre = newvalue;
    }

    /**
     * @return gibt die Variable subkap_name zurueck
     */
    public String getSubkap_name() {
        return this.subkap_name;
    }

    /**
     * setzt die Variable subkap_name
     */
    public void setSubkap_name(final String newvalue) {
        this.subkap_name = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("FBinfSubkapDTO [");
        buffer.append("sublfdnr = ").append(this.sublfdnr).append(", ");
        buffer.append("kaplfdnr = ").append(this.kaplfdnr).append(", ");
        buffer.append("abslfdnr = ").append(this.abslfdnr).append(", ");
        buffer.append("subkap_pre = ").append(this.subkap_pre).append(", ");
        buffer.append("subkap_name = ").append(this.subkap_name).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
