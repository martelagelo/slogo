package slogo.backend.impl.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.MathOperation;

public class Remainder extends MathOperation{

    public Remainder() {
		super("Remainder", 2, 2);
	}

	@Override
	protected Number executeMath(List<Number> args) {
		return args.get(0).doubleValue() % args.get(1).doubleValue();
	}
}
