package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.ExecutionContext;
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
        String expression = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        List<ISyntaxNode> children = current.children();
        int secondListOpen = 3;
        
        //Need error checking?
        for(int i = 4; i<children.size()-1; i++){
            if(children.get(i).type().equals(Constants.OPENING_LIST_LABEL)){
                secondListOpen = i; 
            }
        }
        Evaluator e = new Evaluator();
        IExecutionContext context = previous;
        if(expression.equals("0")){
            for(int j = 2; j < secondListOpen-1; j++){
                try {
                    context = e.evaluate(current.children().get(j), context);
                } catch (MalformedSyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        
        }
        else{
            for(int k = secondListOpen+1; k < args.size()-1; k++){
                try {
                    context = e.evaluate(current.children().get(k), context);
                } catch (MalformedSyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    return new ExecutionContext(context.turtles(),context.environment(),context.userDefinedCommands());
    }

}
