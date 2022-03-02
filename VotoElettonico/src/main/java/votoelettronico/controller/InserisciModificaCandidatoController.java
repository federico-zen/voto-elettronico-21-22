package votoelettronico.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import votoelettronico.dao.PartecipanteDAO;
import votoelettronico.factory.DAOFactory;
import votoelettronico.model.Candidato;
import votoelettronico.model.Partito;
import votoelettronico.model.Scrutinatore;

public class InserisciModificaCandidatoController extends Controller implements Initializable {
	Scrutinatore logged;
	Partito p ;
	Candidato c;

    @FXML
    private Label addLabel;

    @FXML
    private Button backBtn;

    @FXML
    private TextField cognomeTF;

    @FXML
    private ComboBox<Partito> listaPartiti;

    @FXML
    private TextField nomeTF;

    @FXML
    private Button saveBtn;

    @FXML
    private Label updateLabel;

    @FXML
    void back(ActionEvent event) {
    	changeView("gestione_candidati.fxml", logged);
    }

    @FXML
    void save(ActionEvent event) {
    	PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
    	
    	String nome = nomeTF.getText();
    	String cognome = cognomeTF.getText();
    	Partito selected = listaPartiti.getSelectionModel().getSelectedItem();
    
    	
    	if(c!= null) {
    		c.setNome(nome);
    		c.setCognome(cognome);
    		dao.updateCandidato(c, selected.getId());
    		System.out.println("Modificato");
    	}else {
    		System.out.println("Inserito");
    		dao.saveCandidato(new Candidato(nome, cognome), selected.getId());
    	}
    	
    	
    	
    	changeView("gestione_candidati.fxml", logged);
    }

	@Override
	public void init(Object parameters) {
		List l = (List) parameters;
		logged = (Scrutinatore) l.get(0);
		
		if(l.size()>1) {
			switch (l.size()) {
			case 2:
				p = (Partito) l.get(1);
				listaPartiti.getSelectionModel().select(p);
				addLabel.setVisible(true);
				updateLabel.setVisible(false);
				break;
			case 3:
				p = (Partito) l.get(1);
				listaPartiti.getSelectionModel().select(p);
				c = (Candidato) l.get(2);
				nomeTF.setText(c.getNome());
				cognomeTF.setText(c.getCognome());
				addLabel.setVisible(false);
				updateLabel.setVisible(true);
				
				break;
				
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
		listaPartiti.getItems().setAll(dao.getPartiti());
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
		
		
	}

}
