package beans.jpa;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Represents the 'PosVerordnungsreferenz' entity.
 * <p>
 * PosVerordnungsreferenz
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_pos_verordnungsreferenz")
public class PosVerordnungsreferenz implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public PosVerordnungsreferenz() {
        super();
    }

    /**
     * The 'pos_verordnungsreferenz_id' property.
     * <p>
     * pos_verordnungsreferenz_id
     */
    @Id
    @GeneratedValue(generator = "tarif_pos_verordnungsreferenz_pos_verordnungsreferenz_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_pos_verordnungsreferenz_pos_verordnungsreferenz_id_seq", schema = "kv", sequenceName = "tarif_pos_verordnungsreferenz_pos_verordnungsreferenz_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "pos_verordnungsreferenz_id", nullable = false, updatable = false)
    private Long pos_verordnungsreferenz_id;

    /**
     * The 'verordnungsreferenz' property.
     * <p>
     * verordnungsreferenz
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "verordnungsreferenz_id", referencedColumnName = "verordnungsreferenz_id", nullable = false)
    private Verordnungsreferenz verordnungsreferenz;

    /**
     * Returns the 'pos_verordnungsreferenz_id' property.
     *
     * @return The 'pos_verordnungsreferenz_id' property.
     */
    public Long getPos_verordnungsreferenz_id() {
        return this.pos_verordnungsreferenz_id;
    }

    /**
     * Sets the 'pos_verordnungsreferenz_id' property.
     *
     * @param pos_verordnungsreferenz_id
     *            The 'pos_verordnungsreferenz_id' property.
     */
    public void setPos_verordnungsreferenz_id(final Long pos_verordnungsreferenz_id) {
        this.pos_verordnungsreferenz_id = pos_verordnungsreferenz_id;
    }

    /**
     * Returns the 'verordnungsreferenz' property.
     *
     * @return The 'verordnungsreferenz' property.
     */
    @Valid
    @NotNull
    public Verordnungsreferenz getVerordnungsreferenz() {
        return this.verordnungsreferenz;
    }

    /**
     * Sets the 'verordnungsreferenz' property.
     *
     * @param verordnungsreferenz
     *            The 'verordnungsreferenz' property.
     */
    public void setVerordnungsreferenz(final Verordnungsreferenz verordnungsreferenz) {
        this.verordnungsreferenz = verordnungsreferenz;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PosVerordnungsreferenz [");
        builder.append("pos_verordnungsreferenz_id=").append(this.pos_verordnungsreferenz_id).append(", ");
        builder.append("verordnungsreferenz=").append(this.verordnungsreferenz).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
