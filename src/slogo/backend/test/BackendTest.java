package slogo.backend.test;

import static org.junit.Assert.*;

import org.junit.Test;

import slogo.Constants;
import slogo.IModel;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.Backend;

public class BackendTest {
	@Test
	public void testExecute(){
		IModel backend = new Backend();
		IExecutionContext result = backend.execute("Minus Difference 80 50");
		assertEquals("-30.0", result.environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
		IExecutionContext foo = backend.execute("Forward 50");
		assertEquals(1, foo.turtles().get("1").lineSequence().size());
	}
}
