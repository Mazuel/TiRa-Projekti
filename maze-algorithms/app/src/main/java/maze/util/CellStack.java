package maze.util;

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

}
