package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.ExecutionContext;
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
        String expression = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        if(expression.equals("0")){
            return previous;
        }
        Evaluator e = new Evaluator();
        IExecutionContext context = previous;
        for(int j = 2; j < args.size()-1; j++){
            try {
                context = e.evaluate(current.children().get(j), context);
            } catch (MalformedSyntaxException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return new ExecutionContext(context.turtles(),context.environment(),context.userDefinedCommands());
    }

}
