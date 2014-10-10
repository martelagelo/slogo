package slogo.UI;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class ListViewUserVariables extends ListViewAllSLOGO {
    
    public ListViewUserVariables(Group root){
        super(root);  
    }
    
    @Override
    protected void create(){
        createThings(200, 200, AppConstants.ALL_SELECTORS_XPOS - 225, AppConstants.TURTLE_IMAGE_YPOS + 80,
                                           "User Variables", 1, Color.BLACK);
    }
    
}
