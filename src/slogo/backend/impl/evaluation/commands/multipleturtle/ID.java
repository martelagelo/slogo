package slogo.backend.impl.evaluation.commands.multipleTurtle;

import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

public class ID extends Operation {
    private static final String COMMAND_NAME = "ID";
    private static final int MIN_NUM_CONTEXT = 1;
    private static final int MAX_NUM_CONTEXT = 1;

    public ID () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);

    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {

        String id = "";
        Map<String, ITurtleStatus> map = args.get(0).turtles();
        for (String s : map.keySet()) {
            if (map.get(s).isActive()) {
                id = s;
            }
        }
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, id);
        return new ExecutionContext(args.get(0).turtles(), args.get(0).environment(), args.get(0)
                .userDefinedCommands());
    }

}
