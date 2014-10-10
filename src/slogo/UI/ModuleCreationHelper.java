package slogo.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * 
 * @author Nick Widmaier
 * @author Michael Deng 
 *
 */
public class ModuleCreationHelper {

	private Group root;
	private HBox firstButtonRow;
	private Canvas myCanvas;
	private GraphicsContext myGraphicsContext;
	private TextField myTextField;
	private Turtle myTurtle;
	private Map<String, Object> myVariablesMap;
	private VBox myCommandInputVBox;
	private VBox myBackgroundSelectorVBox;
	private VBox myPathSelectorVBox;
	private VBox myTurtleSelectorVBox;
	private VBox mySelectorsVBox;
	private VBox myCommandsVBox;
	private VBox myVariablesVBox;
	private VBox myUserCommandsVBox;
	private VBox myUserVariablesVBox;
	private ListView<String> myCommandsList;
	private ListView<String> myVariablesList;
	private ListView<String> myUserVariablesList;
	private ListView<String> myUserCommandsList;
	private ObservableList<String> myCommands = FXCollections.observableArrayList();
	private ObservableList<String> myVariables = FXCollections.observableArrayList();
	private ObservableList<String> myUserVariables = FXCollections.observableArrayList();
	private ObservableList<String> myUserCommands = FXCollections.observableArrayList();


	public ModuleCreationHelper(Group root) {
		this.root = root;
	}

	/**
	 * 
	 */
	public void createMainPageModules() {
		createTitle();
		createFirstButtonRow();
		createTurtleCanvas();
		createPlayButton();
		createStopButton();
		createHelpButton();
		createTurtle();
		createTextField();
		createEnteredCommandsList();
		initializeVariablesMap();
		updateObservableVariables();
		createSelectors();
		createPermanentVariablesList();
		createUserVariablesList();
		createUserCommandsList();
	}

	private void createFirstButtonRow() {
		firstButtonRow = new HBox(3);
		firstButtonRow.setLayoutX(AppConstants.FIRST_ROW_BUTTON_HBOX_X_POS);
		firstButtonRow.setLayoutY(AppConstants.FIRST_ROW_BUTTON_HBOX_Y_POS);
		root.getChildren().add(firstButtonRow);
	}
	
	private void createTitle(){
		LabelCreator LC = new LabelCreator(root);
		Label label = LC.createLabel("SLOGO!!!", 75, 0, AppConstants.TITLE_LABEL_FONT_SIZE, Color.BLACK);
	}

	private void createTurtleCanvas() {
		myCanvas = new Canvas(400,400);
		myCanvas.setLayoutX(75);
		myCanvas.setLayoutY(75);
		myGraphicsContext = myCanvas.getGraphicsContext2D();
		myGraphicsContext.setFill(Color.WHITE);
		myGraphicsContext.setStroke(Color.BLACK);
		myGraphicsContext.setLineWidth(1);
		myGraphicsContext.fillRect(1, 1, myCanvas.getWidth()-2, myCanvas.getHeight()-2);
		myGraphicsContext.strokeRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
		root.getChildren().add(myCanvas);
	}

	private void createTurtle(){
		myTurtle = new Turtle("Circle", (int) (myCanvas.getLayoutX()+myCanvas.getWidth()/2), (int) (myCanvas.getLayoutY()+myCanvas.getHeight()/2));
		root.getChildren().add(myTurtle.getImage());
	}

	private void createPlayButton() {
		ButtonCreator BC = new ButtonCreator(firstButtonRow);
		Button btn = BC.createButton(new Image(getClass().getResourceAsStream("green-plain-play-button-icon-th.png")));
		activatPlayAppButton(btn);
	}

	private void createStopButton() {
		ButtonCreator BC = new ButtonCreator(firstButtonRow);
		Button btn = BC.createButton(new Image(getClass().getResourceAsStream("red-stop-button-plain-icon-th.png")));
		activateExitAppButton(btn);
	}

