package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.Direction;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;

public class Right extends Operation {
    private static final String COMMAND_NAME = "Right";
    private static final int MIN_NUM_CONTEXT = 1;
    private static final int MAX_NUM_CONTEXT = 1;

    public Right () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        String right = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        double rightValue = Double.parseDouble(right);

        Map<String, ITurtleStatus> turtles = args.get(0).turtles();

        for (String name : turtles.keySet()) {

            ITurtleStatus status = turtles.get(name);
            if (status.isActive()) {

                ICoordinates pos = status.turtlePosition();
                IDirection dir = status.turtleDirection();
                PenState pen = status.penState();
                IDirection newDir = new Direction(dir.toDegrees() - rightValue);

                ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(), pos, newDir, pen,
                        status.turtleVisibility(), status.turtleQualities());
                turtles.put(name, newStatus);
            }
        }
        return new ExecutionContext(args.get(0).turtles(), args.get(0).environment(), args.get(0)
                .userDefinedCommands());
    }

}
