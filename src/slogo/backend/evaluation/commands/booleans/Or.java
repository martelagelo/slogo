package slogo.backend.evaluation.commands.booleans;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Or implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        String argumentOne = args.get(0).environment().get("returnValue");
        double testOne = Double.parseDouble(argumentOne);
        String argumentTwo = args.get(1).environment().get("returnValue");
        double testTwo = Double.parseDouble(argumentTwo);
        int value = testOne!=0||testTwo!=0 ? 1:0;
        String returnArgument = String.valueOf(value);
        //Update or create new ExecutionContext
    }

}
