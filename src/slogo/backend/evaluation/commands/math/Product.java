package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Product implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        double product = 1;
        for (IExecutionContext context: args){
            String argument = context.environment().get("returnValue");
            double doubleArgument = Double.parseDouble(argument);
            product*=doubleArgument;
        }
        String returnArgument = String.valueOf(product);
        args.get(0).environment().put("returnValue", returnArgument);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
