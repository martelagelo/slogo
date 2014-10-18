package slogo.backend.impl.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.MathOperation;

public class Minus extends MathOperation{

    public Minus() {
		super("Minus", 1, 1);
	}

	@Override
	protected Number executeMath(List<Number> args) {
		return 0 - args.get(0).doubleValue();
	}
}
