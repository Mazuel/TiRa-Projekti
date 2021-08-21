package maze.algorithm;

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

import maze.algorithms.BfsSolver;
import maze.algorithms.BinaryTreeGenerator;
import maze.gui.Maze;
import maze.gui.MazeGridPanel;

public class BinaryTreeGeneratorTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private Graphics graphics;
    @Mock
    private Timer timer;

    private MazeGridPanel mazeGridPanel;
    private BinaryTreeGenerator generator;

    @Before
    public void setUp() throws Exception {
        mazeGridPanel = spy(new MazeGridPanel(32, 32));
        generator = new BinaryTreeGenerator(mazeGridPanel);
        doNothing().when(mazeGridPanel).repaint();
        doNothing().when(timer).start();
        Maze.generated = false;
    }

    private void generateMaze() {
        while (!Maze.generated) {
            generator.generate();
        }
    }

    @Test
    public void shouldGenerateMazeAndHaveVisitedAllCells() {
        assertFalse(mazeGridPanel.getGrid().isAllVisited());
        generateMaze();
        assertTrue(Maze.generated);
        assertTrue(mazeGridPanel.getGrid().isAllVisited());
    }

    @Test
    public void shouldHaveGeneratedValidMaze() {
        generateMaze();
        BfsSolver solver = new BfsSolver(mazeGridPanel);
        while (!Maze.solved) {
            solver.solve();
        }
        assertTrue(mazeGridPanel.getGrid().isAllVisited());
    }

}
