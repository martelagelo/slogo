package slogo.backend.impl.evaluation.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.parsing.ISyntaxNode;

public class Variable extends Operation{
    private String name;
    public Variable (String name){
    	super("Variable", 1, 1);
        this.name = name;
    }
    @Override
    public IExecutionContext executeRaw (List<IExecutionContext> args, IExecutionContext previous,
            ISyntaxNode current) throws MalformedSyntaxException {
    	IExecutionContext childContext = args.get(0);
        Map<String, String> newEnvironment = new HashMap<>(childContext.environment());
        if (childContext.environment().containsKey(name)){
            newEnvironment.put(Constants.RETURN_VALUE_ENVIRONMENT, childContext.environment().get(name));
        }
        else {
        	newEnvironment.put(Constants.RETURN_VALUE_ENVIRONMENT, Constants.UNDEFINED_RETURN_VALUE);
        }
        newEnvironment.put(Constants.RETURNED_VARIABLE_NAME, name);
        return new ExecutionContext(args.get(0).turtles(), newEnvironment, args.get(0).userDefinedCommands());
    }

}
