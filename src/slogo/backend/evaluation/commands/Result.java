package slogo.backend.evaluation.commands;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Result implements IOperation {

	@Override
	public IExecutionContext execute(List<IExecutionContext> args) {
		return args.get(0);
	}
/*
	@Override
	public Number minArgCount() {
		return 1;
	}

	@Override
	public Number maxArgCount() {
		// TODO Auto-generated method stub
		return 1;
	}
*/
	@Override
	public String type() {
		return "result";
	}

}
