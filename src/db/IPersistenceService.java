package db;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import beans.dto.PosnrKrzDTO;
import beans.aliasBean.BevorzugteTarife;
import beans.aliasBean.FokoSA;
import .beans.aliasBean.Positionsname;
import beans.aliasBean.PositionsnameDTO;
import beans.aliasBean.PosnrBereich;
import beans.aliasBean.PosnrFachZuordnung;
import beans.dto.BevorzugteTarifeDTO;
import beans.dto.FBinfKapDTO;
import beans.dto.FBinfSubkapDTO;
import beans.dto.FKtoBhDTO;
import beans.dto.LeistungspfadDTO;
import beans.dto.PositionLeistungssparteDTO;
import beans.dto.PosnrBinfDTO;
import beans.dto.PosnrFachDTO;
import beans.dto.PosnrFokoDTO;
import beans.dto.PosnrSbtextDTO;
import beans.dto.PosnrSynonymDTO;
import beans.dto.PosnrTextDTO;
import beans.dto.PosnrWokeDTO;
import beans.dto.TarifwertDTO;
import beans.dto.VerordnungsreferenzDTO;
import db.basic.IPersistenceBasicService;
import sv.utils.beans.DaoRueckgabeDTO;
import sv.utils.soap.tarifkatalog.typen.TarifZusammenfassung;
import sv.utils.soap.tarifkatalog.typen.TariftypEnum;

/**
 * @author SV-Benutzer
 *
 */
public interface IPersistenceService extends IPersistenceBasicService {
    /**
     * @return Anzahl aller in der DB vorhandenen Zeilen (Tabelle Posnr_Binf)
     */
    public int findAnzahlPosnrsInDB(String request);

    /**
     * Sucht umfangreiche Tarifinformationen und retourniert diese in einem
     * DaoRueckgabeDTO. Liste mit Posnr ist optional. Falls mitgeschickt, werden
     * nur Werte zu den Nummern aus der DB gezogen.
     *
     * @param posnrListe
     *            - optional, um Suche einzuschraenken
     * @return DaoRueckgabeDTO befuellt mit Tarifinformationen
     */
    public DaoRueckgabeDTO findPosnrBinfZuIndex(List<String> posnrListe, String request);

    /**
     * Sucht umfangreiche Tarifinformationen und retourniert diese in einem
     * DaoRueckgabeDTO. Die Anzahl der Resultate laesst sich steuern, indem ein
     * Startwert fuer die erste Zeile und eine maximale Anzahl an Zeilen fuer
     * das Resultat angegeben wird.
     *
     * @param firstResult
     *            - ab welcher Zeile soll das Resultat beginnen
     * @param maxResult
     *            - wie viele Zeilen sollen dem Resultat hinzugefuegt werden
     * @return DaoRueckgabeDTO befuellt mit Tarifinformationen
     */
    public DaoRueckgabeDTO findCreateIndexValuesZuLimit(int firstResult, int maxResult, String request);

    /**
     * Erstellt Map aus Kurzposition mit Posnr
     */
    public List<PosnrKrzDTO> getKrzposData(String request);

    /**
     * liefert eine List<String> mit alle posnr's
     */
    public List<String> findAllPosnrAsString(String request) throws Exception;

    /**
     * liefert eine PosnrBinfDTO gesucht nach posnr zurueck
     */
    public PosnrBinfDTO findPosnrBinfByPosnr(String posnr, String request) throws Exception;

    /**
     * liefert eine PosnrTextDTO gesucht nach posnr zurueck
     */
    public PosnrTextDTO findPosnrTextByPosnr(String posnr, String request);

    /**
     * Loeschen alle PosnrFach-Daten die die aenderungsdatum nicht gleich sind
     */
    public int deletePosnrFachByAenderungstag(Date aenderungstag, String request);

    /**
     * Loeschen alle PosnrTextBO-Daten die die aenderungsdatum nicht gleich sind
     */
    public int deletePosnrTextByAenderungstag(Date aenderungstag, String request);

