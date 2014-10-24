package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class For extends Operation{

    public For (String type, int argMin, int argMax) {
        super(type, 5, 5);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        // TODO Auto-generated method stub
        
        String variable = args.get(1).environment().get("returnValue");
        String start = args.get(2).environment().get("returnValue");
        String end = args.get(3).environment().get("returnValue");
        String increment = args.get(4).environment().get("returnValue");
        IExecutionContext context = args.get(7);
        double startNum = Double.parseDouble(start);
        double endNum = Double.parseDouble(end);
        double incrementNum = Double.parseDouble(increment);
        Evaluator e = new Evaluator();
        for(double i = startNum; i<=endNum; i+=incrementNum){
            context.environment().put(variable, String.valueOf(i));
            try {
                for(int j = 7; j < args.size()-1; j++){
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
