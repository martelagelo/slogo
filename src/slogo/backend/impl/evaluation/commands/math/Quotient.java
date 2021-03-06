package slogo.backend.impl.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.commands.MathOperation;

public class Quotient extends MathOperation {
    private static final String COMMAND_NAME = "Quotient";
    private static final int MIN_NUM_CONTEXT = 2;
    private static final int MAX_NUM_CONTEXT = 2;

    public Quotient () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected Number executeMath (List<Number> args) throws MalformedSyntaxException {
        try {
            return args.get(0).doubleValue() / args.get(1).doubleValue();
        } catch (ArithmeticException e) {
            throw new MalformedSyntaxException(e.getMessage());
        }
    }
}
