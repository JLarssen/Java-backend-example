package beans.dto;

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
public class PositionLeistungssparteDTO {
    /**
     * position_leistungssparte_id
     */
    private Long position_leistungssparte_id;

    /**
     * leistungspfad
     */
    private beans.dto.LeistungspfadDTO leistungspfad;

    /**
     * buchhaltungSet
     */
    private java.util.Set<BuchhaltungDTO> buchhaltungSet;

    /**
     * leistungssparte
     */
    private String leistungssparte;

    /**
     * leistungsgruppe_typ
     */
    private String leistungsgruppe_typ;

    /**
     * lsp_wert
     */
    private Integer lsp_wert;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable position_leistungssparte_id zurueck
     */
    public Long getPosition_leistungssparte_id() {
        return this.position_leistungssparte_id;
    }

    /**
     * setzt die Variable position_leistungssparte_id
     */
    public void setPosition_leistungssparte_id(final Long newvalue) {
        this.position_leistungssparte_id = newvalue;
    }

    /**
     * @return gibt die Variable leistungspfad zurueck
     */
    public beans.dto.LeistungspfadDTO getLeistungspfad() {
        return this.leistungspfad;
    }

    /**
     * setzt die Variable leistungspfad
     */
    public void setLeistungspfad(final beans.dto.LeistungspfadDTO newvalue) {
        this.leistungspfad = newvalue;
    }

    /**
     * @return gibt die Variable buchhaltungSet zurueck
     */
    public java.util.Set<BuchhaltungDTO> getBuchhaltungSet() {
        return this.buchhaltungSet;
    }

    /**
     * setzt die Variable buchhaltungSet
     */
    public void setBuchhaltungSet(final java.util.Set<BuchhaltungDTO> newvalue) {
        this.buchhaltungSet = newvalue;
    }

    /**
     * @return gibt die Variable leistungssparte zurueck
     */
    public String getLeistungssparte() {
        return this.leistungssparte;
    }

    /**
     * setzt die Variable leistungssparte
     */
    public void setLeistungssparte(final String newvalue) {
        this.leistungssparte = newvalue;
    }

    /**
     * @return gibt die Variable leistungsgruppe_typ zurueck
     */
    public String getLeistungsgruppe_typ() {
        return this.leistungsgruppe_typ;
    }

    /**
     * setzt die Variable leistungsgruppe_typ
     */
    public void setLeistungsgruppe_typ(final String newvalue) {
        this.leistungsgruppe_typ = newvalue;
    }

    /**
     * @return gibt die Variable lsp_wert zurueck
     */
    public Integer getLsp_wert() {
        return this.lsp_wert;
    }

    /**
     * setzt die Variable lsp_wert
     */
    public void setLsp_wert(final Integer newvalue) {
        this.lsp_wert = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("PositionLeistungssparteDTO [");
        buffer.append("position_leistungssparte_id = ").append(this.position_leistungssparte_id).append(", ");
        buffer.append("leistungspfad = ").append(this.leistungspfad).append(", ");
        buffer.append("buchhaltungSet = ").append(this.buchhaltungSet).append(", ");
        buffer.append("leistungssparte = ").append(this.leistungssparte).append(", ");
        buffer.append("leistungsgruppe_typ = ").append(this.leistungsgruppe_typ).append(", ");
        buffer.append("lsp_wert = ").append(this.lsp_wert).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
