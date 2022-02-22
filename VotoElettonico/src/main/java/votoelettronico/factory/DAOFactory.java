package votoelettronico.factory;

import votoelettronico.bean.Partecipante;
import votoelettronico.bean.Utente;
import votoelettronico.dao.GenericDAO;
import votoelettronico.dao.PartecipanteDAO;
import votoelettronico.dao.UtenteDAO;

public class DAOFactory {
	
	private static DAOFactory instance = null;
	
	private DAOFactory() {
	}
	
	public static DAOFactory getInstance() {
		if(instance == null) {
			instance = new DAOFactory();
		}
		
		return instance;
	}

	
	public GenericDAO<Utente> getUtenteDAO() {
		return new UtenteDAO();
	}
	
	public GenericDAO<Partecipante> getPartecipanteDAO() {
		return new PartecipanteDAO();
	}
	
	
	

}
