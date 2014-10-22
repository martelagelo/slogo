package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.Direction;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;

public class SetHeading extends Operation{

    public SetHeading () {
        super("SetHeading", 1, 1);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args) {
        ITurtleStatus status = args.get(0).turtles().get(Constants.DEFAULT_TURTLE_NAME);
        String heading = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        double headingValue = Double.parseDouble(heading);
        ICoordinates pos = status.turtlePosition();
        
        PenState pen = status.penState();
        IDirection newDir = new Direction(headingValue);
        IDirection dir = status.turtleDirection();
        double degreeTurned = Math.abs(newDir.toDegrees()-dir.toDegrees());
        String degreeString = String.valueOf(degreeTurned);
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, degreeString);
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),pos,newDir,pen,status.turtleVisibility());
        args.get(0).turtles().put(Constants.DEFAULT_TURTLE_NAME, newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
