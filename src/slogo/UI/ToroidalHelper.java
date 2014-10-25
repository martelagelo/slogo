package slogo.UI;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;

public class ToroidalHelper {

	List<Line> lineList;
	private static final Integer MAX_X = AppConstants.CANVAS_WIDTH - AppConstants.CANVAS_OFFSET_X_POS;
	private static final Integer MIN_X = AppConstants.CANVAS_OFFSET_X_POS;
	private static final Integer MAX_Y = AppConstants.CANVAS_HEIGHT - AppConstants.CANVAS_OFFSET_Y_POS;
	private static final Integer MIN_Y = AppConstants.CANVAS_OFFSET_Y_POS;
	private static final Integer WIDTH = AppConstants.CANVAS_WIDTH;
	private static final Integer HEIGHT = AppConstants.CANVAS_HEIGHT;

	public ToroidalHelper(List<Line> lineList) {
		this.lineList = lineList;
	}

	public List<Line> makeLinesToroidal() {
		List<Line> newLineList = new ArrayList<Line>();

		for(Line l: lineList) {
			if (!checkBounds(l)) {
				List<Line> subLineList = new ArrayList<>();
				cutLines(l, subLineList);
				newLineList.addAll(subLineList);
			}
			else {
				newLineList.add(l);
			}
		}
		return newLineList;
	}

	private boolean checkBounds(Line l) {
		return (l.getEndX() <= MAX_X && l.getEndX() >= MIN_X && l.getEndY() <= MAX_Y && l.getEndY() >= MIN_Y);
	}

	private void cutLines(Line l, List<Line> lines) {
		if (checkBounds(l)) {
			lines.add(l);
		}
		else {
			double angle = Math.atan((l.getStartX() - l.getEndX()) / (l.getStartY() - l.getEndY()));
			Line newLine = runXCalculation(l, angle);
			lines.add(newLine);
			cutLines(newLine, lines);
		}
	}

	private Line runXCalculation(Line l, double angle) {
		Line newLine = new Line();
		if (l.getEndX() > MAX_X) {
			if (angle > 90) angle = angle - 90;
			setLineCoords(newLine, MIN_X, l.getStartY() + (MAX_X - l.getStartX()) / Math.tan(angle), l.getEndX()-WIDTH, l.getEndY());
			setLineCoords(l, l.getStartX(), l.getStartY(), MAX_X, l.getStartY() + (MAX_X - l.getStartX()) / Math.tan(angle));
		}
		else if (l.getEndX() < MIN_X) {
			if (angle < -90) angle = angle + 90;
			setLineCoords(newLine, MAX_X, l.getStartY() + (l.getStartX() - MIN_X) / Math.tan(angle), l.getEndX() + WIDTH, l.getEndY());
			setLineCoords(l, l.getStartX(), l.getStartY(), MIN_X, l.getStartY() + (l.getStartX() - MIN_X) / Math.tan(angle));
		}
		newLine = runYCalculation(newLine, angle);
		return newLine;
	}

	private Line runYCalculation(Line l, double angle) {
		if (l.getEndY() > MAX_Y) {
			if (angle > 90) angle = angle - 90;
			//Line newLine = new Line();
			//setLineCoords(newLine, l.getStartX() + (MAX_Y - l.getStartY()) * Math.tan(angle), MIN_Y, l.getEndX(), l.getEndY() - HEIGHT);
			setLineCoords(l, l.getStartX(), l.getStartY(), l.getStartX() + (MAX_Y - l.getStartY()) * Math.tan(angle), MAX_Y);
		}
		else if (l.getEndY() < MIN_Y) {
			if (angle < -90) angle = angle + 90;
			//Line newLine = new Line();
			//setLineCoords(newLine, l.getStartX() + (l.getStartY() - MIN_Y) * Math.tan(angle), MAX_Y, l.getEndX(), l.getEndY() + HEIGHT);
			setLineCoords(l, l.getStartX(), l.getStartY(), l.getStartX() + (l.getStartY() - MIN_Y) * Math.tan(angle), MIN_Y);
		}
		return l;
	}

	private void setLineCoords(Line l, double start_x, double start_y, double end_x, double end_y) {
		l.setStartX(start_x);
		l.setStartY(start_y);
		l.setEndX(end_x);
		l.setEndY(end_y);
	}
}
