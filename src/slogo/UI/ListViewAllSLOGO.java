package slogo.UI;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * 
 * 10/8/2014
 * 
 * Version 1
 * 
 * @author Nick Widmaier
 * @author Michael Deng
 * @author Michael Ren
 * @author Eric Chen
 *
 */
public abstract class ListViewAllSLOGO {

	private Group myRoot;
	private VBox vBox;
	protected ListViewCreator myListViewCreator;

	public ListViewAllSLOGO(Group root){
		myRoot = root;
		myListViewCreator = new ListViewCreator();
	}
	
	public ListViewAllSLOGO(VBox vBox) {
		this.vBox = vBox;
	}

	public ListViewAllSLOGO createListView(int listViewHeight, int listViewWidth,
			int vboxXpos, int vboxYpos, String labelName,
			int fontSize, Color color){
		myListViewCreator= new ListViewCreator(listViewHeight, listViewWidth);
		VBox vbox = myListViewCreator.createListViewWithLabel(labelName, fontSize, color);
		vbox.setLayoutX(vboxXpos);
		vbox.setLayoutY(vboxYpos);
		if (myRoot != null) myRoot.getChildren().add(vbox);
		else this.vBox.getChildren().add(vbox);
		return this;
	}

	public ListViewAllSLOGO createListView(int listViewHeight, int listViewWidth,
			String labelName,
			int fontSize, Color color){
		myListViewCreator= new ListViewCreator(listViewHeight, listViewWidth);
		VBox vbox = myListViewCreator.createListViewWithLabel(labelName, fontSize, color);
		if (myRoot != null) myRoot.getChildren().add(vbox);
		else this.vBox.getChildren().add(vbox);
		return this;
	}

	protected ListView<String> getListView(){
		return myListViewCreator.getListView();
	}

	protected void setItems(ObservableList<String> oList){
		myListViewCreator.getListView().setItems(oList);
	}

	protected ObservableList<String> getItems(){
		return myListViewCreator.getListView().getItems();
	}

	protected abstract void create ();

}
