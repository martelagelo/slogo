package slogo.backend.impl.parsing;

import java.util.ArrayList;
import java.util.List;

import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.IOperationFactory;
import slogo.backend.evaluation.commands.Result;
import slogo.backend.parsing.IGrammarRule;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.tokenization.IToken;

public class GrammarRule implements IGrammarRule {
	private String command;
	private List<String> args;
	public GrammarRule(String command, List<String> args){
		this.command = command;
		this.args = args;
	}
	@Override
	public boolean matches(List<ISyntaxNode> nodes) {
		if (nodes.size() == 1 + args.size()
				&& nodes.get(0).type() == command
				){
			for (int i = 0; i < args.size(); i++){
				if (args.get(i) != nodes.get(i+1).type()){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	@Override
	public ISyntaxNode produce(List<ISyntaxNode> nodes) {
		ISyntaxNode result = new SyntaxNode("result", new Result(), new ArrayList<>());
		ISyntaxNode operation = nodes.get(0);
		List<ISyntaxNode> args = nodes.subList(1, nodes.size());
		operation.setChildren(args);
		List<ISyntaxNode> operationList = new ArrayList<>();
		operationList.add(operation);
		result.setChildren(operationList);
		return result;
	}
}
