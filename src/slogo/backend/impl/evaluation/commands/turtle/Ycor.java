package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.util.ITurtleStatus;

public class Ycor extends Operation{

    public Ycor (String type, int argMin, int argMax) {
        super(type, 0, 0);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        ITurtleStatus status = args.get(0).turtles().get("1");
        double y = status.turtlePosition().getY().doubleValue();
        String returnString = String.valueOf(y);
        args.get(0).environment().put("returnValue", returnString);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
