package dev.cruding.engine.gen.helper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import dev.cruding.engine.champ.Champ;

public class Util {
    public static int findFirstCapitalIndex(String word) {
        for (int i = 1; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                return i;
            }

        }
        return -1;
    }

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

    public static StringBuilder processListeChamp(Champ[] listeChamp, String elementType) {

        HashSet<String> listeType = new HashSet<>();

        for (Champ field : listeChamp) {
            if (field != null) {
                listeType.add(field.ui(elementType));
            } else {
                System.out.println("Champ is null");

            }
        }
        StringBuilder sb = new StringBuilder();
        if (listeType.size() > 0) {
            Object[] array = listeType.toArray();
            Arrays.sort(array);
            for (Object type : array) {
                sb.append(type).append(", ");
            }
        }
        return sb;
    }


    public static Path calculateRelativePath(String pathFile1, String pathFile2) {
        Path file1Path = Paths.get(pathFile1).toAbsolutePath();
        Path file2Path = Paths.get(pathFile2).toAbsolutePath();

        return file1Path.relativize(file2Path);
    }
}
