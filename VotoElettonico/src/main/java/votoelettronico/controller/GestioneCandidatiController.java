package votoelettronico.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import votoelettronico.dao.GenericDAO;
import votoelettronico.dao.PartecipanteDAO;
import votoelettronico.factory.DAOFactory;
import votoelettronico.logger.VotoLogger;
import votoelettronico.model.Candidato;
import votoelettronico.model.Partecipante;
import votoelettronico.model.Partito;
import votoelettronico.model.Scrutinatore;
import votoelettronico.model.Utente;

public class GestioneCandidatiController extends Controller implements Initializable{
	
	Scrutinatore logged;
	@FXML
	private ImageView logo;
	
    @FXML
    private Button addCandidatoBtn;

    @FXML
    private Button addPartitoBtn;

    @FXML
    private Button deleteCandidatoBtn;

    @FXML
    private Button deletePartitoBtn;

    @FXML
    private ListView<Candidato> listViewCandidato;

    @FXML
    private ListView<Partito> listViewPartito;

    @FXML
    private Button updateCandidatoBtn;
    
    @FXML
    private Button backBtn;

    @FXML
    private Button updatePartitoBtn;

    @FXML
    void addCandidato(ActionEvent event) {
    	Partito p = listViewPartito.getSelectionModel().getSelectedItem();
    	List params = new ArrayList();
    	params.add(logged);
    	if(p != null) {
    		params.add(p);
    		changeView("inserisci_modifica_candidato.fxml", params);
    	}else {
    		//Messaggio errore partito
    	}
    	/*Candidato c = listViewCandidato.getSelectionModel().getSelectedItem();
    	if(c != null) {
    		params.add(c);
    	}*/
    	
    	
    	
    }

    @FXML
    void addPartito(ActionEvent event) {
    	changeView("inserisci_modifica_partito.fxml",List.of(logged));
    }
    
    @FXML
    void back(ActionEvent event) {
    	changeView("home_gestore.fxml",logged);
    }
    
    
    @FXML
    void deleteCandidato(ActionEvent event) {
    	PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
    	Candidato c = listViewCandidato.getSelectionModel().getSelectedItem();
    	
    	if(c != null) {
    		
    		boolean result = listViewCandidato.getItems().remove(c);
    		
        	if(result) {
        		dao.delete(c);
        		Partito p = listViewPartito.getSelectionModel().getSelectedItem();
        		p.rimuoviCandidato(c);
        		VotoLogger.writeToLog(c.getNome() +" Eliminato da " + logged.getNome() + " " + logged.getCognome() );
        	}
    	}
    }

    @FXML
    void deletePartito(ActionEvent event) {
    	PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
    	Partito p = listViewPartito.getSelectionModel().getSelectedItem();
    	
    	if(p != null) {
    		
    		boolean result = listViewPartito.getItems().remove(p);
    		
        	if(result) {
        		dao.delete(p);
        		listViewCandidato.getItems().clear();
        		logo.setImage(null);
        		VotoLogger.writeToLog(p.getNome() +" Eliminato da " + logged.getNome() + " " + logged.getCognome() );
        	}
    	}
    }

    @FXML
    void selectedPartito(MouseEvent event) {
    		listViewCandidato.getItems().clear();
    		
    		Partito p = listViewPartito.getSelectionModel().getSelectedItem();
    		if (p != null) {
    			listViewCandidato.setCellFactory(new Callback<ListView<Candidato>, ListCell<Candidato>>() {

    			    @Override
    			    public ListCell<Candidato> call(ListView<Candidato> list) {
    			        ListCell<Candidato> cell = new ListCell<Candidato>() {
    			            @Override
    			            public void updateItem(Candidato item, boolean empty) {
    			                super.updateItem(item, empty);
    			                if(item!= null) {
    			                	setText(item.getNome()+ " "+ item.getCognome());
    			                }else {
    			                	setText(null);
    			                }
    			            }
    			        };

    			        return cell;
    			    }
    			});
    			
    			Iterator<Candidato> it = p.iterator();
    			while(it.hasNext()) {
    				listViewCandidato.getItems().add(it.next());
    			}
    			
    			try {
    				logo.setImage(new Image(p.getLogo().getBinaryStream()));
    			} catch (SQLException e) {
    				VotoLogger.writeToLog("Error : ", Level.WARNING, e);
    			}
    			
    		}
    		
    	
    }

    @FXML
    void updateCandidato(ActionEvent event) {
    	
    	
    }

    @FXML
    void updatePartito(ActionEvent event) {
    	Partito p = listViewPartito.getSelectionModel().getSelectedItem();
    	if(p!= null) {
    		changeView("inserisci_modifica_partito.fxml",List.of(logged,p));
    	}else{
    		Alert t = new Alert(AlertType.ERROR, "Seleziona il partito da modificare");
    		t.setHeaderText(null);
  			t.showAndWait();
    	}
    }
    
    @Override
	public void init(Object parameters) {
		logged = (Scrutinatore) parameters;
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
		
		listViewPartito.setCellFactory(new Callback<ListView<Partito>, ListCell<Partito>>() {

		    @Override
		    public ListCell<Partito> call(ListView<Partito> list) {
		        ListCell<Partito> cell = new ListCell<Partito>() {
		            @Override
		            public void updateItem(Partito item, boolean empty) {
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
		
		listViewPartito.getItems().setAll(dao.getPartiti());
		
	}

}
