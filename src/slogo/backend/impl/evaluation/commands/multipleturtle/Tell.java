package slogo.backend.impl.evaluation.commands.multipleturtle;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.scene.paint.Color;
import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

public class Tell extends Operation {
    private static final String COMMAND_NAME = "Tell";
    private static final int MIN_NUM_CONTEXT = 3;
    private static final int MAX_NUM_CONTEXT = Constants.INFINITE_ARGUMENTS;
    
    public Tell () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        Set<String> activeTurtle = new HashSet<String>();
        IExecutionContext context = previous;
        Map<String, ITurtleStatus> map = context.turtles();
        Color c = Color.WHITE;
        for(int i = 1; i < args.size()-1; i++){
            activeTurtle.add(args.get(i).environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
        }
        for(String s: previous.turtles().keySet()){
            if(activeTurtle.contains(s)){
                map.get(s).setActive(true);
                activeTurtle.remove(s);
                c = map.get(s).turtleQualities().backgroundColor();
            }
            else{
                map.get(s).setActive(false);
            }
        }
        for(String str: activeTurtle){
            TurtleStatus TS = new TurtleStatus.Builder().build();
            TS.turtleQualities().setBackground(c);
            map.put(str, TS);
        }
        return new ExecutionContext(context.turtles(),context.environment(),context.userDefinedCommands());
    }

}
