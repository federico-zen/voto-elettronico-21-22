package votoelettronico.model;

import java.util.Iterator;
import java.util.List;

public class Ordinale extends Voto implements Iterable<Partecipante>{
	List<Partecipante> preferenza;
	String tipo ;

	public Ordinale(List<Partecipante> preferenza,String tipo) {
		super();
		this.preferenza = preferenza;
		this.tipo = tipo;
	}

	public Ordinale(int id,List<Partecipante> preferenza,String tipo) {
		super(id);
		this.preferenza=preferenza;
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Categorico [preferenza=" + preferenza + ", tipo=" + tipo + ", getId()=" + getId() + ", isBianca()="
				+ isBianca() + "]";
	}

	@Override
	public Iterator<Partecipante> iterator() {
		return preferenza.iterator();
	}

	
	
	
	
	
	
	
}
