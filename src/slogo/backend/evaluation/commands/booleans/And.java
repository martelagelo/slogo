package slogo.backend.evaluation.commands.booleans;

import java.util.List;

import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class And implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        String argumentOne = args.get(0).environment().get("returnValue");
        double testOne = Double.parseDouble(argumentOne);
        String argumentTwo = args.get(1).environment().get("returnValue");
        double testTwo = Double.parseDouble(argumentTwo);
        int value = testOne!=0&&testTwo!=0 ? 1:0;
        String returnArgument = String.valueOf(value);
        args.get(0).environment().put("returnValue", returnArgument);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
