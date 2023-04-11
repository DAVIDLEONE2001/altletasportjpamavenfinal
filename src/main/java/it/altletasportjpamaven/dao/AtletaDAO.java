package it.altletasportjpamaven.dao;

import it.altletasportjpamaven.model.Atleta;

public interface AtletaDAO extends IBaseDAO<Atleta> {
	
	public Atleta caricaSingoloElementoConSport(Long id) throws Exception;
	
	
	

}
