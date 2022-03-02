package votoelettronico.factory;

import votoelettronico.dao.GenericDAO;
import votoelettronico.dao.PartecipanteDAO;
import votoelettronico.dao.SessioneDAO;
import votoelettronico.dao.UtenteDAO;
import votoelettronico.model.Partecipante;
import votoelettronico.model.Sessione;
import votoelettronico.model.Utente;

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
	
	public GenericDAO<Sessione> getSessioneDAO() {
		return new SessioneDAO();
	}
	
	
	

}
