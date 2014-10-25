package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

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

public class Back extends Operation{

    public Back () {
        super("Back", 1, 1);
    }

    @Override
	protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
        ITurtleStatus status = args.get(0).turtles().get(Constants.DEFAULT_TURTLE_NAME);
        String backward = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        double backwardValue = Double.parseDouble(backward);
        ICoordinates pos = status.turtlePosition();
        IDirection dir = status.turtleDirection();
        PenState pen = status.penState();
        Visibility vis = status.turtleVisibility();
        double newX = pos.getX().doubleValue()- backwardValue*Math.cos(dir.toRadians());
        double newY = pos.getY().doubleValue()- backwardValue*Math.sin(dir.toRadians());
        ICoordinates newPos = new Coordinates(newX,newY);
        if(pen.equals(PenState.DOWN)){
            ILine newLine = new Line(pos, newPos, vis);
            status.lineSequence().add(newLine);
        }
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),newPos,dir,pen,status.turtleVisibility(), status.turtleQualities());
        args.get(0).turtles().put(Constants.DEFAULT_TURTLE_NAME, newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

   

}
