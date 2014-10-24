package slogo.backend.impl.evaluation.commands.multipleTurtle;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class Turtles extends Operation {

    public Turtles (String type, int argMin, int argMax) {
        super("Turtles", 1, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        // TODO Auto-generated method stub
        String returnValue = String.valueOf(args.get(0).turtles().size());
        args.get(0).environment().put("returnValue", returnValue);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
