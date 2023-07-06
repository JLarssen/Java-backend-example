package db.basic;

import java.io.Serializable;
import java.util.List;

import beans.dto.BevorzugteTarifeDTO;
import beans.dto.BuchhaltungDTO;
import beans.dto.FBinfAbschnittDTO;
import beans.dto.FBinfKapDTO;
import beans.dto.FBinfSubkapDTO;
import beans.dto.FKtoBhDTO;
import beans.dto.FPosnrJstatDTO;
import beans.dto.FPosnrVpDTO;
import beans.dto.LeistungspfadDTO;
import beans.dto.PosVerordnungsreferenzDTO;
import beans.dto.PositionBundeslandDTO;
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

/**
 * GENERATED CONTENT - DO NOT EDIT!
 *
 * Disclaimer: this content gets replaced with every run of the Generator!
 *
 * @author hbm2service-Generator
 */
public interface IPersistenceBasicService {
    /**
     * saveFPosnrJstat(FPosnrJstatDTO dto)
     *
     * @return FPosnrJstatDTO
     */
    public FPosnrJstatDTO saveFPosnrJstat(FPosnrJstatDTO dto, String request);

    /**
     * saveListFPosnrJstat(FPosnrJstatDTO dto)
     *
     * @return FPosnrJstatDTO
     */
    public List<FPosnrJstatDTO> saveFPosnrJstatList(List<FPosnrJstatDTO> dtoList, String request);

    /**
     * deleteFPosnrJstat(Serializable id)
     */
    public void deleteFPosnrJstat(Serializable id, String request);

    /**
     * deleteFPosnrJstat(FPosnrJstatDTO dto)
     */
    public void deleteFPosnrJstat(FPosnrJstatDTO bo, String request);

    /**
     * findFPosnrJstatById(Serializable id)
     *
     * @return FPosnrJstatDTO
     */
    public FPosnrJstatDTO findFPosnrJstatById(Serializable id, String request);

    /**
     * saveBuchhaltung(BuchhaltungDTO dto)
     *
     * @return BuchhaltungDTO
     */
    public BuchhaltungDTO saveBuchhaltung(BuchhaltungDTO dto, String request);

    /**
     * saveListBuchhaltung(BuchhaltungDTO dto)
     *
     * @return BuchhaltungDTO
     */
    public List<BuchhaltungDTO> saveBuchhaltungList(List<BuchhaltungDTO> dtoList, String request);

    /**
     * deleteBuchhaltung(Serializable id)
     */
    public void deleteBuchhaltung(Serializable id, String request);

    /**
     * deleteBuchhaltung(BuchhaltungDTO dto)
     */
    public void deleteBuchhaltung(BuchhaltungDTO bo, String request);

    /**
     * findBuchhaltungById(Serializable id)
     *
     * @return BuchhaltungDTO
     */
    public BuchhaltungDTO findBuchhaltungById(Serializable id, String request);

    /**
     * saveBevorzugteTarife(BevorzugteTarifeDTO dto)
     *
     * @return BevorzugteTarifeDTO
     */
    public BevorzugteTarifeDTO saveBevorzugteTarife(BevorzugteTarifeDTO dto, String request);

    /**
     * saveListBevorzugteTarife(BevorzugteTarifeDTO dto)
     *
     * @return BevorzugteTarifeDTO
     */
    public List<BevorzugteTarifeDTO> saveBevorzugteTarifeList(List<BevorzugteTarifeDTO> dtoList, String request);

    /**
     * deleteBevorzugteTarife(Serializable id)
     */
    public void deleteBevorzugteTarife(Serializable id, String request);

    /**
     * deleteBevorzugteTarife(BevorzugteTarifeDTO dto)
     */
    public void deleteBevorzugteTarife(BevorzugteTarifeDTO bo, String request);

    /**
     * findBevorzugteTarifeById(Serializable id)
     *
     * @return BevorzugteTarifeDTO
     */
    public BevorzugteTarifeDTO findBevorzugteTarifeById(Serializable id, String request);

    /**
     * savePosnrBinf(PosnrBinfDTO dto)
     *
     * @return PosnrBinfDTO
     */
    public PosnrBinfDTO savePosnrBinf(PosnrBinfDTO dto, String request);

