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

public class SetXY implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        ITurtleStatus status = args.get(0).turtles().get("1");
        String newX = args.get(0).environment().get("returnValue");
        String newY = args.get(1).environment().get("returnValue");
        double newXValue = Double.parseDouble(newX);
        double newYValue = Double.parseDouble(newY);
        ICoordinates pos = status.turtlePosition();
        IDirection dir = status.turtleDirection();
        PenState pen = status.penState();
        Visibility vis = Visibility.VISIBLE;

        if(pen.equals(PenState.UP)){
            vis = Visibility.INVISIBLE;
        }

        ICoordinates newPos = new Coordinates(newXValue,newYValue);
        String distance = String.valueOf(newPos.getDistance(pos).doubleValue());
        args.get(0).environment().put("returnValue", distance);
        ILine newLine = new Line(pos, newPos, vis);
        status.lineSequence().add(newLine);
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),newPos,dir,pen,status.turtleVisibility());
        args.get(0).turtles().put("1", newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
