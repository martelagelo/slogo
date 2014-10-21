package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class IfElse extends Operation{

    public IfElse (String type, int argMin, int argMax) {
        super(type, 3, 3);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        // TODO Auto-generated method stub
        String expression = args.get(0).environment().get("returnValue");
        if(expression.equals("0")){
            return args.get(2);
        }
        return args.get(1);
    }

}
