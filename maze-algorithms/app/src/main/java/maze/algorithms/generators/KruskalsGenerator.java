package maze.algorithms.generators;

import maze.gui.Maze;
import maze.gui.MazeGridPanel;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.CellStack;
import maze.util.DisjointSet;
import maze.util.GeneratorAlgorithm;

/**
 * Kruskalin algoritmi </br>
 * 
 * 1. Luodaan jokaiselle solulle oma joukko, pusketaan solut pinoon ja laitetaan
 * pino satunnaiseen järjestykseen </br>
 * 
 * 2. Otetaan pinosta solu ja asetetaan se vierailluksi </br>
 * 
 * 3. Käydään solun naapurit läpi. Jos naapuri kuuluu eri joukkoon kuin nykyinen
 * solu, poistetaan seinä näiden väliltä ja yhdistetään joukot </br>
 * 
 * 4. Algoritmin suoritus loppuu kun pino on tyhjä
 * 
 */
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
        grid.markCellAsVisited(currentCell);

        CellList neighbours = currentCell.getNeighbours(grid);

        for (int i = 0; i < neighbours.size(); i++) {
            Cell neighbour = neighbours.get(i);
            if (disjointSet.find(currentCell.getId()) != disjointSet.find(neighbour.getId())) {
                currentCell.removeWall(neighbour);
                grid.markCellAsVisited(neighbour);
                disjointSet.union(currentCell.getId(), neighbour.getId());
            }
        }

        if (stack.isEmpty()) {
            Maze.generated = true;
        }
    }

}
