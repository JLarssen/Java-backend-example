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
public class PosnrBinfDTO {
    /**
     * position_id
     */
    private Long position_id;

    /**
     * fbinfAbschnitt
     */
    private beans.dto.FBinfAbschnittDTO fbinfAbschnitt;

    /**
     * fbinfKap
     */
    private beans.dto.FBinfKapDTO fbinfKap;

    /**
     * fbinfSubkap
     */
    private beans.dto.FBinfSubkapDTO fbinfSubkap;

    /**
     * posnrWoke
     */
    private beans.dto.PosnrWokeDTO posnrWoke;

    /**
     * posnrText
     */
    private beans.dto.PosnrTextDTO posnrText;

    /**
     * posnrFoko
     */
    private beans.dto.PosnrFokoDTO posnrFoko;

    /**
     * fposnrJstat
     */
    private beans.dto.FPosnrJstatDTO fposnrJstat;

    /**
     * posnrSbtextSet
     */
    private java.util.Set<PosnrSbtextDTO> posnrSbtextSet;

    /**
     * posnrSynonymSet
     */
    private java.util.Set<PosnrSynonymDTO> posnrSynonymSet;

    /**
     * fposnrVpSet
     */
    private java.util.Set<FPosnrVpDTO> fposnrVpSet;

    /**
     * positionLeistungssparteSet
     */
    private java.util.Set<PositionLeistungssparteDTO> positionLeistungssparteSet;

    /**
     * posVerordnungsreferenzSet
     */
    private java.util.Set<PosVerordnungsreferenzDTO> posVerordnungsreferenzSet;

    /**
     * positionBundeslandSet
     */
    private java.util.Set<PositionBundeslandDTO> positionBundeslandSet;

    /**
     * posnr
     */
    private String posnr;

    /**
     * hdat_nr
     */
    private String hdat_nr;

    /**
     * abslfdnr
     */
    private Integer abslfdnr;

    /**
     * kaplfdnr
     */
    private Integer kaplfdnr;

    /**
     * sublfdnr
     */
    private Integer sublfdnr;

    /**
     * krzpos
     */
    private String krzpos;

    /**
     * indikation
     */
    private String indikation;

    /**
     * info
     */
    private String info;

    /**
     * vo_best
     */
    private String vo_best;

    /**
     * beschr
     */
    private String beschr;

    /**
     * rehab
     */
    private String rehab;

    /**
     * artnr
     */
    private String artnr;

    /**
     * stkanz
     */
    private Integer stkanz;

    /**
     * funktion
     */
    private String funktion;

    /**
     * wirkw
     */
    private String wirkw;

    /**
     * erkl
     */
    private String erkl;

    /**
     * ausf
     */
    private String ausf;

    /**
     * erz
     */
    private String erz;

    /**
     * kregion
     */
    private String kregion;

    /**
     * labpar
     */
    private String labpar;

    /**
     * leihgeb
     */
    private String leihgeb;

    /**
     * notesktxt
     */
    private String notesktxt;

    /**
     * noteskvpnr
     */
    private String noteskvpnr;

    /**
     * rehab_info
     */
    private String rehab_info;

    /**
     * esv_anzeigen
     */
    private Boolean esv_anzeigen;

    /**
     * anlegezeitpunkt
     */
    private java.sql.Timestamp anlegezeitpunkt;

    /**
     * gl_tarif_fuer_sachleister
     */
    private Boolean gl_tarif_fuer_sachleister;

    /* GENERATED Getters and Setters (controlled by SV.java.bean.property) */
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
     * @return gibt die Variable fbinfAbschnitt zurueck
     */
    public beans.dto.FBinfAbschnittDTO getFbinfAbschnitt() {
        return this.fbinfAbschnitt;
    }

    /**
     * setzt die Variable fbinfAbschnitt
     */
    public void setFbinfAbschnitt(final beans.dto.FBinfAbschnittDTO newvalue) {
        this.fbinfAbschnitt = newvalue;
    }

