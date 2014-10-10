package slogo.UI;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CommandMapInitializer {

	private Map<String, Method> commandMap = new HashMap<String, Method>();
	private MethodRunner runner;
	
	public CommandMapInitializer(MethodRunner runner) {
		this.runner = runner;
	}
	
	
	public Map init() {
		commandMap.put("fd", runner.moveTurtleForward(0, 0));
		return commandMap;
	}
	
}
