package art1;

/**
 * Classe astratta che descrive un utente
 */
public abstract class Utente {
	//ATTRIBUTI
	protected String nome;
	protected String cognome;
	protected String username;
	
	//COSTRUTTORE
	/**
	 * @param nome
	 * @param cognome
	 * @param username
	 */
	public Utente(String nome, String cognome, String username) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
	}

	/**
	 * 
	 */
	public Utente() {
	}
	
	//METODI
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
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", username=" + username + "]";
	}
		
	
}
