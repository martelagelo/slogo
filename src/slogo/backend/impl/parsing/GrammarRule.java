package slogo.backend.impl.parsing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.IOperationFactory;
import slogo.backend.impl.evaluation.commands.Result;
import slogo.backend.parsing.IGrammarRule;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.tokenization.IToken;

public class GrammarRule implements IGrammarRule {
	private String command;
	private List<String> args;
	public GrammarRule(String command, String[] args){
		this(command, Arrays.asList(args));
	}
	public GrammarRule(String command, List<String> args){
		this.command = command;
		this.args = args;
	}
	@Override
	public int matches(List<ISyntaxNode> nodes) {
		List<String> searchPattern = new ArrayList<>();
		searchPattern.add(command);
		searchPattern.addAll(args);
		
		List<String> toSearch = new ArrayList<>();
		for (ISyntaxNode node: nodes){
			toSearch.add(node.type());
		}
		
		for (int i = 0; i <= toSearch.size() - searchPattern.size(); i++){
			if (searchPattern.equals(toSearch.subList(i, i + searchPattern.size()))){
				return i;
			}
		}
		return -1;
	}
}