    /**
     * Liefert eine Liste von Vpnrs (eindeutig) zurueck. Filter posnr. Tabelle:
     * FPosnrVpBO
     */
    public List<String> findVpnrByPosnr(String posnr, String request);

    /**
     * Abfrage mit Set von KapLfdnr oder absldnr, bundesland kann mitgegeben
     * werden. Liefert die Felder von den angefuehrten Tabellen. Verwendete
     * Tabellen: FBinfAbschnittBO, FBinfKapBO
     */
    public DaoRueckgabeDTO findKapitelUndAbschnittBySetKaplfdnr(Integer abslfdnr, Set<Integer> kabLfdnr,
            List<Integer> bundesland, String request);

    /**
     * Abfrage mit Set von AbschnittLfdnr. Liefert die Felder von den
     * angefuehrten Tabellen. Verwendete Tabellen: FBinfAbschnittBO
     */
    public DaoRueckgabeDTO findAbschnittBySetAbschnittlfdnr(Set<Integer> abschnittLfdnr, String request);

    /**
     * Liefert alle Felder aus der Tabelle FBinfAbschnittBO. Die Anzahl an
     * Treffer muss eingegrenzt werden (>0)
     */
    public DaoRueckgabeDTO findAbschnittValuesUEZuLimit(int firstResult, int maxResult, String request);

    /**
     * Liefert die Anzahl der Tabelle FBinfAbschnittBO
     */
    public int findAnzahlAbschnittInDB(String request);

    /**
     * Liefert alle Felder aus der Tabelle FBinfKapBO. Die Anzahl an Treffer
     * muss eingegrenzt werden (>0)
     */
    public DaoRueckgabeDTO findKapitelValuesUEZuLimit(int firstResult, int maxResult, String request);

    /**
     * Liefert die Anzahl der Tabelle FBinfKapBO
     */
    public int findAnzahlKapitelInDB(String request);

    /**
     * Liefert alle Felder aus der Tabelle FBinfSubKap. Die Anzahl an Treffer
     * muss eingegrenzt werden (>0)
     */
    public DaoRueckgabeDTO findSubKapValuesUEZuLimit(int firstResult, int maxResult, String request);

    /**
     * Liefert die Anzahl der Tabelle FBinfSubKap
     */
    public int findAnzahlSubKapInDB(String request);

    /**
     * liefert eine List<String> mit alle PosnrLtxt aus Posnr_text zurueck
     */
    public List<String> findAllPosnrLtxt(String request);

    /**
     * liefert eine List<PosnrSbtextDTO> gesucht nach Posnr-Liste und kzbea_id
     * (Optional) zurueck
     */
    public List<PosnrSbtextDTO> findPosnrSbtextByPosnrListeAndKzbeaId(Set<String> posnrListe, String kzbea_id,
            String request);

    /**
     * liefert eine PosnrSbtextDTO nach posnr und kzbea_id zurueck
     */
    public PosnrSbtextDTO findPosnrSbtextByPosnrKzbeaId(String posnr, String kzbea_id, String request);

    /**
     * Findet zu einer Liste von posnr PosnrBinfDTO-Objekte.
     *
     * @param posnrs
     *            Pflicht
     */
    public List<PosnrBinfDTO> findPosnrBinfByListPosnr(List<String> posnrs, String request);

    /**
     * Findet zu einer Liste von hdat_nr alle korrespondierenden PosnrBinfDTOs
     *
     * @param hdatnrList
     *            Pflicht
     */
    public List<PosnrBinfDTO> findPosnrBinfByListHdatnr(List<String> hdatnrList, String request);

    /**
     * Stellt aus sublfdnr und oder krzpos (muss vorkommen) oder bundesland ein
     * Objekt zusammen. Dao beinhaltet Abschnitt, Kapitel, Subkapitel, PosnrBinf
     * und PosnrText - Informationen
     *
     * Sublfdnr oder Kzrpos Pflichtparameter
     */
    public DaoRueckgabeDTO findPosnrBinfBySublfdnrBundeslandAndKrzPos(Integer sublfdnr, boolean krzposNotNull,
            List<Integer> bundesland, String request);

