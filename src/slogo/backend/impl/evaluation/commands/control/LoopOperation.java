package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public abstract class LoopOperation extends Operation {

	public LoopOperation(String type, int argMin, int argMax) {
		super(type, argMin, argMax);
	}

	protected abstract IExecutionContext executeRaw(List<IExecutionContext> args,IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException;

	protected IExecutionContext loop(
			List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current,
			int countStartIndex, int countEndIndex, int incrementIndex,
			int counterNameIndex,
			int startOfCommands) throws MalformedSyntaxException{
		int countStart = 1;
		int countEnd;
		int increment = 1;
		String counterName = Constants.DEFAULT_COUNTER_VARIABLE_NAME;
		if (countStartIndex != -1) {
			String retVal = getContextReturnValue(args.get(countStartIndex));
			if (retVal.length() != 0) {
				countStart = Integer.parseInt(retVal);
			}
			else {
				throw new MalformedSyntaxException(
						"The starting index for the loop could not be found at position: " + countStartIndex);
			}
		}
		countEnd = Integer.parseInt(
					getContextReturnValue(args.get(countEndIndex)));
		if (counterNameIndex != -1) {
			// we don't want return value
			String varName = getContextReturnedVariableName(args.get(counterNameIndex));
			if (varName.length() != 0){
				counterName = varName;
			}
			else {
				throw new MalformedSyntaxException(
						"The counter variable name for the loop could not be found at position: " + counterNameIndex);
			}
		}
		if (incrementIndex != -1) {
			String retVal = getContextReturnValue(args.get(incrementIndex));
			if (retVal.length() != 0) {
				increment = Integer.parseInt(retVal);
			}
			else {
				throw new MalformedSyntaxException(
						"The increment index for the loop could not be found at position: " + incrementIndex);
			}
		}
        Evaluator e = new Evaluator();
        IExecutionContext context = previous;
        for (int i = countStart ; i <= countEnd; i += increment){
        	context.environment().put(counterName, Integer.toString(i));
        	for(int k = startOfCommands; k < args.size()-1; k++){
        		context = e.evaluate(current.children().get(k), context);
        	}
        }
        return new ExecutionContext(context.turtles(),context.environment(),context.userDefinedCommands());
	}
	//FOR [ variable start end increment ]
	//[ command(s) ]
	//REPEAT expr [ command(s) ]
}
