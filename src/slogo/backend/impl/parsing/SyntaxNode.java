package slogo.backend.impl.parsing;

import java.util.List;

import slogo.backend.evaluation.IOperation;
import slogo.backend.parsing.ISyntaxNode;

public class SyntaxNode implements ISyntaxNode{

	private IOperation operation;
	private List<ISyntaxNode> children;
	private String type;

	public SyntaxNode(String type, IOperation operation, List<ISyntaxNode> children){
		this.type = type;
		this.operation = operation;
		//this.parent = parent;
		this.children = children;
	}
	@Override
	public IOperation operation() {
		return operation;
	}

	@Override
	public List<ISyntaxNode> children() {
		return children;
	}
	
	@Override
	public void setChildren(List<ISyntaxNode> children){
		this.children = children;
	}

	@Override
	public String type() {
		return type;
	}
	
}
