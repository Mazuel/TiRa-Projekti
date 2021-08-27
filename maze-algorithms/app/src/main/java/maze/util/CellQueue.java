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

    public void push(Cell cell) {
        if (isFull()) {
            ensureCapacity();
        }

        queueItems[++last] = cell;
        currentSize++;
        realSize++;

    }

    public Cell pop() {
        if (isEmpty()) {
            throw new QueueEmptyException("Cannot remove from empty queue");
        }
        currentSize--;
        return queueItems[first++];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return realSize == capacity;
    }

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
