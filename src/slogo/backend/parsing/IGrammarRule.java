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

	boolean matches(List<ISyntaxNode> tokens);

	public ISyntaxNode produce(List<ISyntaxNode> nodes);
}
