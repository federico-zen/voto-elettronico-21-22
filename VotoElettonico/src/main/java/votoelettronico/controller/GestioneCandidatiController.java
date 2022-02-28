package votoelettronico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import votoelettronico.model.Scrutinatore;

public class GestioneCandidatiController extends Controller {
	
	Scrutinatore logged;

    @FXML
    private Button addCandidatoBtn;

    @FXML
    private Button addPartitoBtn;

    @FXML
    private Button deleteCandidatoBtn;

    @FXML
    private Button deletePartitoBtn;

    @FXML
    private ListView<?> listViewCandidato;

    @FXML
    private ListView<?> listViewPartito;

    @FXML
    private Button updateCandidatoBtn;

    @FXML
    private Button updatePartitoBtn;

    @FXML
    void addCandidato(ActionEvent event) {

    }

    @FXML
    void addPartito(ActionEvent event) {

    }

    @FXML
    void deleteCandidato(ActionEvent event) {

    }

    @FXML
    void deletePartito(ActionEvent event) {

    }

    @FXML
    void selectedPartito(MouseEvent event) {

    }

    @FXML
    void updateCandidato(ActionEvent event) {

    }

    @FXML
    void updatePartito(ActionEvent event) {

    }
    
    @Override
	public void init(Object parameters) {
		logged = (Scrutinatore) parameters;
		
	}

}
