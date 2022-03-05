package votoelettronico.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import votoelettronico.dao.PartecipanteDAO;
import votoelettronico.dao.SessioneDAO;
import votoelettronico.factory.AlertFactory;
import votoelettronico.factory.DAOFactory;
import votoelettronico.logger.VotoLogger;
import votoelettronico.model.Candidato;
import votoelettronico.model.Elettore;
import votoelettronico.model.Partecipante;
import votoelettronico.model.Partito;
import votoelettronico.model.Sessione;

public class OrdinaleController extends Controller implements Initializable {
	
	Elettore logged;
	Sessione s = null;
	Map<Candidato,Partito> m = null;
	Map<Integer, Partecipante> position = null;

    @FXML
    private Button addBtn;

    @FXML
    private Button confirmBtn;

    @FXML
    private Label informationLabel;

    @FXML
    private Label informationLabel2;

    @FXML
    private ListView<Partecipante> listaPartecipanti;

    @FXML
    private ListView<Partecipante> listaPartecipantiInseriti;

    @FXML
    private ImageView logo;

    @FXML
    private Label nomePartitoLabel;

    @FXML
    private Label nomeSessioneLabel;

    @FXML
    private ComboBox<Integer> posizioneCB;

    @FXML
    private Button resetBtn;

    @FXML
    private Button whiteBtn;

    @FXML
    void addPartecipante(ActionEvent event) {
    	Partecipante p = listaPartecipanti.getSelectionModel().getSelectedItem();
    	listaPartecipanti.getItems().remove(p);
    	int pos = posizioneCB.getSelectionModel().getSelectedIndex();
    	listaPartecipantiInseriti.getItems().add(p);
    	position.put(pos, p);
    	posizioneCB.getItems().remove(pos);
    	posizioneCB.getSelectionModel().selectFirst();
    	listaPartecipantiInseriti.getItems().setAll(position.values());
    }

    @FXML
    void astenuto(ActionEvent event) {
    	//alert conferma
    	Alert alert = AlertFactory.getInstance().getSlimAlert(AlertType.CONFIRMATION, "Sei sicuro di voler confermare l'astensione?");
    	alert.showAndWait();
    	
    	if(alert.getResult() == ButtonType.OK) {
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION, "Azione Registrata!").showAndWait();
    		
    		//Carica Voto
    		SessioneDAO dao = (SessioneDAO) DAOFactory.getInstance().getSessioneDAO();
    		dao.addVotazione(s, logged);
    		
    		//Carica Scheda
    		
    		
    		//Change View
    		changeView("home_elettore.fxml", logged);
    	}
    }

    @FXML
    void confirm(ActionEvent event) {
    	//alert conferma
    	Alert alert = AlertFactory.getInstance().getSlimAlert(AlertType.CONFIRMATION, "Sei sicuro di voler confermare il tuo voto?");
    	alert.showAndWait();
    	
    	if(alert.getResult() == ButtonType.OK) {
    		AlertFactory.getInstance().getSlimAlert(AlertType.INFORMATION, "Azione Registrata!").showAndWait();
    		
    		
    		//Carica Voto
    		SessioneDAO dao = (SessioneDAO) DAOFactory.getInstance().getSessioneDAO();
    		dao.addVotazione(s, logged);
    		
    		//Carica Scheda
    		//List<Candidato> selected = listaCandidati.getSelectionModel().getSelectedItems();
    		
    		
    		
    		//Change View
    		changeView("home_elettore.fxml", logged);
    	}
    }
    
    @FXML
    void showInformation(MouseEvent event) {
    	if (s.getMod_voto().equalsIgnoreCase("ordinale-partiti")) {
    		Partito p = (Partito) listaPartecipanti.getSelectionModel().getSelectedItem();
    		if(p!=null) {
    			nomePartitoLabel.setText(p.getNome());
    			try {
					logo.setImage(new Image(p.getLogo().getBinaryStream()));
				} catch (SQLException e) {
					VotoLogger.writeToLog("Error : ", Level.WARNING, e);
				}
    		}else {
    			//Errore
    		}
    	}else {
    		Candidato c = (Candidato) listaPartecipanti.getSelectionModel().getSelectedItem();
    		if(c!=null) {
    			Partito p = m.get(c);
    			nomePartitoLabel.setText(p.getNome());
    			try {
					logo.setImage(new Image(p.getLogo().getBinaryStream()));
				} catch (SQLException e) {
					VotoLogger.writeToLog("Error : ", Level.WARNING, e);
				}
    		}else {
    			//Errore
    		}
    	
    	}
    }

    @FXML
    void reset(ActionEvent event) {
    	changeView("ordinale.fxml",List.of(logged,s));
    }

	@Override
	public void init(Object parameters) {
		List l = (List) parameters;
		logged = (Elettore) l.get(0);
		s = (Sessione) l.get(1);
		nomeSessioneLabel.setText(s.getNome());
		if (s.getMod_voto().equalsIgnoreCase("ordinale-partiti")) {
			informationLabel.setText("Selezionare un partito dalla lista");
			informationLabel2.setText("Lista partiti inseriti");
		} else {
			informationLabel.setText("Selezionare un candidato dalla lista");
			informationLabel2.setText("Lista candidati inseriti");
		}
		setupListe();
		position = new TreeMap<>();
	}
	
	private void setupListe() {
		PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
		List<Partito> l = dao.getPartiti();
		
		if (s.getMod_voto().equalsIgnoreCase("ordinale-partiti")) {
			
			//Elenco dei Partiti
			listaPartecipanti.setCellFactory(new Callback<ListView<Partecipante>, ListCell<Partecipante>>() {

			    @Override
			    public ListCell<Partecipante> call(ListView<Partecipante> list) {
			        ListCell<Partecipante> cell = new ListCell<Partecipante>() {
			            @Override
			            public void updateItem(Partecipante item, boolean empty) {
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
			listaPartecipanti.getItems().setAll(l);
			
			//Init combobox
			int numP = dao.getNPartiti();
			if (numP < 10) {
				for (int i = 1; i < numP+1; i++) {
					posizioneCB.getItems().add(i);				
				}
			} else {
				posizioneCB.getItems().addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
			}
		} else {
			//Elenco dei Candidati 
			m = new HashMap<>();
			
			listaPartecipanti.setCellFactory(new Callback<ListView<Partecipante>, ListCell<Partecipante>>() {

			    @Override
			    public ListCell<Partecipante> call(ListView<Partecipante> list) {
			        ListCell<Partecipante> cell = new ListCell<Partecipante>() {
			            @Override
			            public void updateItem(Partecipante item, boolean empty) {
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
			
			
			
			for (Partito partito : l) {
				Iterator<Candidato> it = partito.iterator();
				while(it.hasNext()) {
					Candidato c = it.next();
					m.put(c, partito);
				}
			}
			
			listaPartecipanti.getItems().setAll(m.keySet());
			
			//Init combobox
			int numC = dao.getNCandidati();
			if (numC < 10) {
				for (int i = 1; i < numC+1; i++) {
					posizioneCB.getItems().add(i);				
				}
			} else {
				posizioneCB.getItems().addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
			}
			
		}
		posizioneCB.getSelectionModel().selectFirst();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
