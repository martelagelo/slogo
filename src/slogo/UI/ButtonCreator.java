package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class ButtonCreator {
	
	private Group root;
	private Color backgroundColor;
	private int xPos;
	private int yPos;

	public ButtonCreator(Group root, Color backgroundColor, int xPos, int yPos) {
		this.root = root;
		this.backgroundColor = backgroundColor;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	private void createButton(Image image) {
		Button btn = new Button(null, new ImageView(image));
		btn.setLayoutX(xPos);
		btn.setLayoutY(yPos);
		btn.setStyle("-fx-background-color: #CC9900;");
		root.getChildren().add(btn);
	}
}