    /**
     * saveListPosnrBinf(PosnrBinfDTO dto)
     *
     * @return PosnrBinfDTO
     */
    public List<PosnrBinfDTO> savePosnrBinfList(List<PosnrBinfDTO> dtoList, String request);

    /**
     * deletePosnrBinf(Serializable id)
     */
    public void deletePosnrBinf(Serializable id, String request);

    /**
     * deletePosnrBinf(PosnrBinfDTO dto)
     */
    public void deletePosnrBinf(PosnrBinfDTO bo, String request);

    /**
     * findPosnrBinfById(Serializable id)
     *
     * @return PosnrBinfDTO
     */
    public PosnrBinfDTO findPosnrBinfById(Serializable id, String request);

    /**
     * savePosnrWoke(PosnrWokeDTO dto)
     *
     * @return PosnrWokeDTO
     */
    public PosnrWokeDTO savePosnrWoke(PosnrWokeDTO dto, String request);

    /**
     * saveListPosnrWoke(PosnrWokeDTO dto)
     *
     * @return PosnrWokeDTO
     */
    public List<PosnrWokeDTO> savePosnrWokeList(List<PosnrWokeDTO> dtoList, String request);

    /**
     * deletePosnrWoke(Serializable id)
     */
    public void deletePosnrWoke(Serializable id, String request);

    /**
     * deletePosnrWoke(PosnrWokeDTO dto)
     */
    public void deletePosnrWoke(PosnrWokeDTO bo, String request);

    /**
     * findPosnrWokeById(Serializable id)
     *
     * @return PosnrWokeDTO
     */
    public PosnrWokeDTO findPosnrWokeById(Serializable id, String request);

    /**
     * savePosnrFach(PosnrFachDTO dto)
     *
     * @return PosnrFachDTO
     */
    public PosnrFachDTO savePosnrFach(PosnrFachDTO dto, String request);

    /**
     * saveListPosnrFach(PosnrFachDTO dto)
     *
     * @return PosnrFachDTO
     */
    public List<PosnrFachDTO> savePosnrFachList(List<PosnrFachDTO> dtoList, String request);

    /**
     * deletePosnrFach(Serializable id)
     */
    public void deletePosnrFach(Serializable id, String request);

    /**
     * deletePosnrFach(PosnrFachDTO dto)
     */
    public void deletePosnrFach(PosnrFachDTO bo, String request);

    /**
     * findPosnrFachById(Serializable id)
     *
     * @return PosnrFachDTO
     */
    public PosnrFachDTO findPosnrFachById(Serializable id, String request);

    /**
     * saveTarifwert(TarifwertDTO dto)
     *
     * @return TarifwertDTO
     */
    public TarifwertDTO saveTarifwert(TarifwertDTO dto, String request);

    /**
     * saveListTarifwert(TarifwertDTO dto)
     *
     * @return TarifwertDTO
     */
    public List<TarifwertDTO> saveTarifwertList(List<TarifwertDTO> dtoList, String request);

    /**
     * deleteTarifwert(Serializable id)
     */
    public void deleteTarifwert(Serializable id, String request);

    /**
     * deleteTarifwert(TarifwertDTO dto)
     */
    public void deleteTarifwert(TarifwertDTO bo, String request);

    /**
     * findTarifwertById(Serializable id)
     *
     * @return TarifwertDTO
     */
    public TarifwertDTO findTarifwertById(Serializable id, String request);

    /**
     * saveFKtoBh(FKtoBhDTO dto)
     *
     * @return FKtoBhDTO
     */
    public FKtoBhDTO saveFKtoBh(FKtoBhDTO dto, String request);

    /**
     * saveListFKtoBh(FKtoBhDTO dto)
     *
     * @return FKtoBhDTO
     */
    public List<FKtoBhDTO> saveFKtoBhList(List<FKtoBhDTO> dtoList, String request);

    /**
     * deleteFKtoBh(Serializable id)
     */
    public void deleteFKtoBh(Serializable id, String request);

    /**
     * deleteFKtoBh(FKtoBhDTO dto)
     */
    public void deleteFKtoBh(FKtoBhDTO bo, String request);

    /**
     * findFKtoBhById(Serializable id)
     *
     * @return FKtoBhDTO
     */
    public FKtoBhDTO findFKtoBhById(Serializable id, String request);

