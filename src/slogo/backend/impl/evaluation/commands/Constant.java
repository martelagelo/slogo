package slogo.backend.impl.evaluation.commands;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.parsing.ISyntaxNode;

public class Constant extends Operation {
	private String value;
	public Constant(String value){
		super(Constants.CONSTANT_LABEL, 1, 1);
		this.value = value;
	}
	@Override
	public IExecutionContext executeRaw(List<IExecutionContext> args) {
		IExecutionContext newContext = mergeContexts(args);
		newContext.environment().put(Constants.RETURN_VALUE_ENVIRONMENT, value);
		return newContext;
	}

}
