package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Atan implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        String argument = args.get(0).environment().get("returnValue");
        double tan = Double.parseDouble(argument);
        double radian = Math.atan(tan);
        double degree = Math.toDegrees(radian);
        //check for error
        String returnArgument = String.valueOf(degree);
        args.get(0).environment().put("returnValue", returnArgument);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
