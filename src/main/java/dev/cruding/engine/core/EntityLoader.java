package dev.cruding.engine.core;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import dev.cruding.engine.entity.Entity;

public final class EntityLoader {

    public static void load(String path) {
        try (Stream<Path> files = Files.walk(Paths.get(path))) {
            files
                    .filter(Files::isRegularFile)
                    .filter(EntityLoader::isJavaFile)
                    .sorted()
                    .map(EntityLoader::loadEntityClass)
                    .forEach(Context.getInstance()::addEntity);
        } catch (Exception e) {
            throw new GeneratorException(String.format("Failed to load entities from directory: %s", path), e);
        }
    }

    private static boolean isJavaFile(Path file) {
        return file.toString().endsWith(".java");
    }

    private static Entity loadEntityClass(Path file) {
        try {
            String className = resolveClassName(file);
            Class<?> clazz = Class.forName(className);

            if (!Entity.class.isAssignableFrom(clazz)) {
                throw new GeneratorException(String.format("Entity class '%s' must extend Entity base class.", clazz.getSimpleName()));
            }

            return (Entity) clazz.getDeclaredConstructor().newInstance();

        } catch (ClassNotFoundException e) {
            throw new GeneratorException(String.format("Entity class not found for file: %s", file), e);
        } catch (NoSuchMethodException e) {
            throw new GeneratorException(String.format("Entity class must have default constructor: %s", file), e);
        } catch (GeneratorException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GeneratorException(String.format("Failed to instantiate entity class: %s", file), e);
        }
    }


    private static String resolveClassName(Path file) {
        Path normalizedFile = file.toAbsolutePath().normalize();

        Path relativePath = EnginePaths.sourceRoot.relativize(normalizedFile);
        String className = relativePath.toString().replace(File.separator, ".");

        if (className.endsWith(".java")) {
            return className.substring(0, className.length() - 5);
        }
        return className;
    }


    private EntityLoader() {}

}
