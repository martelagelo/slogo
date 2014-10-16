package slogo.backend.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;










import slogo.backend.evaluation.ExecutionContext;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.IOperationFactory;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.evaluation.commands.booleans.And;
import slogo.backend.evaluation.commands.booleans.Less;
import slogo.backend.evaluation.commands.booleans.Not;
import slogo.backend.evaluation.commands.booleans.Or;
import slogo.backend.evaluation.commands.math.Sum;
import slogo.backend.impl.evaluation.OperationFactory;
import slogo.backend.util.Coordinates;
import slogo.backend.util.Direction;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ILine;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;
import slogo.backend.util.TurtleStatus;
import slogo.backend.util.Visibility;

public class OperationTest {
	@Test
	public void testOperationFactory(){
		IOperationFactory factory = new OperationFactory();
		try {
			IOperation sum = factory.makeElement("Sum");
			assertEquals("Sum", sum.type());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			factory.makeElement("Tasdf");
			fail("exception was not thrown");
		} catch (Exception e) {
			
		}
	}
	public IExecutionContext buildContext(){
	    List<ILine> list = new ArrayList<ILine>();
	    ICoordinates cor = new Coordinates(0,0);
	    IDirection dir = new Direction(0);
	    PenState pen = PenState.DOWN;
	    Visibility vis = Visibility.VISIBLE;
	    ITurtleStatus status = new TurtleStatus(list,cor,dir,pen,vis);
	    Map <String, ITurtleStatus> turtles = new HashMap<String, ITurtleStatus>();
	    turtles.put("1", status);
	    Map<String, String> environment = new HashMap<String, String>();
	    return new ExecutionContext(turtles, environment);
	}
	public String binary (IOperation op, String s1, String s2){
	    IExecutionContext con1 = buildContext();
        con1.environment().put("returnValue", s1);
        IExecutionContext con2 = buildContext();
        con2.environment().put("returnValue", s2);
        List<IExecutionContext> list = new ArrayList<IExecutionContext>();
        list.add(con1);list.add(con2);
        IExecutionContext result = null;
        try {
            result = op.execute(list);
        } catch (MalformedSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result.environment().get("returnValue");
	}
	@Test
	public void testAnd(){
	    And and = new And();
	    String s1 = binary (and, "20", "41");
	    String s2 = binary (and, "1", "-3");
	    String s3 = binary (and, "0", "10");
	    String s4 = binary (and, "-3", "0");
	    String s5 = binary (and, "0", "0");
	    assertEquals(s1, "1");
	    assertEquals(s2, "1");
	    assertEquals(s3, "0");
	    assertEquals(s4, "0");
	    assertEquals(s5, "0");
	    
	}
	@Test
	public void testOr(){
	    Or or = new Or();
        String s1 = binary (or, "20", "41");
        String s2 = binary (or, "1", "-3");
        String s3 = binary (or, "0", "10");
        String s4 = binary (or, "-3", "0");
        String s5 = binary (or, "0", "0");
        assertEquals(s1, "1");
        assertEquals(s2, "1");
        assertEquals(s3, "1");
        assertEquals(s4, "1");
        assertEquals(s5, "0");
	}
	@Test
    public void testNot(){
        Not not = new Not();
        String s1 = binary (not, "20", "");
        String s2 = binary (not, "1", "");
        String s3 = binary (not, "0", "");
        String s4 = binary (not, "-3", "");
        String s5 = binary (not, "0", "");
        assertEquals(s1, "0");
        assertEquals(s2, "0");
        assertEquals(s3, "1");
        assertEquals(s4, "0");
        assertEquals(s5, "1");
    }
	@Test
    public void testLess(){
        Less less = new Less();
        String s1 = binary (less, "20", "100");
        String s2 = binary (less, "1", "1");
        String s3 = binary (less, "0", "0.1");
        String s4 = binary (less, "-3", "1");
        String s5 = binary (less, "0", "7.2");
        assertEquals(s1, "1");
        assertEquals(s2, "0");
        assertEquals(s3, "1");
        assertEquals(s4, "1");
        assertEquals(s5, "1");
    }
	@Test
    public void testSum(){
        Sum sum = new Sum();
        String s1 = binary (sum, "27", "7.2");
        String s2 = binary (sum, "1", "1");
        String s3 = binary (sum, "0", "0.1");
        String s4 = binary (sum, "-3", "1");
        String s5 = binary (sum, "0", "7.2");
        assertEquals(s1, "34.2");
        assertEquals(s2, "2");
        assertEquals(s3, "0.1");
        assertEquals(s4, "-2");
        assertEquals(s5, "7.2");
    }
	
}
