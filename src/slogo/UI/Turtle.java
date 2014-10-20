package slogo.UI;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Turtle {

	private Node turtleImage;
	private double xpos;
	private double ypos;
	private boolean myDashed;
	private boolean myBold;
	private double orientation;
	private Map<String, Node> imagesMap;

	public Turtle(String s, int x, int y){
		xpos = x;
		ypos = y;
		//myBold = true;
		imagesMap = new HashMap<String, Node>();
		fillUpMap();
		turtleImage = imagesMap.get(s);
		turtleImage.relocate(xpos, ypos);
	}

	private void fillUpMap () {
		Circle c = new Circle();
		c.setCenterX(xpos);
		c.setCenterY(ypos);
		c.setRadius(8);
		imagesMap.put("Circle", c);
		
		Rectangle r = new Rectangle();
		r.setX(xpos);
		r.setY(ypos);
		r.setWidth(12);
		r.setHeight(12);
		r.setArcHeight(1);
		r.setArcHeight(1);
		imagesMap.put("Rectangle", r);
		
		Polygon p = new Polygon();
		p.getPoints().addAll(new Double[]{
				(double) (xpos-6), (double) ypos,
				(double) xpos, (double) (ypos+12),
				(double) (xpos+6), (double) ypos, 
				});
		imagesMap.put("Triangle", p);
		
		Image I = new Image(getClass().getResourceAsStream("Turtle"));
		ImageView IV = new ImageView(I);
		IV.setLayoutX(xpos);
		IV.setLayoutY(ypos);
		imagesMap.put("Turtle", IV);

	}

	private void setXPos(double d){
		turtleImage.setLayoutX(d - 275);
		xpos = d;
	}

	protected double getXPos(){
		return xpos;
	}

	private void setYPos(double e){
		turtleImage.setLayoutY(e - 275);
		ypos = e;
	}

	protected double getYPos(){
		return ypos;
	}

	protected void setOrientation(double o){
		turtleImage.setRotate(o);
		orientation = o;
	}

	protected void incrementOrientation(int o) {
		turtleImage.setRotate(orientation + o);
		orientation = orientation + o;
	}

	protected double getOrientation(){
		return orientation;
	}

	protected void setImage(String name){
		turtleImage = imagesMap.get(name); 
		turtleImage.relocate(xpos, ypos);
	}

	protected Node getImage(){
		return turtleImage;
	}

	protected void moveTurtle(double d, double e){
		setXPos(d);
		setYPos(e);
	}
	
	protected Turtle getTurtle() {
		return this;
	}


	protected Map<String, Node> getShapesMap () {
		return imagesMap;
	}

        protected void dashed (boolean b) {
            myDashed = b;
        
        }

        public boolean isDashed () {
            return myDashed;
        }

        protected void bold (boolean b) {
            myBold = b;
            
        }

}
