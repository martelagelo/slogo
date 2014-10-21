package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class Make extends Operation{

    public Make (String type, int argMin, int argMax) {
        super(type, 2, 2);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        // TODO Auto-generated method stub
        String argumentOne = args.get(0).environment().get("returnValue");
        String argumentTwo = args.get(1).environment().get("returnValue");
        IExecutionContext context = args.get(0);
        context.environment().put(argumentOne, argumentTwo);
        return context;
        
    }

}
