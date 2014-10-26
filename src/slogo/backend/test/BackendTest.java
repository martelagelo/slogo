package slogo.backend.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import slogo.Constants;
import slogo.ExecutionException;
import slogo.IModel;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.Backend;
import slogo.backend.impl.InitializationException;

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
		IExecutionContext result = backend.execute("Repeat 3 [ Sum 1 1 ]");
		assertEquals("6.0", result.environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
	}
	@Test
	public void testDoTimes() throws Exception {
		IExecutionContext result = backend.execute("DoTimes [ :foo 3 ] [ Sum :foo :foo]");
		assertEquals("12.0", result.environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
	}
	@Test
	public void testFor() throws Exception {
		IExecutionContext result = backend.execute("For [ :foo 3 6 2 ] [ Sum :foo :foo ]");
		assertEquals("16.0", result.environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
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
}
