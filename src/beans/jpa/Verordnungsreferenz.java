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
 * Represents the 'Verordnungsreferenz' entity.
 * <p>
 * Verordnungsreferenz
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_verordnungsreferenz")
public class Verordnungsreferenz implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public Verordnungsreferenz() {
        super();
    }

    /**
     * The 'verordnungsreferenz_id' property.
     * <p>
     * verordnungsreferenz_id
     */
    @Id
    @GeneratedValue(generator = "tarif_verordnungsreferenz_verordnungsreferenz_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_verordnungsreferenz_verordnungsreferenz_id_seq", schema = "kv", sequenceName = "tarif_verordnungsreferenz_verordnungsreferenz_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "verordnungsreferenz_id", nullable = false, updatable = false)
    private Long verordnungsreferenz_id;

    /**
     * The 'verordnungsreferenz' property.
     * <p>
     * verordnungsreferenz
     */
    @Basic(optional = false)
    @Column(name = "verordnungsreferenz", length = 9, nullable = false)
    private String verordnungsreferenz;

    /**
     * The 'verordnungsreferenz_txt' property.
     * <p>
     * verordnungsreferenz_txt
     */
    @Basic(optional = false)
    @Column(name = "verordnungsreferenz_txt", length = 200, nullable = false)
    private String verordnungsreferenz_txt;

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
     * The 'kategorie_txt' property.
     * <p>
     * kategorie_txt
     */
    @Basic
    @Column(name = "kategorie_txt", length = 254)
    private String kategorie_txt;

    /**
     * The 'code_ua' property.
     * <p>
     * code_ua
     */
    @Basic
    @Column(name = "code_ua", length = 3)
    private String code_ua;

    /**
     * Returns the 'verordnungsreferenz_id' property.
     *
     * @return The 'verordnungsreferenz_id' property.
     */
    public Long getVerordnungsreferenz_id() {
        return this.verordnungsreferenz_id;
    }

    /**
     * Sets the 'verordnungsreferenz_id' property.
     *
     * @param verordnungsreferenz_id
     *            The 'verordnungsreferenz_id' property.
     */
    public void setVerordnungsreferenz_id(final Long verordnungsreferenz_id) {
        this.verordnungsreferenz_id = verordnungsreferenz_id;
    }

    /**
     * Returns the 'verordnungsreferenz' property.
     *
     * @return The 'verordnungsreferenz' property.
     */
    @NotNull
    @Size(min = 1, max = 9)
    public String getVerordnungsreferenz() {
        return this.verordnungsreferenz;
    }

    /**
     * Sets the 'verordnungsreferenz' property.
     *
     * @param verordnungsreferenz
     *            The 'verordnungsreferenz' property.
     */
    public void setVerordnungsreferenz(final String verordnungsreferenz) {
        this.verordnungsreferenz = verordnungsreferenz;
    }

    /**
     * Returns the 'verordnungsreferenz_txt' property.
     *
     * @return The 'verordnungsreferenz_txt' property.
     */
    @NotNull
    @Size(min = 1, max = 200)
    public String getVerordnungsreferenz_txt() {
        return this.verordnungsreferenz_txt;
    }

    /**
     * Sets the 'verordnungsreferenz_txt' property.
     *
     * @param verordnungsreferenz_txt
     *            The 'verordnungsreferenz_txt' property.
     */
    public void setVerordnungsreferenz_txt(final String verordnungsreferenz_txt) {
        this.verordnungsreferenz_txt = verordnungsreferenz_txt;
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

    /**
     * Returns the 'kategorie_txt' property.
     *
     * @return The 'kategorie_txt' property.
     */
    @Size(min = 1, max = 254)
    public String getKategorie_txt() {
        return this.kategorie_txt;
    }

    /**
     * Sets the 'kategorie_txt' property.
     *
     * @param kategorie_txt
     *            The 'kategorie_txt' property.
     */
    public void setKategorie_txt(final String kategorie_txt) {
        this.kategorie_txt = kategorie_txt;
    }

    /**
     * Returns the 'code_ua' property.
     *
     * @return The 'code_ua' property.
     */
    @Size(min = 1, max = 3)
    public String getCode_ua() {
        return this.code_ua;
    }

    /**
     * Sets the 'code_ua' property.
     *
     * @param code_ua
     *            The 'code_ua' property.
     */
    public void setCode_ua(final String code_ua) {
        this.code_ua = code_ua;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Verordnungsreferenz [");
        builder.append("verordnungsreferenz_id=").append(this.verordnungsreferenz_id).append(", ");
        builder.append("verordnungsreferenz=").append(this.verordnungsreferenz).append(", ");
        builder.append("verordnungsreferenz_txt=").append(this.verordnungsreferenz_txt).append(", ");
        builder.append("von_am=").append(this.von_am).append(", ");
        builder.append("bis=").append(this.bis).append(", ");
        builder.append("kategorie_txt=").append(this.kategorie_txt).append(", ");
        builder.append("code_ua=").append(this.code_ua).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
