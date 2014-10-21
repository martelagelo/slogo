package slogo.backend.impl.evaluation.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.parsing.ISyntaxNode;

public class Constant implements IOperation {
	private String value;
	public Constant(String value){
		this.value = value;
	}
	@Override
	public IExecutionContext execute (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
		Map<String, String> newEnvironment = new HashMap<>(args.get(0).environment());
		newEnvironment.put(Constants.RETURN_VALUE_ENVIRONMENT, value);
		return new ExecutionContext(new HashMap<>(args.get(0).turtles()), newEnvironment);
	}
	@Override
	public String type() {
		return Constants.CONSTANT_LABEL;
	}

}
