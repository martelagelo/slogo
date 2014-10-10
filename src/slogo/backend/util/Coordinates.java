package slogo.backend.util;

public class Coordinates implements ICoordinates {
double x;
double y;
public Coordinates(double myX, double myY){
    x = myX;
    y = myY;
}
    @Override
    public Number getX () {
        // TODO Auto-generated method stub
        return x;
    }

    @Override
    public Number getY () {
        // TODO Auto-generated method stub
        return y;
    }

}
