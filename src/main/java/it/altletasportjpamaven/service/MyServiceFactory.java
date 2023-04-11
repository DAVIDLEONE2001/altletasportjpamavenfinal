package it.altletasportjpamaven.service;

import it.altletasportjpamaven.dao.MyDAOFactory;

public class MyServiceFactory {

	// rendiamo questo factory SINGLETON
	private static SportService SPORT_SERVICE_INSTANCE;
	private static AtletaService ATLETA_SERVICE_INSTANCE;

	public static SportService getSportServiceInstance() {
		if (SPORT_SERVICE_INSTANCE == null)
			SPORT_SERVICE_INSTANCE = new SportServiceImpl();

		SPORT_SERVICE_INSTANCE.setSportDAO(MyDAOFactory.getSportDAOInstance());
//		SPORT_SERVICE_INSTANCE.setAtletaDAO(MyDAOFactory.getRuoloDAOInstance());
		return SPORT_SERVICE_INSTANCE;
	}

	public static AtletaService getAtletaServiceInstance() {
		if (ATLETA_SERVICE_INSTANCE == null)
			ATLETA_SERVICE_INSTANCE = new AtletaServiceImpl();

		ATLETA_SERVICE_INSTANCE.setAtletaDAO(MyDAOFactory.getAtletaDAOInstance());
		ATLETA_SERVICE_INSTANCE.setSportDAO(MyDAOFactory.getSportDAOInstance());
		return ATLETA_SERVICE_INSTANCE;
	}

}
