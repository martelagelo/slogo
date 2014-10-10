package slogo.UI;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import slogo.CommandExecutor;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class MethodRunner {

	private Map<String, Runnable> commandMap;
	private Turtle turtle;
	private Canvas canvas;
	private Group root;
	private Stack<Line> pathStack;
	
	private CommandExecutor CE;
	private int x;
	private int y;

	public MethodRunner(Group root, Canvas canvas, Turtle turtle, Map commandMap, Stack stack) {
		this.turtle = turtle;
		this.canvas = canvas;
		this.root = root;
		this.commandMap = commandMap;
		this.pathStack = stack;
	}

	public void init() {
		commandMap.put("fd", () -> { 
			moveTurtle(x, y);
		});
		commandMap.put("move", () -> {
			moveTurtle(CE.getList());
		});
	}

	private void moveTurtle(int x, int y) {
		Double xPos = turtle.getXPos();
		Double yPos = turtle.getYPos();
		//turtle.moveTurtle(coord.getX(), coord.getY());
		turtle.moveTurtle(x, y);
		Line line = new Line();
		line.setStartX(xPos);
		line.setStartY(yPos);
		line.setEndX(x);
		line.setEndY(y);
		//line.setEndX(coord.getX());
		//.line.setEndY(coord.getY());
		root.getChildren().add(line);
	}
	
	private void moveTurtle(List<Line> lines) {
		for (Line l: lines) {
			root.getChildren().add(l);
			pathStack.push(l);
			if (lines.get(lines.size()-1) == l) turtle.moveTurtle(l.getEndX(), l.getEndY());
		}
	}

	private void setTurtleDirection(int orientation) {
		turtle.setOrientaion(orientation);
	}
	
	public void setCommandExecutor(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setCommandExecutor(CommandExecutor commandExecutor) {
		this.CE = commandExecutor;
	}

}

