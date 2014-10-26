package slogo.backend.impl.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.MathOperation;

public class NaturalLog extends MathOperation{
    private static final String COMMAND_NAME = "Log";
    private static final int MIN_NUM_CONTEXT = 1;
    private static final int MAX_NUM_CONTEXT = 1;
    public NaturalLog() {
		super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
	}

	@Override
	protected Number executeMath(List<Number> args) {
		return Math.log(args.get(0).doubleValue());
	}
}
