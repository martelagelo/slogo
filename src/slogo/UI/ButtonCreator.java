package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

/**
 * 
 * @author Michael
 *
 */
public class ButtonCreator {
	
	private HBox hbox;
	private VBox vBox;
	
	public ButtonCreator(HBox hbox) {
		this.hbox = hbox;
	}
	
	public ButtonCreator(VBox vbox) {
		this.vBox = vbox;
	}
	
	public Button createButton(Image image) {
		Button btn = new Button(null, new ImageView(image));
		if (hbox != null) {
			hbox.getChildren().add(btn);
		}
		else {
			vBox.getChildren().add(btn);
		}
		return btn;
	}
	
	public Button createButton(String content) {
		Button btn = new Button(content);
		if (hbox != null) {
			hbox.getChildren().add(btn);
		}
		else {
			vBox.getChildren().add(btn);
		}
		return btn;
	}
}
