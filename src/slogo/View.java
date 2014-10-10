package slogo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import slogo.UI.CommandMapInitializer;
import slogo.UI.MethodRunner;
import slogo.UI.Turtle;

public class View implements IView{

	private Map<String, Method> commandMap = new HashMap<String, Method>();
	private CommandMapInitializer CMP;
	private MethodRunner runner;
	
	
	
	public void init(Group root, Canvas canvas, Turtle turtle) {
		CMP = new CommandMapInitializer(runner);
		//commandMap = CMP.init();
		runner = new MethodRunner(root, canvas, turtle);
	}
	
	public void sendCommandToBackend(String command) {
		
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
