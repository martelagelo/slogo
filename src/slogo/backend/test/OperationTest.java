package slogo.backend.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.paint.Color;
import org.junit.Test;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.IOperationFactory;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.OperationFactory;
import slogo.backend.impl.evaluation.commands.Constant;
import slogo.backend.impl.evaluation.commands.booleans.And;
import slogo.backend.impl.evaluation.commands.booleans.Less;
import slogo.backend.impl.evaluation.commands.booleans.Not;
import slogo.backend.impl.evaluation.commands.booleans.Or;
import slogo.backend.impl.evaluation.commands.control.Repeat;
import slogo.backend.impl.evaluation.commands.math.Atan;
import slogo.backend.impl.evaluation.commands.math.Sum;
import slogo.backend.impl.evaluation.commands.turtle.Forward;
import slogo.backend.impl.evaluation.commands.turtle.HideTurtle;
import slogo.backend.impl.parsing.SyntaxNode;
import slogo.backend.impl.util.Coordinates;
import slogo.backend.impl.util.Direction;
import slogo.backend.impl.util.Line;
import slogo.backend.impl.util.Qualities;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.ISyntaxNode;
import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ILine;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;
import slogo.backend.util.Visibility;

public class OperationTest {
	@Test
	public void testOperationFactory(){
		String[] commands = {
				"Sum",
				"Forward",
				"And"
		};
		IOperationFactory factory = new OperationFactory();
		for (String command: commands) {
			try {
				IOperation sum = factory.makeElement(command);
				assertEquals(command, sum.type());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
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
	    Qualities q = new Qualities(Color.BLACK, 0, 1);
	    ITurtleStatus status = new TurtleStatus(list,cor,dir,pen,vis, q);
	    Map <String, ITurtleStatus> turtles = new HashMap<String, ITurtleStatus>();
	    turtles.put("1", status);
	    Map<String, String> environment = new HashMap<String, String>();
	    return new ExecutionContext(turtles, environment, new HashMap<>());
	}
	public String operate (int flag, IOperation op, String s1, String s2){
	    IExecutionContext con1 = buildContext();
        con1.environment().put("returnValue", s1);
        IExecutionContext con2 = buildContext();
        con2.environment().put("returnValue", s2);
        List<IExecutionContext> list = new ArrayList<IExecutionContext>();
        list.add(con1);
        if(flag==2){
        list.add(con2);
        }
        IExecutionContext result = null;
        try {
            result = op.execute(list, null, null);
        } catch (MalformedSyntaxException e) {
            e.printStackTrace();
        }
        return result.environment().get("returnValue");
	}
	@Test
	public void testAnd(){
	    IOperation and = new And();
	    String s1 = operate (2,and, "20", "41");
	    String s2 = operate (2,and, "1", "-3");
	    String s3 = operate (2,and, "0", "10");
	    String s4 = operate (2,and, "-3", "0");
	    String s5 = operate (2,and, "0", "0");
	    assertEquals("1",s1);
	    assertEquals("1",s2);
	    assertEquals("0",s3);
	    assertEquals("0",s4);
	    assertEquals("0",s5);
	    
	}
	@Test
	public void testOr(){
	    Or or = new Or();
        String s1 = operate (2,or, "20", "41");
        String s2 = operate (2,or, "1", "-3");
        String s3 = operate (2,or, "0", "10");
        String s4 = operate (2,or, "-3", "0");
        String s5 = operate (2,or, "0", "0");
        assertEquals("1",s1);
        assertEquals("1",s2);
        assertEquals("1",s3);
        assertEquals("1",s4);
        assertEquals("0",s5);
	}
	@Test
    public void testNot(){
        Not not = new Not();
        String s1 = operate (1,not, "20", "");
        String s2 = operate (1,not, "1", "");
        String s3 = operate (1,not, "0", "");
        String s4 = operate (1,not, "-3", "");
        String s5 = operate (1,not, "0", "");
        assertEquals("0",s1);
        assertEquals("0",s2);
        assertEquals("1",s3);
        assertEquals("0",s4);
        assertEquals("1",s5);
    }
	@Test
    public void testLess(){
        Less less = new Less();
        String s1 = operate (2,less, "20", "100");
        String s2 = operate (2,less, "1", "1");
        String s3 = operate (2,less, "0", "0.1");
        String s4 = operate (2,less, "-3", "1");
        String s5 = operate (2,less, "0", "7.2");
        assertEquals("1",s1);
        assertEquals("0",s2);
        assertEquals("1",s3);
        assertEquals("1",s4);
        assertEquals("1",s5);
    }
	@Test
    public void testSum(){
        Sum sum = new Sum();
        String s1 = operate (2,sum, "27", "7.2");
        String s2 = operate (2,sum, "1", "1");
        String s3 = operate (2,sum, "0", "0.1");
        String s4 = operate (2,sum, "-3", "1");
        String s5 = operate (2,sum, "0", "7.2");
        assertEquals("34.2",s1);
        assertEquals("2.0",s2);
        assertEquals("0.1",s3);
        assertEquals("-2.0",s4);
        assertEquals("7.2",s5);
    }
	@Test
    public void testAtan(){
        Atan atan = new Atan();
        String s1 = operate (1,atan, "1", "");
        String s2 = operate (1,atan, "2.2", "");
        String s3 = operate (1,atan, "1.2", "");
        String s4 = operate (1,atan, "-10.3", "");
        String s5 = operate (1,atan, "7.8", "");
        assertEquals(Double.toString(Math.toDegrees(Math.atan(1))),s1);
        assertEquals(Double.toString(Math.toDegrees(Math.atan(2.2))),s2);
        assertEquals(Double.toString(Math.toDegrees(Math.atan(1.2))),s3);
        assertEquals(Double.toString(Math.toDegrees(Math.atan(-10.3))),s4);
        assertEquals(Double.toString(Math.toDegrees(Math.atan(7.8))),s5);
    }
	@Test
    public void testForward(){
	IOperation forward = new Forward();
	IExecutionContext con1 = buildContext();
	
	con1.environment().put("returnValue", "50");
	List<IExecutionContext> list = new ArrayList<IExecutionContext>();
	list.add(con1);
	IExecutionContext con = null;
    try {
        con = forward.execute(list,null,null);
    } catch (MalformedSyntaxException e) {
        e.printStackTrace();
    }
	ITurtleStatus stat = con.turtles().get("1");
	
	String retString = con.environment().get("returnValue");
	
	List<ILine> lineList = stat.lineSequence();
	int listSize = lineList.size();
	ILine line = lineList.get(0);
	String startX = line.start().getX().toString();
	String startY = line.start().getY().toString();
	String endX = line.end().getX().toString();
	String endY = line.end().getY().toString();
	
	ICoordinates cor = stat.turtlePosition();
	String x = cor.getX().toString();
	String y = cor.getY().toString();
	
	IDirection dir = stat.turtleDirection();
	String dirString = Double.toString(dir.toDegrees());
	
	PenState pen = stat.penState();
	
	Visibility vis = stat.turtleVisibility();
	
	assertEquals("50",retString);
	
	assertEquals(1,listSize);
	assertEquals("0.0",startX);
	assertEquals("0.0",startY);
	assertEquals("50.0",endX);
	assertEquals("0.0",endY);
	
	assertEquals("50.0",x);
	assertEquals("0.0",y);
	
	assertEquals("0.0",dirString);
	
	assertEquals(PenState.DOWN,pen);
	
	assertEquals(Visibility.VISIBLE,vis);
	}
	@Test
	public void testHideTurtle(){
	    IOperation hide = new HideTurtle();
	    IExecutionContext con1 = buildContext();
	    List<IExecutionContext> list = new ArrayList<IExecutionContext>();
	    list.add(con1);
	    IExecutionContext con = null;
        try {
            con = hide.execute(list,null,null);
        } catch (MalformedSyntaxException e) {
            e.printStackTrace();
        }
	    Visibility vis = con.turtles().get("1").turtleVisibility();
	    
	    assertEquals(Visibility.INVISIBLE,vis);
	    
	}
	@Test
	public void testRepeat(){
	    ISyntaxNode child = new SyntaxNode("Constant",new Constant("50"),new ArrayList<ISyntaxNode>());
	    List<ISyntaxNode> forwardList = new ArrayList<ISyntaxNode>();
	    forwardList.add(child);
	    ISyntaxNode forward = new SyntaxNode("Forward", new Forward(),forwardList );
	    ISyntaxNode constant = new SyntaxNode("Constant",new Constant("3"), new ArrayList<ISyntaxNode>());
	    List<ISyntaxNode> repeatList = new ArrayList<ISyntaxNode>();
	    repeatList.add(constant); repeatList.add(forward);
	    ISyntaxNode cur = new SyntaxNode("Repeat", new Repeat("Repeat", 2, 2), repeatList);
	    IExecutionContext expression = buildContext();
	    expression.environment().put("returnValue", "4");
	    IExecutionContext context = buildContext();
	   Repeat re = new Repeat("Repeat", 2, 2);
	   List<IExecutionContext> contextList = new ArrayList<IExecutionContext>();
	   contextList.add(expression); contextList.add(context);
	   IExecutionContext result = context;
	   try {
            result = re.execute(contextList, context, cur);
        } catch (MalformedSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    ICoordinates cor = result.turtles().get("1").turtlePosition();
	    double x = cor.getX().doubleValue();
	    double y = cor.getY().doubleValue();
	    assertEquals(150, x, 0.001);
	    assertEquals(0,y,0.001);
	}
}
