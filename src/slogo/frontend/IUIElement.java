package slogo.frontend;

import java.util.Collection;
import java.util.Map;

import javafx.scene.Node;

/**
 * A single element appearing on the UI
 *
 */
public interface IUIElement {
	/**
	 * Return the visual representation of the element to the UI
	 * 
	 * @return The Node representing this element
	 */
	public Node draw();
	/**
	 * Update the state of this element
	 * 
	 * @param string The changes to be made
	 */
	public void update(Map<String, String> data);
	/**
	 * Get the backend data dependencies for this element
	 * 
	 * @return the list of dependencies this element needs
	 */
	public Collection<String> dataDependencies();
}
