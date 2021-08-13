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

    private void generate() {
        currentCell.setVisited(true);

        CellList unvisitedNeighbours = currentCell.getUnvisitedNeighbours(grid);
        Cell nextDirection = null;
        if (unvisitedNeighbours.size() > 0) {
            nextDirection = unvisitedNeighbours.get(randomCellPicker.nextInt(unvisitedNeighbours.size()));
        }

        if (nextDirection != null) {
            cellStack.push(currentCell);
            currentCell.removeWall(nextDirection);
            currentCell = nextDirection;
        } else if (cellStack.isNotEmpty()) {
            currentCell.setRecursiveVisit(true);
            currentCell = cellStack.pop();
            currentCell.setRecursiveVisit(true);
        } else {
            Maze.generated = true;
        }
    }
}
