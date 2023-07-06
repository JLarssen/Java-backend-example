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
 * Represents the 'PosnrSbtext' entity.
 * <p>
 * PosnrSbtext
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_posnr_sbtext")
public class PosnrSbtext implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public PosnrSbtext() {
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
     * The 'kzbea_id' property.
     * <p>
     * kzbea_id
     */
    @Basic(optional = false)
    @Column(name = "kzbea_id", length = 10, nullable = false)
    private String kzbea_id;

    /**
     * The 'kzbea_ts' property.
     * <p>
     * kzbea_ts
     */
    @Basic(optional = false)
    @Column(name = "kzbea_ts", nullable = false)
    private java.sql.Timestamp kzbea_ts;

    /**
     * The 'posnr_sbtext' property.
     * <p>
     * posnr_sbtext
     */
    @Basic(optional = false)
    @Column(name = "posnr_sbtext", length = 2000, nullable = false)
    private String posnr_sbtext;

    /**
     * The 'position_sbtext_id' property.
     * <p>
     * position_sbtext_id
     */
    @Id
    @GeneratedValue(generator = "tarif_posnr_sbtext_position_sbtext_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_posnr_sbtext_position_sbtext_id_seq", schema = "kv", sequenceName = "tarif_posnr_sbtext_posnr_sbtext_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "position_sbtext_id", nullable = false, updatable = false)
    private Long position_sbtext_id;

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
     * Returns the 'kzbea_id' property.
     *
     * @return The 'kzbea_id' property.
     */
    @NotNull
    @Size(min = 1, max = 10)
    public String getKzbea_id() {
        return this.kzbea_id;
    }

    /**
     * Sets the 'kzbea_id' property.
     *
     * @param kzbea_id
     *            The 'kzbea_id' property.
     */
    public void setKzbea_id(final String kzbea_id) {
        this.kzbea_id = kzbea_id;
    }

    /**
     * Returns the 'kzbea_ts' property.
     *
     * @return The 'kzbea_ts' property.
     */
    @NotNull
    public java.sql.Timestamp getKzbea_ts() {
        return this.kzbea_ts;
    }

    /**
     * Sets the 'kzbea_ts' property.
     *
     * @param kzbea_ts
     *            The 'kzbea_ts' property.
     */
    public void setKzbea_ts(final java.sql.Timestamp kzbea_ts) {
        this.kzbea_ts = kzbea_ts;
    }

    /**
     * Returns the 'posnr_sbtext' property.
     *
     * @return The 'posnr_sbtext' property.
     */
    @NotNull
    @Size(min = 1, max = 2000)
    public String getPosnr_sbtext() {
        return this.posnr_sbtext;
    }

    /**
     * Sets the 'posnr_sbtext' property.
     *
     * @param posnr_sbtext
     *            The 'posnr_sbtext' property.
     */
    public void setPosnr_sbtext(final String posnr_sbtext) {
        this.posnr_sbtext = posnr_sbtext;
    }

    /**
     * Returns the 'position_sbtext_id' property.
     *
     * @return The 'position_sbtext_id' property.
     */
    public Long getPosition_sbtext_id() {
        return this.position_sbtext_id;
    }

    /**
     * Sets the 'position_sbtext_id' property.
     *
     * @param position_sbtext_id
     *            The 'position_sbtext_id' property.
     */
    public void setPosition_sbtext_id(final Long position_sbtext_id) {
        this.position_sbtext_id = position_sbtext_id;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PosnrSbtext [");
        builder.append("posnr=").append(this.posnr).append(", ");
        builder.append("kzbea_id=").append(this.kzbea_id).append(", ");
        builder.append("kzbea_ts=").append(this.kzbea_ts).append(", ");
        builder.append("posnr_sbtext=").append(this.posnr_sbtext).append(", ");
        builder.append("position_sbtext_id=").append(this.position_sbtext_id).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
