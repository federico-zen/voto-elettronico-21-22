package votoelettronico.model;

import java.util.Iterator;
import java.util.List;

public class CategoricoPreferenze extends Voto implements Iterable<Candidato>{
	List<Candidato> preferenze;
	
	public CategoricoPreferenze(List<Candidato> preferenze) {
		super();
		this.preferenze = preferenze;
	}

	public CategoricoPreferenze(int id,List<Candidato> preferenze) {
		super(id);
		this.preferenze = preferenze;
	}

	@Override
	public Iterator<Candidato> iterator() {
		return preferenze.iterator();
	}

	@Override
	public String toString() {
		return "CategoricoPreferenze [preferenze=" + preferenze + ", getId()=" + getId() + ", isBianca()=" + isBianca()
				+ "]";
	}
	
	

	
}
