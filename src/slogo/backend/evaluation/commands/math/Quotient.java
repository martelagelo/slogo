package slogo.backend.evaluation.commands.math;

import java.util.List;

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
        //update or create a new ExecutionContext and return it
    }

}
