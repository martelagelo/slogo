package slogo.UI;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import slogo.CommandExecutor;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.util.ILine;
import slogo.backend.util.ITurtleStatus;
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
	private Stack<Line> pathStack;
	
	private ITurtleStatus TS;
	private String environment;
	
	public MethodRunner(Group root, Canvas canvas, Turtle turtle, Stack stack) {
		this.turtle = turtle;
		this.canvas = canvas;
		this.root = root;
		this.pathStack = stack;
	}
	
	public void changeTurtle() {
		moveTurtle();
		setTurtleDirection();
		setPenState();
		setTurtleVisibility();
	}
	
	public void changeEnvironment() {
		System.out.println(environment);
		
	}

//	private void moveTurtle(int x, int y) {
//		Double xPos = turtle.getXPos();
//		Double yPos = turtle.getYPos();
//		turtle.moveTurtle(x, y);
//		Line line = new Line();
//		line.setStartX(xPos);
//		line.setStartY(yPos);
//		line.setEndX(x);
//		line.setEndY(y);
//
//		root.getChildren().add(line);
//	}
	
	
	private void moveTurtle() {
		for (ILine l: TS.lineSequence()) {
		    Line line = new Line();
		    line.setStartX((double) l.start().getX() + AppConstants.INITIAL_TURTLE_X_POS);
		    System.out.println((double) l.start().getX());
		    line.setStartY((double) l.start().getY() + AppConstants.INITIAL_TURTLE_Y_POS);
		    System.out.println((double) l.start().getY());
		    line.setEndX((double) l.end().getX() + AppConstants.INITIAL_TURTLE_X_POS);
		    System.out.println((double) l.end().getX() );
		    line.setEndY((double) l.end().getY() + AppConstants.INITIAL_TURTLE_Y_POS);
		    System.out.println((double) l.end().getY() );
		    if(turtle.isDashed()){
		        line.getStrokeDashArray().addAll(10d);
		    }
			root.getChildren().add(line);
			pathStack.push(line);
			//if (lines.get(lines.size()-1) == l) turtle.moveTurtle(l.getEndX(), l.getEndY());
		}
		turtle.moveTurtle((double) TS.turtlePosition().getX() + AppConstants.INITIAL_TURTLE_X_POS, (double) TS.turtlePosition().getY() + AppConstants.INITIAL_TURTLE_Y_POS); 
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

