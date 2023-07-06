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
 * Represents the 'PosnrFoko' entity.
 * <p>
 * PosnrFoko
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_posnr_foko")
public class PosnrFoko implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public PosnrFoko() {
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
     * The 'bereich' property.
     * <p>
     * bereich
     */
    @Basic(optional = false)
    @Column(name = "bereich", nullable = false)
    private Integer bereich;

    /**
     * The 'abrkat' property.
     * <p>
     * abrkat
     */
    @Basic(optional = false)
    @Column(name = "abrkat", nullable = false)
    private Integer abrkat;

    /**
     * The 'live' property.
     * <p>
     * live
     */
    @Basic(optional = false)
    @Column(name = "live", nullable = false)
    private Integer live;

    /**
     * The 'position_foko_id' property.
     * <p>
     * position_foko_id
     */
    @Id
    @GeneratedValue(generator = "tarif_posnr_foko_position_foko_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_posnr_foko_position_foko_id_seq", schema = "kv", sequenceName = "tarif_posnr_foko_posnr_foko_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "position_foko_id", nullable = false, updatable = false)
    private Long position_foko_id;

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
     * Returns the 'bereich' property.
     *
     * @return The 'bereich' property.
     */
    @NotNull
    public Integer getBereich() {
        return this.bereich;
    }

    /**
     * Sets the 'bereich' property.
     *
     * @param bereich
     *            The 'bereich' property.
     */
    public void setBereich(final Integer bereich) {
        this.bereich = bereich;
    }

    /**
     * Returns the 'abrkat' property.
     *
     * @return The 'abrkat' property.
     */
    @NotNull
    public Integer getAbrkat() {
        return this.abrkat;
    }

    /**
     * Sets the 'abrkat' property.
     *
     * @param abrkat
     *            The 'abrkat' property.
     */
    public void setAbrkat(final Integer abrkat) {
        this.abrkat = abrkat;
    }

    /**
     * Returns the 'live' property.
     *
     * @return The 'live' property.
     */
    @NotNull
    public Integer getLive() {
        return this.live;
    }

    /**
     * Sets the 'live' property.
     *
     * @param live
     *            The 'live' property.
     */
    public void setLive(final Integer live) {
        this.live = live;
    }

    /**
     * Returns the 'position_foko_id' property.
     *
     * @return The 'position_foko_id' property.
     */
    public Long getPosition_foko_id() {
        return this.position_foko_id;
    }

    /**
     * Sets the 'position_foko_id' property.
     *
     * @param position_foko_id
     *            The 'position_foko_id' property.
     */
    public void setPosition_foko_id(final Long position_foko_id) {
        this.position_foko_id = position_foko_id;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PosnrFoko [");
        builder.append("posnr=").append(this.posnr).append(", ");
        builder.append("bereich=").append(this.bereich).append(", ");
        builder.append("abrkat=").append(this.abrkat).append(", ");
        builder.append("live=").append(this.live).append(", ");
        builder.append("position_foko_id=").append(this.position_foko_id).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
