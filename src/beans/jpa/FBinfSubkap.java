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
 * Represents the 'FBinfSubkap' entity.
 * <p>
 * FBinfSubkap
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_f_binf_subkap")
public class FBinfSubkap implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public FBinfSubkap() {
        super();
    }

    /**
     * The 'sublfdnr' property.
     * <p>
     * sublfdnr
     */
    @Basic(optional = false)
    @Column(name = "sublfdnr", nullable = false)
    private Integer sublfdnr;

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
     * The 'subkap_pre' property.
     * <p>
     * subkap_pre
     */
    @Basic(optional = false)
    @Column(name = "subkap_pre", length = 254, nullable = false)
    private String subkap_pre;

    /**
     * The 'subkap_name' property.
     * <p>
     * subkap_name
     */
    @Basic(optional = false)
    @Column(name = "subkap_name", length = 254, nullable = false)
    private String subkap_name;

    /**
     * The 'position_subkapitel_id' property.
     * <p>
     * position_subkapitel_id
     */
    @Id
    @GeneratedValue(generator = "tarif_f_binf_subkap_position_subkapitel_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_f_binf_subkap_position_subkapitel_id_seq", schema = "kv", sequenceName = "tarif_f_binf_subkap_f_binf_subkap_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "position_subkapitel_id", nullable = false, updatable = false)
    private Long position_subkapitel_id;

    /**
     * Returns the 'sublfdnr' property.
     *
     * @return The 'sublfdnr' property.
     */
    @NotNull
    public Integer getSublfdnr() {
        return this.sublfdnr;
    }

    /**
     * Sets the 'sublfdnr' property.
     *
     * @param sublfdnr
     *            The 'sublfdnr' property.
     */
    public void setSublfdnr(final Integer sublfdnr) {
        this.sublfdnr = sublfdnr;
    }

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
     * Returns the 'subkap_pre' property.
     *
     * @return The 'subkap_pre' property.
     */
    @NotNull
    @Size(min = 1, max = 254)
    public String getSubkap_pre() {
        return this.subkap_pre;
    }

    /**
     * Sets the 'subkap_pre' property.
     *
     * @param subkap_pre
     *            The 'subkap_pre' property.
     */
    public void setSubkap_pre(final String subkap_pre) {
        this.subkap_pre = subkap_pre;
    }

    /**
     * Returns the 'subkap_name' property.
     *
     * @return The 'subkap_name' property.
     */
    @NotNull
    @Size(min = 1, max = 254)
    public String getSubkap_name() {
        return this.subkap_name;
    }

    /**
     * Sets the 'subkap_name' property.
     *
     * @param subkap_name
     *            The 'subkap_name' property.
     */
    public void setSubkap_name(final String subkap_name) {
        this.subkap_name = subkap_name;
    }

    /**
     * Returns the 'position_subkapitel_id' property.
     *
     * @return The 'position_subkapitel_id' property.
     */
    public Long getPosition_subkapitel_id() {
        return this.position_subkapitel_id;
    }

    /**
     * Sets the 'position_subkapitel_id' property.
     *
     * @param position_subkapitel_id
     *            The 'position_subkapitel_id' property.
     */
    public void setPosition_subkapitel_id(final Long position_subkapitel_id) {
        this.position_subkapitel_id = position_subkapitel_id;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("FBinfSubkap [");
        builder.append("sublfdnr=").append(this.sublfdnr).append(", ");
        builder.append("kaplfdnr=").append(this.kaplfdnr).append(", ");
        builder.append("abslfdnr=").append(this.abslfdnr).append(", ");
        builder.append("subkap_pre=").append(this.subkap_pre).append(", ");
        builder.append("subkap_name=").append(this.subkap_name).append(", ");
        builder.append("position_subkapitel_id=").append(this.position_subkapitel_id).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
