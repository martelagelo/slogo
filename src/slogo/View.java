package slogo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import slogo.UI.CommandMapInitializer;

public class View implements IView{

	private Map<String, Method> commandMap = new HashMap<String, Method>();
	private CommandMapInitializer CMP;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		CMP = new CommandMapInitializer();
		commandMap = CMP.init();
	}
	
	public void sendCommandToBackend(String command) {
		
	}

	@Override
	public void error(String message) {
		// TODO Auto-generated method stub
	}

}
