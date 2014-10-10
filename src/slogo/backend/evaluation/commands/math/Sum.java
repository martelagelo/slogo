package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Sum implements IOperation {

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        for (IExecutionContext context: args){
            String argument = context.environment().get("returnValue");
            
        }
        return null;
    }

}
