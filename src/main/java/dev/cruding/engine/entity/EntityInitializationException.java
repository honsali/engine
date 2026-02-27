package dev.cruding.engine.entity;

public class EntityInitializationException extends RuntimeException {
    public EntityInitializationException(String message) {
        super(message);
    }

    public EntityInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
