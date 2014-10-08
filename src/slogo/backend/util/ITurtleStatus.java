package slogo.backend.util;

import java.util.List;

/**
 * The status of one turtle
 *
 */
public interface ITurtleStatus {
	/**
	 * Get the sequence of lines that have been drawn so far
	 * 
	 * @return a list of lines, in order of drawing
	 */
	public List<ILine> lineSequence();
	/**
	 * Get the position of the turtle
	 * 
	 * @return the coordinates representing the position of the turtle
	 */
	public ICoordinates turtlePosition();
	/**
	 * Get the direction of the turtle
	 * 
	 * @return the direction that the turtle is pointing
	 */
	public IDirection turtleDirection();
	/**
	 * Get the state of the Pen
	 * 
	 * @return the state of the Pen
	 */
	public PenState penState();
	/**
	 * Get the state of the Turtle
	 * 
	 * @return the state of the turtle
	 */
	public Visibility turtleVisibility();
}