package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class CheckBoxCreator {

	private Group root;
	private VBox vBox;
	
	public CheckBoxCreator(Group root) {
		this.root = root;
	}
	
	public CheckBoxCreator(VBox vBox) {
		this.vBox = vBox;
	}
	
	public CheckBox createCheckBox(String content, Integer xPos, Integer yPos) {
		CheckBox cb = new CheckBox(content);
		cb.setIndeterminate(false);
		cb.setLayoutX(xPos);
		cb.setLayoutY(yPos);
		root.getChildren().add(cb);
		return cb;
	}
	
	public CheckBox createCheckBox(String content) {
		CheckBox cb = new CheckBox(content);
		cb.setIndeterminate(false);
		vBox.getChildren().add(cb);
		return cb;
	}
}
