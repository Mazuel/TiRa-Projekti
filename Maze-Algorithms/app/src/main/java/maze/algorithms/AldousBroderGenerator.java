package maze.algorithms;

import java.util.Random;

import maze.gui.Maze;
import maze.gui.MazeGridPanel;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.Direction;
import maze.util.GeneratorAlgorithm;

public class AldousBroderGenerator implements GeneratorAlgorithm {

    private Cell currentCell;
    private Cell previousCell;
    private int startCell;
    private final CellList grid;
    private Random random = new Random();

    public AldousBroderGenerator(MazeGridPanel gridPanel) {
        this.grid = gridPanel.getGrid();
        this.startCell = random.nextInt(grid.size());
        this.currentCell = grid.get(startCell);
    }

    // Algoritmi
    @Override
    public void generate() {

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

        Maze.generated = grid.isAllVisited();
        if (Maze.generated) {
            currentCell.setCursor(false);
        }
    }
}
