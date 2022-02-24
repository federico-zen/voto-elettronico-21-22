package votoelettronico.bean;



public class Partito extends Partecipante {

	
	
	//Immagine
	public Partito(int id,String nome) {
		super(id,nome);
	}
	
	@Override
	public boolean isPartito() {
		// TODO Auto-generated method stub
		return true;
	}
}
