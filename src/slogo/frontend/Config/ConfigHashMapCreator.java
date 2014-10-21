package slogo.frontend.Config;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class ConfigHashMapCreator {
	
	private Map<String, String> configMap;
	private Map<String, String> variableMap;
	private int numOfTurtles; 
	
	public ConfigHashMapCreator() {
		configMap = new HashMap<String, String>();
	}
	
	public Map<String, String> getConfigHashMap() {
		return configMap;
	}
	
	public void setVariableMap(Map<String, String> map) {
		this.variableMap = map;
		for (String s: variableMap.keySet()) {
			configMap.put("variable " + s, variableMap.get(s));
		}
	}
	
	public void setNumOfTurtles(int turtles) {
		this.numOfTurtles = turtles;
		String str = fromIntToString(numOfTurtles);
		configMap.put("turtles", str);
	}
	
	public void setColorParam(Color color, String name) {
		String colorString = color.toString();
		configMap.put("color " + name, colorString);
	}
	
	private String fromIntToString(int i) {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(i);
		String strI = sb.toString();
		return strI;
	}
}
