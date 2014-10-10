package slogo.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class BackgroundColorSelector {
    private VBox myVbox;
    
    public BackgroundColorSelector(VBox vbox){
        myVbox = vbox;
    }
    
    protected void create(Group root, Canvas canvas, GraphicsContext GC){
        ColorSelectorCreator sc = new ColorSelectorCreator(root);
        sc.setUpSelector("Background Color", AppConstants.SELECTOR_WIDTH, AppConstants.SELECTOR_HEIGHT, AppConstants.SELECTOR_FONT_SIZE, (Color) GC.getFill());
        final ColorPicker bgColor = sc.getSelector();
        bgColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                    if(bgColor.getValue() != null){
                            Color c = bgColor.getValue();
                            GC.setFill(c);
                            GC.fillRect(1, 1, canvas.getWidth()-2, canvas.getHeight()-2);
                    }
            }
        });
        VBox selectorWithLabel = sc.createSelectorWithLabel("Select a Background Color", AppConstants.TITLE_LABEL_FONT_SIZE/3, Color.BLACK);
        myVbox.getChildren().add(selectorWithLabel);
    }

}