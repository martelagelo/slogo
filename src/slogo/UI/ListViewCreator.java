//This entire file is part of my masterpiece.
//Nick Widmaier
package slogo.UI;

import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * This class aids in the construction of ListViews
 * Used in conjunction with the ListViewAllSLOGO class, whose methods instantiate
 * a ListViewCreator which deals with the layouts for the ListView to be created
 * as well as the labels for which we attach to each ListView
 * @author Nick Widmaier
 *
 */
public class ListViewCreator {

    private ListView<String> myListView;
    
    /**
     * constructor setting the properties of the ListView
     * @param height
     * @param width
     */
    public ListViewCreator (int height, int width) {
        myListView = new ListView<String>();
        myListView.setPrefHeight(height);
        myListView.setPrefWidth(width);
    }
    /**
     * constructor without parameters that relies on constants that are
     * repeated for our code
     */
    public ListViewCreator () {
        this(AppConstants.DEFAULT_LISTVIEW_HEIGHT, AppConstants.DEFAULT_LISTVIEW_WIDTH);
    }

    /**
     * gets the ListView that was created
     * @return
     */
    protected ListView<String> getListView () {
        return myListView;
    }

    /**
     * puts the ListView and a label in a VBox
     * userful for moving it around, less formatting when you make a new one
     * @param s the label text
     * @param fontsize the label fontsize
     * @param color the label text color
     * @return
     */
    protected VBox createListViewWithLabel (String s, int fontsize, Color color) {
        VBox listViewWithLabel = new VBox();
        LabelCreator lc = new LabelCreator(listViewWithLabel);
        lc.createLabel(s, fontsize, color);
        listViewWithLabel.getChildren().addAll(myListView);
        return listViewWithLabel;
    }

}
