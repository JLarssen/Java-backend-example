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
public class FPosnrJstatDTO {
    /**
     * posnr
     */
    private String posnr;

    /**
     * posnr_uegr
     */
    private String posnr_uegr;

    /**
     * posnr_hgr
     */
    private String posnr_hgr;

    /**
     * posnr_ugr
     */
    private String posnr_ugr;

    /**
     * stat_leistart
     */
    private Integer stat_leistart;

    /**
     * stat_form
     */
    private String stat_form;

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
     * @return gibt die Variable posnr_uegr zurueck
     */
    public String getPosnr_uegr() {
        return this.posnr_uegr;
    }

    /**
     * setzt die Variable posnr_uegr
     */
    public void setPosnr_uegr(final String newvalue) {
        this.posnr_uegr = newvalue;
    }

    /**
     * @return gibt die Variable posnr_hgr zurueck
     */
    public String getPosnr_hgr() {
        return this.posnr_hgr;
    }

    /**
     * setzt die Variable posnr_hgr
     */
    public void setPosnr_hgr(final String newvalue) {
        this.posnr_hgr = newvalue;
    }

    /**
     * @return gibt die Variable posnr_ugr zurueck
     */
    public String getPosnr_ugr() {
        return this.posnr_ugr;
    }

    /**
     * setzt die Variable posnr_ugr
     */
    public void setPosnr_ugr(final String newvalue) {
        this.posnr_ugr = newvalue;
    }

    /**
     * @return gibt die Variable stat_leistart zurueck
     */
    public Integer getStat_leistart() {
        return this.stat_leistart;
    }

    /**
     * setzt die Variable stat_leistart
     */
    public void setStat_leistart(final Integer newvalue) {
        this.stat_leistart = newvalue;
    }

    /**
     * @return gibt die Variable stat_form zurueck
     */
    public String getStat_form() {
        return this.stat_form;
    }

    /**
     * setzt die Variable stat_form
     */
    public void setStat_form(final String newvalue) {
        this.stat_form = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("FPosnrJstatDTO [");
        buffer.append("posnr = ").append(this.posnr).append(", ");
        buffer.append("posnr_uegr = ").append(this.posnr_uegr).append(", ");
        buffer.append("posnr_hgr = ").append(this.posnr_hgr).append(", ");
        buffer.append("posnr_ugr = ").append(this.posnr_ugr).append(", ");
        buffer.append("stat_leistart = ").append(this.stat_leistart).append(", ");
        buffer.append("stat_form = ").append(this.stat_form).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
