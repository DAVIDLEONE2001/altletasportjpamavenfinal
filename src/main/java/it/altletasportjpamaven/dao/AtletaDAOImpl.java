package it.altletasportjpamaven.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.altletasportjpamaven.model.Atleta;

public class AtletaDAOImpl implements AtletaDAO {

	EntityManager entityManager;

	@Override
	public List<Atleta> list() throws Exception {

		return entityManager.createQuery("from Atleta", Atleta.class).getResultList();

	}

	@Override
	public Atleta get(Long id) throws Exception {

		return entityManager.find(Atleta.class, id);

	}

	@Override
	public void update(Atleta o) throws Exception {

		if (o == null) {
			throw new Exception("Problema valore input");
		}

		o = entityManager.merge(o);

	}

	@Override
	public void insert(Atleta o) throws Exception {

		if (o == null) {
			throw new Exception("Problema valore input");
		}

		entityManager.persist(o);

	}

	@Override
	public void delete(Atleta o) throws Exception {

		if (o == null) {
			throw new Exception("Problema valore input");
		}
		
		entityManager.remove(entityManager.merge(o));
		
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;

	}

	@Override
	public Atleta caricaSingoloElementoConSport(Long id) throws Exception {
		TypedQuery<Atleta> query = entityManager
				.createQuery("select u FROM Atleta u left join fetch u.sports r where u.id = :idAtleta", Atleta.class);
		query.setParameter("idAtleta", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}


	

}
