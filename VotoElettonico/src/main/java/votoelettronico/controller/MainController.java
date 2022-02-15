package votoelettronico.controller;



import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import votoelettronico.bean.Utente;
import votoelettronico.dao.GenericDAO;
import votoelettronico.dbconnection.DBConnection;
import votoelettronico.factory.DAOFactory;
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
    	
    	String user ,psw;

    	if(username.getText().isEmpty() || password.getText().isEmpty() ) {
    		Alert t = new Alert(AlertType.INFORMATION,"Inserire tutti i campi");
    		t.showAndWait();
    	}
    	
    	
    	
    }

    @FXML
    void loginPresenza(ActionEvent event) {
    	this.changeView("/fxml/login_presenza.fxml",List.of(username.getText(),password.getText()));
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
