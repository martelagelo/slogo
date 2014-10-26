package slogo.UI;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.Line;
import slogo.backend.util.ILine;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;
import slogo.backend.util.Visibility;


/**
 *
 *
 * This class takes the returns from the backend and updates the user display, including the turtles
 * and the variables that the user sees
 *
 *
 * @author Eric Chen
 * @author Michael Deng
 * @author Michael Ren
 * @auther Nick Widmaier
 *
 */
public class MethodRunner {

    private List<Turtle> turtleList;
    private Canvas canvas;
    private Group root;
    // list of lines in the root
    private List<Line> pathList;

    private Turtle turtle;
    // list of all lines from backend
    private List<ILine> allILines;
    // the turtle id
    private String ID;
    // the turtlestatus from the backend
    private ITurtleStatus TS;
    private String environment;
    private ModuleCreationHelper MCH;

    /**
     * construct a new methodrunner to update our gui
     *
     * @param root the root
     * @param canvas the canvas where the turtle lies
     * @param turtleList list of all our turtles created
     * @param list list of all lines drawn on the seen
     * @param MCH our class holding all our ui elements
     */

    public MethodRunner (Group root,
                         Canvas canvas,
                         List<Turtle> turtleList,
                         List<Line> list,
                         ModuleCreationHelper MCH) {
        this.turtleList = turtleList;
        this.canvas = canvas;
        this.root = root;
        pathList = list;
        this.MCH = MCH;
        allILines = new ArrayList<ILine>();
    }

    /**
     * updates the appropriate turtle
     * takes the turtlestatus and checks if any turtles have the same id,
     * otherwise makes a new turtle
     * then sets all the turtle attributes from the backend returns
     *
     */
    public void changeTurtle () {
        boolean found = false;
        for (Turtle t : turtleList) {
            if (t.getId().equals(ID)) {
                turtle = t;
                found = true;
                break;
            }
        }
        // make new turtle if not found
        if (!found) {
            turtle =
                    new Turtle("Triangle", AppConstants.INITIAL_TURTLE_X_POS,
                               AppConstants.INITIAL_TURTLE_Y_POS, ID);
            MCH.getTurtleList().add(turtle);
            turtle.setShapesMap(MCH.getTurtleList().get(0).getShapesMap());
        }
        // change attributes
        if (TS.isActive()) {
            setTurtleVisibility();
            moveTurtle();
            setTurtleDirection();
            setPenState();
            setPenAttributes();
            setBackgroundColor();
            MCH.setListViewVariables((double) TS.turtlePosition().getX(), -1 *
                                     (double) TS
                                     .turtlePosition()
                                     .getY(), (-1 * TS
                                             .turtleDirection().toDegrees()) % 360, setPenState(), turtle.getThickness());
        }
    }

    /**
     * show or hide the turtle based on its visibility status
     */
    private void setTurtleVisibility () {
        if (TS.turtleVisibility().equals(Visibility.VISIBLE) &&
                !root.getChildren().contains(turtle.getImage())) {
            root.getChildren().add(turtle.getImage());
        }
        if (TS.turtleVisibility().equals(Visibility.INVISIBLE) &&
                root.getChildren().contains(turtle.getImage())) {
            root.getChildren().remove(turtle.getImage());
        }

    }

    /**
     * Moves turtle to the appropriate position based on turtle status
     * draws the lines behind it if the pen is down
     */
    private void moveTurtle () {
        if (TS.penState().equals(PenState.DOWN)) {
            for (Line l : pathList) {
                root.getChildren().remove(l);
            }
            List<Line> copyLines = new ArrayList<Line>();
            copyLines.addAll(pathList);
            pathList.clear();
            for (ILine l : TS.lineSequence()) {
                allILines.add(l);
            }
            for (ILine l : allILines) {
                boolean alreadyAdded = false;
                for (Line line : copyLines) {
                    alreadyAdded = redrawIfInScene(l, line);
                    if (alreadyAdded) {
                        break;
                    }
                }
                if (!alreadyAdded) {
                    drawNewLine(l);
                }
            }
            drawLinesUsingToroidal();
        }
        // turtle.moveTurtle((double) TS.turtlePosition().getX() +
        // AppConstants.INITIAL_TURTLE_X_POS, (double) TS.turtlePosition().getY() +
        // AppConstants.INITIAL_TURTLE_Y_POS);
        turtle.moveTurtle(pathList.get(pathList.size() - 1).getEndX(),
                          pathList.get(pathList.size() - 1).getEndY());
    }

