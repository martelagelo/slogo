package slogo.backend.impl.evaluation.commands.math;

import java.util.List;

import slogo.backend.impl.evaluation.commands.MathOperation;

public class Sine extends MathOperation {
    private static final String COMMAND_NAME = "Sine";
    private static final int MIN_NUM_CONTEXT = 1;
    private static final int MAX_NUM_CONTEXT = 1;

    public Sine () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected Number executeMath (List<Number> args) {
        double argument = Math.toRadians(args.get(0).doubleValue());
        double result = Math.sin(argument);
        return Math.toDegrees(result);
    }
}
