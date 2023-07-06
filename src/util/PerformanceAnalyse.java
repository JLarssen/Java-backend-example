package util;

import controller.BearbeiterController;
import controller.ImportKapitelController;
import controller.ImportPosNrController;
import controller.LogoutController;
import controller.SelectItemController;
import controller.TarifInformationenAnzeigenController;
import controller.TarifeZuFachgebietAnzeigenController;
import controller.TarifkatalogController;
import controller.ValueListController;
import db.BevorzugteTarifeDAO;
import db.BuchhaltungDAO;
import db.FBinfAbschnittDAO;
import db.FBinfKapDAO;
import db.FBinfSubkapDAO;
import db.FKtoBhDAO;
import db.FPosnrJstatDAO;
import db.FPosnrVpDAO;
import db.LeistungspfadDAO;
import db.PosVerordnungsreferenzDAO;
import db.PositionBundeslandDAO;
import db.PositionLeistungssparteDAO;
import db.PosnrBinfDAO;
import db.PosnrFachDAO;
import db.PosnrFokoDAO;
import db.PosnrSbtextDAO;
import db.PosnrSynonymDAO;
import db.PosnrTextDAO;
import db.PosnrWokeDAO;
import db.TarifwertDAO;
import db.VerordnungsreferenzDAO;
import db.dao.hibernate.HibernateBevorzugteTarifeDAO;
import db.dao.hibernate.HibernateBuchhaltungDAO;
import db.dao.hibernate.HibernateFBinfAbschnittDAO;
import db.dao.hibernate.HibernateFBinfKapDAO;
import db.dao.hibernate.HibernateFBinfSubkapDAO;
import db.dao.hibernate.HibernateFKtoBhDAO;
import db.dao.hibernate.HibernateFPosnrJstatDAO;
import db.dao.hibernate.HibernateFPosnrVpDAO;
import db.dao.hibernate.HibernateLeistungspfadDAO;
import db.dao.hibernate.HibernatePosVerordnungsreferenzDAO;
import db.dao.hibernate.HibernatePositionLeistungssparteDAO;
import db.dao.hibernate.HibernatePosnrBinfDAO;
import db.dao.hibernate.HibernatePosnrFachDAO;
import db.dao.hibernate.HibernatePosnrFokoDAO;
import db.dao.hibernate.HibernatePosnrSbtextDAO;
import db.dao.hibernate.HibernatePosnrSynonymDAO;
import db.dao.hibernate.HibernatePosnrTextDAO;
import db.dao.hibernate.HibernatePosnrWokeDAO;
import db.dao.hibernate.HibernateTarifwertDAO;
import db.dao.hibernate.HibernateVerordnungsreferenzDAO;
import logic.KapitelImportLogic;
import logic.LogikTarifkatalog;
import logic.indizierung.IndexErsteller;
import logic.indizierung.IndexVerwalter;
import logic.validierung.ImportKapitelValidator;
import logic.validierung.ImportPosnrValidator;
import schnittstellen.FokoDatenVorbereiten;
import schnittstellen.TarifPosnrImportieren;
import schnittstellen.TarifPosnrTexteVorbereiten;
import schnittstellen.TarifkatalogExport;
import .schnittstellen.rest.EbeneLoeschenService;
import schnittstellen.rest.KapitelLadenService;
import schnittstellen.rest.KatalogLadenService;
import schnittstellen.rest.SbAnmerkungenLoeschenService;
import schnittstellen.rest.SbAnmerkungenSpeichernService;
import schnittstellen.rest.SubKapitelLadenService;
import schnittstellen.rest.SynonymeSpeichernService;
import schnittstellen.rest.TarifSucheService;
import schnittstellen.rest.TarifeLadenService;
import schnittstellen.rest.UpdateConvTimeoutService;
import schnittstellen.soap.TarifkatalogServicePortImpl;
import sv.utils.db.AbstractHibernateDAO;
import sv.utils.db.AbstractHibernateJpaDAO;

/**
 * 
 */
public class PerformanceAnalyse extends sv.utils.beans.PerformanceAnalyseBasic {
    /**
     *
     * Default-Konstruktor
     *
     */
    public PerformanceAnalyse() {
        super();
    }

