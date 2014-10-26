package slogo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.UI.MessageBox;
import slogo.UI.MethodRunner;
import slogo.UI.ModuleCreationHelper;
import slogo.UI.Turtle;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.impl.Backend;
import slogo.backend.impl.InitializationException;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.util.ITurtleStatus;

/**
 * This class connects our user interface to the back end
 * Our modulecreationhelper sets a view which allows the user controls
 * to send commands to the back end when they are clicked on
 * the view then sends these to the backend, which processes them and returns
 * them for the method runner to use to update all the user interface commands
 * 
 * @author Michael Deng
 * @author Nick Widmaier
 * @author Michael Ren
 * @author Eric Chen
 *
 */

public class View implements IView{

	private MethodRunner runner;
	private IModel backend;	
	private List<Line> pathList;
	private Queue<String> commandQueue;
	private Queue<String> immediateHistoryQueue;
	private int recordCommandHistoryCounter;
	private Timeline animationTimeline;
	private ModuleCreationHelper MCH;

	/**
	 * Initializes the View 
	 * 
	 * @param root The Group all the modules are placed on
	 * @param canvas The canvas on which the turtle is drawn
	 * @param turtle The moving object on the canvas
	 */
	public void init(Group root, ModuleCreationHelper MCH) {
		pathList = new ArrayList<Line>();
		commandQueue = new LinkedList<String>();
		immediateHistoryQueue = new LinkedList<String>();
		this.MCH = MCH;
		try {
			backend = new Backend();
		} catch (InitializationException e) {
			new MessageBox(e.getMessage());
		}
	}
	
	/**
	 * initialize the runner so that it can use update all the modules correctly 
	 * @param root
	 * @param MCH
	 */
	public void initRunner(Group root, ModuleCreationHelper MCH){
	      runner = new MethodRunner(root, MCH.getCanvas(), MCH.getTurtleList(), pathList, MCH);
	}

	/**
	 * Stores the command in the queue
	 * @param command The input from the user
	 */
	public void recordCommand(String command) {
		commandQueue.add(command);
		immediateHistoryQueue.add(command);
	}

	/**
	 * sends the string to the backend for evaluation
	 * will do so based on the animation timeline - if active, it will evaluate the command
	 * and then display the animation
	 */
	public void sendCommandToBackend() {
		if(animationTimeline != null && animationTimeline.getStatus() == Status.PAUSED) {
			MCH.turnOnRunningStatusLabel();
			animationTimeline.play();
		}
		else if (!commandQueue.isEmpty()) {
			MCH.turnOnRunningStatusLabel();

			recordCommandHistoryCounter = MCH.getCommandsHistoryCounter();
			//setting up the timeline
			animationTimeline = new Timeline();
			animationTimeline.setCycleCount(Timeline.INDEFINITE);

			animationTimeline.getKeyFrames().add(
					new KeyFrame(Duration.millis(1000 / MCH.getAnimationSliderValue()),
							new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {

							IExecutionContext result = null;
							//try to send to backend and obtain result
							try {
								result = backend.execute(commandQueue.poll());
							} catch (ExecutionException e) {
								new MessageBox(e.getMessage());
							}
							//execute result
							executeCommand(result);
							MCH.stepThroughCommandsHistory(0);
							//stop animation once no more commands in the list
							if (commandQueue.isEmpty()) {
								animationTimeline.stop();
								animationTimeline = null;
								MCH.turnOffRunningStatusLabel();
								immediateHistoryQueue.clear();
								recordCommandHistoryCounter = 0;
							}
						}
					}));
			animationTimeline.play();
		}
	}
	
	/**
	 * resets the animation, stopping it from playing 
	 * commands list is reset and user would have to enter more commands to run it
	 */
	public void resetAnimation() {
		MCH.turnOffRunningStatusLabel();
		animationTimeline.stop();
		animationTimeline = null;

		Queue<String> queue = new LinkedList<String>();
		while (!immediateHistoryQueue.isEmpty()) {
			commandQueue.add(immediateHistoryQueue.peek());
			queue.add(immediateHistoryQueue.poll());
		}
		while (!queue.isEmpty()) {
			immediateHistoryQueue.add(queue.poll());
		}

		MCH.setCommandsHistoryCounter(recordCommandHistoryCounter);
		MCH.resetCommandsHistoryList(recordCommandHistoryCounter);
	}

	/**
	 * pauses the animation while in the middle of running through commands
	 * can be resumed to run through the rest of the commands
	 */
	public void pauseAnimation() {
		if (animationTimeline != null) {
			animationTimeline.pause();
			MCH.turnOffRunningStatusLabel();
		}
	}


	/**
	 * Sends a command to the back-end
	 * @param command The code written by the user to be computed
	 */
	public void sendCommandToBackend(String command) {
		IExecutionContext result = null;
		try {
			result = backend.execute(command);
		} catch (ExecutionException e) {
			//FIXME handle if an error occurs in execution; 
			// print this out to a UI widget maybe?
			e.printStackTrace();
		}
		executeCommand(result);
	}
	
	/**
	 * steps into a command for debugging
	 * easy to go through one command at a time
	 */
	public void stepIntoCommand() {
		if (!commandQueue.isEmpty()) {
			IExecutionContext result = null;
			try {
				result = backend.execute(commandQueue.poll());
			} catch (ExecutionException e) {
				new MessageBox(e.getMessage());
			}
			executeCommand(result);
		}
		else {
			new MessageBox("No more commands to step through");
		}
	}
	
	/**
	 * steps over a particular command, also for debugging
	 */
	public void stepOverCommand() {
		commandQueue.poll();
		if (!(commandQueue.peek() == null)) {
			IExecutionContext result = null;
			try {
				result = backend.execute(commandQueue.poll());
			} catch (ExecutionException e) {
				new MessageBox(e.getMessage());
			}
			executeCommand(result);
		}
		else {
			new MessageBox("No more commands after the next");
		}
	}

	/**
	 * Executes the command based off status returned from the backend
	 * @param str 
	 * @param iTurtleStatus the status of the turtle from the backend
	 */
	private void executeTurtleCommands(String k, ITurtleStatus iTurtleStatus){
		System.out.println("This is k: " + k);
	        runner.setTurtleStatus(k, iTurtleStatus);
		runner.changeTurtle();
	}
	
	/**
	 * Executes the command based off the status returned from backend
	 * to update the variables
	 * @param var string representing variables
	 */
	private void executeEnvironmentCommands(String var) {
		runner.setEnvironment(var);
		runner.changeEnvironment();
	}
	
	/**
	 * goes through the returns from the backend and executes the commands for each turtle, as
	 * well as the environment
	 * @param result the context returned by the backend
	 */
	private void executeCommand(IExecutionContext result) {
		for(String k: result.turtles().keySet()) {
			executeTurtleCommands(k, result.turtles().get(k));
		}
		for(String k: result.environment().keySet()) {
			executeEnvironmentCommands(result.environment().get("returnValue"));
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
