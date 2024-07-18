package dev.cruding.engine.gen;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {

    public static String getRelativePath(String localPath, String importedFile, boolean inElement) {
        Path pathLocal = Paths.get(localPath);
        Path pathImported = Paths.get(importedFile);
        Path pathRelative = pathLocal.relativize(pathImported);
        String p = pathRelative.toString().replace('\\', '/');
        if (inElement) {
            return "../" + p;
        }
        return p;
    }
}
