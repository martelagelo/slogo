package slogo.backend.evaluation;

import java.util.Collection;
import java.util.Map;

import slogo.backend.parsing.ISyntaxNode;

/**
 * 
 *
 */
public interface IEvaluator {

	public void evaluate(ISyntaxNode root) throws MalformedSyntaxException;
	public Map<String,String> getData(Collection<String> elements) throws ElementUnsupportedException;
}
