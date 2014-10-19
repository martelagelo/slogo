package slogo.UI;

import java.lang.reflect.InvocationTargetException;

import slogo.View;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * October 8th, 2014
 * 
 * Version 1
 * 
 * @author Nick Widmaier
 * @author Michael Deng 
 * @author Michael Ren
 * @author Eric Chen
 *
 */
public class ModuleCreationHelper {

	private View myView;
	private Group root;
	private Scene scene;
	private HBox firstButtonRow;
	private TurtleCanvas myCanvas;
	private CommandsTextField myTextField;
	private Turtle myTurtle;
	private VBox myCommandInputVBox;
	private VBox mySelectorsVBox;
	private ListViewPreviousCommands myCommandsList;
	private ListViewSLOGOVariables myVariablesList;
	private ListViewUserVariables myUserVariablesList;
	private ListViewUserCommands myUserCommandsList;
	private ObservableList<String> myCommands = FXCollections.observableArrayList();
	private ObservableList<String> myVariables = FXCollections.observableArrayList();
	private ObservableList<String> myUserVariables = FXCollections.observableArrayList();
	private ObservableList<String> myUserCommands = FXCollections.observableArrayList();
	private GraphicsContext myGraphicsContext;


	/**
	 * The constructor
	 * @param root The group all the modules are placed in
	 */
	public ModuleCreationHelper(Group root, Scene scene) {
		this.root = root;
		this.scene = scene;
	}

	/**
	 * 
	 */
	public void createMainPageModules() {
		createTitle();
		createTurtleCanvas();
		createTurtle();
		createSelectorVBox();
		createFirstButtonRow();
		createPlayButton();
		createStopButton();
		createHelpButton();
		createTextField();
		createListViews();
		createSelectors();
		createGridCheckBox();
		activateKeyEvents();
	}

	/**
	 * Creates a checkBox for the Grid
	 */
	private void createGridCheckBox() {
		CheckBoxCreator cb = new CheckBoxCreator(mySelectorsVBox);
		CheckBox CB = cb.createCheckBox("Toggle Grid");
		activateReferenceCB(CB);
	}

	/**
	 * Creates a HBox for the buttons
	 */
	private void createFirstButtonRow() {
		HBoxCreator HBC = new HBoxCreator(mySelectorsVBox);
		firstButtonRow = HBC.createHBox(AppConstants.STAGE_PADDING);
	}

	/**
	 * Creates the title for our application
	 */
	private void createTitle(){
		LabelCreator LC = new LabelCreator(root);
		Label label = LC.createLabel("SLOGO!!!", AppConstants.TITLE_X_POS, AppConstants.TITLE_Y_POS, AppConstants.TITLE_LABEL_FONT_SIZE, Color.BLACK);
	}

	/**
	 * Creates the canvas on which the turtle runs
	 */
	private void createTurtleCanvas() {
		myCanvas = new TurtleCanvas(root);
		myGraphicsContext = myCanvas.getGraphicsContext();
	}

	/**
	 * Creates the turtle object on the canvas
	 */
	private void createTurtle(){
		myTurtle = new Turtle("Circle", AppConstants.INITIAL_TURTLE_X_POS, AppConstants.INITIAL_TURTLE_Y_POS);
		root.getChildren().add(myTurtle.getImage());
	}

	/**
	 * 
	 */
	private void createPlayButton() {
		ButtonCreator BC = new ButtonCreator(firstButtonRow);
		Button btn = BC.createButton(new Image(getClass().getResourceAsStream("green-plain-play-button-icon-th.png")));
		//activateReferenceGridButton(btn);
	}

	/**
	 * Creates the button that exits the application
	 */
	private void createStopButton() {
		ButtonCreator BC = new ButtonCreator(firstButtonRow);
		Button btn = BC.createButton(new Image(getClass().getResourceAsStream("red-stop-button-plain-icon-th.png")));
		activateExitAppButton(btn);
	}

	/**
	 * Creates the help button
	 */
	private void createHelpButton(){
		ButtonCreator BC = new ButtonCreator(firstButtonRow);
		Button btn = BC.createButton("Help");
		btn.setPrefSize(AppConstants.HELP_BUTTON_PREF_WIDTH, AppConstants.HELP_BUTTON_PREF_HEIGHT);
		activateHelpButton(btn);    
	}
	
	/**
	 * Creates the text field where the user enters code
	 */
	private void createTextField(){
		myTextField = new CommandsTextField(root).createTextField();
		activateTextField(myTextField.getTextField());
	}

