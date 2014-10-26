package slogo.frontend.Config;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

import javafx.scene.paint.Color;

/**
 * NOT FULLY IMPLEMENTED OR COMPLETED.
 * NOT REPRESENTATIVE OF THE REST OF THE PROJECT
 * 
 * @author Michael Deng
 *
 */
public class ParameterDistributor {
	
	Map<String, String> configMap = new HashMap<String, String>();
	
	Map<String, Integer> variableMap = new HashMap<String, Integer>();
	Map<String, Color> colorMap = new HashMap<String, Color>();
	public int numOfTurtles;
	
	public ParameterDistributor readConfigHashMap(Map<String, String> map) {
		this.configMap = map;
		for(String s: configMap.keySet()) {
			distributeVariables(s);
		}
		return this;
	}
	
	private void distributeVariables(String s) {
		if (s.contains("variable")) {
			int value = Integer.parseInt(configMap.get(s));
			variableMap.put(s.substring(s.indexOf(' ') + 1), value);
		}
		else if (s.contains("color")) {
			try {
				Field field = Class.forName("javafx.scene.paint.Color").getField(configMap.get(s));
			    Color color = (Color) field.get(null);
			    colorMap.put(s.substring(s.indexOf(' ') + 1), color);
			} catch (Exception e) {
			    Color color = null; 
			    System.out.println("No color found");
			}
		}
		else if (s.contains("turtles")) {
			numOfTurtles = Integer.parseInt(configMap.get(s));
		}
	}
	
	public Map<String, Integer> getVariableMap() {
		return variableMap;
	}
	
	public Map<String, Color> getColorMap() {
		return colorMap;
	}
	
	public int getNumOfTurtles() {
		return numOfTurtles;
	}
	
	
	
	
}
