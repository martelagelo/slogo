package slogo.backend;

/**
 * The direction an object faces, with up being 0 degrees
 *
 */
public interface IDirection {
	/**
	 * Get the direction in degrees
	 * 
	 * @return the direction in degrees
	 */
	public double toDegrees();
	/**
	 * Get the direction in radians
	 * 
	 * @return the direction in radians
	 */
	public double toRadians();
}
