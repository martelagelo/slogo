package slogo.backend.evaluation;

import java.util.Collection;
import java.util.Map;

import slogo.backend.parsing.ISyntaxNode;

/**
 * 
 *
 */
public interface IEvaluator {

	public IExecutionContext evaluate(ISyntaxNode root, IExecutionContext previous) throws MalformedSyntaxException;
	//public Map<String,String> getData(Collection<String> elements) throws ElementUnsupportedException;
}