    /**
     * If a line with the same properties as the ILine exists in the scene, redraw it
     * Done so that we keep lines drawn from previous commands
     *
     * @param l ILine in the line sequence provided from the backend
     * @param line in the scene based on previous pathlist
     * @return true if already in the scene
     */
    private boolean redrawIfInScene (ILine l, Line line) {

        // checks if starting and ending coordinates are the same
        if (Math.abs((double) l.start().getX() -
                     (line.getStartX() - AppConstants.INITIAL_TURTLE_X_POS)) < AppConstants.ROUNDING_ERROR &&
                     Math.abs((double) l.end().getX() - (line.getEndX() - AppConstants.INITIAL_TURTLE_X_POS)) < AppConstants.ROUNDING_ERROR &&
                     Math.abs((double) l.start().getY() -
                              (line.getStartY() - AppConstants.INITIAL_TURTLE_Y_POS)) < AppConstants.ROUNDING_ERROR &&
                              Math.abs((double) l.end().getY() - (line.getEndY() - AppConstants.INITIAL_TURTLE_Y_POS)) < AppConstants.ROUNDING_ERROR) {
            // if(!root.getChildren().contains(line)){
            // root.getChildren().add(line);
            // }
            pathList.add(line);
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * if it's not in the scene, we draw is based on the conditions
     * supplied from the backend
     *
     * @param l the current Iline in the TurtleStatus line sequence
     */
    private void drawNewLine (ILine l) {
        Line line = new Line();
        // set color
        line.setStroke(TS.turtleQualities().toColor());
        // set start and end
        line.setStartX((double) l.start().getX() + AppConstants.INITIAL_TURTLE_X_POS);
        line.setStartY((double) l.start().getY() + AppConstants.INITIAL_TURTLE_Y_POS);
        line.setEndX((double) l.end().getX() + AppConstants.INITIAL_TURTLE_X_POS);
        line.setEndY((double) l.end().getY() + AppConstants.INITIAL_TURTLE_Y_POS);
        // set path texture
        if (turtle.getLineProperty().equals("Dashed")) {
            line.setStrokeWidth(turtle.getThickness());
            line.getStrokeDashArray().addAll(AppConstants.DASH_SIZE);
        }
        if (turtle.getLineProperty().equals("Bold")) {
            line.getStrokeDashArray().clear();
            line.setStrokeWidth(Math.min(AppConstants.MAX_PATH_WIDTH,
                                         (turtle.getThickness() + AppConstants.BOLD_SIZE)));
        }
        if (turtle.getLineProperty().equals("None")) {
            line.getStrokeDashArray().clear();
            line.setStrokeWidth(turtle.getThickness());
        }
        pathList.add(line);
    }

    private void drawLinesUsingToroidal () {
        ToroidalHelper TH = new ToroidalHelper(pathList);
        List<Line> newToroidalPathList = TH.makeLinesToroidal();
        for (Line l : newToroidalPathList) {
            if (!root.getChildren().contains(l)) {
                root.getChildren().add(l);
            }
        }
        pathList = newToroidalPathList;
    }

    /**
     * set the turtle orientation
     */
    private void setTurtleDirection () {
        turtle.setOrientation(TS.turtleDirection().toDegrees() + AppConstants.ORIENTATION_OFFSET);
    }

    /**
     * Used to update the Variables ListView displayed to the user
     *
     * @return true if pen is down, false if pen is up
     */
    private boolean setPenState () {
        return TS.penState().equals(PenState.DOWN);
    }

    /**
     * set the turtle status
     *
     * @param id - the turtle id that should be updated
     * @param TS - the turtle status corresponding to this id
     */
    public void setTurtleStatus (String id, ITurtleStatus TS) {
        this.TS = TS;
        ID = id;
    }

    /**
     * Sets all attributes of the pen
     */
    private void setPenAttributes () {
        setPenColor();
        setPenThickness();
        setTurtleImage();
    }

    /**
     * Sets the Pen Color, based off value from TurtleStatus
     * Updates the selector as well
     */
    private void setPenColor () {
        MCH.getPathColorSelector().setValue(TS.turtleQualities().toColor());
        turtle.setColor(MCH.getPathColorSelector().getValue());
    }

    /**
     * Sets the pen thickness, based off value from TurtleStatus
     * This is a value between 1 and 15
     */
    private void setPenThickness () {
        turtle.setThickness(Math.max(1, TS.turtleQualities().thickness()));
        turtle.setThickness(Math.min(AppConstants.MAX_PATH_WIDTH, turtle.getThickness()));

    }

    /**
     * Sets the image based off the index returned from the TurtleStatus
     * index is val user put in for command "SetShape val"
     *
     */
    private void setTurtleImage () {
        // Checks that index is valid based on size of list of options in image selector
        if (TS.turtleQualities().index() > 0 &&
                TS.turtleQualities().index() <= MCH.getTurtleSelector().getItems().size()) {
            System.out.println("This is where I set the image");
            MCH.getTurtleSelector().setValue((MCH.getTurtleSelector().getItems().get((TS
                    .turtleQualities().index() - 1))));
            System.out.println(MCH.getTurtleSelector().getValue());
            turtle.setImage(MCH.getTurtleSelector().getValue(), root);
            System.out.println(ID + turtle.getImageName());
        }
        // otherwise displays message to user, and sets index to 0
        else if (TS.turtleQualities().index() < 0 ||
                TS.turtleQualities().index() > MCH.getTurtleSelector().getItems().size()) {
            new MessageBox("Not a Valid Number!");
            TS.turtleQualities().setIndex(0);
        }
    }

    /**
     * Changes the background color to be whatever value is given from the TurtleStatus
     */
    private void setBackgroundColor () {
        MCH.getBackgroundColorSelector().setValue(TS.turtleQualities().backgroundColor());
        MCH.getCanvas().getGraphicsContext2D().setFill(MCH.getBackgroundColorSelector().getValue());
        MCH.getCanvas().getGraphicsContext2D()
        .fillRect(1, 1, AppConstants.CANVAS_WIDTH - 2, AppConstants.CANVAS_HEIGHT - 2);
    }

    public void setEnvironment (String var) {
        environment = var;
    }

    public void changeEnvironment () {
        MCH.setUserVariable(environment);

    }

}
