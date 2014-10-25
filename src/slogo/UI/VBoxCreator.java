package slogo.UI;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VBoxCreator {

private Group root;
private HBox hBox;
	
	public VBoxCreator(Group root) {
		this.root = root;
	}
	
	public VBoxCreator(HBox hBox) {
		this.hBox = hBox;
	}
	
	public VBox createVBox(int spacing, Integer x_Coord, Integer y_Coord) {
		VBox vBox = new VBox(spacing);
		vBox.setLayoutX(x_Coord);
		vBox.setLayoutY(y_Coord);
		if (root != null) root.getChildren().add(vBox);
		else hBox.getChildren().add(vBox);
		return vBox;
	}
	
	public VBox createVBox(int spacing) {
		VBox vBox = new VBox(spacing);
		vBox.setAlignment(Pos.CENTER);
		if (root != null) root.getChildren().add(vBox);
		else hBox.getChildren().add(vBox);
		return vBox;
	}
	
	public VBox createVBoxScrollable(int spacing) {
		VBox vBox = new VBox(spacing);
		return vBox;
	}
}
