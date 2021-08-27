package maze.util;

public class QueueEmptyException extends RuntimeException {
    public QueueEmptyException(String errorMessage) {
        super(errorMessage);
    }
}
