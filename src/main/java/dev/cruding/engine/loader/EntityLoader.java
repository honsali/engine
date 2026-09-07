package dev.cruding.engine.loader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Context;

public final class EntityLoader {

    private EntityLoader() {}

    public static void load(Context context, String path) {
        Objects.requireNonNull(context, "EntityLoader Context cannot be null");
        try (Stream<Path> files = Files.walk(Paths.get(path))) {
            files.filter(Files::isRegularFile)
                    .filter(LoaderUtils::isJavaFile)
                    .sorted()
                    .map(EntityLoader::loadEntityClass)
                    .forEach(context::addEntity);
        } catch (Exception e) {
            throw new GeneratorException(String.format("Failed to load entities from directory: %s", path), e);
        }
    }


    private static Entity loadEntityClass(Path file) {
        try {
            String className = LoaderUtils.resolveClassName(file);
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


}
