package slogo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
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

public class View implements IView{

	private Map<String, Runnable> commandMap = new HashMap<String, Runnable>();
	private MethodRunner runner;
	private boolean existingFunction;
	
	private Stack<Line> pathStack;
	
	public void init(Group root, Canvas canvas, Turtle turtle) {
		pathStack = new Stack<Line>();
		runner = new MethodRunner(root, canvas, turtle, commandMap, pathStack);
		runner.init();
	}
	
	public void sendCommandToBackend(String command) {
		
	}
	
	public void executeCommand(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		existingFunction = false;
		runner.setInput(400, 500);
		for(String k: commandMap.keySet()) {
			if (k.equals(str)) {
				commandMap.get(k).run();
				existingFunction = true;
			}
		}
		if (!existingFunction) {
			error("Command does not exist!!!");
		}
	}

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
