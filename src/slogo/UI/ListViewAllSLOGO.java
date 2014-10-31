// This entire file is part of my masterpiece.
// Nick Widmaier
package slogo.UI;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 *
 * @author Nick Widmaier
 * @author Michael Deng
 * @author Michael Ren
 * @author Eric Chen
 *
 *This class serves as a superclass to all the ListViews that are displayed to
 *the user. We utilize listviews to display the previous commands,
 *the variables defined by the user, the commands defined by the user,
 *and some permanent variables (like turtle position). The creation of these 
 *listviews all stems from this class - they only need to inplement create which becomes
 *simple given the createListViewMethods in this class
 *If someone wanted to add another listView to display something else,
 *it would only take a few lines of code due to this class and ListViewCreator
 */
public abstract class ListViewAllSLOGO {
    
    //where it is getting added
    private Group myRoot;
    private VBox vBox;
    protected ListViewCreator myListViewCreator;

    /**
     * constructors for the ListView. Can be added to a root, or a particular vbox
     * @param root the root you add it into
     */
    public ListViewAllSLOGO (Group root) {
        myRoot = root;
    }

    public ListViewAllSLOGO (VBox vBox) {
        this.vBox = vBox;
    }
    /**
     * creates a ListView that can be displayed to the user
     * formats it and labels it for the user
     * used so when a new ListView is added they can just fill in these parameters
     * and call create to make a specific one
     * @param listViewHeight the height of the ListView
     * @param listViewWidth the width of the ListView
     * @param vboxXpos the xpos of the vbox, which contains listview and label
     * @param vboxYpos the ypos of the vbox, which contains listview and label
     * @param labelName the label text
     * @param fontSize of the label
     * @param color of the label text
     * @return
     */
    protected ListViewAllSLOGO createListView (int listViewHeight, int listViewWidth,
                                            int vboxXpos, int vboxYpos, String labelName,
                                            int fontSize, Color color) {
        myListViewCreator = new ListViewCreator(listViewHeight, listViewWidth);
        VBox vbox = myListViewCreator.createListViewWithLabel(labelName, fontSize, color);
        vbox.setLayoutX(vboxXpos);
        vbox.setLayoutY(vboxYpos);
        if (myRoot != null) {
            myRoot.getChildren().add(vbox);
        }
        else {
            vBox.getChildren().add(vbox);
        }
        return this;
    }
    
    /**
     * same as above, except does not set layout for ListView with label
     * used when added to another VBox that's layout is already set
     * @param listViewHeight
     * @param listViewWidth
     * @param labelName
     * @param fontSize
     * @param color
     * @return
     */
    protected ListViewAllSLOGO createListView (int listViewHeight, int listViewWidth,
                                            String labelName,
                                            int fontSize, Color color) {
        myListViewCreator = new ListViewCreator(listViewHeight, listViewWidth);
        VBox vbox = myListViewCreator.createListViewWithLabel(labelName, fontSize, color);
        if (myRoot != null) {
            myRoot.getChildren().add(vbox);
        }
        else {
            vBox.getChildren().add(vbox);
        }
        return this;
    }
    
    /**
     * obtains the ListView object itself
     * @return
     */
    protected ListView<String> getListView () {
        return myListViewCreator.getListView();
    }
    
    /**
     * sets the items displayed to the user
     * @param oList the observable list of items
     */
    protected void setItems (ObservableList<String> oList) {
        myListViewCreator.getListView().setItems(oList);
    }
    
    /**
     * gets the items currently in the listview
     * @return the list of items in the listview
     */
    protected ObservableList<String> getItems () {
        return myListViewCreator.getListView().getItems();
    }
    
    /**
     * implemented by each subclass to create a specific type
     * of list view to be displayed to the user
     * New types of list views need only implement create, and 
     * can use createListView methods in this class to assist in that
     */
    protected abstract void create ();

}
