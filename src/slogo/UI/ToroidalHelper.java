package slogo.UI;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Line;


/**
 * 10/26/2014
 *
 * Version 1
 *
 * @author Michael Deng
 * @author Michael Ren
 * @author Eric Chen
 * @author Nick Widmaier
 *
 */
public class ToroidalHelper {

    List<Line> lineList;
    private static final Integer MAX_X = AppConstants.CANVAS_WIDTH +
            AppConstants.CANVAS_OFFSET_X_POS;
    private static final Integer MIN_X = AppConstants.CANVAS_OFFSET_X_POS;
    private static final Integer MAX_Y = AppConstants.CANVAS_HEIGHT +
            AppConstants.CANVAS_OFFSET_Y_POS;
    private static final Integer MIN_Y = AppConstants.CANVAS_OFFSET_Y_POS;
    private static final Integer WIDTH = AppConstants.CANVAS_WIDTH;
    private static final Integer HEIGHT = AppConstants.CANVAS_HEIGHT;

    /**
     * Constructor
     *
     * @param lineList The list of "un-toroidalled" lines
     */
    public ToroidalHelper (List<Line> lineList) {
        this.lineList = lineList;
    }

    /**
     * Makes the lines toroidal and puts them in a list
     *
     * @return The new list with toroidal lines
     */
    public List<Line> makeLinesToroidal () {
        List<Line> newLineList = new ArrayList<Line>();

        for (Line l : lineList) {
            if (!checkEndBounds(l)) {
                List<Line> subLineList = new ArrayList<>();
                double angle =
                        Math.atan2(l.getEndX() - l.getStartX(), -(l.getEndY() - l.getStartY()));
                if (angle > Math.PI / 2) {
                    angle = angle - Math.PI / 2;
                }
                else if (angle < -1 * Math.PI / 2) {
                    angle = angle + Math.PI / 2;
                }
                cutLines(l, subLineList, angle);
                newLineList.addAll(subLineList);
            }
            else {
                newLineList.add(l);
            }
        }
        return newLineList;
    }

    /**
     * Cuts the lines into smaller segments that fit in the canvas
     *
     * @param l The current un-toroidalled line
     * @param lines The new list of lines that will be populated
     * @param angle The angle of the original line
     */
    private void cutLines (Line l, List<Line> lines, double angle) {
        if (!checkStartBounds(l)) {
            runLineXShift(l);
            runLineYShift(l);
        }
        List<Line> newLineList = new ArrayList<Line>();
        runXCalculations(l, angle, newLineList);
        runYShift(newLineList, angle, lines);

    }

    /**
     * Calculates the split of two lines crossing the X boundary
     *
     * @param l The current un-toroidalled line
     * @param angle The angle of the original line
     * @param lines The new list of lines that will be populated
     */
    private void runXCalculations (Line l, double angle, List<Line> lines) {
        if (checkXBounds(l.getEndX())) {
            lines.add(l);
        }
        else if (l.getEndX() > MAX_X) {
            Line newLine = new Line();
            setLineCoords(newLine, MIN_X,
                          l.getStartY() - (MAX_X - l.getStartX()) / Math.tan(angle), l.getEndX() -
                          WIDTH,
                          l.getEndY());
            setLineCoords(l, l.getStartX(), l.getStartY(), MAX_X,
                          l.getStartY() - (MAX_X - l.getStartX()) / Math.tan(angle));
            lines.add(l);
            runXCalculations(newLine, angle, lines);
        }
        else if (l.getEndX() < MIN_X) {
            Line newLine = new Line();
            setLineCoords(newLine, MAX_X,
                          l.getStartY() + (l.getStartX() - MIN_X) / Math.tan(angle), l.getEndX() +
                          WIDTH,
                          l.getEndY());
            setLineCoords(l, l.getStartX(), l.getStartY(), MIN_X,
                          l.getStartY() + (l.getStartX() - MIN_X) / Math.tan(angle));
            lines.add(l);
            runXCalculations(newLine, angle, lines);
        }
    }

    /**
     * Shifts lines that all have the same x positions vertically to fit into one canvas
     *
     * @param oldLines The old list filled with lines that need to be adjusted
     * @param angle The angle of the original line
     * @param newLines The new list of lines that will be populated
     */
    private void runYShift (List<Line> oldLines, double angle, List<Line> newLines) {
        for (Line l : oldLines) {
            runLineYShift(l);
            runYCalculations(l, angle, newLines);
        }
    }

