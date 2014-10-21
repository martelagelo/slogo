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
import javafx.scene.Group;
import javafx.scene.Node;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	private TurtleCanvas myCanvas;
	private CommandsTextField myTextField;
	private List<Turtle> myTurtleList;
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

	private Map<String, Node> myImagesMap;
	private int totalUserImages;
	private TurtleImageSelector myTurtleSelector;

	private List<Line> myGridLines;


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
		createNewTurtleButton();
		createTurtleImageLoaderButton();
		createLoadButton();
		createSaveButton();
		createExtraWorkspaceButton();
		createGridCheckBox();
		activateKeyEvents();
	}

	/**
	 * Creates a checkBox for the Grid
	 */
	private void createGridCheckBox() {
		CheckBoxCreator cb = new CheckBoxCreator(mySelectorsVBox);
		CheckBox CB = cb.createCheckBox("Toggle Grid");
		myGridLines = new ArrayList<Line>();
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
		myTurtleList = new ArrayList<Turtle>();
		myTurtleList.add(new Turtle("Circle", AppConstants.INITIAL_TURTLE_X_POS, AppConstants.INITIAL_TURTLE_Y_POS));
		root.getChildren().add(myTurtleList.get(0).getImage());
	}

	/**
	 * 
	 */
	private void createPlayButton() {
		ButtonCreator BC = new ButtonCreator(firstButtonRow);
		Button btn = BC.createButton(new Image(getClass().getResourceAsStream("green-plain-play-button-icon-th.png")));
		activatePlayAppButton(btn);
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

	private void createTurtleImageLoaderButton(){
		ButtonCreator BC = new ButtonCreator(mySelectorsVBox);
		Button btn = BC.createButton("Load New Turtle Image");
		activateTurtleImageLoaderButton(btn);
	}

	/**
	 * Add another turtle to scene
	 */
	private void createNewTurtleButton(){
		ButtonCreator BC = new ButtonCreator(mySelectorsVBox);
		Button btn = BC.createButton("Add turtle");
		activateNewTurtleButton(btn);
	}

	//NEEDS BACKEND FUNCTIONALITY
	private void activateNewTurtleButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Turtle newTurtle = new Turtle("Circle", AppConstants.INITIAL_TURTLE_X_POS, AppConstants.INITIAL_TURTLE_Y_POS);
				root.getChildren().add(newTurtle.getImage());
				myTurtleList.add(newTurtle);
			}
		});	    

	}

	//NEEDS BACKEND FUNCTIONALITY
	private void activateTurtleImageLoaderButton(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Load in a New Turtle Image");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.jpg", "*.png"));
				File selectedFile = fileChooser.showOpenDialog(stage);
				if (selectedFile != null) {
					Image i = new Image((selectedFile.toURI().toString()), AppConstants.MAX_NEW_IMAGE_WIDTH, AppConstants.MAX_NEW_IMAGE_HEIGHT, true, true);
					myTurtleSelector.updateMap("User Image #" + totalUserImages, new ImageView(i), myTurtleList.get(0));
					totalUserImages +=1;
				}
				else{
					System.out.println("No File Selected");
				}
			}
		});
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

	private void createExtraWorkspaceButton() {
		ButtonCreator BC = new ButtonCreator(mySelectorsVBox);
		Button btn = BC.createButton("Load Extra Workspace");
		activateExtraWorkspaceButton(btn);
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
		mySelectorsVBox = VBC.createVBox(AppConstants.VBOX_SPACING, AppConstants.FIRST_ROW_BUTTON_HBOX_X_POS, AppConstants.FIRST_ROW_BUTTON_HBOX_Y_POS);
	}

	/**
	 * 
	 */
	private void createSelectors() {
		TurtleImageSelector turtleSelect = new TurtleImageSelector(mySelectorsVBox);
		turtleSelect.create(root, myTurtleList.get(0));
		myTurtleSelector = turtleSelect;

		BackgroundColorSelector backgroundSelect = new BackgroundColorSelector(mySelectorsVBox);
		backgroundSelect.create(root, myGraphicsContext);

		PathColorSelector pathSelect = new PathColorSelector(mySelectorsVBox);
		pathSelect.create(root, myGraphicsContext);

		PathTextureSelector pathTexture = new PathTextureSelector(mySelectorsVBox);
		pathTexture.create(root, myTurtleList.get(0));
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
	 * 
	 * @param btn
	 */
	public void activatePlayAppButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main main = new Main();
				main.start(stage);
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
						//NEEDS BACKEND FUNCTIONALITY
					}
					
					new MessageBox("Read from config file successful!");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
				
				//NEEDS BACKEND FUNCTIONALITY
			}
		});
	}

	/**
	 * 
	 * @param btn
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

	public void activateReferenceCB(CheckBox cb){
		cb.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				if(cb.isSelected()) {
					for(double x = myCanvas.getCanvas().getLayoutX(); x < myCanvas.getCanvas().getHeight() + myCanvas.getCanvas().getLayoutX(); x += AppConstants.GRIDLINES_SPACING){
						Line line = new Line(x, myCanvas.getCanvas().getLayoutY(), x, myCanvas.getCanvas().getLayoutY() + myCanvas.getCanvas().getHeight());
						line.setFill(Color.DARKGREY);

						Line line2 = new Line(myCanvas.getCanvas().getLayoutX(), x, myCanvas.getCanvas().getLayoutX() + myCanvas.getCanvas().getWidth(), x);
						line2.setFill(Color.DARKGREY);
						myGridLines.add(line);
						myGridLines.add(line2);
					}
					root.getChildren().addAll(myGridLines);
				}
				else{
					for(Line l : myGridLines){
						root.getChildren().remove(l);
					}
					myGridLines.clear();
				}
			}
		});
	}

	public void activateTextField(TextField TF){
		TF.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				addToCertainList(myCommands, myCommandsList,  myTextField.getText());
				myView.sendCommandToBackend(myTextField.getText());
				myTextField.setText("");
			}
		});
	}

	//NEEDS BACKEND FUNCTIONALITY
	private void activateKeyEvents() {
		root.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				for(Turtle t : myTurtleList){
					if(t.isActive()){
						if (event.getCode() == KeyCode.A) {
							t.moveTurtle(t.getXPos() - 10, t.getYPos());
						}
						if (event.getCode() == KeyCode.D) {
							t.moveTurtle(t.getXPos() + 10, t.getYPos());
						}
						if (event.getCode() == KeyCode.W) {
							t.moveTurtle(t.getXPos(), t.getYPos() - 10);
						}
						if (event.getCode() == KeyCode.S) {
							t.moveTurtle(t.getXPos(), t.getYPos() + 10);
						}
						if (event.getCode() == KeyCode.E) {
							t.incrementOrientation(10);
						}
						if (event.getCode() == KeyCode.Q) {
							t.incrementOrientation(-10);
						}
					}
					System.out.println(t.getXPos() +"  "+ t.getYPos());
					System.out.println(t.getImage().getLayoutX() + "  " + t.getImage().getLayoutY());
				}
			}
		});
	}
	
	
	private void addToCertainList(ObservableList<String> currentList, ListViewAllSLOGO listView, String input) {
		currentList.add(input);
		listView.setItems(currentList);
	}
	
	private void clearCertainList(ObservableList<String> currentList, ListViewAllSLOGO listView) {
		currentList.clear();
		listView.setItems(currentList);
	}
	

	/**
	 * Returns the current turtle
	 * @return The turtle
	 */
	public Turtle getTurtle() {
		for(Turtle t : myTurtleList){
			if(t.isActive()){
				return t;
			}
		}
		return null;
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

