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
	 * @param codiceFiscale
	 */
	public Elettore(String nome, String cognome, String codiceFiscale) {
		super(nome, cognome, codiceFiscale);
	}

	//METODI
	



	@Override
	public String toString() {
		return "Elettore [nome=" + nome + ", cognome=" + cognome + ", codice fiscale=" + codiceFiscale + "]";
	}
	
	
}
