package slogo.backend.impl.evaluation.commands.math;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.MathOperation;

public class Sum extends MathOperation {
    private static final String COMMAND_NAME = "Sum";
    private static final int MIN_NUM_CONTEXT = 2;
    private static final int MAX_NUM_CONTEXT = Constants.INFINITE_ARGUMENTS;
    public Sum() {
		super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
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
