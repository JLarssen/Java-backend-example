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
 * Represents the 'PosnrText' entity.
 * <p>
 * PosnrText
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_posnr_text")
public class PosnrText implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public PosnrText() {
        super();
    }

    /**
     * The 'posnr' property.
     * <p>
     * posnr
     */
    @Basic(optional = false)
    @Column(name = "posnr", length = 7, nullable = false)
    private String posnr;

    /**
     * The 'alpha' property.
     * <p>
     * alpha
     */
    @Basic(optional = false)
    @Column(name = "alpha", length = 10, nullable = false)
    private String alpha;

    /**
     * The 'posnrktxt' property.
     * <p>
     * posnrktxt
     */
    @Basic(optional = false)
    @Column(name = "posnrktxt", length = 50, nullable = false)
    private String posnrktxt;

    /**
     * The 'posnrltxt' property.
     * <p>
     * posnrltxt
     */
    @Basic(optional = false)
    @Column(name = "posnrltxt", length = 200, nullable = false)
    private String posnrltxt;

    /**
     * The 'wlst' property.
     * <p>
     * wlst
     */
    @Basic
    @Column(name = "wlst")
    private Integer wlst;

    /**
     * The 'bearbdat' property.
     * <p>
     * bearbdat
     */
    @Basic(optional = false)
    @Column(name = "bearbdat", nullable = false)
    private java.sql.Date bearbdat;

    /**
     * The 'aenderungstag' property.
     * <p>
     * aenderungstag
     */
    @Basic
    @Column(name = "aenderungstag")
    private java.sql.Date aenderungstag;

    /**
     * The 'position_text_id' property.
     * <p>
     * position_text_id
     */
    @Id
    @GeneratedValue(generator = "tarif_posnr_text_position_text_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_posnr_text_position_text_id_seq", schema = "kv", sequenceName = "tarif_posnr_text_posnr_text_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "position_text_id", nullable = false, updatable = false)
    private Long position_text_id;

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
     * Returns the 'alpha' property.
     *
     * @return The 'alpha' property.
     */
    @NotNull
    @Size(min = 1, max = 10)
    public String getAlpha() {
        return this.alpha;
    }

    /**
     * Sets the 'alpha' property.
     *
     * @param alpha
     *            The 'alpha' property.
     */
    public void setAlpha(final String alpha) {
        this.alpha = alpha;
    }

    /**
     * Returns the 'posnrktxt' property.
     *
     * @return The 'posnrktxt' property.
     */
    @NotNull
    @Size(min = 1, max = 50)
    public String getPosnrktxt() {
        return this.posnrktxt;
    }

    /**
     * Sets the 'posnrktxt' property.
     *
     * @param posnrktxt
     *            The 'posnrktxt' property.
     */
    public void setPosnrktxt(final String posnrktxt) {
        this.posnrktxt = posnrktxt;
    }

    /**
     * Returns the 'posnrltxt' property.
     *
     * @return The 'posnrltxt' property.
     */
    @NotNull
    @Size(min = 1, max = 200)
    public String getPosnrltxt() {
        return this.posnrltxt;
    }

    /**
     * Sets the 'posnrltxt' property.
     *
     * @param posnrltxt
     *            The 'posnrltxt' property.
     */
    public void setPosnrltxt(final String posnrltxt) {
        this.posnrltxt = posnrltxt;
    }

    /**
     * Returns the 'wlst' property.
     *
     * @return The 'wlst' property.
     */
    public Integer getWlst() {
        return this.wlst;
    }

    /**
     * Sets the 'wlst' property.
     *
     * @param wlst
     *            The 'wlst' property.
     */
    public void setWlst(final Integer wlst) {
        this.wlst = wlst;
    }

    /**
     * Returns the 'bearbdat' property.
     *
     * @return The 'bearbdat' property.
     */
    @NotNull
    public java.sql.Date getBearbdat() {
        return this.bearbdat;
    }

    /**
     * Sets the 'bearbdat' property.
     *
     * @param bearbdat
     *            The 'bearbdat' property.
     */
    public void setBearbdat(final java.sql.Date bearbdat) {
        this.bearbdat = bearbdat;
    }

    /**
     * Returns the 'aenderungstag' property.
     *
     * @return The 'aenderungstag' property.
     */
    public java.sql.Date getAenderungstag() {
        return this.aenderungstag;
    }

    /**
     * Sets the 'aenderungstag' property.
     *
     * @param aenderungstag
     *            The 'aenderungstag' property.
     */
    public void setAenderungstag(final java.sql.Date aenderungstag) {
        this.aenderungstag = aenderungstag;
    }

    /**
     * Returns the 'position_text_id' property.
     *
     * @return The 'position_text_id' property.
     */
    public Long getPosition_text_id() {
        return this.position_text_id;
    }

    /**
     * Sets the 'position_text_id' property.
     *
     * @param position_text_id
     *            The 'position_text_id' property.
     */
    public void setPosition_text_id(final Long position_text_id) {
        this.position_text_id = position_text_id;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PosnrText [");
        builder.append("posnr=").append(this.posnr).append(", ");
        builder.append("alpha=").append(this.alpha).append(", ");
        builder.append("posnrktxt=").append(this.posnrktxt).append(", ");
        builder.append("posnrltxt=").append(this.posnrltxt).append(", ");
        builder.append("wlst=").append(this.wlst).append(", ");
        builder.append("bearbdat=").append(this.bearbdat).append(", ");
        builder.append("aenderungstag=").append(this.aenderungstag).append(", ");
        builder.append("position_text_id=").append(this.position_text_id).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
