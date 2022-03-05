package votoelettronico.controller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import votoelettronico.dao.SessioneDAO;
import votoelettronico.dao.VotoDAO;
import votoelettronico.factory.AlertFactory;
import votoelettronico.factory.DAOFactory;
import votoelettronico.model.Elettore;
import votoelettronico.model.Referendum;
import votoelettronico.model.SchedaBianca;
import votoelettronico.model.Sessione;

public class ReferendumController extends Controller {
	
	Elettore logged;
	Sessione s = null;

    @FXML
    private Button confirmBtn;

    @FXML
    private TextArea domanda;

    @FXML
    private Label nomeSessioneLabel;

    @FXML
    private ToggleGroup scelta;

    @FXML
    private Button whiteBtn;

    @FXML
    void astenuto(ActionEvent event) {
    	
    	Alert alert = AlertFactory.getInstance().getSlimAlert(AlertType.CONFIRMATION, "Sei sicuro di voler confermare l'astensione?");
    	alert.showAndWait();
    	
    	if(alert.getResult() == ButtonType.OK) {
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION, "Azione Registrata!").showAndWait();
    		//Carica Voto
    		SessioneDAO dao = (SessioneDAO) DAOFactory.getInstance().getSessioneDAO();
    		dao.addVotazione(s, logged);
    		
    		//Carica Scheda
    		VotoDAO daoV = (VotoDAO) DAOFactory.getInstance().getVotoDAO();
    		daoV.save(new SchedaBianca(),s.getId());
    		
    		//Change View
    		changeView("home_elettore.fxml", logged);
    		
    	}
    }

    @FXML
    void confirm(ActionEvent event) {
    	//alert conferma
    	Alert alert = AlertFactory.getInstance().getSlimAlert(AlertType.CONFIRMATION, "Sei sicuro di voler confermare il tuo voto?");
    	alert.showAndWait();
    	
    	if(alert.getResult() == ButtonType.OK) {
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION, "Azione Registrata!").showAndWait();
    		
    		//Carica Voto
    		SessioneDAO dao = (SessioneDAO) DAOFactory.getInstance().getSessioneDAO();
    		dao.addVotazione(s, logged);
    		
    		//Carica Scheda
    		RadioButton b = (RadioButton) scelta.getSelectedToggle();
    		String risposta = b.getText();
    		VotoDAO daoV = (VotoDAO) DAOFactory.getInstance().getVotoDAO();
    		if(risposta.equalsIgnoreCase("si")) {
        		daoV.save(new Referendum(true),s.getId());
    		}else {
    			daoV.save(new Referendum(false),s.getId());
    		}
    		
    		//Change View
    		changeView("home_elettore.fxml", logged);
    	}
    }

	@Override
	public void init(Object parameters) {
		List l = (List) parameters;
		logged = (Elettore) l.get(0);
		s = (Sessione) l.get(1);
		domanda.setText(s.getDomanda());
		domanda.setWrapText(true);
		nomeSessioneLabel.setText(s.getNome());
	}

}

