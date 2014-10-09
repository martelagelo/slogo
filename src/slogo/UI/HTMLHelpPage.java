package slogo.UI;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HTMLHelpPage {
    
    private WebView myWebView;

    public HTMLHelpPage(String siteName){
        myWebView = new WebView();
        WebEngine engine = myWebView.getEngine();
        String helpPageSite = "http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php";
        engine.load(helpPageSite);
        engine.locationProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue<? extends String> ov, final String oldLoc, final String loc) {
              if (!loc.contains(helpPageSite)) {
                Platform.runLater(new Runnable() {
                  @Override public void run() {
                    engine.load(oldLoc);
                  }
                });
              }
            }
          });
    }
    
    protected void displayPage(){
        Group newRoot = new Group();
        newRoot.getChildren().addAll(myWebView);
        Scene newScene = new Scene(newRoot, 800, 500);
        Stage newStage = new Stage();
        newStage.setTitle("Help Page");
        newStage.setScene(newScene);
        newStage.setX(50);
        newStage.setY(50);
        newStage.show();
    }
}
