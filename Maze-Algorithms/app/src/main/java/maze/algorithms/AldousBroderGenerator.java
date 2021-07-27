package maze.algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import maze.gui.Cell;
import maze.gui.MazeGridPanel;

public class AldousBroderGenerator {
    
    private final int STEP_TIME_UNIT = 5;
    private Cell currentCell;
    private int index;

    public AldousBroderGenerator(MazeGridPanel gridPanel) {
        createStepTimer(gridPanel).start();
    }

    private Timer createStepTimer(MazeGridPanel gridPanel) {
        Timer stepTimer = new Timer(STEP_TIME_UNIT, null);
        stepTimer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (index < gridPanel.getGrid().size() - 1) {
                    //generate();
                } else {
                    stepTimer.stop();
                }
            }
        });
        return stepTimer;
    }
}
