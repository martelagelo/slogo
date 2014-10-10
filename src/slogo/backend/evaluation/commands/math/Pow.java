package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Pow implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        String argumentOne = args.get(0).environment().get("returnValue");
        double base = Double.parseDouble(argumentOne);
        String argumentTwo = args.get(1).environment().get("returnValue");
        double exponent = Double.parseDouble(argumentTwo);
        double power = Math.pow(base,exponent);
      //check for error
        String returnArgument = String.valueOf(power);
        //update or create a new ExecutionContext and return it
    }

}
