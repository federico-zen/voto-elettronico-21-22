package votoelettronico.model;

public class SchedaBianca extends Voto {
	
	public SchedaBianca() {
		super();
		
	}

	public SchedaBianca(int id) {
		super(id);
		
	}

	public boolean isBianca() {
		return true;
	}
}
