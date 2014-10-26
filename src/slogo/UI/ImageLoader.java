package slogo.UI;

import java.io.File;
import java.util.List;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class ImageLoader {
    private List<Turtle> myTurtleList;
    private int totalUserImages;

    public ImageLoader (List<Turtle> t, int i) {
        myTurtleList = t;
        totalUserImages = i;
    }

    protected void chooseNewImage (Stage stage, TurtleImageSelector turtleSelector) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load in a New Turtle Image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.jpg", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            Image i =
                    new Image((selectedFile.toURI().toString()), AppConstants.MAX_NEW_IMAGE_WIDTH,
                              AppConstants.MAX_NEW_IMAGE_HEIGHT, true, true);
            turtleSelector.updateMap("User Image #" + totalUserImages, i, myTurtleList);
            totalUserImages += 1;
        }
        else {
            new MessageBox("No File Selected");
        }
    }
}
