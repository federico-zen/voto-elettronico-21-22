package votoelettronico.bean;

public class Candidato extends Partecipante {
	
	String cognome;
	
	public Candidato(String nome,String cognome) {
		super.nome=nome;
		this.cognome=cognome;
	}
	@Override
	public boolean isPartito() {
		// TODO Auto-generated method stub
		return false;
	}
}
