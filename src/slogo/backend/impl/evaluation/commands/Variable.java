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

public class Variable implements IOperation{
    private String name;
    public Variable (String name){
        this.name = name;
    }
    @Override
    public IExecutionContext execute (List<IExecutionContext> args, IExecutionContext previous,
            ISyntaxNode current) throws MalformedSyntaxException {
        // TODO Auto-generated method stub
        Map<String, String> newEnvironment = new HashMap<>(args.get(0).environment());
        newEnvironment.put(Constants.RETURN_VALUE_ENVIRONMENT, args.get(0).environment().get(name));
        return new ExecutionContext(new HashMap<>(args.get(0).turtles()), newEnvironment);
    }

    @Override
    public String type () {
        // TODO Auto-generated method stub
        return "Variable";
    }

}
