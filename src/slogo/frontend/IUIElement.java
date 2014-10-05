package slogo.frontend;

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
	public void update(String string);
}
