package slogo.backend.evaluation.commands.booleans;

import java.util.List;

import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Less implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        String argumentOne = args.get(0).environment().get("returnValue");
        double exprOne = Double.parseDouble(argumentOne);
        String argumentTwo = args.get(1).environment().get("returnValue");
        double exprTwo = Double.parseDouble(argumentTwo);
        int value = exprOne<exprTwo ? 1:0;
        String returnArgument = String.valueOf(value);
        args.get(0).environment().put("returnValue", returnArgument);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
