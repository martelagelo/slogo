package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class Repeat extends Operation{

    public Repeat (String type, int argMin, int argMax) {
        super(type, 4, Integer.MAX_VALUE);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
        // TODO Auto-generated method stub
        String expression = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        int times = Integer.parseInt(expression);
        //Error check times > 1
        Evaluator e = new Evaluator();
        IExecutionContext context = previous;
        for (int i = 0 ; i<times-1; i++){
            try {
                for(int k = 2; k < args.size()-1; k++){
                    
                
                context = e.evaluate(current.children().get(k), context);
                }
            } catch (MalformedSyntaxException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return new ExecutionContext(context.turtles(),context.environment(),context.userDefinedCommands());
    }

}
