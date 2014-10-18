package slogo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import slogo.UI.MethodRunner;
import slogo.UI.Turtle;
import slogo.backend.impl.evaluation.ExecutionContext;

/**
 * October 5th, 2014
 * 
 * Version 1
 * 
 * @author Michael Deng
 * @author Nick Widmaier
 * @author Michael Ren
 * @author Eric Chen
 *
 */
public class View implements IView{

	private Map<String, Runnable> commandMap = new HashMap<String, Runnable>();
	private MethodRunner runner;
	private boolean existingFunction;
	
	private Stack<Line> pathStack;
	
	/**
	 * Initializes the View 
	 * 
	 * @param root The Group all the modules are placed on
	 * @param canvas The canvas on which the turtle is drawn
	 * @param turtle The moving object on the canvas
	 */
	public void init(Group root, Canvas canvas, Turtle turtle) {
		pathStack = new Stack<Line>();
		runner = new MethodRunner(root, canvas, turtle, commandMap, pathStack);
		runner.init();
		
		CommandExecutor CE = new CommandExecutor();
		List<Line> lines = new ArrayList<Line>();
		Line line = new Line(275, 275, 300, 300);
		lines.add(line);
		Line line2 = new Line(300, 300, 250, 350);
		lines.add(line2);
		Line line3 = new Line(250, 350, 200, 200);
		lines.add(line3);
		CE.setList(lines);
		CE.setType("move");
		executeInidividualCommands(CE);
	}
	
	/**
	 * Sends a command to the back-end
	 * @param command The code written by the user to be computed
	 */
	public void sendCommandToBackend(String command) {
		System.out.println(command);
	}
	
	/**
	 * Executes the command returned from the back-end
	 * @param str 
	 */
	public void executeInidividualCommands(CommandExecutor CE){
		existingFunction = false;
		runner.setCommandExecutor(CE);
		for(String k: commandMap.keySet()) {
			if (k.equals(CE.getType())) {
				commandMap.get(k).run();
				existingFunction = true;
			}
		}
		if (!existingFunction) {
			error("Command does not exist!!!");
		}
	}
	
//	public void executeCommand(ExecutionContext EC) {
//		for(String k: EC.turtles().keySet()) {
//			executeInidividualsCommands(EC.turtles().get(k));
//		}
//	}

	/**
	 * Creates and displays an error pop-up
	 */
	@Override
	public void error(String message) {
		 final Stage dialog = new Stage();
         dialog.initModality(Modality.APPLICATION_MODAL);
         VBox dialogVbox = new VBox(20);
         dialogVbox.getChildren().add(new Text(message));
         Scene dialogScene = new Scene(dialogVbox, 200, 100);
         dialog.setScene(dialogScene);
         dialog.show();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
