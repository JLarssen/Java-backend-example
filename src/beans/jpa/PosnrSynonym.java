package beans.jpa;

import java.io.Serializable;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents the 'PosnrSynonym' entity.
 * <p>
 * PosnrSynonym
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_posnr_synonym")
public class PosnrSynonym implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public PosnrSynonym() {
        super();
    }

    /**
     * The 'posnr_synonym_id' property.
     * <p>
     * posnr_synonym_id
     */
    @Id
    @GeneratedValue(generator = "tarif_posnr_synonym_posnr_synonym_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_posnr_synonym_posnr_synonym_id_seq", schema = "kv", sequenceName = "tarif_posnr_synonym_posnr_synonym_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "posnr_synonym_id", nullable = false, updatable = false)
    private Long posnr_synonym_id;

    /**
     * The 'posnr' property.
     * <p>
     * posnr
     */
    @Basic(optional = false)
    @Column(name = "posnr", length = 7, nullable = false)
    private String posnr;

    /**
     * The 'synonym_text' property.
     * <p>
     * synonym_text
     */
    @Basic(optional = false)
    @Column(name = "synonym_text", length = 254, nullable = false)
    private String synonym_text;

    /**
     * Returns the 'posnr_synonym_id' property.
     *
     * @return The 'posnr_synonym_id' property.
     */
    public Long getPosnr_synonym_id() {
        return this.posnr_synonym_id;
    }

    /**
     * Sets the 'posnr_synonym_id' property.
     *
     * @param posnr_synonym_id
     *            The 'posnr_synonym_id' property.
     */
    public void setPosnr_synonym_id(final Long posnr_synonym_id) {
        this.posnr_synonym_id = posnr_synonym_id;
    }

    /**
     * Returns the 'posnr' property.
     *
     * @return The 'posnr' property.
     */
    @NotNull
    @Size(min = 1, max = 7)
    public String getPosnr() {
        return this.posnr;
    }

    /**
     * Sets the 'posnr' property.
     *
     * @param posnr
     *            The 'posnr' property.
     */
    public void setPosnr(final String posnr) {
        this.posnr = posnr;
    }

    /**
     * Returns the 'synonym_text' property.
     *
     * @return The 'synonym_text' property.
     */
    @NotNull
    @Size(min = 1, max = 254)
    public String getSynonym_text() {
        return this.synonym_text;
    }

    /**
     * Sets the 'synonym_text' property.
     *
     * @param synonym_text
     *            The 'synonym_text' property.
     */
    public void setSynonym_text(final String synonym_text) {
        this.synonym_text = synonym_text;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PosnrSynonym [");
        builder.append("posnr_synonym_id=").append(this.posnr_synonym_id).append(", ");
        builder.append("posnr=").append(this.posnr).append(", ");
        builder.append("synonym_text=").append(this.synonym_text).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
