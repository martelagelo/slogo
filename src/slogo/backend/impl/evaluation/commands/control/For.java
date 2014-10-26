package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class For extends Operation{
    private static final String COMMAND_NAME = "For";
    private static final int MIN_NUM_CONTEXT = 9;
    private static final int MAX_NUM_CONTEXT = Integer.MAX_VALUE;
	public For () {
		super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
	}

	@Override
	protected IExecutionContext executeRaw (List<IExecutionContext> args,
			IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException {        
		String variable = args.get(1).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
		String start = args.get(2).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
		String end = args.get(3).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
		String increment = args.get(4).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
		IExecutionContext context = previous;
		double startNum = Double.parseDouble(start);
		double endNum = Double.parseDouble(end);
		double incrementNum = Double.parseDouble(increment);
		Evaluator e = new Evaluator();
		for(double i = startNum; i<=endNum; i+=incrementNum){
			context.environment().put(variable, String.valueOf(i));
			for(int j = 7; j < args.size()-1; j++){
				context = e.evaluate(current.children().get(j), context);
			}
		}
		return new ExecutionContext(context.turtles(),context.environment(),context.userDefinedCommands());
	}

}
