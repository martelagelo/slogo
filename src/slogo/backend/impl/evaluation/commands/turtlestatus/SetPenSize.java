package slogo.backend.impl.evaluation.commands.turtlestatus;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.Qualities;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

public class SetPenSize extends Operation {

    private static final String COMMAND_NAME = "SetPenSize";
    private static final int MIN_NUM_CONTEXT = 1;
    private static final int MAX_NUM_CONTEXT = 1;
    
    public SetPenSize(){
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
                                            IExecutionContext previous,
                                            ISyntaxNode current) {
        ITurtleStatus status = args.get(0).turtles().get(Constants.DEFAULT_TURTLE_NAME);
        String thick = args.get(0).environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
        int thickness = Integer.parseInt(thick);
        Qualities q = new Qualities(status.turtleQualities().backgroundColor(), status.turtleQualities().toColor(), status.turtleQualities().index(), thickness);
        ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(), status.turtlePosition(), status.turtleDirection(), status.penState(), status.turtleVisibility(), q);
        args.get(0).turtles().put(Constants.DEFAULT_TURTLE_NAME, newStatus);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment(), args.get(0).userDefinedCommands());
    }

}
