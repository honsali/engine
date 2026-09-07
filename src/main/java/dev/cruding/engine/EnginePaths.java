package dev.cruding.engine;

import java.nio.file.Path;

public final class EnginePaths {

    public static final Path sourceRoot = Path.of("src/main/java").toAbsolutePath().normalize();
    public static final Path modelPath = sourceRoot.resolve("model");
    public static Path outputRoot = Path.of("result").toAbsolutePath().normalize();

    private EnginePaths() {}
}
