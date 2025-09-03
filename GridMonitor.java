/**
 * Patik Heffley
 * 9/1/2025
 * CS 221 Warmup Assignment
 * 
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The GridMonitor class represents an X by X solar array that can be prompted in a variety of ways to check for saftey in the grid
 * 
 * @author Patik Heffley
 * @version 1.0 CS 221 Fall 2025
 */
public class GridMonitor implements GridMonitorInterface {

    private File gridFile;

    private Scanner gridScanner;

    private int rows;

    private int columns;

    private double[][] baseGrid;

    private double[][] sumGrid;

    private double[][] avgGrid;

    private double[][] deltaGrid;

    private boolean[][] dangerGrid;

    // public static void main(String[] args) throws FileNotFoundException {
    // GridMonitor monitor = new GridMonitor("sample.txt");

    // System.out.println(monitor.toString());

    // monitor.getBaseGrid();

    // monitor.getSurroundingSumGrid();

    // monitor.getSurroundingAvgGrid();

    // monitor.getDeltaGrid();

    // monitor.getDangerGrid();
    // }

    /**
     * Initilization for GridMonitor
     * 
     * @param fileName input File
     * @throws FileNotFoundException This exception has been instructed to not be handled or caught
     */
    public GridMonitor(String fileName) throws FileNotFoundException {

        gridFile = new File(fileName);

        gridScanner = new Scanner(gridFile);

    }

    /**
     * Returns the content of the base grid in a frankly ugly format, but it is a string
     * @return String of baseGrid
     */
    public String toString() {
        if (baseGrid == null) {
            getBaseGrid();
        }
        if (dangerGrid == null) {
            getDangerGrid();
        }


        String baseString = "Base Grid:\n";
        String dangerString = "Danger Detected in these grids:\n";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                baseString += baseGrid[i][j] + " ";
                dangerString += dangerGrid[i][j] + " ";
            }
            baseString += "\n";
            dangerString += "\n";

        }

        return baseString + "\n" + dangerString;

    }

    /**
     * Creates a baseGrid from the input file & sets the values of rows and columns. This method is required to be run before any other method may be used.
     * @return Double[][] baseGrid to be used in calculation
     */
    @Override
    public double[][] getBaseGrid() {
        // System.out.println("\nbaseGrid: \n");

        if (baseGrid != null) {
            return baseGrid;
        }

        rows = gridScanner.nextInt();
        columns = gridScanner.nextInt();

        baseGrid = new double[rows][columns];
        double[][] localBaseGrid = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                double nextDouble = gridScanner.nextDouble();
                baseGrid[i][j] = nextDouble;
                localBaseGrid[i][j] = nextDouble;

                // System.out.println(baseGrid[i][j]);
            }
        }

        // gridScanner.close();

        return localBaseGrid;
    }

    /**
     * Sets the value of sumGrid using the values from baseGrid by adding the values of surrounding grid tiles. If no grid tile is given then the tile adds the value of itself.
     * @return Double[][] sumGrid 
     */
    @Override
    public double[][] getSurroundingSumGrid() {

        if (baseGrid == null) {
            getBaseGrid();
        }

        sumGrid = new double[rows][columns];

        // System.out.println("\n PRINTING sumGrid: \n");

        double above;
        double left;
        double right;
        double below;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (j - 1 < 0) {
                    left = baseGrid[i][j];
                } else {
                    left = baseGrid[i][j - 1];
                }

                if (j + 1 >= columns) {
                    right = baseGrid[i][j];
                } else {
                    right = baseGrid[i][j + 1];
                }

                if (i - 1 < 0) {
                    below = baseGrid[i][j];
                } else {
                    below = baseGrid[i - 1][j];
                }

                if (i + 1 >= rows) {
                    above = baseGrid[i][j];
                } else {
                    above = baseGrid[i + 1][j];
                }

                sumGrid[i][j] = above + left + right + below;
                // System.out.println(sumGrid[i][j]);
            }
        }
        return sumGrid;
    }

    /**
     * Sets the value of avgGrid using the values from sumGrid. This simply divides all values from sumGrid by 4
     * @return Double[][] avgGrid
     */
    @Override
    public double[][] getSurroundingAvgGrid() {

        if (sumGrid == null) {
            getSurroundingSumGrid();
        }

        // System.out.println("\navGrid: \n");
        avgGrid = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                avgGrid[i][j] = (sumGrid[i][j] / 4);

                // System.out.println(avgGrid[i][j]);
            }

        }

        return avgGrid;
    }

    /**
     * Sets the value of deltaGrid by dividing all of the values from avgGrid by 2 and then finding the absolute value
     * @return Double[][] deltaGrid this value will always be positive
     */
    @Override
    public double[][] getDeltaGrid() {
        if (avgGrid == null) {
            getSurroundingAvgGrid();
        }

        // System.out.println("\ndeltaGrid: \n");
        deltaGrid = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                deltaGrid[i][j] = Math.abs(avgGrid[i][j] / 2);

                // System.out.println(deltaGrid[i][j]);
            }

        }

        return deltaGrid;
    }

    /**
     * Sets the value of dangerGrid by comparing the value of avgGrid added or subtracted from the value from deltaGrid and comparing that against the baseGrid.
     * @return boolean[][] dangerGrid, this value will be true if danger is detected in that grid tile
     */
    @Override
    public boolean[][] getDangerGrid() {

        if (deltaGrid == null) {
            getDeltaGrid();
        }
        // System.out.println("\nsdangerGrid: \n");
        dangerGrid = new boolean[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boolean danger = false;
                if ((avgGrid[i][j] - deltaGrid[i][j]) > baseGrid[i][j]) {
                    danger = true;
                }
                if ((avgGrid[i][j] + deltaGrid[i][j]) < baseGrid[i][j]) {
                    danger = true;
                }

                dangerGrid[i][j] = danger;

                // System.out.println(dangerGrid[i][j]);
            }

        }

        return dangerGrid;
    }
}