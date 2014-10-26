package slogo.backend.impl.evaluation.commands;

import java.util.ArrayList;
import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.parsing.ISyntaxNode;

/**
 * Super class for all non-math operations
 *
 */
public abstract class Operation implements IOperation {

    private String type;
    private int argMin;
    private int argMax;

    public Operation (String type, int argMin, int argMax) {
        this.type = type;
        this.argMin = argMin;
        this.argMax = argMax;
    }

    /**
     * check for valid number of arguments
     * 
     * @param args
     * @throws MalformedSyntaxException
     */
    protected void validateArguments (List<IExecutionContext> args) throws MalformedSyntaxException {
        if (args.size() < argMin) {
            throw new MalformedSyntaxException("Arg size is below " + argMin);
        }
        if (args.size() > argMax && argMax != Constants.INFINITE_ARGUMENTS) {
            throw new MalformedSyntaxException("Arg size is above " + argMax);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see slogo.backend.evaluation.IOperation#execute(java.util.List,
     * slogo.backend.evaluation.IExecutionContext,
     * slogo.backend.parsing.ISyntaxNode)
     */
    @Override
    public IExecutionContext execute (List<IExecutionContext> args, IExecutionContext previous,
            ISyntaxNode current) throws MalformedSyntaxException {
        validateArguments(args);
        return executeRaw(args, previous, current);
    }

    /**
     * Process child ExecutionContext and merge them into one single
     * ExecutionContext
     * 
     * @param args
     *            , a list of child ExecutionContexts
     * @param previous
     *            , the previous ExecutionContext
     * @param current
     *            , the current syntax node
     * @return the merged ExecutionContext
     * @throws MalformedSyntaxException
     */
    protected abstract IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException;

    protected List<Number> generateNumericArguments (List<IExecutionContext> args) {
        List<Number> argsNum = new ArrayList<>();
        for (IExecutionContext context : args) {
            argsNum.add(new Double(context.environment().get(Constants.RETURN_VALUE_ENVIRONMENT)));
        }
        return argsNum;
    }

    protected IExecutionContext mergeContexts (List<IExecutionContext> contexts) {
        return new ExecutionContext(contexts.get(0));
    }

    protected String getContextReturnValue (IExecutionContext context) {
        if (context.environment().containsKey(Constants.RETURN_VALUE_ENVIRONMENT)) {
            return context.environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        } else {
            return "";
        }
    }

    protected String getContextReturnedVariableName (IExecutionContext context) {
        if (context.environment().containsKey(Constants.RETURNED_VARIABLE_NAME)) {
            return context.environment().get(Constants.RETURNED_VARIABLE_NAME);
        } else {
            return "";
        }
    }

    @Override
    public String type () {
        return type;
    }

}
