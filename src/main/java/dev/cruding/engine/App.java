package dev.cruding.engine;

import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Processor;
import dev.cruding.engine.loader.EntityLoader;
import dev.cruding.engine.loader.GeneratorException;
import dev.cruding.engine.loader.LoaderUtils;
import dev.cruding.engine.loader.ProjectBootstrapLoader;

public class App {

    public static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args) {
        try {
            long startTime = System.nanoTime();

            Path basePath = LoaderUtils.getBasePath();
            Path modelPath = LoaderUtils.getModelPath();
            Path modulesPath = LoaderUtils.getModulesPath();

            Context.getInstance().setBasePath("result");

            LOGGER.info("Using engine base path: {}", basePath);

            LOGGER.info("Loading entities from: {}", modelPath);
            (new EntityLoader()).load(modelPath.toString());

            LOGGER.info("Loading project bootstrap from: {}", modulesPath);
            (new ProjectBootstrapLoader()).load(modulesPath.toString());

            LOGGER.info("Initializing entities");
            Context.getInstance().initEntities();

            LOGGER.info("Initializing pages");
            Context.getInstance().initPages();

            LOGGER.info("Initializing actions");
            Context.getInstance().initActions();

            LOGGER.info("Start generation...");
            (new Processor()).execute();

            long duration = (System.nanoTime() - startTime) / 1_000_000;
            LOGGER.info("Generation completed successfully in {} ms", duration);

        } catch (GeneratorException e) {
            LOGGER.error("Generation failed: {}", e.getMessage());
            if (e.getCause() != null) {
                LOGGER.error("Caused by: {}", e.getCause().getMessage());
            }
            System.exit(1);
        } catch (Exception e) {
            LOGGER.error("Unexpected error during generation", e);
            System.exit(1);
        }
    }

}
