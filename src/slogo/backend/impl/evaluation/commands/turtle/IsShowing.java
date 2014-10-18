package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.Visibility;

public class IsShowing extends Operation{

    public IsShowing (String type, int argMin, int argMax) {
        super(type, 0, 0);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        ITurtleStatus status = args.get(0).turtles().get("1");
        Visibility vis = status.turtleVisibility();
        String returnString = vis.equals(Visibility.VISIBLE)? "1":"0";
        args.get(0).environment().put("returnValue", returnString);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
