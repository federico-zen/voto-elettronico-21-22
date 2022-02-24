package votoelettronico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import votoelettronico.logger.VotoLogger;
import votoelettronico.model.Scrutinatore;

public class HomeGestoreController extends Controller {

    @FXML
    private Button addCandidatoBtn;

    @FXML
    private Button addPartitoBtn;

    @FXML
    private Button addUserBtn;

    @FXML
    private Button deleteCandidatoBtn;

    @FXML
    private Button deletePartitoBtn;

    @FXML
    private Button deleteUserBtn;

    @FXML
    private Button endSessionBtn;

    @FXML
    private Button newSessionBtn;

    @FXML
    private Label welcomeLabel;

    @FXML
    void addCandidatoBtnEvent(ActionEvent event) {

    }

    @FXML
    void addPartitoBtnEvent(ActionEvent event) {

    }

    @FXML
    void addUserBtnEvent(ActionEvent event) {

    }

    @FXML
    void deleteCandidatoBtnEvent(ActionEvent event) {

    }

    @FXML
    void deletePartitoBtnEvent(ActionEvent event) {

    }

    @FXML
    void deleteUserBtnEvent(ActionEvent event) {

    }

    @FXML
    void endSessionBtnEvent(ActionEvent event) {

    }

    @FXML
    void newSessionBtnEvent(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		Scrutinatore t = (Scrutinatore) parameters;
		welcomeLabel.setText("Benvenuto "+ t.getNome());
		VotoLogger.writeToLog("Loggato : "+ t.getNome() + " come Scrutinatore");
		
	}

}
