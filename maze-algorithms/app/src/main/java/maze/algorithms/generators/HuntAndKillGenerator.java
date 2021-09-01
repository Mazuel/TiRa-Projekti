package maze.algorithms.generators;

import java.util.Random;

import maze.gui.Maze;
import maze.gui.MazeGridPanel;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.GeneratorAlgorithm;

public class HuntAndKillGenerator implements GeneratorAlgorithm {

    private CellList grid;
    private Cell currentCell;
    private Random random;

    public HuntAndKillGenerator(MazeGridPanel gridPanel) {
        this.random = new Random();
        this.grid = gridPanel.getGrid();
        currentCell = grid.get(random.nextInt(grid.size()));
    }

    @Override
    public void generate() {
        currentCell.setVisited(true);
        CellList unvisitedNeighbours = currentCell.getUnvisitedNeighbours(grid);

        if (unvisitedNeighbours.size() > 0) {
            Cell nextCell = unvisitedNeighbours.get(random.nextInt(unvisitedNeighbours.size()));
            currentCell.removeWall(nextCell);
            currentCell = nextCell;
        } else {
            for (int i = 0; i < grid.size(); i++) {
                Cell cell = grid.get(i);
                if (cell.getUnvisitedNeighbours(grid).size() > 0 && cell.isVisited()) {
                    currentCell = cell;
                    break;
                }

            }
        }

        if (grid.isAllVisited()) {
            Maze.generated = true;
        }

    }
}
