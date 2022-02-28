package votoelettronico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class CancellaUtenteController extends Controller {

    @FXML
    private Label cognome;

    @FXML
    private Button deleteBtn;
    
    @FXML
    private Button backBtn;

    @FXML
    private ListView<?> listView;

    @FXML
    private Label nome;

    @FXML
    private Label ruolo;

    @FXML
    private Label username;

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void selected(MouseEvent event) {

    }
    
    @FXML
    void back(ActionEvent event) {

    }

	@Override
	public void init(Object parameters) {
		// TODO Auto-generated method stub
		
	}

}
