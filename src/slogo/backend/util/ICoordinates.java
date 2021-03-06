package slogo.backend.util;

/**
 * A set of 2D coordinate points
 *
 */
public interface ICoordinates {
    /**
     * Get the x coordinate
     * 
     * @return the x coordinate
     */
    public Number getX ();

    /**
     * Get the y coordinate
     * 
     * @return the y coordinate
     */
    public Number getY ();

    public Number getDistance (ICoordinates c);
}
