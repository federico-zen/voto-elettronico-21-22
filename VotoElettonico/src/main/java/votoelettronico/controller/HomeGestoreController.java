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

    }

    @FXML
    void endSessionBtnEvent(ActionEvent event) {

    }

    @FXML
    void gestioneCandidati(ActionEvent event) {

    }

    @FXML
    void newSessionBtnEvent(ActionEvent event) {

    }
    
    @Override
    public void init(Object parameters) {
    	logged = (Scrutinatore) parameters;
    	welcomeLabel.setText("Benvenuto "+ logged.getNome());
    	
    	
    }

}




