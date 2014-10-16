package slogo.backend.evaluation.commands.turtle;

import java.util.List;

import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ILine;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;
import slogo.backend.util.TurtleStatus;
import slogo.backend.util.Visibility;

public class HideTurtle implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        ITurtleStatus status = args.get(0).turtles().get("1");

        ICoordinates cor = status.turtlePosition();
        IDirection dir = status.turtleDirection();
        List<ILine> list = status.lineSequence();
        PenState pen = status.penState();
        args.get(0).environment().put("returnValue", "0");


        ITurtleStatus newStatus = new TurtleStatus(list,cor,dir,pen,Visibility.INVISIBLE);
        args.get(0).turtles().put("1", newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
