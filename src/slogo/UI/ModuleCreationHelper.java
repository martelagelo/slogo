package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * 
 * @author Michael
 *
 */
public class ModuleCreationHelper {

	private Group root;
	
	private HBox firstButtonRow;
	
	public ModuleCreationHelper(Group root) {
		this.root = root;
	}
	
	/**
	 * 
	 */
	public void createMainPageModules() {
		createFirstButtonRow();
		createTitle();
		createTurtleCanvas();
		createPlayButton();
		createStopButton();
	}
	
	/**
	 * 
	 */
	private void createFirstButtonRow() {
		firstButtonRow = new HBox(3);
		firstButtonRow.setLayoutX(AppConstants.FIRST_ROW_BUTTON_HBOX_X_POS);
		firstButtonRow.setLayoutY(AppConstants.FIRST_ROW_BUTTON_HBOX_Y_POS);
		root.getChildren().add(firstButtonRow);
	}
	
	/**
	 * 
	 */
	private void createTitle() {
		LabelCreator LC = new LabelCreator(root, AppConstants.TITLE_LABEL_FONT_SIZE, AppConstants.TITLE_X_POS, AppConstants.TITLE_Y_POS, Color.BLACK);
		LC.createLabel("SLOGO!!!!!");
	}
	
	private void createTurtleCanvas() {
		
	}
	
	private void createPlayButton() {
		ButtonCreator BC = new ButtonCreator(firstButtonRow);
		Button btn = BC.createButton(new Image(getClass().getResourceAsStream("green-plain-play-button-icon-th.png")));
	}
	
	private void createStopButton() {
		ButtonCreator BC = new ButtonCreator(firstButtonRow);
		Button btn = BC.createButton(new Image(getClass().getResourceAsStream("red-stop-button-plain-icon-th.png")));
	}
}
