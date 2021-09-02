package maze.algorithms.generators;

import maze.gui.Maze;
import maze.gui.MazeGridPanel;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.CellStack;
import maze.util.DisjointSet;
import maze.util.GeneratorAlgorithm;

public class KruskalsGenerator implements GeneratorAlgorithm {

    private CellStack stack;
    private CellList grid;
    private Cell currentCell;
    private DisjointSet disjointSet = new DisjointSet();

    public KruskalsGenerator(MazeGridPanel mazeGridPanel) {
        this.grid = mazeGridPanel.getGrid();
        this.stack = new CellStack(grid.size() * 10);
        this.currentCell = grid.get(0);

        disjointSet.createSet(grid);

        for (int i = 0; i < grid.size(); i++) {
            stack.push(grid.get(i));
        }

        stack.shuffle();
    }

    public void generate() {
        currentCell = stack.pop();
        currentCell.setVisited(true);

        CellList neighbours = currentCell.getNeighbours(grid);

        for (int i = 0; i < neighbours.size(); i++) {
            Cell neighbour = neighbours.get(i);
            if (disjointSet.find(currentCell.getId()) != disjointSet.find(neighbour.getId())) {
                currentCell.removeWall(neighbour);
                disjointSet.union(currentCell.getId(), neighbour.getId());
            }
        }

        stack.shuffle();

        if (stack.isEmpty()) {
            Maze.generated = true;
        }
    }

}
