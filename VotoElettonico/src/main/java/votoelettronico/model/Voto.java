package votoelettronico.model;

public abstract class Voto {
	protected int id;
	protected String tipo;
	
	public Voto() {
		
	}

	public Voto(String tipo) {
		this.tipo = tipo;
	}
	
	
	public Voto(int id,String tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isBianca() {
		return false;
	}

	@Override
	public String toString() {
		return "Voto [id=" + id + ", tipo=" + tipo + "]";
	}

	
	
	
	
	
	
	
}
