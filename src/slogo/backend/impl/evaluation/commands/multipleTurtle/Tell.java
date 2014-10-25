package slogo.backend.impl.evaluation.commands.multipleTurtle;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

public class Tell extends Operation {

    public Tell (String type, int argMin, int argMax) {
        super(type, 3, Integer.MAX_VALUE);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        // TODO Auto-generated method stub
        Set<String> activeTurtle = new HashSet<String>();
        IExecutionContext context = previous;
        Map<String, ITurtleStatus> map = context.turtles();
        for(int i = 1; i < args.size()-1; i++){
            activeTurtle.add(args.get(i).environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
        }
        for(String s: previous.turtles().keySet()){
            if(activeTurtle.contains(s)){
                map.get(s).setActive(true);
                activeTurtle.remove(s);
            }
            else{
                map.get(s).setActive(false);
            }
        }
        for(String str: activeTurtle){
            map.put(str, new TurtleStatus.Builder().build());
        }
        return new ExecutionContext(context.turtles(),context.environment(),context.userDefinedCommands());
    }

}
