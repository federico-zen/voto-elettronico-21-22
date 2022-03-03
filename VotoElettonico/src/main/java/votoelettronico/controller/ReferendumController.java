package votoelettronico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import votoelettronico.model.Elettore;

public class ReferendumController extends Controller {
	
	Elettore logged;

    @FXML
    private Button confirmBtn;

    @FXML
    private TextArea domanda;

    @FXML
    private Label nomeSessioneLabel;

    @FXML
    private ToggleGroup scelta;

    @FXML
    private Button whiteBtn;

    @FXML
    void astenuto(ActionEvent event) {
    	//qualcosa
    	
    	//alert conferma
    }

    @FXML
    void confirm(ActionEvent event) {
    	//qualcosa
    	
    	//alert conferma
    }

	@Override
	public void init(Object parameters) {
		logged = (Elettore) parameters;
	}

}

