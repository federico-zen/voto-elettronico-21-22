package votoelettronico.controller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import votoelettronico.factory.AlertFactory;
import votoelettronico.model.Elettore;
import votoelettronico.model.Sessione;

public class CategoricoPreferenzeController extends Controller {
	
	Elettore logged;
	Sessione s = null;

    @FXML
    private Button confirmBtn;

    @FXML
    private ListView<?> listaCandidati;

    @FXML
    private ListView<?> listaPartiti;

    @FXML
    private ImageView logo;

    @FXML
    private Label nomeSessioneLabel;

    @FXML
    private Button whiteBtn;

    @FXML
    void astenuto(ActionEvent event) {
    	//alert conferma
    	Alert alert = AlertFactory.getInstance().getSlimAlert(AlertType.CONFIRMATION, "Sei sicuro di voler confermare l'astensione?");
    	alert.showAndWait();
    	
    	if(alert.getResult() == ButtonType.OK) {
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION, "Azione Registrata!").showAndWait();
    		
    		//Carica Voto
    		//Change View
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
    		//Change View
    	}
    		
    }

    @FXML
    void showCandidati(MouseEvent event) {

    }

	@Override
	public void init(Object parameters) {
		List l = (List) parameters;
		logged = (Elettore) l.get(0);
		s = (Sessione) l.get(1);
		nomeSessioneLabel.setText(s.getNome());
	}

}

