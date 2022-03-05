package votoelettronico.model;

public class SchedaBianca extends Voto {
	
	public SchedaBianca(String tipo) {
		super(tipo);
		
	}

	public SchedaBianca(int id,String tipo) {
		super(id,tipo);
		
	}

	public boolean isBianca() {
		return true;
	}
}
