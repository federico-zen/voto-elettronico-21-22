package votoelettronico.model;

public class Referendum extends Voto {
	
	boolean risposta;

	public Referendum(int id,boolean risposta) {
		super(id,"Referendum");
		this.risposta =risposta;
		
	}
	
	public Referendum(boolean risposta) {
		super("Referendum");
		this.risposta =risposta;
	}

	public boolean getRisposta() {
		return risposta;
	}

	public void setRisposta(boolean risposta) {
		this.risposta = risposta;
	}

	@Override
	public String toString() {
		return "Referendum [risposta=" + risposta + ", getTipo()=" + getTipo() + ", getId()=" + getId()
				+ ", isBianca()=" + isBianca() + "]";
	}

	
	

	
	

}
