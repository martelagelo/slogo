package slogo.UI;

import javafx.scene.Group;

import javafx.scene.paint.Color;

public class ListViewPreviousCommands extends ListViewAllSLOGO {
    
    public ListViewPreviousCommands(Group root){
        super(root);
    }
    
    protected void create(){
        createThings(200, 400, 505, 60, "Your Previously Entered Commands", 1, Color.BLACK);
    }
}
