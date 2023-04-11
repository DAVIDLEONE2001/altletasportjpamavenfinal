package it.altletasportjpamaven.dao;

import java.util.List;

import it.altletasportjpamaven.model.Sport;

public interface SportDAO extends IBaseDAO<Sport> {

	public Sport cercaPerDescrizione(String descrizione) throws Exception;
	
	public List<Sport> trovaErrori()throws Exception;
	
	public int numMedDiAtletiConSportChiusi() throws Exception; 

}
