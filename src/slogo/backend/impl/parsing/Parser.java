package slogo.backend.impl.parsing;

import java.util.ArrayList;
import java.util.List;

import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.IOperationFactory;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.evaluation.commands.Constant;
import slogo.backend.impl.evaluation.OperationFactory;
import slogo.backend.parsing.IGrammarRule;
import slogo.backend.parsing.IParser;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.parsing.InvalidGrammarRuleException;
import slogo.backend.tokenization.IToken;

public class Parser implements IParser {

	private List<IGrammarRule> rules;
	private IOperationFactory operationFactory;

	public Parser(List<IGrammarRule> rules) throws InvalidGrammarRuleException{
		loadGrammar(rules);
		operationFactory = new OperationFactory();
	}

	@Override
	public ISyntaxNode parse(List<IToken> tokens) throws MalformedSyntaxException {
		List<ISyntaxNode> nodes = tokensToNodes(tokens);
		List<ISyntaxNode> nodeStack = new ArrayList<>();
		
		int nodeIndex = 0;
		int oldSize = -1;
		int newSize = 0;
		while (newSize != oldSize){
			nodeStack.add(nodes.get(nodeIndex));
			for (IGrammarRule rule: rules){
				if (rule.matches(nodeStack)){
					ISyntaxNode result = rule.produce(nodeStack);
					nodeStack = new ArrayList<>();
					nodeStack.add(result);
				}
			}
			if (nodeIndex < nodes.size()){
				nodeIndex++;
			}
			oldSize = newSize;
			newSize = nodeStack.size();
		}
		if (nodeStack.size() == 1){
			return nodeStack.get(0);
		}
		else {
			return null;
		}
	}
	public List<ISyntaxNode> tokensToNodes(List<IToken> tokens) throws MalformedSyntaxException {
		List<ISyntaxNode> nodes = new ArrayList<>();
		for (IToken token: tokens){
			IOperation operation;
			if (token.type() == "constant") {
				operation = new Constant(token.text());
			}
			else {
				try {
					operation =  operationFactory.makeElement(token.text());
				} catch (ClassNotFoundException e) {
					throw new MalformedSyntaxException(e.getMessage());
				}
			}
			ISyntaxNode newNode = new SyntaxNode(token.type(), operation, new ArrayList<>());
			nodes.add(newNode);
		}
		return nodes;
	}

	@Override
	public void loadGrammar(List<IGrammarRule> rules) throws InvalidGrammarRuleException {
		if (rules != null){
			this.rules = rules;
		}
		else {
			throw new InvalidGrammarRuleException();
		}
	}
}
