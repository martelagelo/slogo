package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ILine;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;
import slogo.backend.util.Visibility;

public class HideTurtle extends Operation{

    public HideTurtle () {
        super("HideTurtle", 1, 1);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
        Map <String,ITurtleStatus> turtles = args.get(0).turtles();
        for(String name:turtles.keySet()){

            if(turtles.get(name).isActive()){
                ITurtleStatus status = turtles.get(name);

                ICoordinates cor = status.turtlePosition();
                IDirection dir = status.turtleDirection();
                List<ILine> list = status.lineSequence();
                PenState pen = status.penState();



                ITurtleStatus newStatus = new TurtleStatus(list,cor,dir,pen,Visibility.INVISIBLE, status.turtleQualities());
                turtles.put(name, newStatus);
            }
        }
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, "0");
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
