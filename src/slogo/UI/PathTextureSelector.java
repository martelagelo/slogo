package slogo.UI;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PathTextureSelector {

	private VBox myVbox;
	private ComboBox<String> pathTexture;

    public PathTextureSelector(VBox vbox){
        myVbox = vbox;
    }
    
    protected void create(Group root, List<Turtle> turtleList){
    	List<String> possibleTextures = new ArrayList<String>();
    	populateLineTextureList(possibleTextures);
        SelectorCreator sc = new SelectorCreator(root);
        sc.setUpSelector("Path Texture", AppConstants.SELECTOR_WIDTH, AppConstants.SELECTOR_HEIGHT, AppConstants.SELECTOR_FONT_SIZE, possibleTextures);
        pathTexture = sc.getSelector();
        pathTexture.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                for(Turtle t : turtleList){
                    if(t.isActive()){
                        t.setLineProperty(pathTexture.getValue());
                    }
                }
            }
        });
        VBox selectorWithLabel = sc.createSelectorWithLabel("Select a Path Texture", AppConstants.LABEL_FONT_SIZE, Color.BLACK);
        myVbox.getChildren().addAll(selectorWithLabel);
    }
    
    
  //TODO: List needs to be populated with different line textures
    private void populateLineTextureList(List<String> list) {
    	list.add("None");
        list.add("Dotted");
    	list.add("Bold");
    	list.add("Dashed");
    }

    public void resetLineTexture (Turtle t) {
        pathTexture.setValue(t.getLineProperty());
        
    }
    
}
