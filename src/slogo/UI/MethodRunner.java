package slogo.UI;

import java.lang.reflect.Method;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.Line;

public class MethodRunner {
	
	private Turtle turtle;
	private Canvas canvas;
	private Group root;
	
	public MethodRunner(Group root, Canvas canvas, Turtle turtle) {
		this.turtle = turtle;
		this.canvas = canvas;
		this.root = root;
	}
	
	public void moveTurtleForward(int x, int y) {
		System.out.println("YOOOOOOOOO!!!");
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
}

