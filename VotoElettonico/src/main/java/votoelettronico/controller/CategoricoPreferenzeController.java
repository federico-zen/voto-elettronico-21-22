package votoelettronico.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import votoelettronico.dao.PartecipanteDAO;
import votoelettronico.dao.SessioneDAO;
import votoelettronico.dao.VotoDAO;
import votoelettronico.factory.AlertFactory;
import votoelettronico.factory.DAOFactory;
import votoelettronico.model.Candidato;
import votoelettronico.model.Elettore;
import votoelettronico.model.Partito;
import votoelettronico.model.SchedaBianca;
import votoelettronico.model.Sessione;

public class CategoricoPreferenzeController extends Controller implements Initializable {
	
	Elettore logged;
	Sessione s = null;

    @FXML
    private Button confirmBtn;

    @FXML
    private ListView<Candidato> listaCandidati;

    @FXML
    private ListView<Partito> listaPartiti;

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
    		List<Candidato> selected = listaCandidati.getSelectionModel().getSelectedItems();
    		
    		
    		
    		//Change View
    		changeView("home_elettore.fxml", logged);
    	}
    		
    }

    @FXML
    void showCandidati(MouseEvent event) {
    	
    	Partito p = listaPartiti.getSelectionModel().getSelectedItem();
    	if (p!= null) {
    		listaCandidati.getItems().clear();
    		
    		listaCandidati.setCellFactory(new Callback<ListView<Candidato>, ListCell<Candidato>>() {

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
				listaCandidati.getItems().add(it.next());
			}
			
			try {
				logo.setImage(new Image(p.getLogo().getBinaryStream()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else {
    		AlertFactory.getInstance().getSlimAlert(AlertType.ERROR, "Selezionare un Partito").showAndWait();
    	}
    }

	@Override
	public void init(Object parameters) {
		List l = (List) parameters;
		logged = (Elettore) l.get(0);
		s = (Sessione) l.get(1);
		nomeSessioneLabel.setText(s.getNome());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
		List<Partito> l = dao.getPartiti();
		
		listaPartiti.setCellFactory(new Callback<ListView<Partito>, ListCell<Partito>>() {

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
		listaPartiti.getItems().setAll(l);
		listaCandidati.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
	}

}

