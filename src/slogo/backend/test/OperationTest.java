package slogo.backend.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.IOperationFactory;
import slogo.backend.impl.evaluation.OperationFactory;

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
}
