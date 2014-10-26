package slogo.backend.impl.evaluation.commands.turtlestatus;

import java.util.List;
import java.util.Map;
import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.Qualities;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

public class SetPenSize extends Operation {

    public SetPenSize () {
        super("SetPenSize", 1, 1);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
                                            IExecutionContext previous,
                                            ISyntaxNode current) {
        Map <String,ITurtleStatus> turtles = args.get(0).turtles();

        for(String name: turtles.keySet()){
            ITurtleStatus status = turtles.get(name);
            if(status.isActive()){
            String thick = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
            int thickness = Integer.parseInt(thick);
            Qualities q = new Qualities(status.turtleQualities().backgroundColor(), status.turtleQualities().toColor(), status.turtleQualities().index(), thickness);
            ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(), status.turtlePosition(), status.turtleDirection(), status.penState(), status.turtleVisibility(), q);
            turtles.put(name, newStatus);
            }
        }
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
