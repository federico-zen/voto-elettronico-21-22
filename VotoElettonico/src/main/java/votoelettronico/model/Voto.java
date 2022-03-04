package votoelettronico.model;

public abstract class Voto {
	protected int id;

	public Voto() {
		super();
	}
	
	public Voto(int id) {
		super();
		
		this.id = id;
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
		return "Voto [id=" + id + "]";
	}
	
	
	
	
	
	
}
