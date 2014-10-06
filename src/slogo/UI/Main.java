package slogo.UI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private ModuleCreationHelper ModulePopulator; 
	
	public void start(Stage primaryStage) {
		Group root = new Group();
		ModulePopulator = new ModuleCreationHelper(root);
		Scene scene = new Scene(root, AppConstants.STAGE_WIDTH, AppConstants.STAGE_HEIGHT);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		ModulePopulator.createMainPageModules();
		primaryStage.setTitle("SLogo!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
