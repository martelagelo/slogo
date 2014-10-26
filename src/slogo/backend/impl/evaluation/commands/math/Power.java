package slogo.backend.impl.evaluation.commands.math;

import java.util.List;

import slogo.backend.impl.evaluation.commands.MathOperation;

public class Power extends MathOperation {
    private static final String COMMAND_NAME = "Power";
    private static final int MIN_NUM_CONTEXT = 2;
    private static final int MAX_NUM_CONTEXT = 2;

    public Power () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected Number executeMath (List<Number> args) {
        return Math.pow(args.get(0).doubleValue(), args.get(1).doubleValue());
    }
}
