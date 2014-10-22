package slogo.backend.impl.evaluation.commands;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;

public class Result extends Operation {

	public Result() {
		super(Constants.RESULT_LABEL, 1, 1);
	}

	@Override
	public IExecutionContext executeRaw(List<IExecutionContext> args) {
		return mergeContexts(args);
	}

}
