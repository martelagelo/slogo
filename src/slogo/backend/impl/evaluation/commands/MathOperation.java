// This entire file is part of my masterpiece.
// Michael Ren
package slogo.backend.impl.evaluation.commands;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.parsing.ISyntaxNode;

/**
 * Super class for all math and boolean operations
 *
 */
public abstract class MathOperation extends Operation {

    public MathOperation (String type, int argMin, int argMax) {
        super(type, argMin, argMax);
    }

    /**
     * Take care of packaging the numeric result of executing a math command into
     * an IExecutionContext
     */
    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException {
        List<Number> argsNum = generateNumericArguments(args);
        String result = executeMath(argsNum).toString();
        IExecutionContext retContext = mergeContexts(args);
        retContext.environment().put(Constants.RETURN_VALUE_ENVIRONMENT, result);
        return retContext;
    }

    /**
     * Mathematically evaluate the operation given a list of number arguments
     * 
     * @param args The arguments to pass in for calculation
     * @return The number result
     * @throws MalformedSyntaxException If there was a problem evaluating the list of numbers,
     * e.g. divide by zero
     */
    protected abstract Number executeMath (List<Number> args) throws MalformedSyntaxException;
}
