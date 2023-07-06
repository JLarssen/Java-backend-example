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
public class PosnrTextDTO {
    /**
     * posnr
     */
    private String posnr;

    /**
     * alpha
     */
    private String alpha;

    /**
     * posnrktxt
     */
    private String posnrktxt;

    /**
     * posnrltxt
     */
    private String posnrltxt;

    /**
     * wlst
     */
    private Integer wlst;

    /**
     * bearbdat
     */
    private java.sql.Date bearbdat;

    /**
     * aenderungstag
     */
    private java.sql.Date aenderungstag;

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
     * @return gibt die Variable alpha zurueck
     */
    public String getAlpha() {
        return this.alpha;
    }

    /**
     * setzt die Variable alpha
     */
    public void setAlpha(final String newvalue) {
        this.alpha = newvalue;
    }

    /**
     * @return gibt die Variable posnrktxt zurueck
     */
    public String getPosnrktxt() {
        return this.posnrktxt;
    }

    /**
     * setzt die Variable posnrktxt
     */
    public void setPosnrktxt(final String newvalue) {
        this.posnrktxt = newvalue;
    }

    /**
     * @return gibt die Variable posnrltxt zurueck
     */
    public String getPosnrltxt() {
        return this.posnrltxt;
    }

    /**
     * setzt die Variable posnrltxt
     */
    public void setPosnrltxt(final String newvalue) {
        this.posnrltxt = newvalue;
    }

    /**
     * @return gibt die Variable wlst zurueck
     */
    public Integer getWlst() {
        return this.wlst;
    }

    /**
     * setzt die Variable wlst
     */
    public void setWlst(final Integer newvalue) {
        this.wlst = newvalue;
    }

    /**
     * @return gibt die Variable bearbdat zurueck
     */
    public java.sql.Date getBearbdat() {
        return this.bearbdat;
    }

    /**
     * setzt die Variable bearbdat
     */
    public void setBearbdat(final java.sql.Date newvalue) {
        this.bearbdat = newvalue;
    }

    /**
     * @return gibt die Variable aenderungstag zurueck
     */
    public java.sql.Date getAenderungstag() {
        return this.aenderungstag;
    }

    /**
     * setzt die Variable aenderungstag
     */
    public void setAenderungstag(final java.sql.Date newvalue) {
        this.aenderungstag = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("PosnrTextDTO [");
        buffer.append("posnr = ").append(this.posnr).append(", ");
        buffer.append("alpha = ").append(this.alpha).append(", ");
        buffer.append("posnrktxt = ").append(this.posnrktxt).append(", ");
        buffer.append("posnrltxt = ").append(this.posnrltxt).append(", ");
        buffer.append("wlst = ").append(this.wlst).append(", ");
        buffer.append("bearbdat = ").append(this.bearbdat).append(", ");
        buffer.append("aenderungstag = ").append(this.aenderungstag).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
