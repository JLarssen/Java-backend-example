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

/**
 * Represents the 'PositionBundesland' entity.
 * <p>
 * PositionBundesland
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_position_bundesland")
public class PositionBundesland implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public PositionBundesland() {
        super();
    }

    /**
     * The 'position_bundesland_id' property.
     * <p>
     * position_bundesland_id
     */
    @Id
    @GeneratedValue(generator = "tarif_position_bundesland_position_bundesland_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_position_bundesland_position_bundesland_id_seq", schema = "kv", sequenceName = "tarif_position_bundesland_position_bundesland_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "position_bundesland_id", nullable = false, updatable = false)
    private Long position_bundesland_id;

    /**
     * The 'bundeslandCode' property.
     * <p>
     * bundeslandCode
     */
    @Basic(optional = false)
    @Column(name = "bundesland_code", nullable = false)
    private Integer bundeslandCode;

    /**
     * Returns the 'position_bundesland_id' property.
     *
     * @return The 'position_bundesland_id' property.
     */
    public Long getPosition_bundesland_id() {
        return this.position_bundesland_id;
    }

    /**
     * Sets the 'position_bundesland_id' property.
     *
     * @param position_bundesland_id
     *            The 'position_bundesland_id' property.
     */
    public void setPosition_bundesland_id(final Long position_bundesland_id) {
        this.position_bundesland_id = position_bundesland_id;
    }

    /**
     * Returns the 'bundeslandCode' property.
     *
     * @return The 'bundeslandCode' property.
     */
    @NotNull
    public Integer getBundeslandCode() {
        return this.bundeslandCode;
    }

    /**
     * Sets the 'bundeslandCode' property.
     *
     * @param bundeslandCode
     *            The 'bundeslandCode' property.
     */
    public void setBundeslandCode(final Integer bundeslandCode) {
        this.bundeslandCode = bundeslandCode;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PositionBundesland [");
        builder.append("position_bundesland_id=").append(this.position_bundesland_id).append(", ");
        builder.append("bundeslandCode=").append(this.bundeslandCode).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
