package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Sum implements IOperation {

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        double sum = 0;
        for (IExecutionContext context: args){
            String argument = context.environment().get("returnValue");
            double doubleArgument = Double.parseDouble(argument);
            sum+=doubleArgument;
        }
        String returnArgument = String.valueOf(sum);
        //update or create a new ExecutionContext and return it
    }

}
