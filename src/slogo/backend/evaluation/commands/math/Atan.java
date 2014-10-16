package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.commands.MathOperation;

public class Atan extends MathOperation{

    public Atan() {
		super("Atan", 1, 1);
	}

	@Override
	protected Number executeMath(List<Number> args) {
		double argument = (args.get(0).doubleValue());
        double result = Math.atan(argument);
        return Math.toDegrees(result);
	}
}
