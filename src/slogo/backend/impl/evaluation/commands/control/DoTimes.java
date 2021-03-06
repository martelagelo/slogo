package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.parsing.ISyntaxNode;

public class DoTimes extends LoopOperation {
    private static final String COMMAND_NAME = "DoTimes";
    private static final int MIN_NUM_CONTEXT = 7;
    private static final int MAX_NUM_CONTEXT = Constants.INFINITE_ARGUMENTS;

    public DoTimes () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException {
        return loop(args, previous, current, -1, 2, -1, 1, 5);
    }

}
