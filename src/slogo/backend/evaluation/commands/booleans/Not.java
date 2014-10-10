package slogo.backend.evaluation.commands.booleans;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Not implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        String argument = args.get(0).environment().get("returnValue");
        double test = Double.parseDouble(argument);
        int value = test==0 ? 1:0;
        String returnArgument = String.valueOf(value);
        //Update or create new ExecutionContext
    }

}
