package slogo.frontend;

/**
 * Make UI elements for the view
 *
 */
public interface IUIElementFactory {
	/**
	 * Create a new element of a certain type
	 * 
	 * @param element The type of element to create
	 * @return The newly created UI element
	 */
	public IUIElement makeElement(String element);
}
