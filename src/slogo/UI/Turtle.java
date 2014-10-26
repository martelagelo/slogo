package slogo.UI;

import java.util.HashMap;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * 10/10/2014
 * 
 * Version 1
 * 
 * @author Michael Deng
 *
 */
public class Turtle {

	private Node turtleImage;
	private double xpos;
	private double ypos;
	private double orientation;
	private Map<String, Image> imagesMap;
	private Color color;
	private int thickness;

	private boolean active;
	private String lineProperty;
	private String imageName;
	private String myId;
	private Label myLabel;


	/**
	 * The constructor
	 * @param s The value of the image 
	 * @param x The x position of the turtle
	 * @param y The y position of the turtle
	 * @param id The id of the turtle
	 */
	public Turtle(String s, double x, double y, String id){
		xpos = x;
		ypos = y;
		imagesMap = new HashMap<String, Image>();
		generateSelectorMap(initialImagesMap());
		turtleImage = new ImageView(imagesMap.get(s));
	        myLabel = new Label(id);
		moveTurtle(xpos, ypos);
		lineProperty = "None";
		imageName = s;
		thickness = 1;
		myId = id;
		active = true;
		color = Color.BLACK;
		setOrientation(90);
		activateTurtle();
	}

	/**
	 * Activates the event handler on the turtle
	 */
	private void activateTurtle() {
	    turtleImage.setOnMouseClicked(new EventHandler<MouseEvent>(){
	        @Override
	        public void handle(MouseEvent event){
	            active = !active;
	        }
	    });
	}

    /**
    * Creates a map of images for the turtle
    * @return The map of images
    */
    private Map<String, String> initialImagesMap(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("Turtle", "Turtle");
        map.put("circle.png", "Circle");
        map.put("triangle.png", "Triangle");
        map.put("rectangle.png", "Rectangle");
        return map;
    }

    /**
     * Generates the map for the turtle's images
     * @param fileList The list of files for the images
     */
    private void generateSelectorMap (Map<String, String> fileList) {
        for(String file : fileList.keySet()){
            Image I = new Image(getClass().getResourceAsStream(file), AppConstants.MAX_NEW_IMAGE_WIDTH, AppConstants.MAX_NEW_IMAGE_HEIGHT, true, true);
            imagesMap.put(fileList.get(file), I); 
        }
    }

	/**
	 * Sets the x position of the turtle
	 * @param d The x value
	 */
	private void setXPos(double d){
		d -= AppConstants.MAX_NEW_IMAGE_WIDTH/2;
		turtleImage.setLayoutX(d);
		xpos = d;
		myLabel.setLayoutX(d);
	}

	/**
	 * Gets the x position of the turtle
	 * @return The x position
	 */
	protected double getXPos(){
		return xpos;
	}

	/**
	 * Gets the turtle image's x position
	 * @return The image's x position
	 */
	protected double getImageXPos(){
		return xpos - AppConstants.INITIAL_TURTLE_X_POS + 7.5;
	}

	/**
	 * Gets the turtle image's y position
	 * @return The image's y position
	 */
	protected double getImageYPos(){
		return ypos - AppConstants.INITIAL_TURTLE_Y_POS + 7.5;
	}
	
	/**
	 * Sets the turtle image's y position
	 * @return The image's y position
	 */
	private void setYPos(double e){
		e -= AppConstants.MAX_NEW_IMAGE_HEIGHT/2;
		turtleImage.setLayoutY(e);
		ypos = e;
		myLabel.setLayoutY(e - 15);
	}

	/**
	 * Gets the y position of the turtle
	 * @return The y position
	 */
	protected double getYPos(){
		return ypos;
	}

	/**
	 * Sets the orientation of the turtle
	 * @param o The orientation
	 */
	protected void setOrientation(double o){
		turtleImage.setRotate(o);
		orientation = o;
	}

	/**
	 * Increments the orientation of the turtle
	 * @param o The amount of incrementation
	 */
	protected void incrementOrientation(int o) {
		turtleImage.setRotate(orientation + o);
		orientation = orientation + o;
	}

	/**
	 * Gets the orientation of the turtle
	 * @return The orientation
	 */
	protected double getOrientation(){
		return orientation;
	}

	/**
	 * Sets the image of the turtle
	 * @param name The name of the image
	 * @param root The group the image belongs to
	 */
	protected void setImage(String name, Group root){
	        root.getChildren().remove(turtleImage);
	        imageName = name;
		turtleImage = new ImageView(imagesMap.get(name)); 
		turtleImage.setRotate(orientation);
		moveTurtle(xpos+AppConstants.MAX_NEW_IMAGE_WIDTH/2, ypos + AppConstants.MAX_NEW_IMAGE_HEIGHT/2);
		root.getChildren().add(turtleImage);
	}

	/**
	 * Gets the image name 
	 * @return The image name
	 */
	protected String getImageName(){
		return imageName;
	}

	/**
	 * Gets the image of the turtle
	 * @return The image
	 */
	protected Node getImage(){
		return turtleImage;
	}

	/**
	 * Moves the turtle to a specified coordinate
	 * @param d The x value 
	 * @param e The y value
	 */
	protected void moveTurtle(double d, double e){
		setXPos(d);
		setYPos(e);
	}

	/**
	 * Gets the turtle itself
	 * @return The turtle
	 */
	protected Turtle getTurtle() {
		return this;
	}

	/**
	 * Gets the shape map of the turtle
	 * @return The shape map
	 */
	protected Map<String, Image> getShapesMap () {
		return imagesMap;
	}

	/**
	 * The boolean value of the selected turtle
	 * @return True if the turtle is selected
	 */
	public boolean isActive () {
		return active;
	}


    
    protected void setShapesMap(Map<String, Image> map){
        imagesMap = map;
    }
    
    
	/**
	 * Sets the color of the turtle lines
	 * @param value The color
	 */
	public void setColor (Color value) {
		color = value;

	}

	/**
	 * Gets the color of the turtle lines
	 * @return The color of the turtle lines
	 */
	public Color getColor(){
		return color;
	}

	/**
	 * Sets the value of active to true
	 */
	protected void activate(){
		active = true;
	}

	/**
	 * Sets the value of active to false
	 */
	protected void deactivate(){
		active = false;
	}

	/**
	 * Gets the line texture of the turtle's path
	 * @return The line texture 
	 */
	protected String getLineProperty () {
		return lineProperty;
	}

	/**
	 * Gets the line's property of the turtle
	 * @param property The line property
	 */
	public void setLineProperty (String property) {
		lineProperty = property;

	}

	/**
	 * Gets the thickness of the path's lines
	 * @return The thickness of the path
	 */
	protected int getThickness(){
		return thickness;
	}

	/**
	 * Sets the thickness of the path's lines
	 * @param i The thickness of the path
	 */
	protected void setThickness (int i) {
		thickness = i;

	}

	/**
	 * Sets the ID of the turtle
	 * @return The turtle ID
	 */
	protected String getId(){
		return myId;
	}

	/**
	 * Adds a label to the turtle image
	 * @param root The group the label will belong to
	 */
	protected void addLabel(Group root){
		root.getChildren().add(myLabel);
	}

	/**
	 * Removes the label from the turtle's image
	 * @param root The group the label belongs to
	 */
	protected void removeLabel(Group root){
		root.getChildren().remove(myLabel);
	}

}
