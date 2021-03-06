package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.Coordinates;
import slogo.backend.impl.util.Line;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ILine;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;
import slogo.backend.util.Visibility;

public class Backward extends Operation {
    private static final String COMMAND_NAME = "Backward";
    private static final int MIN_NUM_CONTEXT = 1;
    private static final int MAX_NUM_CONTEXT = 1;

    public Backward () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        String backward = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        double backwardValue = Double.parseDouble(backward);

        Map<String, ITurtleStatus> turtles = args.get(0).turtles();

        for (String name : turtles.keySet()) {

            ITurtleStatus status = turtles.get(name);
            if (status.isActive()) {
                ICoordinates pos = status.turtlePosition();
                IDirection dir = status.turtleDirection();
                PenState pen = status.penState();
                Visibility vis = Visibility.VISIBLE;

                if (pen.equals(PenState.UP)) {
                    vis = Visibility.INVISIBLE;
                }
                double newX = pos.getX().doubleValue() - backwardValue * Math.cos(dir.toRadians());
                double newY = pos.getY().doubleValue() - backwardValue * Math.sin(dir.toRadians());
                ICoordinates newPos = new Coordinates(newX, newY);

                ILine newLine = new Line(pos, newPos, vis);
                status.lineSequence().add(newLine);
                ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(), newPos, dir, pen,
                        status.turtleVisibility(), status.turtleQualities());
                turtles.put(name, newStatus);
            }
        }

        return new ExecutionContext(args.get(0).turtles(), args.get(0).environment(), args.get(0)
                .userDefinedCommands());
    }

}
