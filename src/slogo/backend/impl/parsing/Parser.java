package slogo.backend.impl.parsing;

import java.util.ArrayList;
import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.IOperationFactory;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.OperationFactory;
import slogo.backend.impl.evaluation.commands.Constant;
import slogo.backend.impl.evaluation.commands.Result;
import slogo.backend.impl.evaluation.commands.Variable;
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
	public void loadGrammar(List<IGrammarRule> rules) throws InvalidGrammarRuleException {
		if (rules != null){
			this.rules = rules;
		}
		else {
			throw new InvalidGrammarRuleException("The rules supplied are null");
		}
	}

	@Override
	public ISyntaxNode parse(List<IToken> tokens) throws MalformedSyntaxException {
		List<ISyntaxNode> nodes = tokensToNodes(tokens);
		List<ISyntaxNode> nodeStack = new ArrayList<>();

		for (ISyntaxNode node: nodes) {
			nodeStack.add(node);
			int index = hasProduction(nodeStack);
			while (index != -1){
				nodeStack = reduce(nodeStack, index);
				index = hasProduction(nodeStack);
			}
		}
		if (nodeStack.size() == 1){
			return nodeStack.get(0);
		}
		else {
			throw new MalformedSyntaxException(generateParseErrorMessage(nodes, nodeStack));
		}
	}

	public List<ISyntaxNode> tokensToNodes(List<IToken> tokens) throws MalformedSyntaxException {
		List<ISyntaxNode> nodes = new ArrayList<>();
		for (IToken token: tokens){
			IOperation operation;
			String type;
			if (token.type() == Constants.CONSTANT_LABEL) {
				operation = new Constant(token.text());
				type = token.type();
			}
			else if (token.type() == Constants.VARIABLE_LABEL) {
				operation = new Variable(token.text());
				type = token.type();
			}
			else if (token.type() == Constants.OPENING_LIST_LABEL
					|| token.type() == Constants.CLOSING_LIST_LABEL) {
				operation = new Result();
				type = token.type();
			}
			else {
				try {
					operation =  operationFactory.makeElement(token.text());
				} catch (ClassNotFoundException e) {
					throw new MalformedSyntaxException(e.getMessage());
				}
				type = token.text();
			}
			ISyntaxNode newNode = new SyntaxNode(type, operation, new ArrayList<>());
			nodes.add(newNode);
		}
		return nodes;
	}

	private int hasProduction(List<ISyntaxNode> nodeStack) {
		for (IGrammarRule rule: rules) {
			int index = rule.matches(nodeStack);
			if (index != -1){
				return index;
			}
		}
		return -1;
	}

	private List<ISyntaxNode> reduce(List<ISyntaxNode> nodes, int index) {
		List<ISyntaxNode> newNodes = new ArrayList<>();
		for (int i = 0; i < index; i++){
			newNodes.add(nodes.get(i));
		}
		List<ISyntaxNode> reducedNodes = new ArrayList<>(nodes.subList(index, nodes.size()));
		ISyntaxNode newNode = createNestedNode(reducedNodes);
		newNodes.add(newNode);
		return newNodes;
	}

	private ISyntaxNode createNestedNode(List<ISyntaxNode> nodes) {
		List<ISyntaxNode> args = new ArrayList<>(nodes.subList(1, nodes.size()));
		ISyntaxNode operation = nodes.get(0);
		operation.setChildren(args);

		ISyntaxNode result = new SyntaxNode(Constants.CONSTANT_LABEL, new Result(), new ArrayList<>());
		List<ISyntaxNode> resultChildren = new ArrayList<>();
		resultChildren.add(operation);
		result.setChildren(resultChildren);
		return result;
	}
	private String generateParseErrorMessage(List<ISyntaxNode> all, List<ISyntaxNode> remaining) {
		return "Reducing " + all + " failed, creating " + remaining + " instead";
	}
}
