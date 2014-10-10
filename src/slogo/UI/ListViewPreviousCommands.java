package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class ListViewPreviousCommands extends ListViewAllSLOGO {
    
    public ListViewPreviousCommands(Group root){
        super(root);
    }
    
    @Override
    protected ListView<String> createAndGetListView(){
        ListViewCreator lvc = createThings(200, 400, 505, 60, "Your Previously Entered Commands", 1, Color.BLACK);
        return lvc.getListView();
    }
}
