package it.altletasportjpamaven.test;

import java.time.LocalDate;
import java.util.List;

import it.altletasportjpamaven.dao.EntityManagerUtil;
import it.altletasportjpamaven.model.Atleta;
import it.altletasportjpamaven.model.Sport;
import it.altletasportjpamaven.service.AtletaService;
import it.altletasportjpamaven.service.MyServiceFactory;
import it.altletasportjpamaven.service.SportService;

public class TestAltletaSportJPAMaven {

	public static void main(String[] args) throws Exception {


		SportService sportServiceIstance = MyServiceFactory.getSportServiceInstance();
		AtletaService atletaServiceIstance = MyServiceFactory.getAtletaServiceInstance();

		try {

			initSport(sportServiceIstance);

//*****************INIZIO TEST SPORT****************************			

			System.out.println("Nella tabella sport ci sono " + sportServiceIstance.listAll().size() + " elementi");

//			testInsertSport(sportServiceIstance);
//			testDeleteSport(sportServiceIstance);
//			testUpdateSport(sportServiceIstance);
//			testTrovaSportConErrori(sportServiceIstance);
//			testSommaMedaglieDiAtletiConAlmenoUnoSportChiuso(atletaServiceIstance, sportServiceIstance);

			System.out.println("Nella tabella sport ci sono " + sportServiceIstance.listAll().size() + " elementi");

//*****************FINE TEST SPORT****************************	

//-------------------------------------------------------------------------------------------------------------------------------------

//*****************INIZIO TEST ATLETA**************************

			System.out.println("Nella tabella atleta ci sono " + atletaServiceIstance.listAll().size() + " elementi");

//			testInsertAtleta(atletaServiceIstance);
//			testUpdateAtleta(atletaServiceIstance);
//			testUnisciAtletaConSport(atletaServiceIstance, sportServiceIstance);
//			testDissasociaAtletaConSport(atletaServiceIstance, sportServiceIstance);
			TestRimuoviAtletaConSport(atletaServiceIstance, sportServiceIstance);

			System.out.println("Nella tabella atleta ci sono " + atletaServiceIstance.listAll().size() + " elementi");

//*****************FINE TEST ATLETA****************************

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	private static void initSport(SportService sportServiceIstance) throws Exception {

		if (sportServiceIstance.cercaPerDescrizione("Calcio") == null) {

			sportServiceIstance.inserisciNuovo(new Sport("Calcio", LocalDate.now(), null));
		}

		if (sportServiceIstance.cercaPerDescrizione("Nuoto") == null) {

			sportServiceIstance.inserisciNuovo(new Sport("Nuoto", LocalDate.now(), null));
		}

		if (sportServiceIstance.cercaPerDescrizione("Corsa") == null) {

			sportServiceIstance.inserisciNuovo(new Sport("Corsa", LocalDate.now(), null));
		}

		if (sportServiceIstance.cercaPerDescrizione("Pallavolo") == null) {

			sportServiceIstance.inserisciNuovo(new Sport("Pallavolo", LocalDate.now(), null));
		}
	}

//*******************************INIZIO TEST SPORT**********************************************	

	private static void testInsertSport(SportService sportServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testInsertSport************");

		Sport sport = new Sport("SportProva", LocalDate.now(), null);

		sportServiceIstance.inserisciNuovo(sport);

		if (sport.getId() == null) {

			throw new RuntimeException("****************TEST FAILED testInsertSport: Sport non aggiunto**********");

		}
		sportServiceIstance.rimuovi(sport.getId());

		System.out.println("***********FINE TEST testInsertSport: PASSED************");

	}

	private static void testUpdateSport(SportService sportServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testUpdateSport************");

		Sport sportUpdate = new Sport();

		sportServiceIstance.inserisciNuovo(sportUpdate);

		List<Sport> presentiSulDb = sportServiceIstance.listAll();

		Sport sport = presentiSulDb.get(presentiSulDb.size() - 1);

		sport.setDefinizione("Sport modificato");

		sportServiceIstance.aggiorna(sport);

		Sport sportModificato = presentiSulDb.get(presentiSulDb.size() - 1);

		if (!sportModificato.getDefinizione().equals(sportModificato.getDefinizione())) {

			throw new RuntimeException("****************TEST FAILED testUpdateSport: Sport non modificato**********");

		}

		sportServiceIstance.rimuovi(sport.getId());

		System.out.println("***********FINE TEST testUpdateSport: PASSED************");

	}

	private static void testTrovaSportConErrori(SportService sportServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testTrovaSportConErrori************");

		List<Sport> presentiSulDb = sportServiceIstance.listAll();

		Sport sport = new Sport("SportERR", LocalDate.now(), LocalDate.parse("1999-04-01"));

		sportServiceIstance.inserisciNuovo(sport);

		if (sport.getId() == null) {
			throw new RuntimeException(
					"****************TEST FAILED testTrovaSportConErrori: Sport non aggiunto**********");
		}

		List<Sport> presentiSulDbErrors = sportServiceIstance.trovaSportConErrori();

		if (presentiSulDbErrors.size() == 0) {

			throw new RuntimeException(
					"****************TEST FAILED testTrovaSportConErrori: errori non trovati**********");

		}

		for (Sport sportItem : presentiSulDbErrors) {

			sportServiceIstance.rimuovi(sportItem.getId());
		}

		System.out.println("***********FINE TEST testTrovaSportConErrori: PASSED************");

	}

//	private static void testDeleteSport(SportService sportServiceIstance) throws Exception {} TESTATO IN testInsertSport

//*********************************FINE TEST SPORT**************************************	
//-------------------------------------------------------------------------------------------------
//*********************************INIZIO TEST ATELTA**************************************	

	private static void testInsertAtleta(AtletaService atletaServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testInsertAtleta************");

		Atleta atleta = new Atleta("Mario", "Toscano", "MRTSCN01", 0, LocalDate.parse("1992-05-06"));

		atletaServiceIstance.inserisciNuovo(atleta);

		if (atleta.getId() == null) {

			throw new RuntimeException("****************TEST FAILED testInsertAtleta: Atleta non aggiunto**********");

		}
		atletaServiceIstance.rimuovi(atleta.getId());

		System.out.println("***********FINE TEST testInsertAtleta: PASSED************");

	}

	private static void testUpdateAtleta(AtletaService atletaServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testUpdateAtleta************");

		atletaServiceIstance
				.inserisciNuovo(new Atleta("Mario", "Toscano", "MRTSCN01", 0, LocalDate.parse("1992-05-06")));

		List<Atleta> presentiSulDb = atletaServiceIstance.listAll();

		Atleta atleta = presentiSulDb.get(presentiSulDb.size() - 1);

		String vecchioNome = atleta.getNome();

		atleta.setNome("Mimmo");

		atletaServiceIstance.aggiorna(atleta);

		if (atleta.getNome().equals(vecchioNome)) {

			throw new RuntimeException("****************TEST FAILED testUpdateAtleta: Atleta non aggiornato**********");

		}
		atletaServiceIstance.rimuovi(atleta.getId());

		System.out.println("***********FINE TEST testUpdateAtleta: PASSED************");

	}

//*********************************FINE TEST ATELTA**************************************	

	private static void testUnisciAtletaConSport(AtletaService atletaServiceIstance, SportService sportServiceIstance)
			throws Exception {

		System.out.println("***********INIZIO TEST testUnisciAtletaConSport************");

		Sport sportEsistenteSulDb = sportServiceIstance.cercaPerDescrizione("Calcio");

		if (sportEsistenteSulDb == null) {
			throw new RuntimeException(
					"****************TEST FAILED testUnisciAtletaConSport: Sport non trovato**********");
		}

		Atleta nuovoAtleta = new Atleta("Luigi", "Toscano", "LGTSCN01", 0, LocalDate.parse("1992-05-06"));

		atletaServiceIstance.inserisciNuovo(nuovoAtleta);

		if (nuovoAtleta.getId() == null) {

			throw new RuntimeException(
					"****************TEST FAILED testUnisciAtletaConSport: Atleta non inserito**********");

		}

		atletaServiceIstance.aggiungiSport(nuovoAtleta, sportEsistenteSulDb);

		Atleta atletaReloaded = atletaServiceIstance.caricaSingoloElementoConSport(nuovoAtleta.getId());
		if (atletaReloaded.getSports().size() < 1) {

			throw new RuntimeException(
					"****************TEST FAILED testUnisciAtletaConSport: Atleta e sport non collegati**********");

		}

		System.out.println("***********FINE TEST testUnisciAtletaConSport: PASSED************");

	}

	private static void testDissasociaAtletaConSport(AtletaService atletaServiceIstance,
			SportService sportServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testDissasociaAtletaConSport************");

		Sport sportEsistenteSulDb = sportServiceIstance.cercaPerDescrizione("Pallavolo");

		if (sportEsistenteSulDb == null) {
			throw new RuntimeException(
					"****************TEST FAILED testDissasociaAtletaConSport: Sport non trovato**********");
		}

		Atleta nuovoAtleta = new Atleta("Luigi", "Toscano", "LGTSCN01", 0, LocalDate.parse("1992-05-06"));

		atletaServiceIstance.inserisciNuovo(nuovoAtleta);

		if (nuovoAtleta.getId() == null) {

			throw new RuntimeException(
					"****************TEST FAILED testDissasociaAtletaConSport: Atleta non inserito**********");

		}

		atletaServiceIstance.aggiungiSport(nuovoAtleta, sportEsistenteSulDb);

		Atleta atletaReloaded = atletaServiceIstance.caricaSingoloElementoConSport(nuovoAtleta.getId());
		if (atletaReloaded.getSports().size() < 1) {

			throw new RuntimeException(
					"****************TEST FAILED testDissasociaAtletaConSport: Atleta e sport non collegati**********");

		}

		atletaServiceIstance.rimuoviSport(nuovoAtleta.getId(), sportEsistenteSulDb.getId());

		atletaReloaded = atletaServiceIstance.caricaSingoloElementoConSport(nuovoAtleta.getId());

		if (atletaReloaded.getSports().size() != 0) {

			throw new RuntimeException(
					"****************TEST FAILED testDissasociaAtletaConSport: Atleta e sport non scollegati**********");

		}

		System.out.println("***********FINE TEST testDissasociaAtletaConSport: PASSED************");

	}

	private static void testSommaMedaglieDiAtletiConAlmenoUnoSportChiuso(AtletaService atletaServiceIstance,
			SportService sportServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testSommaMedaglieDiAtletiConAlmenoUnoSportChiuso************");

		Sport sportEsistenteSulDb = sportServiceIstance.cercaPerDescrizione("Nuoto");

		if (sportEsistenteSulDb == null) {
			throw new RuntimeException(
					"****************TEST FAILED testSommaMedaglieDiAtletiConAlmenoUnoSportChiuso: Sport non trovato**********");
		}
		sportEsistenteSulDb.setDataFine(LocalDate.now());
		sportServiceIstance.aggiorna(sportEsistenteSulDb);

		// mi assicuro di liberare lo sport da eventuali atleti gia presenti cosi da
		// poter sapere certamente il numero di medaglie
		List<Atleta> presentiSulDb = atletaServiceIstance.listAll();
		for (Atleta atletaItem : presentiSulDb) {
			Atleta atletaTemp = atletaServiceIstance.caricaSingoloElementoConSport(atletaItem.getId());
			if (atletaTemp.getSports().contains(sportEsistenteSulDb)) {
				atletaServiceIstance.rimuoviSport(atletaItem.getId(), sportEsistenteSulDb.getId());
			}
		}

		Atleta nuovoAtleta = new Atleta("Luigi", "Toscano", "LGTSCN01", 10, LocalDate.parse("1992-05-06"));
		Atleta nuovoAtleta2 = new Atleta("Jamal", "Fuscio", "JMLFSC01", 10, LocalDate.parse("1992-05-06"));

		// int sommaMedaglie

		atletaServiceIstance.inserisciNuovo(nuovoAtleta);
		atletaServiceIstance.inserisciNuovo(nuovoAtleta2);

		if (nuovoAtleta.getId() == null || nuovoAtleta2.getId() == null) {

			throw new RuntimeException(
					"****************TEST FAILED testSommaMedaglieDiAtletiConAlmenoUnoSportChiuso: Atleti non inseriti**********");

		}

		atletaServiceIstance.aggiungiSport(nuovoAtleta, sportEsistenteSulDb);
		atletaServiceIstance.aggiungiSport(nuovoAtleta2, sportEsistenteSulDb);

//		Atleta atletaReloaded = atletaServiceIstance.caricaSingoloElementoConSport(nuovoAtleta.getId());
		int medaglieVeterani = sportServiceIstance.sommaMedaglieDiAtletiConAlmenoUnoSportChiuso();
		if (medaglieVeterani != 20) {

			throw new RuntimeException(
					"****************TEST FAILED testSommaMedaglieDiAtletiConAlmenoUnoSportChiuso: Medaglie non congruenti**********");

		}

		atletaServiceIstance.rimuoviSport(nuovoAtleta.getId(), sportEsistenteSulDb.getId());
		atletaServiceIstance.rimuoviSport(nuovoAtleta2.getId(), sportEsistenteSulDb.getId());
		atletaServiceIstance.rimuovi(nuovoAtleta.getId());
		atletaServiceIstance.rimuovi(nuovoAtleta2.getId());

		System.out.println("***********FINE TEST testSommaMedaglieDiAtletiConAlmenoUnoSportChiuso: PASSED************");

	}
	
	private static void TestRimuoviAtletaConSport(AtletaService atletaServiceIstance,
			SportService sportServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testDissasociaAtletaConSport************");

		Sport sportEsistenteSulDb = sportServiceIstance.cercaPerDescrizione("Pallavolo");

		if (sportEsistenteSulDb == null) {
			throw new RuntimeException(
					"****************TEST FAILED testDissasociaAtletaConSport: Sport non trovato**********");
		}

		Atleta nuovoAtleta = new Atleta("Luigi", "Toscano", "LGTSCN01", 0, LocalDate.parse("1992-05-06"));

		atletaServiceIstance.inserisciNuovo(nuovoAtleta);
		
		int presentiSulDbDopoInsert = atletaServiceIstance.listAll().size();

		if (nuovoAtleta.getId() == null) {

			throw new RuntimeException(
					"****************TEST FAILED testDissasociaAtletaConSport: Atleta non inserito**********");

		}

		atletaServiceIstance.aggiungiSport(nuovoAtleta, sportEsistenteSulDb);

		Atleta atletaReloaded = atletaServiceIstance.caricaSingoloElementoConSport(nuovoAtleta.getId());
		if (atletaReloaded.getSports().size() < 1) {

			throw new RuntimeException(
					"****************TEST FAILED testDissasociaAtletaConSport: Atleta e sport non collegati**********");

		}

//		atletaServiceIstance.rimuoviSport(nuovoAtleta.getId(), sportEsistenteSulDb.getId());

//		atletaReloaded = atletaServiceIstance.caricaSingoloElementoConSport(nuovoAtleta.getId());
		
		atletaServiceIstance.rimuoviAtletaConSport(nuovoAtleta.getId());
		
		int presentiSulDbDopoDelete = atletaServiceIstance.listAll().size();

		if (presentiSulDbDopoDelete>=presentiSulDbDopoInsert) {

			throw new RuntimeException(
					"****************TEST FAILED testDissasociaAtletaConSport: Atleta e sport non scollegati**********");

		}

		System.out.println("***********FINE TEST testDissasociaAtletaConSport: PASSED************");

	}

}
