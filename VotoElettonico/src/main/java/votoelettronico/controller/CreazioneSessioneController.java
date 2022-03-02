package votoelettronico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import votoelettronico.model.Scrutinatore;

public class CreazioneSessioneController extends Controller {
	
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
    private ListView<?> partitiAggiuntiLV;

    @FXML
    private ListView<?> partitiInseribiliLV;

    @FXML
    private Button rimuoviBtn;

    @FXML
    private Button startBtn;

    @FXML
    private ComboBox<?> tipologiaVittoriaCB;

    @FXML
    private ComboBox<?> tipologiaVotoCB;

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {
    	changeView("home_gestore.fxml", logged);
    }

    @FXML
    void remove(ActionEvent event) {

    }

    @FXML
    void selectedVoto(ActionEvent event) {

    }

    @FXML
    void start(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		logged = (Scrutinatore) parameters;
	}

}
