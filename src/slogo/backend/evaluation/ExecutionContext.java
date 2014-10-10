package slogo.backend.evaluation;

import java.util.Map;

import slogo.backend.util.ITurtleStatus;

public class ExecutionContext implements IExecutionContext {
    Map<String, ITurtleStatus> turtleInfo;
    Map<String, String> environmentInfo;
    
    public ExecutionContext(Map<String,ITurtleStatus> myTurtleInfo, Map<String,String> myEnvironmentInfo){
        turtleInfo = myTurtleInfo;
        environmentInfo = myEnvironmentInfo;
    }
    
    @Override
    public Map<String, ITurtleStatus> turtles () {
        // TODO Auto-generated method stub                                                                                      
        
        
        return turtleInfo;
    }

    @Override
    public Map<String, String> environment () {
        // TODO Auto-generated method stub
        return environmentInfo;
    }
    //public ExecutionContext copy(){
      // Deep copy? 
    //}

}
