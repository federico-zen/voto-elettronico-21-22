package votoelettronico.controller;

import java.util.ArrayList;
import java.util.List;

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
    	this.changeView("/fxml/main.fxml", List.of(username.getText(),password.getText()));
    }

    @FXML
    void loginPresenza(ActionEvent event) {
    	
    }

	@Override
	public void init(Object parameters) {
		if(parameters != null) {
			List<Object> l = (List<Object>) parameters;
			username.setText((String)l.get(0));
			password.setText((String)l.get(1));
		}
		
	}

}
