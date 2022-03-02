package votoelettronico.factory;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertFactory {
	
	private static AlertFactory instance = null;
	
	private AlertFactory() {
	}
	
	public static AlertFactory getInstance() {
		if(instance == null) {
			instance = new AlertFactory();
		}
		
		return instance;
	}

	
	public Alert getAlert(AlertType t,String msg) {
		Alert alert = new Alert(t,msg);
		return alert;
		
	}
	
	public Alert getSlimAlert(AlertType t,String msg) {
		Alert alert = new Alert(t,msg);
		alert.setHeaderText(null);
		return alert;
		
	}
	
	
	
	
	

}
