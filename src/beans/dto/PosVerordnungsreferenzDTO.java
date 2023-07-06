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
public class PosVerordnungsreferenzDTO {
    /**
     * pos_verordnungsreferenz_id
     */
    private Long pos_verordnungsreferenz_id;

    /**
     * verordnungsreferenz
     */
    private beans.dto.VerordnungsreferenzDTO verordnungsreferenz;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable pos_verordnungsreferenz_id zurueck
     */
    public Long getPos_verordnungsreferenz_id() {
        return this.pos_verordnungsreferenz_id;
    }

    /**
     * setzt die Variable pos_verordnungsreferenz_id
     */
    public void setPos_verordnungsreferenz_id(final Long newvalue) {
        this.pos_verordnungsreferenz_id = newvalue;
    }

    /**
     * @return gibt die Variable verordnungsreferenz zurueck
     */
    public beans.dto.VerordnungsreferenzDTO getVerordnungsreferenz() {
        return this.verordnungsreferenz;
    }

    /**
     * setzt die Variable verordnungsreferenz
     */
    public void setVerordnungsreferenz(final beans.dto.VerordnungsreferenzDTO newvalue) {
        this.verordnungsreferenz = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("PosVerordnungsreferenzDTO [");
        buffer.append("pos_verordnungsreferenz_id = ").append(this.pos_verordnungsreferenz_id).append(", ");
        buffer.append("verordnungsreferenz = ").append(this.verordnungsreferenz).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
