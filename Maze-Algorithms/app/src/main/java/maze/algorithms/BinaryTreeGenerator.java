package maze.algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import maze.gui.Cell;
import maze.gui.MazeGridPanel;
import maze.util.CellList;
import maze.util.Direction;

public class BinaryTreeGenerator {

    private int renderSpeed = 3; // Millisekuntia
    private int index;

    private final CellList grid;
    private Cell currentCell;
    private Random randomDirection = new Random();

    public BinaryTreeGenerator(MazeGridPanel mazeGridPanel) {
        this.grid = mazeGridPanel.getGrid();
        this.index = grid.size() - 1;
        this.currentCell = grid.get(index);

        // Asetetaan pieni viive jokaisen askeleen väliin, jotta voidaan hahmottaa
        // algoritmin toimintaa helpommin
        Timer timer = new Timer(renderSpeed, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index >= 0) {
                    generate();
                } else {
                    timer.stop();
                }
                mazeGridPanel.repaint();
            }
        });
        timer.start();
    }

    // Algoritmi
    private void generate() {
        Cell topNeighbour = currentCell.getNeighbour(grid, Direction.TOP);
        Cell leftNeighbour = currentCell.getNeighbour(grid, Direction.LEFT);

        if (topNeighbour != null && leftNeighbour != null) {
            int direction = randomDirection.nextInt(2);
            if (direction == 0) {
                currentCell.removeWall(leftNeighbour);
            } else {
                currentCell.removeWall(topNeighbour);
            }
        } else if (leftNeighbour != null) {
            currentCell.removeWall(leftNeighbour);
        } else if (topNeighbour != null) {
            currentCell.removeWall(topNeighbour);
        }
        currentCell.setVisited(true);

        if (index - 1 >= 0) {
            currentCell = grid.get(--index);
        }
    }
}
