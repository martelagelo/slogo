package slogo.backend.impl.evaluation.commands.turtlestatus;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ITurtleStatus;

public class SetStatusTest {

    public IExecutionContext buildContext () {
        Map<String, ITurtleStatus> turtles = new HashMap<String, ITurtleStatus>();
        for (int i = 1; i < 4; i++) {
            turtles.put(String.valueOf(i), new TurtleStatus.Builder().build());
        }
        Map<String, String> environment = new HashMap<String, String>();
        Map<String, ISyntaxNode> function = new HashMap<String, ISyntaxNode>();
        return new ExecutionContext(turtles, environment, function);
    }

    @Test
    public void testSetPenSize () throws MalformedSyntaxException {
        SetPenSize pen = new SetPenSize();
        IExecutionContext context1 = buildContext();
        context1.environment().put(Constants.RETURN_VALUE_ENVIRONMENT, "50");
        List<IExecutionContext> list1 = new ArrayList<IExecutionContext>();
        list1.add(context1);
        IExecutionContext result1 = pen.execute(list1, null, null);

        IExecutionContext context2 = buildContext();
        context2.environment().put(Constants.RETURN_VALUE_ENVIRONMENT, "2");
        List<IExecutionContext> list2 = new ArrayList<IExecutionContext>();
        list2.add(context2);
        IExecutionContext result2 = pen.execute(list2, null, null);

        IExecutionContext context3 = buildContext();
        context3.environment().put(Constants.RETURN_VALUE_ENVIRONMENT, "10");
        context3.turtles().get("2").setActive(false);
        List<IExecutionContext> list3 = new ArrayList<IExecutionContext>();
        list3.add(context3);
        IExecutionContext result3 = pen.execute(list3, null, null);

        assertEquals(50, result1.turtles().get("1").turtleQualities().thickness());
        assertEquals(50, result1.turtles().get("2").turtleQualities().thickness());
        assertEquals(50, result1.turtles().get("3").turtleQualities().thickness());

        assertEquals(2, result2.turtles().get("1").turtleQualities().thickness());
        assertEquals(2, result2.turtles().get("2").turtleQualities().thickness());
        assertEquals(2, result2.turtles().get("3").turtleQualities().thickness());

        assertEquals(10, result3.turtles().get("1").turtleQualities().thickness());
        assertEquals(1, result3.turtles().get("2").turtleQualities().thickness());
        assertEquals(10, result3.turtles().get("3").turtleQualities().thickness());

    }

}
