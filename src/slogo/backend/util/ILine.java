package slogo.backend.util;


/**
 * A directed line that is either visible or invisible
 *
 */
public interface ILine {
	/**
	 * Get the start of the line
	 * 
	 * @return coordinates representing the start of the line
	 */
	public ICoordinates start();
	/**
	 * Get the end of the line
	 * 
	 * @return coordinates representing the end of the line
	 */
	public ICoordinates end();
	/**
	 * Get whether the line is visible or not
	 * 
	 * @return an enum representing whether the line is visible or not
	 */
	public Visibility visible();
}
