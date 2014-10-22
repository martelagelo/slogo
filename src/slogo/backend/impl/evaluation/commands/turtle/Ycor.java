package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.util.ITurtleStatus;

public class Ycor extends Operation{

    public Ycor () {
        super("Ycor", 0, 0);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args) {
        ITurtleStatus status = args.get(0).turtles().get(Constants.DEFAULT_TURTLE_NAME);
        double y = status.turtlePosition().getY().doubleValue();
        String returnString = String.valueOf(y);
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, returnString);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
