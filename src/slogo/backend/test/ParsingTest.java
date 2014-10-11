package slogo.backend.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import slogo.backend.impl.parsing.Parser;
import slogo.backend.impl.tokenization.Token;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.parsing.InvalidGrammarRuleException;
import slogo.backend.tokenization.IToken;

public class ParsingTest {

	@Test
	public void testTokenToNode(){
		Parser parser = null;
		try {
			parser = new Parser(new ArrayList<>());
		} catch (InvalidGrammarRuleException e) {
			fail();
		}
		List<IToken> tokens = new ArrayList<>();
		tokens.add(new Token("fd", "command"));
		tokens.add(new Token("sum", "command"));
		tokens.add(new Token("50", "constant"));
		tokens.add(new Token("30", "constant"));
		List<ISyntaxNode> nodes = parser.tokensToNodes(tokens);
		
	}

}
