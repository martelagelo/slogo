// This entire file is part of my masterpiece.
// Michael Ren

package slogo.backend.impl.evaluation.commands.booleans;

import java.util.List;

import slogo.Constants;
import slogo.backend.impl.evaluation.commands.MathOperation;

public class And extends MathOperation {

    private static final String COMMAND_NAME = "And";
    private static final int MIN_NUM_CONTEXT = 2;
    private static final int MAX_NUM_CONTEXT = 2;

    public And () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected Number executeMath (List<Number> args) {
        return (args.get(0).intValue() != Constants.FALSE_INT)
                && (args.get(1).intValue() != Constants.FALSE_INT) ? Constants.TRUE_INT
                : Constants.FALSE_INT;
    }

}
