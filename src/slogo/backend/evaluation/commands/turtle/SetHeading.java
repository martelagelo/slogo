package slogo.backend.evaluation.commands.turtle;

import java.util.List;

import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.util.Direction;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;
import slogo.backend.util.TurtleStatus;

public class SetHeading implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        ITurtleStatus status = args.get(0).turtles().get("1");
        String heading = args.get(0).environment().get("returnValue");
        double headingValue = Double.parseDouble(heading);
        ICoordinates pos = status.turtlePosition();
        
        PenState pen = status.penState();
        IDirection newDir = new Direction(headingValue);
        IDirection dir = status.turtleDirection();
        double degreeTurned = Math.abs(newDir.toDegrees()-dir.toDegrees());
        String degreeString = String.valueOf(degreeTurned);
        args.get(0).environment().put("returnValue", degreeString);
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),pos,newDir,pen,status.turtleVisibility());
        args.get(0).turtles().put("1", newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
