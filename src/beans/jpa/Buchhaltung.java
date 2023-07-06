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
 * Represents the 'Buchhaltung' entity.
 * <p>
 * Buchhaltung
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_buchhaltung")
public class Buchhaltung implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public Buchhaltung() {
        super();
    }

    /**
     * The 'buchhaltung_id' property.
     * <p>
     * buchhaltung_id
     */
    @Id
    @GeneratedValue(generator = "tarif_buchhaltung_buchhaltung_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_buchhaltung_buchhaltung_id_seq", schema = "kv", sequenceName = "tarif_buchhaltung_buchhaltung_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "buchhaltung_id", nullable = false, updatable = false)
    private Long buchhaltung_id;

    /**
     * The 'buchhaltungskonto' property.
     * <p>
     * buchhaltungskonto
     */
    @Basic(optional = false)
    @Column(name = "buchhaltungskonto", length = 10, nullable = false)
    private String buchhaltungskonto;

    /**
     * The 'von_am' property.
     * <p>
     * von_am
     */
    @Basic
    @Column(name = "von_am")
    private java.sql.Date von_am;

    /**
     * The 'bis' property.
     * <p>
     * bis
     */
    @Basic
    @Column(name = "bis")
    private java.sql.Date bis;

    /**
     * Returns the 'buchhaltung_id' property.
     *
     * @return The 'buchhaltung_id' property.
     */
    public Long getBuchhaltung_id() {
        return this.buchhaltung_id;
    }

    /**
     * Sets the 'buchhaltung_id' property.
     *
     * @param buchhaltung_id
     *            The 'buchhaltung_id' property.
     */
    public void setBuchhaltung_id(final Long buchhaltung_id) {
        this.buchhaltung_id = buchhaltung_id;
    }

    /**
     * Returns the 'buchhaltungskonto' property.
     *
     * @return The 'buchhaltungskonto' property.
     */
    @NotNull
    @Size(min = 1, max = 10)
    public String getBuchhaltungskonto() {
        return this.buchhaltungskonto;
    }

    /**
     * Sets the 'buchhaltungskonto' property.
     *
     * @param buchhaltungskonto
     *            The 'buchhaltungskonto' property.
     */
    public void setBuchhaltungskonto(final String buchhaltungskonto) {
        this.buchhaltungskonto = buchhaltungskonto;
    }

    /**
     * Returns the 'von_am' property.
     *
     * @return The 'von_am' property.
     */
    public java.sql.Date getVon_am() {
        return this.von_am;
    }

    /**
     * Sets the 'von_am' property.
     *
     * @param von_am
     *            The 'von_am' property.
     */
    public void setVon_am(final java.sql.Date von_am) {
        this.von_am = von_am;
    }

    /**
     * Returns the 'bis' property.
     *
     * @return The 'bis' property.
     */
    public java.sql.Date getBis() {
        return this.bis;
    }

    /**
     * Sets the 'bis' property.
     *
     * @param bis
     *            The 'bis' property.
     */
    public void setBis(final java.sql.Date bis) {
        this.bis = bis;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Buchhaltung [");
        builder.append("buchhaltung_id=").append(this.buchhaltung_id).append(", ");
        builder.append("buchhaltungskonto=").append(this.buchhaltungskonto).append(", ");
        builder.append("von_am=").append(this.von_am).append(", ");
        builder.append("bis=").append(this.bis).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