    /**
     * setzt alle Zaehler auf 0 zurueck
     */
    @Override
    protected void resetWerte() {
        super.resetWerte();
        // DAO's
        AbstractHibernateDAO.abstractResetWerte();
        AbstractHibernateJpaDAO.abstractResetWerte();
        PositionBundeslandDAO.resetWerte();
        HibernateBevorzugteTarifeDAO.resetWerte();
        HibernateBuchhaltungDAO.resetWerte();
        HibernateFBinfAbschnittDAO.resetWerte();
        HibernateFBinfKapDAO.resetWerte();
        HibernateFBinfSubkapDAO.resetWerte();
        HibernateFKtoBhDAO.resetWerte();
        HibernateFPosnrJstatDAO.resetWerte();
        HibernateFPosnrVpDAO.resetWerte();
        HibernateLeistungspfadDAO.resetWerte();
        HibernatePositionLeistungssparteDAO.resetWerte();
        HibernatePosnrBinfDAO.resetWerte();
        HibernatePosnrFachDAO.resetWerte();
        HibernatePosnrFokoDAO.resetWerte();
        HibernatePosnrSbtextDAO.resetWerte();
        HibernatePosnrSynonymDAO.resetWerte();
        HibernatePosnrTextDAO.resetWerte();
        HibernatePosnrWokeDAO.resetWerte();
        HibernatePosVerordnungsreferenzDAO.resetWerte();
        HibernateTarifwertDAO.resetWerte();
        HibernateVerordnungsreferenzDAO.resetWerte();
        // DAO's - JPA
        AbstractHibernateJpaDAO.abstractResetWerte();
        BevorzugteTarifeDAO.resetWerte();
        BuchhaltungDAO.resetWerte();
        FBinfAbschnittDAO.resetWerte();
        FBinfKapDAO.resetWerte();
        FBinfSubkapDAO.resetWerte();
        FKtoBhDAO.resetWerte();
        FPosnrJstatDAO.resetWerte();
        FPosnrVpDAO.resetWerte();
        LeistungspfadDAO.resetWerte();
        PositionLeistungssparteDAO.resetWerte();
        PosnrBinfDAO.resetWerte();
        PosnrFachDAO.resetWerte();
        PosnrFokoDAO.resetWerte();
        PosnrSbtextDAO.resetWerte();
        PosnrSynonymDAO.resetWerte();
        PosnrTextDAO.resetWerte();
        PosnrWokeDAO.resetWerte();
        PosVerordnungsreferenzDAO.resetWerte();
        TarifwertDAO.resetWerte();
        VerordnungsreferenzDAO.resetWerte();
        // Actions, Controller
        BearbeiterController.resetWerte();
        ImportKapitelController.resetWerte();
        ImportPosNrController.resetWerte();
        LogoutController.resetWerte();
        SelectItemController.resetWerte();
        TarifeZuFachgebietAnzeigenController.resetWerte();
        TarifInformationenAnzeigenController.resetWerte();
        TarifkatalogController.resetWerte();
        ValueListController.resetWerte();
        // Services
        EbeneLoeschenService.resetWerte();
        KapitelLadenService.resetWerte();
        KatalogLadenService.resetWerte();
        SbAnmerkungenLoeschenService.resetWerte();
        SbAnmerkungenSpeichernService.resetWerte();
        SubKapitelLadenService.resetWerte();
        SynonymeSpeichernService.resetWerte();
        TarifeLadenService.resetWerte();
        TarifSucheService.resetWerte();
        UpdateConvTimeoutService.resetWerte();
        TarifkatalogServicePortImpl.resetWerte();
        // Sonstiges
        IndexErsteller.resetWerte();
        IndexVerwalter.resetWerte();
        ImportKapitelValidator.resetWerte();
        ImportPosnrValidator.resetWerte();
        KapitelImportLogic.resetWerte();
        LogikTarifkatalog.resetWerte();
        FokoDatenVorbereiten.resetWerte();
        TarifkatalogExport.resetWerte();
        TarifPosnrImportieren.resetWerte();
        TarifPosnrTexteVorbereiten.resetWerte();
    }

