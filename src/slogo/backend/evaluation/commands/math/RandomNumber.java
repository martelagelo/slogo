package slogo.backend.evaluation.commands.math;

import java.util.List;
import java.util.Random;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class RandomNumber implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        String argument = args.get(0).environment().get("returnValue");
        double max = Double.parseDouble(argument);
        Random random = new Random();
        double r = max*random.nextDouble();
        String returnArgument = String.valueOf(r);
        //update or create a new ExecutionContext and return it
    }

}
