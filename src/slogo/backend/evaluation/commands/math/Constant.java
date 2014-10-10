package slogo.backend.evaluation.commands.math;

import java.util.List;

import com.sun.javafx.collections.MappingChange.Map;

import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;

public class Constant implements IOperation {
private String value;
    public Constant(String s){
    value = s;
}
    @Override
    public IExecutionContext execute (List<IExecutionContext> args) {
        // TODO Auto-generated method stub
        args.get(0).environment().put("returnValue", value);
        return new ExecutionContext(args.get(0).turtles(),args.get(0).environment());
    }

}
