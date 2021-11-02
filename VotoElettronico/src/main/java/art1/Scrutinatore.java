package art1;

/**
 * Classe che descrive uno scrutinatore
 */
public class Scrutinatore extends Utente {
	//ATTRIBUTI
	
	//COSTRUTTORE
	/**
	 * 
	 */
	public Scrutinatore() {
		super();
	}

	/**
	 * @param nome
	 * @param cognome
	 * @param username
	 */
	public Scrutinatore(String nome, String cognome, String username) {
		super(nome, cognome, username);
	}

	//METODI
	@Override
	public String toString() {
		return "Scrutinatore [nome=" + nome + ", cognome=" + cognome + ", username=" + username + "]";
	}

}
