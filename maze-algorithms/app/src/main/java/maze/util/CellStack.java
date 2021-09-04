package maze.util;

import java.util.Random;

public class CellStack {
    private Cell cellArray[];
    private int top;
    private int capacity;

    public CellStack(int size) {
        cellArray = new Cell[size];
        capacity = size;
        top = -1;
    }

    /**
     * Pusketaan pinoon solu
     * 
     * @throws StackOverflowError
     * @param cell
     */
    public void push(Cell cell) {
        if (top == capacity - 1) {
            throw new StackOverflowError("Cannot push to full stack");
        }
        cellArray[++top] = cell;
    }

    /**
     * Poistetaan pinosta päällimmäinen solu
     * 
     * @throws StackEmptyError
     * @return Cell
     */
    public Cell pop() {
        if (isEmpty()) {
            throw new StackEmptyError("Cannot pop from empty stack");
        }
        return cellArray[top--];
    }

    /**
     * Palauttaa pinon koon
     * 
     * @return int
     */
    public int size() {
        return top + 1;
    }

    /**
     * Palauttaa totuusarvon siitä onko pino tyhjä
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * @return boolean
     */
    public boolean isNotEmpty() {
        return !isEmpty();
    }

    /**
     * Sekoittaa pinon satunnaiseen järjestykseen
     */
    public void shuffle() {
        Random random = new Random();
        for (int i = top; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);

            Cell temp = cellArray[i];
            cellArray[i] = cellArray[randomIndex];
            cellArray[randomIndex] = temp;
        }
    }

    /**
     * @return String
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= top; i++) {
            sb.append(cellArray[i].toString());
            if (i < top - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
