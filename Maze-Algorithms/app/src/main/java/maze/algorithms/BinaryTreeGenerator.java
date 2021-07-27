package maze.algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import maze.gui.MazeGridPanel;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.Direction;

public class BinaryTreeGenerator {

    private int renderSpeed = 5; // Millisekuntia
    private int index;

    private final CellList grid;
    private Cell currentCell;
    private Cell previousCell;
    private Random randomDirection = new Random();
    private boolean generated = false;

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
                if (!generated) {
                    generate();
                    generated = grid.isAllVisited();
                } else {
                    timer.stop();
                }
                mazeGridPanel.repaint();
                // previousCell.setCursor(false);
            }
        });
        timer.start();
    }

    // Algoritmi
    private void generate() {

        if (previousCell != null) {
            previousCell.setCursor(false);
        }
        
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
        currentCell.setCursor(true);

        if (index - 1 >= 0) {
            previousCell = currentCell;
            currentCell = grid.get(--index);
        } else {
            currentCell.setCursor(false);
        }
    }
}
