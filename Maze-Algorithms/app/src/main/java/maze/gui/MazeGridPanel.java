package maze.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import maze.algorithms.AldousBroderGenerator;
import maze.algorithms.BinaryTreeGenerator;
import maze.util.Cell;
import maze.util.CellList;

public class MazeGridPanel extends JPanel {

    private CellList grid = new CellList();

    public MazeGridPanel(int rows, int cols) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                grid.add(new Cell(x, y));
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // +1 jotta solujen 'seinät' näkyvät myös oikealla ja alhaalla
        return new Dimension(Maze.WIDTH + 1, Maze.HEIGHT + 1);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int i = 0; i < grid.size(); i++) {
            grid.get(i).draw(graphics);
        }
    }

    public void generate(AlgorithmOption algorithm) {
        System.out.println(algorithm);
        switch (algorithm) {
            case BINARY_TREE:
                new BinaryTreeGenerator(this);
                break;
            case ALDOUS_BRODER:
                new AldousBroderGenerator(this);
                break;
            default:
        }
    }

    public CellList getGrid() {
        return grid;
    }

    public void setGrid(CellList grid) {
        this.grid = grid;
    }
}
