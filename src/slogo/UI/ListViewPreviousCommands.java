package slogo.UI;

import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class ListViewPreviousCommands extends ListViewAllSLOGO {

    public ListViewPreviousCommands (Group root) {
        super(root);
    }

    public ListViewPreviousCommands (VBox vBox) {
        super(vBox);
    }

    @Override
    protected void create () {
        createListView(AppConstants.COMMAND_HISTORY_HEIGHT, AppConstants.COMMAND_HISTORY_WIDTH,
                       "Your Previously Entered Commands", 1, Color.BLACK);
    }
}
