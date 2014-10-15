package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.commands.MathOperation;

public class Sum extends MathOperation {

    public Sum() {
		super("Sum", 2, -1);
	}

	@Override
	protected Number executeMath(List<Number> args) {
		double result = 0;
		for (Number number: args){
			result += number.doubleValue();
		}
		return result;
	}
}