    /**
     * savePositionLeistungssparte(PositionLeistungssparteDTO dto)
     *
     * @return PositionLeistungssparteDTO
     */
    public PositionLeistungssparteDTO savePositionLeistungssparte(PositionLeistungssparteDTO dto, String request);

    /**
     * saveListPositionLeistungssparte(PositionLeistungssparteDTO dto)
     *
     * @return PositionLeistungssparteDTO
     */
    public List<PositionLeistungssparteDTO> savePositionLeistungssparteList(List<PositionLeistungssparteDTO> dtoList,
            String request);

    /**
     * deletePositionLeistungssparte(Serializable id)
     */
    public void deletePositionLeistungssparte(Serializable id, String request);

    /**
     * deletePositionLeistungssparte(PositionLeistungssparteDTO dto)
     */
    public void deletePositionLeistungssparte(PositionLeistungssparteDTO bo, String request);

    /**
     * findPositionLeistungssparteById(Serializable id)
     *
     * @return PositionLeistungssparteDTO
     */
    public PositionLeistungssparteDTO findPositionLeistungssparteById(Serializable id, String request);

    /**
     * saveVerordnungsreferenz(VerordnungsreferenzDTO dto)
     *
     * @return VerordnungsreferenzDTO
     */
    public VerordnungsreferenzDTO saveVerordnungsreferenz(VerordnungsreferenzDTO dto, String request);

    /**
     * saveListVerordnungsreferenz(VerordnungsreferenzDTO dto)
     *
     * @return VerordnungsreferenzDTO
     */
    public List<VerordnungsreferenzDTO> saveVerordnungsreferenzList(List<VerordnungsreferenzDTO> dtoList,
            String request);

    /**
     * deleteVerordnungsreferenz(Serializable id)
     */
    public void deleteVerordnungsreferenz(Serializable id, String request);

    /**
     * deleteVerordnungsreferenz(VerordnungsreferenzDTO dto)
     */
    public void deleteVerordnungsreferenz(VerordnungsreferenzDTO bo, String request);

    /**
     * findVerordnungsreferenzById(Serializable id)
     *
     * @return VerordnungsreferenzDTO
     */
    public VerordnungsreferenzDTO findVerordnungsreferenzById(Serializable id, String request);

    /**
     * saveFPosnrVp(FPosnrVpDTO dto)
     *
     * @return FPosnrVpDTO
     */
    public FPosnrVpDTO saveFPosnrVp(FPosnrVpDTO dto, String request);

    /**
     * saveListFPosnrVp(FPosnrVpDTO dto)
     *
     * @return FPosnrVpDTO
     */
    public List<FPosnrVpDTO> saveFPosnrVpList(List<FPosnrVpDTO> dtoList, String request);

    /**
     * deleteFPosnrVp(Serializable id)
     */
    public void deleteFPosnrVp(Serializable id, String request);

    /**
     * deleteFPosnrVp(FPosnrVpDTO dto)
     */
    public void deleteFPosnrVp(FPosnrVpDTO bo, String request);

    /**
     * findFPosnrVpById(Serializable id)
     *
     * @return FPosnrVpDTO
     */
    public FPosnrVpDTO findFPosnrVpById(Serializable id, String request);

    /**
     * saveFBinfKap(FBinfKapDTO dto)
     *
     * @return FBinfKapDTO
     */
    public FBinfKapDTO saveFBinfKap(FBinfKapDTO dto, String request);

    /**
     * saveListFBinfKap(FBinfKapDTO dto)
     *
     * @return FBinfKapDTO
     */
    public List<FBinfKapDTO> saveFBinfKapList(List<FBinfKapDTO> dtoList, String request);

    /**
     * deleteFBinfKap(Serializable id)
     */
    public void deleteFBinfKap(Serializable id, String request);

    /**
     * deleteFBinfKap(FBinfKapDTO dto)
     */
    public void deleteFBinfKap(FBinfKapDTO bo, String request);

    /**
     * findFBinfKapById(Serializable id)
     *
     * @return FBinfKapDTO
     */
    public FBinfKapDTO findFBinfKapById(Serializable id, String request);

