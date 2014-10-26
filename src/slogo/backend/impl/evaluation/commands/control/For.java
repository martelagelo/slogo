package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.parsing.ISyntaxNode;

public class For extends LoopOperation {
    private static final String COMMAND_NAME = "For";
    private static final int MIN_NUM_CONTEXT = 9;
    private static final int MAX_NUM_CONTEXT = Constants.INFINITE_ARGUMENTS;

    public For () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException {
        return loop(args, previous, current, 2, 3, 4, 1, 7);
    }

}
