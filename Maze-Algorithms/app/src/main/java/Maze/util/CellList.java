package maze.util;

import maze.gui.Cell;

public class CellList {

    private int size = 0;

    private static final int DEFAULT_SIZE = 10;

    private Cell cells[];

    public CellList() {
        cells = new Cell[DEFAULT_SIZE];
    }

    public void add(Cell cell) {
        if (size == cells.length) {
            ensureCapacity();
        }

        cells[size++] = cell;
    }

    public Cell get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        return cells[index];
    }

    public Cell remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        Cell cell = cells[index];
        int newSize = size - (index + 1);
        System.arraycopy(cells, index + 1, cells, index, newSize);
        size--;
        return cell;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        int newSize = cells.length * 2;
        Cell[] copy = new Cell[newSize];
        for (int i = 0; i < cells.length; i++) {
            copy[i] = cells[i];
        }
        cells = copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(cells[i].toString());
            if (i < size - 1) {
                sb.append(",");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
