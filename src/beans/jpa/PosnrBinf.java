package beans.jpa;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents the 'PosnrBinf' entity.
 * <p>
 * PosnrBinf
 */
@Access(AccessType.FIELD)
@Entity
@Table(schema = "kv", name = "tarif_posnr_binf")
public class PosnrBinf implements Serializable {
    /**
     * The default unique serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default-Konstruktor
     */
    public PosnrBinf() {
        super();
    }

    /**
     * The 'position_id' property.
     * <p>
     * position_id
     */
    @Id
    @GeneratedValue(generator = "tarif_posnr_binf_position_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tarif_posnr_binf_position_id_seq", schema = "kv", sequenceName = "tarif_posnr_binf_posnr_binf_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "position_id", nullable = false, updatable = false)
    private Long position_id;

    /**
     * The 'posnr' property.
     * <p>
     * posnr
     */
    @Basic(optional = false)
    @Column(name = "posnr", length = 7, nullable = false)
    private String posnr;

    /**
     * The 'hdat_nr' property.
     * <p>
     * hdat_nr
     */
    @Basic(optional = false)
    @Column(name = "hdat_nr", length = 7, nullable = false)
    private String hdat_nr;

    /**
     * The 'abslfdnr' property.
     * <p>
     * abslfdnr
     */
    @Basic
    @Column(name = "abslfdnr")
    private Integer abslfdnr;

    /**
     * The 'kaplfdnr' property.
     * <p>
     * kaplfdnr
     */
    @Basic
    @Column(name = "kaplfdnr")
    private Integer kaplfdnr;

    /**
     * The 'sublfdnr' property.
     * <p>
     * sublfdnr
     */
    @Basic
    @Column(name = "sublfdnr")
    private Integer sublfdnr;

    /**
     * The 'krzpos' property.
     * <p>
     * krzpos
     */
    @Basic
    @Column(name = "krzpos", length = 2)
    private String krzpos;

    /**
     * The 'indikation' property.
     * <p>
     * indikation
     */
    @Basic
    @Column(name = "indikation", length = 2000)
    private String indikation;

    /**
     * The 'info' property.
     * <p>
     * info
     */
    @Basic
    @Column(name = "info", length = 1000)
    private String info;

    /**
     * The 'vo_best' property.
     * <p>
     * vo_best
     */
    @Basic
    @Column(name = "vo_best", length = 254)
    private String vo_best;

    /**
     * The 'blnd' property.
     * <p>
     * blnd
     */
    @Basic
    @Column(name = "blnd", length = 2)
    private String blnd;

    /**
     * The 'beschr' property.
     * <p>
     * beschr
     */
    @Basic
    @Column(name = "beschr", length = 2000)
    private String beschr;

    /**
     * The 'rehab' property.
     * <p>
     * rehab
     */
    @Basic
    @Column(name = "rehab", length = 13)
    private String rehab;

    /**
     * The 'artnr' property.
     * <p>
     * artnr
     */
    @Basic
    @Column(name = "artnr", length = 150)
    private String artnr;

    /**
     * The 'stkanz' property.
     * <p>
     * stkanz
     */
    @Basic
    @Column(name = "stkanz")
    private Integer stkanz;

    /**
     * The 'funktion' property.
     * <p>
     * funktion
     */
    @Basic
    @Column(name = "funktion", length = 2000)
    private String funktion;

    /**
     * The 'wirkw' property.
     * <p>
     * wirkw
     */
    @Basic
    @Column(name = "wirkw", length = 2000)
    private String wirkw;

    /**
     * The 'erkl' property.
     * <p>
     * erkl
     */
    @Basic
    @Column(name = "erkl", length = 2000)
    private String erkl;

    /**
     * The 'ausf' property.
     * <p>
     * ausf
     */
    @Basic
    @Column(name = "ausf", length = 2000)
    private String ausf;

    /**
     * The 'erz' property.
     * <p>
     * erz
     */
    @Basic
    @Column(name = "erz", length = 1000)
    private String erz;

    /**
     * The 'kregion' property.
     * <p>
     * kregion
     */
    @Basic
    @Column(name = "kregion", length = 254)
    private String kregion;

    /**
     * The 'labpar' property.
     * <p>
     * labpar
     */
    @Basic
    @Column(name = "labpar", length = 1)
    private String labpar;

    /**
     * The 'leihgeb' property.
     * <p>
     * leihgeb
     */
    @Basic
    @Column(name = "leihgeb", length = 7)
    private String leihgeb;

    /**
     * The 'notesktxt' property.
     * <p>
     * notesktxt
     */
    @Basic
    @Column(name = "notesktxt", length = 500)
    private String notesktxt;

    /**
     * The 'noteskvpnr' property.
     * <p>
     * noteskvpnr
     */
    @Basic
    @Column(name = "noteskvpnr", length = 7)
    private String noteskvpnr;

    /**
     * The 'rehab_info' property.
     * <p>
     * rehab_info
     */
    @Basic
    @Column(name = "rehab_info", length = 600)
    private String rehab_info;

    /**
     * The 'esv_anzeigen' property.
     * <p>
     * esv_anzeigen
     */
    @Basic
    @Column(name = "esv_anzeigen")
    private Integer esv_anzeigen;

    /**
     * The 'anlegezeitpunkt' property.
     * <p>
     * anlegezeitpunkt
     */
    @Basic
    @Column(name = "anlegezeitpunkt")
    private java.sql.Timestamp anlegezeitpunkt;

    /**
     * The 'gl_tarif_fuer_sachleister' property.
     * <p>
     * gl_tarif_fuer_sachleister
     */
    @Basic(optional = false)
    @Column(name = "gl_tarif_fuer_sachleister", nullable = false)
    private Integer gl_tarif_fuer_sachleister;

    /**
     * The 'fposnrJstat' property.
     * <p>
     * fposnrJstat
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_jstat_id", referencedColumnName = "position_jstat_id")
    private FPosnrJstat fposnrJstat;

    /**
     * The 'fbinfAbschnitt' property.
     * <p>
     * fbinfAbschnitt
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "position_abschnitt_id", referencedColumnName = "position_abschnitt_id", nullable = false)
    private FBinfAbschnitt fbinfAbschnitt;

    /**
     * The 'fbinfKap' property.
     * <p>
     * fbinfKap
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "position_kapitel_id", referencedColumnName = "position_kapitel_id", nullable = false)
    private FBinfKap fbinfKap;

    /**
     * The 'fbinfSubkap' property.
     * <p>
     * fbinfSubkap
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "position_subkapitel_id", referencedColumnName = "position_subkapitel_id", nullable = false)
    private FBinfSubkap fbinfSubkap;

    /**
     * The 'posnrWoke' property.
     * <p>
     * posnrWoke
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "position_woke_id", referencedColumnName = "position_woke_id", nullable = false)
    private PosnrWoke posnrWoke;

    /**
     * The 'posnrText' property.
     * <p>
     * posnrText
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "position_text_id", referencedColumnName = "position_text_id", nullable = false)
    private PosnrText posnrText;

    /**
     * The 'posnrFoko' property.
     * <p>
     * posnrFoko
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "position_foko_id", referencedColumnName = "position_foko_id", nullable = false)
    private PosnrFoko posnrFoko;

    /**
     * The 'fposnrVpSet' property.
     * <p>
     * fposnrVpSet
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id", referencedColumnName = "position_id", nullable = false)
    private java.util.Set<FPosnrVp> fposnrVpSet;

    /**
     * The 'posnrSbtextSet' property.
     * <p>
     * posnrSbtextSet
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id", referencedColumnName = "position_id", nullable = false)
    private java.util.Set<PosnrSbtext> posnrSbtextSet;

    /**
     * The 'posnrSynonymSet' property.
     * <p>
     * posnrSynonymSet
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id", referencedColumnName = "position_id", nullable = false)
    private java.util.Set<PosnrSynonym> posnrSynonymSet;

    /**
     * The 'posVerordnungsreferenzSet' property.
     * <p>
     * posVerordnungsreferenzSet
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id", referencedColumnName = "position_id", nullable = false)
    private java.util.Set<PosVerordnungsreferenz> posVerordnungsreferenzSet;

    /**
     * The 'positionLeistungssparteSet' property.
     * <p>
     * positionLeistungssparteSet
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id", referencedColumnName = "position_id", nullable = false)
    private java.util.Set<PositionLeistungssparte> positionLeistungssparteSet;

    /**
     * The 'positionBundeslandSet' property.
     * <p>
     * positionBundeslandSet
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id", referencedColumnName = "position_id", nullable = false)
    private java.util.Set<PositionBundesland> positionBundeslandSet;

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
     * Returns the 'hdat_nr' property.
     *
     * @return The 'hdat_nr' property.
     */
    @NotNull
    @Size(min = 1, max = 7)
    public String getHdat_nr() {
        return this.hdat_nr;
    }

    /**
     * Sets the 'hdat_nr' property.
     *
     * @param hdat_nr
     *            The 'hdat_nr' property.
     */
    public void setHdat_nr(final String hdat_nr) {
        this.hdat_nr = hdat_nr;
    }

    /**
     * Returns the 'abslfdnr' property.
     *
     * @return The 'abslfdnr' property.
     */
    public Integer getAbslfdnr() {
        return this.abslfdnr;
    }

    /**
     * Sets the 'abslfdnr' property.
     *
     * @param abslfdnr
     *            The 'abslfdnr' property.
     */
    public void setAbslfdnr(final Integer abslfdnr) {
        this.abslfdnr = abslfdnr;
    }

    /**
     * Returns the 'kaplfdnr' property.
     *
     * @return The 'kaplfdnr' property.
     */
    public Integer getKaplfdnr() {
        return this.kaplfdnr;
    }

    /**
     * Sets the 'kaplfdnr' property.
     *
     * @param kaplfdnr
     *            The 'kaplfdnr' property.
     */
    public void setKaplfdnr(final Integer kaplfdnr) {
        this.kaplfdnr = kaplfdnr;
    }

    /**
     * Returns the 'sublfdnr' property.
     *
     * @return The 'sublfdnr' property.
     */
    public Integer getSublfdnr() {
        return this.sublfdnr;
    }

    /**
     * Sets the 'sublfdnr' property.
     *
     * @param sublfdnr
     *            The 'sublfdnr' property.
     */
    public void setSublfdnr(final Integer sublfdnr) {
        this.sublfdnr = sublfdnr;
    }

    /**
     * Returns the 'krzpos' property.
     *
     * @return The 'krzpos' property.
     */
    @Size(min = 1, max = 2)
    public String getKrzpos() {
        return this.krzpos;
    }

    /**
     * Sets the 'krzpos' property.
     *
     * @param krzpos
     *            The 'krzpos' property.
     */
    public void setKrzpos(final String krzpos) {
        this.krzpos = krzpos;
    }

    /**
     * Returns the 'indikation' property.
     *
     * @return The 'indikation' property.
     */
    @Size(min = 1, max = 2000)
    public String getIndikation() {
        return this.indikation;
    }

    /**
     * Sets the 'indikation' property.
     *
     * @param indikation
     *            The 'indikation' property.
     */
    public void setIndikation(final String indikation) {
        this.indikation = indikation;
    }

    /**
     * Returns the 'info' property.
     *
     * @return The 'info' property.
     */
    @Size(min = 1, max = 1000)
    public String getInfo() {
        return this.info;
    }

    /**
     * Sets the 'info' property.
     *
     * @param info
     *            The 'info' property.
     */
    public void setInfo(final String info) {
        this.info = info;
    }

    /**
     * Returns the 'vo_best' property.
     *
     * @return The 'vo_best' property.
     */
    @Size(min = 1, max = 254)
    public String getVo_best() {
        return this.vo_best;
    }

    /**
     * Sets the 'vo_best' property.
     *
     * @param vo_best
     *            The 'vo_best' property.
     */
    public void setVo_best(final String vo_best) {
        this.vo_best = vo_best;
    }

    /**
     * Returns the 'blnd' property.
     *
     * @return The 'blnd' property.
     */
    @Size(min = 1, max = 2)
    public String getBlnd() {
        return this.blnd;
    }

    /**
     * Sets the 'blnd' property.
     *
     * @param blnd
     *            The 'blnd' property.
     */
    public void setBlnd(final String blnd) {
        this.blnd = blnd;
    }

    /**
     * Returns the 'beschr' property.
     *
     * @return The 'beschr' property.
     */
    @Size(min = 1, max = 2000)
    public String getBeschr() {
        return this.beschr;
    }

    /**
     * Sets the 'beschr' property.
     *
     * @param beschr
     *            The 'beschr' property.
     */
    public void setBeschr(final String beschr) {
        this.beschr = beschr;
    }

    /**
     * Returns the 'rehab' property.
     *
     * @return The 'rehab' property.
     */
    @Size(min = 1, max = 13)
    public String getRehab() {
        return this.rehab;
    }

    /**
     * Sets the 'rehab' property.
     *
     * @param rehab
     *            The 'rehab' property.
     */
    public void setRehab(final String rehab) {
        this.rehab = rehab;
    }

    /**
     * Returns the 'artnr' property.
     *
     * @return The 'artnr' property.
     */
    @Size(min = 1, max = 150)
    public String getArtnr() {
        return this.artnr;
    }

    /**
     * Sets the 'artnr' property.
     *
     * @param artnr
     *            The 'artnr' property.
     */
    public void setArtnr(final String artnr) {
        this.artnr = artnr;
    }

    /**
     * Returns the 'stkanz' property.
     *
     * @return The 'stkanz' property.
     */
    public Integer getStkanz() {
        return this.stkanz;
    }

    /**
     * Sets the 'stkanz' property.
     *
     * @param stkanz
     *            The 'stkanz' property.
     */
    public void setStkanz(final Integer stkanz) {
        this.stkanz = stkanz;
    }

    /**
     * Returns the 'funktion' property.
     *
     * @return The 'funktion' property.
     */
    @Size(min = 1, max = 2000)
    public String getFunktion() {
        return this.funktion;
    }

    /**
     * Sets the 'funktion' property.
     *
     * @param funktion
     *            The 'funktion' property.
     */
    public void setFunktion(final String funktion) {
        this.funktion = funktion;
    }

    /**
     * Returns the 'wirkw' property.
     *
     * @return The 'wirkw' property.
     */
    @Size(min = 1, max = 2000)
    public String getWirkw() {
        return this.wirkw;
    }

    /**
     * Sets the 'wirkw' property.
     *
     * @param wirkw
     *            The 'wirkw' property.
     */
    public void setWirkw(final String wirkw) {
        this.wirkw = wirkw;
    }

    /**
     * Returns the 'erkl' property.
     *
     * @return The 'erkl' property.
     */
    @Size(min = 1, max = 2000)
    public String getErkl() {
        return this.erkl;
    }

    /**
     * Sets the 'erkl' property.
     *
     * @param erkl
     *            The 'erkl' property.
     */
    public void setErkl(final String erkl) {
        this.erkl = erkl;
    }

    /**
     * Returns the 'ausf' property.
     *
     * @return The 'ausf' property.
     */
    @Size(min = 1, max = 2000)
    public String getAusf() {
        return this.ausf;
    }

    /**
     * Sets the 'ausf' property.
     *
     * @param ausf
     *            The 'ausf' property.
     */
    public void setAusf(final String ausf) {
        this.ausf = ausf;
    }

    /**
     * Returns the 'erz' property.
     *
     * @return The 'erz' property.
     */
    @Size(min = 1, max = 1000)
    public String getErz() {
        return this.erz;
    }

    /**
     * Sets the 'erz' property.
     *
     * @param erz
     *            The 'erz' property.
     */
    public void setErz(final String erz) {
        this.erz = erz;
    }

    /**
     * Returns the 'kregion' property.
     *
     * @return The 'kregion' property.
     */
    @Size(min = 1, max = 254)
    public String getKregion() {
        return this.kregion;
    }

    /**
     * Sets the 'kregion' property.
     *
     * @param kregion
     *            The 'kregion' property.
     */
    public void setKregion(final String kregion) {
        this.kregion = kregion;
    }

    /**
     * Returns the 'labpar' property.
     *
     * @return The 'labpar' property.
     */
    @Size(min = 1, max = 1)
    public String getLabpar() {
        return this.labpar;
    }

    /**
     * Sets the 'labpar' property.
     *
     * @param labpar
     *            The 'labpar' property.
     */
    public void setLabpar(final String labpar) {
        this.labpar = labpar;
    }

    /**
     * Returns the 'leihgeb' property.
     *
     * @return The 'leihgeb' property.
     */
    @Size(min = 1, max = 7)
    public String getLeihgeb() {
        return this.leihgeb;
    }

    /**
     * Sets the 'leihgeb' property.
     *
     * @param leihgeb
     *            The 'leihgeb' property.
     */
    public void setLeihgeb(final String leihgeb) {
        this.leihgeb = leihgeb;
    }

    /**
     * Returns the 'notesktxt' property.
     *
     * @return The 'notesktxt' property.
     */
    @Size(min = 1, max = 500)
    public String getNotesktxt() {
        return this.notesktxt;
    }

    /**
     * Sets the 'notesktxt' property.
     *
     * @param notesktxt
     *            The 'notesktxt' property.
     */
    public void setNotesktxt(final String notesktxt) {
        this.notesktxt = notesktxt;
    }

    /**
     * Returns the 'noteskvpnr' property.
     *
     * @return The 'noteskvpnr' property.
     */
    @Size(min = 1, max = 7)
    public String getNoteskvpnr() {
        return this.noteskvpnr;
    }

    /**
     * Sets the 'noteskvpnr' property.
     *
     * @param noteskvpnr
     *            The 'noteskvpnr' property.
     */
    public void setNoteskvpnr(final String noteskvpnr) {
        this.noteskvpnr = noteskvpnr;
    }

    /**
     * Returns the 'rehab_info' property.
     *
     * @return The 'rehab_info' property.
     */
    @Size(min = 1, max = 600)
    public String getRehab_info() {
        return this.rehab_info;
    }

    /**
     * Sets the 'rehab_info' property.
     *
     * @param rehab_info
     *            The 'rehab_info' property.
     */
    public void setRehab_info(final String rehab_info) {
        this.rehab_info = rehab_info;
    }

    /**
     * Returns the 'esv_anzeigen' property.
     *
     * @return The 'esv_anzeigen' property.
     */
    public Integer getEsv_anzeigen() {
        return this.esv_anzeigen;
    }

    /**
     * Sets the 'esv_anzeigen' property.
     *
     * @param esv_anzeigen
     *            The 'esv_anzeigen' property.
     */
    public void setEsv_anzeigen(final Integer esv_anzeigen) {
        this.esv_anzeigen = esv_anzeigen;
    }

    /**
     * Returns the 'anlegezeitpunkt' property.
     *
     * @return The 'anlegezeitpunkt' property.
     */
    public java.sql.Timestamp getAnlegezeitpunkt() {
        return this.anlegezeitpunkt;
    }

    /**
     * Sets the 'anlegezeitpunkt' property.
     *
     * @param anlegezeitpunkt
     *            The 'anlegezeitpunkt' property.
     */
    public void setAnlegezeitpunkt(final java.sql.Timestamp anlegezeitpunkt) {
        this.anlegezeitpunkt = anlegezeitpunkt;
    }

    /**
     * Returns the 'gl_tarif_fuer_sachleister' property.
     *
     * @return The 'gl_tarif_fuer_sachleister' property.
     */
    @NotNull
    public Integer getGl_tarif_fuer_sachleister() {
        return this.gl_tarif_fuer_sachleister;
    }

    /**
     * Sets the 'gl_tarif_fuer_sachleister' property.
     *
     * @param gl_tarif_fuer_sachleister
     *            The 'gl_tarif_fuer_sachleister' property.
     */
    public void setGl_tarif_fuer_sachleister(final Integer gl_tarif_fuer_sachleister) {
        this.gl_tarif_fuer_sachleister = gl_tarif_fuer_sachleister;
    }

    /**
     * Returns the 'fposnrJstat' property.
     *
     * @return The 'fposnrJstat' property.
     */
    @Valid
    public FPosnrJstat getFposnrJstat() {
        return this.fposnrJstat;
    }

    /**
     * Sets the 'fposnrJstat' property.
     *
     * @param fposnrJstat
     *            The 'fposnrJstat' property.
     */
    public void setFposnrJstat(final FPosnrJstat fposnrJstat) {
        this.fposnrJstat = fposnrJstat;
    }

    /**
     * Returns the 'fbinfAbschnitt' property.
     *
     * @return The 'fbinfAbschnitt' property.
     */
    @Valid
    @NotNull
    public FBinfAbschnitt getFbinfAbschnitt() {
        return this.fbinfAbschnitt;
    }

    /**
     * Sets the 'fbinfAbschnitt' property.
     *
     * @param fbinfAbschnitt
     *            The 'fbinfAbschnitt' property.
     */
    public void setFbinfAbschnitt(final FBinfAbschnitt fbinfAbschnitt) {
        this.fbinfAbschnitt = fbinfAbschnitt;
    }

    /**
     * Returns the 'fbinfKap' property.
     *
     * @return The 'fbinfKap' property.
     */
    @Valid
    @NotNull
    public FBinfKap getFbinfKap() {
        return this.fbinfKap;
    }

    /**
     * Sets the 'fbinfKap' property.
     *
     * @param fbinfKap
     *            The 'fbinfKap' property.
     */
    public void setFbinfKap(final FBinfKap fbinfKap) {
        this.fbinfKap = fbinfKap;
    }

    /**
     * Returns the 'fbinfSubkap' property.
     *
     * @return The 'fbinfSubkap' property.
     */
    @Valid
    @NotNull
    public FBinfSubkap getFbinfSubkap() {
        return this.fbinfSubkap;
    }

    /**
     * Sets the 'fbinfSubkap' property.
     *
     * @param fbinfSubkap
     *            The 'fbinfSubkap' property.
     */
    public void setFbinfSubkap(final FBinfSubkap fbinfSubkap) {
        this.fbinfSubkap = fbinfSubkap;
    }

    /**
     * Returns the 'posnrWoke' property.
     *
     * @return The 'posnrWoke' property.
     */
    @Valid
    @NotNull
    public PosnrWoke getPosnrWoke() {
        return this.posnrWoke;
    }

    /**
     * Sets the 'posnrWoke' property.
     *
     * @param posnrWoke
     *            The 'posnrWoke' property.
     */
    public void setPosnrWoke(final PosnrWoke posnrWoke) {
        this.posnrWoke = posnrWoke;
    }

    /**
     * Returns the 'posnrText' property.
     *
     * @return The 'posnrText' property.
     */
    @Valid
    @NotNull
    public PosnrText getPosnrText() {
        return this.posnrText;
    }

    /**
     * Sets the 'posnrText' property.
     *
     * @param posnrText
     *            The 'posnrText' property.
     */
    public void setPosnrText(final PosnrText posnrText) {
        this.posnrText = posnrText;
    }

    /**
     * Returns the 'posnrFoko' property.
     *
     * @return The 'posnrFoko' property.
     */
    @Valid
    @NotNull
    public PosnrFoko getPosnrFoko() {
        return this.posnrFoko;
    }

    /**
     * Sets the 'posnrFoko' property.
     *
     * @param posnrFoko
     *            The 'posnrFoko' property.
     */
    public void setPosnrFoko(final PosnrFoko posnrFoko) {
        this.posnrFoko = posnrFoko;
    }

    /**
     * Returns the 'fposnrVpSet' property.
     *
     * @return The 'fposnrVpSet' property.
     */
    @Valid
    public java.util.Set<FPosnrVp> getFposnrVpSet() {
        if (this.fposnrVpSet == null) {
            this.fposnrVpSet = new java.util.HashSet<>();
        }
        return this.fposnrVpSet;
    }

    /**
     * Sets the 'fposnrVpSet' property.
     *
     * @param fposnrVpSet
     *            The 'fposnrVpSet' property.
     */
    public void setFposnrVpSet(final java.util.Set<FPosnrVp> fposnrVpSet) {
        this.fposnrVpSet = fposnrVpSet;
    }

    /**
     * Returns the 'posnrSbtextSet' property.
     *
     * @return The 'posnrSbtextSet' property.
     */
    @Valid
    public java.util.Set<PosnrSbtext> getPosnrSbtextSet() {
        if (this.posnrSbtextSet == null) {
            this.posnrSbtextSet = new java.util.HashSet<>();
        }
        return this.posnrSbtextSet;
    }

    /**
     * Sets the 'posnrSbtextSet' property.
     *
     * @param posnrSbtextSet
     *            The 'posnrSbtextSet' property.
     */
    public void setPosnrSbtextSet(final java.util.Set<PosnrSbtext> posnrSbtextSet) {
        this.posnrSbtextSet = posnrSbtextSet;
    }

    /**
     * Returns the 'posnrSynonymSet' property.
     *
     * @return The 'posnrSynonymSet' property.
     */
    @Valid
    public java.util.Set<PosnrSynonym> getPosnrSynonymSet() {
        if (this.posnrSynonymSet == null) {
            this.posnrSynonymSet = new java.util.HashSet<>();
        }
        return this.posnrSynonymSet;
    }

    /**
     * Sets the 'posnrSynonymSet' property.
     *
     * @param posnrSynonymSet
     *            The 'posnrSynonymSet' property.
     */
    public void setPosnrSynonymSet(final java.util.Set<PosnrSynonym> posnrSynonymSet) {
        this.posnrSynonymSet = posnrSynonymSet;
    }

    /**
     * Returns the 'posVerordnungsreferenzSet' property.
     *
     * @return The 'posVerordnungsreferenzSet' property.
     */
    @Valid
    public java.util.Set<PosVerordnungsreferenz> getPosVerordnungsreferenzSet() {
        if (this.posVerordnungsreferenzSet == null) {
            this.posVerordnungsreferenzSet = new java.util.HashSet<>();
        }
        return this.posVerordnungsreferenzSet;
    }

    /**
     * Sets the 'posVerordnungsreferenzSet' property.
     *
     * @param posVerordnungsreferenzSet
     *            The 'posVerordnungsreferenzSet' property.
     */
    public void setPosVerordnungsreferenzSet(final java.util.Set<PosVerordnungsreferenz> posVerordnungsreferenzSet) {
        this.posVerordnungsreferenzSet = posVerordnungsreferenzSet;
    }

    /**
     * Returns the 'positionLeistungssparteSet' property.
     *
     * @return The 'positionLeistungssparteSet' property.
     */
    @Valid
    public java.util.Set<PositionLeistungssparte> getPositionLeistungssparteSet() {
        if (this.positionLeistungssparteSet == null) {
            this.positionLeistungssparteSet = new java.util.HashSet<>();
        }
        return this.positionLeistungssparteSet;
    }

    /**
     * Sets the 'positionLeistungssparteSet' property.
     *
     * @param positionLeistungssparteSet
     *            The 'positionLeistungssparteSet' property.
     */
    public void setPositionLeistungssparteSet(final java.util.Set<PositionLeistungssparte> positionLeistungssparteSet) {
        this.positionLeistungssparteSet = positionLeistungssparteSet;
    }

    /**
     * Returns the 'positionBundeslandSet' property.
     *
     * @return The 'positionBundeslandSet' property.
     */
    @Valid
    public java.util.Set<PositionBundesland> getPositionBundeslandSet() {
        if (this.positionBundeslandSet == null) {
            this.positionBundeslandSet = new java.util.HashSet<>();
        }
        return this.positionBundeslandSet;
    }

    /**
     * Sets the 'positionBundeslandSet' property.
     *
     * @param positionBundeslandSet
     *            The 'positionBundeslandSet' property.
     */
    public void setPositionBundeslandSet(final java.util.Set<PositionBundesland> positionBundeslandSet) {
        this.positionBundeslandSet = positionBundeslandSet;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PosnrBinf [");
        builder.append("position_id=").append(this.position_id).append(", ");
        builder.append("posnr=").append(this.posnr).append(", ");
        builder.append("hdat_nr=").append(this.hdat_nr).append(", ");
        builder.append("abslfdnr=").append(this.abslfdnr).append(", ");
        builder.append("kaplfdnr=").append(this.kaplfdnr).append(", ");
        builder.append("sublfdnr=").append(this.sublfdnr).append(", ");
        builder.append("krzpos=").append(this.krzpos).append(", ");
        builder.append("indikation=").append(this.indikation).append(", ");
        builder.append("info=").append(this.info).append(", ");
        builder.append("vo_best=").append(this.vo_best).append(", ");
        builder.append("blnd=").append(this.blnd).append(", ");
        builder.append("beschr=").append(this.beschr).append(", ");
        builder.append("rehab=").append(this.rehab).append(", ");
        builder.append("artnr=").append(this.artnr).append(", ");
        builder.append("stkanz=").append(this.stkanz).append(", ");
        builder.append("funktion=").append(this.funktion).append(", ");
        builder.append("wirkw=").append(this.wirkw).append(", ");
        builder.append("erkl=").append(this.erkl).append(", ");
        builder.append("ausf=").append(this.ausf).append(", ");
        builder.append("erz=").append(this.erz).append(", ");
        builder.append("kregion=").append(this.kregion).append(", ");
        builder.append("labpar=").append(this.labpar).append(", ");
        builder.append("leihgeb=").append(this.leihgeb).append(", ");
        builder.append("notesktxt=").append(this.notesktxt).append(", ");
        builder.append("noteskvpnr=").append(this.noteskvpnr).append(", ");
        builder.append("rehab_info=").append(this.rehab_info).append(", ");
        builder.append("esv_anzeigen=").append(this.esv_anzeigen).append(", ");
        builder.append("anlegezeitpunkt=").append(this.anlegezeitpunkt).append(", ");
        builder.append("gl_tarif_fuer_sachleister=").append(this.gl_tarif_fuer_sachleister).append(", ");
        builder.append("fposnrJstat=").append(this.fposnrJstat).append(", ");
        builder.append("fbinfAbschnitt=").append(this.fbinfAbschnitt).append(", ");
        builder.append("fbinfKap=").append(this.fbinfKap).append(", ");
        builder.append("fbinfSubkap=").append(this.fbinfSubkap).append(", ");
        builder.append("posnrWoke=").append(this.posnrWoke).append(", ");
        builder.append("posnrText=").append(this.posnrText).append(", ");
        builder.append("posnrFoko=").append(this.posnrFoko).append(", ");
        builder.append("fposnrVpSet=").append(this.fposnrVpSet).append(", ");
        builder.append("posnrSbtextSet=").append(this.posnrSbtextSet).append(", ");
        builder.append("posnrSynonymSet=").append(this.posnrSynonymSet).append(", ");
        builder.append("posVerordnungsreferenzSet=").append(this.posVerordnungsreferenzSet).append(", ");
        builder.append("positionLeistungssparteSet=").append(this.positionLeistungssparteSet).append(", ");
        builder.append("positionBundeslandSet=").append(this.positionBundeslandSet).append(", ");
        builder.append("]");
        return builder.toString();
    }
}
