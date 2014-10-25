package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class Make extends Operation{

    public Make () {
        super("Make", 2, 2);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        String expression = args.get(1).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        String variable = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        IExecutionContext context = args.get(1);
        context.environment().put(variable, expression);
        return context;
    }

}
