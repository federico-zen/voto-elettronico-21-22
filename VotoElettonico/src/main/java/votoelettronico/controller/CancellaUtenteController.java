package votoelettronico.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import votoelettronico.dao.GenericDAO;
import votoelettronico.dao.UtenteDAO;
import votoelettronico.factory.DAOFactory;
import votoelettronico.logger.VotoLogger;
import votoelettronico.model.Scrutinatore;
import votoelettronico.model.Utente;

public class CancellaUtenteController extends Controller implements Initializable {
	
	Scrutinatore logged;

    @FXML
    private Button deleteBtn;
    
    @FXML
    private Button backBtn;

    @FXML
    private ListView<Utente> listView;

    @FXML
    private Label nome;
    
    @FXML
    private Label cognome;

    @FXML
    private Label ruolo;

    @FXML
    private Label username;

    @FXML
    void delete(ActionEvent event) {
    	UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
    	Utente t = listView.getSelectionModel().getSelectedItem();
    	if(t != null ) {
    		if(t.getCodiceFiscale().equals(logged.getCodiceFiscale())){
    			Alert x = new Alert(AlertType.ERROR,"Non puoi cancellarti da solo");
    			x.setHeaderText(null);
        		x.showAndWait();
    		}else {
    			dao.delete(t);
            	boolean result = listView.getItems().remove(t);
            	
            	if(result) {
            		VotoLogger.writeToLog(t.getCodiceFiscale() +" Eliminato da " + logged.getNome() + " " + logged.getCognome() );
            	}
            	nome.setText("");
            	cognome.setText("");
            	username.setText("");
            	ruolo.setText("");
    		}
    		
    	}
    	
    }

    @FXML
    void selected(MouseEvent event) {
    	Utente t = listView.getSelectionModel().getSelectedItem();
    	if(t!= null) {
    		nome.setText(t.getNome());
        	cognome.setText(t.getCognome());
        	username.setText(t.getCodiceFiscale());
        	if(t.isElettore()) {
        		ruolo.setText("Elettore");
        	}else {
        		ruolo.setText("Scrutinatore");
        	}
    	}
    	
    }
    
    @FXML
    void back(ActionEvent event) {
    	changeView("home_gestore.fxml", logged);
    }
    
	@Override
	public void init(Object parameters) {
		logged = (Scrutinatore) parameters;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Utente> lista = DAOFactory.getInstance().getUtenteDAO().getAll();
		
		listView.setCellFactory(new Callback<ListView<Utente>, ListCell<Utente>>() {

		    @Override
		    public ListCell<Utente> call(ListView<Utente> list) {
		        ListCell<Utente> cell = new ListCell<Utente>() {
		            @Override
		            public void updateItem(Utente item, boolean empty) {
		                super.updateItem(item, empty);
		                if(item!= null) {
		                	setText(item.getCodiceFiscale());
		                }else {
		                	setText(null);
		                }
		            }
		        };

		        return cell;
		    }
		});
		
		
		listView.getItems().addAll(lista);

		
		
	}

}
