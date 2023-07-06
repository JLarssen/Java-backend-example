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
 * Represents the 'PosnrWoke' entity.
 * <p>
 * PosnrWoke
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_posnr_woke")
public class PosnrWoke implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public PosnrWoke() {
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
     * The 'rechenbox' property.
     * <p>
     * rechenbox
     */
    @Basic
    @Column(name = "rechenbox", length = 10)
    private String rechenbox;

    /**
     * The 'opgruppe' property.
     * <p>
     * opgruppe
     */
    @Basic
    @Column(name = "opgruppe")
    private Integer opgruppe;

    /**
     * The 'position_woke_id' property.
     * <p>
     * position_woke_id
     */
    @Id
    @GeneratedValue(generator = "tarif_posnr_woke_position_woke_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_posnr_woke_position_woke_id_seq", schema = "kv", sequenceName = "tarif_posnr_woke_posnr_woke_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "position_woke_id", nullable = false, updatable = false)
    private Long position_woke_id;

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
     * Returns the 'rechenbox' property.
     *
     * @return The 'rechenbox' property.
     */
    @Size(min = 1, max = 10)
    public String getRechenbox() {
        return this.rechenbox;
    }

    /**
     * Sets the 'rechenbox' property.
     *
     * @param rechenbox
     *            The 'rechenbox' property.
     */
    public void setRechenbox(final String rechenbox) {
        this.rechenbox = rechenbox;
    }

    /**
     * Returns the 'opgruppe' property.
     *
     * @return The 'opgruppe' property.
     */
    public Integer getOpgruppe() {
        return this.opgruppe;
    }

    /**
     * Sets the 'opgruppe' property.
     *
     * @param opgruppe
     *            The 'opgruppe' property.
     */
    public void setOpgruppe(final Integer opgruppe) {
        this.opgruppe = opgruppe;
    }

    /**
     * Returns the 'position_woke_id' property.
     *
     * @return The 'position_woke_id' property.
     */
    public Long getPosition_woke_id() {
        return this.position_woke_id;
    }

    /**
     * Sets the 'position_woke_id' property.
     *
     * @param position_woke_id
     *            The 'position_woke_id' property.
     */
    public void setPosition_woke_id(final Long position_woke_id) {
        this.position_woke_id = position_woke_id;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PosnrWoke [");
        builder.append("posnr=").append(this.posnr).append(", ");
        builder.append("rechenbox=").append(this.rechenbox).append(", ");
        builder.append("opgruppe=").append(this.opgruppe).append(", ");
        builder.append("position_woke_id=").append(this.position_woke_id).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
