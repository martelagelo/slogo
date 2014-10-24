package slogo.backend.test;

import static org.junit.Assert.*;

import org.junit.Test;

import slogo.Constants;
import slogo.ExecutionException;
import slogo.IModel;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.Backend;
import slogo.backend.impl.InitializationException;

public class BackendTest {
	@Test
	public void testExecute() throws ExecutionException{
		IModel backend = null;
		try {
			backend = new Backend();
		} catch (InitializationException e) {
			e.printStackTrace();
			fail();
		}
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
		IModel backend = new Backend();
		backend.execute("!!@#!@$!@#$@#$");
	}
}
