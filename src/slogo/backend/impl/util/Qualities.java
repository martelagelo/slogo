package slogo.backend.impl.util;

import javafx.scene.paint.Color;

public class Qualities {
    
    private Color pathColor;
    private int myShapeIndex;
    private int myThickness;
    
    public Qualities(Color c, int imageIndex, int thickness){
        pathColor = c;
        myShapeIndex = imageIndex;
        myThickness = thickness;
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
