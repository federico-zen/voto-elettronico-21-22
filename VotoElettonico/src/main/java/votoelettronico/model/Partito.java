package votoelettronico.model;

import java.util.List;

public class Partito extends Partecipante {

	List<Candidato> candidati ;
	
	//Immagine
	public Partito(int id,String nome,List<Candidato> candidati) {
		super(id,nome);
		this.candidati = candidati;
	}
	
	@Override
	public String toString() {
		return "Partito [id=" + id + ", nome=" + nome + " Candidati="+candidati.toString()+"]";
	}

	@Override
	public boolean isPartito() {
		return true;
	}
}
