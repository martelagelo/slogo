package slogo.backend.impl.evaluation.commands.multipleTurtle;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

public class Ask extends Operation{
    private static final String COMMAND_NAME = "Ask";
    private static final int MIN_NUM_CONTEXT = 6;
    private static final int MAX_NUM_CONTEXT = Constants.INFINITE_ARGUMENTS;
    public Ask () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
        
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException {
        
        List<ISyntaxNode> children = current.children();
        int secondListOpen = 3;

        for(int i = 3; i<children.size()-2; i++){
            if(children.get(i).type().equals(Constants.OPENING_LIST_LABEL)){
                secondListOpen = i; 
            }
        }
        
        Set<String> tempActive = new HashSet<String>();
        Set<String> previousActive = new HashSet<String>();
        IExecutionContext context = previous;
        Map<String, ITurtleStatus> map = context.turtles();
        for(int i = 1; i < secondListOpen-1; i++){
            tempActive.add(args.get(i).environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
        }
        
        for(String s: context.turtles().keySet()){
            if(map.get(s).isActive()){
                previousActive.add(s);
            }
            if(tempActive.contains(s)){
                map.get(s).setActive(true);
               
            }
            else{
                map.get(s).setActive(false);
            }
        }
        Evaluator e = new Evaluator();
        for(int j = secondListOpen + 1; j<args.size()-1; j++){
            context = e.evaluate(children.get(j), context);
        }
        for(String k:context.turtles().keySet()){
            if(previousActive.contains(k)){
                context.turtles().get(k).setActive(true);
            }
            else{
                context.turtles().get(k).setActive(false);
            }
        }
        return new ExecutionContext(context.turtles(),context.environment(),context.userDefinedCommands());
    }

}
