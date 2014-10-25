package slogo.UI;

import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ListViewPreviousCommands extends ListViewAllSLOGO {
    
    public ListViewPreviousCommands(Group root){
        super(root);
    }
    
    public ListViewPreviousCommands(VBox vBox) {
    	super(vBox);
    }
    
    protected void create(){
        createThings(AppConstants.COMMAND_HISTORY_HEIGHT, AppConstants.COMMAND_HISTORY_WIDTH, AppConstants.COMMAND_HISTORY_X_POS, AppConstants.COMMAND_HISTORY_Y_POS, "Your Previously Entered Commands", 1, Color.BLACK);
    }
}