    /**
     * Calculates the split of two lines crossing the Y boundary
     *
     * @param l The current un-toroidalled line
     * @param angle The angle of the original line
     * @param lines The new list of lines that will be populatedd
     */
    private void runYCalculations (Line l, double angle, List<Line> lines) {
        if (checkYBounds(l.getEndY())) {
            lines.add(l);
        }
        else if (l.getEndY() > MAX_Y) {
            Line newLine = new Line();
            setLineCoords(newLine,
                          newLine.getStartX() - (MAX_Y - newLine.getStartY()) * Math.tan(angle),
                          MIN_Y, newLine.getEndX(), newLine.getEndY() - HEIGHT);
            setLineCoords(l, l.getStartX(), l.getStartY(),
                          newLine.getStartX() - (MAX_Y - newLine.getStartY()) * Math.tan(angle),
                          MAX_Y);
            lines.add(l);
            runYCalculations(newLine, angle, lines);
        }
        else if (l.getEndY() < MIN_Y) {
            Line newLine = new Line();
            setLineCoords(newLine, l.getStartX() + (l.getStartY() - MIN_Y) * Math.tan(angle),
                          MAX_Y, l.getEndX(), l.getEndY() + HEIGHT);
            setLineCoords(l, l.getStartX(), l.getStartY(), l.getStartX() + (l.getStartY() - MIN_Y) *
                          Math.tan(angle), MIN_Y);
            lines.add(l);
            runYCalculations(newLine, angle, lines);
        }
    }

    /**
     * Shifts a lines y position by multiples of the height of the canvas
     *
     * @param l The current un-toroidalled line
     */
    private void runLineYShift (Line l) {
        while (!checkYBounds(l.getStartY())) {
            if (l.getStartY() > MAX_Y) {
                l.setStartY(l.getStartY() - HEIGHT);
                l.setEndY(l.getEndY() - HEIGHT);
            }
            else if (l.getStartY() < MIN_Y) {
                l.setStartY(l.getStartY() + HEIGHT);
                l.setEndY(l.getEndY() + HEIGHT);
            }
        }
    }

    /**
     * Shifts a lines x position by multiples of the width of the canvas
     *
     * @param l The current un-toroidalled line
     */
    private void runLineXShift (Line l) {
        while (!checkXBounds(l.getStartX())) {
            if (l.getStartX() > MAX_X) {
                l.setStartX(l.getStartX() - WIDTH);
                l.setEndX(l.getEndX() - WIDTH);
            }
            else if (l.getStartX() < MIN_X) {
                l.setStartX(l.getStartX() + WIDTH);
                l.setEndX(l.getEndX() + WIDTH);
            }
        }
    }

    /**
     * Sets the coordinates of a line
     *
     * @param l The line
     * @param start_x The starting x position of the line
     * @param start_y The starting y position of the line
     * @param end_x The ending x position of the line
     * @param end_y The ending y position of the line
     */
    private void setLineCoords (Line l, double start_x, double start_y, double end_x, double end_y) {
        l.setStartX(start_x);
        l.setStartY(start_y);
        l.setEndX(end_x);
        l.setEndY(end_y);
    }

    /**
     * Checks all the bounds of a line
     *
     * @param l The current line
     * @return True if the whole line is in the canvas
     */
    private boolean checkAllBounds (Line l) {
        return (checkStartBounds(l) && checkEndBounds(l));
    }

    /**
     * Checks if the end coordinate point is in the canvas
     *
     * @param l The current line
     * @return True if the end point is in the canvas
     */
    private boolean checkEndBounds (Line l) {
        return (checkXBounds(l.getEndX()) && checkYBounds(l.getEndY()));
    }

    /**
     * Checks if the start coordinate point is in the canvas
     *
     * @param l The current line
     * @return True if the start point is in the canvas
     */
    private boolean checkStartBounds (Line l) {
        return (l.getStartX() <= MAX_X && l.getStartX() >= MIN_X && l.getStartY() <= MAX_Y && l
                .getStartY() >= MIN_Y);
    }

    /**
     * Checks the if the x bounds of a coordinate point fall in the canvas
     *
     * @param x The x coordinate
     * @return True if the x bound is within the canvas
     */
    private boolean checkXBounds (double x) {
        return (x <= MAX_X && x >= MIN_X);
    }

    /**
     * Checks the if the y bounds of a coordinate point fall in the canvas
     *
     * @param y The y coordinate
     * @return True if the y bound is within the canvas
     */
    private boolean checkYBounds (double y) {
        return (y <= MAX_Y && y >= MIN_Y);
    }
}
