package slogo.backend.evaluation.commands.booleans;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Greater implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        String argumentOne = args.get(0).environment().get("returnValue");
        double exprOne = Double.parseDouble(argumentOne);
        String argumentTwo = args.get(1).environment().get("returnValue");
        double exprTwo = Double.parseDouble(argumentTwo);
        int value = exprOne>exprTwo ? 1:0;
        String returnArgument = String.valueOf(value);
        //Update or create new ExecutionContext
    }

}
