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
        private VBox myVBox;
    

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
	
	private void activateTurtle() {
	    turtleImage.setOnMouseClicked(new EventHandler<MouseEvent>(){
	        @Override
	        public void handle(MouseEvent event){
	            active = !active;
	        }
	    });
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
            //ImageView IV = new ImageView(I);
            //IV.setLayoutX(xpos);
            //IV.setLayoutY(ypos);
            imagesMap.put(fileList.get(file), I); 
        }
    }

	private void setXPos(double d){
	        d -= AppConstants.MAX_NEW_IMAGE_WIDTH/2;
		turtleImage.setLayoutX(d);
	        xpos = d;
	        myLabel.setLayoutX(d);
	}

	protected double getXPos(){
		return xpos;
	}
	
	protected double getImageXPos(){
	    return xpos - AppConstants.INITIAL_TURTLE_X_POS + 7.5;
	}
	
	protected double getImageYPos(){
	    return ypos - AppConstants.INITIAL_TURTLE_Y_POS + 7.5;
	}

	private void setYPos(double e){
	        e -= AppConstants.MAX_NEW_IMAGE_HEIGHT/2;
		turtleImage.setLayoutY(e);
	        ypos = e;
	        myLabel.setLayoutY(e - 15);
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

	protected void setImage(String name, Group root){
	        root.getChildren().remove(turtleImage);
	        imageName = name;
		turtleImage = new ImageView(imagesMap.get(name)); 
		turtleImage.setRotate(orientation);
		moveTurtle(xpos+AppConstants.MAX_NEW_IMAGE_WIDTH/2, ypos + AppConstants.MAX_NEW_IMAGE_HEIGHT/2);
		root.getChildren().add(turtleImage);
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


	protected Map<String, Image> getShapesMap () {
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
    
    protected int getThickness(){
        return thickness;
    }

    protected void setThickness (int i) {
        thickness = i;
        
    }
    
    protected String getId(){
        return myId;
    }
    
    protected void addLabel(Group root){
        root.getChildren().add(myLabel);
    }
    
    protected void removeLabel(Group root){
        root.getChildren().remove(myLabel);
    }
    
    protected void setShapesMap(Map<String, Image> map){
        imagesMap = map;
    }
    
    

}
