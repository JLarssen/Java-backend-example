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
public class FPosnrVpDTO implements Serializable {
    /**
     * posnr
     */
    private String posnr;

    /**
     * kvpnr
     */
    private String kvpnr;

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
     * @return gibt die Variable kvpnr zurueck
     */
    public String getKvpnr() {
        return this.kvpnr;
    }

    /**
     * setzt die Variable kvpnr
     */
    public void setKvpnr(final String newvalue) {
        this.kvpnr = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("FPosnrVpDTO [");
        buffer.append("posnr = ").append(this.posnr).append(", ");
        buffer.append("kvpnr = ").append(this.kvpnr).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
