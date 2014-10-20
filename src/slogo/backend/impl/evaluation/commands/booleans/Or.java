package slogo.backend.impl.evaluation.commands.booleans;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;

public class Or extends Operation{

    public Or() {
		super("Or", 2, 2);
	}

	@Override
    public IExecutionContext executeRaw(List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        String argumentOne = args.get(0).environment().get("returnValue");
        double testOne = Double.parseDouble(argumentOne);
        String argumentTwo = args.get(1).environment().get("returnValue");
        double testTwo = Double.parseDouble(argumentTwo);
        int value = testOne!=0||testTwo!=0 ? 1:0;
        String returnArgument = String.valueOf(value);
        args.get(0).environment().put("returnValue", returnArgument);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
