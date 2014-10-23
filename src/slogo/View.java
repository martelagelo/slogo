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
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.util.ITurtleStatus;

/**
 * October 5th, 2014
 * 
 * Version 1
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
		this.MCH = MCH;
		runner = new MethodRunner(root, MCH.getCanvas(), MCH.getTurtle(), pathList, MCH);
		backend = new Backend();
	}

	/**
	 * Stores the command in the queue
	 * @param command The input from the user
	 */
	public void recordCommand(String command) {
		commandQueue.add(command);
	}

	public void sendCommandToBackend() {
		if(animationTimeline != null && animationTimeline.getStatus() == Status.PAUSED) {
			MCH.toggleRunningStatusLabel();
			animationTimeline.play();
		}
		else if (!commandQueue.isEmpty()) {
			MCH.toggleRunningStatusLabel();
			animationTimeline = new Timeline();
			animationTimeline.setCycleCount(Timeline.INDEFINITE);

			animationTimeline.getKeyFrames().add(
					new KeyFrame(Duration.millis(1000 / MCH.getAnimationSliderValue()),
							new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {

							IExecutionContext result = backend.execute(commandQueue.poll());
							executeCommand(result);
							MCH.stepThroughCommandsHistory(0);

							if (commandQueue.isEmpty()) {
								animationTimeline.stop();
								animationTimeline = null;
								MCH.toggleRunningStatusLabel();
							}
						}
					}));
			animationTimeline.play();
		}
	}

	public void pauseAnimation() {
		if (animationTimeline != null) {
			animationTimeline.pause();
			MCH.toggleRunningStatusLabel();
		}
	}


	/**
	 * Sends a command to the back-end
	 * @param command The code written by the user to be computed
	 */
	public void sendCommandToBackend(String command) {
		IExecutionContext result = backend.execute(command);
		executeCommand(result);
	}

	public void stepIntoCommand() {
		if (!commandQueue.isEmpty()) {
			IExecutionContext result = backend.execute(commandQueue.poll());
			executeCommand(result);
		}
		else {
			new MessageBox("No more commands to step through");
		}
	}

	public void stepOverCommand() {
		commandQueue.poll();
		if (!(commandQueue.peek() == null)) {
			IExecutionContext result = backend.execute(commandQueue.poll());
			executeCommand(result);
		}
		else {
			new MessageBox("No more commands after the next");
		}
	}

	/**
	 * Executes the command returned from the back-end
	 * @param str 
	 */
	private void executeTurtleCommands(ITurtleStatus iTurtleStatus){
		runner.setTurtleStatus(iTurtleStatus);
		runner.changeTurtle();
	}

	private void executeEnvironmentCommands(String var) {
		runner.setEnvironment(var);
		runner.changeEnvironment();
	}

	private void executeCommand(IExecutionContext result) {
		for(String k: result.turtles().keySet()) {
			executeTurtleCommands(result.turtles().get(k));
		}
		for(String k: result.environment().keySet()) {
			executeEnvironmentCommands(result.environment().get("returnValue"));
		}
	}

	/**
	 * Creates and displays an error pop-up
	 */
	public void error(String message) {
		new MessageBox(message);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
