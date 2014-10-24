package slogo.UI;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ListViewSLOGOVariables extends ListViewAllSLOGO {
    
    private Map<String, Object> myMap;

    public ListViewSLOGOVariables(Group root){
        super(root);
    }
    
    public ListViewSLOGOVariables(VBox vBox) {
    	super(vBox);
    }
    
    @Override
    protected void create(){
        createThings(AppConstants.LIST_BLOCKS_HEIGHT, AppConstants.LIST_BLOCKS_WIDTH,  
                                           "SLOGO Variables", 1, Color.BLACK);
        initializeVariablesMap();
        myListViewCreator.getListView().setItems(updateObservableVariables());
    }
    
    private void initializeVariablesMap(){
        myMap = new HashMap<String, Object>();
        myMap.put("Turtle X Position:     ", 0);
        myMap.put("Turtle Y Position:     ", 0);
        myMap.put("Turtle Heading:        ", 0);
        myMap.put("Pen Down?:     ", true);
    }
    
    private ObservableList<String> updateObservableVariables(){
        ObservableList<String> variablesList = FXCollections.observableArrayList();
        for(String key : myMap.keySet()){
                variablesList.add(key.toString() + myMap.get(key).toString());
        }
        return variablesList;
    }
    
    protected Map<String, Object> getMap(){
        return myMap;
    }
}
