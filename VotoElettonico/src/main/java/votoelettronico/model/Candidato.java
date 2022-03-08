package votoelettronico.model;

public class Candidato extends Partecipante {
	
	private String cognome;
	
	public Candidato(int id,String nome,String cognome) {
		super(id,nome);
		this.cognome=cognome;
	}
	
	public Candidato(String nome,String cognome) {
		super(nome);
		this.cognome=cognome;
	}
	
	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	@Override
	public String toString() {
		return "Candidato [id=" + id + ", nome=" + nome + ", cognome=" + cognome + "]";
	}

	@Override
	public boolean isPartito() {
		return false;
	}
}
