package slogo.UI;

import java.util.ArrayList;
import java.util.List;
import slogo.View;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TurtleImageSelector {
    
    private VBox mySelectorsVBox;
    private List<String> myPossibleImages;
    private ComboBox<String> mySelector;
    
    public TurtleImageSelector(VBox selectorsVBox){
        mySelectorsVBox = selectorsVBox;    
        myPossibleImages = new ArrayList<String>();
    }
    
    protected void create(Group root, Turtle turtle, View view){
        myPossibleImages.addAll(turtle.getShapesMap().keySet());
        SelectorCreator sc = new SelectorCreator(root);
        sc.setUpSelector("Turtle Image", AppConstants.SELECTOR_WIDTH, AppConstants.SELECTOR_HEIGHT, AppConstants.SELECTOR_FONT_SIZE, myPossibleImages);
        mySelector = sc.getSelector();
        mySelector.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                        if(mySelector.getValue() != null){
                            view.sendCommandToBackend("SetShape " + (mySelector.getItems().indexOf(mySelector.getValue()) + 1));
                        }
            }
        });
        VBox selectorWithLabel = sc.createSelectorWithLabel("Select a Turtle Image", AppConstants.LABEL_FONT_SIZE, Color.BLACK);
        mySelectorsVBox.getChildren().add(selectorWithLabel);
    }
    
    protected void updateMap(String s, Image i, List<Turtle> turtleList){
        mySelector.getItems().add(s);
        for(Turtle t : turtleList){
            t.getShapesMap().put(s, i);
        }
    }

    public ObservableList<String> getItems () {
        return mySelector.getItems();
    }
    
    public void setValue(String s){
        mySelector.setValue(s);
    }
    
    public String getValue(){
        return mySelector.getValue();
    }
}