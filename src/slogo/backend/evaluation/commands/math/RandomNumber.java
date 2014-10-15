package slogo.backend.evaluation.commands.math;

import java.util.List;
import java.util.Random;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.commands.MathOperation;

public class RandomNumber extends MathOperation{

    public RandomNumber() {
		super("RandomNumber", 1, 1);
	}

	@Override
	protected Number executeMath(List<Number> args) {
		return new Random().nextDouble() * args.get(0).doubleValue();
	}
}
