package maze.algorithm.solvers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

import java.awt.Graphics;

import javax.swing.Timer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import maze.algorithms.generators.BinaryTreeGenerator;
import maze.algorithms.solvers.BfsSolver;
import maze.gui.Maze;
import maze.gui.MazeGridPanel;

public class BfsSolverTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private Graphics graphics;
    @Mock
    private Timer timer;

    private MazeGridPanel mazeGridPanel;

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        mazeGridPanel = spy(new MazeGridPanel(32, 32));
        doNothing().when(mazeGridPanel).repaint();
        doNothing().when(timer).start();
        Maze.generated = false;
        Maze.solved = false;
    }

    @Test
    public void shouldNotHaveVisitedAllCellsOnInvalidMaze() {
        BinaryTreeGenerator binaryTreeGenerator = new BinaryTreeGenerator(mazeGridPanel);
        while (!Maze.generated) {
            binaryTreeGenerator.generate();
        }
        mazeGridPanel.getGrid().setAllUnvisited();
        mazeGridPanel.getGrid().get(2).setWalls(new boolean[] { true, true, true, true });

        BfsSolver solver = new BfsSolver(mazeGridPanel);

        while (!Maze.solved) {
            solver.solve();
        }

        assertFalse(mazeGridPanel.getGrid().isAllVisited());

    }

    @Test
    public void shouldHaveVisitedAllCellsOnValidMaze() {
        BinaryTreeGenerator binaryTreeGenerator = new BinaryTreeGenerator(mazeGridPanel);
        while (!Maze.generated) {
            binaryTreeGenerator.generate();
        }
        mazeGridPanel.getGrid().setAllUnvisited();

        BfsSolver solver = new BfsSolver(mazeGridPanel);

        while (!Maze.solved) {
            solver.solve();
        }

        assertTrue(mazeGridPanel.getGrid().isAllVisited());
    }
}
