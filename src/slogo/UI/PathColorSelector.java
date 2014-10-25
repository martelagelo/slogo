package slogo.UI;

import java.util.List;
import slogo.View;
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
    
    protected void create(Group root, Turtle t, View view){
        ColorSelectorCreator sc = new ColorSelectorCreator(root);
        sc.setUpSelector("Path Color", AppConstants.SELECTOR_WIDTH, AppConstants.SELECTOR_HEIGHT, AppConstants.SELECTOR_FONT_SIZE, Color.BLACK);
        pathColor = sc.getSelector();
        pathColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                Color c = pathColor.getValue();
                    if(c != null){
                                view.sendCommandToBackend("SetPenColor " + (int) 255*c.getRed() + " " + (int) 255*c.getGreen() + " " + (int) 255*c.getBlue());
                    }
            }
        });
        VBox selectorWithLabel = sc.createSelectorWithLabel("Select a Path Color", AppConstants.TITLE_LABEL_FONT_SIZE/3, Color.BLACK);
        myVbox.getChildren().addAll(selectorWithLabel);
    }

    public void setValue (Color color) {
        pathColor.setValue(color);
        
    }

    public Color getValue () {
        return pathColor.getValue();
    }
    
}