package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;

public class IsPenDown extends Operation{

    public IsPenDown () {
        super("IsPenDown", 1, 1);
    }

    @Override
	protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
        Map <String,ITurtleStatus> turtles = args.get(0).turtles();
        String lastActive = null;
        for(String name : turtles.keySet()){
            if(turtles.get(name).isActive()){
                lastActive = name;
            }
        }
        if (lastActive==null)
        {
            //throw no active turtle exception?}
        }
        ITurtleStatus status = turtles.get(lastActive);
      
        PenState pen = status.penState();
        String returnString = pen.equals(PenState.DOWN)? "1":"0";
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, returnString);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
