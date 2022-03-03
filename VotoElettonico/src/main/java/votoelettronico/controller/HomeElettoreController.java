package votoelettronico.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import votoelettronico.dao.SessioneDAO;
import votoelettronico.factory.AlertFactory;
import votoelettronico.factory.DAOFactory;
import votoelettronico.model.Elettore;
import votoelettronico.model.Sessione;

public class HomeElettoreController extends Controller implements Initializable {
	
	Elettore logged;

    @FXML
    private Button logOutBtn;

    @FXML
    private ListView<Sessione> sessioniLV;

    @FXML
    private Button votoBtn;

    @FXML
    void logOut(ActionEvent event) {
    	changeView("main.fxml", null);
    }

    @FXML
    void vaiAlVoto(ActionEvent event) {
    	Sessione s = null;
    	s = sessioniLV.getSelectionModel().getSelectedItem();
    	
    	if(s!=null) {
    		String modVoto = s.getMod_voto();
    		
    		//cambiare a seconda della modalità di voto
    		
    	}else {
    		AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Selezionare una sessione").showAndWait();
    	}
    }

	@Override
	public void init(Object parameters) {
		logged = (Elettore) parameters;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SessioneDAO dao =(SessioneDAO) DAOFactory.getInstance().getSessioneDAO();
		sessioniLV.setCellFactory(new Callback<ListView<Sessione>, ListCell<Sessione>>() {

		    @Override
		    public ListCell<Sessione> call(ListView<Sessione> list) {
		        ListCell<Sessione> cell = new ListCell<Sessione>() {
		            @Override
		            public void updateItem(Sessione item, boolean empty) {
		                super.updateItem(item, empty);
		                if(item!= null) {
		                	setText(item.getNome());
		                }else {
		                	setText(null);
		                }
		            }
		        };

		        return cell;
		    }
		});
		
		
		sessioniLV.getItems().setAll(dao.getAllActive());
		
	}

}

