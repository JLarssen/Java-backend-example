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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents the 'FBinfAbschnitt' entity.
 * <p>
 * FBinfAbschnitt
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_f_binf_abschnitt")
public class FBinfAbschnitt implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public FBinfAbschnitt() {
        super();
    }

    /**
     * The 'abslfdnr' property.
     * <p>
     * abslfdnr
     */
    @Basic(optional = false)
    @Column(name = "abslfdnr", nullable = false)
    private Integer abslfdnr;

    /**
     * The 'abschnitt_pre' property.
     * <p>
     * abschnitt_pre
     */
    @Basic(optional = false)
    @Column(name = "abschnitt_pre", length = 254, nullable = false)
    private String abschnitt_pre;

    /**
     * The 'abschnitt_name' property.
     * <p>
     * abschnitt_name
     */
    @Basic(optional = false)
    @Column(name = "abschnitt_name", length = 254, nullable = false)
    private String abschnitt_name;

    /**
     * The 'position_abschnitt_id' property.
     * <p>
     * position_abschnitt_id
     */
    @Id
    @GeneratedValue(generator = "tarif_f_binf_abschnitt_position_abschnitt_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_f_binf_abschnitt_position_abschnitt_id_seq", schema = "kv", sequenceName = "tarif_f_binf_abschnitt_f_binf_abschnitt_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "position_abschnitt_id", nullable = false, updatable = false)
    private Long position_abschnitt_id;

    /**
     * The 'fbinfKapSet' property.
     * <p>
     * fbinfKapSet
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_abschnitt_id", referencedColumnName = "position_abschnitt_id", nullable = false)
    private java.util.Set<FBinfKap> fbinfKapSet;

    /**
     * Returns the 'abslfdnr' property.
     *
     * @return The 'abslfdnr' property.
     */
    @NotNull
    public Integer getAbslfdnr() {
        return this.abslfdnr;
    }

    /**
     * Sets the 'abslfdnr' property.
     *
     * @param abslfdnr
     *            The 'abslfdnr' property.
     */
    public void setAbslfdnr(final Integer abslfdnr) {
        this.abslfdnr = abslfdnr;
    }

    /**
     * Returns the 'abschnitt_pre' property.
     *
     * @return The 'abschnitt_pre' property.
     */
    @NotNull
    @Size(min = 1, max = 254)
    public String getAbschnitt_pre() {
        return this.abschnitt_pre;
    }

    /**
     * Sets the 'abschnitt_pre' property.
     *
     * @param abschnitt_pre
     *            The 'abschnitt_pre' property.
     */
    public void setAbschnitt_pre(final String abschnitt_pre) {
        this.abschnitt_pre = abschnitt_pre;
    }

    /**
     * Returns the 'abschnitt_name' property.
     *
     * @return The 'abschnitt_name' property.
     */
    @NotNull
    @Size(min = 1, max = 254)
    public String getAbschnitt_name() {
        return this.abschnitt_name;
    }

    /**
     * Sets the 'abschnitt_name' property.
     *
     * @param abschnitt_name
     *            The 'abschnitt_name' property.
     */
    public void setAbschnitt_name(final String abschnitt_name) {
        this.abschnitt_name = abschnitt_name;
    }

    /**
     * Returns the 'position_abschnitt_id' property.
     *
     * @return The 'position_abschnitt_id' property.
     */
    public Long getPosition_abschnitt_id() {
        return this.position_abschnitt_id;
    }

    /**
     * Sets the 'position_abschnitt_id' property.
     *
     * @param position_abschnitt_id
     *            The 'position_abschnitt_id' property.
     */
    public void setPosition_abschnitt_id(final Long position_abschnitt_id) {
        this.position_abschnitt_id = position_abschnitt_id;
    }

    /**
     * Returns the 'fbinfKapSet' property.
     *
     * @return The 'fbinfKapSet' property.
     */
    @Valid
    public java.util.Set<FBinfKap> getFbinfKapSet() {
        if (this.fbinfKapSet == null) {
            this.fbinfKapSet = new java.util.HashSet<>();
        }
        return this.fbinfKapSet;
    }

    /**
     * Sets the 'fbinfKapSet' property.
     *
     * @param fbinfKapSet
     *            The 'fbinfKapSet' property.
     */
    public void setFbinfKapSet(final java.util.Set<FBinfKap> fbinfKapSet) {
        this.fbinfKapSet = fbinfKapSet;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("FBinfAbschnitt [");
        builder.append("abslfdnr=").append(this.abslfdnr).append(", ");
        builder.append("abschnitt_pre=").append(this.abschnitt_pre).append(", ");
        builder.append("abschnitt_name=").append(this.abschnitt_name).append(", ");
        builder.append("position_abschnitt_id=").append(this.position_abschnitt_id).append(", ");
        builder.append("fbinfKapSet=").append(this.fbinfKapSet).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
