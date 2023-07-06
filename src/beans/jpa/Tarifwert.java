package beans.jpa;

import java.io.Serializable;
import java.math.BigDecimal;

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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents the 'Tarifwert' entity.
 * <p>
 * Tarifwert
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_tarifwert")
public class Tarifwert implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public Tarifwert() {
        super();
    }

    /**
     * The 'tarifwert_id' property.
     * <p>
     * tarifwert_id
     */
    @Id
    @GeneratedValue(generator = "tarif_tarifwert_tarifwert_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_tarifwert_tarifwert_id_seq", schema = "kv", sequenceName = "tarif_tarifwert_tarifwert_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "tarifwert_id", nullable = false, updatable = false)
    private Long tarifwert_id;

    /**
     * The 'position_id' property.
     * <p>
     * position_id
     */
    @Basic
    @Column(name = "position_id")
    private Long position_id;

    /**
     * The 'tarif_typ' property.
     * <p>
     * tarif_typ
     */
    @Basic(optional = false)
    @Column(name = "tarif_typ", length = 16, nullable = false)
    private String tarif_typ;

    /**
     * The 'von_am' property.
     * <p>
     * von_am
     */
    @Basic(optional = false)
    @Column(name = "von_am", nullable = false)
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
     * The 'punktewert' property.
     * <p>
     * punktewert
     */
    @Basic
    @Column(name = "punktewert", precision = 12, scale = 4)
    private BigDecimal punktewert;

    /**
     * The 'punkte' property.
     * <p>
     * punkte
     */
    @Basic
    @Column(name = "punkte", precision = 7, scale = 1)
    private BigDecimal punkte;

    /**
     * The 'erstellungszeitpunkt' property.
     * <p>
     * erstellungszeitpunkt
     */
    @Basic(optional = false)
    @Column(name = "erstellungszeitpunkt", nullable = false)
    private java.sql.Timestamp erstellungszeitpunkt;

    /**
     * The 'aenderungszeitpunkt' property.
     * <p>
     * aenderungszeitpunkt
     */
    @Basic
    @Column(name = "aenderungszeitpunkt")
    private java.sql.Timestamp aenderungszeitpunkt;

    /**
     * The 'tarifwert' property.
     * <p>
     * tarifwert
     */
    @Basic
    @Column(name = "tarifwert", precision = 12, scale = 4)
    private BigDecimal tarifwert;

    /**
     * The 'waehrung' property.
     * <p>
     * waehrung
     */
    @Basic
    @Column(name = "waehrung", length = 16)
    private String waehrung;

    /**
     * Returns the 'tarifwert_id' property.
     *
     * @return The 'tarifwert_id' property.
     */
    public Long getTarifwert_id() {
        return this.tarifwert_id;
    }

    /**
     * Sets the 'tarifwert_id' property.
     *
     * @param tarifwert_id
     *            The 'tarifwert_id' property.
     */
    public void setTarifwert_id(final Long tarifwert_id) {
        this.tarifwert_id = tarifwert_id;
    }

    /**
     * Returns the 'position_id' property.
     *
     * @return The 'position_id' property.
     */
    public Long getPosition_id() {
        return this.position_id;
    }

    /**
     * Sets the 'position_id' property.
     *
     * @param position_id
     *            The 'position_id' property.
     */
    public void setPosition_id(final Long position_id) {
        this.position_id = position_id;
    }

    /**
     * Returns the 'tarif_typ' property.
     *
     * @return The 'tarif_typ' property.
     */
    @NotNull
    @Size(min = 1, max = 16)
    public String getTarif_typ() {
        return this.tarif_typ;
    }

    /**
     * Sets the 'tarif_typ' property.
     *
     * @param tarif_typ
     *            The 'tarif_typ' property.
     */
    public void setTarif_typ(final String tarif_typ) {
        this.tarif_typ = tarif_typ;
    }

    /**
     * Returns the 'von_am' property.
     *
     * @return The 'von_am' property.
     */
    @NotNull
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
     * Returns the 'punktewert' property.
     *
     * @return The 'punktewert' property.
     */
    @Digits(integer = 8, fraction = 4)
    public BigDecimal getPunktewert() {
        return this.punktewert;
    }

    /**
     * Sets the 'punktewert' property.
     *
     * @param punktewert
     *            The 'punktewert' property.
     */
    public void setPunktewert(final BigDecimal punktewert) {
        this.punktewert = punktewert;
    }

    /**
     * Returns the 'punkte' property.
     *
     * @return The 'punkte' property.
     */
    @Digits(integer = 6, fraction = 1)
    public BigDecimal getPunkte() {
        return this.punkte;
    }

    /**
     * Sets the 'punkte' property.
     *
     * @param punkte
     *            The 'punkte' property.
     */
    public void setPunkte(final BigDecimal punkte) {
        this.punkte = punkte;
    }

    /**
     * Returns the 'erstellungszeitpunkt' property.
     *
     * @return The 'erstellungszeitpunkt' property.
     */
    @NotNull
    public java.sql.Timestamp getErstellungszeitpunkt() {
        return this.erstellungszeitpunkt;
    }

    /**
     * Sets the 'erstellungszeitpunkt' property.
     *
     * @param erstellungszeitpunkt
     *            The 'erstellungszeitpunkt' property.
     */
    public void setErstellungszeitpunkt(final java.sql.Timestamp erstellungszeitpunkt) {
        this.erstellungszeitpunkt = erstellungszeitpunkt;
    }

    /**
     * Returns the 'aenderungszeitpunkt' property.
     *
     * @return The 'aenderungszeitpunkt' property.
     */
    public java.sql.Timestamp getAenderungszeitpunkt() {
        return this.aenderungszeitpunkt;
    }

    /**
     * Sets the 'aenderungszeitpunkt' property.
     *
     * @param aenderungszeitpunkt
     *            The 'aenderungszeitpunkt' property.
     */
    public void setAenderungszeitpunkt(final java.sql.Timestamp aenderungszeitpunkt) {
        this.aenderungszeitpunkt = aenderungszeitpunkt;
    }

    /**
     * Returns the 'tarifwert' property.
     *
     * @return The 'tarifwert' property.
     */
    @Digits(integer = 8, fraction = 4)
    public BigDecimal getTarifwert() {
        return this.tarifwert;
    }

    /**
     * Sets the 'tarifwert' property.
     *
     * @param tarifwert
     *            The 'tarifwert' property.
     */
    public void setTarifwert(final BigDecimal tarifwert) {
        this.tarifwert = tarifwert;
    }

    /**
     * Returns the 'waehrung' property.
     *
     * @return The 'waehrung' property.
     */
    @Size(min = 1, max = 16)
    public String getWaehrung() {
        return this.waehrung;
    }

    /**
     * Sets the 'waehrung' property.
     *
     * @param waehrung
     *            The 'waehrung' property.
     */
    public void setWaehrung(final String waehrung) {
        this.waehrung = waehrung;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Tarifwert [");
        builder.append("tarifwert_id=").append(this.tarifwert_id).append(", ");
        builder.append("position_id=").append(this.position_id).append(", ");
        builder.append("tarif_typ=").append(this.tarif_typ).append(", ");
        builder.append("von_am=").append(this.von_am).append(", ");
        builder.append("bis=").append(this.bis).append(", ");
        builder.append("punktewert=").append(this.punktewert).append(", ");
        builder.append("punkte=").append(this.punkte).append(", ");
        builder.append("erstellungszeitpunkt=").append(this.erstellungszeitpunkt).append(", ");
        builder.append("aenderungszeitpunkt=").append(this.aenderungszeitpunkt).append(", ");
        builder.append("tarifwert=").append(this.tarifwert).append(", ");
        builder.append("waehrung=").append(this.waehrung).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
