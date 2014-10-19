package slogo.UI;

import slogo.View;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * October 7th, 2014
 * 
 * Version 1
 * 
 * @author Michael Deng
 * @author Nick Widmaier
 * @author Michael Ren
 * @author Eric Chen
 *
 */
public class Main extends Application {

	private ModuleCreationHelper ModulePopulator; 
	private View view;
	private Stage primaryStage;

	/**
	 * Starts up the application
	 */
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		Group root = new Group();
		Scene scene = new Scene(root, AppConstants.STAGE_WIDTH, AppConstants.STAGE_HEIGHT);
		ModulePopulator = new ModuleCreationHelper(root, scene, primaryStage);
		view = new View();
		scene.setFill(AppConstants.BACKGROUND_COLOR);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		ModulePopulator.createMainPageModules();
		view.init(root, ModulePopulator.getCanvas(), ModulePopulator.getTurtle());
		ModulePopulator.setView(view);
		
		primaryStage.setTitle("SLogo!");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		System.out.println("App Has Started!");
	}


	/**
	 * Launches the application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
