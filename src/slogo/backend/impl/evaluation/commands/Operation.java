// This entire file is part of my masterpiece.
// Michael Ren

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

    /**
     * Create a new operation
     * 
     * @param type An identifier for this operation
     * @param argMin The minimum number of arguments for this operation
     * @param argMax The maximum number of arguments for this operation
     */
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

    /**
     * Provide a policy for which child context should be promoted to represent the state
     * of the parent
     * 
     * @param contexts A list of candidate child contexts to choose from
     * @return The chosen context
     */
    protected IExecutionContext mergeContexts (List<IExecutionContext> contexts) {
        return new ExecutionContext(contexts.get(0));
    }

    /**
     * Get the string return value representation from a context
     * 
     * @param context The context to get the return value from
     * @return The string representing the return value, or an empty string if
     * no return value exists
     */
    protected String getContextReturnValue (IExecutionContext context) {
        if (context.environment().containsKey(Constants.RETURN_VALUE_ENVIRONMENT)) {
            return context.environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        } else {
            return "";
        }
    }

    /**
     * Get the name of the variable that was returned in the return value, if any
     * 
     * @param context The context to get the returned variable name
     * @return The name of the variable that was returned
     */
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
