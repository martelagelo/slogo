package slogo.backend.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import slogo.backend.evaluation.MalformedSyntaxException;
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
		tokens.add(new Token("Minus", "command"));
		tokens.add(new Token("Sum", "command"));
		tokens.add(new Token("50", "constant"));
		tokens.add(new Token("30", "constant"));
		List<ISyntaxNode> nodes = null;
		try {
			nodes = parser.tokensToNodes(tokens);
		} catch (MalformedSyntaxException e) {
			fail("Minus Sum 50 30 is a valid command");
		}
		String[] expected = {"command","command","constant","constant"};
		assertEquals(4, nodes.size());
		for (int i = 0; i < nodes.size(); i++){
			assertEquals(expected[i], nodes.get(i).type());
		}
		List<IToken> badTokens = new ArrayList<>();
		badTokens.add(new Token("hai", "command"));
		try {
			List<ISyntaxNode> badNodes = parser.tokensToNodes(badTokens);
			fail("Exception should be thrown for nonexistent command \"hai\"");
		} catch (MalformedSyntaxException e) {
			
		}
	}

}
