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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents the 'PositionLeistungssparte' entity.
 * <p>
 * PositionLeistungssparte
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_position_leistungssparte")
public class PositionLeistungssparte implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public PositionLeistungssparte() {
        super();
    }

    /**
     * The 'position_leistungssparte_id' property.
     * <p>
     * position_leistungssparte_id
     */
    @Id
    @GeneratedValue(generator = "tarif_position_leistungssparte_position_leistungssparte_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_position_leistungssparte_position_leistungssparte_id_seq", schema = "kv", sequenceName = "tarif_position_leistungssparte_position_leistungssparte_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "position_leistungssparte_id", nullable = false, updatable = false)
    private Long position_leistungssparte_id;

    /**
     * The 'leistungssparte' property.
     * <p>
     * leistungssparte
     */
    @Basic(optional = false)
    @Column(name = "leistungssparte", length = 2, nullable = false)
    private String leistungssparte;

    /**
     * The 'leistungsgruppe_typ' property.
     * <p>
     * leistungsgruppe_typ
     */
    @Basic
    @Column(name = "leistungsgruppe_typ", length = 16)
    private String leistungsgruppe_typ;

    /**
     * The 'lsp_wert' property.
     * <p>
     * lsp_wert
     */
    @Basic(optional = false)
    @Column(name = "lsp_wert", nullable = false)
    private Integer lsp_wert;

    /**
     * The 'leistungspfad' property.
     * <p>
     * leistungspfad
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "leistungspfad_id", referencedColumnName = "leistungspfad_id")
    private Leistungspfad leistungspfad;

    /**
     * The 'buchhaltungSet' property.
     * <p>
     * buchhaltungSet
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_leistungssparte_id", referencedColumnName = "position_leistungssparte_id", nullable = false)
    private java.util.Set<Buchhaltung> buchhaltungSet;

    /**
     * Returns the 'position_leistungssparte_id' property.
     *
     * @return The 'position_leistungssparte_id' property.
     */
    public Long getPosition_leistungssparte_id() {
        return this.position_leistungssparte_id;
    }

    /**
     * Sets the 'position_leistungssparte_id' property.
     *
     * @param position_leistungssparte_id
     *            The 'position_leistungssparte_id' property.
     */
    public void setPosition_leistungssparte_id(final Long position_leistungssparte_id) {
        this.position_leistungssparte_id = position_leistungssparte_id;
    }

    /**
     * Returns the 'leistungssparte' property.
     *
     * @return The 'leistungssparte' property.
     */
    @NotNull
    @Size(min = 1, max = 2)
    public String getLeistungssparte() {
        return this.leistungssparte;
    }

    /**
     * Sets the 'leistungssparte' property.
     *
     * @param leistungssparte
     *            The 'leistungssparte' property.
     */
    public void setLeistungssparte(final String leistungssparte) {
        this.leistungssparte = leistungssparte;
    }

    /**
     * Returns the 'leistungsgruppe_typ' property.
     *
     * @return The 'leistungsgruppe_typ' property.
     */
    @Size(min = 1, max = 16)
    public String getLeistungsgruppe_typ() {
        return this.leistungsgruppe_typ;
    }

    /**
     * Sets the 'leistungsgruppe_typ' property.
     *
     * @param leistungsgruppe_typ
     *            The 'leistungsgruppe_typ' property.
     */
    public void setLeistungsgruppe_typ(final String leistungsgruppe_typ) {
        this.leistungsgruppe_typ = leistungsgruppe_typ;
    }

    /**
     * Returns the 'lsp_wert' property.
     *
     * @return The 'lsp_wert' property.
     */
    @NotNull
    public Integer getLsp_wert() {
        return this.lsp_wert;
    }

    /**
     * Sets the 'lsp_wert' property.
     *
     * @param lsp_wert
     *            The 'lsp_wert' property.
     */
    public void setLsp_wert(final Integer lsp_wert) {
        this.lsp_wert = lsp_wert;
    }

    /**
     * Returns the 'leistungspfad' property.
     *
     * @return The 'leistungspfad' property.
     */
    @Valid
    public Leistungspfad getLeistungspfad() {
        return this.leistungspfad;
    }

    /**
     * Sets the 'leistungspfad' property.
     *
     * @param leistungspfad
     *            The 'leistungspfad' property.
     */
    public void setLeistungspfad(final Leistungspfad leistungspfad) {
        this.leistungspfad = leistungspfad;
    }

    /**
     * Returns the 'buchhaltungSet' property.
     *
     * @return The 'buchhaltungSet' property.
     */
    @Valid
    public java.util.Set<Buchhaltung> getBuchhaltungSet() {
        if (this.buchhaltungSet == null) {
            this.buchhaltungSet = new java.util.HashSet<>();
        }
        return this.buchhaltungSet;
    }

    /**
     * Sets the 'buchhaltungSet' property.
     *
     * @param buchhaltungSet
     *            The 'buchhaltungSet' property.
     */
    public void setBuchhaltungSet(final java.util.Set<Buchhaltung> buchhaltungSet) {
        this.buchhaltungSet = buchhaltungSet;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PositionLeistungssparte [");
        builder.append("position_leistungssparte_id=").append(this.position_leistungssparte_id).append(", ");
        builder.append("leistungssparte=").append(this.leistungssparte).append(", ");
        builder.append("leistungsgruppe_typ=").append(this.leistungsgruppe_typ).append(", ");
        builder.append("lsp_wert=").append(this.lsp_wert).append(", ");
        builder.append("leistungspfad=").append(this.leistungspfad).append(", ");
        builder.append("buchhaltungSet=").append(this.buchhaltungSet).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
