package votoelettronico.controller;



import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    	
    	

    	if(username.getText().isEmpty() || password.getText().isEmpty() ) {
    		Alert t = new Alert(AlertType.INFORMATION,"Inserire tutti i campi");
    		t.showAndWait();
    	}else {
    		String user=username.getText() ,psw=password.getText();
    		System.out.println("Username : "+user);
    		System.out.println("Password : "+psw);
    		
    		/*
    		 * if (getDB == null){
    		 * 		Alert t = new Alert (AlertType.INFORMATION,"Username o Password Errati");
    		 * 		t.showAndWait();
    		 * }else{
    		 * 
    		 * 	...changeView()..;
    		 * 
    		 * }
    		 * 
    		 */
    	}
    }

    @FXML
    void loginPresenza(ActionEvent event) {
    	this.changeView("/fxml/login_presenza.fxml",List.of(username.getText(),password.getText()));
    }

	@Override
	public void init(Object parameters) {
		if(parameters != null) {
			@SuppressWarnings("unchecked")
			List<Object> l = (List<Object>) parameters;
			username.setText((String)l.get(0));
			password.setText((String)l.get(1));
		}
		
	}

}
