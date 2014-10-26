package slogo.UI;

import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class ListViewUserCommands extends ListViewAllSLOGO {

    public ListViewUserCommands (Group root) {
        super(root);
    }

    public ListViewUserCommands (VBox vBox) {
        super(vBox);
    }

    @Override
    protected void create () {
        createListView(AppConstants.LIST_BLOCKS_HEIGHT, AppConstants.LIST_BLOCKS_WIDTH,
                       "User Defined Commands", 1, Color.BLACK);
    }

}
