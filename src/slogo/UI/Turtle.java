package slogo.UI;

import java.util.HashMap;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Turtle {

	private Node turtleImage;
	private double xpos;
	private double ypos;
	private boolean myDashed;
	private boolean myBold;
	private double orientation;
	private Map<String, Node> imagesMap;
	//private int turtleID;

	private boolean active;

	public Turtle(String s, int x, int y){
		xpos = x;
		ypos = y;
		//myBold = true;
		imagesMap = new HashMap<String, Node>();
		generateSelectorMap(initialImagesMap());
		turtleImage = imagesMap.get(s);
		moveTurtle(xpos, ypos);
		active = true;
		activateTurtle();
	}

	private void activateTurtle () {
		turtleImage.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				active = !active;
				System.out.println(getYPos());
			}
		});

	}
	
//	private void fillUpMap () {
//		Circle c = new Circle();
//		c.setCenterX(xpos);
//		c.setCenterY(ypos);
//		c.setRadius(8);
//		imagesMap.put("Circle", c);
//
//		Rectangle r = new Rectangle();
//		r.setX(xpos);
//		r.setY(ypos);
//		r.setWidth(12);
//		r.setHeight(12);
//		r.setArcHeight(1);
//		r.setArcHeight(1);
//		imagesMap.put("Rectangle", r);
//
//		Polygon p = new Polygon();
//		p.getPoints().addAll(new Double[]{
//				(double) (xpos-6), (double) ypos,
//				(double) xpos, (double) (ypos+12),
//				(double) (xpos+6), (double) ypos, 
//		});
//		imagesMap.put("Triangle", p);
//
//		Image I = new Image(getClass().getResourceAsStream("Turtle"));
//		ImageView IV = new ImageView(I);
//		IV.setLayoutX(xpos);
//		IV.setLayoutY(ypos);
//		imagesMap.put("Turtle", IV);
//
//	}

    private Map<String, String> initialImagesMap(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("Turtle", "Turtle");
        map.put("circle.png", "Circle");
        map.put("triangle.png", "Triangle");
        map.put("rectangle.png", "Rectangle");
        return map;
    }

    private void generateSelectorMap (Map<String, String> fileList) {
        for(String file : fileList.keySet()){
            Image I = new Image(getClass().getResourceAsStream(file));
            ImageView IV = new ImageView(I);
            IV.setLayoutX(xpos);
            IV.setLayoutY(ypos);
            imagesMap.put(fileList.get(file), IV); 
        }
    }

	private void setXPos(double d){
		turtleImage.setLayoutX(d);
		xpos = d;
	}

	protected double getXPos(){
		return xpos;
	}
	
	protected double getImageXPos(){
	    return turtleImage.getLayoutX();
	}
	
	protected double getImageYPos(){
	    return turtleImage.getLayoutY();
	}

	private void setYPos(double e){
		turtleImage.setLayoutY(e);
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
		turtleImage.setRotate(orientation);
		moveTurtle(xpos, ypos);
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

	public boolean isActive () {
		return active;
	}
	

}
