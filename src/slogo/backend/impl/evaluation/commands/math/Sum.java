// This entire file is part of my masterpiece.
// Michael Ren

package slogo.backend.impl.evaluation.commands.math;

import java.util.List;

import slogo.Constants;
import slogo.backend.impl.evaluation.commands.MathOperation;

/**
 * Return the sum of one or many numbers
 *
 */
public class Sum extends MathOperation {
    private static final String COMMAND_NAME = "Sum";
    private static final int MIN_NUM_CONTEXT = 2;
    private static final int MAX_NUM_CONTEXT = Constants.INFINITE_ARGUMENTS;

    public Sum () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    /**
     * Add a list of numbers together, returning the result
     */
    @Override
    protected Number executeMath (List<Number> args) {
        double result = 0;
        for (Number number : args) {
            result += number.doubleValue();
        }
        return result;
    }
}
