package votoelettronico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import votoelettronico.model.Elettore;

public class HomeElettoreController extends Controller {
	
	Elettore logged;

    @FXML
    private Button logOutBtn;

    @FXML
    private ListView<?> sessioniLV;

    @FXML
    private Button votoBtn;

    @FXML
    void logOut(ActionEvent event) {
    	changeView("main.fxml", null);
    }

    @FXML
    void vaiAlVoto(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		logged = (Elettore) parameters;
	}

}

