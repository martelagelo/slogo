package slogo.backend.impl.evaluation;

import java.util.Map;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.util.ITurtleStatus;

public class ExecutionContext implements IExecutionContext {
    private final Map<String, ITurtleStatus> turtleInfo;
    private final Map<String, String> environmentInfo;

    public ExecutionContext(Map<String,ITurtleStatus> myTurtleInfo, Map<String,String> myEnvironmentInfo){
        turtleInfo = myTurtleInfo;
        environmentInfo = myEnvironmentInfo;
    }
    
    @Override
    public Map<String, ITurtleStatus> turtles () {
        return turtleInfo;
    }

    @Override
    public Map<String, String> environment () {
        return environmentInfo;
    }
    //public ExecutionContext copy(){
      // Deep copy? 
    //}
}
