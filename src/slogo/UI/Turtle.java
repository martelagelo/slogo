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
    private int xpos;
    private int ypos;
    private int orientation;
    private Map<String, Node> imagesMap;
    
    public Turtle(String s, int x, int y){
        xpos = x;
        ypos = y;
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
                                          (double) (xpos+6), (double) ypos,
                                          (double) xpos, (double) (ypos+12) });
        imagesMap.put("Triangle", p);
        Image i = new Image(getClass().getResourceAsStream("Turtle"));
        ImageView iv = new ImageView(i);
        iv.setLayoutX(xpos);
        iv.setLayoutY(ypos);
        imagesMap.put("Turtle", iv);
        
    }

    private void setXPos(int x){
        turtleImage.setLayoutX(x);
        xpos = x;
    }
    
    protected int getXPos(){
        return xpos;
    }
    
    private void setYPos(int y){
        turtleImage.setLayoutY(y);
        ypos = y;
    }
    
    protected int getYPos(){
        return ypos;
    }
    
    protected void setOrientaion(int o){
        turtleImage.setRotate(o);
        orientation = o;
    }
    
    protected int getOrientation(){
        return orientation;
    }
    
    protected void setImage(String name){
        turtleImage = imagesMap.get(name); 
        turtleImage.relocate(xpos, ypos);
    }
    
    protected Node getImage(){
        return turtleImage;
    }
    
    protected void moveTurtle(int x, int y){
        setXPos(x);
        setYPos(y);
    }

    protected Map<String, Node> getShapesMap () {
        return imagesMap;
    }
    
}
