package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.IDirection;
import slogo.backend.util.ITurtleStatus;

public class Heading extends Operation {
    private static final String COMMAND_NAME = "Heading";
    private static final int MIN_NUM_CONTEXT = 1;
    private static final int MAX_NUM_CONTEXT = 1;

    public Heading () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        Map<String, ITurtleStatus> turtles = args.get(0).turtles();
        String lastActive = null;
        for (String name : turtles.keySet()) {
            if (turtles.get(name).isActive()) {
                lastActive = name;
            }
        }

        ITurtleStatus status = turtles.get(lastActive);

        IDirection dir = status.turtleDirection();
        double dirDegree = dir.toDegrees();
        String dirString = String.valueOf(dirDegree);
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, dirString);
        return new ExecutionContext(args.get(0).turtles(), args.get(0).environment(), args.get(0)
                .userDefinedCommands());
    }

}
