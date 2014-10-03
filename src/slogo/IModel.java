package slogo;

import slogo.backend.evaluation.IExecutionContext;

/**
 * Base class for the model
 *
 */
public interface IModel {
	/**
	 * Get the status of the simulation after a command
	 * 
	 * @param string a string representing the command
	 * @return the state of the environment after execution
	 */
	public IExecutionContext update(String string);
}
