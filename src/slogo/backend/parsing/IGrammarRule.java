package slogo.backend.parsing;

import java.util.List;

import slogo.backend.tokenization.IToken;

/**
 * 
 *
 */
public interface IGrammarRule {
	public IASTNode produce(List<IToken> tokens);
}
