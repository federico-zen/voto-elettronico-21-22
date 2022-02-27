package votoelettronico.model;

import java.sql.Blob;
import java.util.Iterator;
import java.util.List;

import javafx.scene.image.Image;

public class Partito extends Partecipante implements Iterable<Candidato> {
	
	//Immagine
	Blob logo;
	List<Candidato> candidati ;
	
	/*public Partito(int id,String nome,List<Candidato> candidati) {
		super(id,nome);
		this.candidati = candidati;
		
	}*/
	
	public Partito(int id,String nome,List<Candidato> candidati,Blob logo) {
		super(id,nome);
		this.candidati = candidati;
		this.logo = logo;
	}
	
	
	public Blob getLogo() {
		return logo;
	}


	public void setLogo(Blob logo) {
		this.logo = logo;
	}


	@Override
	public String toString() {
		return "Partito [id=" + id + ", nome=" + nome + " Candidati="+candidati.toString()+"]";
	}

	@Override
	public boolean isPartito() {
		return true;
	}

	@Override
	public Iterator<Candidato> iterator() {
		return candidati.iterator();
	}
}
