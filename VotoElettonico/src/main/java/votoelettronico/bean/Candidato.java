package votoelettronico.bean;

public class Candidato extends Partecipante {
	
	String cognome;
	
	public Candidato(int id,String nome,String cognome) {
		super(id,nome);
		this.cognome=cognome;
	}
	
	
	@Override
	public boolean isPartito() {
		return false;
	}
}
