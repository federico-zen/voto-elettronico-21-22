package votoelettronico.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import votoelettronico.dao.PartecipanteDAO;
import votoelettronico.factory.AlertFactory;
import votoelettronico.factory.DAOFactory;
import votoelettronico.model.Candidato;
import votoelettronico.model.Partito;
import votoelettronico.model.Scrutinatore;

public class CreazioneSessioneController extends Controller implements Initializable{
	
	Scrutinatore logged;

    @FXML
    private Button aggiungiBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TextField domandaTF;

    @FXML
    private TextField nomeSessioneTF;

    @FXML
    private ListView<Partito> partitiAggiuntiLV;

    @FXML
    private ListView<Partito> partitiInseribiliLV;

    @FXML
    private Button rimuoviBtn;

    @FXML
    private Button startBtn;

    @FXML
    private ComboBox<String> tipologiaVittoriaCB;

    @FXML
    private ComboBox<String> tipologiaVotoCB;

    @FXML
    void add(ActionEvent event) {
    	Partito p = partitiInseribiliLV.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		partitiAggiuntiLV.getItems().add(p);
    		partitiInseribiliLV.getItems().remove(p);
    	}else {
    		AlertFactory.getInstance().getAlert(AlertType.ERROR, "Seleziona il partito da inserire").showAndWait();
    	}
    }

    @FXML
    void back(ActionEvent event) {
    	changeView("home_gestore.fxml", logged);
    }

    @FXML
    void remove(ActionEvent event) {
    	Partito p = partitiAggiuntiLV.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		partitiInseribiliLV.getItems().add(p);
    		partitiAggiuntiLV.getItems().remove(p);
    	}else {
    		AlertFactory.getInstance().getAlert(AlertType.ERROR, "Seleziona il partito da rimuovere").showAndWait();
    	}
    }

    @FXML
    void selectedVoto(ActionEvent event) {
    	String tipoVoto = tipologiaVotoCB.getSelectionModel().getSelectedItem();
    	
    	if(tipoVoto.equals("Referendum")) {
    		tipologiaVittoriaCB.getItems().setAll(List.of("Quorum","Senza-Quorum"));
    		partitiAggiuntiLV.setDisable(true);
    		partitiInseribiliLV.setDisable(true);
    		aggiungiBtn.setDisable(true);
    		rimuoviBtn.setDisable(true);
    		domandaTF.setDisable(false);
    		
    	}else {
    		tipologiaVittoriaCB.getItems().setAll(List.of("Maggioranza","Maggioranza-Assoluta"));
    		partitiAggiuntiLV.setDisable(false);
    		partitiInseribiliLV.setDisable(false);
    		aggiungiBtn.setDisable(false);
    		rimuoviBtn.setDisable(false);
    		domandaTF.setDisable(true);
    	}
    	
    	tipologiaVittoriaCB.getSelectionModel().selectFirst();
    }

    @FXML
    void start(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		logged = (Scrutinatore) parameters;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		setup();
		
		
	}
	
	private void setup() {
		
		//Liste
		PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
		partitiInseribiliLV.getItems().setAll(dao.getPartiti());
		
		partitiInseribiliLV.setCellFactory(new Callback<ListView<Partito>, ListCell<Partito>>() {

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
		
		partitiAggiuntiLV.setCellFactory(new Callback<ListView<Partito>, ListCell<Partito>>() {

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
		
		tipologiaVotoCB.getItems().setAll(List.of("Ordinale","Categorico","Categorico-Preferenze","Referendum"));		
		tipologiaVotoCB.getSelectionModel().selectFirst();
		
	}

}
