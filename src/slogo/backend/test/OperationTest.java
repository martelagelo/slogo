package slogo.backend.test;

import org.junit.Test;

import slogo.backend.evaluation.IOperationFactory;
import slogo.backend.impl.evaluation.OperationFactory;

public class OperationTest {
	@Test
	public void testOperationFactory(){
		IOperationFactory factory = new OperationFactory();
	}
}
