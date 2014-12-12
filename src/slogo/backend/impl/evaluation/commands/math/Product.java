// This entire file is part of my masterpiece.
// Michael Ren

package slogo.backend.impl.evaluation.commands.math;

import java.util.List;

import slogo.Constants;
import slogo.backend.impl.evaluation.commands.MathOperation;

/**
 * Return the product of one or many numbers
 *
 */
public class Product extends MathOperation {
    private static final String COMMAND_NAME = "Product";
    private static final int MIN_NUM_CONTEXT = 2;
    private static final int MAX_NUM_CONTEXT = Constants.INFINITE_ARGUMENTS;

    public Product () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    /**
     * Return the product of a list of numbers
     */
    @Override
    protected Number executeMath (List<Number> args) {
        double result = 1;
        for (Number number : args) {
            result *= number.doubleValue();
        }
        return result;
    }
}
