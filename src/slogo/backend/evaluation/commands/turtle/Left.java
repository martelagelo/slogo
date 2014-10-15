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

public class Left implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        ITurtleStatus status = args.get(0).turtles().get("1");
        String left = args.get(0).environment().get("returnValue");
        double leftValue = Double.parseDouble(left);
        ICoordinates pos = status.turtlePosition();
        IDirection dir = status.turtleDirection();
        PenState pen = status.penState();
        IDirection newDir = new Direction(dir.toDegrees()+leftValue);



        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),pos,newDir,pen,status.turtleVisibility());
        args.get(0).turtles().put("1", newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
