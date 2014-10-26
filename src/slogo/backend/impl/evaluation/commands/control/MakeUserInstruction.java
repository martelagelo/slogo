package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class MakeUserInstruction extends Operation {

	public MakeUserInstruction() {
		super("To", 7, -1);
	}

	@Override
	protected IExecutionContext executeRaw(List<IExecutionContext> args,
			IExecutionContext previous, ISyntaxNode current)
			throws MalformedSyntaxException {
		return new ExecutionContext(previous);
	}

}
