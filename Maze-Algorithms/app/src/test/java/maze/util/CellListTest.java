package maze.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import maze.gui.Cell;

public class CellListTest {

    @Test
    public void shouldCreateEmptyList() {
        CellList cellList = new CellList();
        assertTrue(cellList.size() == 0);
    }

    @Test
    public void shouldAddNewCellToList() {
        CellList cellList = new CellList();
        cellList.add(new Cell(0, 0));
        assertTrue(cellList.size() == 1);
    }

    @Test
    public void shouldAddMultipleCells() {
        CellList cellList = new CellList();

        for (int i = 0; i < 50; i++) {
            cellList.add(new Cell(i, i));
        }
        assertTrue(cellList.size() == 50);
    }

}
