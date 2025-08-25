package dev.cruding.engine.loader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.gen.Contexte;

public class EntiteLoader {


    private static final Logger LOGGER = LoggerFactory.getLogger(EntiteLoader.class);

    public void load(String path) {
        try (Stream<Path> files = Files.walk(Paths.get(path))) {
            files.filter(Files::isRegularFile).filter(Launcher::isJavaFile).map(file -> loadEntityClass(file)).forEach(Contexte.getInstance()::add);
        } catch (Exception e) {
            throw new GeneratorException(String.format("Failed to load entities from directory: %s", path), e);
        }
        Contexte.getInstance().initEntities();
    }


    private Object loadEntityClass(Path file) {
        try {
            String className = Launcher.resolveClassName(file);
            Class<?> clazz = Class.forName(className);

            if (!Entite.class.isAssignableFrom(clazz)) {
                throw new GeneratorException(String.format("Entity class '%s' must extend Entite base class.", clazz.getSimpleName(), file));
            }

            return clazz.getDeclaredConstructor().newInstance();

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
