package slogo.frontend.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class AngleFindingTest {
	
	@Test
	public void testAngle() {
		double a = 45.0;
		assertEquals(a, a, Math.toDegrees(Math.atan2(1,1)));
	}
	

}
