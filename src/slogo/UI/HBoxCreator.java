package slogo.UI;

import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HBoxCreator {
	
	private Group root;
	private VBox vBox;
	
	public HBoxCreator(Group root) {
		this.root = root;
	}
	
	public HBoxCreator(VBox vBox) {
		this.vBox = vBox;
	}
	
	public HBox createHBox(int spacing, Integer x_Coord, Integer y_Coord) {
		HBox firstButtonRow = new HBox(spacing);
		firstButtonRow.setLayoutX(x_Coord);
		firstButtonRow.setLayoutY(y_Coord);
		root.getChildren().add(firstButtonRow);
		return firstButtonRow;
	}
	
	public HBox createHBox(int spacing) {
		HBox firstButtonRow = new HBox(spacing);
		vBox.getChildren().add(firstButtonRow);
		return firstButtonRow;
	}

}
