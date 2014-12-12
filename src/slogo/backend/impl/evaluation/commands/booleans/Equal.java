// This entire file is part of my masterpiece.
// Michael Ren

package slogo.backend.impl.evaluation.commands.booleans;

import java.util.List;

import slogo.Constants;
import slogo.backend.impl.evaluation.commands.MathOperation;

/**
 * Determine whether two numbers are equal to each other or not
 *
 */
public class Equal extends MathOperation {
    private static final String COMMAND_NAME = "Equal";
    private static final int MIN_NUM_CONTEXT = 2;
    private static final int MAX_NUM_CONTEXT = 2;

    public Equal () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    /**
     * Return true if two numbers have the same value
     */
    @Override
    protected Number executeMath (List<Number> args) {
        return args.get(0).doubleValue() == args.get(1).doubleValue() ? Constants.TRUE_INT
                : Constants.FALSE_INT;
    }

}
