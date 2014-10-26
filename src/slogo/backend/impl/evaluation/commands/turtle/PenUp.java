package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;

public class PenUp extends Operation {
    private static final String COMMAND_NAME = "PenUp";
    private static final int MIN_NUM_CONTEXT = 1;
    private static final int MAX_NUM_CONTEXT = 1;

    public PenUp () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        Map<String, ITurtleStatus> turtles = args.get(0).turtles();

        for (String name : turtles.keySet()) {

            ITurtleStatus status = turtles.get(name);
            if (status.isActive()) {

                ICoordinates pos = status.turtlePosition();
                IDirection dir = status.turtleDirection();

                ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(), pos, dir,
                        PenState.UP, status.turtleVisibility(), status.turtleQualities());
                turtles.put(name, newStatus);
            }
        }
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, Constants.FALSE_STRING);
        return new ExecutionContext(args.get(0).turtles(), args.get(0).environment(), args.get(0)
                .userDefinedCommands());
    }

}
