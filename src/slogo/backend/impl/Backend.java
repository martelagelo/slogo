package slogo.backend.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.IModel;
import slogo.backend.evaluation.ElementUnsupportedException;
import slogo.backend.evaluation.IEvaluator;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.parsing.GrammarRule;
import slogo.backend.impl.parsing.Parser;
import slogo.backend.impl.tokenization.TokenRule;
import slogo.backend.impl.tokenization.Tokenizer;
import slogo.backend.parsing.IGrammarRule;
import slogo.backend.parsing.IParser;
import slogo.backend.parsing.InvalidGrammarRuleException;
import slogo.backend.tokenization.IToken;
import slogo.backend.tokenization.ITokenRule;
import slogo.backend.tokenization.ITokenizer;
import slogo.backend.tokenization.InvalidTokenRulesException;

public class Backend implements IModel{
	private ExecutionContext lastContext;

	public Backend(){
		this.lastContext = new ExecutionContext(new HashMap<>(), new HashMap<>());
	}

	private List<ITokenRule> tokenRules(){
		List<ITokenRule> rules = new ArrayList<>();
		rules.add(new TokenRule.Builder(Constants.CONSTANT_LABEL, "-?[0-9]+\\.?[0-9]*").build());
		rules.add(new TokenRule.Builder("command", "[a-zA-z_]+(\\?)?").build());
		return rules;
	}
	private List<IGrammarRule> grammarRules(){
		// three-star programming
		String[][][] rules = {
				{ { "Difference" }, { "constant", "constant" } },
				{ { "Sum" }, { "constant", "constant" } },
				{ { "Minus" }, { "constant" } }
		};
		List<IGrammarRule> ruleList = new ArrayList<>();
		for (String[][] rule: rules) {
			ruleList.add(new GrammarRule(rule[0][0], Arrays.asList(rule[1])));
		}
		return ruleList;
	}
	@Override
	public IExecutionContext execute(String string) {
		ITokenizer tokenizer = null;
		IParser parser = null;
		IEvaluator evaluator = null;
		try {
			tokenizer = new Tokenizer(tokenRules());
		} catch (InvalidTokenRulesException e) {
			//FIXME
			e.printStackTrace();
		}
		try {
			parser = new Parser(grammarRules());
		} catch (InvalidGrammarRuleException e) {
			//FIXME
			e.printStackTrace();
		}
		evaluator = new Evaluator();		

		StringReader reader = new StringReader(string);
		IExecutionContext result = null;
		try {
			result = evaluator.evaluate(
					parser.parse(tokenizer.tokenize(reader)),
					lastContext);
		} catch (MalformedSyntaxException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Map<String, String> getData(Collection<String> elements)
			throws ElementUnsupportedException {
		return null;
	}

}
