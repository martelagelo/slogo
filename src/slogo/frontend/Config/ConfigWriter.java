package slogo.frontend.Config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigWriter {

	private String path = "src/slogo/frontend/Config/Config.txt";	
	
	
	public void writeToTextFile() {
		BufferedWriter writer = null;
	    try {
	        writer = new BufferedWriter(new FileWriter(path));
	        for (String s: hashMapTest().keySet()) {
	    		writer.write(hashMapTest().get(s) + " " + s);
	    		writer.newLine();
	    	}
	    } catch (IOException e) {
	        System.err.println(e);
	    } finally {
	        if (writer != null) {
	            try {
	                writer.close();
	            } catch (IOException e) {
	                System.err.println(e);
	            }
	        }
	    }
	}
	
	private Map<String, String> hashMapTest() {
		Map<String, String> map = new HashMap<String, String>();
		int a = 3;
		int b = 4;
		map.put("variable a", a + "");
		map.put("variable b", b + "");
		return map;
	}
}
