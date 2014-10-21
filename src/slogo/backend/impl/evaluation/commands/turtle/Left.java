package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

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

public class Left extends Operation{

    public Left () {
        super("Left", 1, 1);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
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
