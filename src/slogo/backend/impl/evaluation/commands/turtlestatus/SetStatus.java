//This entire file is part of my masterpiece
//Eric Chen

package slogo.backend.impl.evaluation.commands.turtlestatus;

import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.impl.util.Qualities;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

public abstract class SetStatus extends Operation {

    public SetStatus (String type, int argMin, int argMax) {
        super(type, argMin, argMax);

    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException {
        Map<String, ITurtleStatus> turtles = args.get(0).turtles();

        for (String name : turtles.keySet()) {
            ITurtleStatus status = turtles.get(name);
            if (status.isActive()) {
                String argString = args.get(0).environment()
                        .get(Constants.RETURN_VALUE_ENVIRONMENT);
                int argInt = Integer.parseInt(argString);
                Qualities q = executeStatus(argInt, status);
                ITurtleStatus newStatus = new TurtleStatus(status.lineSequence(),
                        status.turtlePosition(), status.turtleDirection(), status.penState(),
                        status.turtleVisibility(), q);
                turtles.put(name, newStatus);
            }
        }
        return new ExecutionContext(args.get(0).turtles(), args.get(0).environment(), args.get(0)
                .userDefinedCommands());
    }

    protected abstract Qualities executeStatus (int argInt, ITurtleStatus status);

}
