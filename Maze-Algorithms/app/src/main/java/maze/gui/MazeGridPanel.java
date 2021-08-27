package maze.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import maze.algorithms.generators.AldousBroderGenerator;
import maze.algorithms.generators.BinaryTreeGenerator;
import maze.algorithms.generators.PrimsGenerator;
import maze.algorithms.generators.RecursiveBacktrackerGenerator;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.ExcludeFromJacocoGeneratedReport;
import maze.util.GeneratorAlgorithm;

public class MazeGridPanel extends JPanel {

    private CellList grid = new CellList();
    private final Color wallColor = Color.BLACK;

    public MazeGridPanel(int rows, int cols) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                grid.add(new Cell(x, y));
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Maze.WIDTH + 1, Maze.HEIGHT + 1);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int i = 0; i < grid.size(); i++) {
            draw(grid.get(i), graphics);
        }
    }

    public GeneratorAlgorithm createAlgorithm(AlgorithmOption algorithm) {
        switch (algorithm) {
            case BINARY_TREE:
                return new BinaryTreeGenerator(this);
            case ALDOUS_BRODER:
                return new AldousBroderGenerator(this);
            case PRIMS:
                return new PrimsGenerator(this);
            case RECURSIVE_BACKTRACKER:
                return new RecursiveBacktrackerGenerator(this);
            default:
                return null;
        }
    }

    @ExcludeFromJacocoGeneratedReport
    public void draw(Cell cell, Graphics graphics) {
        int xLocation = cell.getX() * Maze.CELL_SIZE;
        int yLocation = cell.getY() * Maze.CELL_SIZE;

        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(xLocation, yLocation, Maze.CELL_SIZE, Maze.CELL_SIZE);

        if (cell.isCursor()) {
            graphics.setColor(Color.RED);
            graphics.fillRect(xLocation, yLocation, Maze.CELL_SIZE, Maze.CELL_SIZE);
        } else if (cell.isVisited()) {
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(xLocation, yLocation, Maze.CELL_SIZE, Maze.CELL_SIZE);
        }

        if (cell.isRecursiveVisit()) {
            graphics.setColor(Color.PINK);
            graphics.fillRect(xLocation, yLocation, Maze.CELL_SIZE, Maze.CELL_SIZE);
        }

        if (cell.isBfsShortestPath()) {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(xLocation + 20, yLocation + 20, Maze.CELL_SIZE - 40 , Maze.CELL_SIZE - 40);
        }
        drawWalls(graphics, xLocation, yLocation, cell);

    }

    @ExcludeFromJacocoGeneratedReport
    private void drawWalls(Graphics graphics, int xLocation, int yLocation, Cell cell) {

        // Seinien väri
        graphics.setColor(wallColor);
        boolean[] walls = cell.getWalls();

        // Ylä
        if (walls[0]) {
            graphics.drawLine(xLocation, yLocation, xLocation + Maze.CELL_SIZE, yLocation);
        }

        // Oikea
        if (walls[1]) {
            graphics.drawLine(xLocation + Maze.CELL_SIZE, yLocation, xLocation + Maze.CELL_SIZE,
                    yLocation + Maze.CELL_SIZE);
        }

        // Ala
        if (walls[2]) {
            graphics.drawLine(xLocation + Maze.CELL_SIZE, yLocation + Maze.CELL_SIZE, xLocation,
                    yLocation + Maze.CELL_SIZE);
        }
        // Vasen
        if (walls[3]) {
            graphics.drawLine(xLocation, yLocation + Maze.CELL_SIZE, xLocation, yLocation);
        }
    }

    public CellList getGrid() {
        return grid;
    }

    public void setGrid(CellList grid) {
        this.grid = grid;
    }
}
