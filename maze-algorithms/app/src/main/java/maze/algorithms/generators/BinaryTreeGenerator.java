package maze.algorithms.generators;

import java.util.Random;

import maze.gui.Maze;
import maze.gui.MazeGridPanel;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.Direction;
import maze.util.GeneratorAlgorithm;

/**
 * Binääripuu -algoritmi </br>
 * 1. Valitaan aloitussoluksi viimeinen solu (tässä tapauksessa oikea
 * alakulma)</br>
 * 2. Jos löytyy ylänaapuri ja vasemman puoleinen naapuri, valitaan
 * satunnaisesti kumman välillä poistetaan seinä</br>
 * 3. Jos löytyy vain toinen naapuri, valitaan se ja jos ei löydy yhtäkään
 * naapuria niin ei tapahdu mitään</br>
 * 4. Valitaan nykyiseksi soluksi järjestyksessä edellinen solu, koska
 * aloitettiin taulukon viimeisestä solusta</br>
 * 5. Toistetaan kohtia 2-4, kunnes jokaisessa solussa on vieraltu kerran
 */
public class BinaryTreeGenerator implements GeneratorAlgorithm {

    private int index;

    private final CellList grid;
    private Cell currentCell;
    private Cell previousCell;
    private Random randomDirection = new Random();

    public BinaryTreeGenerator(MazeGridPanel mazeGridPanel) {
        this.grid = mazeGridPanel.getGrid();
        this.index = grid.size() - 1;
        this.currentCell = grid.get(index);
    }

    @Override
    public void generate() {
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
        grid.markCellAsVisited(currentCell);
        currentCell.setCursor(true);

        if (index - 1 >= 0) {
            previousCell = currentCell;
            currentCell = grid.get(--index);
        } else {
            Maze.generated = true;
            currentCell.setCursor(false);
        }
    }
}
