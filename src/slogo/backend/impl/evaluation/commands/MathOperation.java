package slogo.backend.impl.evaluation.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;

public abstract class MathOperation extends Operation{

	public MathOperation(String type, int argMin, int argMax) {
		super(type, argMin, argMax);
	}

	@Override
	protected IExecutionContext executeRaw(List<IExecutionContext> args) {
		List<Number> argsNum = new ArrayList<>();
		for (IExecutionContext arg: args){
			argsNum.add(new Double(arg.environment().get(Constants.RETURN_VALUE_ENVIRONMENT)));
		}
        String result = executeMath(argsNum).toString();
        // this is problematic; assume 0th argument for now
        IExecutionContext retContext = new ExecutionContext(args.get(0));
        retContext.environment().put(Constants.RETURN_VALUE_ENVIRONMENT, result);
        return retContext;
	}
	protected abstract Number executeMath(List<Number> args);
}
