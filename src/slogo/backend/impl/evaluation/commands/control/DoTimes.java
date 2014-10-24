package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class DoTimes extends Operation{

    public DoTimes (String type, int argMin, int argMax) {
        super(type, 3, 3);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        // TODO Auto-generated method stub
        String expression = args.get(2).environment().get("returnValue");
        String variable = args.get(1).environment().get("returnValue");
        IExecutionContext context = args.get(5);
        int number = Integer.parseInt(expression);
        Evaluator e = new Evaluator();
        for(int i = 1; i<=number; i++){
            context.environment().put(variable, String.valueOf(i));
            try {
                for(int j = 5; j < args.size()-1; j++){
                context = e.evaluate(current.children().get(j), context);
                }
            } catch (MalformedSyntaxException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return context;
    }

}
