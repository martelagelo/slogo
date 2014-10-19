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

		FileReader fr = new FileReader("Config");
		BufferedReader textReader = new BufferedReader(fr);

		int numberOfLines = 1;
		String[] textData = new String[numberOfLines];

		for (int i = 0; i < numberOfLines; i++) {
			textData[i] = textReader.readLine();
		}

		textReader.close();
		System.out.println(textData[0]);
	}
	
	public static void main(String[] args) throws IOException {
		readFile();
	}
}
