package Maze.Gui;

import java.awt.Graphics;
import java.awt.Dimension;
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
        return new Dimension(Maze.WIDTH + 1, Maze.HEIGHT + 1);
    }

    public void generateMaze(int algorithmIndex) {

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (Cell cell : grid) {
            cell.draw(graphics);
        }
    }
}
