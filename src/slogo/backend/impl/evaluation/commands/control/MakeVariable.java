package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class MakeVariable extends Operation {
    private static final String COMMAND_NAME = "Make";
    private static final int MIN_NUM_CONTEXT = 2;
    private static final int MAX_NUM_CONTEXT = 2;

    public MakeVariable () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        String variable = args.get(0).environment().get(Constants.RETURNED_VARIABLE_NAME);
        String expression = args.get(1).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        IExecutionContext context = args.get(1);
        context.environment().put(variable, expression);
        return new ExecutionContext(context.turtles(), context.environment(),
                context.userDefinedCommands());
    }

}
