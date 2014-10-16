package slogo.UI;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class ListViewUserCommands extends ListViewAllSLOGO {
    
    public ListViewUserCommands(Group root){
        super(root);
    }

    @Override
    protected void create() {
        createThings(AppConstants.LIST_BLOCKS_HEIGHT, AppConstants.LIST_BLOCKS_WIDTH, AppConstants.LIST_BLOCKS_X_POS, AppConstants.LIST_BLOCKS_Y_POS_USER_COMMANDS,
                                           "User Defined Commands", 1, Color.BLACK);
    }

}