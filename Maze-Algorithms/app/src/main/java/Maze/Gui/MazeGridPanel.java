package maze.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class MazeGridPanel extends JPanel {

    private List<Cell> grid = new ArrayList<Cell>();

    public MazeGridPanel(int rows, int cols) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                grid.add(new Cell(x, y));
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // +1 so the borders will be visible on the bottom and right of the cell
        return new Dimension(Maze.WIDTH + 1, Maze.HEIGHT + 1);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (Cell cell : grid) {
            cell.draw(graphics);
        }

        grid.get(Maze.START_CELL).colorCell(graphics, Color.GREEN);
    }
}
