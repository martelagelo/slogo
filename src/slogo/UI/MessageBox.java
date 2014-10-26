package slogo.UI;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * 10/26/2014
 *
 * @author Michael
 *
 */
public class MessageBox {

    /**
     * Constructor
     *
     * @param message The message in the message box
     */
    public MessageBox (String message) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(message));
        Scene dialogScene =
                new Scene(dialogVbox, AppConstants.MESSAGE_BOX_WIDTH,
                          AppConstants.MESSAGE_BOX_HEIGHT);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
