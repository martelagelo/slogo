package slogo.UI;

import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class ListViewSLOGOVariables extends ListViewAllSLOGO {

    public ListViewSLOGOVariables(Group root){
        super(root);
    }
    
    @Override
    protected void create(){
        createThings(200, 200, AppConstants.ALL_SELECTORS_XPOS, AppConstants.TURTLE_IMAGE_YPOS + 80,
                                           "SLOGO Variables", 1, Color.BLACK);
        myListViewCreator.getListView().setItems(updateObservableVariables(initializeVariablesMap()));
    }
    
    private Map<String, Object> initializeVariablesMap(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("Turtle X Position:     ", 0);
        variables.put("Turtle Y Position:     ", 0);
        variables.put("Turtle Heading:        ", 0);
        variables.put("Pen Down?:     ", true);
        return variables;
    }
    
    private ObservableList<String> updateObservableVariables(Map<String,Object> variablesMap){
        ObservableList<String> variablesList = FXCollections.observableArrayList();
        for(String key : variablesMap.keySet()){
                variablesList.add(key.toString() + variablesMap.get(key).toString());
        }
        return variablesList;
    }
}
