package slogo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import slogo.UI.MethodRunner;
import slogo.UI.Turtle;

public class View implements IView{

	private Map<String, Runnable> commandMap = new HashMap<String, Runnable>();
	private MethodRunner runner;
	
	public void init(Group root, Canvas canvas, Turtle turtle) {
		runner = new MethodRunner(root, canvas, turtle, commandMap);
		runner.init();
	}
	
	public void sendCommandToBackend(String command) {
		
	}
	
	public void executeCommand(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		runner.setInput(400, 500);
		for(String k: commandMap.keySet()) {
			if (k.equals(str)) {
				commandMap.get(k).run();
			}
		}
	}

	@Override
	public void error(String message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
