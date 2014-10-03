package slogo.backend.evaluation;

import slogo.backend.parsing.IASTNode;

/**
 * 
 *
 */
public interface IEvaluator {

	public IExecutionContext evaluate(IASTNode root);
}
