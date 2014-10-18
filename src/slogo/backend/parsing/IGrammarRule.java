package slogo.backend.parsing;

import java.util.List;

import slogo.backend.impl.parsing.SyntaxNode;
import slogo.backend.tokenization.IToken;

/**
 * 
 *
 */
public interface IGrammarRule {
	//public ISyntaxNode produce(List<IToken> tokens);

	/**
	 * Check if the rule matches any right subset of a list of tokens
	 * 
	 * @param tokens The tokens to match on
	 * @return The index indicating the beginning of the match; -1 if no match
	 */
	public int matches(List<ISyntaxNode> tokens);

	/**
	 * Nest "constant" arguments under the operation node,
	 * and nest the operation node under a single, new "constant" node
	 * 
	 * @param nodes The list of nodes
	 * @param index The beginning of the subset of the list used for nesting
	 * @return The list of nodes with the new "constant" node
	 */
	//public List<ISyntaxNode> reduce(List<ISyntaxNode> nodes, int index);
}
