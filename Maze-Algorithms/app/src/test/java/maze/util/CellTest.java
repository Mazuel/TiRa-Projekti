package maze.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CellTest {

    @Test
    public void shouldRemoveTopWall() {
        Cell currentCell = new Cell(10, 10);
        Cell topNeighbour = new Cell(10, 9);

        currentCell.removeWall(topNeighbour);

        assertFalse(currentCell.getWalls()[0]);
        assertFalse(topNeighbour.getWalls()[2]);

        boolean[] currentCellWalls = currentCell.getWalls();
        assertTrue(currentCellWalls[1]);
        assertTrue(currentCellWalls[2]);
        assertTrue(currentCellWalls[3]);

        boolean[] topNeighbourWalls = topNeighbour.getWalls();
        assertTrue(topNeighbourWalls[0]);
        assertTrue(topNeighbourWalls[1]);
        assertTrue(topNeighbourWalls[3]);
    }

    @Test
    public void shouldRemoveRightWall() {
        Cell currentCell = new Cell(10, 10);
        Cell rightNeighbour = new Cell(11, 10);

        currentCell.removeWall(rightNeighbour);

        assertFalse(currentCell.getWalls()[1]);
        assertFalse(rightNeighbour.getWalls()[3]);

        boolean[] currentCellWalls = currentCell.getWalls();
        assertTrue(currentCellWalls[0]);
        assertTrue(currentCellWalls[2]);
        assertTrue(currentCellWalls[3]);

        boolean[] rightNeighbourWalls = rightNeighbour.getWalls();
        assertTrue(rightNeighbourWalls[0]);
        assertTrue(rightNeighbourWalls[1]);
        assertTrue(rightNeighbourWalls[2]);
    }

    @Test
    public void shouldRemoveLeftWall() {
        Cell currentCell = new Cell(10, 10);
        Cell leftNeighbour = new Cell(9, 10);

        currentCell.removeWall(leftNeighbour);

        assertFalse(currentCell.getWalls()[3]);
        assertFalse(leftNeighbour.getWalls()[1]);

        boolean[] currentCellWalls = currentCell.getWalls();
        assertTrue(currentCellWalls[0]);
        assertTrue(currentCellWalls[2]);
        assertTrue(currentCellWalls[1]);

        boolean[] leftNeighboutWalls = leftNeighbour.getWalls();
        assertTrue(leftNeighboutWalls[0]);
        assertTrue(leftNeighboutWalls[2]);
        assertTrue(leftNeighboutWalls[3]);
    }

    @Test
    public void shouldRemoveBottomWall() {
        Cell currentCell = new Cell(10, 10);
        Cell bottomNeighbour = new Cell(10, 11);

        currentCell.removeWall(bottomNeighbour);

        assertFalse(currentCell.getWalls()[2]);
        assertFalse(bottomNeighbour.getWalls()[0]);

        boolean[] currentCellWalls = currentCell.getWalls();
        assertTrue(currentCellWalls[0]);
        assertTrue(currentCellWalls[1]);
        assertTrue(currentCellWalls[3]);

        boolean[] bottomNeighbourWalls = bottomNeighbour.getWalls();
        assertTrue(bottomNeighbourWalls[1]);
        assertTrue(bottomNeighbourWalls[2]);
        assertTrue(bottomNeighbourWalls[3]);
    }

}
