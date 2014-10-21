package slogo.backend.impl.evaluation.commands.booleans;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;

public class NotEqual extends Operation{

    public NotEqual() {
		super("NotEqual", 2, 2);
	}

	@Override
    public IExecutionContext executeRaw(List<IExecutionContext> args) {
        String argumentOne = args.get(0).environment().get("returnValue");
        double exprOne = Double.parseDouble(argumentOne);
        String argumentTwo = args.get(1).environment().get("returnValue");
        double exprTwo = Double.parseDouble(argumentTwo);
        int value = exprOne!=exprTwo ? 1:0;
        String returnArgument = String.valueOf(value);
        args.get(0).environment().put("returnValue", returnArgument);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
