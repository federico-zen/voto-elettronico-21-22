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
import votoelettronico.model.Partito;
import votoelettronico.model.Scrutinatore;
import votoelettronico.model.Sessione;

public class TerminaSessioneController extends Controller implements Initializable {
	
	Scrutinatore logged;

    @FXML
    private Button backBtn;

    @FXML
    private Button endSessionBtn;

    @FXML
    private ListView<Sessione> sessioniAttiveLV;

    @FXML
    private ListView<Sessione> sessioniTerminateLV;

    @FXML
    private Button viewResultBtn;

    @FXML
    void back(ActionEvent event) {
    	changeView("home_gestore.fxml", logged);
    }

    @FXML
    void endSession(ActionEvent event) {
    	Sessione s =sessioniAttiveLV.getSelectionModel().getSelectedItem();
    	if(s!= null) {
    		SessioneDAO dao = (SessioneDAO) DAOFactory.getInstance().getSessioneDAO();
    		dao.closeSession(s);
    		changeView("termina_sessione.fxml", logged);
    		
    	}else {
    		AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Seleziona una sessione da terminare");
    	}
    }

    @FXML
    void viewResult(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		logged = (Scrutinatore) parameters;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		sessioniAttiveLV.setCellFactory(new Callback<ListView<Sessione>, ListCell<Sessione>>() {

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
		
		sessioniTerminateLV.setCellFactory(new Callback<ListView<Sessione>, ListCell<Sessione>>() {

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
		
		SessioneDAO dao = (SessioneDAO) DAOFactory.getInstance().getSessioneDAO();
		sessioniAttiveLV.getItems().setAll(dao.getAllActive());
		sessioniTerminateLV.getItems().setAll(dao.getAllClosed());
		
	}

}
