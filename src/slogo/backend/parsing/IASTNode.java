package slogo.backend.parsing;

import java.util.List;

import slogo.backend.evaluation.IOperation;

/**
 * A node of an abstract syntax tree
 *
 */
public interface IASTNode {
	/**
	 * Get the operation associated with the node
	 * 
	 * @return the operation associated with the node
	 */
	IOperation operation();
	/**
	 * Get the children of this node
	 * 
	 * @return the node's children
	 */
	List<IASTNode> children();
	/**
	 * Get the parent of this node
	 * 
	 * @return the node's parent
	 */
	IASTNode parent();
	/**
	 * Get a name representing the child
	 * 
	 * @return the name
	 */
	String name();
}