// This entire file is part of my masterpiece.
// Nick Widmaier
package slogo.UI;

import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * 
 * @author Nick Widmaier
 *This class is a subclass of ListViewAllSLOGO, illustrating how easy it is to
 * create a new one if need be
 *You just implement create, which in this instance, as would be the case with many,
 *you call the superclass method with predefined constants
 */
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
