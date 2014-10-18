package slogo.backend.impl.parsing;

import java.util.ArrayList;
import java.util.List;

import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.IOperationFactory;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.evaluation.commands.Constant;
import slogo.backend.evaluation.commands.Result;
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
	public void loadGrammar(List<IGrammarRule> rules) throws InvalidGrammarRuleException {
		if (rules != null){
			this.rules = rules;
		}
		else {
			throw new InvalidGrammarRuleException();
		}
	}

	//FIXME
	@Override
	public ISyntaxNode parse(List<IToken> tokens) throws MalformedSyntaxException {
		List<ISyntaxNode> nodes = tokensToNodes(tokens);
		List<ISyntaxNode> nodeStack = new ArrayList<>();
		
		//int nodeIndex = 0;
		//int oldSize = -1;
		//int newSize = 0;
		
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
			throw new MalformedSyntaxException("Unable to parse tokens");
		}
		/*
		while (newSize != oldSize){
			nodeStack.add(nodes.get(nodeIndex));
			for (IGrammarRule rule: rules){
				for (int i = 0; i < nodeStack.size(); i++){
					List<ISyntaxNode> subList = nodeStack.subList(i, nodeStack.size());
					ArrayList<ISyntaxNode> foofoo = new ArrayList<>(subList);
					if (rule.matches(subList)){
						nodeStack = reduce(nodeStack, rule, i);
					}
				}
			}
			if (nodeIndex < nodes.size() - 1){
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
		*/
	}


	public List<ISyntaxNode> tokensToNodes(List<IToken> tokens) throws MalformedSyntaxException {
		List<ISyntaxNode> nodes = new ArrayList<>();
		for (IToken token: tokens){
			IOperation operation;
			String type;
			if (token.type() == "constant") {
				operation = new Constant(token.text());
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

		ISyntaxNode result = new SyntaxNode("constant", new Result(), new ArrayList<>());
		List<ISyntaxNode> resultChildren = new ArrayList<>();
		resultChildren.add(operation);
		result.setChildren(resultChildren);
		return result;
	}
}
