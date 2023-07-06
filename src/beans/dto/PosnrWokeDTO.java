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
public class PosnrWokeDTO {
    /**
     * posnr
     */
    private String posnr;

    /**
     * rechenbox
     */
    private String rechenbox;
    /**
     * opgruppe
     */
    private Integer opgruppe;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable posnr zurueck
     */
    public String getPosnr() {
        return this.posnr;
    }

    /**
     * setzt die Variable posnr
     */
    public void setPosnr(final String newvalue) {
        this.posnr = newvalue;
    }

    /**
     * @return gibt die Variable rechenbox zurueck
     */
    public String getRechenbox() {
        return this.rechenbox;
    }

    /**
     * setzt die Variable rechenbox
     */
    public void setRechenbox(final String newvalue) {
        this.rechenbox = newvalue;
    }

    /**
     * @return gibt die Variable opgruppe zurueck
     */
    public Integer getOpgruppe() {
        return this.opgruppe;
    }

    /**
     * setzt die Variable opgruppe
     */
    public void setOpgruppe(final Integer newvalue) {
        this.opgruppe = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("PosnrWokeDTO [");
        buffer.append("posnr = ").append(this.posnr).append(", ");
        buffer.append("rechenbox = ").append(this.rechenbox).append(", ");
        buffer.append("opgruppe = ").append(this.opgruppe).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
