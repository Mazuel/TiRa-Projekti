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

    public void push(Cell cell) {
        if (top == capacity - 1) {
            throw new StackOverflowError("Cannot push to full stack");
        }
        cellArray[++top] = cell;
    }

    public Cell pop() {
        if (isEmpty()) {
            throw new StackEmptyError("Cannot pop from empty stack");
        }
        return cellArray[top--];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = top; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);

            Cell temp = cellArray[i];
            cellArray[i] = cellArray[randomIndex];
            cellArray[randomIndex] = temp;
        }
    }

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
