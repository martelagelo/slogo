package slogo.backend.impl.evaluation.commands;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.parsing.ISyntaxNode;

public class Result extends Operation {

	public Result() {
		super(Constants.RESULT_LABEL, 1, 1);
	}

	@Override
	public IExecutionContext executeRaw(List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
		return mergeContexts(args);
	}

}
