package slogo.backend.impl.evaluation.commands.booleans;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;

public class Not extends Operation{

    public Not() {
		super("Not", 1, 1);
	}

	@Override
    public IExecutionContext executeRaw(List<IExecutionContext> args) {
        String argument = args.get(0).environment().get("returnValue");
        double test = Double.parseDouble(argument);
        int value = test==0 ? 1:0;
        String returnArgument = String.valueOf(value);
        args.get(0).environment().put("returnValue", returnArgument);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}