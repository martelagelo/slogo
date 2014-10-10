package slogo.UI;

import java.lang.reflect.Method;
import java.util.Map;

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
	
	private int x;
	private int y;

	public MethodRunner(Group root, Canvas canvas, Turtle turtle, Map commandMap) {
		this.turtle = turtle;
		this.canvas = canvas;
		this.root = root;
		this.commandMap = commandMap;
	}

	public void init() {
		commandMap.put("fd", () -> { 
			System.out.println("NOTOOTOOO");   
			moveTurtle(x, y);
		});
	}

	void moveTurtle(int x, int y) {
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

	private void setTurtleDirection(int orientation) {
		turtle.setOrientaion(orientation);
	}
	
	public void setInput(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

