package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Minus implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        String argument = args.get(0).environment().get("returnValue");
        double number = Double.parseDouble(argument);
        double minusNumber = 0 - number;
        String returnArgument = String.valueOf(minusNumber);
      //update or create a new ExecutionContext and return it
    }

}
