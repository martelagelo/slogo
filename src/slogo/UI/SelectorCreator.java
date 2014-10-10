package slogo.UI;

import java.util.List;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SelectorCreator {
    
    
    private ComboBox<String> mySelector;
    private Group myRoot;
    
    public SelectorCreator(Group root, String prompt, int width, int height, double d, List<String> items ){
        mySelector = new ComboBox<String>();
        myRoot = root;
        mySelector.getItems().addAll(items);
        mySelector.setPromptText(prompt);
        mySelector.setPrefSize(width, height);
        mySelector.setStyle("-fx-font-size: " + d + "em;");
        myRoot.getChildren().add(mySelector);
    }
    
    public SelectorCreator(Group root, String prompt, List<String> items){
        this(root, prompt, 200, 40, .8, items);
    }
    
    protected ComboBox<String> getSelector(){
        return mySelector;
    }
    
    protected VBox createSelectorWithLabel(String s, int fontsize, Color color){
        VBox selectorWithLabel = new VBox();
        LabelCreator lc = new LabelCreator(selectorWithLabel);
        Label l = lc.createLabel(s, fontsize, color);
        selectorWithLabel.getChildren().addAll(mySelector);
        return selectorWithLabel;
        
    }
    
}
