package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class IfElse extends Operation{
    private static final String COMMAND_NAME = "IfElse";
    private static final int MIN_NUM_CONTEXT = 7;
    private static final int MAX_NUM_CONTEXT = Constants.INFINITE_ARGUMENTS;
	public IfElse () {
		super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
	}

	@Override
	protected IExecutionContext executeRaw (List<IExecutionContext> args,
			IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException {
		String expression = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
		List<ISyntaxNode> children = current.children();
		int secondListOpen = 4;

		
		for(int i = 4; i<children.size()-2; i++){
			if(children.get(i).type().equals(Constants.OPENING_LIST_LABEL)){
				secondListOpen = i; 
			}
		}
		Evaluator e = new Evaluator();
		IExecutionContext context = previous;
		if(expression.equals(Constants.FALSE_STRING)){
			for(int j = 2; j < secondListOpen-1; j++){
				context = e.evaluate(current.children().get(j), context);
			}

		}
		else{
			for(int k = secondListOpen+1; k < args.size()-1; k++){
				context = e.evaluate(current.children().get(k), context);
			}
		}
		return new ExecutionContext(context.turtles(),context.environment(),context.userDefinedCommands());
	}

}
