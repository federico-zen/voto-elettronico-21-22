package votoelettronico.model;

public class Categorico extends Voto{
	Partecipante preferenza;
	String tipo ;

	public Categorico(Partecipante preferenza,String tipo) {
		super();
		this.preferenza = preferenza;
		this.tipo=tipo;
	}

	public Categorico(int id,Partecipante preferenza,String tipo) {
		super(id);
		this.preferenza=preferenza;
		this.tipo = tipo;
	
	}

	public Partecipante getPreferenza() {
		return preferenza;
	}

	public void setPreferenza(Partecipante preferenza) {
		this.preferenza = preferenza;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Categorico [preferenza=" + preferenza + ", tipo=" + tipo + ", getId()=" + getId() + ", isBianca()="
				+ isBianca() + "]";
	}

	
	
	
	
	
	
	
}
