// This entire file is part of my masterpiece.
// Michael Ren

package slogo.backend.impl.evaluation;

import java.util.HashMap;
import java.util.Map;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

/**
 * The implementation of a context representing the state of execution of a command
 * at a certain point
 *
 */
public class ExecutionContext implements IExecutionContext {
    private Map<String, ITurtleStatus> turtleInfo;
    private Map<String, String> environmentInfo;
    private Map<String, ISyntaxNode> userDefinedCommands;

    /**
     * Create a deep copy of a previous ExecutionContext
     * @param context
     */
    public ExecutionContext (IExecutionContext context) {
        this.turtleInfo = new HashMap<>(context.turtles());
        this.environmentInfo = new HashMap<>(context.environment());
        this.userDefinedCommands = new HashMap<>(context.userDefinedCommands());
    }

    /**
     * Create a new execution context from component parts
     * 
     * @param myTurtleInfo The map of turtles to their status
     * @param myEnvironmentInfo The environment variables, potentially including the return value
     * @param userDefinedCommands The user-defined commands
     */
    public ExecutionContext (Map<String, ITurtleStatus> myTurtleInfo,
            Map<String, String> myEnvironmentInfo, Map<String, ISyntaxNode> userDefinedCommands) {
        this.turtleInfo = new HashMap<>(myTurtleInfo);
        this.environmentInfo = new HashMap<>(myEnvironmentInfo);
        this.userDefinedCommands = new HashMap<>(userDefinedCommands);
    }

    @Override
    public Map<String, ITurtleStatus> turtles () {
        return turtleInfo;
    }

    @Override
    public Map<String, String> environment () {
        return environmentInfo;
    }

    @Override
    public Map<String, ISyntaxNode> userDefinedCommands () {
        return userDefinedCommands;
    }
}
