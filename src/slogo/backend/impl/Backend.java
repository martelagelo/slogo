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
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.IGrammarRule;
import slogo.backend.parsing.IParser;
import slogo.backend.parsing.InvalidGrammarRuleException;
import slogo.backend.tokenization.IToken;
import slogo.backend.tokenization.ITokenRule;
import slogo.backend.tokenization.ITokenizer;
import slogo.backend.tokenization.InvalidTokenRulesException;
import slogo.backend.util.ITurtleStatus;

public class Backend implements IModel{

	private IExecutionContext lastContext;

	public Backend(){
		Map<String, ITurtleStatus> turtles = new HashMap<>();
		turtles.put("1", new TurtleStatus.Builder().build());
		this.lastContext = new ExecutionContext(turtles, new HashMap<>());
	}

	private List<ITokenRule> tokenRules(){
		List<ITokenRule> rules = new ArrayList<>();
		rules.add(new TokenRule.Builder(Constants.CONSTANT_LABEL, "-?[0-9]+\\.?[0-9]*").build());
		rules.add(new TokenRule.Builder(Constants.COMMAND_LABEL, "[a-zA-z_]+(\\?)?").build());
		rules.add(new TokenRule.Builder(Constants.VARIABLE_LABEL, ":[a-zA-Z]+").build());
		rules.add(new TokenRule.Builder(Constants.LIST_LABEL, "\\[.*\\]").build());
		return rules;
	}
	private List<IGrammarRule> grammarRules(){
		String[][] rules = {
		        {"And", "2"},
		        {"Equal","2"},
		        {"Greater","2"},    
		        {"Less","2"},
		        {"Not","1"},
		        {"NotEqual","2"},
		        {"Or","2"},
		        {"Atan","1"},
		        {"Cos","1"},
		        { "Difference", "2" },
		        {"Log","1"},
		        { "Minus", "1" },
				{"Pow","2"},
				{"Product","2"},
				{"Quotient","2"},
				{"RandomNumber","1"},
				{"Remainder","2"},
				{"Sin","1"},
				{ "Sum", "2" },
				{"Tan","1"},
				{"Back","1"},
				{"ClearScreen","0"},
				{ "Forward", "1" },
				{"Heading","0"},
				{"HideTurtle","0"},
				{"Home","0"},
				{"IsPenDown","0"},
				{"IsShowing","0"},
				{"Left","1"},
				{"PenDown","0"},
				{"PenUp","0"},
				{"Right","1"},
				{"SetHeading","1"},
				{"SetXY","2"},
				{"ShowTurtle","0"},
				{"Towards","2"},
				{"Xcor","0"},
				{"Ycor","0"},
				
		};
		List<IGrammarRule> ruleList = new ArrayList<>();
		for (String[] rule: rules) {
			List<String> repeatedConstants = new ArrayList<>();
			for (int i = 0; i < Integer.parseInt(rule[1]); i++){
				repeatedConstants.add("constant");
			}
			ruleList.add(new GrammarRule(rule[0], repeatedConstants));
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
		lastContext = result;
		return result;
	}

	@Override
	public Map<String, String> getData(Collection<String> elements)
			throws ElementUnsupportedException {
		return null;
	}

}
