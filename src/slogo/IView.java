package slogo;

public interface IView {
	/**
	 * Start up the UI
	 */
	public void init();
	/**
	 * Notify the view that an error has occurred
	 * 
	 * @param message The message
	 */
	public void error(String message);
}
