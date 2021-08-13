package maze.algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import maze.gui.Maze;
import maze.gui.MazeGridPanel;
import maze.util.Cell;
import maze.util.CellList;
import maze.util.CellStack;

public class RecursiveBacktrackerGenerator {

    private CellList grid;
    private CellStack cellStack;

    private Cell currentCell;
    private int renderSpeed = 10;
    private Random randomCellPicker = new Random();

    public RecursiveBacktrackerGenerator(MazeGridPanel mazeGridPanel) {
        this.grid = mazeGridPanel.getGrid();
        this.currentCell = grid.get(randomCellPicker.nextInt(grid.size()));
        this.cellStack = new CellStack(grid.size() * 10);

        // Asetetaan pieni viive jokaisen askeleen väliin, jotta voidaan hahmottaa
        // algoritmin toimintaa helpommin
        Timer timer = new Timer(renderSpeed, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Maze.generated) {
                    generate();
                } else {
                    timer.stop();
                    Maze.algorithmInAction = false;
                }
                mazeGridPanel.repaint();
            }
        });
        timer.start();
    }

    // Algoritmin toiminta sen jälkeen kun aloitus solu on valittu:
    // 1. Valitse nykyisen solun satunnainen naapuri, jossa ei ole vierailtu
    // 2. Jos naapuri löytyi, lisää nykyinen solu pinoon ja poista seinä nykyisen
    // solun ja naapurin väliltä
    // 3. Jos naapuria ei löytynyt, otetaan pinosta aikaisemmin vierailtu solu
    // 4. Toistetaan askelia 1-3 kunnes pino on tyhjä

    private void generate() {
        currentCell.setVisited(true);

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
            currentCell.setRecursiveVisit(true);
            currentCell = cellStack.pop();
        } else {
            // 4
            currentCell.setRecursiveVisit(true);
            Maze.generated = true;
        }
    }
}
