package slogo.backend.impl.util;

import slogo.backend.util.IDirection;

public class Direction implements IDirection {
private double direction;
public Direction(double myDirection){
    direction = myDirection;
}
    @Override
    public double toDegrees () {
        // TODO Auto-generated method stub
        return direction;
    }

    @Override
    public double toRadians () {
        // TODO Auto-generated method stub
        return Math.toRadians(direction);
    }

}
