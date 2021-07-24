package maze.algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import maze.gui.Cell;
import maze.gui.MazeGridPanel;
import maze.util.CellList;

public class BinaryTreeGenerator {

    private final CellList grid;
    private Cell currentCell;
    private int index;
    private final Random random = new Random();
    private int renderSpeed = 2;

    public BinaryTreeGenerator(MazeGridPanel mazeGridPanel) {
        this.grid = mazeGridPanel.getGrid();
        this.index = 0;
        this.currentCell = grid.get(index);

        Timer timer = new Timer(renderSpeed, null);

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < grid.size()) {
                    generate();
                } else {
                    currentCell = null;
                    timer.stop();
                }
                currentCell = grid.get(++index);
                mazeGridPanel.repaint();
                timer.setDelay(renderSpeed);
            }
        });
        timer.start();
    }

    private void generate() {
        System.out.println(currentCell);
        currentCell.setVisited(true);
    }
}
