package slogo.UI;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class ModuleCreationHelper {

	private Group root;
	
	public ModuleCreationHelper(Group root) {
		this.root = root;
	}
	
	public void createMainPageModules() {
		createTitle();
		createTurtleCanvas();
	}
	
	private void createTitle() {
		LabelCreator LC = new LabelCreator(root, AppConstants.TITLE_LABEL_FONT_SIZE, AppConstants.TITLE_X_POS, AppConstants.TITLE_Y_POS, Color.BLACK);
		LC.createLabel("SLOGO!!!!!");
	}
	
	private void createTurtleCanvas() {
		
	}
	
	private void createPlayButton() {
		ButtonCreator BC = new ButtonCreator(root, );
	}
}
