package slogo.UI;


import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class SliderCreator {

	private VBox vBox;
	
	public SliderCreator(VBox vBox) {
		this.vBox = vBox;
	}
	
	/**
	 * Method to create the slider for animation speed
	 * @param minValue Smallest value of slider
	 * @param maxValue Largest value of slider
	 * @param currentValue The default value of the slider
	 * @return The slider
	 */
	public Slider createSlider(int minValue, int maxValue, int currentValue){
		Slider slider = new Slider();
		slider.setMin(minValue);
		slider.setMax(maxValue);
		slider.setValue(currentValue);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		vBox.getChildren().add(slider);
		return slider;
	}
	
	/**
	 * Method to create the slider for animation speed
	 * @param minValue Smallest value of slider
	 * @param maxValue Largest value of slider
	 * @param x_Coord X position of slider
	 * @param y_Coord Y position of slider
	 * @param currentValue The default value of the slider
	 * @return The slider
	 */
	public Slider createSlider(int minValue, int maxValue,  int currentValue, int x_Coord, int y_Coord){
		Slider slider = createSlider(minValue, maxValue, currentValue);
		slider.setLayoutX(x_Coord);
		slider.setLayoutY(y_Coord);
		return slider;
	}
}
