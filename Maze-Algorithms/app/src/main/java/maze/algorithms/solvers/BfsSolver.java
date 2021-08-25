package maze.algorithms.solvers;

import maze.gui.Maze;
import maze.gui.MazeGridPanel;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.CellQueue;

public class BfsSolver {
    private CellList grid;
    private CellQueue cellQueue;
    private Cell startCell;

    public BfsSolver(MazeGridPanel gridpanel) {
        this.grid = gridpanel.getGrid();
        cellQueue = new CellQueue();
        this.startCell = grid.get(0);
        cellQueue.push(startCell);
    }

    public void solve() {
        Cell currentCell = cellQueue.pop();
        currentCell.setVisited(true);

        CellList neighbours = currentCell.getUnvisitedValidMoveNeighbours(grid);
        for (int i = 0; i < neighbours.size(); i++) {
            Cell neighbour = neighbours.get(i);
            neighbour.setVisited(true);
            cellQueue.push(neighbour);
        }

        if (cellQueue.isEmpty()) {
            Maze.solved = true;
        }
    }
}
