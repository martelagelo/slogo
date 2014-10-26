package slogo.UI;

import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class ListViewUserVariables extends ListViewAllSLOGO {

    public ListViewUserVariables (Group root) {
        super(root);
    }

    public ListViewUserVariables (VBox vBox) {
        super(vBox);
    }

    @Override
    protected void create () {
        createListView(AppConstants.LIST_BLOCKS_HEIGHT, AppConstants.LIST_BLOCKS_WIDTH,
                       "User Variables", 1, Color.BLACK);
    }

}
