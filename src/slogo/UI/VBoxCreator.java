package slogo.UI;

import javafx.scene.Group;
import javafx.scene.layout.VBox;

public class VBoxCreator {

private Group root;
	
	public VBoxCreator(Group root) {
		this.root = root;
	}
	
	public VBox createVBox(int spacing, Integer x_Coord, Integer y_Coord) {
		VBox vBox = new VBox(spacing);
		vBox.setLayoutX(x_Coord);
		vBox.setLayoutY(y_Coord);
		root.getChildren().add(vBox);
		return vBox;
	}
}
