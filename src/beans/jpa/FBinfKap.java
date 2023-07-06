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
 * Represents the 'FBinfKap' entity.
 * <p>
 * FBinfKap
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_f_binf_kap")
public class FBinfKap implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public FBinfKap() {
        super();
    }

    /**
     * The 'kaplfdnr' property.
     * <p>
     * kaplfdnr
     */
    @Basic(optional = false)
    @Column(name = "kaplfdnr", nullable = false)
    private Integer kaplfdnr;

    /**
     * The 'abslfdnr' property.
     * <p>
     * abslfdnr
     */
    @Basic(optional = false)
    @Column(name = "abslfdnr", nullable = false)
    private Integer abslfdnr;

    /**
     * The 'kapitel_pre' property.
     * <p>
     * kapitel_pre
     */
    @Basic(optional = false)
    @Column(name = "kapitel_pre", length = 254, nullable = false)
    private String kapitel_pre;

    /**
     * The 'kapitel_name' property.
     * <p>
     * kapitel_name
     */
    @Basic(optional = false)
    @Column(name = "kapitel_name", length = 500, nullable = false)
    private String kapitel_name;

    /**
     * The 'kapitel_pre_arab' property.
     * <p>
     * kapitel_pre_arab
     */
    @Basic
    @Column(name = "kapitel_pre_arab")
    private Integer kapitel_pre_arab;

    /**
     * The 'position_kapitel_id' property.
     * <p>
     * position_kapitel_id
     */
    @Id
    @GeneratedValue(generator = "tarif_f_binf_kap_position_kapitel_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_f_binf_kap_position_kapitel_id_seq", schema = "kv", sequenceName = "tarif_f_binf_kap_f_binf_kap_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "position_kapitel_id", nullable = false, updatable = false)
    private Long position_kapitel_id;

    /**
     * The 'fbinfSubkapSet' property.
     * <p>
     * fbinfSubkapSet
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_kapitel_id", referencedColumnName = "position_kapitel_id", nullable = false)
    private java.util.Set<FBinfSubkap> fbinfSubkapSet;

    /**
     * Returns the 'kaplfdnr' property.
     *
     * @return The 'kaplfdnr' property.
     */
    @NotNull
    public Integer getKaplfdnr() {
        return this.kaplfdnr;
    }

    /**
     * Sets the 'kaplfdnr' property.
     *
     * @param kaplfdnr
     *            The 'kaplfdnr' property.
     */
    public void setKaplfdnr(final Integer kaplfdnr) {
        this.kaplfdnr = kaplfdnr;
    }

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
     * Returns the 'kapitel_pre' property.
     *
     * @return The 'kapitel_pre' property.
     */
    @NotNull
    @Size(min = 1, max = 254)
    public String getKapitel_pre() {
        return this.kapitel_pre;
    }

    /**
     * Sets the 'kapitel_pre' property.
     *
     * @param kapitel_pre
     *            The 'kapitel_pre' property.
     */
    public void setKapitel_pre(final String kapitel_pre) {
        this.kapitel_pre = kapitel_pre;
    }

    /**
     * Returns the 'kapitel_name' property.
     *
     * @return The 'kapitel_name' property.
     */
    @NotNull
    @Size(min = 1, max = 500)
    public String getKapitel_name() {
        return this.kapitel_name;
    }

    /**
     * Sets the 'kapitel_name' property.
     *
     * @param kapitel_name
     *            The 'kapitel_name' property.
     */
    public void setKapitel_name(final String kapitel_name) {
        this.kapitel_name = kapitel_name;
    }

    /**
     * Returns the 'kapitel_pre_arab' property.
     *
     * @return The 'kapitel_pre_arab' property.
     */
    public Integer getKapitel_pre_arab() {
        return this.kapitel_pre_arab;
    }

    /**
     * Sets the 'kapitel_pre_arab' property.
     *
     * @param kapitel_pre_arab
     *            The 'kapitel_pre_arab' property.
     */
    public void setKapitel_pre_arab(final Integer kapitel_pre_arab) {
        this.kapitel_pre_arab = kapitel_pre_arab;
    }

    /**
     * Returns the 'position_kapitel_id' property.
     *
     * @return The 'position_kapitel_id' property.
     */
    public Long getPosition_kapitel_id() {
        return this.position_kapitel_id;
    }

    /**
     * Sets the 'position_kapitel_id' property.
     *
     * @param position_kapitel_id
     *            The 'position_kapitel_id' property.
     */
    public void setPosition_kapitel_id(final Long position_kapitel_id) {
        this.position_kapitel_id = position_kapitel_id;
    }

    /**
     * Returns the 'fbinfSubkapSet' property.
     *
     * @return The 'fbinfSubkapSet' property.
     */
    @Valid
    public java.util.Set<FBinfSubkap> getFbinfSubkapSet() {
        if (this.fbinfSubkapSet == null) {
            this.fbinfSubkapSet = new java.util.HashSet<>();
        }
        return this.fbinfSubkapSet;
    }

    /**
     * Sets the 'fbinfSubkapSet' property.
     *
     * @param fbinfSubkapSet
     *            The 'fbinfSubkapSet' property.
     */
    public void setFbinfSubkapSet(final java.util.Set<FBinfSubkap> fbinfSubkapSet) {
        this.fbinfSubkapSet = fbinfSubkapSet;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("FBinfKap [");
        builder.append("kaplfdnr=").append(this.kaplfdnr).append(", ");
        builder.append("abslfdnr=").append(this.abslfdnr).append(", ");
        builder.append("kapitel_pre=").append(this.kapitel_pre).append(", ");
        builder.append("kapitel_name=").append(this.kapitel_name).append(", ");
        builder.append("kapitel_pre_arab=").append(this.kapitel_pre_arab).append(", ");
        builder.append("position_kapitel_id=").append(this.position_kapitel_id).append(", ");
        builder.append("fbinfSubkapSet=").append(this.fbinfSubkapSet).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
