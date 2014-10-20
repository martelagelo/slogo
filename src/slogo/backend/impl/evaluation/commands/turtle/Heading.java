package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.util.IDirection;
import slogo.backend.util.ITurtleStatus;

public class Heading extends Operation{

    public Heading () {
        super("Heading", 0, 0);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args) {
        ITurtleStatus status = args.get(0).turtles().get("1");
        
        
        IDirection dir = status.turtleDirection();
        double dirDegree = dir.toDegrees();
        String dirString = String.valueOf(dirDegree);
        args.get(0).environment().put("returnValue", dirString);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
