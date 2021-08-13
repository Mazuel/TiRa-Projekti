package maze.util;

public class StackEmptyError extends RuntimeException {
    public StackEmptyError(String errorMessage) {
        super(errorMessage);
    }
}
