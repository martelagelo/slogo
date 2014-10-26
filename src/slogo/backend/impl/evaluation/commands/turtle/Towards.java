package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

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

public class Towards extends Operation{

    public Towards () {
        super("Towards", 2, 2);
    }

    @Override
	protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
        ITurtleStatus status = args.get(0).turtles().get(Constants.DEFAULT_TURTLE_NAME);
        String xString = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        double faceXValue = Double.parseDouble(xString);
        String yString = args.get(1).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        double faceYValue = Double.parseDouble(yString);
        ICoordinates pos = status.turtlePosition();
        double xValue = pos.getX().doubleValue();
        double yValue = pos.getY().doubleValue();
        double newRad = Math.atan((faceYValue-yValue)/(faceXValue-xValue));
        double newDegree = Math.toDegrees(newRad);
        
        PenState pen = status.penState();
        IDirection newDir = new Direction(newDegree);
        IDirection dir = status.turtleDirection();
        double degreeTurned = Math.abs(newDir.toDegrees()-dir.toDegrees());
        String degreeString = String.valueOf(degreeTurned);
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, degreeString);
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),pos,newDir,pen,status.turtleVisibility(), status.turtleQualities());
        args.get(0).turtles().put(Constants.DEFAULT_TURTLE_NAME, newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
