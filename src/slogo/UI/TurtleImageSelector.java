package slogo.UI;

import java.util.ArrayList;
import java.util.List;
import slogo.View;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * 10/13/2014
 * 
 * Version 1
 * 
 * @author Michael Deng
 *
 */
public class TurtleImageSelector {
    
    private VBox mySelectorsVBox;
    private List<String> myPossibleImages;
    private ComboBox<String> mySelector;
    
    /**
     * The constructor
     * @param selectorsVBox The vBox the selector will belong to
     */
    public TurtleImageSelector(VBox selectorsVBox){
        mySelectorsVBox = selectorsVBox;    
        myPossibleImages = new ArrayList<String>();
    }
    
    /**
     * Creates a VBox 
     * @param root The group the VBox will belong to
     * @param turtle The turtle 
     * @param view The view
     */
    protected void create(Group root, Turtle turtle, View view){
        myPossibleImages.addAll(turtle.getShapesMap().keySet());
        SelectorCreator sc = new SelectorCreator(root);
        sc.setUpSelector("Turtle Image", AppConstants.SELECTOR_WIDTH, AppConstants.SELECTOR_HEIGHT, AppConstants.SELECTOR_FONT_SIZE, myPossibleImages);
        mySelector = sc.getSelector();
        mySelector.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                        if(mySelector.getValue() != null){
                            view.sendCommandToBackend("SetShape " + (mySelector.getItems().indexOf(mySelector.getValue()) + 1));
                        }
            }
        });
        VBox selectorWithLabel = sc.createSelectorWithLabel("Select a Turtle Image", AppConstants.LABEL_FONT_SIZE, Color.BLACK);
        mySelectorsVBox.getChildren().add(selectorWithLabel);
    }
    
    /**
     * Populates the map of turtle images
     * @param s The new key
     * @param iv The image view
     * @param turtleList The list of turtles
     */
    protected void updateMap(String s, ImageView iv, List<Turtle> turtleList){
        mySelector.getItems().add(s);
        for(Turtle t : turtleList){
            t.getShapesMap().put(s, iv);
        }
    }

    /**
     * Gets the items from the list of images
     * @return The list of images
     */
    public ObservableList<String> getItems () {
        return mySelector.getItems();
    }
    
    /**
     * Sets the value of the selector
     * @param s The value
     */
    public void setValue(String s){
        mySelector.setValue(s);
    }
    
    /**
     * Gets the value of the selector
     * @return The value
     */
    public String getValue(){
        return mySelector.getValue();
    }
}