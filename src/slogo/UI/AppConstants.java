package slogo.UI;

import java.util.Map;

import javafx.scene.paint.Color;

import java.lang.reflect.Field;

/**
 * 
 * @author Michael
 *
 */
public class AppConstants {
	
		public static final Color BACKGROUND_COLOR = Color.BURLYWOOD;
	
        public static final Integer STAGE_WIDTH = 1300;
        public static final Integer STAGE_HEIGHT = 750;
        public static final Integer STAGE_PADDING = 5;
        public static final Integer MODULE_PADDING = 30;
        
        public static final Integer CANVAS_OFFSET_X_POS = 75;
        public static final Integer CANVAS_OFFSET_Y_POS = 75;
        public static final Integer CANVAS_WIDTH = 400;
        public static final Integer CANVAS_HEIGHT = 400;
        
        public static final Integer LABEL_FONT_SIZE = 1;
        
        public static final Integer VBOX_SPACING = 10;
        
        public static final Integer TITLE_LABEL_FONT_SIZE = LABEL_FONT_SIZE * 3;
        public static final Integer TITLE_X_POS = CANVAS_OFFSET_X_POS;
        public static final Integer TITLE_Y_POS = STAGE_PADDING;
        
        public static final Integer INITIAL_TURTLE_X_POS = CANVAS_OFFSET_X_POS + CANVAS_WIDTH/2;
        public static final Integer INITIAL_TURTLE_Y_POS = CANVAS_OFFSET_Y_POS + CANVAS_HEIGHT/2;
        
        public static final Integer FIRST_ROW_BUTTON_HBOX_X_POS = CANVAS_OFFSET_X_POS + CANVAS_WIDTH + MODULE_PADDING;
        public static final Integer FIRST_ROW_BUTTON_HBOX_Y_POS = STAGE_PADDING;
        public static final Integer FIRST_ROW_BUTTON_HBOX_WIDTH = 250;
    
        public static final Integer LIST_BLOCKS_X_POS = FIRST_ROW_BUTTON_HBOX_X_POS + FIRST_ROW_BUTTON_HBOX_WIDTH + MODULE_PADDING;
        public static final Integer LIST_BLOCKS_WIDTH = 200;
        public static final Integer LIST_BLOCKS_HEIGHT = 200;
        public static final Integer LIST_BLOCKS_Y_POS_USER_VARIABLES = STAGE_PADDING;
        public static final Integer LIST_BLOCKS_Y_POS_USER_COMMANDS = LIST_BLOCKS_Y_POS_USER_VARIABLES + LIST_BLOCKS_HEIGHT + MODULE_PADDING;
        public static final Integer LIST_BLOCKS_Y_POS_SLOGO_VARIABLES = LIST_BLOCKS_Y_POS_USER_COMMANDS + LIST_BLOCKS_HEIGHT + MODULE_PADDING;
        
        public static final Integer COMMAND_HISTORY_X_POS = LIST_BLOCKS_X_POS + LIST_BLOCKS_WIDTH + MODULE_PADDING ;
        public static final Integer COMMAND_HISTORY_Y_POS = STAGE_PADDING;
        public static final Integer COMMAND_HISTORY_WIDTH = 250;
        public static final Integer COMMAND_HISTORY_HEIGHT = STAGE_HEIGHT - 50;
        
        public static final Integer HELP_BUTTON_PREF_WIDTH = 100;
        public static final Integer HELP_BUTTON_PREF_HEIGHT = 50;
        
        public static final Integer TEXT_BOX_ROW_X_POS = STAGE_PADDING;
        public static final Integer TEXT_BOX_ROW_Y_POS = STAGE_HEIGHT - STAGE_PADDING;
        
        public static final Integer HBOX_SPACING = STAGE_PADDING;
        
        //public static final Integer ALL_SELECTORS_XPOS = STAGE_WIDTH - 2*STAGE_WIDTH/10;
        //public static final Integer BACKGROUND_COLOR_YPOS = 25*STAGE_PADDING;
        //public static final Integer PATH_COLOR_YPOS = 40*STAGE_PADDING;
        //public static final Integer TURTLE_IMAGE_YPOS = 55*STAGE_PADDING;
        
        //public static final Integer COMMAND_LABEL_XPOS = 75;
        //public static final Integer COMMAND_LABEL_YPOS = 510;
        public static final Integer SELECTOR_HEIGHT = 40;
        public static final Integer SELECTOR_WIDTH = 200;
        public static final Double SELECTOR_FONT_SIZE = .8;
        
        public static final String HELP_URL = "http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php";
        public static final Integer HELP_PAGE_WIDTH = 800;
        public static final Integer HELP_PAGE_HEIGHT = 500;

        public static final double MAX_NEW_IMAGE_WIDTH = 15;
        public static final double MAX_NEW_IMAGE_HEIGHT = 15;

        public static final double GRIDLINES_SPACING = 50;
        
}