    /**
     * @return gibt die Variable fbinfKap zurueck
     */
    public beans.dto.FBinfKapDTO getFbinfKap() {
        return this.fbinfKap;
    }

    /**
     * setzt die Variable fbinfKap
     */
    public void setFbinfKap(final beans.dto.FBinfKapDTO newvalue) {
        this.fbinfKap = newvalue;
    }

    /**
     * @return gibt die Variable fbinfSubkap zurueck
     */
    public beans.dto.FBinfSubkapDTO getFbinfSubkap() {
        return this.fbinfSubkap;
    }

    /**
     * setzt die Variable fbinfSubkap
     */
    public void setFbinfSubkap(final beans.dto.FBinfSubkapDTO newvalue) {
        this.fbinfSubkap = newvalue;
    }

    /**
     * @return gibt die Variable posnrWoke zurueck
     */
    public beans.dto.PosnrWokeDTO getPosnrWoke() {
        return this.posnrWoke;
    }

    /**
     * setzt die Variable posnrWoke
     */
    public void setPosnrWoke(final beans.dto.PosnrWokeDTO newvalue) {
        this.posnrWoke = newvalue;
    }

    /**
     * @return gibt die Variable posnrText zurueck
     */
    public beans.dto.PosnrTextDTO getPosnrText() {
        return this.posnrText;
    }

    /**
     * setzt die Variable posnrText
     */
    public void setPosnrText(final beans.dto.PosnrTextDTO newvalue) {
        this.posnrText = newvalue;
    }

    /**
     * @return gibt die Variable posnrFoko zurueck
     */
    public beans.dto.PosnrFokoDTO getPosnrFoko() {
        return this.posnrFoko;
    }

    /**
     * setzt die Variable posnrFoko
     */
    public void setPosnrFoko(final beans.dto.PosnrFokoDTO newvalue) {
        this.posnrFoko = newvalue;
    }

    /**
     * @return gibt die Variable fposnrJstat zurueck
     */
    public beans.dto.FPosnrJstatDTO getFposnrJstat() {
        return this.fposnrJstat;
    }

    /**
     * setzt die Variable fposnrJstat
     */
    public void setFposnrJstat(final beans.dto.FPosnrJstatDTO newvalue) {
        this.fposnrJstat = newvalue;
    }

    /**
     * @return gibt die Variable posnrSbtextSet zurueck
     */
    public java.util.Set<PosnrSbtextDTO> getPosnrSbtextSet() {
        return this.posnrSbtextSet;
    }

    /**
     * setzt die Variable posnrSbtextSet
     */
    public void setPosnrSbtextSet(final java.util.Set<PosnrSbtextDTO> newvalue) {
        this.posnrSbtextSet = newvalue;
    }

    /**
     * @return gibt die Variable posnrSynonymSet zurueck
     */
    public java.util.Set<PosnrSynonymDTO> getPosnrSynonymSet() {
        return this.posnrSynonymSet;
    }

    /**
     * setzt die Variable posnrSynonymSet
     */
    public void setPosnrSynonymSet(final java.util.Set<PosnrSynonymDTO> newvalue) {
        this.posnrSynonymSet = newvalue;
    }

    /**
     * @return gibt die Variable fposnrVpSet zurueck
     */
    public java.util.Set<FPosnrVpDTO> getFposnrVpSet() {
        return this.fposnrVpSet;
    }

    /**
     * setzt die Variable fposnrVpSet
     */
    public void setFposnrVpSet(final java.util.Set<FPosnrVpDTO> newvalue) {
        this.fposnrVpSet = newvalue;
    }

    /**
     * @return gibt die Variable positionLeistungssparteSet zurueck
     */
    public java.util.Set<PositionLeistungssparteDTO> getPositionLeistungssparteSet() {
        return this.positionLeistungssparteSet;
    }

    /**
     * setzt die Variable positionLeistungssparteSet
     */
    public void setPositionLeistungssparteSet(final java.util.Set<PositionLeistungssparteDTO> newvalue) {
        this.positionLeistungssparteSet = newvalue;
    }

