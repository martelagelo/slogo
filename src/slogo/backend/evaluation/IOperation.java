// This entire file is part of my masterpiece.
// Michael Ren

package slogo.backend.evaluation;

import java.util.List;

import slogo.backend.parsing.ISyntaxNode;

/**
 * Any operation or literal for the Logo language
 *
 */
public interface IOperation {
    /**
     * Perform the function on a state/context
     * 
     * @param args
     *            any arguments needed for the operation
     * @return the new state of the execution
     */
    public IExecutionContext execute (List<IExecutionContext> args, IExecutionContext previous,
            ISyntaxNode current) throws MalformedSyntaxException;

    /**
     * Identifies the type of the operation
     * 
     * @return the string that represents the type of the operation
     */
    public String type ();
}
