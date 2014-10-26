package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.Direction;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;

public class SetTowards extends Operation{
    private static final String COMMAND_NAME = "Towards";
    private static final int MIN_NUM_CONTEXT = 2;
    private static final int MAX_NUM_CONTEXT = 2;
    public SetTowards () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
	protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
        String xString = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        double faceXValue = Double.parseDouble(xString);
        String yString = args.get(1).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        double faceYValue = Double.parseDouble(yString);
        
        Map <String,ITurtleStatus> turtles = args.get(0).turtles();

        for(String name: turtles.keySet()){

            ITurtleStatus status = turtles.get(name);
            if(status.isActive()){
        
        ICoordinates pos = status.turtlePosition();
        double xValue = pos.getX().doubleValue();
        double yValue = pos.getY().doubleValue();
        double newRad = Math.atan((faceYValue-yValue)/(faceXValue-xValue));
        double newDegree = Math.toDegrees(newRad);
        
        PenState pen = status.penState();
        IDirection newDir = new Direction(newDegree);
        IDirection dir = status.turtleDirection();
        double degreeTurned = Math.abs(newDir.toDegrees()-dir.toDegrees());
        String degreeString = String.valueOf(degreeTurned);
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, degreeString);
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),pos,newDir,pen,status.turtleVisibility(), status.turtleQualities());
        turtles.put(name, newStatus);
            }
        }
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