    /**
     * @return gibt die Variable posVerordnungsreferenzSet zurueck
     */
    public java.util.Set<PosVerordnungsreferenzDTO> getPosVerordnungsreferenzSet() {
        return this.posVerordnungsreferenzSet;
    }

    /**
     * setzt die Variable posVerordnungsreferenzSet
     */
    public void setPosVerordnungsreferenzSet(final java.util.Set<PosVerordnungsreferenzDTO> newvalue) {
        this.posVerordnungsreferenzSet = newvalue;
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
     * @return gibt die Variable hdat_nr zurueck
     */
    public String getHdat_nr() {
        return this.hdat_nr;
    }

    /**
     * setzt die Variable hdat_nr
     */
    public void setHdat_nr(final String newvalue) {
        this.hdat_nr = newvalue;
    }

    /**
     * @return gibt die Variable abslfdnr zurueck
     */
    public Integer getAbslfdnr() {
        return this.abslfdnr;
    }

    /**
     * setzt die Variable abslfdnr
     */
    public void setAbslfdnr(final Integer newvalue) {
        this.abslfdnr = newvalue;
    }

    /**
     * @return gibt die Variable kaplfdnr zurueck
     */
    public Integer getKaplfdnr() {
        return this.kaplfdnr;
    }

    /**
     * setzt die Variable kaplfdnr
     */
    public void setKaplfdnr(final Integer newvalue) {
        this.kaplfdnr = newvalue;
    }

    /**
     * @return gibt die Variable sublfdnr zurueck
     */
    public Integer getSublfdnr() {
        return this.sublfdnr;
    }

    /**
     * setzt die Variable sublfdnr
     */
    public void setSublfdnr(final Integer newvalue) {
        this.sublfdnr = newvalue;
    }

    /**
     * @return gibt die Variable krzpos zurueck
     */
    public String getKrzpos() {
        return this.krzpos;
    }

    /**
     * setzt die Variable krzpos
     */
    public void setKrzpos(final String newvalue) {
        this.krzpos = newvalue;
    }

    /**
     * @return gibt die Variable indikation zurueck
     */
    public String getIndikation() {
        return this.indikation;
    }

    /**
     * setzt die Variable indikation
     */
    public void setIndikation(final String newvalue) {
        this.indikation = newvalue;
    }

    /**
     * @return gibt die Variable info zurueck
     */
    public String getInfo() {
        return this.info;
    }

    /**
     * setzt die Variable info
     */
    public void setInfo(final String newvalue) {
        this.info = newvalue;
    }

    /**
     * @return gibt die Variable vo_best zurueck
     */
    public String getVo_best() {
        return this.vo_best;
    }

    /**
     * setzt die Variable vo_best
     */
    public void setVo_best(final String newvalue) {
        this.vo_best = newvalue;
    }

    /**
     * @return gibt die Variable beschr zurueck
     */
    public String getBeschr() {
        return this.beschr;
    }

    /**
     * setzt die Variable beschr
     */
    public void setBeschr(final String newvalue) {
        this.beschr = newvalue;
    }

    /**
     * @return gibt die Variable rehab zurueck
     */
    public String getRehab() {
        return this.rehab;
    }

    /**
     * setzt die Variable rehab
     */
    public void setRehab(final String newvalue) {
        this.rehab = newvalue;
    }

    /**
     * @return gibt die Variable artnr zurueck
     */
    public String getArtnr() {
        return this.artnr;
    }

    /**
     * setzt die Variable artnr
     */
    public void setArtnr(final String newvalue) {
        this.artnr = newvalue;
    }

    /**
     * @return gibt die Variable stkanz zurueck
     */
    public Integer getStkanz() {
        return this.stkanz;
    }

    /**
     * setzt die Variable stkanz
     */
    public void setStkanz(final Integer newvalue) {
        this.stkanz = newvalue;
    }

    /**
     * @return gibt die Variable funktion zurueck
     */
    public String getFunktion() {
        return this.funktion;
    }

    /**
     * setzt die Variable funktion
     */
    public void setFunktion(final String newvalue) {
        this.funktion = newvalue;
    }

    /**
     * @return gibt die Variable wirkw zurueck
     */
    public String getWirkw() {
        return this.wirkw;
    }

    /**
     * setzt die Variable wirkw
     */
    public void setWirkw(final String newvalue) {
        this.wirkw = newvalue;
    }

    /**
     * @return gibt die Variable erkl zurueck
     */
    public String getErkl() {
        return this.erkl;
    }

    /**
     * setzt die Variable erkl
     */
    public void setErkl(final String newvalue) {
        this.erkl = newvalue;
    }

    /**
     * @return gibt die Variable ausf zurueck
     */
    public String getAusf() {
        return this.ausf;
    }

    /**
     * setzt die Variable ausf
     */
    public void setAusf(final String newvalue) {
        this.ausf = newvalue;
    }

    /**
     * @return gibt die Variable erz zurueck
     */
    public String getErz() {
        return this.erz;
    }

    /**
     * setzt die Variable erz
     */
    public void setErz(final String newvalue) {
        this.erz = newvalue;
    }

    /**
     * @return gibt die Variable kregion zurueck
     */
    public String getKregion() {
        return this.kregion;
    }

    /**
     * setzt die Variable kregion
     */
    public void setKregion(final String newvalue) {
        this.kregion = newvalue;
    }

    /**
     * @return gibt die Variable labpar zurueck
     */
    public String getLabpar() {
        return this.labpar;
    }

    /**
     * setzt die Variable labpar
     */
    public void setLabpar(final String newvalue) {
        this.labpar = newvalue;
    }

    /**
     * @return gibt die Variable leihgeb zurueck
     */
    public String getLeihgeb() {
        return this.leihgeb;
    }

    /**
     * setzt die Variable leihgeb
     */
    public void setLeihgeb(final String newvalue) {
        this.leihgeb = newvalue;
    }

    /**
     * @return gibt die Variable notesktxt zurueck
     */
    public String getNotesktxt() {
        return this.notesktxt;
    }

    /**
     * setzt die Variable notesktxt
     */
    public void setNotesktxt(final String newvalue) {
        this.notesktxt = newvalue;
    }

    /**
     * @return gibt die Variable noteskvpnr zurueck
     */
    public String getNoteskvpnr() {
        return this.noteskvpnr;
    }

    /**
     * setzt die Variable noteskvpnr
     */
    public void setNoteskvpnr(final String newvalue) {
        this.noteskvpnr = newvalue;
    }

    /**
     * @return gibt die Variable rehab_info zurueck
     */
    public String getRehab_info() {
        return this.rehab_info;
    }

    /**
     * setzt die Variable rehab_info
     */
    public void setRehab_info(final String newvalue) {
        this.rehab_info = newvalue;
    }

    /**
     * @return gibt die Variable esv_anzeigen zurueck
     */
    public Boolean getEsv_anzeigen() {
        return this.esv_anzeigen;
    }

    /**
     * setzt die Variable esv_anzeigen
     */
    public void setEsv_anzeigen(final Boolean newvalue) {
        this.esv_anzeigen = newvalue;
    }

    /**
     * @return gibt die Variable anlegezeitpunkt zurueck
     */
    public java.sql.Timestamp getAnlegezeitpunkt() {
        return this.anlegezeitpunkt;
    }

    /**
     * setzt die Variable anlegezeitpunkt
     */
    public void setAnlegezeitpunkt(final java.sql.Timestamp newvalue) {
        this.anlegezeitpunkt = newvalue;
    }

    /**
     * @return gibt die Variable gl_tarif_fuer_sachleister zurueck
     */
    public Boolean getGl_tarif_fuer_sachleister() {
        return this.gl_tarif_fuer_sachleister;
    }

    /**
     * setzt die Variable gl_tarif_fuer_sachleister
     */
    public void setGl_tarif_fuer_sachleister(final Boolean newvalue) {
        this.gl_tarif_fuer_sachleister = newvalue;
    }

    /**
     * @return the positionBundeslandSet
     */
    public java.util.Set<PositionBundeslandDTO> getPositionBundeslandSet() {
        return (this.positionBundeslandSet);
    }

    /**
     * @param positionBundeslandSet
     *            the positionBundeslandSet to set
     */
    public void setPositionBundeslandSet(final java.util.Set<PositionBundeslandDTO> positionBundeslandSet) {
        this.positionBundeslandSet = positionBundeslandSet;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PosnrBinfDTO [position_id=");
        builder.append(this.position_id);
        builder.append(", fbinfAbschnitt=");
        builder.append(this.fbinfAbschnitt);
        builder.append(", fbinfKap=");
        builder.append(this.fbinfKap);
        builder.append(", fbinfSubkap=");
        builder.append(this.fbinfSubkap);
        builder.append(", posnrWoke=");
        builder.append(this.posnrWoke);
        builder.append(", posnrText=");
        builder.append(this.posnrText);
        builder.append(", posnrFoko=");
        builder.append(this.posnrFoko);
        builder.append(", fposnrJstat=");
        builder.append(this.fposnrJstat);
        builder.append(", posnrSbtextSet=");
        builder.append(this.posnrSbtextSet);
        builder.append(", posnrSynonymSet=");
        builder.append(this.posnrSynonymSet);
        builder.append(", fposnrVpSet=");
        builder.append(this.fposnrVpSet);
        builder.append(", positionLeistungssparteSet=");
        builder.append(this.positionLeistungssparteSet);
        builder.append(", posVerordnungsreferenzSet=");
        builder.append(this.posVerordnungsreferenzSet);
        builder.append(", positionBundeslandSet=");
        builder.append(this.positionBundeslandSet);
        builder.append(", posnr=");
        builder.append(this.posnr);
        builder.append(", hdat_nr=");
        builder.append(this.hdat_nr);
        builder.append(", abslfdnr=");
        builder.append(this.abslfdnr);
        builder.append(", kaplfdnr=");
        builder.append(this.kaplfdnr);
        builder.append(", sublfdnr=");
        builder.append(this.sublfdnr);
        builder.append(", krzpos=");
        builder.append(this.krzpos);
        builder.append(", indikation=");
        builder.append(this.indikation);
        builder.append(", info=");
        builder.append(this.info);
        builder.append(", vo_best=");
        builder.append(this.vo_best);
        builder.append(", beschr=");
        builder.append(this.beschr);
        builder.append(", rehab=");
        builder.append(this.rehab);
        builder.append(", artnr=");
        builder.append(this.artnr);
        builder.append(", stkanz=");
        builder.append(this.stkanz);
        builder.append(", funktion=");
        builder.append(this.funktion);
        builder.append(", wirkw=");
        builder.append(this.wirkw);
        builder.append(", erkl=");
        builder.append(this.erkl);
        builder.append(", ausf=");
        builder.append(this.ausf);
        builder.append(", erz=");
        builder.append(this.erz);
        builder.append(", kregion=");
        builder.append(this.kregion);
        builder.append(", labpar=");
        builder.append(this.labpar);
        builder.append(", leihgeb=");
        builder.append(this.leihgeb);
        builder.append(", notesktxt=");
        builder.append(this.notesktxt);
        builder.append(", noteskvpnr=");
        builder.append(this.noteskvpnr);
        builder.append(", rehab_info=");
        builder.append(this.rehab_info);
        builder.append(", esv_anzeigen=");
        builder.append(this.esv_anzeigen);
        builder.append(", anlegezeitpunkt=");
        builder.append(this.anlegezeitpunkt);
        builder.append(", gl_tarif_fuer_sachleister=");
        builder.append(this.gl_tarif_fuer_sachleister);
        builder.append("]");
        return builder.toString();
    }
}
