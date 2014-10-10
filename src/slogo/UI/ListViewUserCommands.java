package slogo.UI;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class ListViewUserCommands extends ListViewAllSLOGO {
    
    public ListViewUserCommands(Group root){
        super(root);
    }

    @Override
    protected void create() {
        createThings(200, 200, AppConstants.ALL_SELECTORS_XPOS - 450, AppConstants.TURTLE_IMAGE_YPOS + 80,
                                           "User Defined Commands", 1, Color.BLACK);
    }

}