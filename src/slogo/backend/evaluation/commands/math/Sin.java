package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Sin implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        String argument = args.get(0).environment().get("returnValue");
        double degree = Double.parseDouble(argument);
        double radian = Math.toRadians(degree);
        double sine = Math.sin(radian);
        String returnArgument = String.valueOf(sine);
        //update or create a new ExecutionContext and return it
    }

}
