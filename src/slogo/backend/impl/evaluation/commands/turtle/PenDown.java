package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;

public class PenDown extends Operation{

    public PenDown (String type, int argMin, int argMax) {
        super(type, 0, 0);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        ITurtleStatus status = args.get(0).turtles().get("1");
       
        ICoordinates pos = status.turtlePosition();
        IDirection dir = status.turtleDirection();
        args.get(0).environment().put("returnValue", "1");
        
        
        
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),pos,dir,PenState.DOWN,status.turtleVisibility());
        args.get(0).turtles().put("1", newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
