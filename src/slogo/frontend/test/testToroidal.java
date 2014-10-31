package slogo.frontend.test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;

import org.junit.Test;

import slogo.UI.ToroidalHelper;

public class testToroidal {

	ToroidalHelper TH = new ToroidalHelper(new ArrayList<Line>());

	private void setLineCoords (Line l, double start_x, double start_y, double end_x, double end_y) {
		l.setStartX(start_x);
		l.setStartY(start_y);
		l.setEndX(end_x);
		l.setEndY(end_y);
	}

	@Test
	public void testAngle() {
		double a = 45.0;
		assertEquals(a, a, Math.toDegrees(Math.atan2(1,1)));
	}

	@Test
	public void testMakeLinesToroidal() {
		List<Line> lines = new ArrayList<Line>();
		Line line = new Line();
		setLineCoords(line, 100, 100, 200, 100);
		lines.add(line);
		assertEquals(lines, new ToroidalHelper(lines).makeLinesToroidal());
	}

	@Test
	public void testMakeLinesToroidal2() {
		List<Line> initialLines = new ArrayList<Line>();
		List<Line> toroidalLines = new ArrayList<Line>();
		Line line = new Line();
		setLineCoords(line, 100, 200, 700, 200);
		initialLines.add(line);
		toroidalLines = new ToroidalHelper(initialLines).makeLinesToroidal();
		Line l = toroidalLines.get(0);
		assertEquals(100, 100, l.getStartX());
		assertEquals(200, 200, l.getStartY());
		assertEquals(610, 610, (int) l.getEndX());
		assertEquals(200, 200, l.getEndY());
		Line l2 = toroidalLines.get(1);
		assertEquals(10, 10, l.getStartX());
		assertEquals(200, 200, l.getStartY());
		assertEquals(100, 100, (int) l.getEndX());
		assertEquals(200, 200, l.getEndY());
	}

	@Test
	public void testSetAngle() {
		Line line = new Line();
		setLineCoords(line, 100, 100, 200, 100);
		assertEquals(0.0, 0.0, TH.setAngle(line));
		setLineCoords(line, 100, 100, 200, 200);
		assertEquals((Math.PI / 4), (Math.PI / 4), TH.setAngle(line));
		setLineCoords(line, 100, 100, 100, 200);
		assertEquals((Math.PI / 2), (Math.PI / 2), TH.setAngle(line));
	}

	@Test
	public void testYBounds() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//		Class c = Class.forName("slogo.UI.ToroidalHelper");
		//		Constructor<ToroidalHelper> constructor = c.getDeclaredConstructor(Object.class);
		//		constructor.setAccessible(true);
		//		ToroidalHelper th = constructor.newInstance("ToroidalHelper");
		//		Method method = th.getClass().getDeclaredMethod("checkYBounds");
		//		method.setAccessible(true);
		//		Object r = method.invoke(th);
		assertEquals(true, TH.checkYBounds(275));
	}

	@Test
	public void testAllBounds() {
		Line line = new Line();
		setLineCoords(line, 100, 100, 200, 200);
		assertEquals(true, TH.checkAllBounds(line));
		setLineCoords(line, 300, 300, 100, 100);
		assertEquals(true, TH.checkAllBounds(line));
		setLineCoords(line, 300, 300, 700, 700);
		assertEquals(false, TH.checkAllBounds(line));
	}

}
