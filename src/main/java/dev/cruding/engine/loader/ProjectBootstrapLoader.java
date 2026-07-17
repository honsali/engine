package dev.cruding.engine.loader;

import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.ProjectBootstrap;

public class ProjectBootstrapLoader {

    public void load(String path) {
        try (Stream<Path> files = Files.walk(Paths.get(path))) {
            List<Class<? extends ProjectBootstrap>> bootstrapClasses = new ArrayList<>();
            files.filter(Files::isRegularFile)
                    .filter(LoaderUtils::isJavaFile)
                    .forEach(file -> {
                        Class<?> clazz = loadClass(file);
                        if (directlyImplementsProjectBootstrap(clazz)) {
                            bootstrapClasses.add(asProjectBootstrapClass(clazz));
                        }
                    });

            if (bootstrapClasses.isEmpty()) {
                throw new GeneratorException(String.format("No project bootstrap found in directory: %s.", path));
            }
            if (bootstrapClasses.size() > 1) {
                throw new GeneratorException(String.format("Multiple project bootstraps found in directory: %s: %s.", path, bootstrapClasses.stream().map(Class::getName).toList()));
            }

            ProjectBootstrap bootstrap = instantiate(bootstrapClasses.get(0));
            Context.getInstance().setGeneratedResourceAuthority(bootstrap.generatedResourceAuthority());
            bootstrap.init();
        } catch (GeneratorException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneratorException(String.format("Failed to load project bootstrap from directory: %s.", path), e);
        }
    }

    private Class<?> loadClass(Path file) {
        try {
            return Class.forName(LoaderUtils.resolveClassName(file));
        } catch (ClassNotFoundException e) {
            throw new GeneratorException(String.format("Class not found for file: %s.", file), e);
        }
    }

    private boolean directlyImplementsProjectBootstrap(Class<?> clazz) {
        return !Modifier.isAbstract(clazz.getModifiers()) && Arrays.asList(clazz.getInterfaces()).contains(ProjectBootstrap.class);
    }

    @SuppressWarnings("unchecked")
    private Class<? extends ProjectBootstrap> asProjectBootstrapClass(Class<?> clazz) {
        return (Class<? extends ProjectBootstrap>) clazz;
    }

    private ProjectBootstrap instantiate(Class<? extends ProjectBootstrap> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new GeneratorException(String.format("Project bootstrap must have a default constructor: %s.", clazz.getName()), e);
        } catch (Exception e) {
            throw new GeneratorException(String.format("Failed to instantiate project bootstrap: %s.", clazz.getName()), e);
        }
    }
}
