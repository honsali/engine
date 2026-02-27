package dev.cruding.engine.loader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class LoaderUtils {

    private static Path BASE_PATH = Paths.get("src/main/java").toAbsolutePath().normalize();

    public static Path getBasePath() {
        return BASE_PATH;
    }

    public static Path getModelPath() {
        return BASE_PATH.resolve("model");
    }

    public static Path getModulesPath() {
        return BASE_PATH.resolve("modules");
    }

    public static String resolveClassName(Path file) {
        Path normalizedFile = file.toAbsolutePath().normalize();
        if (!normalizedFile.startsWith(BASE_PATH)) {
            throw new GeneratorException(String.format("File %s is outside configured base path %s", normalizedFile, BASE_PATH));
        }

        Path relativePath = BASE_PATH.relativize(normalizedFile);
        String className = relativePath.toString().replace(File.separator, ".");

        if (className.endsWith(".java")) {
            return className.substring(0, className.length() - 5);
        }
        return className;
    }

    public static boolean isJavaFile(Path file) {
        return file.toString().endsWith(".java");
    }

}
