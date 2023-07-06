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
public class PosnrFachDTO {
    /**
     * posnr_fach_id
     */
    private Long posnr_fach_id;

    /**
     * posnr
     */
    private String posnr;

    /**
     * fachg
     */
    private Integer fachg;

    /**
     * kvpnr
     */
    private String kvpnr;

    /**
     * vp_fachg
     */
    private Integer vp_fachg;

    /**
     * vk_kbund
     */
    private String vk_kbund;

    /**
     * kadr_code
     */
    private Integer kadr_code;

    /**
     * labe
     */
    private java.sql.Date labe;

    /**
     * laen
     */
    private java.sql.Date laen;

    /**
     * hdat_nr
     */
    private String hdat_nr;

    /**
     * kerst_ts
     */
    private java.sql.Timestamp kerst_ts;

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
     * @return gibt die Variable posnr_fach_id zurueck
     */
    public Long getPosnr_fach_id() {
        return this.posnr_fach_id;
    }

    /**
     * setzt die Variable posnr_fach_id
     */
    public void setPosnr_fach_id(final Long newvalue) {
        this.posnr_fach_id = newvalue;
    }

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
     * @return gibt die Variable fachg zurueck
     */
    public Integer getFachg() {
        return this.fachg;
    }

    /**
     * setzt die Variable fachg
     */
    public void setFachg(final Integer newvalue) {
        this.fachg = newvalue;
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

    /**
     * @return gibt die Variable vp_fachg zurueck
     */
    public Integer getVp_fachg() {
        return this.vp_fachg;
    }

    /**
     * setzt die Variable vp_fachg
     */
    public void setVp_fachg(final Integer newvalue) {
        this.vp_fachg = newvalue;
    }

    /**
     * @return gibt die Variable vk_kbund zurueck
     */
    public String getVk_kbund() {
        return this.vk_kbund;
    }

    /**
     * setzt die Variable vk_kbund
     */
    public void setVk_kbund(final String newvalue) {
        this.vk_kbund = newvalue;
    }

    /**
     * @return gibt die Variable kadr_code zurueck
     */
    public Integer getKadr_code() {
        return this.kadr_code;
    }

    /**
     * setzt die Variable kadr_code
     */
    public void setKadr_code(final Integer newvalue) {
        this.kadr_code = newvalue;
    }

    /**
     * @return gibt die Variable labe zurueck
     */
    public java.sql.Date getLabe() {
        return this.labe;
    }

    /**
     * setzt die Variable labe
     */
    public void setLabe(final java.sql.Date newvalue) {
        this.labe = newvalue;
    }

    /**
     * @return gibt die Variable laen zurueck
     */
    public java.sql.Date getLaen() {
        return this.laen;
    }

    /**
     * setzt die Variable laen
     */
    public void setLaen(final java.sql.Date newvalue) {
        this.laen = newvalue;
    }

    /**
     * @return gibt die Variable hdat_nr zurueck
     */
    public String getHdat_nr() {
        return this.hdat_nr;
    }

    /**
     * setzt die Variable hdat_nr
     */
    public void setHdat_nr(final String newvalue) {
        this.hdat_nr = newvalue;
    }

    /**
     * @return gibt die Variable kerst_ts zurueck
     */
    public java.sql.Timestamp getKerst_ts() {
        return this.kerst_ts;
    }

    /**
     * setzt die Variable kerst_ts
     */
    public void setKerst_ts(final java.sql.Timestamp newvalue) {
        this.kerst_ts = newvalue;
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
        buffer.append("PosnrFachDTO [");
        buffer.append("posnr_fach_id = ").append(this.posnr_fach_id).append(", ");
        buffer.append("posnr = ").append(this.posnr).append(", ");
        buffer.append("fachg = ").append(this.fachg).append(", ");
        buffer.append("kvpnr = ").append(this.kvpnr).append(", ");
        buffer.append("vp_fachg = ").append(this.vp_fachg).append(", ");
        buffer.append("vk_kbund = ").append(this.vk_kbund).append(", ");
        buffer.append("kadr_code = ").append(this.kadr_code).append(", ");
        buffer.append("labe = ").append(this.labe).append(", ");
        buffer.append("laen = ").append(this.laen).append(", ");
        buffer.append("hdat_nr = ").append(this.hdat_nr).append(", ");
        buffer.append("kerst_ts = ").append(this.kerst_ts).append(", ");
        buffer.append("aenderungstag = ").append(this.aenderungstag).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
