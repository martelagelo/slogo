package slogo.UI;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.paint.Color;

public class ScrollPaneCreator {

	Group root;
	
	public ScrollPaneCreator(Group root) {
		this.root = root;
	}
	
	public ScrollPane createScrollPane(Integer x_Coord, Integer y_Coord, Integer height, Integer width) {
		ScrollPane moduleScrollPane = new ScrollPane();
		moduleScrollPane.setStyle("-fx-background: #F5F5DC;");
		moduleScrollPane.setLayoutX(x_Coord);
		moduleScrollPane.setLayoutY(y_Coord);
		moduleScrollPane.setPrefHeight(width);
		moduleScrollPane.setPrefWidth(height);
		moduleScrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		moduleScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		root.getChildren().add(moduleScrollPane);
		return moduleScrollPane;
	}
}
