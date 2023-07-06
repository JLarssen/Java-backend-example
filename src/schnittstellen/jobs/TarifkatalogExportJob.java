package schnittstellen.jobs;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Konfiguration;
import beans.KonfigurationEinlesen;
import beans.dto.TarifwertDTO;
import db.IPersistenceService;
import schnittstellen.ImportExportJobLogik;
import sv.utils.DateiHandler;
import sv.utils.ExceptionHandler;
import sv.utils.Helper;
import sv.utils.JPAUtil;
import sv.utils.KommandozeilenParameter;
import sv.utils.beans.DaoRueckgabeDTO;
import sv.utils.beans.KonfigurationBasic;
import sv.utils.beans.ProtokollDTO;
import sv.utils.beans.PruefeAusgabeDatei;
import util.PerformanceAnalyse;
import sv.utils.TypConverter;
import constants.Constants_Feldindex;
import constants.Constants_Jobs;
import constants.Constants_Werte;
import constants.Constants_Allgemein;
import constants.Constants_Parameter;
import constants.Constants_Protokoll;

/**
 * @author larsju
 *
 */
public class TarifkatalogExportJob {
	private static Logger log = LoggerFactory.getLogger(TarifkatalogExportJob.class);

	private static String[] bezeichnungen = new String[] { "TarifkatalogExport()", "exportiereTarifKatalog()" };

	private static long[] zaehler = new long[bezeichnungen.length];

	private static long[] laufzeiten = new long[bezeichnungen.length];

	private static TypConverter typConverter = new TypConverter();

	private final static int INDEX_MAIN = 0;

	private final static int INDEX_EXPORT_CSV = 1;

	private final static ExceptionHandler exceptionHandler = new ExceptionHandler();

	private final Helper helper;

	private final ImportExportJobLogik importExportJobLogik;

	private final IPersistenceService persistenceService;

	/**
	 *
	 * Default-Konstruktor
	 *
	 * @param persistenceService IPersistenceService
	 */
	public TarifkatalogExportJob(final IPersistenceService persistenceService) {
		this.helper = new Helper();
		this.persistenceService = persistenceService;
		this.importExportJobLogik = new ImportExportJobLogik();
	}

	/**
	 * Kommandozeilenparameter fuer ExportTarifkatalogJob
	 *
	 * -ausgabeDatei -
	 */
	public static void main(final String[] args) {
		log.info("START des Job - TarifkatalogExportJob");
		(zaehler[INDEX_MAIN])++;
		final long jetzt = System.currentTimeMillis();
		final Helper helper = new Helper();
		final String request = helper.ausgabeKommandozeilenparameter(args);
		try (ClassPathXmlApplicationContext ctxt = new ClassPathXmlApplicationContext(
				KonfigurationBasic.getApplicationContextStandalone())) {
			util.PerformanceAnalyse.initLetzteAnalyseZeitpunkt();
			final KommandozeilenParameter clp = new KommandozeilenParameter();
			final CommandLine commandLine = clp.getKommandozeileParameter(args, Constants_Jobs.JOB_TARIF_EXPORT_CSV);
			if (commandLine != null) {
				final IPersistenceService persistenceService = Konfiguration.getPersistenceService();
				new KonfigurationEinlesen().allesNeuEinlesenUndVerarbeiten(request);
				final TarifkatalogExportJob tarifExportJob = new TarifkatalogExportJob(persistenceService);
				final String ausgabeDatei = typConverter
						.getNotNullString(commandLine.getOptionValue(Constants_Parameter.AUSGABE_DATEI));
				final PruefeAusgabeDatei pruefeAusgabeDatei = new PruefeAusgabeDatei(ausgabeDatei);
				if (pruefeAusgabeDatei.pruefeParameter(request)) {
					final ProtokollDTO protokollDTO = tarifExportJob.exportiereTarifKatalog(ausgabeDatei, request);
					try {
						new PerformanceAnalyse().erstelleAnalyse(false, request);
					} catch (final Exception e) {
						log.error(exceptionHandler.getErrorMeldung(e, request));
					}
					if (protokollDTO != null) {
						log.info(protokollDTO.protokollAnzeigen());
					}
				} else {
					log.info(
							"AusgabeDatei ist bereits vorhanden --> TarifkatalogExportJob wird abgebrochen. Bitte wenden Sie sich an Ihren Systemadministrator! ");
				}
			}
		} catch (final Exception e) {
			log.error(exceptionHandler.getErrorMeldung(e, request));
		}
		final long dauer = System.currentTimeMillis() - jetzt;
		(laufzeiten[INDEX_MAIN]) += dauer;
		log.info("TarifkatalogExportJob wurde durchgefuehrt, Dauer: " + new TypConverter().getNotNullString(dauer, true)
				+ " Millisekunde(n)");
		log.info("ENDE des Job - TarifkatalogExportJob");
		try {
			JPAUtil.shutdown();
		} catch (final Exception e) {
			log.error(exceptionHandler.getErrorMeldung(e, request));
		}
		System.exit(0);
	}

