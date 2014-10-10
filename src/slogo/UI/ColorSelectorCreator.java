package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorSelectorCreator {
        
        
        private ColorPicker myColorSelector;
        private Group myRoot;
        
        public ColorSelectorCreator(Group root){
            myColorSelector = new ColorPicker();
            myRoot = root;
        }
        
        protected ColorPicker getSelector(){
            return myColorSelector;
        }
        
        protected void setUpSelector(String prompt, int width, int height, double d, Color c){
            myColorSelector = new ColorPicker(c);
            myColorSelector.setPromptText(prompt);
            myColorSelector.setPrefSize(width, height);
            myColorSelector.setStyle("-fx-font-size: " + d + "em;");
            myRoot.getChildren().add(myColorSelector);
        }
        
        protected VBox createSelectorWithLabel(String s, int fontsize, Color color){
            VBox selectorWithLabel = new VBox();
            LabelCreator lc = new LabelCreator(selectorWithLabel);
            Label l = lc.createLabel(s, fontsize, color);
            selectorWithLabel.getChildren().addAll(myColorSelector);
            return selectorWithLabel;
            
        }
}
