package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class TextFieldCreator {

    private Group root;
    private HBox hBox;

    public TextFieldCreator (Group root) {
        this.root = root;
    }

    public TextFieldCreator (HBox hBox) {
        this.hBox = hBox;
    }

    public TextField createTextField () {
        TextField TF = new TextField();

        TF.setMinWidth(700.0);
        TF.setPromptText("Enter code here");
        if (root != null) {
            root.getChildren().add(TF);
        }
        else {
            hBox.getChildren().add(TF);
        }
        return TF;
    }
}