    /**
     * Stellt aus kaplfdnr und oder abslfdnr oder bundesland ein Objekt
     * zusammen. Dao beinhaltet Abschnitt, Kapitel - Informationen
     *
     * kaplfdnr oder abslfdnr Pflichtparameter
     */
    public DaoRueckgabeDTO findAbschnittAndKapitelBySetKaplfdnrOrAbslfnr(Integer abslfdnr, Set<Integer> kaplfdnr,
            List<Integer> bundesland, String request);

    /**
     * Stellt aus kaplfdnr und oder subKaplfdnr oder bundesland ein Objekt
     * zusammen. Dao beinhaltet Abschnitt, Kapitel, Subkapitel - Informationen
     *
     * kaplfdnr oder subKaplfdnr Pflichtparameter
     */
    public DaoRueckgabeDTO findBySetSubkaplfdnrOrKaplfndr(Integer kaplfdnr, List<Integer> bundesland,
            Set<Integer> subKaplfdnr, String request);

    /**
     * Findet zu Bundesland alle Abschnitt-Objekte
     *
     * bundesland optional
     */
    public DaoRueckgabeDTO findAbschnittByBundesland(List<Integer> bundesland, String request);

    /**
     * Findet zu posnrFirst to posnrLast alle gueltigen Posnr, die noch ein
     * offenes bis datum haben und gibt eine Liste von Posnr zurueck
     */
    public List<String> findStringGueltigenWertByListPosnr(String posnrFirst, String posnrLast, String request);

    /**
     * Findet zu posnrFirst to posnrLast die aktuellsten (das sind die neuesten Tarifwert-Eintragungen) posnr
     * und gibt eine Liste von Posnr zurueck
     */
    public List<String> findStringGueltigenWertByListPosnr(String posnrFirst, String posnrLast, java.sql.Date heute,
            String request);

    /**
     *
     */
    public FBinfSubkapDTO findByAbslfdnrKaplfdnrSubkaplfdnr(Integer abslfdnr, Integer kaplfdnr, Integer sublfdnr,
            String request);

    /**
     * liefert eine List<FKtoBhDTO> zu einer bestimmten PositionLeistungssparte
     */
    public List<FKtoBhDTO> findFKtoBhByPositionLeistungssparte_id(final Long pos_ls_id, final String request);

    /**
     * liefert eine DaoRueckgabeDTO gesucht nach bereich zuruck
     */
    public List<FokoSA> findDaoRueckgabeByBereichPosnrAb(String posnr_ab, Integer bereich, String request);

    /**
     * liefert eine DaoRueckgabeDTO gesucht nach bereich zuruck
     */
    public List<PosnrBereich> findDaoRueckgabeByBereichAbrkat(Integer bereich, String request);

    /**
     * Finde alle PosnrBinf mit esv_anzeigen == true
     */
    public List<PosnrBinfDTO> findPosnrBinfByEsvAnzeigen(String request);

    /**
     * Findet zu einem Set von Abslfdnr Objekte zu Abschnitt und Kapitel
     */
    public DaoRueckgabeDTO findAbschnittUndKapitelBySetKaplfdnr(Set<Integer> abslfdnr, String request);

    /**
     * Liefert zu der Kaplfdnr ein Objekt. Inhalt Abschnitt, Kapitel, und
     * Subkapitel
     */
    public DaoRueckgabeDTO findByKaplfndr(Integer kaplfdnr, String request);

    /**
     * Liefer zu Posnr eine Liste von PosnrSynonymDTO-Objekten
     */
    public List<PosnrSynonymDTO> findPosnrSynonymByPosnrList(final Set<String> posnrs, final String request);

    /**
     * Updatet alle PosnrTextDTO-Datensaetze die ein Aenderungstag gleiche als
     * die aenderungstag haben
     */
    public boolean updatePosnrTextByAenderungstag(final Date aenderungstag, final String request);

