package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ILine;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;
import slogo.backend.util.Visibility;

public class HideTurtle extends Operation{

    public HideTurtle () {
        super("HideTurtle", 0, 0);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args) {
        ITurtleStatus status = args.get(0).turtles().get(Constants.DEFAULT_TURTLE_NAME);

        ICoordinates cor = status.turtlePosition();
        IDirection dir = status.turtleDirection();
        List<ILine> list = status.lineSequence();
        PenState pen = status.penState();
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, "0");


        ITurtleStatus newStatus = new TurtleStatus(list,cor,dir,pen,Visibility.INVISIBLE);
        args.get(0).turtles().put(Constants.DEFAULT_TURTLE_NAME, newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
