package slogo.frontend.Config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ConfigReader {

	private static String path = "C:/Users/Michael/workspace/slogo_team03/src/slogo/frontend/Config/Config.txt";

	public ConfigReader() {

	}

	public static void readFile() throws IOException{

		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);

		int numOfLines = calculateNumOfLines();
		
		String[] textData = new String[numOfLines];

		for (int i = 0; i < numOfLines; i++) {
			textData[i] = textReader.readLine();
			textData[i] = textData[i].substring(0, textData[i].indexOf(' '));
			System.out.println(textData[i]);
		}

		textReader.close();
	}
	
	private static int calculateNumOfLines() throws IOException{
		
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		String aLine;
		int numberOfLines = 0;
		while ((aLine = textReader.readLine()) != null) {
			numberOfLines++;
		}
		return numberOfLines;
	}
	
	public static void main(String[] args) throws IOException {
		readFile();
	}
}
