package dev.cruding.engine.loader;

import java.io.File;
import java.nio.file.Path;
import dev.cruding.engine.EnginePaths;

public final class LoaderUtils {

    public static String resolveClassName(Path file) {
        Path normalizedFile = file.toAbsolutePath().normalize();
        if (!normalizedFile.startsWith(EnginePaths.sourceRoot)) {
            throw new GeneratorException(String.format("File %s is outside configured source root %s", normalizedFile, EnginePaths.sourceRoot));
        }

        Path relativePath = EnginePaths.sourceRoot.relativize(normalizedFile);
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
