package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import votoelettronico.dao.UtenteDAO;
import votoelettronico.dbconnection.DBConnection;
import votoelettronico.factory.DAOFactory;
import votoelettronico.model.Elettore;
import votoelettronico.model.Scrutinatore;
import votoelettronico.model.Utente;


public class DBTest {
	
	
	
	@Test
	void testLogin() {
		UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
		assertNotNull(dao.get("admin", "123"));
	}
	
	@Test
	void testElettore() {
		Elettore e = new Elettore("Elettore","Elettore" , "CF");
		UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
		
		dao.save(e,"123");
		
		
		Utente u =dao.get("CF","123");
		assertTrue(u.isElettore());
		dao.delete(u);
		
		
		
	}
	
	@Test
	void testScrutinatore() {
		Scrutinatore s = new Scrutinatore("Scrutinatore","Scrutinatore" , "CF");
		UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
		
		dao.save(s,"123");
		
		
		Utente u =dao.get("CF","123");
		assertFalse(u.isElettore());
		dao.delete(u);
			
	}
	

}