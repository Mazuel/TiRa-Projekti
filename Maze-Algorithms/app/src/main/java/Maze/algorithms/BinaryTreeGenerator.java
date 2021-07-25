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

    private int renderSpeed = 0; // Millisekuntia
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
                    currentCell = null;
                    timer.stop();
                }
                mazeGridPanel.repaint();
            }
        });
        timer.start();
    }

    // Algoritmi
    private void generate() {
        boolean topNeighbour = grid.contains(currentCell.getNeighbour(grid, Direction.TOP));
        boolean leftNeighbour = grid.contains(currentCell.getNeighbour(grid, Direction.LEFT));

        if (topNeighbour && leftNeighbour) {
            removeWallsDirection(randomDirection.nextInt(2));
        } else if (leftNeighbour) {
            removeWallsDirection(0);
        } else if (topNeighbour) {
            removeWallsDirection(1);
        }
        currentCell.setVisited(true);

        currentCell = grid.get(index--);
    }

    // 0 = Poista vasen seinä
    // 1 = Poista ylä seinä
    private void removeWallsDirection(int direction) {
        CellList neighbours = currentCell.getNeighbours(grid);
        if (direction == 0) {
            for (int i = 0; i < neighbours.size(); i++) {
                Cell neighbour = neighbours.get(i);
                if (neighbour.getY() + 1 == currentCell.getY()) {
                    currentCell.removeWall(neighbour);
                }
            }
        } else {
            for (int i = 0; i < neighbours.size(); i++) {
                Cell neighbour = neighbours.get(i);
                if (neighbour.getX() + 1 == currentCell.getX()) {
                    currentCell.removeWall(neighbour);
                }
            }
        }
    }
}
