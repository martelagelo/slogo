package slogo.backend.evaluation;

import java.util.List;

/**
 * Any operation or literal for the Logo language
 *
 */
public interface IOperation {
	/**
	 * Perform the function on a state/context
	 * 
	 * @param args any arguments needed for the operation
	 * @return the new state of the execution
	 */
	public IExecutionContext execute(List<IExecutionContext> args) throws MalformedSyntaxException;
	/**
	 * 
	 * @return
	 */
	public String type();
}
