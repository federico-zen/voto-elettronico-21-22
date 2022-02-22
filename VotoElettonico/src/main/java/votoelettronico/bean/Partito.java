package votoelettronico.bean;



public class Partito extends Partecipante {

	
	
	//Immagine
	public Partito(String nome) {
		super.nome=nome;
	}
	
	@Override
	public boolean isPartito() {
		// TODO Auto-generated method stub
		return true;
	}
}
