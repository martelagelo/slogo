package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.util.Direction;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;

public class Towards implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        ITurtleStatus status = args.get(0).turtles().get("1");
        String xString = args.get(0).environment().get("returnValue");
        double faceXValue = Double.parseDouble(xString);
        String yString = args.get(1).environment().get("returnValue");
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
        args.get(0).environment().put("returnValue", degreeString);
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),pos,newDir,pen,status.turtleVisibility());
        args.get(0).turtles().put("1", newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
