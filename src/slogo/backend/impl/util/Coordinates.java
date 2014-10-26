package slogo.backend.impl.util;

import slogo.backend.util.ICoordinates;

public class Coordinates implements ICoordinates {
    double x;
    double y;

    public Coordinates (double myX, double myY) {
        x = myX;
        y = myY;
    }

    @Override
    public Number getX () {
        return x;
    }

    @Override
    public Number getY () {
        return y;
    }

    @Override
    public Number getDistance (ICoordinates c) {
        double xSquare = Math.pow(c.getX().doubleValue() - this.getX().doubleValue(), 2);
        double ySquare = Math.pow(c.getY().doubleValue() - this.getY().doubleValue(), 2);
        return Math.sqrt(xSquare + ySquare);
    }

}
