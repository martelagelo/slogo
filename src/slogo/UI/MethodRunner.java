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

	private Turtle turtle;
	private Canvas canvas;
	private Group root;
	private List<Line> pathList;
	
	private ITurtleStatus TS;
	private String environment;
	private ModuleCreationHelper MCH;
	
	public MethodRunner(Group root, Canvas canvas, Turtle turtle, List<Line> list, ModuleCreationHelper MCH) {
		this.turtle = turtle;
		this.canvas = canvas;
		this.root = root;
		this.pathList = list;
		this.MCH = MCH;
	}
	
	public void changeTurtle() {
		moveTurtle();
		setTurtleDirection();
		setPenState();
		setTurtleVisibility();
		MCH.setListViewVariables((double) TS.turtlePosition().getX(),(double) TS.turtlePosition().getY(), TS.turtleDirection().toDegrees(), true);
	}
	
	public void changeEnvironment() {
		//NEEDS FUNCTIONALITY
		System.out.println(environment);
		
	}
		
	private void moveTurtle() {
	    for (Line l : pathList) {
	        root.getChildren().remove(l);
	    }
	    List<Line> copyLines = new ArrayList<Line>();
	    copyLines.addAll(pathList);
	    pathList.clear();
	    for (ILine l: TS.lineSequence()) {
	        boolean alreadyAdded = false;
	        for(Line line : copyLines){
	            alreadyAdded = redrawIfInScene(l, line);
	            if(alreadyAdded) break;
	        }
	        if(!alreadyAdded){
	            drawNewLine(l);
	        }
	    }
	    turtle.moveTurtle((double) TS.turtlePosition().getX() + AppConstants.INITIAL_TURTLE_X_POS, (double) TS.turtlePosition().getY() + AppConstants.INITIAL_TURTLE_Y_POS); 
	}


	private boolean redrawIfInScene(ILine l, Line line){
	    if((double) l.start().getX() == (line.getStartX() - AppConstants.INITIAL_TURTLE_X_POS) &&
	            (double) l.end().getX() == (line.getEndX() - AppConstants.INITIAL_TURTLE_X_POS) &&
	            (double) l.start().getY() == (line.getStartY() - AppConstants.INITIAL_TURTLE_Y_POS) &&
	            (double) l.end().getY() == (line.getEndY() - AppConstants.INITIAL_TURTLE_Y_POS)){
	        root.getChildren().add(line);
	        pathList.add(line);
	        return true;
	    }
	    else{
	        return false;
	    }
	}

	private void drawNewLine(ILine l){
	    Line line = new Line();
	    line.setStroke(turtle.getColor());
	    line.setStartX((double) l.start().getX() + AppConstants.INITIAL_TURTLE_X_POS);
	    line.setStartY((double) l.start().getY() + AppConstants.INITIAL_TURTLE_Y_POS);
	    line.setEndX((double) l.end().getX() + AppConstants.INITIAL_TURTLE_X_POS);
	    line.setEndY((double) l.end().getY() + AppConstants.INITIAL_TURTLE_Y_POS);
	    if(turtle.isDashed()){
	        line.getStrokeDashArray().addAll(10d);
	    }
	    root.getChildren().add(line);
	    pathList.add(line);
	}

	private void setTurtleDirection() {
		turtle.setOrientation(TS.turtleDirection().toDegrees() + 90);
	}
	
	private void setPenState() {
		
	}
	
	private void setTurtleVisibility() {
		if (TS.turtleVisibility().VISIBLE != null) {
			//TODO
		}
	}
	
	public void setTurtleStatus(ITurtleStatus TS) {
		this.TS = TS;
	}

	public void setEnvironment(String var) {
		this.environment = var;
	}

}

