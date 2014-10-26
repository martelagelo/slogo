package slogo.backend.parsing;

import java.util.List;

import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.tokenization.IToken;

public interface IParser {
    public ISyntaxNode parse (List<IToken> tokens) throws MalformedSyntaxException;

    public void loadGrammar (List<IGrammarRule> rules) throws InvalidGrammarRuleException;
}
