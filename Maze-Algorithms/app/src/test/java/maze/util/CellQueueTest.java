package maze.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CellQueueTest {

    CellQueue cellQueue;

    @Before
    public void setup() {
        cellQueue = new CellQueue();
        for (int i = 0; i < 50; i++) {
            cellQueue.push(new Cell(i, i));
        }
    }

    @Test
    public void shouldPushToQueue() {
        assertEquals(50, cellQueue.size());
    }

    @Test
    public void shouldPopCorrectCellFromQueue() {
        cellQueue.pop();
        cellQueue.pop();
        cellQueue.pop();
        Cell cell = cellQueue.pop();
        assertEquals(3, cell.getX());
        assertEquals(3, cell.getY());
        assertEquals(46, cellQueue.size());
    }
}
