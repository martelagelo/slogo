package slogo.backend.evaluation.commands;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.MalformedSyntaxException;

public abstract class Operation implements IOperation{

	private String type;
	private int argMin;
	private int argMax;
	public Operation(String type, int argMin, int argMax){
		this.type = type;
		this.argMin = argMin;
		this.argMax = argMax;
	}
	protected void validateArguments(List<IExecutionContext> args) throws MalformedSyntaxException {
		if (args.size() < argMin || (args.size() > argMax && argMax != -1)) {
			throw new MalformedSyntaxException();
		}
	}
	@Override
	public IExecutionContext execute(List<IExecutionContext> args) throws MalformedSyntaxException{
		validateArguments(args);
		return executeRaw(args);
	}

	protected abstract IExecutionContext executeRaw(List<IExecutionContext> args);

	@Override
	public String type() {
		return null;
	}

}
