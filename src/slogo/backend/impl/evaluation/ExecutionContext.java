package slogo.backend.impl.evaluation;

import java.util.HashMap;
import java.util.Map;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

public class ExecutionContext implements IExecutionContext {
	private Map<String, ITurtleStatus> turtleInfo;
	private Map<String, String> environmentInfo;
	private Map<String, ISyntaxNode> userDefinedCommands;

	public ExecutionContext(IExecutionContext context) {
		this.turtleInfo = new HashMap<>(context.turtles());
		this.environmentInfo = new HashMap<>(context.environment());
		this.userDefinedCommands = new HashMap<>(context.userDefinedCommands());
	}
	public ExecutionContext(Map<String,ITurtleStatus> myTurtleInfo,
			Map<String,String> myEnvironmentInfo,
			Map<String, ISyntaxNode> userDefinedCommands){
		this.turtleInfo = myTurtleInfo;
		this.environmentInfo = myEnvironmentInfo;
		this.userDefinedCommands = userDefinedCommands;
	}

	@Override
	public Map<String, ITurtleStatus> turtles () {
		return turtleInfo;
	}

	@Override
	public Map<String, String> environment () {
		return environmentInfo;
	}

	@Override
	public Map<String, ISyntaxNode> userDefinedCommands() {
		return userDefinedCommands;
	}
}
