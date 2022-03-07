package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import votoelettronico.model.Candidato;
import votoelettronico.model.Partito;
import votoelettronico.model.Sessione;


public class TestDominio {
	
	
	
	@Test
	public  void testPartito() {
		
		Partito t = new Partito("Partito",List.of(new Candidato("c1", "c1"),new Candidato("c2", "c2") ),null);
		assertNotNull(t.iterator().next());
		assertTrue(t.isPartito());
	}
		
	
	@Test
	public  void testSessione() {
		
		Sessione s = new Sessione("nome", "Referendum", "Quorum", "Domanda", true, null);
		
		if(s.getMod_voto().equalsIgnoreCase("Referendum")) {
			
			assertTrue(s.getDomanda().length()>0);
		}
		
		assertTrue(s.getStato());
	}

}