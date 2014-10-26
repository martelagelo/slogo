package slogo.backend.impl.evaluation.commands.booleans;

import java.util.List;

import slogo.Constants;
import slogo.backend.impl.evaluation.commands.MathOperation;

public class Not extends MathOperation {
    private static final String COMMAND_NAME = "Not";
    private static final int MIN_NUM_CONTEXT = 1;
    private static final int MAX_NUM_CONTEXT = 1;

    public Not () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected Number executeMath (List<Number> args) {
        return args.get(0).intValue() == Constants.FALSE_INT ? Constants.TRUE_INT
                : Constants.FALSE_INT;
    }

}
