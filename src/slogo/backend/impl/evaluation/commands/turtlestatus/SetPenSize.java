//This entire file is part of my masterpiece
//Eric Chen

package slogo.backend.impl.evaluation.commands.turtlestatus;

import slogo.backend.impl.util.Qualities;
import slogo.backend.util.ITurtleStatus;

public class SetPenSize extends SetStatus {

    private static final String COMMAND_NAME = "SetPenSize";
    private static final int MIN_NUM_CONTEXT = 1;
    private static final int MAX_NUM_CONTEXT = 1;

    public SetPenSize () {
        super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);

    }

    @Override
    protected Qualities executeStatus (int argInt, ITurtleStatus status) {
        return new Qualities(status.turtleQualities().backgroundColor(), status.turtleQualities()
                .toColor(), status.turtleQualities().index(), argInt);
    }

}
