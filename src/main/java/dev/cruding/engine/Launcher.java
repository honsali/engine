package dev.cruding.engine;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Processeur;

public class Launcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(Launcher.class);
    private static final String ELEMENT_TYPES = "#Form#Etat#Action#Tableau#Filtre#";
    private static final String BASE_PATH = "src/main/java";
    private static final String MODEL_PATH = "src/main/java/modele";
    private static final String MODULES_PATH = "src/main/java/modules";

    public static void main(final String[] args) {
        try {
            long startTime = System.nanoTime();

            Contexte.getInstance().setBasePath("result");

            loadEntite(MODEL_PATH);
            Contexte.getInstance().initEntities();

            loadPage(MODULES_PATH);
            Contexte.getInstance().initPages();

            Contexte.getInstance().initActions();
            Processeur processeur = new Processeur();
            processeur.executer();

            long duration = (System.nanoTime() - startTime) / 1_000_000; // Convert to milliseconds
            LOGGER.info("Generation completed in {} ms", duration);

        } catch (Exception e) {
            LOGGER.error("Error during application generation", e);
            System.exit(1);
        }
    }

    private static void loadEntite(String path) {
        try (Stream<Path> files = Files.walk(Paths.get(path))) {
            files.filter(Files::isRegularFile).map(Launcher::newInstance).forEach(Contexte.getInstance()::add);
        } catch (Exception e) {
            LOGGER.error("Error loading entities from path: {}", path, e);
            throw new RuntimeException("Failed to load entities", e);
        }
    }

    private static void loadPage(String path) {
        try (Stream<Path> files = Files.walk(Paths.get(path))) {
            files.filter(Files::isRegularFile).filter(Launcher::isNotElement).map(Launcher::newInstance).forEach(Contexte.getInstance()::add);
        } catch (Exception e) {
            LOGGER.error("Error loading pages from path: {}", path, e);
            throw new RuntimeException("Failed to load pages", e);
        }
    }

    private static Object newInstance(Path file) {
        try {
            String className = resolveClassName(file, BASE_PATH);
            return Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            LOGGER.error("Error creating instance for file: {}", file, ex);
            throw new RuntimeException("Failed to create instance", ex);
        }
    }

    private static boolean isNotElement(Path file) {
        String fileName = file.getFileName().toString();
        return ELEMENT_TYPES.indexOf(fileName.substring(0, 4)) < 0;
    }

    private static String resolveClassName(Path file, String basePath) {
        Path relativePath = Paths.get(basePath).relativize(file);
        return relativePath.toString().replace(File.separator, ".").replace(".java", "");
    }
}
