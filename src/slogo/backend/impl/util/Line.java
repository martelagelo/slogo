package slogo.backend.impl.util;

import slogo.backend.util.ICoordinates;
import slogo.backend.util.ILine;
import slogo.backend.util.Visibility;

public class Line implements ILine {

    private ICoordinates start;
    private ICoordinates end;
    private Visibility visibility;
    public Line(ICoordinates s, ICoordinates e, Visibility v){
        start = s;
        end = e;
        visibility = v;
    }
    @Override
    public ICoordinates start () {
        // TODO Auto-generated method stub
        return start;
    }

    @Override
    public ICoordinates end () {
        // TODO Auto-generated method stub
        return end;
    }

    @Override
    public Visibility visible () {
        // TODO Auto-generated method stub
        return visibility;
    }

}
