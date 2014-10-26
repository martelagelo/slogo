package slogo.frontend.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class AngleFindingTest {
	
	@Test
	public void testAngle() {
		assertEquals("45.0", Math.toDegrees(Math.atan2(1,1)));
		//System.out.println(Math.atan(0/1));
	}

}
