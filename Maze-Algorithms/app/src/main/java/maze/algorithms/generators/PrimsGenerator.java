package maze.algorithms.generators;

import java.util.HashSet;
import java.util.Random;

import maze.gui.Maze;
import maze.gui.MazeGridPanel;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.GeneratorAlgorithm;

public class PrimsGenerator implements GeneratorAlgorithm {

    private final CellList grid;
    private int index;
    private Random randomCellPicker = new Random();
    private Cell currentCell;

    private HashSet<Cell> frontierCells;
    private HashSet<Cell> visitedCells;

    public PrimsGenerator(MazeGridPanel mazeGridPanel) {
        this.grid = mazeGridPanel.getGrid();
        this.index = randomCellPicker.nextInt(grid.size());
        this.currentCell = grid.get(index);
        this.frontierCells = new HashSet<Cell>();
        this.visitedCells = new HashSet<Cell>();
        visitedCells.add(currentCell);
        currentCell.setVisited(true);

    }

    @Override
    public void generate() {
        CellList neighbours = currentCell.getUnvisitedNeighbours(grid);
        for (int i = 0; i < neighbours.size(); i++) {
            frontierCells.add(neighbours.get(i));
        }

        CellList frontierCellArray = new CellList();
        frontierCells.forEach((cell) -> {
            frontierCellArray.add(cell);
        });
        currentCell = frontierCellArray.get(randomCellPicker.nextInt(frontierCellArray.size()));
        frontierCells.remove(currentCell);
        visitedCells.add(currentCell);
        currentCell.setVisited(true);

        CellList visitedNeighbours = currentCell.getVisitedNeighbours(grid);

        Cell randomVisitedNeighbour = null;
        while (randomVisitedNeighbour == null) {
            int randomIndex = randomCellPicker.nextInt(visitedNeighbours.size());
            Cell randomCell = visitedNeighbours.get(randomCellPicker.nextInt(visitedNeighbours.size()));
            if (visitedCells.contains(randomCell)) {
                randomVisitedNeighbour = randomCell;
            } else {
                visitedNeighbours.remove(randomIndex);
            }
        }

        currentCell.removeWall(randomVisitedNeighbour);

        if (frontierCells.isEmpty()) {
            Maze.generated = true;
        }

    }
}
