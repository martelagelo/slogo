package slogo.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo.View;


/**
 * 10/15/2014
 *
 * Version 1
 *
 * @author Michael Deng
 *
 */
public class BackgroundColorSelector {
    private VBox myVbox;
    private ColorPicker bgColor;

    public BackgroundColorSelector (VBox vbox) {
        myVbox = vbox;
    }

    protected void create (Group root, GraphicsContext GC, View view) {
        ColorSelectorCreator sc = new ColorSelectorCreator(root);
        sc.setUpSelector("Background Color", AppConstants.SELECTOR_WIDTH,
                         AppConstants.SELECTOR_HEIGHT, AppConstants.SELECTOR_FONT_SIZE,
                         (Color) GC.getFill());
        bgColor = sc.getSelector();
        bgColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                Color c = bgColor.getValue();
                if (c != null) {
                    view.sendCommandToBackend("SetBackground " + 255 * c.getRed() + " " +
                            255 * c.getGreen() + " " + 255 *
                            c.getBlue());
                }
            }
        });
        VBox selectorWithLabel =
                sc.createSelectorWithLabel("Select a Background Color",
                                           AppConstants.TITLE_LABEL_FONT_SIZE / 3, Color.BLACK);
        myVbox.getChildren().add(selectorWithLabel);
    }

    public void setValue (Color backgroundColor) {
        bgColor.setValue(backgroundColor);

    }

    public Color getValue () {
        return bgColor.getValue();
    }

}
