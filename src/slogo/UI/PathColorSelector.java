package slogo.UI;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PathColorSelector {

    private VBox myVbox;
    private ColorPicker pathColor;

    public PathColorSelector(VBox vbox){
        myVbox = vbox;
    }
    
    protected void create(Group root, List<Turtle> turtleList){
        ColorSelectorCreator sc = new ColorSelectorCreator(root);
        sc.setUpSelector("Path Color", AppConstants.SELECTOR_WIDTH, AppConstants.SELECTOR_HEIGHT, AppConstants.SELECTOR_FONT_SIZE, Color.BLACK);
        pathColor = sc.getSelector();
        pathColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                    if(pathColor.getValue() != null){
                        for(Turtle t: turtleList){
                            if(t.isActive()){
                                t.setColor(pathColor.getValue());
                            }
                        }
                    }
            }
        });
        VBox selectorWithLabel = sc.createSelectorWithLabel("Select a Path Color", AppConstants.TITLE_LABEL_FONT_SIZE/3, Color.BLACK);
        myVbox.getChildren().addAll(selectorWithLabel);
    }
    
    protected void resetPathColor(Turtle t){
        pathColor.setValue(t.getColor());
    }
}