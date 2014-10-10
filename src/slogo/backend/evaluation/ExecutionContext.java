package slogo.backend.evaluation;

import java.util.HashMap;
import java.util.Map;

import slogo.backend.util.ITurtleStatus;

public class ExecutionContext implements IExecutionContext {
    HashMap<String, ITurtleStatus> turtleInfo;
    HashMap<String, String> environmentInfo;
    
    public ExecutionContext(HashMap<String,ITurtleStatus> myTurtleInfo, HashMap<String,String> myEnvironmentInfo){
        turtleInfo = myTurtleInfo;
        environmentInfo = myEnvironmentInfo;
    }
    
    @Override
    public Map<String, ITurtleStatus> turtles () {
        // TODO Auto-generated method stub  111111111111111111111111111111111111111111111111111111                                                                                      
        
        
        return turtleInfo;
    }

    @Override
    public Map<String, String> environment () {
        // TODO Auto-generated method stub
        return environmentInfo;
    }

}
