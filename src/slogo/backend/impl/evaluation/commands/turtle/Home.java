package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.Coordinates;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;

public class Home extends Operation{

    public Home () {
        super("Home", 1, 1);
    }

    @Override
	protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
        ITurtleStatus status = args.get(0).turtles().get(Constants.DEFAULT_TURTLE_NAME);

        ICoordinates pos = status.turtlePosition();
        IDirection dir = status.turtleDirection();
        PenState pen = status.penState();

        double newX = 0;
        double newY = 0;
        ICoordinates newPos = new Coordinates(newX,newY);
        String distance = String.valueOf(newPos.getDistance(pos).doubleValue());
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, distance);


        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),newPos,dir,pen,status.turtleVisibility(), status.turtleQualities());
        args.get(0).turtles().put(Constants.DEFAULT_TURTLE_NAME, newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
