package slogo.UI;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TurtleImageSelector {
    
    private VBox mySelectorsVBox;
    
    public TurtleImageSelector(VBox selectorsVBox){
        mySelectorsVBox = selectorsVBox;    
    }
    
    protected void create(Turtle turtle, Group root){
        List<String> possibleImages = new ArrayList<String>();
        possibleImages.addAll(turtle.getShapesMap().keySet());
        SelectorCreator sc = new SelectorCreator(root);
        sc.setUpSelector("Turtle image", AppConstants.SELECTOR_WIDTH, AppConstants.SELECTOR_HEIGHT, AppConstants.SELECTOR_FONT_SIZE, possibleImages);
        final ComboBox<String> turtleSelector = sc.getSelector();
        turtleSelector.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                    if(turtleSelector.getValue() != null){
                            root.getChildren().remove(turtle.getImage());
                            turtle.setImage(turtleSelector.getValue());
                            root.getChildren().add(turtle.getImage());
                    }
            }
        });
        VBox selectorWithLabel = sc.createSelectorWithLabel("Select a Turtle Image", AppConstants.LABEL_FONT_SIZE, Color.BLACK);
        mySelectorsVBox.getChildren().add(selectorWithLabel);
    }
}