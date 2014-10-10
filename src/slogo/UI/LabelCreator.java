package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LabelCreator {
	
	private Group root;
	private VBox vBox;
	
	public LabelCreator(Group root) {
		this.root = root;
	}
	
	public LabelCreator(VBox vBox) {
		this.vBox = vBox;
	}
	
	public Label createLabel(String content, int fontSize, Color color) {
		Label label = new Label();
		label.setText(content);
		label.setTextFill(color);
		label.setStyle("-fx-font-size: " + fontSize + "em;");
		if (root != null) {
			root.getChildren().add(label);
		}
		else {
			vBox.getChildren().add(label);
		}
		return label;
	}
	
	public Label createLabel(String content, int xPos, int yPos, int fontSize, Color color) {
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
			vBox.getChildren().add(label);
		}
		return label;
	}

}
