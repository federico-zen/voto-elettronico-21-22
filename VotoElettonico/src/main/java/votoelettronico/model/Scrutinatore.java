package votoelettronico.model;

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
	 * @param codiceFiscale
	 */
	public Scrutinatore(String nome, String cognome, String codiceFiscale) {
		super(nome, cognome, codiceFiscale);
	}

	//METODI
	@Override
	public String toString() {
		return "Scrutinatore [nome=" + nome + ", cognome=" + cognome + ", codice fiscale=" + codiceFiscale + "]";
	}

	@Override
	public boolean isElettore() {
		return false;
	}

}
