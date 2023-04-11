package it.altletasportjpamaven.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import it.altletasportjpamaven.dao.AtletaDAO;
import it.altletasportjpamaven.dao.EntityManagerUtil;
import it.altletasportjpamaven.dao.SportDAO;
import it.altletasportjpamaven.model.Atleta;
import it.altletasportjpamaven.model.Sport;

public class AtletaServiceImpl implements AtletaService {

	private AtletaDAO atletaDAO;
	private SportDAO sportDAO;

	@Override
	public List<Atleta> listAll() throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			atletaDAO.setEntityManager(entityManager);

			return atletaDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}

	}

	@Override
	public Atleta caricaSingoloElemento(Long id) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			atletaDAO.setEntityManager(entityManager);

			return atletaDAO.get(id);

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Atleta o) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			atletaDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			atletaDAO.update(o);
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuovo(Atleta o) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			atletaDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			atletaDAO.insert(o);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}

	}

	@Override
	public void rimuovi(Long id) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			atletaDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			atletaDAO.delete(atletaDAO.get(id));
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}

	}

	@Override
	public void setAtletaDAO(AtletaDAO atletaDAO) {

		this.atletaDAO = atletaDAO;

	}

	@Override
	public void aggiungiSport(Atleta atleta, Sport sportIstance) {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			atletaDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se è già presente quel ruolo non deve essere inserito
			atleta = entityManager.merge(atleta);
			sportIstance = entityManager.merge(sportIstance);

			atleta.getSports().add(sportIstance);
			// l'update non viene richiamato a mano in quanto
			// risulta automatico, infatti il contesto di persistenza
			// rileva che utenteEsistente ora è dirty vale a dire che una sua
			// proprieta ha subito una modifica (vale anche per i Set ovviamente)

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void setSportDAO(SportDAO sportDAO) {
		this.sportDAO = sportDAO;

	}

	@Override
	public Atleta caricaSingoloElementoConSport(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			atletaDAO.setEntityManager(entityManager);

			return atletaDAO.caricaSingoloElementoConSport(id);

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuoviSport(Long IdAtleta, Long IdSport) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			atletaDAO.setEntityManager(entityManager);
			sportDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se è già presente quel ruolo non deve essere inserito
			Atleta atletaEsistente = atletaDAO.caricaSingoloElementoConSport(IdAtleta);
			Sport sportEsistente = sportDAO.get(IdSport);
			atletaEsistente.getSports().remove(sportEsistente);
			// l'update non viene richiamato a mano in quanto
			// risulta automatico, infatti il contesto di persistenza
			// rileva che utenteEsistente ora è dirty vale a dire che una sua
			// proprieta ha subito una modifica (vale anche per i Set ovviamente)

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuoviAtletaConSport(Long IdAtleta) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			atletaDAO.setEntityManager(entityManager);
			sportDAO.setEntityManager(entityManager);

			Atleta atletaEsistente = atletaDAO.caricaSingoloElementoConSport(IdAtleta);
			List<Sport> sportEsistenti = atletaEsistente.getSports();
			if (sportEsistenti.size() > 0) {
				List<Sport> sportVuoti = new ArrayList<>();
				atletaEsistente.setSports(sportVuoti);
			}
			atletaDAO.delete(atletaEsistente);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

}
