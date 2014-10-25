package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class Make extends Operation{

    public Make (String type, int argMin, int argMax) {
        super(type, 2, 2);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        // TODO Auto-generated method stub
        String expression = args.get(1).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        String variable = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        IExecutionContext context = args.get(1);
        context.environment().put(variable, expression);
        return new ExecutionContext(context.turtles(),context.environment(),context.userDefinedCommands());
        
    }

}
