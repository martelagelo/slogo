package slogo.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CommandsTextField {

    private Group myRoot;
    private TextField myTextField;

    public CommandsTextField(Group root){
        myRoot = root;
        myTextField = new TextField();
    }
    
    protected CommandsTextField createTextField(){
        LabelCreator LC = new LabelCreator(myRoot);
        Label l = LC.createLabel("Enter your commands here! Hit Enter to see them displayed!", AppConstants.TITLE_LABEL_FONT_SIZE/3, Color.BLACK);
        myTextField.setPromptText("Enter Command");
        myTextField.setPrefHeight(20);
        myTextField.setPrefWidth(400);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(l, myTextField);
        vbox.setLayoutX(75);
        vbox.setLayoutY(500);
        myRoot.getChildren().add(vbox);
        return this;
    }
    
    protected TextField getTextField(){
        return myTextField;
    }
    
    protected String getText(){
        return myTextField.getText();
    }
    
    protected void setText(String s){
        myTextField.setText(s);
    }
}