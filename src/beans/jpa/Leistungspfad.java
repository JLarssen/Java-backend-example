package beans.jpa;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;

/**
 * Represents the 'Leistungspfad' entity.
 * <p>
 * Leistungspfad
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_leistungspfad")
public class Leistungspfad implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public Leistungspfad() {
        super();
    }

    /**
     * The 'leistungspfad_id' property.
     * <p>
     * leistungspfad_id
     */
    @Id
    @GeneratedValue(generator = "tarif_leistungspfad_leistungspfad_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_leistungspfad_leistungspfad_id_seq", schema = "kv", sequenceName = "tarif_leistungspfad_leistungspfad_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "leistungspfad_id", nullable = false, updatable = false)
    private Long leistungspfad_id;

    /**
     * The 'leistungspfad_code' property.
     * <p>
     * leistungspfad_code
     */
    @Basic(optional = false)
    @Column(name = "leistungspfad_code", length = 2, nullable = false)
    private String leistungspfad_code;

    /**
     * The 'leistungspfad_txt' property.
     * <p>
     * leistungspfad_txt
     */
    @Basic(optional = false)
    @Column(name = "leistungspfad_txt", length = 254, nullable = false)
    private String leistungspfad_txt;

    /**
     * The 'leistungspfadParent' property.
     * <p>
     * leistungspfadParent
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "leistungspfad_parent_id", referencedColumnName = "leistungspfad_id")
    private Leistungspfad leistungspfadParent;

    /**
     * Returns the 'leistungspfad_id' property.
     *
     * @return The 'leistungspfad_id' property.
     */
    public Long getLeistungspfad_id() {
        return this.leistungspfad_id;
    }

    /**
     * Sets the 'leistungspfad_id' property.
     *
     * @param leistungspfad_id
     *            The 'leistungspfad_id' property.
     */
    public void setLeistungspfad_id(final Long leistungspfad_id) {
        this.leistungspfad_id = leistungspfad_id;
    }

    /**
     * Returns the 'leistungspfad_code' property.
     *
     * @return The 'leistungspfad_code' property.
     */
    @NotNull
    @Size(min = 1, max = 2)
    public String getLeistungspfad_code() {
        return this.leistungspfad_code;
    }

    /**
     * Sets the 'leistungspfad_code' property.
     *
     * @param leistungspfad_code
     *            The 'leistungspfad_code' property.
     */
    public void setLeistungspfad_code(final String leistungspfad_code) {
        this.leistungspfad_code = leistungspfad_code;
    }

    /**
     * Returns the 'leistungspfad_txt' property.
     *
     * @return The 'leistungspfad_txt' property.
     */
    @NotNull
    @Size(min = 1, max = 254)
    public String getLeistungspfad_txt() {
        return this.leistungspfad_txt;
    }

    /**
     * Sets the 'leistungspfad_txt' property.
     *
     * @param leistungspfad_txt
     *            The 'leistungspfad_txt' property.
     */
    public void setLeistungspfad_txt(final String leistungspfad_txt) {
        this.leistungspfad_txt = leistungspfad_txt;
    }

    /**
     * Returns the 'leistungspfadParent' property.
     *
     * @return The 'leistungspfadParent' property.
     */
    @Valid
    public Leistungspfad getLeistungspfadParent() {
        return this.leistungspfadParent;
    }

    /**
     * Sets the 'leistungspfadParent' property.
     *
     * @param leistungspfadParent
     *            The 'leistungspfadParent' property.
     */
    public void setLeistungspfadParent(final Leistungspfad leistungspfadParent) {
        this.leistungspfadParent = leistungspfadParent;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Leistungspfad [");
        builder.append("leistungspfad_id=").append(this.leistungspfad_id).append(", ");
        builder.append("leistungspfad_code=").append(this.leistungspfad_code).append(", ");
        builder.append("leistungspfad_txt=").append(this.leistungspfad_txt).append(", ");
        builder.append("leistungspfadParent=").append(this.leistungspfadParent).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
