package dev.cruding.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Processor;
import dev.cruding.engine.loader.EntityLoader;
import dev.cruding.engine.loader.GeneratorException;
import modules.ProjectBootstrap;

public class App {

    public static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args) {
        try {
            long startTime = System.nanoTime();

            Context.init();

            LOGGER.info("Using engine source root: {}", EnginePaths.sourceRoot);
            LOGGER.info("Using engine output root: {}", EnginePaths.outputRoot);

            LOGGER.info("Loading entities from: {}", EnginePaths.modelPath);
            EntityLoader.load(EnginePaths.modelPath.toString());

            LOGGER.info("Initializing project modules");
            ProjectBootstrap.init();

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
