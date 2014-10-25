package slogo.backend.impl.util;

import javafx.scene.paint.Color;

public class Qualities {
    
    private Color backgroundColor;
    private Color pathColor;
    private int myShapeIndex;
    private int myThickness;
    
    public Qualities(Color background, Color path, int imageIndex, int thickness){
        backgroundColor = background;
        pathColor = path;
        myShapeIndex = imageIndex;
        myThickness = thickness;
    }
    
    public Color backgroundColor(){
        return backgroundColor;
    }
    
    public Color toColor(){
        return pathColor;
    }
    
    public int index(){
        return myShapeIndex;
    }
    
    public int thickness(){
        return myThickness;
    }
    
    public void setIndex(int i){
        myShapeIndex = i;
    }

}
