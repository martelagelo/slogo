package slogo.UI;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * 10/14/2014
 *
 * Version 1
 *
 * @author Michael Deng
 *
 */
public class VBoxCreator {

    private Group root;
    private HBox hBox;

    /**
     * The Constructor
     *
     * @param root The group of the scene
     */
    public VBoxCreator (Group root) {
        this.root = root;
    }

    /**
     * The Constructor
     *
     * @param hBox An HBox
     */
    public VBoxCreator (HBox hBox) {
        this.hBox = hBox;
    }

    /**
     * Creates a VBox
     *
     * @param spacing The spacing between each module in the VBox
     * @param x_Coord The x pos of the VBox
     * @param y_Coord The y pos of the YBox
     * @return The newly created VBox
     */
    public VBox createVBox (int spacing, Integer x_Coord, Integer y_Coord) {
        VBox vBox = new VBox(spacing);
        vBox.setLayoutX(x_Coord);
        vBox.setLayoutY(y_Coord);
        if (root != null) {
            root.getChildren().add(vBox);
        }
        else {
            hBox.getChildren().add(vBox);
        }
        return vBox;
    }

    /**
     * Creates a VBox
     *
     * @param spacing The spacing between the modules
     * @return The newly created VBox
     */
    public VBox createVBox (int spacing) {
        VBox vBox = new VBox(spacing);
        vBox.setAlignment(Pos.CENTER);
        if (root != null) {
            root.getChildren().add(vBox);
        }
        else {
            hBox.getChildren().add(vBox);
        }
        return vBox;
    }

    /**
     * Creates a VBox that is scrollable
     *
     * @param spacing The spacing between the modules
     * @return The newly created VBox
     */
    public VBox createVBoxScrollable (int spacing) {
        VBox vBox = new VBox(spacing);
        return vBox;
    }
}
