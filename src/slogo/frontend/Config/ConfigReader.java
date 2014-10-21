package slogo.frontend.Config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import slogo.UI.AppConstants;


/**
 * 
 * @author Michael Deng
 *
 */
public class ConfigReader {

	private Map<String, String> configMap;
	private String path = "src/slogo/frontend/Config/Config.txt";
	private ParameterDistributor paramDistributor;

	public ConfigReader() {
		configMap = new HashMap<String, String>();
		paramDistributor = new ParameterDistributor();
	}

	public ParameterDistributor readFile() throws IOException{

		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);

		int numOfLines = calculateNumOfLines();

		populateTextData(numOfLines, textReader);

		textReader.close();

		paramDistributor = paramDistributor.readConfigHashMap(configMap);
		return paramDistributor;
	}
	
	private int calculateNumOfLines() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		String aLine;
		int numberOfLines = 0;
		while ((aLine = textReader.readLine()) != null) {
			numberOfLines++;
		}
		return numberOfLines;
	}
	
	private void populateTextData(int count, BufferedReader textReader) throws IOException{
		String textLine;
		for (int i = 0; i < count; i++) {
			textLine = textReader.readLine();
			configMap.put(textLine.substring(textLine.indexOf(' ')+1), textLine.substring(0, textLine.indexOf(' ')));
			//System.out.println(textLine.substring(textLine.indexOf(' ')+1));
			//System.out.println(configMap.get(textLine.substring(textLine.indexOf(' ')+1)));
		}
	}
	
	private Map<String, String> hashMapTest() {
		Map<String, String> map = new HashMap<String, String>();
		int a = 3;
		int b = 4;
		map.put("variable a", a + "");
		map.put("variable b", b + "");
		map.put("color BackgroundColor", "yellow");
		map.put("color PenColor", "black");
		return map;
	}
		
}
