package slogo.backend.evaluation;

import slogo.backend.parsing.ISyntaxNode;

/**
 * Evaluator that evaluates the syntax tree
 *
 */
public interface IEvaluator {

    public IExecutionContext evaluate (ISyntaxNode root, IExecutionContext previous)
            throws MalformedSyntaxException;

}
