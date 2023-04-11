package it.altletasportjpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.altletasportjpamaven.dao.EntityManagerUtil;
import it.altletasportjpamaven.dao.SportDAO;
import it.altletasportjpamaven.model.Sport;

public class SportServiceImpl implements SportService {

	private SportDAO sportDAO;

	@Override
	public List<Sport> listAll() throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {

			sportDAO.setEntityManager(entityManager);
			return sportDAO.list();

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public Sport caricaSingoloElemento(Long id) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {

			sportDAO.setEntityManager(entityManager);
			
			return sportDAO.get(id);

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void aggiorna(Sport o) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			
			entityManager.getTransaction().begin();
			sportDAO.setEntityManager(entityManager);
			sportDAO.update(o);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void inserisciNuovo(Sport o) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			sportDAO.setEntityManager(entityManager);
			sportDAO.insert(o);
			entityManager.getTransaction().commit();
			
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
			}
		finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void rimuovi(Long id) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			sportDAO.setEntityManager(entityManager);
			sportDAO.delete(sportDAO.get(id));
			entityManager.getTransaction().commit();
		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
			
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void setSportDAO(SportDAO sportDAO) {
		this.sportDAO = sportDAO;

	}

	@Override
	public Sport cercaPerDescrizione(String descrizione) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			sportDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return sportDAO.cercaPerDescrizione(descrizione);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Sport> trovaSportConErrori() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {

			sportDAO.setEntityManager(entityManager);
			return sportDAO.trovaErrori();

		} catch (Exception e) {

			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public int sommaMedaglieDiAtletiConAlmenoUnoSportChiuso() throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			
			sportDAO.setEntityManager(entityManager);
			return sportDAO.numMedDiAtletiConSportChiusi();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
		
	}

}
