package slogo.UI;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import slogo.CommandExecutor;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.util.ILine;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;
import slogo.backend.util.Visibility;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * 10/7/2014
 * 
 * Version 1
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
	private List<Line> pathList;

	private Turtle turtle;
	private List<ILine> allILines;
	private String ID;
	private ITurtleStatus TS;
	private String environment;
	private ModuleCreationHelper MCH;

	public MethodRunner(Group root, Canvas canvas, List<Turtle> turtleList, List<Line> list, ModuleCreationHelper MCH) {
		this.turtleList = turtleList;
		this.canvas = canvas;
		this.root = root;
		this.pathList = list;
		this.MCH = MCH;
		this.allILines = new ArrayList<ILine>();
	}

	public void changeTurtle() {
		boolean found = false;
		for(Turtle t : turtleList){
			if (t.getId().equals(ID)){
				turtle = t;
				found = true;
				break;
			}
		}
		if(!found){
			turtle = new Turtle("Triangle", AppConstants.INITIAL_TURTLE_X_POS, AppConstants.INITIAL_TURTLE_Y_POS, ID);
			MCH.getTurtleList().add(turtle);
		}
		if(TS.isActive()){
			setTurtleVisibility();
			moveTurtle();
			setTurtleDirection();
			setPenAttributes();
			setBackgroundColor();
			MCH.setListViewVariables((double) TS.turtlePosition().getX(), -1*(double) TS.turtlePosition().getY(), -1*TS.turtleDirection().toDegrees(), setPenState(), turtle.getThickness());
		}
	}

	public void changeEnvironment() {
		//NEEDS FUNCTIONALITY
		System.out.println("Environment:      " + environment);

	}

	private void setTurtleVisibility(){
		if(TS.turtleVisibility().equals(Visibility.VISIBLE) && !root.getChildren().contains(turtle.getImage())){
			root.getChildren().add(turtle.getImage());
		}
		if(TS.turtleVisibility().equals(Visibility.INVISIBLE) && root.getChildren().contains(turtle.getImage())){
			root.getChildren().remove(turtle.getImage());
		}

	}
	/**
	 * Moves turtle to the appropriate position based on turtle status	
	 */
	private void moveTurtle() {
		if(TS.penState().equals(PenState.DOWN)){
			for (Line l : pathList) {
				root.getChildren().remove(l);
			}
			List<Line> copyLines = new ArrayList<Line>();
			copyLines.addAll(pathList);
			pathList.clear();
			for (ILine l : TS.lineSequence()){
				allILines.add(l);
			}
			for (ILine l : allILines) {
				boolean alreadyAdded = false;
				for(Line line : copyLines){
					alreadyAdded = redrawIfInScene(l, line);
					if(alreadyAdded) break;
				}
				if(!alreadyAdded){
					drawNewLine(l);
				}
			}
			drawLinesUsingToroidal();
		}
		turtle.moveTurtle((double) TS.turtlePosition().getX() + AppConstants.INITIAL_TURTLE_X_POS, (double) TS.turtlePosition().getY() + AppConstants.INITIAL_TURTLE_Y_POS); 
	}


	/**
	 * If a line with the same properties as the ILine exists in the scene, redraw it
	 * Done so that we keep lines drawn from previous commands
	 * @param l ILine in the line sequence provided from the backend
	 * @param line in the scene based on previous pathlist
	 * @return true if already in the scene
	 */
	private boolean redrawIfInScene(ILine l, Line line){

		//checks if starting and ending coordinates are the same
		if(Math.abs((double) l.start().getX() - (line.getStartX() - AppConstants.INITIAL_TURTLE_X_POS)) < AppConstants.ROUNDING_ERROR &&
				Math.abs((double) l.end().getX() - (line.getEndX() - AppConstants.INITIAL_TURTLE_X_POS)) < AppConstants.ROUNDING_ERROR &&
				Math.abs((double) l.start().getY() - (line.getStartY() - AppConstants.INITIAL_TURTLE_Y_POS)) < AppConstants.ROUNDING_ERROR &&
				Math.abs((double) l.end().getY() - (line.getEndY() - AppConstants.INITIAL_TURTLE_Y_POS)) < AppConstants.ROUNDING_ERROR){   
			//if(!root.getChildren().contains(line)){
			//	root.getChildren().add(line);
			//}
			pathList.add(line);
			return true;
		}
		else{
			return false;
		}

	}

	/**
	 * if it's not in the scene, we draw is based on the conditions
	 * supplied from the backend 
	 * @param l the current Iline in the TurtleStatus line sequence
	 */
	private void drawNewLine(ILine l){
		Line line = new Line();
		line.setStroke(TS.turtleQualities().toColor());
		line.setStartX((double) l.start().getX() + AppConstants.INITIAL_TURTLE_X_POS);
		line.setStartY((double) l.start().getY() + AppConstants.INITIAL_TURTLE_Y_POS);
		line.setEndX((double) l.end().getX() + AppConstants.INITIAL_TURTLE_X_POS);
		line.setEndY((double) l.end().getY() + AppConstants.INITIAL_TURTLE_Y_POS);
		//PATH PROPERTY SHOULD BE MOVED OR MADE A COMMAND
		if(turtle.getLineProperty().equals("Dashed")){
			line.setStrokeWidth(turtle.getThickness());
			line.getStrokeDashArray().addAll(AppConstants.DASH_SIZE);
		}
		if(turtle.getLineProperty().equals("Bold")){
			line.getStrokeDashArray().clear();
			line.setStrokeWidth(Math.min(AppConstants.MAX_PATH_WIDTH, (turtle.getThickness() + AppConstants.BOLD_SIZE)));
		}
		if(turtle.getLineProperty().equals("None")){
			line.getStrokeDashArray().clear();
			line.setStrokeWidth(turtle.getThickness());
		}
		pathList.add(line);
	}

	private void drawLinesUsingToroidal() {
		ToroidalHelper TH = new ToroidalHelper(pathList);
		List<Line> newToroidalPathList = TH.makeLinesToroidal();
		for (Line l: newToroidalPathList) {
			if(!root.getChildren().contains(l)){
				root.getChildren().add(l);
			}
		}
		pathList = newToroidalPathList;
	}

	private void setTurtleDirection() {
		turtle.setOrientation(TS.turtleDirection().toDegrees() + AppConstants.ORIENTATION_OFFSET);
	}


	/**
	 * Used to update the Variables ListView displayed to the user
	 * @return true if pen is down, false if pen is up
	 */
	private boolean setPenState() {
		return TS.penState().equals(PenState.DOWN);
	}

	public void setTurtleStatus(String id, ITurtleStatus TS) {
		this.TS = TS;
		this.ID = id;
	}

	/**
	 * Sets all attributes of the pen
	 */
	private void setPenAttributes() {
		setPenColor();
		setPenThickness();
		setTurtleImage();
	}

	/**
	 * Sets the Pen Color, based off value from TurtleStatus
	 * Updates the selector as well
	 */
	private void setPenColor(){
		MCH.getPathColorSelector().setValue(TS.turtleQualities().toColor());
		turtle.setColor(MCH.getPathColorSelector().getValue());   
	}

	/**
	 * Sets the pen thickness, based off value from TurtleStatus
	 * This is a value between 1 and 15
	 */
	private void setPenThickness(){
		turtle.setThickness(Math.max(1, TS.turtleQualities().thickness()));
		turtle.setThickness(Math.min(AppConstants.MAX_PATH_WIDTH, turtle.getThickness()));

	}

	/**
	 * Sets the image based off the index returned from the TurtleStatus
	 * index is val user put in for command "SetShape val"
	 * 
	 */
	private void setTurtleImage(){
		//Checks that index is valid based on size of list of options in image selector
		if(TS.turtleQualities().index() > 0 && TS.turtleQualities().index() <= MCH.getTurtleSelector().getItems().size()){
			MCH.getTurtleSelector().setValue((MCH.getTurtleSelector().getItems().get((TS.turtleQualities().index() - 1))));
			System.out.println(MCH.getTurtleSelector().getValue());
			turtle.setImage(MCH.getTurtleSelector().getValue(), root);
		}
		//otherwise displays message to user, and sets index to 0
		else if (TS.turtleQualities().index() < 0 || TS.turtleQualities().index() > MCH.getTurtleSelector().getItems().size()){
			new MessageBox("Not a Valid Number!");
			TS.turtleQualities().setIndex(0);
		}
	}


	/**
	 * Changes the background color to be whatever value is given from the TurtleStatus
	 */
	private void setBackgroundColor(){
		MCH.getBackgroundColorSelector().setValue(TS.turtleQualities().backgroundColor());
		MCH.getCanvas().getGraphicsContext2D().setFill(MCH.getBackgroundColorSelector().getValue());
		MCH.getCanvas().getGraphicsContext2D().fillRect(1, 1, AppConstants.CANVAS_WIDTH - 2, AppConstants.CANVAS_HEIGHT - 2);
	}

	public void setEnvironment(String var) {
		this.environment = var;
	}

}

