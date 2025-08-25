package dev.cruding.engine.gen;

public class ContexteException extends RuntimeException {
    public ContexteException(String message) {
        super(message);
    }

    public ContexteException(String message, Throwable cause) {
        super(message, cause);
    }
}
