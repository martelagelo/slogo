package slogo.UI;

import slogo.View;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author Michael
 *
 */
public class Main extends Application {

	private ModuleCreationHelper ModulePopulator; 
	private View view;

	public void start(Stage primaryStage) {
		Group root = new Group();
		ModulePopulator = new ModuleCreationHelper(root);
		view = new View();
		Scene scene = new Scene(root, AppConstants.STAGE_WIDTH, AppConstants.STAGE_HEIGHT);
		scene.setFill(Color.BISQUE);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		ModulePopulator.createMainPageModules();

		view.init(root, ModulePopulator.getCanvas(), ModulePopulator.getTurtle());

		primaryStage.setTitle("SLogo!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
