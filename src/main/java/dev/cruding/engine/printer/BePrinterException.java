package dev.cruding.engine.printer;

import dev.cruding.engine.loader.GeneratorException;

public class BePrinterException extends GeneratorException {

    public BePrinterException(String message) {
        super(message);
    }

    public BePrinterException(String message, Throwable cause) {
        super(message, cause);
    }
}
