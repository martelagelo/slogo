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

	private Map<String, Runnable> commandMap;
	private Turtle turtle;
	private Canvas canvas;
	private Group root;
	private Stack<Line> pathStack;
	
	private ITurtleStatus TS;
	private int x;
	private int y;

	public MethodRunner(Group root, Canvas canvas, Turtle turtle, Map commandMap, Stack stack) {
		this.turtle = turtle;
		this.canvas = canvas;
		this.root = root;
		this.commandMap = commandMap;
		this.pathStack = stack;
	}

//	public void init() {
//		commandMap.put("fd", () -> { 
//			moveTurtle(x, y);
//		});
//		commandMap.put("move", () -> {
//			moveTurtle(CE.getList());
//		});
//	}
	
	public void changeFrontEnd() {
		//moveTurtle();
		setVariables();
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
	
	private void setVariables() {
		
	}
	
	private void moveTurtle() {
		for (ILine l: TS.lineSequence()) {
		    Line line = (Line) l;
		    if(turtle.isDashed()){
		        line.getStrokeDashArray().addAll(10d);
		    }
			root.getChildren().add((Line) l);
			pathStack.push((Line) l);
			//if (lines.get(lines.size()-1) == l) turtle.moveTurtle(l.getEndX(), l.getEndY());
		}
		turtle.moveTurtle((double) TS.turtlePosition().getX(), (double) TS.turtlePosition().getY()); 
	}

	private void setTurtleDirection(int orientation) {
		turtle.setOrientation(orientation);
	}
	
	public void setTurtleStatus(ITurtleStatus TS) {
		this.TS = TS;
	}

}

