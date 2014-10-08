package slogo.UI;

import javafx.scene.Group;
import javafx.scene.layout.HBox;

public class HBoxCreator {
	
	private Group root;
	
	public HBoxCreator(Group root) {
		this.root = root;
	}
	
	public HBox createHBox(int spacing, Integer x_Coord, Integer y_Coord) {
		HBox firstButtonRow = new HBox(spacing);
		firstButtonRow.setLayoutX(x_Coord);
		firstButtonRow.setLayoutY(y_Coord);
		root.getChildren().add(firstButtonRow);
		return firstButtonRow;
	}

}
