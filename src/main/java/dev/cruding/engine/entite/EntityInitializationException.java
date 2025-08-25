package dev.cruding.engine.entite;

public class EntityInitializationException extends RuntimeException {
    public EntityInitializationException(String message) {
        super(message);
    }

    public EntityInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
