package maze.util;

public class CellList {

    private int size = 0;

    private static final int DEFAULT_SIZE = 10;

    private Cell[] cells;

    public CellList() {
        cells = new Cell[DEFAULT_SIZE];
    }

    /**
     * Lisää solun listaan
     * 
     * @param cell
     */
    public void add(Cell cell) {
        if (size == cells.length) {
            ensureCapacity();
        }

        cells[size++] = cell;
    }

    /**
     * Palauttaa indeksissä olevan solun, mikäli indeksi on validi
     * 
     * @param index
     * @throws IndexOutOfBoundsException
     * @return Cell
     */
    public Cell get(int index) {
        checkIndexInBounds(index);
        return cells[index];
    }

    /**
     * @param index
     * @return Cell
     */
    public Cell remove(int index) {
        checkIndexInBounds(index);
        Cell cell = cells[index];
        int newSize = size - (index + 1);
        System.arraycopy(cells, index + 1, cells, index, newSize);
        size--;
        return cell;
    }

    /**
     * @param index
     */
    private void checkIndexInBounds(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    }

    /**
     * Palauttaa listan koon
     * 
     * @return int
     */
    public int size() {
        return size;
    }

    public void setAllUnvisited() {
        for (int i = 0; i < size; i++) {
            cells[i].setVisited(false);
            cells[i].setRecursiveVisit(false);
        }
    }

    /**
     * Kertoo onko kaikissa soluissa vierailtu
     * 
     * @return boolean
     */
    public boolean isAllVisited() {
        for (int i = 0; i < size; i++) {
            if (!cells[i].isVisited()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Kertoo sisältääkö lista solun
     * 
     * @param cell
     * @return boolean
     */
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

    /**
     * Palauttaa solun indeksin
     * 
     * @param cell
     * @return int
     */
    public int indexOf(Cell cell) {
        for (int i = 0; i < size; i++) {
            if (cells[i].equals(cell)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return String
     */
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
