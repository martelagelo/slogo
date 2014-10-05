package slogo.backend.evaluation;

import java.util.Collection;
import java.util.Map;

import slogo.backend.parsing.IASTNode;

/**
 * 
 *
 */
public interface IEvaluator {

	public void evaluate(IASTNode root) throws MalformedSyntaxException;
	public Map<String,String> getData(Collection<String> elements) throws ElementUnsupportedException;
}
