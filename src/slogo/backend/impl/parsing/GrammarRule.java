package slogo.backend.impl.parsing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import slogo.Constants;
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
			if (standardMatches(searchPattern, toSearch, i)
					|| infiniteMatches(searchPattern, toSearch, i)){
				return i;
			}
		}
		return -1;
	}
	private boolean standardMatches(List<String> searchPattern, List<String> toSearch, int index){
		return searchPattern.equals(toSearch.subList(index, index + searchPattern.size()));
	}
	private boolean infiniteMatches(List<String> searchPattern, List<String> toSearch, int index){
		return infiniteMatchRecurse(searchPattern, toSearch);
	}
	public static boolean infiniteMatchRecurse(List<String> patternRemaining, List<String> searchRemaining){
		if (patternRemaining.size() == 0 && searchRemaining.size() == 0) {
			return true;
		}
		boolean infinite = false;
		if (patternRemaining.size() > 1) {
			if (Constants.INFINITE_MATCHING_LABEL.equals(patternRemaining.get(1))){
				infinite=true;
			}
		}
		if (infinite) {
			if (searchRemaining.size() == 0) {
				if (patternRemaining.size() == 2) {
					return true;
				}
				else {
					return false;
				}
			}
			if (patternRemaining.get(0).equals(searchRemaining.get(0))) {
				return true && infiniteMatchRecurse(
						patternRemaining,
						searchRemaining.subList(1, searchRemaining.size())
						);
			}
			if ( patternRemaining.size() > 2 && patternRemaining.get(2).equals(searchRemaining.get(0)) ) {
				return true && infiniteMatchRecurse(
						patternRemaining.subList(2, patternRemaining.size()),
						searchRemaining
						);
			}
			else {
				return false;
			}
		}
		else {
			if (patternRemaining.get(0).equals(searchRemaining.get(0))){
				return true && infiniteMatchRecurse(
						patternRemaining.subList(1, patternRemaining.size()),
						searchRemaining.subList(1, searchRemaining.size())
						);
			}
			else {
				return false;
			}
		}
	}
}
