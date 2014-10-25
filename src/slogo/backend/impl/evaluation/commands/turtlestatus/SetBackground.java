package slogo.backend.impl.evaluation.commands.turtlestatus;

import java.util.List;
import javafx.scene.paint.Color;
import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.Qualities;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

public class SetBackground extends Operation {

    
    public SetBackground(){
        super("SetBackground", 3, 3);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
                                            IExecutionContext previous,
                                            ISyntaxNode current) {
        ITurtleStatus status = args.get(0).turtles().get(Constants.DEFAULT_TURTLE_NAME);
        int red = (int) Math.round(Double.parseDouble(args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT)));
        int green = (int) Math.round(Double.parseDouble(args.get(1).environment().get(Constants.RETURN_VALUE_ENVIRONMENT)));
        int blue = (int) Math.round(Double.parseDouble(args.get(2).environment().get(Constants.RETURN_VALUE_ENVIRONMENT)));
        Color c;
        try{
            c = Color.rgb(red, green, blue);
        }
        catch (Exception e){
            c = status.turtleQualities().backgroundColor();
        }
        Qualities q = new Qualities(c, status.turtleQualities().toColor(), status.turtleQualities().index(), status.turtleQualities().thickness());
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(), status.turtlePosition(), status.turtleDirection(), status.penState(), status.turtleVisibility(), q);
        args.get(0).turtles().put(Constants.DEFAULT_TURTLE_NAME, newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }
    
}
