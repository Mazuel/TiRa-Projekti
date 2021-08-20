package maze.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import maze.algorithms.AldousBroderGenerator;
import maze.algorithms.BinaryTreeGenerator;
import maze.algorithms.PrimsGenerator;
import maze.algorithms.RecursiveBacktrackerGenerator;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.GeneratorAlgorithm;

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

    public CellList getGrid() {
        return grid;
    }

    public void setGrid(CellList grid) {
        this.grid = grid;
    }
}
