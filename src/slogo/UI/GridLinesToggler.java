package slogo.UI;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class GridLinesToggler {

    private CheckBox myCheckBox;
    private List<Line> myGridLines;

    public GridLinesToggler (VBox vbox) {
        CheckBoxCreator cb = new CheckBoxCreator(vbox);
        myCheckBox = cb.createCheckBox("Toggle Grid");
        myGridLines = new ArrayList<Line>();
    }

    protected List<Line> getLinesList () {
        return myGridLines;
    }

    /**
     * Creates an event handler that makes grid lines on the canvas when fired
     *
     * @param cb The checkbox that fires the event
     */
    protected void activate (TurtleCanvas myCanvas, Group root) {
        myCheckBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                if (myCheckBox.isSelected()) {
                    for (double x = myCanvas.getCanvas().getLayoutX(); x < myCanvas.getCanvas()
                            .getWidth() + myCanvas.getCanvas().getLayoutX(); x +=
                            AppConstants.GRIDLINES_SPACING) {
                        Line line =
                                new Line(x, myCanvas.getCanvas().getLayoutY(), x,
                                         myCanvas.getCanvas().getLayoutY() +
                                         myCanvas.getCanvas().getHeight());
                        addLine(line);
                    }
                    for (double y = myCanvas.getCanvas().getLayoutY(); y < myCanvas.getCanvas()
                            .getHeight() + myCanvas.getCanvas().getLayoutY(); y +=
                            AppConstants.GRIDLINES_SPACING) {
                        Line line2 =
                                new Line(myCanvas.getCanvas().getLayoutX(), y, myCanvas.getCanvas()
                                         .getLayoutX() + myCanvas.getCanvas().getWidth(), y);
                        addLine(line2);
                    }
                    root.getChildren().addAll(myGridLines);
                }
                else {
                    removeLines(root);
                }
            }
        });
    }

    private void addLine (Line l) {
        l.setFill(Color.LIGHTGREY);
        myGridLines.add(l);
    }

    private void removeLines (Group root) {
        for (Line l : myGridLines) {
            root.getChildren().remove(l);
        }
        myGridLines.clear();
    }
}
