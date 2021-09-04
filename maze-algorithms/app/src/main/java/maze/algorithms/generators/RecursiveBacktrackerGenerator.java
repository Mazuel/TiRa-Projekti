package maze.algorithms.generators;

import java.util.Random;

import maze.gui.Maze;
import maze.gui.MazeGridPanel;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.CellStack;
import maze.util.GeneratorAlgorithm;

/**
 * Satunnaistetty syvyyshaku </br>
 * 
 * 0. Aloitus. Valitaan satunnaisesti solu, josta aloitetaan algoritmin suoritus
 * 
 * 1. Valitse nykyisen solun satunnainen naapuri, jossa ei ole vierailtu </br>
 * 
 * 2. Jos naapuri löytyi, lisää nykyinen solu pinoon ja poista seinä nykyisen
 * solun ja naapurin väliltä. Jos naapuria ei löytynyt, otetaan pinosta
 * aikaisemmin vierailtu solu </br>
 * 
 * 3. Toistetaan askelia 1 ja 2 kunnes pino on tyhjä
 */
public class RecursiveBacktrackerGenerator implements GeneratorAlgorithm {

    private CellList grid;
    private CellStack cellStack;

    private Cell currentCell;
    private Random randomCellPicker = new Random();

    public RecursiveBacktrackerGenerator(MazeGridPanel mazeGridPanel) {
        this.grid = mazeGridPanel.getGrid();
        this.currentCell = grid.get(randomCellPicker.nextInt(grid.size()));
        this.cellStack = new CellStack(grid.size() * 10);
    }

    @Override
    public void generate() {
        grid.markCellAsVisited(currentCell);

        CellList unvisitedNeighbours = currentCell.getUnvisitedNeighbours(grid);
        Cell nextDirection = null;
        if (unvisitedNeighbours.size() > 0) {
            // 1
            nextDirection = unvisitedNeighbours.get(randomCellPicker.nextInt(unvisitedNeighbours.size()));
        }

        if (nextDirection != null) {
            // 2
            cellStack.push(currentCell);
            currentCell.removeWall(nextDirection);
            currentCell = nextDirection;
        } else if (cellStack.isNotEmpty()) {
            // 3
            currentCell.setDeadEnd(true);
            currentCell = cellStack.pop();
        } else {
            // 4
            currentCell.setDeadEnd(true);
            Maze.generated = true;
        }
    }
}
