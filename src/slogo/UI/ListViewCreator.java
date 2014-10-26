package slogo.UI;

import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class ListViewCreator {

    private ListView<String> myListView;

    public ListViewCreator (int height, int width) {
        myListView = new ListView<String>();
        myListView.setPrefHeight(height);
        myListView.setPrefWidth(width);
    }

    public ListViewCreator () {
        this(200, 200);
    }

    protected ListView<String> getListView () {
        return myListView;
    }

    protected VBox createListViewWithLabel (String s, int fontsize, Color color) {
        VBox listViewWithLabel = new VBox();
        LabelCreator lc = new LabelCreator(listViewWithLabel);
        lc.createLabel(s, fontsize, color);
        listViewWithLabel.getChildren().addAll(myListView);
        return listViewWithLabel;
    }

}
