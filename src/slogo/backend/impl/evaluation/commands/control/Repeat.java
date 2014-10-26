package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class Repeat extends Operation{
    private static final String COMMAND_NAME = "Repeat";
    private static final int MIN_NUM_CONTEXT = 4;
    private static final int MAX_NUM_CONTEXT = Constants.INFINITE_ARGUMENTS;
    public Repeat () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException {
        String expression = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        int times = Integer.parseInt(expression);
        //Error check times > 1
        Evaluator e = new Evaluator();
        IExecutionContext context = previous;
        for (int i = 1 ; i <= times; i++){
        	context.environment().put(Constants.DEFAULT_COUNTER_VARIABLE_NAME, Integer.toString(i));
        	for(int k = 2; k < args.size()-1; k++){
        		context = e.evaluate(current.children().get(k), context);
        	}
        }
        return new ExecutionContext(context.turtles(),context.environment(),context.userDefinedCommands());
    }

}
