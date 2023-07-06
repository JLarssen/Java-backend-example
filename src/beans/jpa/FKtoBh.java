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
 * Represents the 'FKtoBh' entity.
 * <p>
 * FKtoBh
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_f_kto_bh")
public class FKtoBh implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public FKtoBh() {
        super();
    }

    /**
     * The 'lsp_wert' property.
     * <p>
     * lsp_wert
     */
    @Basic(optional = false)
    @Column(name = "lsp_wert", nullable = false)
    private Integer lsp_wert;

    /**
     * The 'lsp_zuord' property.
     * <p>
     * lsp_zuord
     */
    @Basic(optional = false)
    @Column(name = "lsp_zuord", nullable = false)
    private Integer lsp_zuord;

    /**
     * The 'glt_ber' property.
     * <p>
     * glt_ber
     */
    @Basic(optional = false)
    @Column(name = "glt_ber", length = 20, nullable = false)
    private String glt_ber;

    /**
     * The 'kto_kr' property.
     * <p>
     * kto_kr
     */
    @Basic(optional = false)
    @Column(name = "kto_kr", length = 1, nullable = false)
    private String kto_kr;

    /**
     * The 'kto_nr' property.
     * <p>
     * kto_nr
     */
    @Basic(optional = false)
    @Column(name = "kto_nr", length = 4, nullable = false)
    private String kto_nr;

    /**
     * The 'kto_hvang' property.
     * <p>
     * kto_hvang
     */
    @Basic(optional = false)
    @Column(name = "kto_hvang", length = 4, nullable = false)
    private String kto_hvang;

    /**
     * The 'kto_dst' property.
     * <p>
     * kto_dst
     */
    @Basic(optional = false)
    @Column(name = "kto_dst", length = 18, nullable = false)
    private String kto_dst;

    /**
     * The 'kto_glsl' property.
     * <p>
     * kto_glsl
     */
    @Basic(optional = false)
    @Column(name = "kto_glsl", length = 4, nullable = false)
    private String kto_glsl;

    /**
     * The 'kto_bkz' property.
     * <p>
     * kto_bkz
     */
    @Basic(optional = false)
    @Column(name = "kto_bkz", length = 8, nullable = false)
    private String kto_bkz;

    /**
     * The 'fkto_bh_id' property.
     * <p>
     * fkto_bh_id
     */
    @Id
    @GeneratedValue(generator = "tarif_f_kto_bh_fkto_bh_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_f_kto_bh_fkto_bh_id_seq", schema = "kv", sequenceName = "tarif_f_kto_bh_f_kto_bh_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "fkto_bh_id", nullable = false, updatable = false)
    private Long fkto_bh_id;

    /**
     * The 'position_leistungssparte_id' property.
     * <p>
     * position_leistungssparte_id
     */
    @Basic(optional = false)
    @Column(name = "position_leistungssparte_id", nullable = false)
    private Long position_leistungssparte_id;

    /**
     * Returns the 'lsp_wert' property.
     *
     * @return The 'lsp_wert' property.
     */
    @NotNull
    public Integer getLsp_wert() {
        return this.lsp_wert;
    }

    /**
     * Sets the 'lsp_wert' property.
     *
     * @param lsp_wert
     *            The 'lsp_wert' property.
     */
    public void setLsp_wert(final Integer lsp_wert) {
        this.lsp_wert = lsp_wert;
    }

    /**
     * Returns the 'lsp_zuord' property.
     *
     * @return The 'lsp_zuord' property.
     */
    @NotNull
    public Integer getLsp_zuord() {
        return this.lsp_zuord;
    }

    /**
     * Sets the 'lsp_zuord' property.
     *
     * @param lsp_zuord
     *            The 'lsp_zuord' property.
     */
    public void setLsp_zuord(final Integer lsp_zuord) {
        this.lsp_zuord = lsp_zuord;
    }

    /**
     * Returns the 'glt_ber' property.
     *
     * @return The 'glt_ber' property.
     */
    @NotNull
    @Size(min = 1, max = 20)
    public String getGlt_ber() {
        return this.glt_ber;
    }

    /**
     * Sets the 'glt_ber' property.
     *
     * @param glt_ber
     *            The 'glt_ber' property.
     */
    public void setGlt_ber(final String glt_ber) {
        this.glt_ber = glt_ber;
    }

    /**
     * Returns the 'kto_kr' property.
     *
     * @return The 'kto_kr' property.
     */
    @NotNull
    @Size(min = 1, max = 1)
    public String getKto_kr() {
        return this.kto_kr;
    }

    /**
     * Sets the 'kto_kr' property.
     *
     * @param kto_kr
     *            The 'kto_kr' property.
     */
    public void setKto_kr(final String kto_kr) {
        this.kto_kr = kto_kr;
    }

    /**
     * Returns the 'kto_nr' property.
     *
     * @return The 'kto_nr' property.
     */
    @NotNull
    @Size(min = 1, max = 4)
    public String getKto_nr() {
        return this.kto_nr;
    }

    /**
     * Sets the 'kto_nr' property.
     *
     * @param kto_nr
     *            The 'kto_nr' property.
     */
    public void setKto_nr(final String kto_nr) {
        this.kto_nr = kto_nr;
    }

    /**
     * Returns the 'kto_hvang' property.
     *
     * @return The 'kto_hvang' property.
     */
    @NotNull
    @Size(min = 1, max = 4)
    public String getKto_hvang() {
        return this.kto_hvang;
    }

    /**
     * Sets the 'kto_hvang' property.
     *
     * @param kto_hvang
     *            The 'kto_hvang' property.
     */
    public void setKto_hvang(final String kto_hvang) {
        this.kto_hvang = kto_hvang;
    }

    /**
     * Returns the 'kto_dst' property.
     *
     * @return The 'kto_dst' property.
     */
    @NotNull
    @Size(min = 1, max = 18)
    public String getKto_dst() {
        return this.kto_dst;
    }

    /**
     * Sets the 'kto_dst' property.
     *
     * @param kto_dst
     *            The 'kto_dst' property.
     */
    public void setKto_dst(final String kto_dst) {
        this.kto_dst = kto_dst;
    }

    /**
     * Returns the 'kto_glsl' property.
     *
     * @return The 'kto_glsl' property.
     */
    @NotNull
    @Size(min = 1, max = 4)
    public String getKto_glsl() {
        return this.kto_glsl;
    }

    /**
     * Sets the 'kto_glsl' property.
     *
     * @param kto_glsl
     *            The 'kto_glsl' property.
     */
    public void setKto_glsl(final String kto_glsl) {
        this.kto_glsl = kto_glsl;
    }

    /**
     * Returns the 'kto_bkz' property.
     *
     * @return The 'kto_bkz' property.
     */
    @NotNull
    @Size(min = 1, max = 8)
    public String getKto_bkz() {
        return this.kto_bkz;
    }

    /**
     * Sets the 'kto_bkz' property.
     *
     * @param kto_bkz
     *            The 'kto_bkz' property.
     */
    public void setKto_bkz(final String kto_bkz) {
        this.kto_bkz = kto_bkz;
    }

    /**
     * Returns the 'fkto_bh_id' property.
     *
     * @return The 'fkto_bh_id' property.
     */
    public Long getFkto_bh_id() {
        return this.fkto_bh_id;
    }

    /**
     * Sets the 'fkto_bh_id' property.
     *
     * @param fkto_bh_id
     *            The 'fkto_bh_id' property.
     */
    public void setFkto_bh_id(final Long fkto_bh_id) {
        this.fkto_bh_id = fkto_bh_id;
    }

    /**
     * Returns the 'position_leistungssparte_id' property.
     *
     * @return The 'position_leistungssparte_id' property.
     */
    @NotNull
    public Long getPosition_leistungssparte_id() {
        return this.position_leistungssparte_id;
    }

    /**
     * Sets the 'position_leistungssparte_id' property.
     *
     * @param position_leistungssparte_id
     *            The 'position_leistungssparte_id' property.
     */
    public void setPosition_leistungssparte_id(final Long position_leistungssparte_id) {
        this.position_leistungssparte_id = position_leistungssparte_id;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("FKtoBh [");
        builder.append("lsp_wert=").append(this.lsp_wert).append(", ");
        builder.append("lsp_zuord=").append(this.lsp_zuord).append(", ");
        builder.append("glt_ber=").append(this.glt_ber).append(", ");
        builder.append("kto_kr=").append(this.kto_kr).append(", ");
        builder.append("kto_nr=").append(this.kto_nr).append(", ");
        builder.append("kto_hvang=").append(this.kto_hvang).append(", ");
        builder.append("kto_dst=").append(this.kto_dst).append(", ");
        builder.append("kto_glsl=").append(this.kto_glsl).append(", ");
        builder.append("kto_bkz=").append(this.kto_bkz).append(", ");
        builder.append("fkto_bh_id=").append(this.fkto_bh_id).append(", ");
        builder.append("position_leistungssparte_id=").append(this.position_leistungssparte_id).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
