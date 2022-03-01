package votoelettronico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import votoelettronico.dao.UtenteDAO;
import votoelettronico.factory.DAOFactory;
import votoelettronico.model.Elettore;
import votoelettronico.model.Scrutinatore;
import votoelettronico.model.Utente;
import javafx.scene.control.Alert.AlertType;

public class NuovoUtenteController extends Controller {
	Scrutinatore logged ;

    @FXML
    private Button aggiungiBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TextField cfTextField;

    @FXML
    private TextField cognomeTextField;
    
    @FXML
    private PasswordField pswField;

    @FXML
    private ToggleGroup elettore_scrutinatore;

    @FXML
    private TextField nomeTextField;

    @FXML
    void aggiungi(ActionEvent event) {
    	String nome = nomeTextField.getText();
    	String cognome = cognomeTextField.getText();
    	String cf = cfTextField.getText();
    	RadioButton rb = (RadioButton) elettore_scrutinatore.getSelectedToggle();
    	String ruolo = rb.getText();
    	String psw = pswField.getText();
    	
    	if (nome.isEmpty() || cognome.isEmpty() || cf.isEmpty() || psw.isEmpty()) {
    		Alert t = new Alert(AlertType.INFORMATION,"Inserire tutti i campi");
    		t.showAndWait();
    	}else {
    		Utente utente;
        	if (ruolo.equals("Elettore")) {
        		utente = new Elettore(nome, cognome, cf);
        	} else {
        		utente = new Scrutinatore(nome, cognome, cf);
        	}
        	UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
        	dao.save(utente, psw);
    	}
    	
    }

    @FXML
    void back(ActionEvent event) {
    	changeView("home_gestore.fxml", logged);
    }

	@Override
	public void init(Object parameters) {
		logged = (Scrutinatore) parameters;
		
		
	}

}
