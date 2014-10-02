package slogo.backend;

/**
 * The state of the pen; whether it is down, i.e. writing, or up, i.e. not writing
 *
 */
public enum PenState {
	/**
	 * The pen is up and will not write any visible lines
	 */
	UP,
	/**
	 * The pen is down and will write visible lines
	 */
	DOWN
}
