package slogo.backend.impl.evaluation.commands;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;

public class Result extends Operation {

	public Result() {
		super(Constants.RESULT_LABEL, 1, 1);
	}

	@Override
	public IExecutionContext executeRaw(List<IExecutionContext> args) {
		return new ExecutionContext(args.get(0));
	}

}
