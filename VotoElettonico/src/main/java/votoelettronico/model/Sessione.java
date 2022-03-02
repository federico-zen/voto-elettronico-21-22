package votoelettronico.model;

import java.util.Iterator;
import java.util.List;

public class Sessione implements Iterable<Partito> {
	
	int id;
	String nome;
	String mod_voto;
	String mod_vittoria;
	String domanda;
	boolean stato;
	List<Partito> lPartiti;
	
	public Sessione() {
		
	}
	public Sessione( String nome, String mod_voto, String mod_vittoria, String domanda, boolean stato,
			List<Partito> lPartiti) {
		super();
		this.nome = nome;
		this.mod_voto = mod_voto;
		this.mod_vittoria = mod_vittoria;
		this.domanda = domanda;
		this.stato = stato;
		this.lPartiti = lPartiti;
	}
	
	

	/**
	 * @param id
	 * @param mod_voto
	 * @param mod_vittoria
	 * @param domanda
	 * @param stato
	 * @param lPartiti
	 */
	public Sessione(int id, String nome, String mod_voto, String mod_vittoria, String domanda, boolean stato,
			List<Partito> lPartiti) {
		super();
		this.id = id;
		this.nome = nome;
		this.mod_voto = mod_voto;
		this.mod_vittoria = mod_vittoria;
		this.domanda = domanda;
		this.stato = stato;
		this.lPartiti = lPartiti;
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the mod_voto
	 */
	public String getMod_voto() {
		return mod_voto;
	}

	/**
	 * @param mod_voto the mod_voto to set
	 */
	public void setMod_voto(String mod_voto) {
		this.mod_voto = mod_voto;
	}

	/**
	 * @return the mod_vittoria
	 */
	public String getMod_vittoria() {
		return mod_vittoria;
	}

	/**
	 * @param mod_vittoria the mod_vittoria to set
	 */
	public void setMod_vittoria(String mod_vittoria) {
		this.mod_vittoria = mod_vittoria;
	}

	/**
	 * @return the domanda
	 */
	public String getDomanda() {
		return domanda;
	}

	/**
	 * @param domanda the domanda to set
	 */
	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	/**
	 * @return the stato
	 */
	public boolean isStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(boolean stato) {
		this.stato = stato;
	}

	@Override
	public Iterator<Partito> iterator() {
		return lPartiti.iterator();
	}
	
	public void addPartito(Partito p) {
		lPartiti.add(p);
	}
	
	public void removePartito(Partito p) {
		lPartiti.remove(p);
	}

	@Override
	public String toString() {
		return "Sessione [id=" + id + ", nome=" + nome + ", mod_voto=" + mod_voto + ", mod_vittoria=" + mod_vittoria
				+ ", domanda=" + domanda + ", stato=" + stato + ", lPartiti=" + lPartiti + "]";
	}
	
}
