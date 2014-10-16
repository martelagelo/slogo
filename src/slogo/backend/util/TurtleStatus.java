package slogo.backend.util;

import java.util.List;

public class TurtleStatus implements ITurtleStatus {
private List<ILine> linesequence;
private ICoordinates turtlePosition;
private IDirection turtleDirection;
private PenState penState;
private Visibility turtleVisibility;


public TurtleStatus(List<ILine> l, ICoordinates p, IDirection d, PenState s, Visibility v){
    linesequence = l;
    turtlePosition = p;
    turtleDirection = d;
    penState = s;
    turtleVisibility = v;
}
    @Override
    public List<ILine> lineSequence () {
        // TODO Auto-generated method stub
        return linesequence;
    }

    @Override
    public ICoordinates turtlePosition () {
        // TODO Auto-generated method stub
        return turtlePosition;
    }

    @Override
    public IDirection turtleDirection () {
        // TODO Auto-generated method stub
        return turtleDirection;
    }

    @Override
    public PenState penState () {
        // TODO Auto-generated method stub
        return penState;
    }

    @Override
    public Visibility turtleVisibility () {
        // TODO Auto-generated method stub
        return turtleVisibility;
    }

}
