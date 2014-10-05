package slogo.backend.tokenization;

import java.io.Reader;
import java.util.List;

/**
 * Something which converts a stream of characters to a list of tokens
 * 
 */
public interface ITokenizer {
	/**
	 * Convert a stream into a list of characters
	 * 
	 * @param input an input stream of characters
	 * @return a list of tokens, in order
	 */
	public List<IToken> tokenize(Reader input);
	/**
	 * Load rules for recognizing tokens
	 * 
	 * @param rules a list of rules
	 */
	public void loadTokenRules(List<ITokenRule> rules);
}
