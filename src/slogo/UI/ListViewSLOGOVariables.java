package slogo.UI;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ListViewSLOGOVariables extends ListViewAllSLOGO {
    
    private ObservableList<String> myList;

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
        myListViewCreator.getListView().setItems(myList);
    }
    
    private void initializeVariablesMap(){
        myList = FXCollections.observableArrayList();
        myList.add("Turtle X Position:     " + (double) 0);
        myList.add("Turtle Y Position:     " + (double) 0);
        myList.add("Turtle Heading:        " + (double) 0);
        myList.add("Pen Down?:             " + true);
        myList.add("Pen Thickness:         " + (int) 1);
    }
    
}
