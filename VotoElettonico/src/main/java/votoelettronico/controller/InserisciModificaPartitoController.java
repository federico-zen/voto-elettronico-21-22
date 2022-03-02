package votoelettronico.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.sql.rowset.serial.SerialBlob;

import com.mysql.cj.result.BinaryStreamValueFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import votoelettronico.dao.PartecipanteDAO;
import votoelettronico.factory.DAOFactory;
import votoelettronico.logger.VotoLogger;
import votoelettronico.model.Candidato;
import votoelettronico.model.Partito;
import votoelettronico.model.Scrutinatore;

public class InserisciModificaPartitoController extends Controller {
	
	Scrutinatore logged;
	Blob b = null;
	Partito p = null;

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
    void addImage(ActionEvent event) throws Exception {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open File");
    	File file = fileChooser.showOpenDialog(null); // you could pass a stage reference here if you wanted
    	if(file != null) {
    		
    		InputStream is = new FileInputStream(file);
    		b = new SerialBlob(is.readAllBytes());
    		
    		logo.setImage(new Image(b.getBinaryStream()));
    	}
    }

    @FXML
    void back(ActionEvent event) {
    	changeView("home_gestore.fxml",logged);
    }

    @FXML
    void save(ActionEvent event) {
    	PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
    	String nome = nameTF.getText();
    	  if (!nome.isEmpty()) {
    		if(p == null && b != null) {
    	    	p = new Partito(nome,new ArrayList<Candidato>(),b);	
    	    	dao.savePartito(p);
    	    	VotoLogger.writeToLog(p.getNome() +" Inserito da " + logged.getNome() + " " + logged.getCognome() );
    	    	
    	    }else {
    	    	
    	    	p.setNome(nome);
    	    	if(b != null) {
    	    		p.setLogo(b);
    	    	}
    	    	dao.update(p, null);
    	    	VotoLogger.writeToLog(p.getNome() +" Aggiornato da " + logged.getNome() + " " + logged.getCognome() );
    	    }
    		changeView("gestione_candidati.fxml", logged);
    		
    	  }else {
    		Alert t = new Alert(AlertType.ERROR, "Inseristi tutti i dati");
      		t.setHeaderText(null);
    		t.showAndWait();
    		
    		
    		
    	  }
    	
    	
    }

	@Override
	public void init(Object parameters) {
		List l = (List) parameters;
		
		logged = (Scrutinatore) l.get(0);
		if(l.size()>1) {
			p = (Partito) l.get(1);
			nameTF.setText(p.getNome());
			try {
				logo.setImage(new Image(p.getLogo().getBinaryStream()));
			} catch (SQLException e) {
				VotoLogger.writeToLog("Error : ", Level.WARNING, e);
			}
			
			addLabel.setVisible(false);
			updateLabel.setVisible(true);
		}else {
			addLabel.setVisible(true);
			updateLabel.setVisible(false);
		}
	}

}