    /**
     * Updatet alle PosnrFachDTO-Datensaetze die ein Aenderungstag gleiche als
     * die aenderungstag haben
     */
    public boolean updatePosnrFachByAenderungstag(final Date aenderungstag, final String request);

    /**
     * liefert eine String<POSNR> gesucht nach hdat_nr zurueck
     */
    public String findPosnrByHdatNr(String hdat_nr, String request);

    /**
     * Findet zum Bereich PosnrVon bis PosnrBis PosnrSynonymDTO-Objekte
     *
     * @return List<PosnrSynonymBO>
     */
    public List<PosnrSynonymDTO> findPosnrSynonymByPosnrBereich(final String posnrVon, final String posnrBis,
            final String request);

    /**
     * liefert eine String<HDAT_NR> gesucht nach posnr zurueck
     */
    public String findHdatnrByPosnr(String posnr, String request);

    /**
     * liefert true oder false, wenn Daten gesucht nach
     * gl_tarif_fuer_sachleister vorhanden sind
     */
    public boolean istGlTarifFuerSachleister(String posnr, String request);

    /**
     * Liefert eine Liste von PosnrSbtext zu einem Bereich
     *
     * @param firstResult
     *            Erster Treffer
     * @param maxResult
     *            Anzahl Treffer
     */
    public List<PosnrSbtextDTO> findPosnrSbTexteByAnzahl(final int firstResult, final int maxResult,
            final String request);

    /**
     * liefert eine List<PosnrBinfDTO> gesucht nach suchliste(enhaelt posnr's
     * oder hdat_nummern oder alpha_bezeichnung) und suchart (moegliche werte:
     * POSITIONSNUMMER oder HDAT_NUMMER oder ALPHA_BEZEICHNUNG) zurueck.
     */
    public List<PosnrBinfDTO> findPosnrBinfBySuchlisteSuchart(List<String> suchliste, String suchart, String request);

    /**
     *
     * @param kaplfdnr
     *            zu welchem subkaplfdnr gefunden werden sollen
     *
     * @return List<String> von Subkaplfdnr zum Kapitel
     */
    public List<Integer> findPosnrBinfSubkapitelfdnrByKapLfdnr(final String kaplfdnr, final String request);

    /**
     *
     * @param abslfdnr
     *            zu welchem kaplfdnr gefunden werden sollen
     *
     * @return @return List<String> von kaplfdnr zum Abschnitt
     */
    public List<Integer> findPosnrBinfKapitelLdnrByAbsLfdnr(final Integer abslfdnr, final String request);

    /**
     *
     * @param abslfdnr
     *            abslfdnr (Opional)
     * @param kaplfdnr
     *            kaplfdnr (Pflicht)
     *
     * @return FBinfKapDTO
     */
    public FBinfKapDTO findFBinfKapDTOByAbsLfdnrUndSetKaplfdnr(final Integer abslfdnr, final Integer kaplfdnr,
            final String request);

    /**
     * Findet alle Abslfdnr (eindeutig)
     *
     * @return List<Integer> abslfdnr
     */
    public List<Integer> findPosnrBinfAlleAbslfdnr(final String request);

    /**
     * Finde zu der uebergebenen Posnr die PosnrFach
     */
    public List<PosnrFachDTO> findListPosnrFachDTOByPosnr(final String posnr, final String request);

    /**
     * Finde die PositionId zur Posnr
     */
    public Long findPosnrBinfPositionIdByPosnr(String posnr, String request);

    /**
     * Findet zur Posnr den NotesKtxt der Tabelle PosnrBinf
     */
    public String findPosnrBinfNotesKtxtByPosnr(String posnr, String request);

    /**
     * Findet zu den Verordnungsreferenzen falls vorhanden die jeweiligen
     * Eintraege
     */
    public List<VerordnungsreferenzDTO> findVerordnungsreferenzDTOByVerordnungsreferenzSet(
            Set<String> verordnungsreferenzSet, final boolean posnrVerknuepfung, String request);

