package maze.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CellTest {

    private CellList testGrid;

    @Before
    public void initialize() {
        testGrid = new CellList();
    }

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

    @Test
    public void shouldReturnAllNeighbours() {
        Cell currentCell = new Cell(10, 10);
        Cell topNeighbour = new Cell(10, 9);
        Cell bottomNeighbour = new Cell(10, 11);
        Cell leftNeighbour = new Cell(11, 10);
        Cell rightNeighbour = new Cell(9, 10);

        testGrid.add(currentCell);
        testGrid.add(topNeighbour);
        testGrid.add(bottomNeighbour);
        testGrid.add(leftNeighbour);
        testGrid.add(rightNeighbour);

        assertEquals(4, currentCell.getNeighbours(testGrid).size());
    }

    @Test
    public void shouldReturnOnlyVisitedNeighbours() {

        Cell currentCell = new Cell(10, 10);
        Cell topNeighbour = new Cell(10, 9);
        Cell bottomNeighbour = new Cell(10, 11);
        Cell leftNeighbour = new Cell(11, 10);
        Cell rightNeighbour = new Cell(9, 10);

        testGrid.markCellAsVisited(leftNeighbour);

        testGrid.add(currentCell);
        testGrid.add(topNeighbour);
        testGrid.add(bottomNeighbour);
        testGrid.add(leftNeighbour);
        testGrid.add(rightNeighbour);

        assertEquals(1, currentCell.getVisitedNeighbours(testGrid).size());
    }

    @Test
    public void shouldReturnUnvisitedNeigbours() {
        Cell currentCell = new Cell(10, 10);
        Cell topNeighbour = new Cell(10, 9);
        Cell bottomNeighbour = new Cell(10, 11);
        Cell leftNeighbour = new Cell(11, 10);
        Cell rightNeighbour = new Cell(9, 10);
        Cell farRightNeihbour = new Cell(8, 10);

        testGrid.markCellAsVisited(leftNeighbour);
        testGrid.markCellAsVisited(rightNeighbour);

        testGrid.add(currentCell);
        testGrid.add(topNeighbour);
        testGrid.add(bottomNeighbour);
        testGrid.add(leftNeighbour);
        testGrid.add(rightNeighbour);
        testGrid.add(farRightNeihbour);

        assertEquals(2, currentCell.getUnvisitedNeighbours(testGrid).size());
    }

    @Test
    public void shouldNotReturnNeighbours() {
        Cell currentCell = new Cell(10, 10);
        Cell topNeighbour = new Cell(12, 9);
        Cell bottomNeighbour = new Cell(14, 11);
        Cell leftNeighbour = new Cell(15, 10);
        Cell rightNeighbour = new Cell(9, 14);

        testGrid.add(currentCell);
        testGrid.add(topNeighbour);
        testGrid.add(bottomNeighbour);
        testGrid.add(leftNeighbour);
        testGrid.add(rightNeighbour);

        assertEquals(0, currentCell.getNeighbours(testGrid).size());
    }

    @Test
    public void shouldReturnTrueForEqualCells() {
        Cell cell = new Cell(10, 10);
        Cell copyCell = cell;
        Cell equalCell = new Cell(10, 10);

        assertTrue(cell.equals(copyCell));
        assertTrue(cell.equals(equalCell));
    }

    @Test
    public void shouldOnlyReturnValidUnvisitedNeighbours() {
        Cell currentCell = new Cell(10, 10);
        Cell topNeighbour = new Cell(10, 9);
        Cell bottomNeighbour = new Cell(10, 11);
        Cell leftNeighbour = new Cell(11, 10);
        Cell rightNeighbour = new Cell(9, 10);

        Cell farRightNeihbour = new Cell(8, 10);

        currentCell.setWalls(new boolean[] { true, false, true, false });
        topNeighbour.setWalls(new boolean[] { true, true, true, true });
        bottomNeighbour.setWalls(new boolean[] { true, true, true, true });
        leftNeighbour.setWalls(new boolean[] { true, true, true, false });
        rightNeighbour.setWalls(new boolean[] { true, true, true, true });

        testGrid.markCellAsVisited(rightNeighbour);

        testGrid.add(currentCell);
        testGrid.add(topNeighbour);
        testGrid.add(bottomNeighbour);
        testGrid.add(leftNeighbour);
        testGrid.add(rightNeighbour);
        testGrid.add(farRightNeihbour);

        assertEquals(1, currentCell.getUnvisitedValidMoveNeighbours(testGrid).size());
    }

    @Test
    public void shouldResetCell() {
        Cell cell = new Cell(10, 10);
        cell.setBfsShortestPath(true);
        cell.setCursor(true);
        cell.setVisited(true);
        cell.setWalls(new boolean[] { false, false, false, false });

        cell.reset();

        assertFalse(cell.isBfsShortestPath());
        assertFalse(cell.isCursor());
        assertFalse(cell.isVisited());
        assertTrue(cell.getWalls()[0] && cell.getWalls()[1] && cell.getWalls()[2] && cell.getWalls()[3]);
    }

}
