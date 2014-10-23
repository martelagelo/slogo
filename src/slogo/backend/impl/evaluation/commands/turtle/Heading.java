package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.IDirection;
import slogo.backend.util.ITurtleStatus;

public class Heading extends Operation{

    public Heading () {
        super("Heading", 1, 1);
    }

    @Override
	protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
        ITurtleStatus status = args.get(0).turtles().get(Constants.DEFAULT_TURTLE_NAME);
        
        
        IDirection dir = status.turtleDirection();
        double dirDegree = dir.toDegrees();
        String dirString = String.valueOf(dirDegree);
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, dirString);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
