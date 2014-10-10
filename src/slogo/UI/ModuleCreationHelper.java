package slogo.UI;

import java.lang.reflect.InvocationTargetException;
import slogo.View;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * October 8th, 2014
 * 
 * Version 1
 * 
 * @author Nick Widmaier
 * @author Michael Deng 
 *
 */
public class ModuleCreationHelper {

	private View myView;
	private Group root;
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
		createListViews();
		createSelectors();
	}

	private void createFirstButtonRow() {
		firstButtonRow = new HBox(3);
		firstButtonRow.setLayoutX(AppConstants.FIRST_ROW_BUTTON_HBOX_X_POS);
		firstButtonRow.setLayoutY(AppConstants.FIRST_ROW_BUTTON_HBOX_Y_POS);
		root.getChildren().add(firstButtonRow);
	}

	/**
	 * Creates the title for our application
	 */
	private void createTitle(){
		LabelCreator LC = new LabelCreator(root);
		Label label = LC.createLabel("SLOGO!!!", 75, 0, AppConstants.TITLE_LABEL_FONT_SIZE, Color.BLACK);
	}

	/**
	 * Creates the canvas on which the turtle runs
	 */
	private void createTurtleCanvas() {
		myCanvas = new TurtleCanvas(root);
		myGraphicsContext = myCanvas.getGraphicsContext();
	}

	private void createTurtle(){
		myTurtle = new Turtle("Circle", 275, 275);
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
		activateHelpButton(btn);    
	}

	private void createTextField(){
	    myTextField = new CommandsTextField(root).createTextField();
	    activateTextField(myTextField.getTextField());
	}

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
        
        private void createSelectors(){
            mySelectorsVBox = new VBox(5);
            TurtleImageSelector turtleSelect = new TurtleImageSelector(mySelectorsVBox);
            turtleSelect.create(myTurtle, root);
            BackgroundColorSelector backgroundSelect = new BackgroundColorSelector(mySelectorsVBox);
            backgroundSelect.create(root, myGraphicsContext);
            PathColorSelector pathSelect = new PathColorSelector(mySelectorsVBox);
            pathSelect.create(root, myGraphicsContext);
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
				View v = new View();
				v.init(root, myCanvas.getCanvas(), myTurtle);
				try {
					v.executeCommand("f");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				v.executeCommand("fd");
			}
		});
	}
	
	public void activateHelpButton(Button btn){
	    btn.setOnAction(new EventHandler<ActionEvent>(){
	        @Override
	        public void handle(ActionEvent event){
	            HTMLHelpPage help = new HTMLHelpPage(AppConstants.HELP_URL);
	            help.displayPage();
	        }
	    });
	}

	public void activateTextField(TextField TF){
	    TF.setOnAction(new EventHandler<ActionEvent>(){
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
	}
	
	public Turtle getTurtle() {
		return myTurtle;
	}

	public Canvas getCanvas() {
		return myCanvas.getCanvas();
	}
	
	public void setView(View view) {
		this.myView = view;
	}
}

