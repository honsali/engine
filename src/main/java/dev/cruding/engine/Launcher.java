package dev.cruding.engine;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Processor;

public class Launcher {

    private static final String element = "#Form#Etat#";

    public static void main(final String[] args) throws Exception {

        if (args == null || args.length == 0) {
            System.out.println("Usage: java -jar cruding.jar <path> <projectName>");
            System.exit(0);
        }

        long deb = System.currentTimeMillis();

        String pathToDesc = args[0] + args[1] + "\\desc";
        String pathToResult = args[0] + args[1] + "\\result";
        String projectName = args[1];
        Context.getInstance().setBasePath(args[1]);
        loadEntity(pathToDesc + "\\src\\main\\java\\" + projectName + "\\modele");

        Context.getInstance().initEntities();

        loadPage(pathToResult + "\\src\\main\\java\\" + projectName + "\\modules");

        Context.getInstance().initPages();

        Processor processor = new Processor();
        processor.process();

        System.out.println(System.currentTimeMillis() - deb);

    }

    private static void loadEntity(String path) {
        try (Stream<Path> files = Files.walk(Paths.get(path))) {
            files.filter(Files::isRegularFile).map(Launcher::newInstance).forEach(Context::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadPage(String path) {
        try (Stream<Path> files = Files.walk(Paths.get(path))) {
            files.filter(Files::isRegularFile).filter(Launcher::isNotElement).map(Launcher::newInstance)
                    .forEach(Context::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object newInstance(Path file) {
        try {
            String className = StringUtils.substringBetween(file.toString(), "\\java\\", ".java").replace('\\', '.');
            return Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static boolean isNotElement(Path file) {
        String fileName = file.getFileName().toString();
        return element.indexOf(fileName.substring(0, 4)) < 0;
    }

}
