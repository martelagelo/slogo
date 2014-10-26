package slogo.backend.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Languages {
	String[] languages = { "Chinese", "French", "Italian", "Portuguese", "Russian", "English" };
	public Languages() throws FileNotFoundException{
		for (String language: languages) {
			buildMap(language);
		}
	}

	Map<String,String> languageMap = new HashMap<String,String>();
	private static final String ADDRESS = "src/resources/languages/";
	private static final String SUFFIX = ".properties";
	public void buildMap(String filename) throws FileNotFoundException{
		File file = new File(ADDRESS+filename+SUFFIX);
		Scanner scan = new Scanner(file);

		String s;

		while(scan.hasNextLine()){
			s = scan.nextLine();
			if(!s.startsWith("#")){
				String[] splitted = s.split(" = ");
				String value = splitted[0];
				String [] keys = splitted[1].split(",");
				for(String key:keys){
					languageMap.put(key,value);
				}
			}
		}
		scan.close();

	}
	public Map<String,String> returnMap() {
		return languageMap;
	}
}
