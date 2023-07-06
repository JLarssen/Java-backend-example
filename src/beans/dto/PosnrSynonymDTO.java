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
public class PosnrSynonymDTO {
    /**
     * posnr_synonym_id
     */
    private Long posnr_synonym_id;

    /**
     * posnr
     */
    private String posnr;

    /**
     * synonym_text
     */
    private String synonym_text;

    /**
     * serialVersionUID = 41354713L
     */
    private static final long serialVersionUID = 41354713L;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
    /**
     * @return gibt die Variable posnr_synonym_id zurueck
     */
    public Long getPosnr_synonym_id() {
        return this.posnr_synonym_id;
    }

    /**
     * setzt die Variable posnr_synonym_id
     */
    public void setPosnr_synonym_id(final Long newvalue) {
        this.posnr_synonym_id = newvalue;
    }

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
     * @return gibt die Variable synonym_text zurueck
     */
    public String getSynonym_text() {
        return this.synonym_text;
    }

    /**
     * setzt die Variable synonym_text
     */
    public void setSynonym_text(final String newvalue) {
        this.synonym_text = newvalue;
    }

    /* Stubs for additional methods */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("PosnrSynonymDTO [");
        buffer.append("posnr_synonym_id = ").append(this.posnr_synonym_id).append(", ");
        buffer.append("posnr = ").append(this.posnr).append(", ");
        buffer.append("synonym_text = ").append(this.synonym_text).append(", ");
        buffer.append("serialVersionUID = ").append(serialVersionUID).append(", ");
        buffer.append("]");
        return buffer.toString();
    }
}
