package slogo.backend.impl.evaluation.commands;

import java.util.ArrayList;
import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.parsing.IGrammarRule;
import slogo.backend.parsing.ISyntaxNode;

public abstract class Operation implements IOperation {

	private String type;
	private int argMin;
	private int argMax;
	public Operation(String type, int argMin, int argMax){
		this.type = type;
		this.argMin = argMin;
		this.argMax = argMax;
	}
	protected void validateArguments(List<IExecutionContext> args) throws MalformedSyntaxException {
		if (args.size() < argMin){
			throw new MalformedSyntaxException("Arg size is below " + argMin);
		}
		if (args.size() > argMax && argMax != -1){
		    throw new MalformedSyntaxException("Arg size is above " + argMax);
		}
	}
	@Override
	public IExecutionContext execute(List<IExecutionContext> args, IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException{
		validateArguments(args);
		return executeRaw(args, previous, current);
	}

	protected abstract IExecutionContext executeRaw(List<IExecutionContext> args,IExecutionContext previous, ISyntaxNode current);

	protected List<Number> generateNumericArguments(List<IExecutionContext> args) {
		List<Number> argsNum = new ArrayList<>();
		for (IExecutionContext context: args) {
			argsNum.add(new Double(context.environment().get(Constants.RETURN_VALUE_ENVIRONMENT)));
		}
		return argsNum;
	}
	protected IExecutionContext mergeContexts(List<IExecutionContext> contexts){
		return new ExecutionContext(contexts.get(0));
	}

	@Override
	public String type() {
		return type;
	}

}
