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
public class FKtoBhDTO implements Serializable {
    /**
     * lsp_wert
     */
    private Integer lsp_wert;

    /**
     * lsp_zuord
     */
    private Integer lsp_zuord;

    /**
     * glt_ber
     */
    private String glt_ber;

    /**
     * kto_kr
     */
    private String kto_kr;

    /**
     * kto_nr
     */
    private String kto_nr;

    /**
     * kto_hvang
     */
    private String kto_hvang;

    /**
     * kto_dst
     */
    private String kto_dst;

    /**
     * kto_glsl
     */
    private String kto_glsl;

    /**
     * kto_bkz
     */
    private String kto_bkz;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable lsp_wert zurueck
     */
    public Integer getLsp_wert() {
        return this.lsp_wert;
    }

    /**
     * setzt die Variable lsp_wert
     */
    public void setLsp_wert(final Integer newvalue) {
        this.lsp_wert = newvalue;
    }

    /**
     * @return gibt die Variable lsp_zuord zurueck
     */
    public Integer getLsp_zuord() {
        return this.lsp_zuord;
    }

    /**
     * setzt die Variable lsp_zuord
     */
    public void setLsp_zuord(final Integer newvalue) {
        this.lsp_zuord = newvalue;
    }

    /**
     * @return gibt die Variable glt_ber zurueck
     */
    public String getGlt_ber() {
        return this.glt_ber;
    }

    /**
     * setzt die Variable glt_ber
     */
    public void setGlt_ber(final String newvalue) {
        this.glt_ber = newvalue;
    }

    /**
     * @return gibt die Variable kto_kr zurueck
     */
    public String getKto_kr() {
        return this.kto_kr;
    }

    /**
     * setzt die Variable kto_kr
     */
    public void setKto_kr(final String newvalue) {
        this.kto_kr = newvalue;
    }

    /**
     * @return gibt die Variable kto_nr zurueck
     */
    public String getKto_nr() {
        return this.kto_nr;
    }

    /**
     * setzt die Variable kto_nr
     */
    public void setKto_nr(final String newvalue) {
        this.kto_nr = newvalue;
    }

    /**
     * @return gibt die Variable kto_hvang zurueck
     */
    public String getKto_hvang() {
        return this.kto_hvang;
    }

    /**
     * setzt die Variable kto_hvang
     */
    public void setKto_hvang(final String newvalue) {
        this.kto_hvang = newvalue;
    }

    /**
     * @return gibt die Variable kto_dst zurueck
     */
    public String getKto_dst() {
        return this.kto_dst;
    }

    /**
     * setzt die Variable kto_dst
     */
    public void setKto_dst(final String newvalue) {
        this.kto_dst = newvalue;
    }

    /**
     * @return gibt die Variable kto_glsl zurueck
     */
    public String getKto_glsl() {
        return this.kto_glsl;
    }

    /**
     * setzt die Variable kto_glsl
     */
    public void setKto_glsl(final String newvalue) {
        this.kto_glsl = newvalue;
    }

    /**
     * @return gibt die Variable kto_bkz zurueck
     */
    public String getKto_bkz() {
        return this.kto_bkz;
    }

    /**
     * setzt die Variable kto_bkz
     */
    public void setKto_bkz(final String newvalue) {
        this.kto_bkz = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("FKtoBhDTO [");
        buffer.append("lsp_wert = ").append(this.lsp_wert).append(", ");
        buffer.append("lsp_zuord = ").append(this.lsp_zuord).append(", ");
        buffer.append("glt_ber = ").append(this.glt_ber).append(", ");
        buffer.append("kto_kr = ").append(this.kto_kr).append(", ");
        buffer.append("kto_nr = ").append(this.kto_nr).append(", ");
        buffer.append("kto_hvang = ").append(this.kto_hvang).append(", ");
        buffer.append("kto_dst = ").append(this.kto_dst).append(", ");
        buffer.append("kto_glsl = ").append(this.kto_glsl).append(", ");
        buffer.append("kto_bkz = ").append(this.kto_bkz).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
