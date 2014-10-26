package slogo.backend.impl.evaluation.commands.math;

import java.util.List;
import java.util.Random;

import slogo.backend.impl.evaluation.commands.MathOperation;

public class RandomNumber extends MathOperation {
    private static final String COMMAND_NAME = "RandomNumber";
    private static final int MIN_NUM_CONTEXT = 1;
    private static final int MAX_NUM_CONTEXT = 1;

    public RandomNumber () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected Number executeMath (List<Number> args) {
        return new Random().nextDouble() * args.get(0).doubleValue();
    }
}
