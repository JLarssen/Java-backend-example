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
 * Represents the 'FPosnrJstat' entity.
 * <p>
 * FPosnrJstat
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_f_posnr_jstat")
public class FPosnrJstat implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public FPosnrJstat() {
        super();
    }

    /**
     * The 'posnr' property.
     * <p>
     * posnr
     */
    @Basic(optional = false)
    @Column(name = "posnr", length = 7, nullable = false)
    private String posnr;

    /**
     * The 'posnr_uegr' property.
     * <p>
     * posnr_uegr
     */
    @Basic(optional = false)
    @Column(name = "posnr_uegr", length = 2, nullable = false)
    private String posnr_uegr;

    /**
     * The 'posnr_hgr' property.
     * <p>
     * posnr_hgr
     */
    @Basic(optional = false)
    @Column(name = "posnr_hgr", length = 2, nullable = false)
    private String posnr_hgr;

    /**
     * The 'posnr_ugr' property.
     * <p>
     * posnr_ugr
     */
    @Basic(optional = false)
    @Column(name = "posnr_ugr", length = 2, nullable = false)
    private String posnr_ugr;

    /**
     * The 'stat_leistart' property.
     * <p>
     * stat_leistart
     */
    @Basic
    @Column(name = "stat_leistart")
    private Integer stat_leistart;

    /**
     * The 'stat_form' property.
     * <p>
     * stat_form
     */
    @Basic(optional = false)
    @Column(name = "stat_form", length = 254, nullable = false)
    private String stat_form;

    /**
     * The 'position_jstat_id' property.
     * <p>
     * position_jstat_id
     */
    @Id
    @GeneratedValue(generator = "tarif_f_posnr_jstat_position_jstat_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_f_posnr_jstat_position_jstat_id_seq", schema = "kv", sequenceName = "tarif_f_posnr_jstat_f_posnr_jstat_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "position_jstat_id", nullable = false, updatable = false)
    private Long position_jstat_id;

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
     * Returns the 'posnr_uegr' property.
     *
     * @return The 'posnr_uegr' property.
     */
    @NotNull
    @Size(min = 1, max = 2)
    public String getPosnr_uegr() {
        return this.posnr_uegr;
    }

    /**
     * Sets the 'posnr_uegr' property.
     *
     * @param posnr_uegr
     *            The 'posnr_uegr' property.
     */
    public void setPosnr_uegr(final String posnr_uegr) {
        this.posnr_uegr = posnr_uegr;
    }

    /**
     * Returns the 'posnr_hgr' property.
     *
     * @return The 'posnr_hgr' property.
     */
    @NotNull
    @Size(min = 1, max = 2)
    public String getPosnr_hgr() {
        return this.posnr_hgr;
    }

    /**
     * Sets the 'posnr_hgr' property.
     *
     * @param posnr_hgr
     *            The 'posnr_hgr' property.
     */
    public void setPosnr_hgr(final String posnr_hgr) {
        this.posnr_hgr = posnr_hgr;
    }

    /**
     * Returns the 'posnr_ugr' property.
     *
     * @return The 'posnr_ugr' property.
     */
    @NotNull
    @Size(min = 1, max = 2)
    public String getPosnr_ugr() {
        return this.posnr_ugr;
    }

    /**
     * Sets the 'posnr_ugr' property.
     *
     * @param posnr_ugr
     *            The 'posnr_ugr' property.
     */
    public void setPosnr_ugr(final String posnr_ugr) {
        this.posnr_ugr = posnr_ugr;
    }

    /**
     * Returns the 'stat_leistart' property.
     *
     * @return The 'stat_leistart' property.
     */
    public Integer getStat_leistart() {
        return this.stat_leistart;
    }

    /**
     * Sets the 'stat_leistart' property.
     *
     * @param stat_leistart
     *            The 'stat_leistart' property.
     */
    public void setStat_leistart(final Integer stat_leistart) {
        this.stat_leistart = stat_leistart;
    }

    /**
     * Returns the 'stat_form' property.
     *
     * @return The 'stat_form' property.
     */
    @NotNull
    @Size(min = 1, max = 254)
    public String getStat_form() {
        return this.stat_form;
    }

    /**
     * Sets the 'stat_form' property.
     *
     * @param stat_form
     *            The 'stat_form' property.
     */
    public void setStat_form(final String stat_form) {
        this.stat_form = stat_form;
    }

    /**
     * Returns the 'position_jstat_id' property.
     *
     * @return The 'position_jstat_id' property.
     */
    public Long getPosition_jstat_id() {
        return this.position_jstat_id;
    }

    /**
     * Sets the 'position_jstat_id' property.
     *
     * @param position_jstat_id
     *            The 'position_jstat_id' property.
     */
    public void setPosition_jstat_id(final Long position_jstat_id) {
        this.position_jstat_id = position_jstat_id;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("FPosnrJstat [");
        builder.append("posnr=").append(this.posnr).append(", ");
        builder.append("posnr_uegr=").append(this.posnr_uegr).append(", ");
        builder.append("posnr_hgr=").append(this.posnr_hgr).append(", ");
        builder.append("posnr_ugr=").append(this.posnr_ugr).append(", ");
        builder.append("stat_leistart=").append(this.stat_leistart).append(", ");
        builder.append("stat_form=").append(this.stat_form).append(", ");
        builder.append("position_jstat_id=").append(this.position_jstat_id).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
