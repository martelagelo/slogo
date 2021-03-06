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

public class SetBackground extends Operation {
    private static final String COMMAND_NAME = "SetBackground";
    private static final int MIN_NUM_CONTEXT = 3;
    private static final int MAX_NUM_CONTEXT = 3;

    public SetBackground () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        Map<String, ITurtleStatus> turtles = args.get(0).turtles();

        for (String name : turtles.keySet()) {
            ITurtleStatus status = args.get(0).turtles().get(name);
            int red = (int) Math.round(Double.parseDouble(args.get(0).environment()
                    .get(Constants.RETURN_VALUE_ENVIRONMENT)));
            int green = (int) Math.round(Double.parseDouble(args.get(1).environment()
                    .get(Constants.RETURN_VALUE_ENVIRONMENT)));
            int blue = (int) Math.round(Double.parseDouble(args.get(2).environment()
                    .get(Constants.RETURN_VALUE_ENVIRONMENT)));
            Color c;
            try {
                c = Color.rgb(red, green, blue);
            } catch (Exception e) {
                c = status.turtleQualities().backgroundColor();
            }
            Qualities q = new Qualities(c, status.turtleQualities().toColor(), status
                    .turtleQualities().index(), status.turtleQualities().thickness());
            ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),
                    status.turtlePosition(), status.turtleDirection(), status.penState(),
                    status.turtleVisibility(), q);
            args.get(0).turtles().put(name, newStatus);
        }
        return new ExecutionContext(args.get(0).turtles(), args.get(0).environment(), args.get(0)
                .userDefinedCommands());
    }

}
