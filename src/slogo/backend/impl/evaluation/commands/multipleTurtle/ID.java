package slogo.backend.impl.evaluation.commands.multipleTurtle;

import java.util.List;
import java.util.Map;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

public class ID extends Operation {

    public ID (String type, int argMin, int argMax) {
        super(type, 1, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) {
        // TODO Auto-generated method stub
        String id = "";
        Map<String,ITurtleStatus> map = args.get(0).turtles();
        for(String s:map.keySet()){
            if(map.get(s).isActive()){
                id = s;
            }
        }
        args.get(0).environment().put("returnValue", id);
    return new ExecutionContext(args.get(0).turtles(), args.get(0).environment(),args.get(0).userDefinedCommands());
    }

}
