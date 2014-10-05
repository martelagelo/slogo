package slogo.backend.evaluation;

/**
 * Makes new instances of IOperations
 *
 */
public interface IOperationFactory {
	/**
	 * Create a new element of a certain type
	 * 
	 * @param element The type of element to create
	 * @return The newly created UI element
	 */
	public IOperation makeElement(String element);
}
