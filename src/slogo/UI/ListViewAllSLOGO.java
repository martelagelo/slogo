package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class ListViewAllSLOGO {
    
    private Group myRoot;

    public ListViewAllSLOGO(Group root){
        myRoot = root;
    }
    
    public ListViewCreator createThings(int listViewHeight, int listViewWidth,
                             int vboxXpos, int vboxYpos, String labelName,
                             int fontSize, Color color){
        ListViewCreator lvc = new ListViewCreator(listViewHeight, listViewWidth);
        VBox vbox = lvc.createListViewWithLabel(labelName, fontSize, color);
        vbox.setLayoutX(vboxXpos);
        vbox.setLayoutY(vboxYpos);
        myRoot.getChildren().add(vbox);
        return lvc;
    }
    
    protected abstract ListView<String> createAndGetListView();

}
