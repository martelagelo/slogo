package slogo.backend.impl.evaluation.commands.booleans;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.MathOperation;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public class Not extends MathOperation{

    public Not() {
		super("Not", 1, 1);
	}

	@Override
	protected Number executeMath(List<Number> args) {
		return args.get(0).intValue() == 0 ? 1 : 0;
	}

}
