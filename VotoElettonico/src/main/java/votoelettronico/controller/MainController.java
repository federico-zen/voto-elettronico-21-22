package votoelettronico.controller;



import java.util.List;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import votoelettronico.dao.UtenteDAO;
import votoelettronico.factory.AlertFactory;
import votoelettronico.factory.DAOFactory;
import votoelettronico.logger.VotoLogger;
import votoelettronico.model.Elettore;
import votoelettronico.model.Scrutinatore;
import votoelettronico.model.Utente;

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
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION, "Inserire tutti i campi").showAndWait();
    	}else {
    		String user=username.getText() ,psw=password.getText();
    		
    		UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
    		Utente u = dao.get(user, psw);
    		if (Objects.isNull(u)) {
    			//messaggio di errore e l'utente reinserisce i dati
    			AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "I dati inseriti non corrispondono ad alcun utente registrato").showAndWait();
    		} else {
    			if (u.isElettore()) {
    				//cambia finestra alla sessione di voto
    				Elettore elettore = (Elettore) u;
        			this.changeView("home_elettore.fxml", elettore);
    			} else {
    				//cambia finestra alla home del gestore
    				Scrutinatore scrutinatore = (Scrutinatore) u;
    				VotoLogger.writeToLog("Loggato : "+ scrutinatore.getNome() + " come Scrutinatore");
    				this.changeView("home_gestore.fxml", scrutinatore);
    			}
    			
    			
    		}
    	}
    }

    @FXML
    void loginPresenza(ActionEvent event) {
    	this.changeView("login_presenza.fxml",List.of(username.getText(),password.getText()));
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
