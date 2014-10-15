package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.commands.MathOperation;

public class Log extends MathOperation{

    public Log() {
		super("Log", 1, 1);
	}

	@Override
	protected Number executeMath(List<Number> args) {
		return Math.log(args.get(0).doubleValue());
	}
}
