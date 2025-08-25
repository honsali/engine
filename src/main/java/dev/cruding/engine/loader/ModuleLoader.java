package dev.cruding.engine.loader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Module;

public class ModuleLoader {

    public static final Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    public void load(String path) {
        try (Stream<Path> files = Files.walk(Paths.get(path))) {
            files.filter(Files::isRegularFile).filter(Launcher::isJavaFile).filter(file -> isModule(file)).map(file -> loadModuleClass(file)).forEach(Contexte.getInstance()::add);
        } catch (Exception e) {
            throw new GeneratorException(String.format("Failed to load modules from directory: %s.", path), e);
        }
        Contexte.getInstance().initPages();
    }


    private Object loadModuleClass(Path file) {
        try {
            String className = Launcher.resolveClassName(file);
            Class<?> clazz = Class.forName(className);

            if (!Module.class.isAssignableFrom(clazz)) {
                throw new GeneratorException(String.format("Module class '%s' must extend  Module base class.", clazz.getSimpleName(), file));
            }

            return clazz.getDeclaredConstructor().newInstance();

        } catch (ClassNotFoundException e) {
            throw new GeneratorException(String.format("Module class not found for file: %s.", file), e);
        } catch (NoSuchMethodException e) {
            throw new GeneratorException(String.format("Module class must have default constructor: %s.", file), e);
        } catch (GeneratorException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GeneratorException(String.format("Failed to instantiate module class: %s.", file), e);
        }
    }

    private boolean isModule(Path file) {
        String fileName = file.getFileName().toString();
        return fileName.startsWith("Module");
    }


}
