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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents the 'BevorzugteTarife' entity.
 * <p>
 * BevorzugteTarife
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_bevorzugte_tarife")
public class BevorzugteTarife implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public BevorzugteTarife() {
        super();
    }

    /**
     * The 'bevorzugte_tarife_id' property.
     * <p>
     * bevorzugte_tarife_id
     */
    @Id
    @GeneratedValue(generator = "tarif_bevorzugte_tarife_bevorzugte_tarife_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_bevorzugte_tarife_bevorzugte_tarife_id_seq", schema = "kv", sequenceName = "tarif_bevorzugte_tarife_bevorzugte_tarife_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "bevorzugte_tarife_id", nullable = false, updatable = false)
    private Long bevorzugte_tarife_id;

    /**
     * The 'fachgebiet' property.
     * <p>
     * fachgebiet
     */
    @Basic
    @Column(name = "fachgebiet", length = 2)
    private String fachgebiet;

    /**
     * The 'applikation' property.
     * <p>
     * applikation
     */
    @Basic(optional = false)
    @Column(name = "applikation", length = 16, nullable = false)
    private String applikation;

    /**
     * The 'kategorie' property.
     * <p>
     * kategorie
     */
    @Basic
    @Column(name = "kategorie", length = 16)
    private String kategorie;

    /**
     * The 'kategorie_wert' property.
     * <p>
     * kategorie_wert
     */
    @Basic
    @Column(name = "kategorie_wert", length = 16)
    private String kategorie_wert;

    /**
     * The 'verordnungsreferenz' property.
     * <p>
     * verordnungsreferenz
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "verordnungsreferenz_id", referencedColumnName = "verordnungsreferenz_id")
    private Verordnungsreferenz verordnungsreferenz;

    /**
     * The 'position' property.
     * <p>
     * position
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    private PosnrBinf position;

    /**
     * Returns the 'bevorzugte_tarife_id' property.
     *
     * @return The 'bevorzugte_tarife_id' property.
     */
    public Long getBevorzugte_tarife_id() {
        return this.bevorzugte_tarife_id;
    }

    /**
     * Sets the 'bevorzugte_tarife_id' property.
     *
     * @param bevorzugte_tarife_id
     *            The 'bevorzugte_tarife_id' property.
     */
    public void setBevorzugte_tarife_id(final Long bevorzugte_tarife_id) {
        this.bevorzugte_tarife_id = bevorzugte_tarife_id;
    }

    /**
     * Returns the 'fachgebiet' property.
     *
     * @return The 'fachgebiet' property.
     */
    @Size(min = 1, max = 2)
    public String getFachgebiet() {
        return this.fachgebiet;
    }

    /**
     * Sets the 'fachgebiet' property.
     *
     * @param fachgebiet
     *            The 'fachgebiet' property.
     */
    public void setFachgebiet(final String fachgebiet) {
        this.fachgebiet = fachgebiet;
    }

    /**
     * Returns the 'applikation' property.
     *
     * @return The 'applikation' property.
     */
    @NotNull
    @Size(min = 1, max = 16)
    public String getApplikation() {
        return this.applikation;
    }

    /**
     * Sets the 'applikation' property.
     *
     * @param applikation
     *            The 'applikation' property.
     */
    public void setApplikation(final String applikation) {
        this.applikation = applikation;
    }

    /**
     * Returns the 'kategorie' property.
     *
     * @return The 'kategorie' property.
     */
    @Size(min = 1, max = 16)
    public String getKategorie() {
        return this.kategorie;
    }

    /**
     * Sets the 'kategorie' property.
     *
     * @param kategorie
     *            The 'kategorie' property.
     */
    public void setKategorie(final String kategorie) {
        this.kategorie = kategorie;
    }

    /**
     * Returns the 'kategorie_wert' property.
     *
     * @return The 'kategorie_wert' property.
     */
    @Size(min = 1, max = 16)
    public String getKategorie_wert() {
        return this.kategorie_wert;
    }

    /**
     * Sets the 'kategorie_wert' property.
     *
     * @param kategorie_wert
     *            The 'kategorie_wert' property.
     */
    public void setKategorie_wert(final String kategorie_wert) {
        this.kategorie_wert = kategorie_wert;
    }

    /**
     * Returns the 'verordnungsreferenz' property.
     *
     * @return The 'verordnungsreferenz' property.
     */
    @Valid
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

    /**
     * Returns the 'position' property.
     *
     * @return The 'position' property.
     */
    @Valid
    public PosnrBinf getPosition() {
        return this.position;
    }

    /**
     * Sets the 'position' property.
     *
     * @param position
     *            The 'position' property.
     */
    public void setPosition(final PosnrBinf position) {
        this.position = position;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BevorzugteTarife [");
        builder.append("bevorzugte_tarife_id=").append(this.bevorzugte_tarife_id).append(", ");
        builder.append("fachgebiet=").append(this.fachgebiet).append(", ");
        builder.append("applikation=").append(this.applikation).append(", ");
        builder.append("kategorie=").append(this.kategorie).append(", ");
        builder.append("kategorie_wert=").append(this.kategorie_wert).append(", ");
        builder.append("verordnungsreferenz=").append(this.verordnungsreferenz).append(", ");
        builder.append("position=").append(this.position).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
