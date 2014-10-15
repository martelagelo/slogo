package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.CheckBox;

public class CheckBoxCreator {

	private Group root;
	
	public CheckBoxCreator(Group root) {
		this.root = root;
	}
	
	public CheckBox createCheckBox(String content) {
		CheckBox cb = new CheckBox(content);
		cb.setIndeterminate(false);
		cb.setLayoutX(500);
		cb.setLayoutY(500);
		root.getChildren().add(cb);
		return cb;
	}
}
