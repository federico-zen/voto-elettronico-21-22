package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import votoelettronico.dbconnection.DBConnection;
import votoelettronico.model.Candidato;
import votoelettronico.model.Partito;
import votoelettronico.model.Sessione;


public class TestDominio {
	
	
	
	@Test
	public  void testPartito() {
		Candidato r = new Candidato("c3","c4");
		Partito t = new Partito("Partito",List.of(new Candidato("c1", "c1"),new Candidato("c2", "c2") ),null);
		assertNotNull(t.iterator().next());
		assertTrue(t.isPartito());
	}
		
	
	@Test
	public  void testSessione() {
		
		Sessione s = new Sessione("nome", "Referendum", "Quorum", null, true, null);
		
		if(s.getMod_voto().equalsIgnoreCase("Referendum")) {
			assertNotNull(s.getDomanda());
		}
		
		assertTrue(s.getStato());
	}

}