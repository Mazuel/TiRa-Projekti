package maze.algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import maze.gui.Maze;
import maze.gui.MazeGridPanel;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.Direction;

public class AldousBroderGenerator {

    private final int STEP_TIME_UNIT = 3;
    private Cell currentCell;
    private Cell previousCell;
    private int START_CELL;
    private final CellList grid;
    private Random random = new Random();
    private boolean generated = false;

    public AldousBroderGenerator(MazeGridPanel gridPanel) {
        this.grid = gridPanel.getGrid();
        this.START_CELL = random.nextInt(grid.size());
        this.currentCell = grid.get(START_CELL);
        createStepTimer(gridPanel).start();
    }

    private Timer createStepTimer(MazeGridPanel gridPanel) {
        Timer stepTimer = new Timer(STEP_TIME_UNIT, null);
        stepTimer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!generated) {
                    generate();
                    generated = grid.isAllVisited();
                } else {
                    currentCell.setCursor(false);
                    Maze.algorithmInAction = false;
                    stepTimer.stop();
                }
                gridPanel.repaint();
            }
        });
        return stepTimer;
    }

    // Algoritmi
    private void generate() {

        if (previousCell != null) {
            previousCell.setCursor(false);
        }

        Cell topNeighbour = currentCell.getNeighbour(grid, Direction.TOP);
        Cell rightNeighbour = currentCell.getNeighbour(grid, Direction.RIGHT);
        Cell leftNeighbour = currentCell.getNeighbour(grid, Direction.LEFT);
        Cell bottomNeighbour = currentCell.getNeighbour(grid, Direction.BOTTOM);

        CellList neighbours = new CellList();
        if (topNeighbour != null) {
            neighbours.add(topNeighbour);
        }
        if (rightNeighbour != null) {
            neighbours.add(rightNeighbour);
        }
        if (leftNeighbour != null) {
            neighbours.add(leftNeighbour);
        }
        if (bottomNeighbour != null) {
            neighbours.add(bottomNeighbour);
        }

        Cell nextCell = neighbours.get(random.nextInt(neighbours.size()));
        if (!nextCell.isVisited()) {
            currentCell.removeWall(nextCell);
        }

        currentCell.setVisited(true);
        currentCell.setCursor(true);

        previousCell = currentCell;
        currentCell = nextCell;
        // currentCell.setCursor(false);
    }
}
