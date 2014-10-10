package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Difference implements IOperation{

    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        double difference = Double.parseDouble(args.get(0).environment().get("returnValue"));
        for (int i=1; i<args.size(); i++){
            String argument = args.get(i).environment().get("returnValue");
            double subtrahend = Double.parseDouble(argument);
            difference-=subtrahend;
        }
        String returnArgument = String.valueOf(difference);
        //update or create a new ExecutionContext and return it
    }

}
