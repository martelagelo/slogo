package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class IfElse extends ConditionalOperation{
    private static final String COMMAND_NAME = "IfElse";
    private static final int MIN_NUM_CONTEXT = 7;
    private static final int MAX_NUM_CONTEXT = Constants.INFINITE_ARGUMENTS;
	public IfElse () {
		super(COMMAND_NAME, MIN_NUM_CONTEXT, MAX_NUM_CONTEXT);
	}

	@Override
	protected IExecutionContext executeRaw(List<IExecutionContext> args,
			IExecutionContext previous, ISyntaxNode current)
			throws MalformedSyntaxException {
		return kipling(args, previous, current, 0, 2);
	}

}
