package votoelettronico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController extends Controller{

    @FXML
    private Button login;

    @FXML
    private Button loginP;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void login(ActionEvent event) {

    }

    @FXML
    void loginPresenza(ActionEvent event) {
    	this.changeView("/fxml/login_presenza.fxml", null);
    }

	@Override
	public void init(Object parameter) {
		// TODO Auto-generated method stub
		
	}

}
