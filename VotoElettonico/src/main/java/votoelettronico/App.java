package votoelettronico;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import votoelettronico.dbconnection.DBConnection;

public class App extends Application {

private  static Scene primaryScene;
	
	public static void main(String[] args) {
		
		 launch();
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/main.fxml"));
		Parent content = loader.load(); 
		
		Scene main = new Scene(content);
        primaryScene = main;
        primaryStage.setScene(main);
        primaryStage.show();
		
	}
	
	@Override
	public void stop(){
	    //Chiudere le connessioni
		
	    
	}
	
	public static Scene getAppScene() {
        return primaryScene;
    }

}
