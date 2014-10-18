package slogo.backend.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import slogo.backend.evaluation.IEvaluator;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.parsing.GrammarRule;
import slogo.backend.impl.parsing.Parser;
import slogo.backend.impl.parsing.SyntaxNode;
import slogo.backend.impl.tokenization.Token;
import slogo.backend.parsing.IGrammarRule;
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
		String[] expected = {"Minus","Sum","constant","constant"};
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
	
	@Test
	public void testGrammarRule(){
		String[] args = { "constant", "constant" };
		IGrammarRule rule = new GrammarRule("Sum", Arrays.asList(args));
		ISyntaxNode[] tokens = {
				new SyntaxNode("Minus", null, null),
				new SyntaxNode("Sum", null, null),
				new SyntaxNode("constant", null, null),
				new SyntaxNode("constant", null, null)
				};
		assertEquals(1, rule.matches(Arrays.asList(tokens)));
	}
	@Test
	public void testParse() {
		String[] unaryArgs1 = { "constant" };
		String[] binaryArgs1 = { "constant", "constant" };
		IGrammarRule unaryRule = new GrammarRule("Minus", Arrays.asList(unaryArgs1));
		IGrammarRule binaryRule = new GrammarRule("Sum", Arrays.asList(binaryArgs1));
		List<IGrammarRule> rules = new ArrayList<>();
		rules.add(unaryRule);
		rules.add(binaryRule);
		Parser parser = null;
		try {
			parser = new Parser(rules);
		} catch (InvalidGrammarRuleException e) {
			fail("these are valid rules");
		}
		IToken[] tokens = {
				new Token("Minus", "command"),
				new Token("Sum", "command"),
				new Token("50", "constant"),
				new Token("80", "constant")
		};

		ISyntaxNode root = null;
		try {
			root = parser.parse(Arrays.asList(tokens));
		} catch (MalformedSyntaxException e) {
			fail("Should be parsed correctly");
		}
		assertNotNull(root);
		assertEquals("constant", root.type());
	}
}
