package votoelettronico.model;

public abstract class Risultato {
	
	String vittoria;
	int nVotanti;
	int nAstenuti;
	int nElettori;
	
	public Risultato(int nVotanti, int nAstenuti,String vittoria,int nElettori) {
		super();
		this.nElettori = nElettori;
		this.vittoria=vittoria;
		this.nVotanti = nVotanti;
		this.nAstenuti = nAstenuti;
	}
	
	public abstract String getVincitore();

	public int getnVotanti() {
		return nVotanti;
	}

	public void setnVotanti(int nVotanti) {
		this.nVotanti = nVotanti;
	}

	public int getnAstenuti() {
		return nAstenuti;
	}

	public void setnAstenuti(int nAstenuti) {
		this.nAstenuti = nAstenuti;
	}

	public String getVittoria() {
		return vittoria;
	}

	public void setVittoria(String vittoria) {
		this.vittoria = vittoria;
	}

	@Override
	public String toString() {
		return "Risultato [nVotanti=" + nVotanti + ", nAstenuti=" + nAstenuti + "]";
	}
	
	
	
	
	
	

	
	
	
	
	
	
}
