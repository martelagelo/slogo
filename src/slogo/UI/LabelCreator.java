package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class LabelCreator {

	private Group root;
	private HBox hBox;

	public LabelCreator(Group root) {
		this.root = root;
	}

	public LabelCreator(HBox hBox) {
		this.hBox = hBox;
	}

	public void createLabel(String content, int fontSize, int xPos, int yPos, Color color) {
		Label label = new Label();
		label.setText(content);
		label.setLayoutX(xPos);
		label.setLayoutY(yPos);
		label.setTextFill(color);
		label.setStyle("-fx-font-size: " + fontSize + "em;");
		if (root != null) {
			root.getChildren().add(label);
		} 
		else {
			hBox.getChildren().add(label);
		}
	}
	
	public void createLabel(String content, int fontSize, Color color) {
		Label label = new Label();
		label.setText(content);
		label.setTextFill(color);
		label.setStyle("-fx-font-size: " + fontSize + "em;");
		if (root != null) {
			root.getChildren().add(label);
		} 
		else {
			hBox.getChildren().add(label);
		}
	}

}
