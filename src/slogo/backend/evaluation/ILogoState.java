package slogo.backend.evaluation;

import java.util.List;
import java.util.Map;

import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ILine;
import slogo.backend.util.PenState;
import slogo.backend.util.Visibility;

/**
 * The read-only state of a Logo simulation at a particular time
 *
 */
public interface ILogoState {
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
	/**
	 * Get the return value from the last-called Operation
	 * 
	 * @return a String representing the return value
	 */
	public String returnValueString();
	/**
	 * Get the return value from the last-called Operation
	 * 
	 * @return a Number representing the return value
	 * @throws NumberFormatException if the return value does not have a numeric representation
	 */
	public Number returnValueNumber() throws NumberFormatException;
	/**
	 * A general place to put configuration variables, akin to a shell's environment
	 * 
	 * @return a key-value Map that represents the list of configuration variables
	 */
	public Map<String, String> environment();
}
