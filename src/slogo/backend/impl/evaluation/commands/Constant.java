package slogo.backend.impl.evaluation.commands;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Constant implements IOperation{
	private String value;

	public Constant(String value){
		this.value = value;
	}

	@Override
	public IExecutionContext execute(List<IExecutionContext> args) {
		args.get(0).environment().put("returnValue", value);
		return args.get(0);
	}
/*
	@Override
	public Number minArgCount() {
		return 1;
	}

	@Override
	public Number maxArgCount() {
		return 1;
	}
*/
	@Override
	public String type() {
		return "constant";
	}

}
