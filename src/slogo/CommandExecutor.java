package slogo;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;

/**
 * This class is to be interact by View.class
 * 
 * The View.class will call methods in here based on the key command that is returned from the backend
 * These methods with affect the front end in whatever way they are designed to
 * 
 * @author Michael
 *
 */
public class CommandExecutor {
	
	//All code below written by Michael Deng for testing. Feel free to delete. 

	private List<Line> lines = new ArrayList<Line>();
	private String type;
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setList(List<Line> lines) {
		this.lines = lines;
	}
	
	public List getList() {
		return lines;
	}
}
