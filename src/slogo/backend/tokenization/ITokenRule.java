package slogo.backend.tokenization;

/**
 * A rule for recognizing a token
 *
 */
public interface ITokenRule {
	/**
	 * Check if a string of text matches and tokenize it if so
	 * 
	 * @param text the text to match for
	 * @return the matching token if matched, an empty token otherwise
	 */
	public IToken match(String text);
	/**
	 * Get the name of the rule
	 * 
	 * @return the name of the rule
	 */
	public String getName();
}
