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

public class Forward  extends Operation{

    public Forward () {
        super("Forward", 1, 1);
    }

    @Override
	protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
        String forward = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        double forwardValue = Double.parseDouble(forward);
        Map <String,ITurtleStatus> turtles = args.get(0).turtles();

        for(String name: turtles.keySet()){
        
        ITurtleStatus status = turtles.get(name);
        if(status.isActive()){
        ICoordinates pos = status.turtlePosition();
        IDirection dir = status.turtleDirection();
        PenState pen = status.penState();
        Visibility vis = Visibility.VISIBLE;
      
        if(pen.equals(PenState.UP)){
            vis = Visibility.INVISIBLE;
        }
        double newX = pos.getX().doubleValue()+ forwardValue*Math.cos(dir.toRadians());
        double newY = pos.getY().doubleValue()+ forwardValue*Math.sin(dir.toRadians());
        ICoordinates newPos = new Coordinates(newX,newY);
        
        ILine newLine = new Line(pos, newPos, vis);
        status.lineSequence().add(newLine);
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),newPos,dir,pen,status.turtleVisibility(), status.turtleQualities());
        turtles.put(name, newStatus);
        }
        }
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
