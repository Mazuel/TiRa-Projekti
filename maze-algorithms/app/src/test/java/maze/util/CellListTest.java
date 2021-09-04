package maze.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CellListTest {

    private CellList cellList;

    @Before
    public void initialize() {
        cellList = new CellList();
        for (int i = 0; i < 50; i++) {
            cellList.add(new Cell(i, i));
        }
    }

    @Test
    public void shouldCreateEmptyList() {
        CellList cellList = new CellList();
        assertTrue(cellList.size() == 0);

        for (int i = 0; i < 50; i++) {
            cellList.add(new Cell(i, i));
        }
    }

    @Test
    public void shouldAddNewCellToList() {
        cellList.add(new Cell(51, 51));
        assertTrue(cellList.size() == 51);
    }

    @Test
    public void shouldAddMultipleCells() {
        assertTrue(cellList.size() == 50);
    }

    @Test
    public void shouldContainCell() {
        assertTrue(cellList.contains(new Cell(12, 12)));
    }

    @Test
    public void shouldRemoveCellFromIndex() {
        cellList.remove(0);
        assertEquals(49, cellList.size());
    }

    @Test
    public void shouldThrowErrorWithIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            cellList.get(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            cellList.remove(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            cellList.remove(51);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            cellList.get(51);
        });
    }

    @Test
    public void shouldNotContainCell() {
        assertFalse(cellList.contains(new Cell(51, 51)));
    }

    @Test
    public void shouldNotContainNullCell() {
        assertFalse(cellList.contains(null));
    }

    @Test
    public void shouldNotFindIndexOfCell() {
        assertEquals(-1, cellList.indexOf(new Cell(51, 51)));
    }

    @Test
    public void shouldNotHaveVisitedAllCells() {
        for (int i = 0; i < 25; i++) {
            cellList.markCellAsVisited(cellList.get(i));
        }
        assertFalse(cellList.isAllVisited());
    }

    @Test
    public void shouldHaveVisitedAllCells() {
        for (int i = 0; i < cellList.size(); i++) {
            cellList.markCellAsVisited(cellList.get(i));
        }

        assertTrue(cellList.isAllVisited());
    }

    @Test
    public void shouldSetAllCellsUnvisited() {
        for (int i = 0; i < cellList.size(); i++) {
            cellList.markCellAsVisited(cellList.get(i));
        }

        assertTrue(cellList.isAllVisited());
        cellList.setAllUnvisited();
        assertFalse(cellList.isAllVisited());
    }

}
