package votoelettronico.model;

public class Categorico extends Voto{
	Partecipante preferenza;

	public Categorico(Partecipante preferenza,String tipo) {
		super(tipo);
		this.preferenza = preferenza;
		
	}

	public Categorico(int id,Partecipante preferenza,String tipo) {
		super(id,tipo);
		this.preferenza=preferenza;
		
	
	}

	public Partecipante getPreferenza() {
		return preferenza;
	}

	public void setPreferenza(Partecipante preferenza) {
		this.preferenza = preferenza;
	}

	

	@Override
	public String toString() {
		return "Categorico [preferenza=" + preferenza + ", tipo=" + getTipo() + ", getId()=" + getId() + ", isBianca()="
				+ isBianca() + "]";
	}

	
	
	
	
	
	
	
}
