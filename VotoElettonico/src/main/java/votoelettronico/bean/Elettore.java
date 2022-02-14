package votoelettronico.bean;

/**
 * Classe che descrive un elettore
 */
public class Elettore extends Utente {
	//ATTRIBUTI
	

	//COSTRUTTORE
	/**
	 * 
	 */
	public Elettore() {
		super();
	}
	
	/**
	 * @param nome
	 * @param cognome
	 * @param username
	 */
	public Elettore(String nome, String cognome, String username) {
		super(nome, cognome, username);
	}

	//METODI
	



	@Override
	public String toString() {
		return "Elettore [nome=" + nome + ", cognome=" + cognome + ", username=" + username + "]";
	}
	
	
}
