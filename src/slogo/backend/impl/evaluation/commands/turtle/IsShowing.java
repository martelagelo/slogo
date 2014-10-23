package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.Visibility;

public class IsShowing extends Operation{

    public IsShowing () {
        super("IsShowing", 1, 1);
    }

    @Override
	protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
        ITurtleStatus status = args.get(0).turtles().get(Constants.DEFAULT_TURTLE_NAME);
        Visibility vis = status.turtleVisibility();
        String returnString = vis.equals(Visibility.VISIBLE)? "1":"0";
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, returnString);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
