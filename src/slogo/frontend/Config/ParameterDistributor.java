package slogo.frontend.Config;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

import javafx.scene.paint.Color;

public class ParameterDistributor {
	
	Map<String, String> configMap = new HashMap<String, String>();
	
	Map<String, Integer> variableMap = new HashMap<String, Integer>();
	Map<String, Color> colorMap = new HashMap<String, Color>();
	
	public void readConfigHashMap(Map<String, String> map) {
		this.configMap = map;
		for(String s: configMap.keySet()) {
			distributeVariables(s);
		}
		
		for(String s: variableMap.keySet()) {
			System.out.println(s);
			System.out.println(variableMap.get(s));
		}

		for(String s: colorMap.keySet()) {
			System.out.println(s);
			System.out.println(colorMap.get(s));
		}
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
		else {
			
		}
	}
	
}
