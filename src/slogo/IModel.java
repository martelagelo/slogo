package slogo;

import java.util.Collection;
import java.util.Map;

import slogo.backend.evaluation.ElementUnsupportedException;
import slogo.backend.evaluation.IExecutionContext;

/**
 * Base class for the model
 *
 */
public interface IModel {
	/**
	 * Execute a command
	 * 
	 * @param string a string representing the command
	 */
	public IExecutionContext execute(String string);
	/**
	 * Get requested data from the backend
	 * 
	 * @param elements The elements requested
	 * @return The data, mapping each element to the value associated with it
	 */
	public Map<String,String> getData(Collection<String> elements) throws ElementUnsupportedException;
}
