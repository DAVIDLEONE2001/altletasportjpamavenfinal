package it.altletasportjpamaven.service;

import it.altletasportjpamaven.dao.AtletaDAO;
import it.altletasportjpamaven.dao.SportDAO;
import it.altletasportjpamaven.model.Atleta;
import it.altletasportjpamaven.model.Sport;

public interface AtletaService extends IBaseService<Atleta> {

	public void setAtletaDAO(AtletaDAO atletaDAO);
	public void setSportDAO(SportDAO sportDAO);
	
	public void aggiungiSport(Atleta atleta, Sport sportIstance);
	public Atleta caricaSingoloElementoConSport(Long id) throws Exception;
	public void rimuoviSport(Long IdAtleta, Long IdSport) throws Exception;
	public void rimuoviAtletaConSport(Long IdAtleta) throws Exception;
	

	
}
