package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.commands.MathOperation;

public class Pow extends MathOperation{

    public Pow() {
		super("Pow", 2, 2);
	}

	@Override
	protected Number executeMath(List<Number> args) {
		return Math.pow(args.get(0).doubleValue(), args.get(1).doubleValue());
	}
}
