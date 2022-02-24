package votoelettronico.model;

/**
 * Classe astratta che descrive un utente
 */
public abstract class Utente {
	//ATTRIBUTI
	protected String nome;
	protected String cognome;
	protected String codiceFiscale;
	
	//COSTRUTTORE
	/**
	 * @param nome
	 * @param cognome
	 * @param codiceFiscale
	 */
	public Utente(String nome, String cognome, String codiceFiscale) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * 
	 */
	public Utente() {
	}
	
	//METODI
	/**
	 * @return il nome
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
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", codice fiscale=" + codiceFiscale + "]";
	}
	
	public abstract boolean isElettore();
		
	
}
