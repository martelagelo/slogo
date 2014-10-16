package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.commands.MathOperation;

public class Tan extends MathOperation{

    public Tan() {
		super("Tan", 1, 1);
	}

	@Override
	protected Number executeMath(List<Number> args) {
		double argument = Math.toRadians(args.get(0).doubleValue());
        double result = Math.tan(argument);
        return Math.toDegrees(result);
	}
}
