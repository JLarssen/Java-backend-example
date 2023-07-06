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
public class LeistungspfadDTO {
    /**
     * leistungspfad_id
     */
    private Long leistungspfad_id;

    /**
     * leistungspfadParent
     */
    private beans.dto.LeistungspfadDTO leistungspfadParent;

    /**
     * leistungspfad_code
     */
    private String leistungspfad_code;

    /**
     * leistungspfad_txt
     */
    private String leistungspfad_txt;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable leistungspfad_id zurueck
     */
    public Long getLeistungspfad_id() {
        return this.leistungspfad_id;
    }

    /**
     * setzt die Variable leistungspfad_id
     */
    public void setLeistungspfad_id(final Long newvalue) {
        this.leistungspfad_id = newvalue;
    }

    /**
     * @return gibt die Variable leistungspfadParent zurueck
     */
    public beans.dto.LeistungspfadDTO getLeistungspfadParent() {
        return this.leistungspfadParent;
    }

    /**
     * setzt die Variable leistungspfadParent
     */
    public void setLeistungspfadParent(final beans.dto.LeistungspfadDTO newvalue) {
        this.leistungspfadParent = newvalue;
    }

    /**
     * @return gibt die Variable leistungspfad_code zurueck
     */
    public String getLeistungspfad_code() {
        return this.leistungspfad_code;
    }

    /**
     * setzt die Variable leistungspfad_code
     */
    public void setLeistungspfad_code(final String newvalue) {
        this.leistungspfad_code = newvalue;
    }

    /**
     * @return gibt die Variable leistungspfad_txt zurueck
     */
    public String getLeistungspfad_txt() {
        return this.leistungspfad_txt;
    }

    /**
     * setzt die Variable leistungspfad_txt
     */
    public void setLeistungspfad_txt(final String newvalue) {
        this.leistungspfad_txt = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("LeistungspfadDTO [");
        buffer.append("leistungspfad_id = ").append(this.leistungspfad_id).append(", ");
        buffer.append("leistungspfadParent = ").append(this.leistungspfadParent).append(", ");
        buffer.append("leistungspfad_code = ").append(this.leistungspfad_code).append(", ");
        buffer.append("leistungspfad_txt = ").append(this.leistungspfad_txt).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
