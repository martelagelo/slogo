package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Remainder implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        String argumentOne = args.get(0).environment().get("returnValue");
        double dividend = Double.parseDouble(argumentOne);
        String argumentTwo = args.get(1).environment().get("returnValue");
        double divisor = Double.parseDouble(argumentTwo);
        double remainder = dividend % divisor;
        String returnArgument = String.valueOf(remainder);
        //update or create a new ExecutionContext and return it
    }

}
