package slogo.backend.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import slogo.Constants;
import slogo.ExecutionException;
import slogo.IModel;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.Backend;
import slogo.backend.impl.InitializationException;
import slogo.backend.impl.Languages;

public class BackendTest {
	private IModel backend;
	@Before
	public void init() throws InitializationException{
		this.backend = new Backend();
	}
	@Test
	public void testExecute() throws ExecutionException{
		IExecutionContext result = backend.execute("Minus Difference 80 50");
		assertEquals("-30.0", result.environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
		IExecutionContext foo = backend.execute("Forward 50");
		assertEquals(1, foo.turtles().get("1").lineSequence().size());
		IExecutionContext bar = backend.execute("And 1 0");
		assertEquals("0", bar.environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
		assertEquals(1, bar.turtles().get("1").lineSequence().size());
	}
	@Test(expected = ExecutionException.class)
	public void testExecuteNegative() throws Exception {
		backend.execute("!!@#!@$!@#$@#$");
	}
	@Test
	public void testMake() throws Exception {
		IExecutionContext result = backend.execute("Make :foo 10");
		assertEquals("10", result.environment().get(":foo"));
		result = backend.execute("Minus :foo");
		assertEquals("-10.0", result.environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
	}
	@Test
	public void testRepeat() throws Exception {
		IExecutionContext result = backend.execute("Repeat 3 [ Forward :repcount ]");
		assertEquals(6.0, result.turtles().get(Constants.DEFAULT_TURTLE_NAME).turtlePosition().getX());
	}
	@Test
	public void testDoTimes() throws Exception {
		IExecutionContext result = backend.execute("DoTimes [ :foo 3 ] [ Forward :foo ]");
		assertEquals(6.0, result.turtles().get(Constants.DEFAULT_TURTLE_NAME).turtlePosition().getX());
	}
	@Test
	public void testFor() throws Exception {
		IExecutionContext result = backend.execute("For [ :foo 3 6 2 ] [ Forward :foo ]");
		assertEquals(8.0, result.turtles().get(Constants.DEFAULT_TURTLE_NAME).turtlePosition().getX());
	}
	@Test
	public void testIf() throws Exception {
		IExecutionContext result = backend.execute("If 0 [ Minus 1 ]");
		assertNotEquals("-1.0", result.environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
	}
	@Test
	public void testIfElse() throws Exception {
		IExecutionContext result = backend.execute("IfElse 0 [ Minus 1 ] [ Minus 2 ]");
		assertEquals("-2.0", result.environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
	}
	@Test
	public void testTo() throws Exception {
		backend.execute("To foo [ :bar ] [ Minus :bar ]");
		IExecutionContext result = backend.execute("foo 1");
		assertEquals("-1.0", result.environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
	}
	@Test
	public void testTell() throws Exception {
	    
	    IExecutionContext result = backend.execute("Tell [ 4 10 3 9 2 ]");
	    result = backend.execute("Tell [ 4 10 ]" );
	    boolean b1 = !result.turtles().get("1").isActive();
	    boolean b4 = result.turtles().get("4").isActive();
	    boolean b10 = result.turtles().get("10").isActive();
	    boolean b3 = !result.turtles().get("3").isActive();
	    boolean b9 = !result.turtles().get("9").isActive();
	    boolean b2 = !result.turtles().get("2").isActive();
	    assertEquals(6,result.turtles().size());
	    assertTrue(b1);
	    assertTrue(b4);
	    assertTrue(b10);
	    assertTrue(b3);
	    assertTrue(b9);
	    assertTrue(b2);
	}
	@Test
	public void testAsk() throws Exception {
	    IModel back = new Backend();
	    IExecutionContext result = back.execute("Tell [ 2 3 4 5 6 ]");
	    result = back.execute("Tell [ 2 5 6 ]");
	    result = back.execute("Ask [ 1 4 5 ] [ Forward 24 ]");
	    assertTrue(!result.turtles().get("1").isActive());
	    assertTrue( result.turtles().get("2").isActive());
	    assertTrue( !result.turtles().get("3").isActive());
	    assertTrue( !result.turtles().get("4").isActive());
	    assertTrue( result.turtles().get("5").isActive());
	    assertTrue( result.turtles().get("6").isActive());
	    assertEquals(24, result.turtles().get("1").turtlePosition().getX().doubleValue(),0.001);
	    assertEquals(0, result.turtles().get("2").turtlePosition().getX().doubleValue(),0.001);
	    assertEquals(0, result.turtles().get("3").turtlePosition().getX().doubleValue(),0.001);
	    assertEquals(24, result.turtles().get("4").turtlePosition().getX().doubleValue(),0.001);
	    assertEquals(24, result.turtles().get("5").turtlePosition().getX().doubleValue(),0.001);
	    assertEquals(0, result.turtles().get("6").turtlePosition().getX().doubleValue(),0.001);
	    
	    
	            
	}
	@Test
	public void testID() throws Exception {
	    IModel back = new Backend();
	    IExecutionContext result = back.execute("Tell [ 2 4 8 ]");
	   result = back.execute("ID");
	   String ans = result.environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
	   
	    result = back.execute("Tell [ 1 3 9 ]");
	    result = back.execute("ID");
	    String ans2 = result.environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
	    boolean b1 = ans.equals("2")||ans.equals("4")||ans.equals("8");
	    boolean b2 = ans2.equals("1")||ans2.equals("3")||ans2.equals("9");
	    assertTrue(b1);
	    assertTrue(b2);
	}
	@Test
	public void testTurtles() throws Exception {
	    IModel back = new Backend();
	    IExecutionContext result = back.execute("Tell [ 9 8 4 6 7 ]");
	    result = back.execute("Turtles");
	    String s1 = result.environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
	    result = back.execute("Tell [ 1 ]");
	    String s2 = result.environment().get(Constants.RETURN_VALUE_ENVIRONMENT);
	    assertEquals("6", s1);
	    assertEquals("6", s2);
	}
	@Test
	public void testLanguages() throws Exception {
	    Languages lan = new Languages();
	    lan.buildMap("src/resources/languages/Chinese.properties");
	    Map<String,String> map = lan.returnMap();
	    assertEquals("Forward",map.get("qianjin"));
	    assertEquals("Forward",map.get("qj"));
	    assertEquals("Home",map.get("jia"));
	}
}
