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
	
	public ToroidalHelper(List<Line> lineList) {
		this.lineList = lineList;
	}
	
	public List<Line> makeLinesToroidal() {
		List<Line> newLineList = new ArrayList<Line>();
		
		for(Line l: lineList) {
			if (checkBounds(l)) {
				List<Line> subLineList = new ArrayList<>();
				cutLines(subLineList);
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

	private void cutLines(List<Line> lines) {
		
	}
}
