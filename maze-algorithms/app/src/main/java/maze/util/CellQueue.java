package maze.util;

public class CellQueue {
    private Cell[] queueItems;
    private int first;
    private int last;
    private int capacity = 10;
    private int realSize;
    private int currentSize;

    public CellQueue() {
        queueItems = new Cell[capacity];
        first = 0;
        last = -1;
        currentSize = 0;
        realSize = 0;
    }

    /**
     * Lisää jonon perälle solun
     * 
     * @param cell
     */
    public void enqueue(Cell cell) {
        if (isFull()) {
            ensureCapacity();
        }

        queueItems[++last] = cell;
        currentSize++;
        realSize++;

    }

    /**
     * Poistaa ja palauttaa jonossa ensimmäiseksi olevan solun
     * 
     * @throws QueueEmptyException
     * @return Cell
     */
    public Cell pop() {
        if (isEmpty()) {
            throw new QueueEmptyException("Cannot remove from empty queue");
        }
        currentSize--;
        return queueItems[first++];
    }

    /**
     * Palauttaa totuusarvon siitä onko jono tyhjä
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Kertoo onko jono täynnä
     * 
     * @return boolean
     */
    public boolean isFull() {
        return realSize == capacity;
    }

    /**
     * Palauttaa jonon koon
     * 
     * @return int
     */
    public int size() {
        return currentSize;
    }

    private void ensureCapacity() {
        int newSize = queueItems.length * 2;
        Cell[] copy = new Cell[newSize];
        for (int i = 0; i < queueItems.length; i++) {
            copy[i] = queueItems[i];
        }
        queueItems = copy;
        capacity = newSize;
    }
}
