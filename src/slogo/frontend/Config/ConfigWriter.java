package slogo.frontend.Config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigWriter {

	private String path = "src/slogo/frontend/Config/Config.txt";
	
	
	
	public void writeToTextFile() {
		BufferedWriter writer = null;
	    try {
	        writer = new BufferedWriter(new FileWriter(path));
	        for (int i = 0; i < 10; i++) {
	    		writer.write("13 yo");
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
}
