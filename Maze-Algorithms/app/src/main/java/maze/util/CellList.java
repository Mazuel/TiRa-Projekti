package maze.util;

public class CellList {

    private int size = 0;

    private static final int DEFAULT_SIZE = 10;

    private Cell[] cells;

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
        checkIndexInBounds(index);
        return cells[index];
    }

    public Cell remove(int index) {
        checkIndexInBounds(index);
        Cell cell = cells[index];
        int newSize = size - (index + 1);
        System.arraycopy(cells, index + 1, cells, index, newSize);
        size--;
        return cell;
    }

    private void checkIndexInBounds(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    }

    public int size() {
        return size;
    }

    public boolean isAllVisited() {
        for (int i = 0; i < size; i++) {
            if (!cells[i].isVisited()) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(Cell cell) {
        if (cell == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (cells[i].equals(cell)) {
                return true;
            }
        }
        return false;
    }

    private void ensureCapacity() {
        int newSize = cells.length * 2;
        Cell[] copy = new Cell[newSize];
        for (int i = 0; i < cells.length; i++) {
            copy[i] = cells[i];
        }
        cells = copy;
    }

    public int indexOf(Cell cell) {
        for (int i = 0; i < size; i++) {
            if (cells[i].equals(cell)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
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
