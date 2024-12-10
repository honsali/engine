package dev.cruding.engine;

import java.io.File;

public class FileCounter {

    public static void main(String[] args) {
        File directory = new File("C:\\singen\\engineBeFe02.12.2024\\result\\fe\\src\\modules\\instruction\\tiers"); // Replace "path/to/directory" with the actual directory path
        int count = countFiles(directory);
        System.out.println("Total number of files: " + count);
    }

    public static int countFiles(File directory) {
        int fileCount = 0;
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileCount++;
                } else if (file.isDirectory()) {
                    fileCount += countFiles(file);
                }
            }
        }
        return fileCount;
    }
}
