package slogo.backend.evaluation.commands.turtle;

import java.util.List;

import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.util.IDirection;
import slogo.backend.util.ITurtleStatus;

public class Heading implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        ITurtleStatus status = args.get(0).turtles().get("1");
        
        
        IDirection dir = status.turtleDirection();
        double dirDegree = dir.toDegrees();
        String dirString = String.valueOf(dirDegree);
        args.get(0).environment().put("returnValue", dirString);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
