package slogo.backend.tokenization;

/**
 * A representation of a token emitted by the tokenizer
 *
 */
public interface IToken {
	/**
	 * Get the text associated with the token
	 * 
	 * @return the text of the token
	 */
	public String text();
	/**
	 * Get the type of token
	 * 
	 * @return the type
	 */
	public String tokenType();
	/**
	 * Get the line number of the token
	 * 
	 * @return the line number
	 */
	public Number lineNumber();
	/**
	 * Get the starting column of the token
	 * 
	 * @return the column number
	 */
	public Number columnNumber();
}
