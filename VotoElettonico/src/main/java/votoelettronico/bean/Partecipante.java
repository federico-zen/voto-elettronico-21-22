package votoelettronico.bean;

public abstract class Partecipante {
	int id;
	String nome;
	
	public Partecipante() {
		
	}
	
	public Partecipante (int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public abstract boolean isPartito();
}
