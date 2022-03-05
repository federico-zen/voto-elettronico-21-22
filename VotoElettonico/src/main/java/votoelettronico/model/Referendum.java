package votoelettronico.model;

public class Referendum extends Voto {
	
	boolean risposta;

	public Referendum(int id,boolean risposta,String tipo) {
		super(id,tipo);
		this.risposta =risposta;
		
	}
	
	public Referendum(boolean risposta,String tipo) {
		super(tipo);
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
		return "Referendum [risposta=" + risposta + ", getTipo()=" + getTipo() + ", getId()=" + getId()
				+ ", isBianca()=" + isBianca() + "]";
	}

	
	

	
	

}
