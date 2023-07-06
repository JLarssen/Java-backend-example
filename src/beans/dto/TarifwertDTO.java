package beans.dto;

import java.math.BigDecimal;

/**
 *
 *
 * GENERATED CONTENT - DO NOT EDIT!
 *
 * Disclaimer: this content gets replaced with every run of the generator!
 * Template: class_description.txt
 *
 * @author sca-mda-generator
 *
 */
public class TarifwertDTO {
    /**
     * tarifwert_id
     */
    private Long tarifwert_id;

    /**
     * position_id
     */
    private Long position_id;

    /**
     * tarif_typ
     */
    private String tarif_typ;

    /**
     * von_am
     */
    private java.sql.Date von_am;

    /**
     * bis
     */
    private java.sql.Date bis;

    /**
     * punktewert
     */
    private BigDecimal punktewert;

    /**
     * punkte
     */
    private BigDecimal punkte;

    /**
     * erstellungszeitpunkt
     */
    private java.sql.Timestamp erstellungszeitpunkt;

    /**
     * aenderungszeitpunkt
     */
    private java.sql.Timestamp aenderungszeitpunkt;

    /**
     * tarifwert
     */
    private BigDecimal tarifwert;

    /**
     * waehrung
     */
    private String waehrung;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable tarifwert_id zurueck
     */
    public Long getTarifwert_id() {
        return this.tarifwert_id;
    }

    /**
     * setzt die Variable tarifwert_id
     */
    public void setTarifwert_id(final Long newvalue) {
        this.tarifwert_id = newvalue;
    }

    /**
     * @return gibt die Variable position_id zurueck
     */
    public Long getPosition_id() {
        return this.position_id;
    }

    /**
     * setzt die Variable position_id
     */
    public void setPosition_id(final Long newvalue) {
        this.position_id = newvalue;
    }

    /**
     * @return gibt die Variable tarif_typ zurueck
     */
    public String getTarif_typ() {
        return this.tarif_typ;
    }

    /**
     * setzt die Variable tarif_typ
     */
    public void setTarif_typ(final String newvalue) {
        this.tarif_typ = newvalue;
    }

    /**
     * @return gibt die Variable von_am zurueck
     */
    public java.sql.Date getVon_am() {
        return this.von_am;
    }

    /**
     * setzt die Variable von_am
     */
    public void setVon_am(final java.sql.Date newvalue) {
        this.von_am = newvalue;
    }

    /**
     * @return gibt die Variable bis zurueck
     */
    public java.sql.Date getBis() {
        return this.bis;
    }

    /**
     * setzt die Variable bis
     */
    public void setBis(final java.sql.Date newvalue) {
        this.bis = newvalue;
    }

    /**
     * @return gibt die Variable punktewert zurueck
     */
    public BigDecimal getPunktewert() {
        return this.punktewert;
    }

    /**
     * setzt die Variable punktewert
     */
    public void setPunktewert(final BigDecimal newvalue) {
        this.punktewert = newvalue;
    }

    /**
     * @return gibt die Variable punkte zurueck
     */
    public BigDecimal getPunkte() {
        return this.punkte;
    }

    /**
     * setzt die Variable punkte
     */
    public void setPunkte(final BigDecimal newvalue) {
        this.punkte = newvalue;
    }

    /**
     * @return gibt die Variable erstellungszeitpunkt zurueck
     */
    public java.sql.Timestamp getErstellungszeitpunkt() {
        return this.erstellungszeitpunkt;
    }

    /**
     * setzt die Variable erstellungszeitpunkt
     */
    public void setErstellungszeitpunkt(final java.sql.Timestamp newvalue) {
        this.erstellungszeitpunkt = newvalue;
    }

    /**
     * @return gibt die Variable aenderungszeitpunkt zurueck
     */
    public java.sql.Timestamp getAenderungszeitpunkt() {
        return this.aenderungszeitpunkt;
    }

    /**
     * setzt die Variable aenderungszeitpunkt
     */
    public void setAenderungszeitpunkt(final java.sql.Timestamp newvalue) {
        this.aenderungszeitpunkt = newvalue;
    }

    /**
     * @return gibt die Variable tarifwert zurueck
     */
    public BigDecimal getTarifwert() {
        return this.tarifwert;
    }

    /**
     * setzt die Variable tarifwert
     */
    public void setTarifwert(final BigDecimal newvalue) {
        this.tarifwert = newvalue;
    }

    /**
     * @return gibt die Variable waehrung zurueck
     */
    public String getWaehrung() {
        return this.waehrung;
    }

    /**
     * setzt die Variable waehrung
     */
    public void setWaehrung(final String newvalue) {
        this.waehrung = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("TarifwertDTO [");
        buffer.append("tarifwert_id = ").append(this.tarifwert_id).append(", ");
        buffer.append("position_id = ").append(this.position_id).append(", ");
        buffer.append("tarif_typ = ").append(this.tarif_typ).append(", ");
        buffer.append("von_am = ").append(this.von_am).append(", ");
        buffer.append("bis = ").append(this.bis).append(", ");
        buffer.append("punktewert = ").append(this.punktewert).append(", ");
        buffer.append("punkte = ").append(this.punkte).append(", ");
        buffer.append("erstellungszeitpunkt = ").append(this.erstellungszeitpunkt).append(", ");
        buffer.append("aenderungszeitpunkt = ").append(this.aenderungszeitpunkt).append(", ");
        buffer.append("tarifwert = ").append(this.tarifwert).append(", ");
        buffer.append("waehrung = ").append(this.waehrung).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
