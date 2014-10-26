package slogo.backend.evaluation;

import java.util.Map;

import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

/**
 * The read-only state of a Logo simulation at a particular time
 *
 */
public interface IExecutionContext {
    /**
     * Get the turtles on the map
     * 
     * @return a map of turtle names to turtle status
     */
    public Map<String, ITurtleStatus> turtles ();

    /**
     * A general place to put configuration variables, akin to a shell's
     * environment. This can be used to store a return value, for example.
     * 
     * @return a key-value Map that represents the list of configuration
     *         variables
     */
    public Map<String, String> environment ();

    /**
     * A place for commands that the user defines; the commands are stored in
     * the tree representation created in parsing
     * 
     * @return The list of user-defined commands
     */
    public Map<String, ISyntaxNode> userDefinedCommands ();
}
