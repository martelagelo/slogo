package slogo.backend.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Languages {

    Map<String,String> languageMap = new HashMap<String,String>();
   
    public void buildMap(String address) throws FileNotFoundException{
    File file = new File(address);
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