	private void createHelpButton(){
		ButtonCreator BC = new ButtonCreator(firstButtonRow);
		Button btn = BC.createButton("Help");
		btn.setPrefSize(100, 50);
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				HTMLHelpPage help = new HTMLHelpPage("http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php");
				help.displayPage();
			}
		});	    
	}

	private void createTextField(){
		myCommandInputVBox = new VBox();
		LabelCreator LC = new LabelCreator(root);
		Label l = LC.createLabel("Enter your commands here! Hit Enter to see them displayed!", AppConstants.TITLE_LABEL_FONT_SIZE/3, Color.BLACK);
		myTextField = new TextField();
		myTextField.setPromptText("Enter Command");
		myTextField.setPrefHeight(20);
		myTextField.setPrefWidth(myCanvas.getWidth() - 75);
		myTextField.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				myCommands.add(myTextField.getText());
				myCommandsList.setItems(myCommands);
				//for fun with testing all the listviews
				myUserCommands.add(myTextField.getText());
				myUserCommandsList.setItems(myUserCommands);
				myUserVariables.add(myTextField.getText());
				myUserVariablesList.setItems(myUserVariables);
				myTextField.setText("");
			}
		});
		myCommandInputVBox.getChildren().addAll(l, myTextField);
		myCommandInputVBox.setLayoutX(75);
		myCommandInputVBox.setLayoutY(500);
		root.getChildren().add(myCommandInputVBox);
	}

	private void createEnteredCommandsList(){
		ListViewCreator lvc = new ListViewCreator((int) (myCanvas.getHeight()/2), 350);
		myCommandsList = lvc.getListView();
		myCommandsVBox = lvc.createListViewWithLabel("Your Previously Entered Commands", 1, Color.BLACK);
		myCommandsVBox.setLayoutX(myCanvas.getWidth()+95);
		myCommandsVBox.setLayoutY(60);
		root.getChildren().add(myCommandsVBox);
	}

	private void createPermanentVariablesList(){
		ListViewCreator lvc = new ListViewCreator(200, 200);
		myVariablesList = lvc.getListView();
		myVariablesList.setItems(myVariables);
		myVariablesVBox = lvc.createListViewWithLabel("SLOGO Variables", 1, Color.BLACK);
		myVariablesVBox.setLayoutX(AppConstants.ALL_SELECTORS_XPOS);
		myVariablesVBox.setLayoutY(AppConstants.TURTLE_IMAGE_YPOS + 80);
		root.getChildren().add(myVariablesVBox);
	}

	private void createUserVariablesList(){
		ListViewCreator lvc = new ListViewCreator(200, 200);
		myUserVariablesList = lvc.getListView();
		myUserVariablesVBox = lvc.createListViewWithLabel("User Variables", 1, Color.BLACK);
		myUserVariablesVBox.setLayoutX(AppConstants.ALL_SELECTORS_XPOS - 225);
		myUserVariablesVBox.setLayoutY(AppConstants.TURTLE_IMAGE_YPOS + 80);
		root.getChildren().add(myUserVariablesVBox);
	}

	private void createUserCommandsList(){
		ListViewCreator lvc = new ListViewCreator(200, 200);
		myUserCommandsList = lvc.getListView();
		myUserCommandsVBox = lvc.createListViewWithLabel("User Defined Commands", 1, Color.BLACK);
		myUserCommandsVBox.setLayoutX(AppConstants.ALL_SELECTORS_XPOS - 450);
		myUserCommandsVBox.setLayoutY(AppConstants.TURTLE_IMAGE_YPOS + 80);
		root.getChildren().addAll(myUserCommandsVBox);
	}

	private void updateObservableVariables(){
		myVariables = FXCollections.observableArrayList();
		for(Object key : myVariablesMap.keySet()){
			myVariables.add(key.toString() + myVariablesMap.get(key).toString());
		}
	}

	private void initializeVariablesMap(){
		myVariablesMap = new HashMap<String, Object>();
		myVariablesMap.put("Turtle X Position:     ", myTurtle.getXPos());
		myVariablesMap.put("Turtle Y Position:     ", myTurtle.getYPos());
		myVariablesMap.put("Turtle Heading:        ", myTurtle.getOrientation());
		myVariablesMap.put("Pen Down?:     ", true);
	}

        private void createPathColorSelector(){
            ColorSelectorCreator sc = new ColorSelectorCreator(root);
            sc.setUpSelector("Path Color", AppConstants.SELECTOR_WIDTH, AppConstants.SELECTOR_HEIGHT, AppConstants.SELECTOR_FONT_SIZE, (Color) myGraphicsContext.getStroke());
            final ColorPicker pathColor = sc.getSelector();
            pathColor.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle (ActionEvent event){
                            if(pathColor.getValue() != null){
                                    myGraphicsContext.setStroke(pathColor.getValue());
                            }
                    }
            });
            myPathSelectorVBox = sc.createSelectorWithLabel("Select a Path Color", AppConstants.TITLE_LABEL_FONT_SIZE/3, Color.BLACK);
    }
        private void createSelectors(){
            mySelectorsVBox = new VBox(5);
            TurtleImageSelector turtleSelect = new TurtleImageSelector(mySelectorsVBox);
            turtleSelect.create(myTurtle, root);
            BackgroundColorSelector backgroundSelect = new BackgroundColorSelector(mySelectorsVBox);
            backgroundSelect.create(root, myCanvas, myGraphicsContext);
            createPathColorSelector();
            mySelectorsVBox.getChildren().addAll(myPathSelectorVBox);
            mySelectorsVBox.setLayoutX(AppConstants.ALL_SELECTORS_XPOS);
            mySelectorsVBox.setLayoutY(AppConstants.BACKGROUND_COLOR_YPOS);
            root.getChildren().addAll(mySelectorsVBox);

    }
	
	/**
	 * Creates an event handler than exits the application on button click.
	 * 
	 * @param btn
	 *            : The button that is clicked to launch the event.
	 */
	public void activateExitAppButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
	}
	
	public void activatPlayAppButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new MethodRunner(root, myCanvas, myTurtle).moveTurtleForward((int) (myTurtle.getXPos() + 50), (int) myTurtle.getYPos() + 50);
			}
		});
	}
	
	public Turtle getTurtle() {
		return myTurtle;
	}
	
	public Canvas getCanvas() {
		return myCanvas;
	}
}

