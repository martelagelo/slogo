package slogo.UI;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class LabelCreator {
	
	private int fontSize;
	private Color color;
	
	public LabelCreator(int font_size, Color color) {
		this.fontSize = font_size;
		this.color = color;
	}
	
	public Label createLabel(String content) {
		Label label = new Label();
		label.setText(content);
		label.setTextFill(color);
		label.setStyle("-fx-font-size: " + fontSize + "em;");
		return label;
	}

}
