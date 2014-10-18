package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;

public class IsPenDown implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        ITurtleStatus status = args.get(0).turtles().get("1");
        PenState pen = status.penState();
        String returnString = pen.equals(PenState.DOWN)? "1":"0";
        args.get(0).environment().put("returnValue", returnString);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
