package dev.cruding.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Processeur;
import dev.cruding.engine.loader.EntiteLoader;
import dev.cruding.engine.loader.GeneratorException;
import dev.cruding.engine.loader.ModuleLoader;

public class App {

    public static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static final String MODEL_PATH = "src/main/java/modele";
    public static final String MODULES_PATH = "src/main/java/modules";

    public static void main(final String[] args) {
        try {
            long startTime = System.nanoTime();

            Contexte.getInstance().setBasePath("result");

            LOGGER.info("Loading entities from: {}", MODEL_PATH);
            (new EntiteLoader()).load(MODEL_PATH);

            LOGGER.info("Loading modules from: {}", MODULES_PATH);
            (new ModuleLoader()).load(MODULES_PATH);

            LOGGER.info("Initializing actions");
            Contexte.getInstance().initActions();

            LOGGER.info("Start generation...");
            (new Processeur()).executer();

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
