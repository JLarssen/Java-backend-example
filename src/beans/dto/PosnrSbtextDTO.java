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
public class PosnrSbtextDTO implements Serializable {
    /**
     * posnr
     */
    private String posnr;

    /**
     * kzbea_id
     */
    private String kzbea_id;

    /**
     * kzbea_ts
     */
    private java.sql.Timestamp kzbea_ts;

    /**
     * posnr_sbtext
     */
    private String posnr_sbtext;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable posnr zurueck
     */
    public String getPosnr() {
        return this.posnr;
    }

    /**
     * setzt die Variable posnr
     */
    public void setPosnr(final String newvalue) {
        this.posnr = newvalue;
    }

    /**
     * @return gibt die Variable kzbea_id zurueck
     */
    public String getKzbea_id() {
        return this.kzbea_id;
    }

    /**
     * setzt die Variable kzbea_id
     */
    public void setKzbea_id(final String newvalue) {
        this.kzbea_id = newvalue;
    }

    /**
     * @return gibt die Variable kzbea_ts zurueck
     */
    public java.sql.Timestamp getKzbea_ts() {
        return this.kzbea_ts;
    }

    /**
     * setzt die Variable kzbea_ts
     */
    public void setKzbea_ts(final java.sql.Timestamp newvalue) {
        this.kzbea_ts = newvalue;
    }

    /**
     * @return gibt die Variable posnr_sbtext zurueck
     */
    public String getPosnr_sbtext() {
        return this.posnr_sbtext;
    }

    /**
     * setzt die Variable posnr_sbtext
     */
    public void setPosnr_sbtext(final String newvalue) {
        this.posnr_sbtext = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("PosnrSbtextDTO [");
        buffer.append("posnr = ").append(this.posnr).append(", ");
        buffer.append("kzbea_id = ").append(this.kzbea_id).append(", ");
        buffer.append("kzbea_ts = ").append(this.kzbea_ts).append(", ");
        buffer.append("posnr_sbtext = ").append(this.posnr_sbtext).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
