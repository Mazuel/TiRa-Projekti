package maze.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;

import maze.util.CellList;

public class MazeGridPanel extends JPanel {

    private CellList grid;

    public MazeGridPanel(int rows, int cols) {
        this.grid = new CellList();
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                grid.add(new Cell(x, y));
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // +1 jotta solujen reunat näkyvät myös alhaalla ja oikealla
        return new Dimension(Maze.WIDTH + 1, Maze.HEIGHT + 1);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int i = 0; i < grid.size(); i++) {
            grid.get(i).draw(graphics);
        }

        grid.get(Maze.START_CELL).colorCell(graphics, Color.GRAY);
    }

    public CellList getGrid() {
        return grid;
    }

    public void setGrid(CellList grid) {
        this.grid = grid;
    }

    public void setCurrent(Cell cell) {
    }
}
