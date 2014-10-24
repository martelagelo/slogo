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
		if (searchRemaining.size() == 0){
			if (patternRemaining.size() == 0 || patternRemaining.size() == 2){
				return true;
			}
			return false;
		}
		List<String> newPattern;
		List<String> newSearch;
		if (isInfinite(patternRemaining)){
			// if we're at the wildcard and both leading elements match, we
			// keep the pattern (so that we can continue wildcard matching),
			// but we iterate to the next search element
			if (patternRemaining.get(0).equals(searchRemaining.get(0))) {
				newPattern = patternRemaining;
				newSearch = searchRemaining.subList(1, searchRemaining.size());
				return true && infiniteMatchRecurse(newPattern,	newSearch);
			}
			// if we encounter the element right after the wildcard element, then
			// we have finished matching everything for the wildcard, so we should
			// set the pattern to the element after the wildcard and handle it
			// normally using non-infinite logic; searchRemaining stays the same
			// so that the next time around, the two leading elements of each list
			// can be matched and iterated over in unison
			if (patternRemaining.size() > 2 && patternRemaining.get(2).equals(searchRemaining.get(0))) {
				newPattern = patternRemaining.subList(2, patternRemaining.size());
				newSearch = searchRemaining;
				return true && infiniteMatchRecurse(newPattern, newSearch);
			}
		}
		// if we're not matching a wildcard, we iterate one at a time over both
		// lists checking to see that both leading elements are equal
		else if (patternRemaining.get(0).equals(searchRemaining.get(0))) {
			newPattern = patternRemaining.subList(1, patternRemaining.size());
			newSearch = searchRemaining.subList(1, searchRemaining.size());
			return true && infiniteMatchRecurse(newPattern,	newSearch);
		}
		return false;
	}
	private static boolean isInfinite(List<String> patternRemaining){
		if (patternRemaining.size() > 1 
				&& Constants.INFINITE_MATCHING_LABEL.equals(patternRemaining.get(1))){
				return true;
		}
		return false;
	}
}
