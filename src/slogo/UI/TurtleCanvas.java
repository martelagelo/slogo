package slogo.UI;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 10/10/2014
 * 
 * Version 1
 * 
 * @author Michael Deng
 *
 */
public class TurtleCanvas {
    
    private Group myRoot;
    private GraphicsContext myGraphicsContext;
    private Canvas myCanvas;
    
    /**
     * The constructor
     * @param root The group the turtle canvas belongs to
     */
    public TurtleCanvas (Group root){
        myRoot = root;
        myCanvas = new Canvas(AppConstants.CANVAS_WIDTH, AppConstants.CANVAS_HEIGHT);
        myCanvas.setLayoutX(AppConstants.CANVAS_OFFSET_X_POS);
        myCanvas.setLayoutY(AppConstants.CANVAS_OFFSET_Y_POS);
        myGraphicsContext = myCanvas.getGraphicsContext2D();
        myGraphicsContext.setFill(Color.WHITE);
        myGraphicsContext.setStroke(Color.BLACK);
        myGraphicsContext.setLineWidth(1);
        myGraphicsContext.fillRect(1, 1, myCanvas.getWidth()-2, myCanvas.getHeight()-2);
        myGraphicsContext.strokeRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
        myRoot.getChildren().add(myCanvas);
    }
    
    /**
     * Gets the graphics context
     * @return The graphics context
     */
    protected GraphicsContext getGraphicsContext(){
        return myGraphicsContext;
    }
    
    /**
     * Gets the canvas
     * @return The canvas
     */
    protected Canvas getCanvas(){
        return myCanvas;
    }

}