    /**
     * Findet zu den Positionsnummern die PosnrBinf-Objekte
     */
    public List<PosnrBinfDTO> findPosnrBinfByPosnrSet(Set<String> posnrs, String request);

    /**
     * Suche zu Applikation und Fachgebiet Eintraege in der Tabelle
     *
     * @param fg
     *            (optional)
     * @param appl
     *            (Applikation
     * @return BevorzugteTarifeBO
     */
    public List<BevorzugteTarife> findBevorzugteTarifeDTOByFachAppl(String fg, String appl,
            final BevorzugteTarifeEnum bevorzugteTarifeEnum, String request);

    /**
     * Findet zu den Parameter einen Eintrag falls vorhanden. Alle Parameter
     * zwingend
     */
    public BevorzugteTarifeDTO findBevorzugteTarifeDTOByFachApplPosnr(final String kategorieWert,
            final String kategorie, final String appl, final String posnr, final String request);

    /**
     * Findet zu den Parameter einen Eintrag falls vorhanden. Alle Parameter
     * zwingend
     */
    public BevorzugteTarifeDTO findBevorzugteTarifeDTOByFachApplVerordnungsreferenz(final String kategorieWert,
            final String appl, final String kategorie, final String verordnungsreferenz, final String request);

    /**
     * Suche PosnrTextBO-Eintrage zu Werten aus der Tabelle BevorzugteTarife
     *
     * @param applikation
     *            pflicht
     * @param fachgebiet
     *            optional
     */
    public List<PosnrTextDTO> findPosnrTextByFachgebietApplikation(Integer fachgebiet, String applikation,
            String request);

    /**
     * Findet zu den Verordnungsreferenzen falls vorhanden die jeweiligen
     * Eintraege
     */
    public List<VerordnungsreferenzDTO> findVerordnungsreferenzDTOByVerordnungsreferenz(String verordnungsreferenz,
            String request);

    /**
     * Findet zum Verordungsreferenz und Datum die pos_verordnungsreferenz_id
     * falls vorhanden
     */
    public Long findPosVerordnungsfererenzByVerordnungsreferenzUndDatum(final String verordnungsreferenz,
            final Date von_am, final String request);

    /**
     * Loesche alle Eintrage in der Tabelle PosVerordnungsreferenz
     */
    public int deleteAllPosVerordnungsreferenz(String request);

    /**
     * Suche BevorzugteTarife zu den Parameter. Zusatzlich wird noch
     * eingeschraenkt ob eine Verknuepfung in der Tabelle PosVerordnungsreferenz
     * exisitert.
     *
     * @param fg
     *            pflicht
     * @param appl
     *            pflicht
     */
    public List<BevorzugteTarifeDTO> findBevorzugteTarifeDTOByApplikationFachgebietUndPosnrVerknuepfung(String fg,
            String appl, String request);

    /**
     * Findet zu den Parameter einen Eintrag falls vorhanden. Alle Parameter
     * zwingend
     */
    public BevorzugteTarifeDTO findBevorzugteTarifeDTOByApplFgVerordnungsreferenzId(Long verordnungsreferenzid,
            String fg, String appl, String request);

    /**
     * Loescht alle Eintraege die eine Verordnungsreferenz-Referenz haben
     *
     * @return anzahl an geloeschten Eintragen
     */
    public int deleteBevorzugteTarifeDTOVerordnungen(String request);

    /**
     * Prueft ob zu der Position_id Eintrage vorhanden sind. Falls ja wird die
     * position_leistungssparte_id zurueckgegeben.
     *
     * @param isLeistungspfadNull
     *            es wird zusatzlich geprueft ob der Leistungspfad Null ist
     */
    public List<Long> findPositionLeistungssparteByPositionId(final Long position_id, final boolean isLeistungspfadNull,
            final String request);

    /**
     * Findet zu den Ids Eintraege in der DB
     */
    public List<PositionLeistungssparteDTO> findPositionLeistugnssparteByIds(List<Long> position_leistungssparte_ids,
            String request);

