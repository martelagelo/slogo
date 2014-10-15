package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Sum implements IOperation {

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        double sum = 0;
        for (IExecutionContext context: args){
            String argument = context.environment().get("returnValue");
            double doubleArgument = Double.parseDouble(argument);
            sum+=doubleArgument;
        }
        String returnArgument = String.valueOf(sum);
        args.get(0).environment().put("returnValue", returnArgument);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