    /**
     * saveFBinfAbschnitt(FBinfAbschnittDTO dto)
     *
     * @return FBinfAbschnittDTO
     */
    public FBinfAbschnittDTO saveFBinfAbschnitt(FBinfAbschnittDTO dto, String request);

    /**
     * saveListFBinfAbschnitt(FBinfAbschnittDTO dto)
     *
     * @return FBinfAbschnittDTO
     */
    public List<FBinfAbschnittDTO> saveFBinfAbschnittList(List<FBinfAbschnittDTO> dtoList, String request);

    /**
     * deleteFBinfAbschnitt(Serializable id)
     */
    public void deleteFBinfAbschnitt(Serializable id, String request);

    /**
     * deleteFBinfAbschnitt(FBinfAbschnittDTO dto)
     */
    public void deleteFBinfAbschnitt(FBinfAbschnittDTO bo, String request);

    /**
     * deleteFBinfAbschnitt(FBinfAbschnittDTO dto)
     */
    public void deletePositionBundesland(PositionBundeslandDTO dto, String request);

    /**
     * findFBinfAbschnittById(Serializable id)
     *
     * @return FBinfAbschnittDTO
     */
    public FBinfAbschnittDTO findFBinfAbschnittById(Serializable id, String request);

    /**
     * saveLeistungspfad(LeistungspfadDTO dto)
     *
     * @return LeistungspfadDTO
     */
    public LeistungspfadDTO saveLeistungspfad(LeistungspfadDTO dto, String request);

    /**
     * saveListLeistungspfad(LeistungspfadDTO dto)
     *
     * @return LeistungspfadDTO
     */
    public List<LeistungspfadDTO> saveLeistungspfadList(List<LeistungspfadDTO> dtoList, String request);

    /**
     * deleteLeistungspfad(Serializable id)
     */
    public void deleteLeistungspfad(Serializable id, String request);

    /**
     * deleteLeistungspfad(LeistungspfadDTO dto)
     */
    public void deleteLeistungspfad(LeistungspfadDTO bo, String request);

    /**
     * findLeistungspfadById(Serializable id)
     *
     * @return LeistungspfadDTO
     */
    public LeistungspfadDTO findLeistungspfadById(Serializable id, String request);

    /**
     * savePosnrSbtext(PosnrSbtextDTO dto)
     *
     * @return PosnrSbtextDTO
     */
    public PosnrSbtextDTO savePosnrSbtext(PosnrSbtextDTO dto, String request);

    /**
     * saveListPosnrSbtext(PosnrSbtextDTO dto)
     *
     * @return PosnrSbtextDTO
     */
    public List<PosnrSbtextDTO> savePosnrSbtextList(List<PosnrSbtextDTO> dtoList, String request);

    /**
     * deletePosnrSbtext(Serializable id)
     */
    public void deletePosnrSbtext(Serializable id, String request);

    /**
     * deletePosnrSbtext(PosnrSbtextDTO dto)
     */
    public void deletePosnrSbtext(PosnrSbtextDTO bo, String request);

    /**
     * findPosnrSbtextById(Serializable id)
     *
     * @return PosnrSbtextDTO
     */
    public PosnrSbtextDTO findPosnrSbtextById(Serializable id, String request);

    /**
     * savePosVerordnungsreferenz(PosVerordnungsreferenzDTO dto)
     *
     * @return PosVerordnungsreferenzDTO
     */
    public PosVerordnungsreferenzDTO savePosVerordnungsreferenz(PosVerordnungsreferenzDTO dto, String request);

    /**
     * savePositionBundesland(PositionBundeslandDTO dto)
     *
     * @return PositionBundeslandDTO
     */
    public PositionBundeslandDTO savePositionBundesland(PositionBundeslandDTO dto, String request);

    /**
     * saveListPosVerordnungsreferenz(PosVerordnungsreferenzDTO dto)
     *
     * @return PosVerordnungsreferenzDTO
     */
    public List<PosVerordnungsreferenzDTO> savePosVerordnungsreferenzList(List<PosVerordnungsreferenzDTO> dtoList,
            String request);

    /**
     * deletePosVerordnungsreferenz(Serializable id)
     */
    public void deletePosVerordnungsreferenz(Serializable id, String request);

