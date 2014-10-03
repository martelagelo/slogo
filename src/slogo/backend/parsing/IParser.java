package slogo.backend.parsing;

import java.util.List;

import slogo.backend.tokenization.IToken;

public interface IParser {
	public IASTNode parse(List<IToken> tokens);
	public void loadGrammar(List<IGrammarRule> rules);
}
