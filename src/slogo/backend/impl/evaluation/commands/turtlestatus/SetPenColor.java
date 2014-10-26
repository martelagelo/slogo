package slogo.backend.impl.evaluation.commands.turtlestatus;

import java.util.List;
import java.util.Map;
import javafx.scene.paint.Color;
import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.Qualities;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

public class SetPenColor extends Operation {

    public SetPenColor(){
        super("SetPenColor", 3, 3);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
                                            IExecutionContext previous,
                                            ISyntaxNode current) {
        Map <String,ITurtleStatus> turtles = args.get(0).turtles();

        for(String name: turtles.keySet()){
            ITurtleStatus status = turtles.get(name);
            if(status.isActive()){
            int red = (int) Math.round(Double.parseDouble(args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT)));
            int green = (int) Math.round(Double.parseDouble(args.get(1).environment().get(Constants.RETURN_VALUE_ENVIRONMENT)));
            int blue = (int) Math.round(Double.parseDouble(args.get(2).environment().get(Constants.RETURN_VALUE_ENVIRONMENT)));
            Color c;
            try{
                c = Color.rgb(red, green, blue);
            }
            catch (Exception e){
                c = status.turtleQualities().toColor();
            }
            Qualities q = new Qualities(status.turtleQualities().backgroundColor(), c, status.turtleQualities().index(), status.turtleQualities().thickness());
            ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(), status.turtlePosition(), status.turtleDirection(), status.penState(), status.turtleVisibility(), q);
            turtles.put(name, newStatus);
            }
        }
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }
}
