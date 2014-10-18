package slogo.backend.impl.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.MathOperation;

public class Sin extends MathOperation{

    public Sin() {
		super("Sin", 1, 1);
	}

	@Override
	protected Number executeMath(List<Number> args) {
		double argument = Math.toRadians(args.get(0).doubleValue());
        double result = Math.sin(argument);
        return Math.toDegrees(result);
	}
}
