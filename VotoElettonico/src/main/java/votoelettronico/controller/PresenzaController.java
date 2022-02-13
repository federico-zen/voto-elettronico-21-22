package votoelettronico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PresenzaController extends Controller {

    @FXML
    private Button back;

    @FXML
    private TextField cfElettore;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void backToMain(ActionEvent event) {
    	this.changeView("/fxml/main.fxml", null);
    }

    @FXML
    void loginPresenza(ActionEvent event) {

    }

	@Override
	public void init(Object parameter) {
		// TODO Auto-generated method stub
		
	}

}
