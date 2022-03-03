package votoelettronico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import votoelettronico.model.Scrutinatore;

public class TerminaSessioneController extends Controller {
	
	Scrutinatore logged;

    @FXML
    private Button backBtn;

    @FXML
    private Button endSessionBtn;

    @FXML
    private ListView<?> sessioniAttiveLV;

    @FXML
    private ListView<?> sessioniTerminateLV;

    @FXML
    private Button viewResultBtn;

    @FXML
    void back(ActionEvent event) {
    	changeView("home_gestore.fxml", logged);
    }

    @FXML
    void endSession(ActionEvent event) {

    }

    @FXML
    void viewResult(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		logged = (Scrutinatore) parameters;
	}

}
