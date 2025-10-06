package dev.cruding.engine.loader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoaderUtils {

    public static final String BASE_PATH = "src/main/java";

    public static String resolveClassName(Path file) {
        Path relativePath = Paths.get(BASE_PATH).relativize(file);
        return relativePath.toString().replace(File.separator, ".").replace(".java", "");
    }

    public static boolean isJavaFile(Path file) {
        return file.toString().endsWith(".java");
    }

}
