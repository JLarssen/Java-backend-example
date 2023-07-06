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
public class PosnrFokoDTO {
    /**
     * posnr
     */
    private String posnr;

    /**
     * bereich
     */
    private Integer bereich;

    /**
     * abrkat
     */
    private Integer abrkat;

    /**
     * live
     */
    private Integer live;

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
     * @return gibt die Variable bereich zurueck
     */
    public Integer getBereich() {
        return this.bereich;
    }

    /**
     * setzt die Variable bereich
     */
    public void setBereich(final Integer newvalue) {
        this.bereich = newvalue;
    }

    /**
     * @return gibt die Variable abrkat zurueck
     */
    public Integer getAbrkat() {
        return this.abrkat;
    }

    /**
     * setzt die Variable abrkat
     */
    public void setAbrkat(final Integer newvalue) {
        this.abrkat = newvalue;
    }

    /**
     * @return gibt die Variable live zurueck
     */
    public Integer getLive() {
        return this.live;
    }

    /**
     * setzt die Variable live
     */
    public void setLive(final Integer newvalue) {
        this.live = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("PosnrFokoDTO [");
        buffer.append("posnr = ").append(this.posnr).append(", ");
        buffer.append("bereich = ").append(this.bereich).append(", ");
        buffer.append("abrkat = ").append(this.abrkat).append(", ");
        buffer.append("live = ").append(this.live).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
