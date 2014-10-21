package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class Repeat extends Operation{

    public Repeat (String type, int argMin, int argMax) {
        super(type, 2, 2);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
        // TODO Auto-generated method stub
        String argumentTwo = args.get(1).environment().get("returnValue");
        int times = Integer.parseInt(argumentTwo);
        //Error check times > 1
        Evaluator e = new Evaluator();
        IExecutionContext context = args.get(0);
        for (int i = 0 ; i<times-1; i++){
            try {
                context = e.evaluate(current.children().get(0), context);
            } catch (MalformedSyntaxException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return context;
    }

}
