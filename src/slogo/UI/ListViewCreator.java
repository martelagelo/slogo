package slogo.UI;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ListViewCreator {
    
    private ListView<String> myListView;
    
    public ListViewCreator(int height, int width){
        myListView = new ListView<String>();
        myListView.setPrefHeight(height);
        myListView.setPrefWidth(width);
    }
    
    protected ListView<String> getListView(){
        return myListView;
    }
    
    protected VBox createListViewWithLabel(String s, int fontsize, Color color){
        VBox listViewWithLabel = new VBox();
        LabelCreator lc = new LabelCreator(fontsize, color);
        Label l = lc.createLabel(s);
        listViewWithLabel.getChildren().addAll(l, myListView);
        return listViewWithLabel;
    }

}
