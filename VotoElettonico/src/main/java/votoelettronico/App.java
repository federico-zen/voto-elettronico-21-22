package votoelettronico;

import java.sql.SQLException;
import java.util.logging.Level;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import votoelettronico.dbconnection.DBConnection;
import votoelettronico.logger.VotoLogger;
import votoelettronico.model.Voto;

public class App extends Application {

private  static Scene primaryScene;
private static Stage stage;
	
	public static void main(String[] args) {
		
		 launch();
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VotoLogger.init();
		DBConnection.getInstance().openConnection();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/main.fxml"));
		Parent content = loader.load(); 
		
		Scene main = new Scene(content);
        primaryScene = main;
        stage= primaryStage;
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.setScene(main);
        primaryStage.show();
		
	}
	
	public static Scene getAppScene() {
        return primaryScene;
    }
	
	public static void resize() {
		stage.sizeToScene();
		stage.show();
	}
	
	@Override
	public void stop() {
		try {
			DBConnection.getInstance().closeConnection();
			System.exit(0);
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
	}

}
