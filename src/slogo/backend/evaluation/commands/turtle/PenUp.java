package slogo.backend.evaluation.commands.turtle;

import java.util.List;

import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;
import slogo.backend.util.TurtleStatus;

public class PenUp implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        ITurtleStatus status = args.get(0).turtles().get("1");
        
        ICoordinates pos = status.turtlePosition();
        IDirection dir = status.turtleDirection();
        args.get(0).environment().put("returnValue", "0");
        
        
        
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),pos,dir,PenState.UP,status.turtleVisibility());
        args.get(0).turtles().put("1", newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
