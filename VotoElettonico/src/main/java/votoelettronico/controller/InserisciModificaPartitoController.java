package votoelettronico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import votoelettronico.model.Scrutinatore;

public class InserisciModificaPartitoController extends Controller {
	
	Scrutinatore logged;

    @FXML
    private Button addImageBtn;

    @FXML
    private Label addLabel;

    @FXML
    private Button backBtn;

    @FXML
    private ImageView logo;

    @FXML
    private TextField nameTF;

    @FXML
    private Button saveBtn;

    @FXML
    private Label updateLabel;

    @FXML
    void addImage(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {
    	changeView("home_gestore.fxml",logged);
    }

    @FXML
    void save(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		logged = (Scrutinatore) parameters;
	}

}

