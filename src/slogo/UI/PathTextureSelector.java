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

    public PathTextureSelector(VBox vbox){
        myVbox = vbox;
    }
    
    protected void create(Group root, Turtle t){
    	List<String> possibleTextures = new ArrayList<String>();
    	populateLineTextureList(possibleTextures);
        SelectorCreator sc = new SelectorCreator(root);
        sc.setUpSelector("Path Texture", AppConstants.SELECTOR_WIDTH, AppConstants.SELECTOR_HEIGHT, AppConstants.SELECTOR_FONT_SIZE, possibleTextures);
        final ComboBox<String> pathTexture = sc.getSelector();
        pathTexture.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                    if(pathTexture.getValue().equals("Dashed")){
                        t.dashed(true);
                    }
                    if(pathTexture.getValue().equals("Bold")){
                        t.bold(true);
                    }
            }
        });
        VBox selectorWithLabel = sc.createSelectorWithLabel("Select a Path Texture", AppConstants.LABEL_FONT_SIZE, Color.BLACK);
        myVbox.getChildren().addAll(selectorWithLabel);
    }
    
    
  //TODO: List needs to be populated with different line textures
    private void populateLineTextureList(List list) {
    	list.add("Dotted");
    	list.add("Bold");
    	list.add("Dashed");
    }
}