    /**
     * fasst die Performance-Analysen aller relevanten Klassen zu einem String
     * zusammen
     */
    @Override
    protected String erstelleAnalyse() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.erstelleAnalyse());
        // DAO's
        sb.append(AbstractHibernateDAO.abstractPerformanceAnalyse());
        sb.append(AbstractHibernateJpaDAO.abstractPerformanceAnalyse());
        sb.append(PositionBundeslandDAO.performanceAnalyse());
        sb.append(HibernateBevorzugteTarifeDAO.performanceAnalyse());
        sb.append(HibernateBuchhaltungDAO.performanceAnalyse());
        sb.append(HibernateFBinfAbschnittDAO.performanceAnalyse());
        sb.append(HibernateFBinfKapDAO.performanceAnalyse());
        sb.append(HibernateFBinfSubkapDAO.performanceAnalyse());
        sb.append(HibernateFKtoBhDAO.performanceAnalyse());
        sb.append(HibernateFPosnrJstatDAO.performanceAnalyse());
        sb.append(HibernateFPosnrVpDAO.performanceAnalyse());
        sb.append(HibernateLeistungspfadDAO.performanceAnalyse());
        sb.append(HibernatePositionLeistungssparteDAO.performanceAnalyse());
        sb.append(HibernatePosnrBinfDAO.performanceAnalyse());
        sb.append(HibernatePosnrFachDAO.performanceAnalyse());
        sb.append(HibernatePosnrFokoDAO.performanceAnalyse());
        sb.append(HibernatePosnrSbtextDAO.performanceAnalyse());
        sb.append(HibernatePosnrSynonymDAO.performanceAnalyse());
        sb.append(HibernatePosnrTextDAO.performanceAnalyse());
        sb.append(HibernatePosnrWokeDAO.performanceAnalyse());
        sb.append(HibernatePosVerordnungsreferenzDAO.performanceAnalyse());
        sb.append(HibernateTarifwertDAO.performanceAnalyse());
        sb.append(HibernateVerordnungsreferenzDAO.performanceAnalyse());
        // DAO's - JPA
        sb.append(AbstractHibernateJpaDAO.abstractPerformanceAnalyse());
        sb.append(BevorzugteTarifeDAO.performanceAnalyse());
        sb.append(BuchhaltungDAO.performanceAnalyse());
        sb.append(FBinfAbschnittDAO.performanceAnalyse());
        sb.append(FBinfKapDAO.performanceAnalyse());
        sb.append(FBinfSubkapDAO.performanceAnalyse());
        sb.append(FKtoBhDAO.performanceAnalyse());
        sb.append(FPosnrJstatDAO.performanceAnalyse());
        sb.append(FPosnrVpDAO.performanceAnalyse());
        sb.append(LeistungspfadDAO.performanceAnalyse());
        sb.append(PositionLeistungssparteDAO.performanceAnalyse());
        sb.append(PosnrBinfDAO.performanceAnalyse());
        sb.append(PosnrFachDAO.performanceAnalyse());
        sb.append(PosnrFokoDAO.performanceAnalyse());
        sb.append(PosnrSbtextDAO.performanceAnalyse());
        sb.append(PosnrSynonymDAO.performanceAnalyse());
        sb.append(PosnrTextDAO.performanceAnalyse());
        sb.append(PosnrWokeDAO.performanceAnalyse());
        sb.append(PosVerordnungsreferenzDAO.performanceAnalyse());
        sb.append(TarifwertDAO.performanceAnalyse());
        sb.append(VerordnungsreferenzDAO.performanceAnalyse());
        // Actions, Controller
        sb.append(BearbeiterController.performanceAnalyse());
        sb.append(ImportKapitelController.performanceAnalyse());
        sb.append(ImportPosNrController.performanceAnalyse());
        sb.append(LogoutController.performanceAnalyse());
        sb.append(SelectItemController.performanceAnalyse());
        sb.append(TarifeZuFachgebietAnzeigenController.performanceAnalyse());
        sb.append(TarifInformationenAnzeigenController.performanceAnalyse());
        sb.append(TarifkatalogController.performanceAnalyse());
        sb.append(ValueListController.performanceAnalyse());
        // Services
        sb.append(EbeneLoeschenService.performanceAnalyse());
        sb.append(KapitelLadenService.performanceAnalyse());
        sb.append(KatalogLadenService.performanceAnalyse());
        sb.append(SbAnmerkungenLoeschenService.performanceAnalyse());
        sb.append(SbAnmerkungenSpeichernService.performanceAnalyse());
        sb.append(SubKapitelLadenService.performanceAnalyse());
        sb.append(SynonymeSpeichernService.performanceAnalyse());
        sb.append(TarifeLadenService.performanceAnalyse());
        sb.append(TarifSucheService.performanceAnalyse());
        sb.append(UpdateConvTimeoutService.performanceAnalyse());
        sb.append(TarifkatalogServicePortImpl.performanceAnalyse());
        // Sonstiges
        sb.append(IndexErsteller.performanceAnalyse());
        sb.append(IndexVerwalter.performanceAnalyse());
        sb.append(ImportKapitelValidator.performanceAnalyse());
        sb.append(ImportPosnrValidator.performanceAnalyse());
        sb.append(KapitelImportLogic.performanceAnalyse());
        sb.append(LogikTarifkatalog.performanceAnalyse());
        sb.append(FokoDatenVorbereiten.performanceAnalyse());
        sb.append(TarifkatalogExport.performanceAnalyse());
        sb.append(TarifPosnrImportieren.performanceAnalyse());
        sb.append(TarifPosnrTexteVorbereiten.performanceAnalyse());
        return (sb.toString());
    }
}
