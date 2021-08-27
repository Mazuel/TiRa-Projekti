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
    private Cell endPoint;

    public BfsSolver(MazeGridPanel gridpanel) {
        this.grid = gridpanel.getGrid();
        cellQueue = new CellQueue();
        this.startCell = grid.get(0);
        this.endPoint = grid.get(grid.size() - 1);
        cellQueue.push(startCell);
    }

    public void solve() {
        Cell currentCell = cellQueue.pop();
        currentCell.setVisited(true);

        if (currentCell == endPoint) {
            colorPath(currentCell);
        }

        CellList neighbours = currentCell.getUnvisitedValidMoveNeighbours(grid);
        for (int i = 0; i < neighbours.size(); i++) {
            Cell neighbour = neighbours.get(i);
            neighbour.setVisited(true);
            neighbour.setParent(currentCell);
            cellQueue.push(neighbour);
        }

        if (cellQueue.isEmpty()) {
            Maze.solved = true;
        }
    }

    private void colorPath(Cell cell) {
        cell.setBfsShortestPath(true);
        Cell parent = cell.getParent();
        while (parent != null) {
            parent.setBfsShortestPath(true);
            parent = parent.getParent();
        }
    }
}
