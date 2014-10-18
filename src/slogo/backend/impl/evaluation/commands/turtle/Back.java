package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.util.Coordinates;
import slogo.backend.impl.util.Line;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ILine;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;
import slogo.backend.util.Visibility;

public class Back implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        ITurtleStatus status = args.get(0).turtles().get("1");
        String backward = args.get(0).environment().get("returnValue");
        double backwardValue = Double.parseDouble(backward);
        ICoordinates pos = status.turtlePosition();
        IDirection dir = status.turtleDirection();
        PenState pen = status.penState();
        Visibility vis = Visibility.VISIBLE;
      
        if(pen.equals(PenState.UP)){
            vis = Visibility.INVISIBLE;
        }
        double newX = pos.getX().doubleValue()- backwardValue*Math.cos(dir.toRadians());
        double newY = pos.getY().doubleValue()+ backwardValue*Math.sin(dir.toRadians());
        ICoordinates newPos = new Coordinates(newX,newY);
        
        ILine newLine = new Line(pos, newPos, vis);
        status.lineSequence().add(newLine);
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),newPos,dir,pen,status.turtleVisibility());
        args.get(0).turtles().put("1", newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
