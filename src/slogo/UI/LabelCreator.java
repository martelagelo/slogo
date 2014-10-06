package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class LabelCreator {
	
	private Group root;
	private int fontSize;
	private int xPos;
	private int yPos;
	private Color color;
	
	public LabelCreator(Group root, int font_size, int x_Pos, int y_Pos, Color color) {
		this.root = root;
		this.fontSize = font_size;
		this.xPos = x_Pos;
		this.yPos = y_Pos;
		this.color = color;
	}
	
	public void createLabel(String content) {
		Label label = new Label();
		label.setText(content);
		label.setLayoutX(xPos);
		label.setLayoutY(yPos);
		label.setTextFill(color);
		label.setStyle("-fx-font-size: " + fontSize + "em;");
		root.getChildren().add(label);
	}

}
