package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class If extends Operation{

    public If (String type, int argMin, int argMax) {
        super(type, 2, 2);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        // TODO Auto-generated method stub
        String argumentTwo = args.get(1).environment().get("returnValue");
        if(argumentTwo.equals("0")){
            return previous;
        }
        return args.get(0);
    }

}