    /**
     * Findet zu den uebergebenen Positiosnnummern Eintraege
     */
    public List<PosnrTextDTO> findPosnrTextBySetPosnr(Set<String> posnrs, String request);

    /**
     * Findet zu den Positionsnummern Eintraege
     */
    public List<PositionLeistungssparteDTO> findPositionLeistungssparteByListPosnr(List<String> posnrs, String request);

    /**
     * Findet zu den Positionsnummern ausgewaehlte Felder
     */
    public List<PositionsnameDTO> findAliasPositionsnameDTOByListPosnr(List<String> posnrs, String request);

    /**
     * Findet zu den Positionsnummer die/das Fachgebiet(e)
     */
    public List<PosnrFachZuordnung> findPosnrFachFachgFachgNotNull(String request);

    /**
     * gibt das PosnrFoko zur uebergebenen Positionsnummer zurueck, falls vorhanden;
     */
    public PosnrFokoDTO findPosnrFokoByPosnr(final String posnr, final String request);

    /**
     * findTarifwertDTOByPosition_id und Bis-Datum=null
     */
    public List<TarifwertDTO> findTarifwertDTOByPosition_id(final Long position_id, final String request);

    /**
     * findTarifwertDTOByPosition_id und Bis-Datum=null
     */
    public List<TarifwertDTO> findTarifwertDTOByPosition_idDatumBis(final Long position_id, final String request);

    /**
     * Findet List<Object[]> aller Positionen welche ueber ein DaoRueckgabeDTO geliefert wird
     * Ltd steht fuer "limited" weil die Anzahl der Ergebnisse begrenzt wird
     *
     * @param firstResult
     *            die erste zu lesende Tabellenzeile
     * @param maxResult
     *            Begrenzung der gelesenen Anzahl
     */
    public DaoRueckgabeDTO findAllPosnrLtd(int firstResult, final int maxResult, String request);

    /**
     * Findet ein TarifwertDTO (als List) mit bestimmter position_id, tariftyp und von_am
     *
     * @param position_id
     *            zu einer bestimmten Posnr
     * @param tariftyp
     *            der Tariftyp
     * @param von_am
     *            das gueltig_ab Datum fuer einen Tarif
     *
     */
    public List<TarifwertDTO> findTarifwertDTOByPosition_idTariftypDatum(final Long position_id, final String tariftyp,
            final Date von_am, final String request);

    /**
     * Findet ein TarifwertDTO mit bestimmter position_id, tariftyp und von_am
     *
     * @param position_id
     *            zu einer bestimmten Posnr
     * @param tariftyp
     *            der Tariftyp
     * @param bis
     *            das gueltig_bis Datum fuer einen Tarif
     */
    public List<TarifwertDTO> findTarifwertDTOByPosition_idTariftypDatumBis(Long position_id, String tariftyp, Date bis,
            String request);

    /**
     * findPosnrSbtextTotalcount(String request)
     *
     * @return Long
     */
    public Long findPosnrSbtextTotalcount(String request);

    /**
     * findLeistungspfadTotalcount(String request)
     *
     * @return Long
     */
    public Long findLeistungspfadTotalcount(String request);

    /**
     * findAllLeistungspfad()
     *
     * @return LeistungspfadDTO
     */
    public List<LeistungspfadDTO> findAllLeistungspfad(String request);

    /**
     * findAllFKtoBh()
     *
     * @return FKtoBhDTO
     */
    public List<FKtoBhDTO> findAllFKtoBh(String request);

    /**
     * findAllPosnrText()
     *
     * @return PosnrTextDTO
     */
    public List<PosnrTextDTO> findAllPosnrText(String request);

    /**
     * findFachgebieteZuPosnrListundFG()
     *
     * @return PosnrFachDTO
     */
    List<PosnrFachDTO> findFachgebieteZuPosnrListundFG(Set<String> posnrs, Integer fg, String request);
}