	/**
	 * 
	 */
	private void createListViews(){
		myCommandsList = new ListViewPreviousCommands(root);
		myCommandsList.create();
		myVariablesList = new ListViewSLOGOVariables(root);
		myVariablesList.create();
		myUserVariablesList = new ListViewUserVariables(root);
		myUserVariablesList.create();
		myUserCommandsList = new ListViewUserCommands(root);
		myUserCommandsList.create();
	}
	
	/**
	 * Creates a VBox for all of the selectors
	 */
	private void createSelectorVBox(){
		VBoxCreator VBC = new VBoxCreator(root);
		mySelectorsVBox = VBC.createVBox(AppConstants.STAGE_PADDING, AppConstants.FIRST_ROW_BUTTON_HBOX_X_POS, AppConstants.FIRST_ROW_BUTTON_HBOX_Y_POS);
	}
	
	/**
	 * 
	 */
	private void createSelectors() {
		TurtleImageSelector turtleSelect = new TurtleImageSelector(mySelectorsVBox);
		turtleSelect.create(myTurtle, root);
		
		BackgroundColorSelector backgroundSelect = new BackgroundColorSelector(mySelectorsVBox);
		backgroundSelect.create(root, myGraphicsContext);
		
		PathColorSelector pathSelect = new PathColorSelector(mySelectorsVBox);
		pathSelect.create(root, myGraphicsContext);
		
		PathTextureSelector pathTexture = new PathTextureSelector(mySelectorsVBox);
		pathTexture.create(root);
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
				//TODO
			}
		});
	}

	/**
	 * Lets the help button navigate to a help website
	 * @param btn: The help button
	 */
	public void activateHelpButton(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				HTMLHelpPage help = new HTMLHelpPage(AppConstants.HELP_URL);
				help.displayPage();
			}
		});
	}

	public void activateReferenceCB(CheckBox cb){
		cb.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				if(cb.isSelected()) {
					Line line = new Line(myCanvas.getCanvas().getLayoutY(), myTurtle.getYPos(), myCanvas.getCanvas().getLayoutY() + myCanvas.getCanvas().getHeight(), myTurtle.getYPos());
					line.setFill(Color.DARKGREY);
					root.getChildren().add(line);
					Line line2 = new Line(myTurtle.getXPos(), myCanvas.getCanvas().getLayoutX(), myTurtle.getXPos(), myCanvas.getCanvas().getLayoutX() + myCanvas.getCanvas().getWidth());
					line2.setFill(Color.DARKGREY);
					root.getChildren().add(line2);
				}
				else {
					root.getChildren().remove(root.getChildren().size() - 1);
					root.getChildren().remove(root.getChildren().size() - 1);
				}
			}
		});
	}

	public void activateTextField(TextField TF){
		TF.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				myCommands.add(myTextField.getText());
				myCommandsList.setItems(myCommands);
				
				myView.sendCommandToBackend(myTextField.getText());
				
				
				//for fun with testing all the listviews
				myUserCommands.add(myTextField.getText());
				myUserCommandsList.setItems(myUserCommands);
				myUserVariables.add(myTextField.getText());
				myUserVariablesList.setItems(myUserVariables);
				myTextField.setText("");
			}
		});
	}
	
	private void activateKeyEvents() {
		root.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.A) {
					myTurtle.moveTurtle(myTurtle.getXPos() - 10, myTurtle.getYPos());
				}
				if (event.getCode() == KeyCode.D) {
					myTurtle.moveTurtle(myTurtle.getXPos() + 10, myTurtle.getYPos());
				}
				if (event.getCode() == KeyCode.W) {
					myTurtle.moveTurtle(myTurtle.getXPos(), myTurtle.getYPos() - 10);
				}
				if (event.getCode() == KeyCode.S) {
					myTurtle.moveTurtle(myTurtle.getXPos(), myTurtle.getYPos() + 10);
				}
				if (event.getCode() == KeyCode.E) {
					myTurtle.incrementOrientation(10);
				}
				if (event.getCode() == KeyCode.Q) {
					myTurtle.incrementOrientation(-10);
				}
			}
		});
	}

	/**
	 * Returns the current turtle
	 * @return The turtle
	 */
	public Turtle getTurtle() {
		return myTurtle;
	}

	/**
	 * Returns the canvas 
	 * @return The canvas
	 */
	public Canvas getCanvas() {
		return myCanvas.getCanvas();
	}

	/**
	 * Sets the value of myView
	 * @param view The view
	 */
	public void setView(View view) {
		this.myView = view;
	}
}

