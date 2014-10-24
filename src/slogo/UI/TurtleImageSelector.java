package slogo.UI;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TurtleImageSelector {
    
    private VBox mySelectorsVBox;
    private List<String> myPossibleImages;
    private ComboBox<String> mySelector;
    
    public TurtleImageSelector(VBox selectorsVBox){
        mySelectorsVBox = selectorsVBox;    
        myPossibleImages = new ArrayList<String>();
    }
    
    protected void create(Group root, List<Turtle> turtleList, PathColorSelector pc, PathTextureSelector pt){
        myPossibleImages.addAll(turtleList.get(0).getShapesMap().keySet());
        SelectorCreator sc = new SelectorCreator(root);
        sc.setUpSelector("Turtle Image", AppConstants.SELECTOR_WIDTH, AppConstants.SELECTOR_HEIGHT, AppConstants.SELECTOR_FONT_SIZE, myPossibleImages);
        mySelector = sc.getSelector();
        mySelector.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                for(Turtle turtle : turtleList){
                    if(turtle.isActive()){
                        if(mySelector.getValue() != null){
                            root.getChildren().remove(turtle.getImage());
                            turtle.setImage(mySelector.getValue());
                            root.getChildren().add(turtle.getImage());
                            activate(turtleList, turtle, pc, pt);
                        }
                    }
                }
            }
        });
        VBox selectorWithLabel = sc.createSelectorWithLabel("Select a Turtle Image", AppConstants.LABEL_FONT_SIZE, Color.BLACK);
        mySelectorsVBox.getChildren().add(selectorWithLabel);
    }
    
    protected void updateMap(String s, ImageView iv, Turtle t){
        mySelector.getItems().add(s);
        t.getShapesMap().put(s, iv);
    }
    
    private void activate (List<Turtle> turtleList, Turtle t, PathColorSelector pc, PathTextureSelector pt) {
        t.getImage().setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                    for(Turtle turtle : turtleList){
                            turtle.deactivate();
                    }
                    t.activate();
                    resetSelectors(t, pc, pt);
                    
            }
        });
        
    }
    
    protected void resetSelectors(Turtle t, PathColorSelector pc, PathTextureSelector pt){
        mySelector.setValue(t.getImageName());
        pc.resetPathColor(t);
        pt.resetLineTexture(t);
    }
}