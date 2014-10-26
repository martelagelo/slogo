package slogo.UI;

import javafx.scene.paint.Color;


/**
 * 10/15/2014
 *
 * @author Michael Deng
 *
 */
public class AppConstants {

    public static final Color BACKGROUND_COLOR = Color.BURLYWOOD;
    public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;

    public static final Integer STAGE_WIDTH = 1300;
    public static final Integer STAGE_HEIGHT = 750;
    public static final Integer STAGE_PADDING = 5;
    public static final Integer MODULE_PADDING = 30;

    public static final Integer CANVAS_OFFSET_X_POS = 10;
    public static final Integer CANVAS_OFFSET_Y_POS = 75;
    public static final Integer CANVAS_WIDTH = 600;
    public static final Integer CANVAS_HEIGHT = 450;

    public static final Integer MESSAGE_BOX_WIDTH = 350;
    public static final Integer MESSAGE_BOX_HEIGHT = 50;

    public static final Integer LABEL_FONT_SIZE = 1;

    public static final Integer VBOX_SPACING = 5;

    public static final Integer TITLE_LABEL_FONT_SIZE = LABEL_FONT_SIZE * 3;
    public static final Integer TITLE_X_POS = CANVAS_OFFSET_X_POS;
    public static final Integer TITLE_Y_POS = STAGE_PADDING;

    public static final Integer TEXT_BOX_VBOX_X_POS = CANVAS_OFFSET_X_POS;
    public static final Integer TEXT_BOX_VBOX_Y_POS = CANVAS_OFFSET_Y_POS + CANVAS_HEIGHT +
            MODULE_PADDING;
    public static final Integer TEXT_BOX_WIDTH = CANVAS_WIDTH;

    public static final Integer DEBUG_LABEL_X_POS = CANVAS_OFFSET_X_POS;
    public static final Integer DEBUG_LABEL_Y_POS = TEXT_BOX_VBOX_Y_POS + 2 * MODULE_PADDING;
    public static final Integer DEBUG_LABEL_FONT_SIZE = LABEL_FONT_SIZE * 2;

    public static final Integer INITIAL_TURTLE_X_POS = CANVAS_OFFSET_X_POS + CANVAS_WIDTH / 2;
    public static final Integer INITIAL_TURTLE_Y_POS = CANVAS_OFFSET_Y_POS + CANVAS_HEIGHT / 2;

    public static final Integer FIRST_SCROLL_PANE_X_POS = CANVAS_OFFSET_X_POS + CANVAS_WIDTH +
            MODULE_PADDING;
    public static final Integer FIRST_SCROLL_PANE_Y_POS = STAGE_PADDING;
    public static final Integer FIRST_SCROLL_PANE_WIDTH = 260;
    public static final Integer FIRST_SCROLL_PANE_HEIGHT = STAGE_HEIGHT - MODULE_PADDING;

    public static final Integer LIST_SCROLL_PANE_X_POS = FIRST_SCROLL_PANE_X_POS +
            FIRST_SCROLL_PANE_WIDTH + MODULE_PADDING;
    public static final Integer LIST_SCROLL_PANE_Y_POS = STAGE_PADDING;
    public static final Integer LIST_SCROLL_PANE_WIDTH = 280;
    public static final Integer LIST_SCROLL_PANE_HEIGHT = STAGE_HEIGHT - MODULE_PADDING;

    public static final Integer LIST_BLOCKS_WIDTH = 200;
    public static final Integer LIST_BLOCKS_HEIGHT = 200;

    public static final Integer COMMAND_HISTORY_WIDTH = 250;
    public static final Integer COMMAND_HISTORY_HEIGHT = STAGE_HEIGHT - 50;

    public static final Integer RUNNING_STATUS_LABEL_X_POS = CANVAS_OFFSET_X_POS + CANVAS_WIDTH /
            2 + MODULE_PADDING;
    public static final Integer RUNNING_STATUS_LABEL_Y_POS = STAGE_PADDING;

    public static final Integer HBOX_SPACING = STAGE_PADDING;

    public static final Integer SELECTOR_HEIGHT = 40;
    public static final Integer SELECTOR_WIDTH = 200;
    public static final Double SELECTOR_FONT_SIZE = .8;

    public static final String HELP_URL =
            "http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php";
    public static final Integer HELP_PAGE_WIDTH = 800;
    public static final Integer HELP_PAGE_HEIGHT = 500;

    public static final double MAX_NEW_IMAGE_WIDTH = 15;
    public static final double MAX_NEW_IMAGE_HEIGHT = 15;

    public static final Integer ANIMATION_SLIDER_MIN_VALUE = 0;
    public static final Integer ANIMATION_SLIDER_MAX_VALUE = 20;
    public static final Integer ANIMATION_SLIDER_DEFAULT_VALUE = 1;
    public static final double ANIMATION_SLIDER_WIDTH = 300;

    public static final double GRIDLINES_SPACING = 10;
    public static final double ROUNDING_ERROR = 0.00000001;
    public static final double DASH_SIZE = 10;
    public static final int MAX_PATH_WIDTH = 15;
    public static final int BOLD_SIZE = 5;
    public static final double ORIENTATION_OFFSET = 90;

}
