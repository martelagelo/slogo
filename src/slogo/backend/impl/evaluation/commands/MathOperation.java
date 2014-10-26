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

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException {
        List<Number> argsNum = generateNumericArguments(args);
        String result = executeMath(argsNum).toString();
        IExecutionContext retContext = mergeContexts(args);
        retContext.environment().put(Constants.RETURN_VALUE_ENVIRONMENT, result);
        return retContext;
    }

    protected abstract Number executeMath (List<Number> args) throws MalformedSyntaxException;
}
