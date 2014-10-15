package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Quotient implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        double quotient = Double.parseDouble(args.get(0).environment().get("returnValue"));
        for (int i=1; i<args.size(); i++){
            String argument = args.get(i).environment().get("returnValue");
            double divisor = Double.parseDouble(argument);
            quotient/=divisor;
        }
        String returnArgument = String.valueOf(quotient);
        args.get(0).environment().put("returnValue", returnArgument);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
