package art1;

/**
 * Classe che descrive un elettore
 */
public class Elettore extends Utente {
	//ATTRIBUTI
	private int eta;

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
	 * @param eta
	 */
	public Elettore(String nome, String cognome, String username, int eta) {
		super(nome, cognome, username);
		this.eta = eta;
	}

	//METODI
	/**
	 * @return the eta
	 */
	public int getEta() {
		return eta;
	}

	/**
	 * @param eta the eta to set
	 */
	public void setEta(int eta) {
		this.eta = eta;
	}

	@Override
	public String toString() {
		return "Elettore [nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", eta'=" + eta + "]";
	}
	
	
}
