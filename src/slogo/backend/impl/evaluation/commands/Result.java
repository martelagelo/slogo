package slogo.backend.impl.evaluation.commands;

import java.util.HashMap;
import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.parsing.ISyntaxNode;

public class Result implements IOperation {

	@Override
	public IExecutionContext execute(List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) {
		return new ExecutionContext(
				new HashMap<>(args.get(0).turtles()),
				new HashMap<>(args.get(0).environment()));
	}

	@Override
	public String type() {
		return "result";
	}

}
