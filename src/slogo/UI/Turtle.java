package slogo.UI;

import java.util.HashMap;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Turtle {

	private Node turtleImage;
	private double xpos;
	private double ypos;
	private boolean myDashed;
	private boolean myBold;
	private double orientation;
	private Map<String, Node> imagesMap;
	private Color color;
	private int id;

	private boolean active;
        private String lineProperty;
        private String imageName;
    

	public Turtle(String s, double x, double y, int id){
		xpos = x;
		ypos = y;
		imagesMap = new HashMap<String, Node>();
		generateSelectorMap(initialImagesMap());
		turtleImage = imagesMap.get(s);
		moveTurtle(xpos, ypos);
		this.id = id;
		lineProperty = "None";
		imageName = s;
		active = true;
		color = Color.BLACK;
		setOrientation(90);
	}

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
            Image I = new Image(getClass().getResourceAsStream(file), AppConstants.MAX_NEW_IMAGE_WIDTH, AppConstants.MAX_NEW_IMAGE_HEIGHT, true, true);
            ImageView IV = new ImageView(I);
            IV.setLayoutX(xpos);
            IV.setLayoutY(ypos);
            imagesMap.put(fileList.get(file), IV); 
        }
    }

	private void setXPos(double d){
	        d -= AppConstants.MAX_NEW_IMAGE_WIDTH/2;
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
	        e -= AppConstants.MAX_NEW_IMAGE_HEIGHT/2;
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
	        imageName = name;
		turtleImage = imagesMap.get(name); 
		turtleImage.setRotate(orientation);
		moveTurtle(xpos+AppConstants.MAX_NEW_IMAGE_WIDTH/2, ypos + AppConstants.MAX_NEW_IMAGE_HEIGHT/2);
	}
	
	protected String getImageName(){
	    return imageName;
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

	public boolean isActive () {
		return active;
	}


    public void setColor (Color value) {
        color = value;
        
    }
    
    public Color getColor(){
        return color;
    }
    
    public int getId(){
        return id;
    }
    
    protected void activate(){
        active = true;
    }
    
    protected void deactivate(){
        active = false;
    }

    protected String getLineProperty () {
        return lineProperty;
    }

    public void setLineProperty (String property) {
        lineProperty = property;
        
    }

}
