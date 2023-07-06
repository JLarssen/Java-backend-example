package beans.jpa;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents the 'PosnrFach' entity.
 * <p>
 * PosnrFach
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_posnr_fach")
public class PosnrFach implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public PosnrFach() {
        super();
    }

    /**
     * The 'posnr_fach_id' property.
     * <p>
     * posnr_fach_id
     */
    @Id
    @GeneratedValue(generator = "tarif_posnr_fach_posnr_fach_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_posnr_fach_posnr_fach_id_seq", schema = "kv", sequenceName = "tarif_posnr_fach_posnr_fach_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "posnr_fach_id", nullable = false, updatable = false)
    private Long posnr_fach_id;

    /**
     * The 'posnr' property.
     * <p>
     * posnr
     */
    @Basic(optional = false)
    @Column(name = "posnr", length = 7, nullable = false)
    private String posnr;

    /**
     * The 'fachg' property.
     * <p>
     * fachg
     */
    @Basic
    @Column(name = "fachg")
    private Integer fachg;

    /**
     * The 'kvpnr' property.
     * <p>
     * kvpnr
     */
    @Basic
    @Column(name = "kvpnr", length = 6)
    private String kvpnr;

    /**
     * The 'vp_fachg' property.
     * <p>
     * vp_fachg
     */
    @Basic
    @Column(name = "vp_fachg")
    private Integer vp_fachg;

    /**
     * The 'vk_kbund' property.
     * <p>
     * vk_kbund
     */
    @Basic(optional = false)
    @Column(name = "vk_kbund", length = 1, nullable = false)
    private String vk_kbund;

    /**
     * The 'kadr_code' property.
     * <p>
     * kadr_code
     */
    @Basic
    @Column(name = "kadr_code")
    private Integer kadr_code;

    /**
     * The 'labe' property.
     * <p>
     * labe
     */
    @Basic(optional = false)
    @Column(name = "labe", nullable = false)
    private java.sql.Date labe;

    /**
     * The 'laen' property.
     * <p>
     * laen
     */
    @Basic
    @Column(name = "laen")
    private java.sql.Date laen;

    /**
     * The 'hdat_nr' property.
     * <p>
     * hdat_nr
     */
    @Basic(optional = false)
    @Column(name = "hdat_nr", length = 7, nullable = false)
    private String hdat_nr;

    /**
     * The 'kerst_ts' property.
     * <p>
     * kerst_ts
     */
    @Basic(optional = false)
    @Column(name = "kerst_ts", nullable = false)
    private java.sql.Timestamp kerst_ts;

    /**
     * The 'aenderungstag' property.
     * <p>
     * aenderungstag
     */
    @Basic
    @Column(name = "aenderungstag")
    private java.sql.Date aenderungstag;

    /**
     * Returns the 'posnr_fach_id' property.
     *
     * @return The 'posnr_fach_id' property.
     */
    public Long getPosnr_fach_id() {
        return this.posnr_fach_id;
    }

    /**
     * Sets the 'posnr_fach_id' property.
     *
     * @param posnr_fach_id
     *            The 'posnr_fach_id' property.
     */
    public void setPosnr_fach_id(final Long posnr_fach_id) {
        this.posnr_fach_id = posnr_fach_id;
    }

    /**
     * Returns the 'posnr' property.
     *
     * @return The 'posnr' property.
     */
    @NotNull
    @Size(min = 1, max = 7)
    public String getPosnr() {
        return this.posnr;
    }

    /**
     * Sets the 'posnr' property.
     *
     * @param posnr
     *            The 'posnr' property.
     */
    public void setPosnr(final String posnr) {
        this.posnr = posnr;
    }

    /**
     * Returns the 'fachg' property.
     *
     * @return The 'fachg' property.
     */
    public Integer getFachg() {
        return this.fachg;
    }

    /**
     * Sets the 'fachg' property.
     *
     * @param fachg
     *            The 'fachg' property.
     */
    public void setFachg(final Integer fachg) {
        this.fachg = fachg;
    }

    /**
     * Returns the 'kvpnr' property.
     *
     * @return The 'kvpnr' property.
     */
    @Size(min = 1, max = 6)
    public String getKvpnr() {
        return this.kvpnr;
    }

    /**
     * Sets the 'kvpnr' property.
     *
     * @param kvpnr
     *            The 'kvpnr' property.
     */
    public void setKvpnr(final String kvpnr) {
        this.kvpnr = kvpnr;
    }

    /**
     * Returns the 'vp_fachg' property.
     *
     * @return The 'vp_fachg' property.
     */
    public Integer getVp_fachg() {
        return this.vp_fachg;
    }

    /**
     * Sets the 'vp_fachg' property.
     *
     * @param vp_fachg
     *            The 'vp_fachg' property.
     */
    public void setVp_fachg(final Integer vp_fachg) {
        this.vp_fachg = vp_fachg;
    }

    /**
     * Returns the 'vk_kbund' property.
     *
     * @return The 'vk_kbund' property.
     */
    @NotNull
    @Size(min = 1, max = 1)
    public String getVk_kbund() {
        return this.vk_kbund;
    }

    /**
     * Sets the 'vk_kbund' property.
     *
     * @param vk_kbund
     *            The 'vk_kbund' property.
     */
    public void setVk_kbund(final String vk_kbund) {
        this.vk_kbund = vk_kbund;
    }

    /**
     * Returns the 'kadr_code' property.
     *
     * @return The 'kadr_code' property.
     */
    public Integer getKadr_code() {
        return this.kadr_code;
    }

    /**
     * Sets the 'kadr_code' property.
     *
     * @param kadr_code
     *            The 'kadr_code' property.
     */
    public void setKadr_code(final Integer kadr_code) {
        this.kadr_code = kadr_code;
    }

    /**
     * Returns the 'labe' property.
     *
     * @return The 'labe' property.
     */
    @NotNull
    public java.sql.Date getLabe() {
        return this.labe;
    }

    /**
     * Sets the 'labe' property.
     *
     * @param labe
     *            The 'labe' property.
     */
    public void setLabe(final java.sql.Date labe) {
        this.labe = labe;
    }

    /**
     * Returns the 'laen' property.
     *
     * @return The 'laen' property.
     */
    public java.sql.Date getLaen() {
        return this.laen;
    }

    /**
     * Sets the 'laen' property.
     *
     * @param laen
     *            The 'laen' property.
     */
    public void setLaen(final java.sql.Date laen) {
        this.laen = laen;
    }

    /**
     * Returns the 'hdat_nr' property.
     *
     * @return The 'hdat_nr' property.
     */
    @NotNull
    @Size(min = 1, max = 7)
    public String getHdat_nr() {
        return this.hdat_nr;
    }

    /**
     * Sets the 'hdat_nr' property.
     *
     * @param hdat_nr
     *            The 'hdat_nr' property.
     */
    public void setHdat_nr(final String hdat_nr) {
        this.hdat_nr = hdat_nr;
    }

    /**
     * Returns the 'kerst_ts' property.
     *
     * @return The 'kerst_ts' property.
     */
    @NotNull
    public java.sql.Timestamp getKerst_ts() {
        return this.kerst_ts;
    }

    /**
     * Sets the 'kerst_ts' property.
     *
     * @param kerst_ts
     *            The 'kerst_ts' property.
     */
    public void setKerst_ts(final java.sql.Timestamp kerst_ts) {
        this.kerst_ts = kerst_ts;
    }

    /**
     * Returns the 'aenderungstag' property.
     *
     * @return The 'aenderungstag' property.
     */
    public java.sql.Date getAenderungstag() {
        return this.aenderungstag;
    }

    /**
     * Sets the 'aenderungstag' property.
     *
     * @param aenderungstag
     *            The 'aenderungstag' property.
     */
    public void setAenderungstag(final java.sql.Date aenderungstag) {
        this.aenderungstag = aenderungstag;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PosnrFach [");
        builder.append("posnr_fach_id=").append(this.posnr_fach_id).append(", ");
        builder.append("posnr=").append(this.posnr).append(", ");
        builder.append("fachg=").append(this.fachg).append(", ");
        builder.append("kvpnr=").append(this.kvpnr).append(", ");
        builder.append("vp_fachg=").append(this.vp_fachg).append(", ");
        builder.append("vk_kbund=").append(this.vk_kbund).append(", ");
        builder.append("kadr_code=").append(this.kadr_code).append(", ");
        builder.append("labe=").append(this.labe).append(", ");
        builder.append("laen=").append(this.laen).append(", ");
        builder.append("hdat_nr=").append(this.hdat_nr).append(", ");
        builder.append("kerst_ts=").append(this.kerst_ts).append(", ");
        builder.append("aenderungstag=").append(this.aenderungstag).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