    /**
     * deletePosVerordnungsreferenz(PosVerordnungsreferenzDTO dto)
     */
    public void deletePosVerordnungsreferenz(PosVerordnungsreferenzDTO bo, String request);

    /**
     * findPosVerordnungsreferenzById(Serializable id)
     *
     * @return PosVerordnungsreferenzDTO
     */
    public PosVerordnungsreferenzDTO findPosVerordnungsreferenzById(Serializable id, String request);

    /**
     * savePosnrSynonym(PosnrSynonymDTO dto)
     *
     * @return PosnrSynonymDTO
     */
    public PosnrSynonymDTO savePosnrSynonym(PosnrSynonymDTO dto, String request);

    /**
     * saveListPosnrSynonym(PosnrSynonymDTO dto)
     *
     * @return PosnrSynonymDTO
     */
    public List<PosnrSynonymDTO> savePosnrSynonymList(List<PosnrSynonymDTO> dtoList, String request);

    /**
     * deletePosnrSynonym(Serializable id)
     */
    public void deletePosnrSynonym(Serializable id, String request);

    /**
     * deletePosnrSynonym(PosnrSynonymDTO dto)
     */
    public void deletePosnrSynonym(PosnrSynonymDTO bo, String request);

    /**
     * findPosnrSynonymById(Serializable id)
     *
     * @return PosnrSynonymDTO
     */
    public PosnrSynonymDTO findPosnrSynonymById(Serializable id, String request);

    /**
     * savePosnrText(PosnrTextDTO dto)
     *
     * @return PosnrTextDTO
     */
    public PosnrTextDTO savePosnrText(PosnrTextDTO dto, String request);

    /**
     * saveListPosnrText(PosnrTextDTO dto)
     *
     * @return PosnrTextDTO
     */
    public List<PosnrTextDTO> savePosnrTextList(List<PosnrTextDTO> dtoList, String request);

    /**
     * deletePosnrText(Serializable id)
     */
    public void deletePosnrText(Serializable id, String request);

    /**
     * deletePosnrText(PosnrTextDTO dto)
     */
    public void deletePosnrText(PosnrTextDTO bo, String request);

    /**
     * findPosnrTextById(Serializable id)
     *
     * @return PosnrTextDTO
     */
    public PosnrTextDTO findPosnrTextById(Serializable id, String request);

    /**
     * saveFBinfSubkap(FBinfSubkapDTO dto)
     *
     * @return FBinfSubkapDTO
     */
    public FBinfSubkapDTO saveFBinfSubkap(FBinfSubkapDTO dto, String request);

    /**
     * saveListFBinfSubkap(FBinfSubkapDTO dto)
     *
     * @return FBinfSubkapDTO
     */
    public List<FBinfSubkapDTO> saveFBinfSubkapList(List<FBinfSubkapDTO> dtoList, String request);

    /**
     * deleteFBinfSubkap(Serializable id)
     */
    public void deleteFBinfSubkap(Serializable id, String request);

    /**
     * deleteFBinfSubkap(FBinfSubkapDTO dto)
     */
    public void deleteFBinfSubkap(FBinfSubkapDTO bo, String request);

    /**
     * findFBinfSubkapById(Serializable id)
     *
     * @return FBinfSubkapDTO
     */
    public FBinfSubkapDTO findFBinfSubkapById(Serializable id, String request);

    /**
     * savePosnrFoko(PosnrFokoDTO dto)
     *
     * @return PosnrFokoDTO
     */
    public PosnrFokoDTO savePosnrFoko(PosnrFokoDTO dto, String request);

    /**
     * saveListPosnrFoko(PosnrFokoDTO dto)
     *
     * @return PosnrFokoDTO
     */
    public List<PosnrFokoDTO> savePosnrFokoList(List<PosnrFokoDTO> dtoList, String request);

    /**
     * deletePosnrFoko(Serializable id)
     */
    public void deletePosnrFoko(Serializable id, String request);

    /**
     * deletePosnrFoko(PosnrFokoDTO dto)
     */
    public void deletePosnrFoko(PosnrFokoDTO bo, String request);

    /**
     * findPosnrFokoById(Serializable id)
     *
     * @return PosnrFokoDTO
     */
    public PosnrFokoDTO findPosnrFokoById(Serializable id, String request);
}
