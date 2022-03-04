package votoelettronico.model;

public class Referendum extends Voto {
	
	boolean risposta;

	public Referendum(int id,boolean risposta) {
		super(id);
		this.risposta =risposta;
		
	}
	
	public Referendum(boolean risposta) {
		this.risposta =risposta;
	}

	public boolean isRisposta() {
		return risposta;
	}

	public void setRisposta(boolean risposta) {
		this.risposta = risposta;
	}

	@Override
	public String toString() {
		return "Referendum [risposta=" + risposta + ", getId()=" + getId() + ", isBianca()=" + isBianca() + "]";
	}

	
	

}
