package slogo.backend.evaluation.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;

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
        Number result = executeMath(argsNum);
        // this is problematic; assume 0th argument for now
        IExecutionContext retContext = args.get(0);
        Map<String,String> retEnvironment = new HashMap<>(retContext.environment());
        String returnArgument = result.toString();
        retEnvironment.put(Constants.RETURN_VALUE_ENVIRONMENT, returnArgument);
        return new ExecutionContext(retContext.turtles(), retEnvironment);
	}
	protected abstract Number executeMath(List<Number> args);
}
