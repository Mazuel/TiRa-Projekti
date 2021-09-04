package maze.algorithms.generators;

import java.util.HashSet;
import java.util.Random;

import maze.gui.Maze;
import maze.gui.MazeGridPanel;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.GeneratorAlgorithm;

/**
 * Primin algoritmi </br>
 * 
 * 1. Valitaan satunnaisesti solu, josta aloitetaan ja asetataan se vierailluksi
 * </br>
 * 
 * 2. Lisätään käsiteltävän solun naapurit 'rajasolu' -joukkoon
 * 
 * 3. Valitaan satunnaisesti seuraavaksi käsiteltävä solu 'rajasolu' -joukosta.
 * Merkitään tämä uusi käsittelyssä oleva solu vierailluksi. </br>
 * 
 * 4. Valitaan satunnaisesti käsiteltävänä olevan solun naapuri, jossa on jo
 * vierailtu ja poistetaan näiden väliltä seinä.</br>
 * 
 * 5. Toistetaan askelia 2-4, kunnes 'rajasolu' -joukko on tyhjä
 * 
 */
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
        grid.markCellAsVisited(currentCell);

    }

    @Override
    public void generate() {
        CellList neighbours = currentCell.getUnvisitedNeighbours(grid);
        for (int i = 0; i < neighbours.size(); i++) {
            neighbours.get(i).setCursor(true);
            frontierCells.add(neighbours.get(i));
        }

        CellList frontierCellArray = new CellList();
        frontierCells.forEach((cell) -> {
            frontierCellArray.add(cell);
        });
        currentCell = frontierCellArray.get(randomCellPicker.nextInt(frontierCellArray.size()));
        frontierCells.remove(currentCell);
        visitedCells.add(currentCell);
        grid.markCellAsVisited(currentCell);
        currentCell.setCursor(false);

        CellList visitedNeighbours = currentCell.getVisitedNeighbours(grid);

        Cell randomVisitedNeighbour = null;
        while (randomVisitedNeighbour == null) {
            Cell randomCell = visitedNeighbours.get(randomCellPicker.nextInt(visitedNeighbours.size()));
            if (visitedCells.contains(randomCell)) {
                randomVisitedNeighbour = randomCell;
            }
        }

        currentCell.removeWall(randomVisitedNeighbour);

        if (frontierCells.isEmpty()) {
            Maze.generated = true;
        }

    }
}