	/**
	 * @param ausgabeDatei Name der .csv Datei
	 * @param request      String
	 * @return ProtokollDTO
	 */
	private ProtokollDTO exportiereTarifKatalog(final String ausgabeDatei, final String request) {
		(zaehler[INDEX_EXPORT_CSV])++;
		final long jetzt = System.currentTimeMillis();
		final ProtokollDTO protokollDTO = new ProtokollDTO();
		try {
			final DateiHandler dateiHandler = new DateiHandler();
			dateiHandler.oeffneDateiZumSchreiben(ausgabeDatei, false, Constants_Allgemein.ENCODING_EXCEL,
					Constants_Werte.EXPORT_CSV_APPEND, request);
			dateiHandler.schreibeInDatei(this.importExportJobLogik.getTabellenheader(), request);
			// der naechste Wert hat rein informativen Charakter und ist nicht funktionell
			// erforderlich
			final int anzahl_posnr = this.persistenceService.findAnzahlPosnrsInDB(request);
			log.debug("Anzahl der Posnr. in der TarifDB: " + anzahl_posnr);
			int datensatz_zaehler = 0;
			while (datensatz_zaehler <= anzahl_posnr) {
				final DaoRueckgabeDTO daoRueckgabeDTO = this.persistenceService.findAllPosnrLtd(datensatz_zaehler,
						Constants_Werte.CACHING_SCHRITTE_DB_TARIFE_AUSLESEN, request);
				if (daoRueckgabeDTO != null) {
					final List<Object[]> resultList = daoRueckgabeDTO.getList();
					protokollDTO.plus(Constants_Protokoll.ANZAHL_GELESEN + " (Datensaetze zu Pos.nummern): ",
							resultList.size());
					if ((daoRueckgabeDTO.getIndex(Constants_Feldindex.POSITION_ID) != null)
							&& (daoRueckgabeDTO.getIndex(Constants_Feldindex.POSNR) != null)
							&& (daoRueckgabeDTO.getIndex(Constants_Feldindex.NOTESKTXT) != null)) {
						for (final Object[] oList : resultList) {
							final String posnr_aktuell = (String) oList[daoRueckgabeDTO
									.getIndex(Constants_Feldindex.POSNR).intValue()];
							final Long position_id_aktuell = (Long) oList[daoRueckgabeDTO
									.getIndex(Constants_Feldindex.POSITION_ID).intValue()];
							final String notesktxt_aktuell = (String) oList[daoRueckgabeDTO
									.getIndex(Constants_Feldindex.NOTESKTXT).intValue()];
							if (!this.helper.isEmptyString(posnr_aktuell)) {
								log.debug("geholte Posnr " + posnr_aktuell);
								List<TarifwertDTO> tarifwertZeilen = null;
								// Es werden 200 Tarife nicht geholt, weil diese kein offenes datum_bis = null
								// haben.
								tarifwertZeilen = this.persistenceService
										.findTarifwertDTOByPosition_idDatumBis(position_id_aktuell, request);
								if (tarifwertZeilen != null) {
									for (final TarifwertDTO tarifwertZeile : tarifwertZeilen) {
										// das folgende logische Kriterium ist nicht meine Idee und gefällt mir nicht
										// --> siehe meine V2 Version mit anderem Algorithmus
										if (tarifwertZeilen.size() >= 3) {
											final String tempStr = this.importExportJobLogik
													.setTabellenzeile(posnr_aktuell, notesktxt_aktuell, tarifwertZeile);
											dateiHandler.schreibeInDatei(tempStr, request);
											protokollDTO.plus(Constants_Protokoll.ANZAHL_GESCHRIEBEN
													+ " (aktuelle Tarifzeilen in .csv-Datei): ");
										}
									} // Ende Schleife fuer Tarifzeilen
								}
							} else {
								log.error(
										"Die erwartete Anzahl Datensaetze stimmt nicht mit dem Leseversuch ueberein. ");
							}
						}
					}
				}
				datensatz_zaehler += Constants_Werte.CACHING_SCHRITTE_DB_TARIFE_AUSLESEN;
			} // while-Schleife fuer alle Posnr
			dateiHandler.schliesseDatei(request);
		} catch (final Exception e) {
			log.error("Etwas ist schief gelaufen beim Tarif-Export...  "
					+ TarifkatalogExportJob.exceptionHandler.getErrorMeldung(e, request));
		}
		laufzeiten[INDEX_EXPORT_CSV] += (System.currentTimeMillis() - jetzt);
		return (protokollDTO);
	}
}
