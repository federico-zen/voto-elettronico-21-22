package votoelettronico.model;

import java.util.Iterator;
import java.util.List;

public class Ordinale extends Voto implements Iterable<Partecipante>{
	List<Partecipante> preferenza;
	

	public Ordinale(List<Partecipante> preferenza,String tipo) {
		super(tipo);
		this.preferenza = preferenza;
		
	}

	public Ordinale(int id,List<Partecipante> preferenza,String tipo) {
		super(id,tipo);
		this.preferenza=preferenza;
		
	}
	
	

	@Override
	public String toString() {
		return "Categorico [preferenza=" + preferenza + ", tipo=" + getTipo() + ", getId()=" + getId() + ", isBianca()="
				+ isBianca() + "]";
	}

	@Override
	public Iterator<Partecipante> iterator() {
		return preferenza.iterator();
	}

	
	
	
	
	
	
	
}
