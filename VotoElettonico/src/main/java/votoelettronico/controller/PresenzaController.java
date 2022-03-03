package votoelettronico.controller;

import java.util.List;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import votoelettronico.dao.UtenteDAO;
import votoelettronico.factory.AlertFactory;
import votoelettronico.factory.DAOFactory;
import votoelettronico.model.Elettore;
import votoelettronico.model.Utente;

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
    	this.changeView("main.fxml", List.of(username.getText(),password.getText()));
    }

    @FXML
    void loginPresenza(ActionEvent event) {
    	if(username.getText().isEmpty() || password.getText().isEmpty() || cfElettore.getText().isEmpty()) {
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION, "Inserire tutti i campi").showAndWait();
    	}else {
    		String user=username.getText() ,psw=password.getText(), cf = cfElettore.getText();
    		System.out.println("Username : "+user);
    		System.out.println("Password : "+psw);
    		System.out.println("CF Elettore : "+cf);
    		
    		UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
    		Utente u = dao.get(user, psw);
    		if (Objects.isNull(u)) {
    			//messaggio di errore sull'input dei dati inseriti
    			AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "I dati inseriti non corrispondono ad alcun utente registrato").showAndWait();
    		} else if (u.isElettore()) {
    			//messaggio di errore sulla tipologia di utente
    			AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Non hai i permessi per eseguire questa azione").showAndWait();
    		} else {
    			Elettore elettore = (Elettore) dao.get(cf);
    			if (Objects.isNull(elettore)) {
    				//messaggio di errore sul cf inserito
    				AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Il CF inserito non corrisponde ad alcun elettore registrato").showAndWait();
    			} else {
    				//cambia finestra alla sessione di voto
    				this.changeView("home_elettore.fxml", elettore);
    			}
    			
    		}
    		
    	}
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
