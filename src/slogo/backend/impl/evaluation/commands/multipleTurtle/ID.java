package slogo.backend.impl.evaluation.commands.multipleTurtle;

import java.util.List;

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
        for(ITurtleStatus status:args.get(0).turtles().values()){
            if(status.isActive()){
                id = status.getID();
            }
        }
        args.get(0).environment().put("returnValue", id);
    return new ExecutionContext(args.get(0).turtles(), args.get(0).environment(),args.get(0).userDefinedCommands());
    }

}
