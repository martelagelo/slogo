package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class If extends Operation{
    private static final String COMMAND_NAME = "If";
    private static final int MIN_NUM_CONTEXT = 4;
    private static final int MAX_NUM_CONTEXT = Constants.INFINITE_ARGUMENTS;
    public If () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException {
        String expression = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        if(expression.equals(Constants.FALSE_STRING)){
            return previous;
        }
        Evaluator e = new Evaluator();
        IExecutionContext context = previous;
        for(int j = 2; j < args.size()-1; j++){
        	context = e.evaluate(current.children().get(j), context);
        }
        return new ExecutionContext(context.turtles(),context.environment(),context.userDefinedCommands());
    }

}
