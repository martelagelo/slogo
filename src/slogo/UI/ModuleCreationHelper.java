package slogo.UI;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.View;
import slogo.frontend.Config.ConfigHashMapCreator;
import slogo.frontend.Config.ConfigReader;
import slogo.frontend.Config.ConfigWriter;
import slogo.frontend.Config.ParameterDistributor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

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

	private ConfigReader configReader;
	private ConfigWriter configWriter;
	private Stage stage;
	private View myView;
	private Group root;
	private Scene scene;
	private HBox firstButtonRow;
	private HBox secondButtonRow;
	private TurtleCanvas myCanvas;
	private CommandsTextField myTextField;
	private List<Turtle> myTurtleList;
	private VBox mySelectorsVBox;
	private VBox myListsVBox;
	private ListViewPreviousCommands myCommandsList;
	private ListViewSLOGOVariables myVariablesList;
	private ListViewUserVariables myUserVariablesList;
	private ListViewUserCommands myUserCommandsList;
	private ObservableList<String> myCommands = FXCollections.observableArrayList();
	private ObservableList<String> myVariables = FXCollections.observableArrayList();
	private ObservableList<String> myUserVariables = FXCollections.observableArrayList();
	private ObservableList<String> myUserCommands = FXCollections.observableArrayList();
	private GraphicsContext myGraphicsContext;
	private Slider animationSlider;
	private ScrollPane moduleScrollPane;
	private ScrollPane listScrollPane;

	private Map<String, Node> myImagesMap;
	private int totalUserImages;
	private TurtleImageSelector myTurtleSelector;

	private List<Line> myGridLines;
	private int totalTurtles;

	private VBox debugVBox;
	private Label debugLabel;
	private Button debugStepIntoButton;
	private Button debugStepOverButton;
	private Boolean isInDebugMode;

	private Label runningStatusLabel;
	private int commandsHistoryCounter;
	private PathColorSelector myPathColorSelector;
	private BackgroundColorSelector myBackgroundColorSelector;
	private CheckBox labelsCheckBox;


	/**
	 * The constructor
	 * @param root The group all the modules are placed in
	 */
	public ModuleCreationHelper(Group root, Scene scene, Stage stage) {
		this.stage = stage;
		this.root = root;
		this.scene = scene;
		configReader = new ConfigReader();
		configWriter = new ConfigWriter();
		totalUserImages = 1;
		isInDebugMode = false;
		commandsHistoryCounter = 0;
		totalTurtles = 2;
		labelsCheckBox = new CheckBox();
	}

	/**
	 * Populates the scene with modules
	 */
	public void createMainPageModules() {
		createTitle();
		createTurtleCanvas();
		createTurtle();
		createModuleScrollPane();
		createSelectorVBox();
		createSelectorVBoxModules();
		createListScrollPane();
		createListVBox();
		createListViews();
		createDebugModules();
		activateKeyEvents();
		createRunningStatusLabel();
	}

	/**
	 * Creates the modules in the first scrolling pane
	 */
	private void createSelectorVBoxModules() {
		createFirstRowButtons();
		createSecondRowButtons();
		createTextField();
		createSelectors();
		createHelpButton();
		createNewTurtleButton();
		createTurtleImageLoaderButton();
		createLoadButton();
		createSaveButton();
		createExtraWorkspaceButton();
		createIncreaseDecreaseButtons();
		createAnimationSpeedSlider();
		createGridCheckBox();
		createLabelsCheckBox();
	}

	/**
	 * Creates the first row of buttons
	 */
	private void createFirstRowButtons() {
		createFirstButtonRow();
		createPlayButton();
		createStopButton();
	}

	/**
	 * Creates the second row of buttons
	 */
	private void createSecondRowButtons() {
		createSecondButtonRow();
		createPauseButton();
		createResetButton();
	}

	/**
	 * Creates all the debugging modules
	 */
	private void createDebugModules() {
		createDebugVBox();
		createDebugLabel();
		createDebugStepIntoButton();
		createDebugStepOverButton();
		createDebugButton();
	}

	/**
	 * Creates a Scroll Pane for the lists in the application
	 */
	private void createListScrollPane() {
		ScrollPaneCreator SPC = new ScrollPaneCreator(root);
		listScrollPane = SPC.createScrollPane(AppConstants.LIST_SCROLL_PANE_X_POS, AppConstants.LIST_SCROLL_PANE_Y_POS, AppConstants.LIST_SCROLL_PANE_WIDTH + 30, AppConstants.LIST_SCROLL_PANE_HEIGHT);
	}

	/**
	 * Creates a Scroll Pane for the modules in the application
	 */
	private void createModuleScrollPane() {
		ScrollPaneCreator SPC = new ScrollPaneCreator(root);
		moduleScrollPane = SPC.createScrollPane(AppConstants.FIRST_SCROLL_PANE_X_POS, AppConstants.FIRST_SCROLL_PANE_Y_POS, AppConstants.FIRST_SCROLL_PANE_WIDTH, AppConstants.FIRST_SCROLL_PANE_HEIGHT);
	}

	/**
	 * Creates a checkBox for the Grid
	 */
	private void createGridCheckBox() {
		GridLinesToggler GLT = new GridLinesToggler(mySelectorsVBox);
		GLT.activate(myCanvas, root);
	}

	/**
	 * Creates a HBox for the buttons
	 */
	private void createFirstButtonRow() {
		HBoxCreator HBC = new HBoxCreator(mySelectorsVBox);
		firstButtonRow = HBC.createHBox(AppConstants.STAGE_PADDING);
	}

	/**
	 * Creates a HBox for buttons below the first row of buttons
	 */
	private void createSecondButtonRow() {
		HBoxCreator HBC = new HBoxCreator(mySelectorsVBox);
		secondButtonRow = HBC.createHBox(AppConstants.STAGE_PADDING);
	}

	/**
	 * Creates the title for our application
	 */
	private void createTitle(){
		LabelCreator LC = new LabelCreator(root);
		LC.createLabel("SLOGO!!!", AppConstants.TITLE_X_POS, AppConstants.TITLE_Y_POS, AppConstants.TITLE_LABEL_FONT_SIZE, Color.BLACK);
	}

	/**
	 * Creates the canvas on which the turtle runs
	 */
	private void createTurtleCanvas() {
		myCanvas = new TurtleCanvas(root);
		myGraphicsContext = myCanvas.getGraphicsContext();
		activateTurtleCanvas();
	}

	/**
	 * Creates the turtle object on the canvas
	 */
	private void createTurtle(){
		myTurtleList = new ArrayList<Turtle>();
		Turtle t = new Turtle("Triangle", AppConstants.INITIAL_TURTLE_X_POS, AppConstants.INITIAL_TURTLE_Y_POS, "1");
		myTurtleList.add(t);
		root.getChildren().addAll(myTurtleList.get(0).getImage());
		if (labelsCheckBox.isSelected()){
			myTurtleList.get(0).addLabel(root);
		}
	}

	/**
	 * Creates a button that runs animations
	 */
	private void createPlayButton() {
		VBoxCreator VBC = new VBoxCreator(firstButtonRow);
		VBox vBox = VBC.createVBox(AppConstants.VBOX_SPACING);
		LabelCreator LC = new LabelCreator(vBox);
		LC.createLabel("Run Animation", AppConstants.LABEL_FONT_SIZE, AppConstants.DEFAULT_TEXT_COLOR);
		ButtonCreator BC = new ButtonCreator(vBox);
		Button btn = BC.createButton(new Image(getClass().getResourceAsStream("green-plain-play-button-icon-th.png")));
		activatePlayButton(btn);
	}

	/**
	 * Creates the button that exits the application
	 */
	private void createStopButton() {
		VBoxCreator VBC = new VBoxCreator(firstButtonRow);
		VBox vBox = VBC.createVBox(AppConstants.VBOX_SPACING);
		LabelCreator LC = new LabelCreator(vBox);
		LC.createLabel("Exit Application", AppConstants.LABEL_FONT_SIZE, AppConstants.DEFAULT_TEXT_COLOR);
		ButtonCreator BC = new ButtonCreator(vBox);
		Button btn = BC.createButton(new Image(getClass().getResourceAsStream("red-stop-button-plain-icon-th.png")));
		activateExitAppButton(btn);
	}

	/**
	 * Creates a button that pauses animation
	 */
	private void createPauseButton() {
		VBoxCreator VBC = new VBoxCreator(secondButtonRow);
		VBox vBox = VBC.createVBox(AppConstants.VBOX_SPACING);
		LabelCreator LC = new LabelCreator(vBox);
		LC.createLabel("Pause Animation", AppConstants.LABEL_FONT_SIZE, AppConstants.DEFAULT_TEXT_COLOR);
		ButtonCreator BC = new ButtonCreator(vBox);
		Button btn = BC.createButton(new Image(getClass().getResourceAsStream("Button-Pause-icon.png")));
		activatePauseButton(btn);
	}

	/**
	 * Creates a button that resets animation
	 */
	private void createResetButton() {
		VBoxCreator VBC = new VBoxCreator(secondButtonRow);
		VBox vBox = VBC.createVBox(AppConstants.VBOX_SPACING);
		LabelCreator LC = new LabelCreator(vBox);
		LC.createLabel("Reset Animation", AppConstants.LABEL_FONT_SIZE, AppConstants.DEFAULT_TEXT_COLOR);
		ButtonCreator BC = new ButtonCreator(vBox);
		Button btn = BC.createButton(new Image(getClass().getResourceAsStream("reset_button.jpg")));
		activateResetButton(btn);
	}

	/**
	 * Creates the help link button
	 */
	private void createHelpButton(){
		ButtonCreator BC = new ButtonCreator(mySelectorsVBox);
		Button btn = BC.createButton("Load Help Page");
		activateHelpButton(btn);    
	}

	/**
	 * Creates a button that loads an image for the turtle
	 */
	private void createTurtleImageLoaderButton(){
		ButtonCreator BC = new ButtonCreator(mySelectorsVBox);
		Button btn = BC.createButton("Load New Turtle Image");
		activateTurtleImageLoaderButton(btn);
	}

	/**
	 * Creates the buttons that increase and decrease stroke width
	 */
	private void createIncreaseDecreaseButtons(){
		HBox hb = new HBox();
		ButtonCreator BC = new ButtonCreator(hb);
		Button btn = BC.createButton("Inc. Pen Size");
		Button btn2 = BC.createButton("Dec. Pen Size");
		activateIncreaseDecreaseButtons(btn, btn2, myView);
		mySelectorsVBox.getChildren().add(hb);   
	}

	/**
	 * Add another turtle to scene
	 */
	private void createNewTurtleButton(){
		ButtonCreator BC = new ButtonCreator(mySelectorsVBox);
		Button btn = BC.createButton("Add turtle");
		activateNewTurtleButton(btn);
	}

	/**
	 * Creates a button that loads in a config file
	 */
	private void createLoadButton() {
		ButtonCreator BC = new ButtonCreator(mySelectorsVBox);
		Button btn = BC.createButton("Load Config File");
		activateLoadButton(btn);
	}

	/**
	 * Create a button that saves to a config file
	 */
	private void createSaveButton() {
		ButtonCreator BC = new ButtonCreator(mySelectorsVBox);
		Button btn = BC.createButton("Save to Config File");
		activateSaveButton(btn);
	}

	/**
	 * Creates an extra workspace window
	 */
	private void createExtraWorkspaceButton() {
		ButtonCreator BC = new ButtonCreator(mySelectorsVBox);
		Button btn = BC.createButton("Load Extra Workspace");
		activateExtraWorkspaceButton(btn);
	}

	/**
	 * Creates a slider that controls animation speed
	 */
	private void createAnimationSpeedSlider() {
		LabelCreator LC = new LabelCreator(mySelectorsVBox);
		SliderCreator SC = new SliderCreator(mySelectorsVBox);
		LC.createLabel("Animation Speed Slider", AppConstants.LABEL_FONT_SIZE, AppConstants.DEFAULT_TEXT_COLOR);
		animationSlider = SC.createSlider(AppConstants.ANIMATION_SLIDER_MIN_VALUE, AppConstants.ANIMATION_SLIDER_MAX_VALUE, AppConstants.ANIMATION_SLIDER_DEFAULT_VALUE);
	}

	/**
	 * Creates a button that activates and deactivates debug mode
	 */
	private void createDebugButton() {
		CheckBoxCreator cb = new CheckBoxCreator(mySelectorsVBox);
		CheckBox CB = cb.createCheckBox("Debug Mode");
		activateDebugCB(CB);
	}

	/**
	 * Creates a VBox that holds all debug modules
	 */
	private void createDebugVBox() {
		VBoxCreator VBC = new VBoxCreator(root);
		debugVBox = VBC.createVBox(AppConstants.VBOX_SPACING, AppConstants.DEBUG_LABEL_X_POS, AppConstants.DEBUG_LABEL_Y_POS);
	}

	/**
	 * Creates the label that indicates debug mode
	 */
	private void createDebugLabel() {
		LabelCreator LC = new LabelCreator(debugVBox);
		debugLabel = LC.createLabel("DEBUG MODE ON", AppConstants.DEBUG_LABEL_FONT_SIZE, Color.RED);
		debugLabel.setVisible(false);
	}

	/**
	 * Creates a button that steps through commands
	 */
	private void createDebugStepIntoButton() {
		ButtonCreator BC = new ButtonCreator(debugVBox);
		debugStepIntoButton = BC.createButton("Step Into Next Command");
		debugStepIntoButton.setVisible(false);
		activateStepIntoButton(debugStepIntoButton);
	}

	/**
	 * Creates a button that steps over commands
	 */
	private void createDebugStepOverButton() {
		ButtonCreator BC = new ButtonCreator(debugVBox);
		debugStepOverButton = BC.createButton("Step Over Next Command");
		debugStepOverButton.setVisible(false);
		activateStepOverButton(debugStepOverButton);
	}

	/**
	 * Creates a label that displays whenever an animation is running
	 */
	private void createRunningStatusLabel() {
		LabelCreator LC = new LabelCreator(root);
		runningStatusLabel = LC.createLabel("Running!", AppConstants.RUNNING_STATUS_LABEL_X_POS, AppConstants.RUNNING_STATUS_LABEL_Y_POS, AppConstants.TITLE_LABEL_FONT_SIZE, Color.RED);
		runningStatusLabel.setVisible(false);
	}

	/**
	 * Creates the text field where the user enters code
	 */
	private void createTextField(){
		myTextField = new CommandsTextField(root).createTextField();
		activateTextField(myTextField.getTextField());
	}
	
	/**
	 * Creates the label checkbox for activating the turtles' labels
	 */
	private void createLabelsCheckBox(){
		CheckBoxCreator cb = new CheckBoxCreator(mySelectorsVBox);
		labelsCheckBox = cb.createCheckBox("Toggle Turtle IDs");
		activateCheckBoxCreator(labelsCheckBox);
	}

	/**
	 * Creates all the lists for each of the variables and commans
	 */
	private void createListViews(){
		myCommandsList = new ListViewPreviousCommands(myListsVBox);
		myCommandsList.create();
		myVariablesList = new ListViewSLOGOVariables(myListsVBox);
		myVariablesList.create();
		myUserVariablesList = new ListViewUserVariables(myListsVBox);
		myUserVariablesList.create();
		myUserCommandsList = new ListViewUserCommands(myListsVBox);
		myUserCommandsList.create();
		addToCertainList(myUserCommands, myUserCommandsList, "OnClick");
	}

	/**
	 * Creates a VBox for all of the selectors
	 */
	private void createSelectorVBox(){
		VBoxCreator VBC = new VBoxCreator(root);
		mySelectorsVBox  = VBC.createVBoxScrollable(AppConstants.VBOX_SPACING);
		moduleScrollPane.setContent(mySelectorsVBox);
	}

	/**
	 * Creates a vBox that stores all the lists
	 */
	private void createListVBox() {
		VBoxCreator VBC = new VBoxCreator(root);
		myListsVBox = VBC.createVBoxScrollable(AppConstants.VBOX_SPACING);
		listScrollPane.setContent(myListsVBox);
	}

	/**
	 * Creates all of the selectors for the application
	 */
	private void createSelectors() {

		myBackgroundColorSelector = new BackgroundColorSelector(mySelectorsVBox);
		myBackgroundColorSelector.create(root, myGraphicsContext, myView);

		myPathColorSelector = new PathColorSelector(mySelectorsVBox);
		myPathColorSelector.create(root, myTurtleList.get(0), myView);

		PathTextureSelector pathTexture = new PathTextureSelector(mySelectorsVBox);
		pathTexture.create(root, myTurtleList.get(0));

		myTurtleSelector = new TurtleImageSelector(mySelectorsVBox);
		myTurtleSelector.create(root, myTurtleList.get(0), myView);
	}

	/**
	 * Creates the turtle canvas
	 */
	private void activateTurtleCanvas(){
		myCanvas.getCanvas().setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				if(myUserCommandsList.getItems().contains("OnClick")){
					myView.sendCommandToBackend("SetPosition " + (event.getX() - AppConstants.INITIAL_TURTLE_X_POS + 10) + " " + (event.getY() - AppConstants.INITIAL_TURTLE_Y_POS + 75));
				}
			}
		});
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

	/**
	 * Creates an event handler that plays the animation whenever the event is fired
	 * @param btn The button that fires the event
	 */
	public void activatePlayButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!isInDebugMode) myView.sendCommandToBackend();
				else new MessageBox("Currently in Debug Mode.\n Cannot run animation.");
			}
		});
	}

	/**
	 * Creates an event handler that pauses animation when fired
	 * @param btn The button the fires the event
	 */
	public void activatePauseButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myView.pauseAnimation();
			}
		});
	}

	/**
	 * Creates an event handler that resets the animation when fired
	 * @param btn The button the fires the event
	 */
	public void activateResetButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//UNTESTED
				myView.resetAnimation();
			}
		});
	}

	/**
	 * Lets the help button navigate to a help website
	 * @param btn: The help button
	 */
	public void activateHelpButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				HTMLHelpPage help = new HTMLHelpPage(AppConstants.HELP_URL);
				help.displayPage();
			}
		});
	}

	/**
	 * Creates an event handler that shows and hides the debug modules when fired
	 * @param cb The checkbox that fires the event
	 */
	public void activateDebugCB(CheckBox cb){
		cb.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				if(cb.isSelected()) {
					debugLabel.setVisible(true);
					debugStepIntoButton.setVisible(true);
					debugStepOverButton.setVisible(true);
					isInDebugMode = true;
				}
				else{
					debugLabel.setVisible(false);
					debugStepIntoButton.setVisible(false);
					debugStepOverButton.setVisible(false);
					isInDebugMode = false;
				}
			}
		});
	}

	/**
	 * Lets the load button load a configuration file
	 * 
	 * @param btn The load button
	 */
	public void activateLoadButton(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				try {
					ParameterDistributor PD = configReader.readFile();

					Map<String, Integer> Variables = PD.getVariableMap();
					Map<String, Color> Colors = PD.getColorMap();
					int numOfTurtles = PD.getNumOfTurtles();

					clearCertainList(myUserVariables, myUserVariablesList);
					for (String s: Variables.keySet()) {
						addToCertainList(myUserVariables, myUserVariablesList, s +  " = " + Variables.get(s));
					}

					new MessageBox("Read from config file successful!");

				} catch (IOException e) {
					new MessageBox("ERROR!! Read from config unsuccessful!");
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Lets the save button save to a configuration file
	 * 
	 * @param btn The save button
	 */
	public void activateSaveButton(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				//STILL IN TESTING PHASE
				ConfigHashMapCreator CHMC = new ConfigHashMapCreator();

				Map<String, String> variableMap = new HashMap<String, String>();
				for (String str: myUserVariablesList.getItems()) {
					variableMap.put(str.substring(0, str.indexOf(' ')), str.substring(str.indexOf('=') + 2));
					System.out.println(str.substring(0, str.indexOf(' ')));
					System.out.println(str.substring(str.indexOf('=') + 2));
				}
				CHMC.setVariableMap(variableMap);
				CHMC.setNumOfTurtles(13);

				configWriter.writeToTextFile(CHMC.getConfigHashMap());

				new MessageBox("Write to config file successful!");
			}
		});
	}

	/**
	 * Creates an event handler that creates a new window when fired
	 * @param btn The button that fires the event
	 */
	public void activateExtraWorkspaceButton(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				Stage newStage = new Stage();
				Main main = new Main();
				main.start(newStage);
			}
		});
	}

	/**
	 * Creates an event handler that steps through commands when fired
	 * @param btn The button that fires the event
	 */
	public void activateStepIntoButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				myView.stepIntoCommand();
				stepThroughCommandsHistory(0);
			}
		});
	}

	/**
	 * Creates an event handler that steps over commands when fired
	 * @param btn The button that fires the event
	 */
	public void activateStepOverButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				myView.stepOverCommand();
				stepThroughCommandsHistory(1);
			}
		});
	}


	/**
	 * Creates an event handler that records the text in the text box when fired
	 * @param TF The text field that fires the event
	 */
	public void activateTextField(TextField TF){
		TF.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				addToCertainList(myCommands, myCommandsList,  myTextField.getText());
				myView.recordCommand(myTextField.getText());
				myTextField.setText("");
			}
		});
	}

	/**
	 * Creates an event handler that generates a new turtle when fired
	 * @param btn The button that fires the event
	 */
	private void activateNewTurtleButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				List<Turtle> l = (List<Turtle>) getActiveTurtles();
				String s = "";
				for(Turtle t : getActiveTurtles()){ 
					s += t.getId() + " ";
				}
				myView.sendCommandToBackend("Tell [ " + s + " " + totalTurtles + " ]");
				totalTurtles += 1;
			}
		});	    

	}

	/**
	 * Activates an event handler that opens up a file directory when fired
	 * @param btn The button that fires the event
	 */
	private void activateTurtleImageLoaderButton(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				ImageLoader IL = new ImageLoader(myTurtleList, totalUserImages);
				IL.chooseNewImage(stage, myTurtleSelector);
				totalUserImages += 1;
			}
		});
	}
	
	/**
	 * Activates an event handler that creates labels for the turtles when fired
	 * @param cb The checkbox that fires the event
	 */
	private void activateCheckBoxCreator(CheckBox cb){
		cb.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				if(labelsCheckBox.isSelected()){
					for(Turtle t : myTurtleList){
						if(root.getChildren().contains(t.getImage())){
							t.addLabel(root);
						}
					}
				}
				else{
					for(Turtle t : myTurtleList){
						t.removeLabel(root);
					}
				}
			}
		});
	}
	
	/**
	 * Creates an event handler that increases or decreases stroke width based on what button is pressed
	 * @param incbtn The button that increases width
	 * @param decbtn The button that decreases width
	 * @param view The view
	 */
	private void activateIncreaseDecreaseButtons(Button incbtn, Button decbtn, View view){
		incbtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				Turtle t = getActiveTurtles().get(0);
				t.setThickness(t.getThickness() + 1);
				view.sendCommandToBackend("SetPenSize " + t.getThickness());
			}
		});
		decbtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				Turtle t = getActiveTurtles().get(0);
				t.setThickness(t.getThickness() + 1);
				view.sendCommandToBackend("SetPenSize " + t.getThickness());
			}
		});
	}

	/**
	 * Activates key events that move the turtle when fired
	 */
	private void activateKeyEvents() {
		root.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.A) {
					myView.sendCommandToBackend("Right 10");
				}
				if (event.getCode() == KeyCode.D) {
					myView.sendCommandToBackend("Left 10");
				}
				if (event.getCode() == KeyCode.W) {
					myView.sendCommandToBackend("Forward 10");		
				}
				if (event.getCode() == KeyCode.S) {
					myView.sendCommandToBackend("Backward 10");
				}
			}
		});
	}

	/**
	 * Adds values to a list
	 * @param currentList The observable list
	 * @param listView The physical list interface
	 * @param input The values that needs to be added to the list
	 */
	private void addToCertainList(ObservableList<String> currentList, ListViewAllSLOGO listView, String input) {
		currentList.add(input);
		listView.setItems(currentList);
	}

	/**
	 * Clears all values from a list
	 * @param currentList The observable list
	 * @param listView The physical list interface
	 */
	private void clearCertainList(ObservableList<String> currentList, ListViewAllSLOGO listView) {
		currentList.clear();
		listView.setItems(currentList);
	}

	/**
	 * Steps through or over commands, depending on the offset
	 * @param offset The offset of stepping
	 */
	public void stepThroughCommandsHistory(int offset) {
		if(commandsHistoryCounter <= myCommands.size()-1) {
			myCommands.add(commandsHistoryCounter + offset, "(Executed) " + myCommands.get(commandsHistoryCounter + offset));
			myCommands.remove(commandsHistoryCounter + offset + 1);
			myCommandsList.setItems(myCommands);
			commandsHistoryCounter = commandsHistoryCounter + offset + 1;
		}
	}

	/**
	 * Sets the value of the command history counter
	 * @param value The value
	 */
	public void setCommandsHistoryCounter(int value) {
		commandsHistoryCounter = value;
	}

	/**
	 * Gets the value of the command history counter
	 * @return The command history counter
	 */
	public int getCommandsHistoryCounter() {
		return commandsHistoryCounter;
	}

	/**
	 * Resets the command history counter to an old state
	 * @param oldCounter The old state of the command history counter
	 */
	public void resetCommandsHistoryList(int oldCounter) {
		while(commandsHistoryCounter <= myCommands.size() - 1) {
			if(myCommands.get(commandsHistoryCounter).charAt(0) == '(' ) {
				myCommands.add(commandsHistoryCounter, myCommands.get(commandsHistoryCounter).substring(myCommands.get(commandsHistoryCounter).indexOf(' ') + 1));
				myCommands.remove(commandsHistoryCounter + 1);
			}
			commandsHistoryCounter++;
		}
		myCommandsList.setItems(myCommands);
		commandsHistoryCounter = oldCounter;
	}

	/**
	 * Returns the current active turtle
	 * @return The turtle
	 */
	public List<Turtle> getActiveTurtles() {
		List<Turtle> activeTurtles = new ArrayList<Turtle>();
		for(Turtle t : myTurtleList){
			if(t.isActive()){
				activeTurtles.add(t);
			}
		}
		return activeTurtles;
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

	/**
	 * Gets the value of of the animation slider
	 * @return The slider value
	 */
	public double getAnimationSliderValue() {
		return animationSlider.getValue();
	}

	/**
	 * Sets the values of the variables in the list
	 * @param x The turtle's x position
	 * @param y The turtle's y position
	 * @param o The orientation of the turtle
	 * @param b The boolean representing the pen down or pen up
	 * @param t The thickness of the pen
	 */
	protected void setListViewVariables(double x, double y, double o, boolean b, int t){
		myVariables.clear();
		myVariables.add("Turtle X Position:     " + String.format("%.5g%n", x));
		myVariables.add("Turtle Y Position:     " + String.format("%.5g%n", y));
		myVariables.add("Turtle Heading:        " + String.format("%.5g%n", o));
		myVariables.add("Pen Down?:             " + b);
		myVariables.add("Pen Thickness:         " + t);
		myVariablesList.setItems(myVariables);
	}

	/**
	 * Makes the running status label visible
	 */
	public void turnOnRunningStatusLabel() {
		runningStatusLabel.setVisible(true);
	}

	/**
	 * Makes the running status label invisible
	 */
	public void turnOffRunningStatusLabel() {
		runningStatusLabel.setVisible(false);
	}

	/**
	 * Gets the turtles image
	 * @return The turtle's image
	 */
	protected TurtleImageSelector getTurtleSelector(){
		return myTurtleSelector;
	}

	/**
	 * Gets the line color selector
	 * @return The color selector for the lines
	 */
	protected PathColorSelector getPathColorSelector () {
		return myPathColorSelector;
	}

	/**
	 * Gets the background color selector
	 * @return The background color selector
	 */
	protected BackgroundColorSelector getBackgroundColorSelector () {
		return myBackgroundColorSelector;
	}

	/**
	 * Gets the list of turtles
	 * @return The list of turtles
	 */
	public List<Turtle> getTurtleList(){
		return myTurtleList;
	}
	/**
	 * Used for adding variables the user dedines to the display
	 * @param environment the string representing the variables
	 */
    protected void setUserVariable (String environment) {
        myUserVariablesList.getItems().add(environment);
        
    }
    
    /**
     * Clears the UserVariables List - needed so UI does not have duplicates of variables
     */
    public void clearUserVariables(){
        myUserVariablesList.getItems().clear();
    }
}

