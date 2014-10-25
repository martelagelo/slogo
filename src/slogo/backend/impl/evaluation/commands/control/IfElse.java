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

	public IfElse () {
		super("IfElse", 7, -1);
	}

	@Override
	protected IExecutionContext executeRaw (List<IExecutionContext> args,
			IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException {
		String expression = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
		List<ISyntaxNode> children = current.children();
		int secondListOpen = 3;

		//Need error checking?
		for(int i = 4; i<children.size()-1; i++){
			if(children.get(i).type().equals(Constants.OPENING_LIST_LABEL)){
				secondListOpen = i; 
			}
		}
		Evaluator e = new Evaluator();
		IExecutionContext context = previous;
		if(expression.equals("0")){
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
