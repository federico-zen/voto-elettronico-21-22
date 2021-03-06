package votoelettronico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import votoelettronico.model.Scrutinatore;

public class HomeGestoreController extends Controller {
	Scrutinatore logged;

    @FXML
    private Button addUserBtn;

    @FXML
    private Button deleteUserBtn;

    @FXML
    private Button endSessionBtn;
    
    @FXML
    private Button logOutBtn;

    @FXML
    private Button gestioneC;

    @FXML
    private Button newSessionBtn;

    @FXML
    private Label welcomeLabel;

    @FXML
    void addUserBtnEvent(ActionEvent event) {
    	changeView("aggiungi_utente.fxml", logged);
    }

    @FXML
    void deleteUserBtnEvent(ActionEvent event) {
    	changeView("cancella_utente.fxml",logged);
    }

    @FXML
    void endSessionBtnEvent(ActionEvent event) {
    	changeView("termina_sessione.fxml",logged);
    }

    @FXML
    void gestioneCandidati(ActionEvent event) {
    	changeView("gestione_candidati.fxml", logged);
    }

    @FXML
    void newSessionBtnEvent(ActionEvent event) {
    	changeView("creazione_sessione.fxml", logged);
    }
    
    @FXML
    void logOut(ActionEvent event) {
    	changeView("main.fxml", null);
    }
    
    @Override
    public void init(Object parameters) {
    	logged = (Scrutinatore) parameters;
    	welcomeLabel.setText("Benvenuto "+ logged.getNome());
    	
    	
    }

}




