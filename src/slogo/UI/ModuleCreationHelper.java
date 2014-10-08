package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	private HBox textBoxRow;
	
	public ModuleCreationHelper(Group root) {
		this.root = root;
	}
	
	/**
	 * 
	 */
	public void createMainPageModules() {
		createHBoxes();
		createTitle();
		createTurtleCanvas();
		createPlayButton();
		createStopButton();
		createTextBoxLabel();
		createTextField();
	}
	
	private void createHBoxes() {
		createFirstButtonRow();
		createTextBoxRow();
	}
	
	/**
	 * 
	 */
	private void createFirstButtonRow() {
		HBoxCreator HBC = new HBoxCreator(root);
		firstButtonRow = HBC.createHBox(AppConstants.HBOX_SPACING, AppConstants.FIRST_ROW_BUTTON_HBOX_X_POS, AppConstants.FIRST_ROW_BUTTON_HBOX_Y_POS);
	}
	
	/**
	 * 
	 */
	private void createTextBoxRow() {
		HBoxCreator HBC = new HBoxCreator(root);
		textBoxRow = HBC.createHBox(AppConstants.HBOX_SPACING, AppConstants.TEXT_BOX_ROW_X_POS, AppConstants.TEXT_BOX_ROW_Y_POS);
	}
	
	/**
	 * 
	 */
	private void createTitle() {
		LabelCreator LC = new LabelCreator(root);
		LC.createLabel("SLOGO!!!!!", AppConstants.TITLE_LABEL_FONT_SIZE, AppConstants.TITLE_X_POS, AppConstants.TITLE_Y_POS, Color.BLACK);
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
	
	private void createTextBoxLabel() {
		LabelCreator LC = new LabelCreator(textBoxRow);
		LC.createLabel("Code goes here: ", 1, Color.BLACK);
	}
	
	private void createTextField() {
		TextFieldCreator TFC = new TextFieldCreator(textBoxRow);
		TextField textField = TFC.createTextField();
	}
}
