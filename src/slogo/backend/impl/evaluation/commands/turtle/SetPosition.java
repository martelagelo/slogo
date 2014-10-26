package slogo.backend.impl.evaluation.commands.turtle;

import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.Coordinates;
import slogo.backend.impl.util.Line;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ILine;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;
import slogo.backend.util.Visibility;

public class SetPosition extends Operation{
    private static final String COMMAND_NAME = "SetXY";
    private static final int MIN_NUM_CONTEXT = 2;
    private static final int MAX_NUM_CONTEXT = 2;
    public SetPosition () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
	protected IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
        String newX = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        String newY = args.get(1).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        double newXValue = Double.parseDouble(newX);
        double newYValue = Double.parseDouble(newY);
        ICoordinates newPos = new Coordinates(newXValue, newYValue);
        
        Map <String,ITurtleStatus> turtles = args.get(0).turtles();

        for(String name: turtles.keySet()){

            ITurtleStatus status = turtles.get(name);
            if(status.isActive()){
      
        ICoordinates pos = status.turtlePosition();
        IDirection dir = status.turtleDirection();
        PenState pen = status.penState();
        Visibility vis = status.turtleVisibility();
        
        if(pen.equals(PenState.DOWN)){
            ILine newLine = new Line(pos, newPos, vis);
            status.lineSequence().add(newLine);
        }
        String distance = String.valueOf(newPos.getDistance(pos).doubleValue());
        args.get(0).environment().put(Constants.RETURN_VALUE_ENVIRONMENT, distance);
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),newPos,dir,pen,status.turtleVisibility(), status.turtleQualities());
        turtles.put(name, newStatus);
            }
        }
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
