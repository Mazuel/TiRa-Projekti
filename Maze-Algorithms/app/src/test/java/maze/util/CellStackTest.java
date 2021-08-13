package maze.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CellStackTest {
    private CellStack cellStack;

    @Before
    public void initialize() {
        this.cellStack = new CellStack(5);
    }

    @Test
    public void shouldReturnCorrectSize() {
        this.cellStack.push(new Cell(10, 10));
        this.cellStack.push(new Cell(11, 11));
        assertTrue(2 == cellStack.size());
    }

    @Test
    public void shouldPopTopCell() {
        this.cellStack.push(new Cell(10, 10));
        this.cellStack.push(new Cell(11, 11));
        Cell topCell = cellStack.pop();

        assertEquals(topCell.getX(), 11);
        assertEquals(topCell.getY(), 11);
    }

    @Test
    public void shouldThrowErrorWhenPoppingEmptyStack() {
        assertThrows(StackEmptyError.class, () -> {
            cellStack.pop();
        });
    }

    @Test
    public void shouldThrowErrorWhenPushingToFullStack() {
        this.cellStack.push(new Cell(10, 10));
        this.cellStack.push(new Cell(11, 11));
        this.cellStack.push(new Cell(12, 12));
        this.cellStack.push(new Cell(13, 13));
        this.cellStack.push(new Cell(14, 14));

        assertThrows(StackOverflowError.class, () -> {
            cellStack.push(new Cell(15, 15));
        });
    }

    @Test
    public void shouldReturnTrueWhenStackIsNotEmpty() {
        this.cellStack.push(new Cell(10, 10));
        assertTrue(cellStack.isNotEmpty());
    }

    @Test
    public void shouldReturnFalseWhenStackIsEmpty() {
        assertFalse(cellStack.isNotEmpty());
    }
}
