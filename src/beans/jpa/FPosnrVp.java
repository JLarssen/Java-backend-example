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
 * Represents the 'FPosnrVp' entity.
 * <p>
 * FPosnrVp
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_f_posnr_vp")
public class FPosnrVp implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public FPosnrVp() {
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
     * The 'kvpnr' property.
     * <p>
     * kvpnr
     */
    @Basic(optional = false)
    @Column(name = "kvpnr", length = 6, nullable = false)
    private String kvpnr;

    /**
     * The 'position_vp_id' property.
     * <p>
     * position_vp_id
     */
    @Id
    @GeneratedValue(generator = "tarif_f_posnr_vp_position_vp_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_f_posnr_vp_position_vp_id_seq", schema = "kv", sequenceName = "tarif_f_posnr_vp_f_posnr_vp_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "position_vp_id", nullable = false, updatable = false)
    private Long position_vp_id;

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
     * Returns the 'kvpnr' property.
     *
     * @return The 'kvpnr' property.
     */
    @NotNull
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
     * Returns the 'position_vp_id' property.
     *
     * @return The 'position_vp_id' property.
     */
    public Long getPosition_vp_id() {
        return this.position_vp_id;
    }

    /**
     * Sets the 'position_vp_id' property.
     *
     * @param position_vp_id
     *            The 'position_vp_id' property.
     */
    public void setPosition_vp_id(final Long position_vp_id) {
        this.position_vp_id = position_vp_id;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("FPosnrVp [");
        builder.append("posnr=").append(this.posnr).append(", ");
        builder.append("kvpnr=").append(this.kvpnr).append(", ");
        builder.append("position_vp_id=").append(this.position_vp_id).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
