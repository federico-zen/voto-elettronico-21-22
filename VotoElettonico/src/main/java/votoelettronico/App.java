package votoelettronico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import votoelettronico.logger.VotoLogger;

public class App extends Application {

private  static Scene primaryScene;
	
	public static void main(String[] args) {
		
		 launch();
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VotoLogger.init();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/main.fxml"));
		Parent content = loader.load(); 
		
		Scene main = new Scene(content);
        primaryScene = main;
        primaryStage.setScene(main);
        primaryStage.show();
		
	}
	
	public static Scene getAppScene() {
        return primaryScene;
    }

}
