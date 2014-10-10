package slogo.UI;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CommandMapInitializer {

	private Map<String, Method> commandMap = new HashMap<String, Method>();
	
	
	public Map init() {
		commandMap.put("fd", moveTurtleForward());
		return commandMap;
	}
	
}
